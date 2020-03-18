package jdk.java.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 커넥션 생성 예시<br>
 * {@link DataSource}를 일반 클래스에서 중첩 클래스로 변경했으니 뭔가 안되면 이 부분을 확인해.
 * 
 * @since 2020-03-18
 * @author noritersand
 */
public class ConnectionTest {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionTest.class);
	
	public static void main(String[] args) {
		final String sql = "SELECT ? AS DOODOO FROM DUAL";

		try (Connection connection = DataSource.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, "getsome"); // 제로 인덱스가 아님
			ResultSet resultSet = statement.executeQuery();
			List<Map<String, Object>> resultList = getResultMapRows(resultSet);
			logger.debug(resultList.toString());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	private static List<Map<String, Object>> getResultMapRows(ResultSet rs) throws SQLException {
		ResultSetMetaData metaData = rs.getMetaData();
		int sizeOfColumn = metaData.getColumnCount();
		List<Map<String, Object>> list = new ArrayList<>();
		Map<String, Object> map;
		String column;
		while (rs.next()) {
			map = new HashMap<String, Object>();
			for (int indexOfcolumn = 0; indexOfcolumn < sizeOfColumn; indexOfcolumn++) {
				column = metaData.getColumnName(indexOfcolumn + 1);
				map.put(column, rs.getString(column));
			}
			list.add(map);
		}
		return list;
	}
	
	private static class DataSource {
		private static final Logger logger = LoggerFactory.getLogger(DataSource.class);
		
		private static Connection connection = null;

		private DataSource() {}

		public static Connection getConnection() {
			final String url = "jdbc:oracle:thin:@localhost:1521:sid";
//			final String url = "jdbc:mysql://localhost:3306/database";
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
//					Class.forName("oracle.jdbc.driver.OracleDriver");
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
}
