package test;

import java.sql.Date;

import server.Database;
import server.Scaner;

//class for testing methods
public class Program {

	public static void main(String[] args) {
		Database database = new Database("jdbc:mysql://localhost:3306/bank_users", "root", "5787");
		//TODO: test your method here
		Scaner in = new Scaner(System.in);
		System.out.println("¬ведите логин: ");
		String input = in.nextString();
		System.out.println("¬ведите пароль: ");
		int input1 = in.nextInt();
		database.login(int pin, String user_login);

}
}