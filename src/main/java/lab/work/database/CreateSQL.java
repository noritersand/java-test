package lab.work.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateSQL {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(CreateSQL.class);

	public static void main(String[] args) {
		whereClauseForPaging();
	}
	
	public static void whereClauseForPaging() {
		for (int i = 0; i < 28; i++) {
			System.out.println("/* " + (i + 1) + " */"
					+ " AND B_TB.RNUM BETWEEN " 
					+ ((i * 50000) + 1) 
					+ " AND " 
					+ ((i + 1) * 50000));
		}
	}
}
