/*
 */
package org.wetk.helper;

import java.math.BigInteger;
import java.security.MessageDigest;


/**
 *
 * @author Radek Ježdík <jezdik.radek@gmail.com>
 */
public class PasswordHashProvider {

	public static String hash(String password) {
		try {
			byte[] bytes = password.getBytes("UTF-8");

			MessageDigest m = MessageDigest.getInstance("MD5");
			byte[] digest = m.digest(bytes);

			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			while(hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}

			return hashtext;
		} catch(Exception ex) {
		}
		return null;
	}

}
