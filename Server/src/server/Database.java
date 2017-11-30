package server;

import java.sql.*;

import com.google.gson.*;

/**
 * Class to execute, connect and manage database
 * 
 * @see java.sql.Connection
 * @see java.sql.Statement
 * @see java.sql.DriverManager
 * @see java.sql.ResultSet
 * @see server.OpType
 */
public class Database {
	/**
	 * Provides connection to DB
	 */
	private Connection connection;

	/**
	 * Provides query execution
	 */
	private Statement statement;

	/**
	 * Class constructor, which connects to the DB bank_users with parameters:
	 * 
	 * @param url
	 *          determines the driver, ip, port and DB name in given format:<br>
	 *          jdbc:mysql://ip:port/database<br>
	 *          default example:<br>
	 *          "jdbc:mysql://localhost:3306/bank_users"
	 * @param user
	 *          user, that connects to DB. "root" by default
	 * @param password
	 *          users password. "root" by default
	 */
	public Database(String url, String user, String password) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Constructor by default
	 */
	public Database() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_users", "root",
					"root");
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Method to process user request
	 * 
	 * @param request
	 *          params of operation to execute in JSON format
	 * @return result of operation in JSON format
	 */
	public String processInput(String request) {
		Gson gson = new Gson();
		String[] args = gson.fromJson(request, String[].class);

		switch (args[0]) {
		case "login": {
			if (logIn(args[1], args[2])) {
				UserInfo user = getUserInfo(args[1]);
				String[] send = { "success", String.valueOf(user.userId), String.valueOf(user.balance),
						user.secretQuestion, user.birthDate.toString(), String.valueOf(user.status) };
				return gson.toJson(send);
			} else {
				String[] send = { "error", "wrong login or pin" };
				return gson.toJson(send);
			}
		}
		case "registry": {
			if (registryNewUser(args[1], args[2], Date.valueOf(args[3]), args[4], args[5])) {
				String[] send = { "success", "welcome" };
				return gson.toJson(send);
			} else {
				String[] send = { "error", "already have user with this login" };
				return gson.toJson(send);
			}
		}
		case "transfer": {
			if (transfer(Integer.valueOf(args[1]), Integer.valueOf(args[2]), Double.valueOf(args[3]))) {
				String[] send = { "success", "money transfer completed" };
				return gson.toJson(send);
			} else {
				String[] send = { "error", "try again to enter the parameters" };
				return gson.toJson(send);
			}
		}
		case "withdrawal": {
			if (withdrawal(Integer.valueOf(args[1]), Double.valueOf(args[2]))) {
				String[] send = { "success", "money withdrawal completed" };
				return gson.toJson(send);
			} else {
				String[] send = { "error", "not enought money" };
				return gson.toJson(send);
			}
		}
		case "refill": {
			if (refill(Integer.valueOf(args[1]), Double.valueOf(args[2]))) {
				String[] send = { "success", "money refill completed" };
				return gson.toJson(send);
			} else {
				String[] send = { "error", "try again" };
				return gson.toJson(send);
			}
		}
		case "update": {
			UserInfo user = getUserInfo(args[1]);
			String[] send = { "success", String.valueOf(user.userId), String.valueOf(user.balance),
					user.secretQuestion, user.birthDate.toString() };
			return gson.toJson(send);
		}
		case "checkQuestion": {
			if(checkQuestion(String.valueOf(args[1]), String.valueOf(args[2]))) {
				String[] send = { "success", "Answer is correct" };
				return gson.toJson(send);
			} else {
				String[] send = { "error", "try again" };
				return gson.toJson(send);
			}
		}
		
		
		case "changePin": {
			if(changePin(String.valueOf(args[1]), Integer.valueOf(args[2]))) {
				String[] send = { "success", "pin is change" };
				return gson.toJson(send);
			} else {
				String[] send = { "error", "try again" };
				return gson.toJson(send);
			}
			
		}
		default:
			String[] send = { "error", "unknown operation" };
			return gson.toJson(send);
		}
	}

	/**
	 * Method to execute specific query
	 * 
	 * @param type
	 *          determines a type of query(insert||select||update)
	 * @param query
	 *          string to execute by DB
	 */
	public Object execute(OpType type, String query) throws SQLException {
		if (type == OpType.INSERT || type == OpType.UPDATE) {
			return statement.executeUpdate(query);
		} else if (type == OpType.SELECT) {
			return statement.executeQuery(query);
		} else {
			return null;
		}
	}

	/**
	 * Method to round balance of all users
	 */
	private void round() throws SQLException {
		execute(OpType.UPDATE, "update users set balance = round(balance, 2);");
	}

	/**
	 * Registrates new user<br>
	 * Generates a unique user_id
	 * 
	 * @param pin
	 *          4-digit number(password for new user)
	 * @param userLogin
	 *          login for new user, must be unique
	 * @param birthDate
	 *          birthdate for new user(can get by Date.valueOf(string in format
	 *          yyyy-mm-dd))
	 * @param secretQuestion
	 *          question used to confirmation
	 * @param secretAnswer
	 *          answer used to confirmation
	 * @return false because of error
	 */
	public boolean registryNewUser(String pin, String userLogin, Date birthDate,
			String secretQuestion, String secretAnswer) {
		try {
			ResultSet maximum = (ResultSet) execute(OpType.SELECT, "select max(user_id) from users;");
			int max = 0;
			while (maximum.next()) {
				max = maximum.getInt(1);
			}
			maximum.close();
			execute(OpType.INSERT,
					"insert into users values(" + ++max + "," + " 0, '" + pin + "'," + " '"
							+ birthDate.toString() + "'," + " '" + userLogin + "'," + " '" + secretQuestion + "',"
							+ " '" + secretAnswer + "'," + " true);");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Method to authentificate user
	 * 
	 * @param pin
	 *          users password
	 * @param userLogin
	 *          login of a user
	 * @return true if confirmed, false if not
	 */
	public boolean logIn(String userLogin, String pin) {
		try {
			ResultSet client = (ResultSet) execute(OpType.SELECT, "select user_login, pin from users "
					+ "where pin = '" + pin + "' and user_login = '" + userLogin + "';");
			if (!client.first()) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Withdrawal money from account
	 * 
	 * @param userId
	 *          account
	 * @param money
	 *          amount of money to withdraw
	 * @return false if userId is unknown or if not enought money on balance
	 */
	public boolean withdrawal(int userId, double money) {
		if (money < 0) {
			return false;
		}
		try {
			ResultSet hasUser = (ResultSet) execute(OpType.SELECT,
					"select user_id from users where user_id = " + userId + ";");
			if (!hasUser.first()) {
				return false;
			}
			ResultSet enoughtMoney = (ResultSet) execute(OpType.SELECT,
					"select balance from users where user_id = " + userId + ";");
			double balance = 0d;
			while (enoughtMoney.next()) {
				balance = enoughtMoney.getDouble(1);
			}
			enoughtMoney.close();
			if (money > balance) {
				return false;
			}
			execute(OpType.UPDATE,
					"update users set balance = balance - " + money + " where user_id = " + userId + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Refill money in account
	 * 
	 * @param userId
	 *          account
	 * @param money
	 *          amount of money to refill
	 * @return false if userId is unknown
	 */
	public boolean refill(int userId, double money) {
		if (money < 0) {
			return false;
		}
		try {
			ResultSet hasUser = (ResultSet) execute(OpType.SELECT,
					"select user_id from users where user_id = " + userId + ";");
			if (!hasUser.first()) {
				return false;
			}
			hasUser.close();
			execute(OpType.UPDATE,
					"update users set balance = balance + " + money + " where user_id = " + userId + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Transfer money from one account to another
	 * 
	 * @param userIdFrom
	 *          account to withdraw
	 * @param userIdTo
	 *          account to refill
	 * @param money
	 *          amount of money to transfer
	 * @return false if any of accounts unknown or<br>
	 *         if userIdFrom have not enought money to transfer
	 */
	public boolean transfer(int userIdFrom, int userIdTo, double money) {
		if (money < 0) {
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

			ResultSet enoughtMoney = (ResultSet) execute(OpType.SELECT,
					"select balance from users where user_id = " + userIdFrom + ";");
			double balance = 0d;
			while (enoughtMoney.next()) {
				balance = enoughtMoney.getDouble(1);
			}
			enoughtMoney.close();
			if (balance < money) {
				return false;
			}

			execute(OpType.UPDATE,
					"update users set balance = balance - " + money + " where user_id = " + userIdFrom + ";");
			execute(OpType.UPDATE,
					"update users set balance = balance + " + money + " where user_id = " + userIdTo + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}

	/**
	 * Method to get user information
	 * 
	 * @param userLogin
	 *          account identifier
	 * @return object of class UserInfo
	 */
	public UserInfo getUserInfo(String userLogin) {
		try {
			ResultSet result = (ResultSet) execute(OpType.SELECT,
					"select * from users where user_login = '" + userLogin + "';");
			UserInfo client = new UserInfo();
			while (result.next()) {
				client.userId = result.getInt(1);
				client.balance = result.getDouble(2);
				client.pin = result.getString(3);
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

	public boolean checkQuestion (String userLogin, String secretAnswer) {
		try {
			ResultSet client = (ResultSet) execute(OpType.SELECT, "select user_login, secretAnswer from users "
					+ "where secretAnswer = '" + secretAnswer + "' and user_login = '" + userLogin + "';");
			if (!client.first()) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		
	}
	
	public boolean changePin (String userLogin, int pin) {
		try {
			ResultSet hasUser = (ResultSet) execute(OpType.SELECT,
					"select user_login from users where user_login = " + userLogin + ";");
			if (!hasUser.first()) {
				return false;
			}
			hasUser.close();
			execute(OpType.UPDATE,
					"update users set pin = " + pin + " where user_login = " + userLogin + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
		
	}
	
	
	
	/**
	 * Close connection with database<br>
	 * Call every time (when finished working with DB) to avoid resource leak
	 */
	public void closeConnection() {
		try {
			connection.close();
			statement.close();
		} catch (SQLException e) {
		}
	}
}
