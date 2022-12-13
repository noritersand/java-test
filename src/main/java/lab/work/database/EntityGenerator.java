package lab.work.database;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.*;
import java.util.*;

@Slf4j
public enum EntityGenerator {
    ;
    private static final String TABLE_NAME = "TB_WOFF";

    public static void main(String[] args) throws IOException, SQLException {
        Path datasourceFile = new File("src/main/resources/config/DO_NOT_COMMIT_ME.properties").toPath();

        Properties properties = new Properties();
        properties.load(new FileInputStream(datasourceFile.toString()));

        String url = properties.getProperty("datasource.url");
        String username = properties.getProperty("datasource.username");
        String password = properties.getProperty("datasource.password");
//        datasource.driver-class-name=net.sf.log4jdbc.sql.jdbcapi.DriverSpy

        log.debug("url: {}", url);
        log.debug("username: {}", username);
        log.debug("password: {}", password);

        Connection connection = DataSource.getConnection(url, username, password);
        log.debug("connection: {}", connection);

        List<Map<String, Object>> tableInfo = null;
        {
            String sql = "SELECT TABLE_COMMENT, TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, TABLE_NAME);
            preparedStatement.setFetchSize(2000);
            ResultSet resultSet = preparedStatement.executeQuery();
            tableInfo = getResultMapRows(resultSet);
        }
        log.info("tableInfo: {}", tableInfo);

        List<Map<String, Object>> columnsInfo = null;
        {
            String sql = "SELECT COLUMN_COMMENT, COLUMN_NAME, COLUMN_TYPE FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, TABLE_NAME);
            preparedStatement.setFetchSize(2000);
            ResultSet resultSet = preparedStatement.executeQuery();
            columnsInfo = getResultMapRows(resultSet);
        }
        log.info("resultMapRows: {}", columnsInfo);

        columnsInfo.forEach(e -> {
            log.debug("original columnType: {}", e.get("COLUMN_TYPE"));
            String columnType = columnTypeToJavaType((String) e.get("COLUMN_TYPE"));
            log.debug("converted columnType: {}", columnType);
        });
    }

    private static String columnTypeToJavaType(String columnType) {
        int firstBracket = columnType.indexOf('(');
        if (-1 < firstBracket) {
            columnType = columnType.substring(0, firstBracket);
        }

        switch (columnType) {
            case "int":
            case "tinyint":
            case "smallint":
            case "mediumint":
                return "Integer";
            case "varchar":
            case "text":
            case "blob":
            case "binary":
            case "char":
            case "enum":
            case "set":
            case "tinyblob":
            case "tinytext":
            case "mediumblob":
            case "mediumtext":
            case "longblob":
            case "longtext":
                return "String";
            case "date":
                return "Date";
            case "time":
            case "timestamp":
                return "Timestamp";
            case "datetime":
                return "DateTime";
            case "bigint":
                return "Long";
            case "float":
                return "Float";
            case "double":
                return "Double";
            case "decimal":
                return "BigDecimal";
            case "bool":
            case "boolean":
                return "Boolean";
            default:
                return "FIXME";
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

        public static Connection getConnection(String url, String username, String password) {
            if (null == DataSource.connection) {
                try {
                    connection = DriverManager.getConnection(url, username, password);
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
                    System.out.println(e);
                }
            }
            connection = null;
        }
    }

}
