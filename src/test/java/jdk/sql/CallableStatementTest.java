package jdk.sql;

import lombok.extern.slf4j.Slf4j;

import java.sql.*;

/**
 * 프로시저 호출용 클래스 CallableStatement 테스트 슈트
 *
 * @author fixalot
 * @since 2017-07-27
 */
@Slf4j
public class CallableStatementTest {

    /**
     * usage
     *
     * @author fixalot
     */
//	@Test
    void test() throws SQLException {
        Connection conn = DriverManager.getConnection("", "", "");
        CallableStatement cstmt = conn.prepareCall("{call TEST_PROCEDURE(?, ?) }");
        cstmt.setInt(0, 12345);
        cstmt.setString(1, "abcde");
        cstmt.registerOutParameter(1, Types.VARCHAR);
        cstmt.execute();
    }
}
