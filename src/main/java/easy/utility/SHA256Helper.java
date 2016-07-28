package easy.utility;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256Helper {
	private SHA256Helper() {

	}

	public static String encrypt(String text) {
		String strDes = null;
		try {
			byte[] textBytes = text.getBytes("utf-8");

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(textBytes);
			strDes = StringHelper.toHexString(md.digest());
		}
		catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			return null;
		}
		return strDes;
	}
}
