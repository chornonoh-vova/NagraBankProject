package client;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Interface to calculate and return MD5 hash
 *
 * @see java.security.MessageDigest
 */
public class Md5Hasher {
  /**
   * Method to get MD5 of a parameter input
   *
   * @param input string, that will be converted to hash
   * @return MD5 hash
   */
  public static String getMd5Hash(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      md.update(StandardCharsets.UTF_8.encode(input));
      return String.format("%032x", new BigInteger(1, md.digest()));
    } catch (NoSuchAlgorithmException expected) {
      return null;
    }
  }
}
