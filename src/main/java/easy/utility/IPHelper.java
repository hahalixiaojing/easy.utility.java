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

        List<String> ipList = new ArrayList<>();

        Enumeration<NetworkInterface> allInterfaces = NetworkInterface
                .getNetworkInterfaces();
        NetworkInterface netInterface ;
        while (allInterfaces.hasMoreElements()) {
            netInterface = allInterfaces.nextElement();

            Enumeration<InetAddress> addresses = netInterface
                    .getInetAddresses();

            InetAddress ip = null;
            while (addresses.hasMoreElements()) {
                ip = addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address
                        && !ip.isLoopbackAddress()) {
                    ipList.add(ip.getHostAddress());
                }
            }
        }
        return ipList.toArray(new String[ipList.size()]);
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

            String[] split = ip.split("\\.");


            if (Integer.parseInt(split[0]) == 192 && Integer.parseInt(split[1]) == 168) {
                return ip;
            }
            if (Integer.parseInt(split[0]) == 10) {
                return ip;
            }

            if (Integer.parseInt(split[0]) == 172 && Integer.parseInt(split[1]) >= 16
                    && Integer.parseInt(split[1]) <= 31) {
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
            String[] split = ip.split("\\.");


            if (Integer.parseInt(split[0]) == 192 && Integer.parseInt(split[1]) == 168) {
                continue;
            }
            if (Integer.parseInt(split[0]) == 10) {
                continue;
            }

            if (Integer.parseInt(split[0]) == 172 && Integer.parseInt(split[1]) >= 16
                    && Integer.parseInt(split[1]) <= 31) {
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
