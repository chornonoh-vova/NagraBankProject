package client;

import java.sql.Date;

public class UserInfo {
	public int userId;
	public double balance;
	public int pin;
	public String userLogin;
	public Date birthDate;
	public String secretQuestion;
	public String secretAnswer;
	public boolean status;

	public UserInfo() {
		this.userId = 0;
		this.balance = 0;
		this.pin = 0;
		this.userLogin = "";
		this.birthDate = Date.valueOf("1970-01-01");
		this.secretQuestion = "";
		this.secretAnswer = "";
		this.status = false;
	}
}
