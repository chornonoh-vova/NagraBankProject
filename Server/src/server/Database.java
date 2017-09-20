package server;

import java.sql.*;

public class Database {
	//format of url(jdbc:mysql://ip:port/database): "jdbc:mysql://localhost:3306/bank_users";
	//default user: "root";
	//default password: "root";

	private Connection connection;
	private Statement statement;

	public Database(String url, String user, String password) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	private Object execute(String type, String query) throws SQLException {
		if (type.equals("insert") || type.equals("update")) {
			return statement.executeUpdate(query);
		} else if (type.equals("select")) {
			return statement.executeQuery(query);
		} else {
			return null;
		}
	}

	private void round() throws SQLException {
		execute("update", "update users set balance = round(balance, 2);");
	}

	public boolean registryNewUser(int pin, String userLogin, Date birthDate,
		String secretQuestion, String secretAnswer) {
		try {
			ResultSet maximum = (ResultSet) execute("select", 
					"select max(user_id) from users;");
			int max = 0;
			while (maximum.next()) {
				max = maximum.getInt(1);
			}
			maximum.close();
			execute("insert", "insert into users values("
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

	public boolean logIn(int pin, String userLogin) {
		try {
			ResultSet client = (ResultSet) execute("select", 
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

	public boolean withdrawal(int userId, double money) {
		if(money < 0) {
			return false;
		}
		try {
			ResultSet hasUser = (ResultSet) execute("select", 
					"select user_id from users where user_id = " + userId + ";" );
			if (!hasUser.first()) {
				return false;
			}
			ResultSet enoughtMoney = (ResultSet) execute("select", 
					"select balance from users where user_id = "+ userId +";");
			double balance = 0d;
			while(enoughtMoney.next()) {
				balance = enoughtMoney.getDouble(1);
			}
			enoughtMoney.close();
			if(money > balance) {
				return false;
			}
			execute("update", "update users set balance = balance - " + money
					+ " where user_id = " + userId + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean refill (int userId, double money) {
		if(money < 0) {
			return false;
		}
		try {
			ResultSet hasUser = (ResultSet) execute("select", 
					"select user_id from users where user_id = " + userId + ";" );
			if (!hasUser.first()) {
				return false;
			}
			hasUser.close();
			execute("update", "update users set balance = balance + " + money
					+ " where user_id = "+ userId + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public boolean transfer(int userIdFrom, int userIdTo, double money) {
		if(money < 0) {
			return false;
		}
		try {
			ResultSet hasUserFrom = (ResultSet) execute("select", 
					"select user_id from users where user_id = " + userIdFrom + ";");
			if (!hasUserFrom.first()) {
				return false;
			}
			hasUserFrom.close();
			ResultSet hasUserTo = (ResultSet) execute("select", 
					"select user_id from users where user_id = " + userIdTo + ";");
			if (!hasUserTo.first()) {
				return false;
			}
			hasUserTo.close();

			ResultSet enoughtMoney =(ResultSet) execute("select", 
					"select balance from users where user_id = " + userIdFrom + ";");
			double balance = 0d;
			while (enoughtMoney.next()) {
				balance = enoughtMoney.getDouble(1);
			}
			enoughtMoney.close();
			if (balance < money) {
				return false;
			}

			execute("update", "update users set balance = balance - " + money
					+ " where user_id = "+ userIdFrom + ";");
			execute("update", "update users set balance = balance + " + money
					+ " where user_id = "+ userIdTo + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	public UserInfo getUserInfo(int userId) {
		try {
			ResultSet result = (ResultSet) execute("select", 
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
