package encoding;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static String getMD5(String pass) {
		try {
			MessageDigest md;
			md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(pass.getBytes());
			BigInteger bi = new BigInteger(1, messageDigest);
			String hash = bi.toString(16);
			return hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return pass;
	}
}
