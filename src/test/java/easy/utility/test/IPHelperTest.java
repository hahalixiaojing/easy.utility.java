package easy.utility.test;

import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import easy.utility.IPHelper;

public class IPHelperTest {

	@Test
	public void getIp4List() throws UnknownHostException, SocketException {

		String[] iplist = IPHelper.getIp4List();


		String ip = IPHelper.getIntranetIp4();

		System.out.println("dsfds" + ip);
	}

	@Test
	public void getAvailablePortTest() throws IOException {

		String ip = IPHelper.getIntranetIp4();

		int port = IPHelper.getAvailablePort(ip, 8000, 9000);

		Assert.assertTrue(port > 0);
	}
}
