package testbed.work.database;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum CreateSQL {
    ;

    public static void main(String[] args) {
        whereClauseForPaging();
    }

    public static void whereClauseForPaging() {
        for (int i = 0; 28 > i; i++) {
            System.out.println("/* " + (i + 1) + " */"
                    + " AND B_TB.RNUM BETWEEN "
                    + ((i * 50000) + 1)
                    + " AND "
                    + ((i + 1) * 50000));
        }
    }
}
