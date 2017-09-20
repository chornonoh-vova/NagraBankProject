package test;

import java.sql.Date;
import java.util.Scanner;

import server.Database;

//class for testing methods
public class Program {

	public static void main(String[] args) {
		Database database = new Database("jdbc:mysql://localhost:3306/bank_users", "root", "root");
		//TODO: test your method here(dlya chotkih kodderov!!!!)
		Scanner in = new Scanner(System.in);
		System.out.println("logIn:");
		String user_login = in.next();
		System.out.println("pin:");
		int pin = in.nextInt();
		//System.out.println("user_id1: ");
		//int user_id_from = in.nextInt();
		//System.out.println("user_id2: ");
		//int user_id_to = in.nextInt();
		//System.out.println("¬ведите сумму: ");
		//double money = in.nextDouble();
		//database.login(int pin, String user_login);
		//if(database.transfer(user_id_from, user_id_to, money)) {
		if (database.logIn(pin, user_login)) {
			System.out.println("ok");
		} else {
			System.out.println("error");
		}
		database.closeConnection();
	}
}
