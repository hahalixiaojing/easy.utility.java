package easy.utility;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class StringHelper {
	private StringHelper() {
	}

	/**
	 * 字符中转成base64编码byte
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] toBase64Bytes(String str) {
		byte[] bytes = Base64.getEncoder().encode(str.getBytes());
		return bytes;
	}

	/**
	 * 字符串转成base64byte,指定字符串编码
	 * 
	 * @param str
	 * @param charsetName
	 * @return
	 */
	public static byte[] toBase64Bytes(String str, String charsetName) {
		byte[] bytes = null;
		try {
			bytes = Base64.getEncoder().encode(str.getBytes(charsetName));
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}

	/**
	 * 字符串转成base64编码
	 * 
	 * @param str
	 * @return
	 */
	public static String toBase64(String str) {
		byte[] bytes = Base64.getEncoder().encode(str.getBytes());
		return new String(bytes);
	}

	/**
	 * 字符串转成base64编码字符串
	 * 
	 * @param str
	 * @param charsetName
	 * @return
	 */
	public static String toBase64(String str, String charsetName) {
		byte[] bytes;
		try {
			bytes = Base64.getEncoder().encode(str.getBytes(charsetName));
			return new String(bytes, charsetName);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * byte数组转成base64字符串
	 * 
	 * @param bytes
	 * @return
	 */
	public static String toBase64(byte[] bytes) {
		byte[] base64Bytes = Base64.getEncoder().encode(bytes);
		return new String(base64Bytes);
	}

	/**
	 * byte数组编码成base64字符串
	 * @param bytes
	 * @param charsetName
	 * @return
	 */
	public static String toBase64(byte[] bytes, String charsetName) {
		byte[] base64Bytes = Base64.getEncoder().encode(bytes);
		try {
			return new String(base64Bytes, charsetName);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * bytes数组编码成base64的byte数组
	 * @param bytes
	 * @return
	 */
	public static byte[] toBase64Bytes(byte[] bytes) {
		byte[] base64Bytes = Base64.getEncoder().encode(bytes);
		return base64Bytes;
	}

	/**
	 * base64字符串解码
	 * 
	 * @param str
	 * @return
	 */
	public static String fromBase64(String str) {
		byte[] decodingByte = Base64.getDecoder().decode(str.getBytes());
		return new String(decodingByte);
	}

	/**
	 * base64字符串解码成byte
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] bytesFromBase64(String str) {
		byte[] decodingByte = Base64.getDecoder().decode(str.getBytes());
		return decodingByte;
	}

	/**
	 * base64字符串解码成字符串
	 * 
	 * @param str
	 * @param charsetName
	 * @return
	 */
	public static String fromBase64(String str, String charsetName) {
		try {
			byte[] decodingByte = Base64.getDecoder().decode(
					str.getBytes(charsetName));
			return new String(decodingByte, charsetName);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * base64 Byte数组解码
	 * 
	 * @param bytes
	 * @return
	 */
	public static String fromBase64(byte[] bytes) {
		byte[] decodingByte = Base64.getDecoder().decode(bytes);
		return new String(decodingByte);
	}

	/**
	 * 将base64编码的bytes 解码成bytes
	 * 
	 * @param bytes
	 * @return
	 */
	public static byte[] bytesFromBase64(byte[] bytes) {
		byte[] decodingByte = Base64.getDecoder().decode(bytes);
		return decodingByte;
	}

	/**
	 * 将base64编码的bytes解码成字符串
	 * 
	 * @param bytes
	 * @param charsetName
	 * @return
	 */
	public static String fromBase64(byte[] bytes, String charsetName) {
		byte[] decodingByte = Base64.getDecoder().decode(bytes);
		try {
			return new String(decodingByte, charsetName);
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 将十六进制字符串转成byte数组
	 * @param str
	 * @return
	 */
	public static byte[] hexStringToBytes(String str) {
		if (str == null || str.equals("")) {
			return null;
		}

		String hexStr = str.toUpperCase();
		int length = hexStr.length() / 2;
		char[] hexChars = hexStr.toCharArray();
		byte[] bytes = new byte[length];

		for (int i = 0; i < length; i++) {
			int pos = i * 2;

			String value = String.valueOf(hexChars[pos])
					+ String.valueOf(hexChars[pos + 1]);

			bytes[i] = (byte)Integer.parseInt(value, 16);
		}
		return bytes;
	}
	/**
	 * bytes数组转换成十六进字符串
	 * @param bytes
	 * @return
	 */
	public static String toHexString(byte[] bytes) {

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();

	}
}
