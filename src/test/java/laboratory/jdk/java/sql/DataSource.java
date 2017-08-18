package laboratory.jdk.java.sql;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataSource {
	private static final Logger logger = LoggerFactory.getLogger(DataSource.class);
	
	private static Connection connection = null;

	private DataSource() {}

	public static Connection getConnection() {
		final String url = "jdbc:oracle:thin:@localhost:1521:sid";
		final String user = "TBSDEV";
		final String pswd = "TBSDEV";
		if (connection == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				connection = DriverManager.getConnection(url, user, pswd);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return connection;
	}

	public static void close() {
		if (connection != null) {
			try {
				if (!connection.isClosed())
					connection.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		connection = null;
	}
}
