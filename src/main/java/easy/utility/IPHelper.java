package easy.utility;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.ServerSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Random;

public class IPHelper {
	private IPHelper() {
	}

	public static String[] getIp4List() throws UnknownHostException,
			SocketException {

		List<String> ipList = new ArrayList<String>();

		Enumeration<NetworkInterface> allInterfaces = NetworkInterface
				.getNetworkInterfaces();
		NetworkInterface netInterface = null;
		while (allInterfaces.hasMoreElements()) {
			netInterface = (NetworkInterface) allInterfaces.nextElement();

			Enumeration<InetAddress> addresses = netInterface
					.getInetAddresses();

			InetAddress ip = null;
			while (addresses.hasMoreElements()) {
				ip = (InetAddress) addresses.nextElement();
				if (ip != null && ip instanceof Inet4Address
						&& !ip.isLoopbackAddress()) {
					ipList.add(ip.getHostAddress());
				}
			}
		}
		return ipList.stream().toArray(String[]::new);
	}

	/**
	 * 获得内网IP
	 * 
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static String getIntranetIp4() throws SocketException,
			UnknownHostException {
		String[] ipList = getIp4List();
		for (String ip : ipList) {
			Integer[] integers = Arrays.stream(ip.split("\\."))
					.map(s -> new Integer(s)).toArray(Integer[]::new);

			if (integers[0].intValue() == 192 && integers[1].intValue() == 168) {
				return ip;
			}
			if (integers[0].intValue() == 10) {
				return ip;
			}

			if (integers[0].intValue() == 172 && integers[1].intValue() >= 16
					&& integers[1].intValue() <= 31) {
				return ip;
			}
		}
		return "";
	}

	/**
	 * 获得公网IP，如果没有则返回空
	 * 
	 * @return
	 * @throws UnknownHostException
	 * @throws SocketException
	 */
	public static String getInternetIp4() throws UnknownHostException,
			SocketException {

		String[] ipList = getIp4List();
		for (String ip : ipList) {
			Integer[] integers = Arrays.stream(ip.split("\\."))
					.map(s -> new Integer(s)).toArray(Integer[]::new);

			if (integers[0].intValue() == 192 && integers[1].intValue() == 168) {
				continue;
			}
			if (integers[0].intValue() == 10) {
				continue;
			}

			if (integers[0].intValue() == 172 && integers[1].intValue() >= 16
					&& integers[1].intValue() <= 31) {
				continue;
			}
			return ip;
		}
		return "";
	}

	/**
	 * 获得可用端口
	 * 
	 * @param ip
	 * @param portStart
	 * @param portEnd
	 * @return
	 * @throws IOException
	 */
	public static int getAvailablePort(String ip, int portStart, int portEnd)
			throws IOException {
		InetAddress address = Inet4Address.getByName(ip);
		Random random = new Random();
		boolean isAvailabel = true;
		while (true) {

			int port = random.nextInt(portEnd) % (portEnd - portStart + 1)
					+ portStart;
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(port, 0, address);
				isAvailabel = true;
			} catch (Exception e) {
				isAvailabel = false;
			} finally {
				if (ss != null)
					ss.close();
			}

			if (isAvailabel) {
				return port;
			}
		}
	}
}
