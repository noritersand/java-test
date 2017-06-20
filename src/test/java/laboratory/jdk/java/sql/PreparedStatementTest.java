package laboratory.jdk.java.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreparedStatementTest {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(PreparedStatementTest.class);
	
	@Test
	public void test() {
		// TODO
		
		/*
		
		try (Connection con = dataSource.getConnection()) {
			java.sql.PreparedStatement stmt = con.prepareStatement(
					"some query");
			stmt.setFetchSize(2000);
			ResultSet rs = stmt.executeQuery();
			getResultMapRows(rs);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
	}
	
	@SuppressWarnings("unused")
	private List<Map<String, Object>> getResultMapRows(ResultSet rs) throws SQLException {
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
