package laboratory.work;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEverything {
	private static final Logger logger = LoggerFactory.getLogger(TestEverything.class);

	public static void main(String[] args) {

		try (FileOutputStream fos = new FileOutputStream("C:/dev/temp/test.properties")) {
			// 상대경로를 지정하면 루트는 '워크스페이스/프로젝트' 폴더다.
			Properties prop = new Properties();
			prop.put("서울의수도", "서울에수도가어디있어");
			prop.store(fos, "코멘트테스트");
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			System.exit(1);
		}

//		FileInputStream fis = new FileInputStream("C:/dev/temp/test.properties");
//		Properties prop = new Properties();
//		prop.load(fis);
//		fis.close();
//		 
//		prop.list(System.out); // 서울의수도=서울에수도가어디있어
//		logger.debug(prop.getProperty("서울의수도")); // 서울에수도가어디있어

//		Properties prop = new Properties();
//		FileInputStream fis = new FileInputStream("C:/dev/temp/test.properties");
//		prop.load(fis);
//		fis.close();
//		System.out.println(prop);
	}
}
