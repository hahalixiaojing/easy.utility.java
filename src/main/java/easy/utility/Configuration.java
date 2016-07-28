package easy.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
	private static Properties p = new Properties();
	public static final String CONFIG_FILE = "/app_config.properties";

	private Configuration() {

	}

	static {
		InputStream stream = Configuration.class
				.getResourceAsStream(CONFIG_FILE);

		if (stream != null) {
			try {

				p.load(stream);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
			}
		}
	}

	public static String get(Object key) {
		if (p.containsKey(key)) {
			return p.getProperty(key.toString());
		}
		return null;
	}
}
