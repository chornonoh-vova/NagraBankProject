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

	private void round() throws SQLException {
		execute("update", "update users set balance = round(balance, 2);");
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
			round();
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
	public boolean withdrawal (int user_id, double money) {
		if(money < 0) {
			return false;
		}
		try {
			ResultSet has_user = (ResultSet) execute("select", "select user_id from users where user_id = " + user_id + ";" );
			if (!has_user.first()) {
				return false;
			}
			//Проверка достаточного количества денег
			ResultSet enought_money = (ResultSet) execute("select", "select balance from users where user_id = "+ user_id +";");
			double balance = 0d;
			while(enought_money.next()) {
				balance = enought_money.getDouble(1);
			}
			enought_money.close();
			if(money > balance) {
				return false;
			}
			//Снимаем деньги
			execute("update", "update users set balance = balance - " + money
					+ " where user_id = " + user_id + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	//Пополнение счета
	public boolean refill (int user_id, double money) {
		if(money < 0) {
			return false;
		}
		try {
			ResultSet has_user = (ResultSet) execute("select", "select user_id from users where user_id = " + user_id + ";" );
			if (!has_user.first()) {
				return false;
			} 
			has_user.close();
			execute("update", "update users set balance = balance + " + money
					+ " where user_id = "+ user_id + ";");
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
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
			round();
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
	}
	
	public UserInfo getUserInfo(int user_id) {
		try {
			ResultSet result = (ResultSet) execute("select", "select * from users where user_id = " + user_id + ";");
			UserInfo client = new UserInfo();
			while(result.next()) {
				client.user_id = result.getInt(1);
				client.balance = result.getDouble(2);
				client.pin = result.getInt(3);
				client.birthdate = result.getDate(4);
				client.user_login = result.getString(5);
				client.secret_question = result.getString(6);
				client.secret_answer = result.getString(7);
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
