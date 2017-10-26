package client;

import java.util.regex.Pattern;

public class Checker {
  public static boolean verifyLogin(String login) {
  	return Pattern.matches("[a-zA-Z]+([/._]?[a-zA-Z0-9]+)*", login);
  }

  public static boolean verifyPinCode(String pin) {
  	return Pattern.matches("[0-9]{4}", pin);
  }

  public static boolean verifyDate(String date) {
  	return Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", date);
  }
}
