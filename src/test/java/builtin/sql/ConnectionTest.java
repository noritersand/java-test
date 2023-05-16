package builtin.sql;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 커넥션 생성 예시<br>
 * {@link DataSource}를 일반 클래스에서 중첩 클래스로 변경했으니 뭔가 안되면 이 부분을 확인해.
 *
 * @author noritersand
 * @since 2020-03-18
 */
@Slf4j
public enum ConnectionTest {
    ;

    public static void main(String[] args) {
        final String sql = "SELECT ? AS DOODOO FROM DUAL";

        final String url = "jdbc:oracle:thin:@localhost:1521:sid";
//        final String url = "jdbc:mysql://localhost:3306/database";
        final String user = "TEST";
        final String pswd = "TEST";

        try (Connection connection = DataSource.getConnection(url, user, pswd)) {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, "getsome"); // 제로 인덱스가 아님
            ResultSet resultSet = statement.executeQuery();
            List<Map<String, Object>> resultList = getResultMapRows(resultSet);
            log.debug(resultList.toString());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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

    @Slf4j
    private enum DataSource {
        ;

        private static Connection connection;

        public static Connection getConnection(String url, String user, String pswd) {
            if (null == DataSource.connection) {
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
                    log.error(e.getMessage(), e);
                }
            }
            return connection;
        }

        public static void close() {
            if (null != DataSource.connection) {
                try {
                    if (!connection.isClosed())
                        connection.close();
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            connection = null;
        }
    }
}
