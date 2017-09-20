package server;

import java.sql.*;

public class Database {
	//format of url(jdbc:mysql://ip:port/database): "jdbc:mysql://localhost:3306/bank_users";
	//default user: "root";
	//default password: "root";

	private Connection connection;
	private Statement statement;

	//Конструктор 
	public Database(String url, String user, String password) {
		try {
			connection = DriverManager.getConnection(url, user, password);
			statement = connection.createStatement();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	//Выполнение SQL запроса
	private Object execute(String type, String query) throws SQLException {
		if (type.equals("insert") || type.equals("update")) {
			return statement.executeUpdate(query);
		} else if (type.equals("select")) {
			return statement.executeQuery(query);
		} else {
			return null;
		}
	}

	//Регистрация клиентов 
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
	
	//Авторизация пользователя
	public boolean logIn(int pin, String user_login) {
		try {
			ResultSet client = (ResultSet) execute("select", "select pin, user_login from users where pin = " + pin + " and user_login = '" + user_login + "';");	
			if (!client.first()) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	//Снятие денег со счета 
	public boolean withdrawal (String login, double money) {
		if(money < 0) {
			return false;
		}
		try {
			ResultSet enought_money = (ResultSet) execute("select", "select balance from users where user_login = '"+ login +"';");
			double balance = 0d;
			while(enought_money.next()) {
				balance = enought_money.getDouble(1);
			}
			enought_money.close();
			if(money > balance) {
				return false;
			}
			execute("update", "update users set balance = balance - " + money
					+ " where user_login = '"+ login + "';");
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	//Перевод денег между клиентами
	public boolean transfer(int user_id_from, int user_id_to, double money) {
		if(money < 0) {
			return false;
		}		
		try {
			//Проверка на наличие клиента от которого передаем деньги
			//Может выбрасывать исключение SQLException
			ResultSet has_user_from = (ResultSet) execute("select", "select user_id from users where user_id = " + user_id_from + ";");
			if (!has_user_from.first()) {
				return false;
			}
			has_user_from.close();
			//Проверка на наличие клиента который получает деньги
			//Может выбрасывать исключение SQLException
			ResultSet has_user_to = (ResultSet) execute("select", "select user_id from users where user_id = " + user_id_to + ";");
			if (!has_user_to.first()) {
				return false;
			}
			has_user_to.close();
			//Проверка на достаточное количество денег у клиента который переводит сумму
			//Может выбрасывать исключение SQLException
			ResultSet enought_money =(ResultSet) execute("select", "select balance from users where user_id = " + user_id_from + ";");
			double balance = 0d;
			while (enought_money.next()) {
				balance = enought_money.getDouble(1);
			}
			enought_money.close();
			if (balance < money) {
				return false;
			}
			//Перевод денег с одного на другой аккаунт
			//Может выбрасывать исключение SQLException
			execute("update", "update users set balance = balance - " + money
					+ " where user_id = "+ user_id_from + ";");
			execute("update", "update users set balance = balance + " + money
					+ " where user_id = "+ user_id_to + ";");
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	public void closeConnection() {
		try {
			connection.close();
			statement.close();
		} catch (SQLException e) {}
	}
	
}
