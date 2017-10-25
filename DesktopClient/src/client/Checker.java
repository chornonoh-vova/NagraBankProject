package client;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Checker {
  public static boolean verifyLogin(String login) {
  	Pattern verifyUserLogin = Pattern.compile("[a-zA-Z]+([/._]?[a-z0-9]+)*", Pattern.CASE_INSENSITIVE);
  	Matcher matcher = verifyUserLogin.matcher(login);
  	boolean verified = matcher.matches();
  	return verified;
  }

  public static boolean verifyPinCode(String pin) {
  	return Pattern.matches("[0-9]{4}", pin);
  }

  public static boolean verifyDate(String date) {
  	return Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", date);
  }
}
