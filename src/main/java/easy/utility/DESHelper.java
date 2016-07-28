package easy.utility;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class DESHelper {

	/**
	 * 加密
	 * 
	 * @param text
	 *            要加密码的字符串
	 * @param password
	 *            密码，密码的长度需要为是8的倍数
	 * @return
	 */
	public static String encrypt(String text, String password) {
		SecureRandom random = new SecureRandom();
		try {
			DESKeySpec desKey = new DESKeySpec(password.getBytes("utf-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKey);

			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, random);

			byte[] bytes = cipher.doFinal(text.getBytes("utf-8"));

			return StringHelper.toHexString(bytes);
		}
		catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			return null;
		}
	}

	/**
	 * 解密
	 * 
	 * @param text
	 *            要解密码的字符串
	 * @param password
	 *            密码，必须是8的倍数
	 * @return
	 */
	public static String decrypt(String text, String password) {

		byte[] hexBytes = StringHelper.hexStringToBytes(text);

		SecureRandom random = new SecureRandom();
		try {
			DESKeySpec desKey = new DESKeySpec(password.getBytes("utf-8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKey);

			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, random);

			byte[] bytes = cipher.doFinal(hexBytes);

			return new String(bytes,"utf-8");
		}
		catch (InvalidKeyException | UnsupportedEncodingException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException e) {
			return null;
		}
	}
}
