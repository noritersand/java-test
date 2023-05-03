package jdk.java.sql;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class PreparedStatementTest {

    /**
     * usage
     *
     * @author fixalot
     */
//	@Test
    void test() {
        try (Connection con = DriverManager.getConnection("")) {
            PreparedStatement stmt = con.prepareStatement("SELECT 123");
            stmt.setFetchSize(2000);
            ResultSet rs = stmt.executeQuery();
            getResultMapRows(rs);
        } catch (SQLException e) {
            log.error(e.getMessage(), e);
        }
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
