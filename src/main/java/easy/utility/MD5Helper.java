package easy.utility;

import java.security.MessageDigest;
/**
 * md5加密码
 * @author 晓静
 *
 */
public class MD5Helper {
	private MD5Helper() {

	}
	/**
	 * 加密md5
	 * @param text
	 * @return
	 */
	public static String encrypt(String text) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(text.getBytes("utf-8"));
		}
		catch (Exception e) {
			return null;
		}
		byte[] bytes = md5.digest();
		
		return StringHelper.toHexString(bytes);
	}
}
