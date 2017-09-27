package server;

import java.sql.*;

public class Database {
	/*
	 * Provides connection to DB
	 */
	private Connection connection;
	
	/*
	 * Provides query execution
	 */
	private Statement statement;

	/*
	 * Class constructor, which connects to the DB bank_users with parameters:
	 * @param url determines the driver, ip, port and DB name in given format:
	 * jdbc:mysql://ip:port/database default example: "jdbc:mysql://localhost:3306/bank_users"
	 * @param user user, that connects to DB. "root" by default
	 * @param password users password. "root" by default
	 */
	public Database(String url, String user, String password) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/*
	 * Method to execute specific query
	 * @param type determines a type of query(insert||select||update)
	 * @param query string to execute by DB
	 */
	private Object execute(OpType type, String query) throws SQLException {
		if (type == OpType.INSERT || type == OpType.UPDATE) {
			return statement.executeUpdate(query);
		} else if (type == OpType.SELECT) {
			return statement.executeQuery(query);
		} else {
			return null;
		}
	}

	/*
	 * Method to round balance of all users
	 */
	private void round() throws SQLException {
		execute(OpType.UPDATE, "update users set balance = round(balance, 2);");
	}

	/*
	 * Registrates new user
	 * Generates a unique user_id
	 * @param pin 4-digit number(password for new user)
	 * @param userLogin login for new user, must be unique
	 * @param birthDate birthdate for new user(can get by Date.valueOf(string in format yyyy-mm-dd))
	 * @param secretQuestion question used to confirmation
	 * @param secretAnswer answer used to confirmation
	 * @return false because of error
	 */
	public boolean registryNewUser(int pin, String userLogin, Date birthDate,
		String secretQuestion, String secretAnswer) {
		try {
			ResultSet maximum = (ResultSet) execute(OpType.SELECT,
					"select max(user_id) from users;");
			int max = 0;
			while (maximum.next()) {
				max = maximum.getInt(1);
			}
			maximum.close();
			execute(OpType.INSERT, "insert into users values("
					+ ++max + ","
					+ " 0,"
					+ pin + ","
					+ " '" + birthDate.toString() + "',"
					+ " '" + userLogin + "',"
					+ " '" + secretQuestion + "',"
					+ " '" + secretAnswer + "',"
					+ " true);");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/*
	 * Method to authentificate user
	 * @param pin users password
	 * @param userLogin login of a user
	 * @return boolean(true if confirmed, false if not)
	 */
	public boolean logIn(int pin, String userLogin) {
		try {
			ResultSet client = (ResultSet) execute(OpType.SELECT,
					"select pin, user_login from users "
					+ "where pin = " + pin + " and user_login = '" + userLogin + "';");
			if (!client.first()) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/*
	 * Withdrawal money from account
	 * @param userId account
	 * @param money amount of money to withdraw
	 * false if userId is unknown
	 * false if not enought money on balance
	 */
	public boolean withdrawal(int userId, double money) {
		if(money < 0) {
			return false;
		}
		try {
			ResultSet hasUser = (ResultSet) execute(OpType.SELECT,
					"select user_id from users where user_id = " + userId + ";" );
			if (!hasUser.first()) {
				return false;
			}
			ResultSet enoughtMoney = (ResultSet) execute(OpType.SELECT,
					"select balance from users where user_id = "+ userId +";");
			double balance = 0d;
			while(enoughtMoney.next()) {
				balance = enoughtMoney.getDouble(1);
			}
			enoughtMoney.close();
			if(money > balance) {
				return false;
			}
			execute(OpType.UPDATE, "update users set balance = balance - " + money
					+ " where user_id = " + userId + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/*
	 * Refill money in account
	 * @param userId account
	 * @param money amount of money to refill
	 * false if userId is unknown
	 */
	public boolean refill (int userId, double money) {
		if(money < 0) {
			return false;
		}
		try {
			ResultSet hasUser = (ResultSet) execute(OpType.INSERT,
					"select user_id from users where user_id = " + userId + ";" );
			if (!hasUser.first()) {
				return false;
			}
			hasUser.close();
			execute(OpType.UPDATE, "update users set balance = balance + " + money
					+ " where user_id = "+ userId + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/*
	 * Transfer money from one account to another
	 * @param userIdFrom account to withdraw
	 * @param userIdTo account to refill
	 * @param money amount of money to transfer
	 * false if any of accounts unknown
	 * false if userIdFrom have not enought money to transfer
	 */
	public boolean transfer(int userIdFrom, int userIdTo, double money) {
		if(money < 0) {
			return false;
		}
		try {
			ResultSet hasUserFrom = (ResultSet) execute(OpType.SELECT,
					"select user_id from users where user_id = " + userIdFrom + ";");
			if (!hasUserFrom.first()) {
				return false;
			}
			hasUserFrom.close();
			ResultSet hasUserTo = (ResultSet) execute(OpType.SELECT,
					"select user_id from users where user_id = " + userIdTo + ";");
			if (!hasUserTo.first()) {
				return false;
			}
			hasUserTo.close();

			ResultSet enoughtMoney =(ResultSet) execute(OpType.SELECT,
					"select balance from users where user_id = " + userIdFrom + ";");
			double balance = 0d;
			while (enoughtMoney.next()) {
				balance = enoughtMoney.getDouble(1);
			}
			enoughtMoney.close();
			if (balance < money) {
				return false;
			}

			execute(OpType.UPDATE, "update users set balance = balance - " + money
					+ " where user_id = "+ userIdFrom + ";");
			execute(OpType.UPDATE, "update users set balance = balance + " + money
					+ " where user_id = "+ userIdTo + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/*
	 * Method to get user information
	 * @param userId account identifier
	 * @return object of class UserInfo
	 */
	public UserInfo getUserInfo(int userId) {
		try {
			ResultSet result = (ResultSet) execute(OpType.SELECT,
					"select * from users where user_id = " + userId + ";");
			UserInfo client = new UserInfo();
			while(result.next()) {
				client.userId = result.getInt(1);
				client.balance = result.getDouble(2);
				client.pin = result.getInt(3);
				client.birthDate = result.getDate(4);
				client.userLogin = result.getString(5);
				client.secretQuestion = result.getString(6);
				client.secretAnswer = result.getString(7);
				client.status = result.getBoolean(8);
			}
			result.close();
			return client;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void closeConnection() {
		try {
			connection.close();
			statement.close();
		} catch (SQLException e) {}
	}
}
