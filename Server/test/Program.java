package test;

import server.Database;

//class for testing methods
public class Program {

	public static void main(String[] args) {
		Database database = new Database("jdbc:mysql://localhost:3306/bank_users", "root", "root");
		//TODO: test your method here
		
		database.closeConnection();
	}

}
