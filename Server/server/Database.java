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

	public Object execute(String type, String query) throws SQLException {
		if (type.equals("insert") || type.equals("update")) {
			return statement.executeUpdate(query);
		} else if (type.equals("select")) {
			return statement.executeQuery(query);
		} else {
			return null;
		}
	}

	public boolean registryNewUser(int pin, String user_login, Date birthdate,
		String secretQuestion, String secretAnswer) {
		try {
			ResultSet maximum = (ResultSet) execute("select", "select max(user_id) from users;");
			int max = 0;
			while (maximum.next()) {
				max = maximum.getInt(1);
			}
			maximum.close();
			execute("insert", "insert into users values("
					+ ++max + ","
					+ " 0,"
					+ pin + ","
					+ " '" + birthdate.toString(	) + "',"
					+ " '" + user_login + "',"
					+ " '" + secretQuestion + "',"
					+ " '" + secretAnswer + "',"
					+ " true);");
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public boolean login(int pin, String user_login)
{
	
	try {execute("select", "select pin, user_login from users where pin==pin1 and user_login==user_login1;");	
		
	
	
	} catch (SQLException e) {
		System.out.println(e.getMessage());
		return false;
		}
	return false; 
	}

	/*public boolean transfer(String user_id_from, String user_id_to, double money) {
		if(money < 0) {
			return false;
		}
		
	}*/
	public void closeConnection() {
		try {
			connection.close();
			statement.close();
		} catch (SQLException e) {}
	}
	
}
