package server;

import java.sql.Date;

public class UserInfo {
	int userId;
	double balance;
	int pin;
	String userLogin;
	Date birthDate;
	String secretQuestion;
	String secretAnswer;
	boolean status;
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
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", balance=" + balance + ", pin=" + pin + ", userLogin=" + userLogin
				+ ", birthDate=" + birthDate + ", secretQuestion=" + secretQuestion + ", secretAnswer=" + secretAnswer
				+ ", status=" + status + "]";
	}
	
}
