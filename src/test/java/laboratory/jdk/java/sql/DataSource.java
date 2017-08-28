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
//		final String url = "jdbc:mysql://localhost:3306/database";
		final String user = "TEST";
		final String pswd = "TEST";
		if (connection == null) {
			try {
				/*
				 * 예전에는 아래처럼 해야했지만, 인스턴스가 생성된 드라이버들이 알아서 DriverManager에 스스로를 추가하는걸로 추정됨. (java.sql.Driver의 구현체라서 이렇게 되는게 아닐까?)
				 * 가령 com.mysql.jdbc.Driver의 경우 아래처럼 static 블럭이 작성되어 있음.:
				 *    static {
				 *        try {
				 *            java.sql.DriverManager.registerDriver(new Driver());
				 *        } catch (SQLException E) {
				 *            throw new RuntimeException("Can't register driver!");
				 *        }
				 *    }
				 * 그리고 DriverManager는 connection을 얻어올 때 위처럼 등록된 드라이버 중 데이터베이스에 적당한 드라이버를 골라서 가져오는걸로 추정된다.
				 */
//				Class.forName("oracle.jdbc.driver.OracleDriver");
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
