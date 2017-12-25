package client;

import java.util.regex.Pattern;

/**
 * Static class to verify login, pin-code and birthdate
 * 
 * @see java.util.regex.Pattern
 */
public class Checker {
	/**
	 * Method to check login
	 * 
	 * @param login
	 *          to verify
	 * @return true if login matches regex
	 */
	public static boolean verifyLogin(String login) {
		return Pattern.matches("[a-zA-Z]+([/._]?[a-zA-Z0-9]+)*", login);
	}

	/**
	 * Method to check pin-code
	 * 
	 * @param pin
	 *          to verify
	 * @return true if pin matches regex
	 */
	public static boolean verifyPinCode(String pin) {
		return Pattern.matches("[0-9]{4}", pin);
	}

	/**
	 * Method to check birthdate
	 * 
	 * @param date
	 *          to verify
	 * @return true if date matches regex
	 */
	public static boolean verifyDate(String date) {
		return Pattern.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}", date);
	}
}
