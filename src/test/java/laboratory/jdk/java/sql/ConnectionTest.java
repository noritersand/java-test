package laboratory.jdk.java.sql;

import java.sql.Connection;
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
}
