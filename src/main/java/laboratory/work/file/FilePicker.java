package laboratory.work.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilePicker {
	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(FilePicker.class);

	private static final String targetLocation = "c:/dev/temp";
    private static final String destLocation = "c:/dev/temp2";
    private static final String[] fileNames = {
		"1503894632690.jpg", "1502946326918.jpg", "1502856268639.jpg", "1502676588001.jpg", "1501639027007.jpg", "1501131432736.jpg", "1501055978928.jpg", "1500603093493.jpg", "1496626879800.jpg", "1496380451203.jpg", "1496292350040.jpg", "1496031209472.jpg", "1494216905255.jpg", "1492577221017.jpg", "1491183994248.jpg", "1490762368774.jpg", "1490149880299.jpg", "1489387145375.jpg", "1489122620089.jpg", "1487125981540.jpg", "1486432968359.jpg", "1484713039452.jpg", "1483683040183.jpg", "1481527180874.jpg", "1481180138807.jpg", "1478859084785.jpg", "1478066013099.png", "1475561339579.jpg", "1474940908118.jpg", "1474591189852.jpg", "1474590931768.jpg", "1472463896436.jpg", "1472177208511.jpg", "1472001564691.png", "1471391964306.jpg", "1469524276659.jpg", "1469148791760.jpg", "1469148187083.jpg", "1467697068084.jpg", "1466415727110.jpg", "1465809889143.jpg", "1463556648149.png", "1462154967857.png", "1461915216773.png", "1457057646506.jpg", "1455871501328.jpg", "1455843568514.jpg", "1453854271300.jpg", "1453713338050.jpg", "1453121591491.jpg", "1452851558796.jpg", "1451898498445.jpg", "1450948747084.jpg", "1450948407116.jpg", "1449575162272.jpg", "1449575547320.jpg", "1446508265858.jpg", "1442974883846.jpg", "1440390219705.jpg", "1439785679475.jpg", "1437975929055.jpg", "1433726864604.jpg", "1432859896476.jpg", "1432800833662.jpg", "1432193775001.jpg", "1432193314985.jpg", "1428883553768.jpg", "1428883484788.jpg", "1426833699668.jpg", "1426568242789.jpg", "1426139713167.jpg", "1425602321311.jpg", "1424159780737.jpg", "1423118045790.jpg", "1423118004073.jpg", "1422874024850.jpg", "1422873982483.jpg", "1422874246767.jpg", "1421385898517.jpg", "1421287124131.jpg", "1417063721612.jpg", "1414636132318.jpg", "1414044534448.jpg", "1413352534031.jpg", "1409017403058.jpg", "1405915571193.jpg", "1400121124433.jpg", "1398322811600.jpg", "1390192400982.jpg", "1389863943209.jpg", "1387531703401.jpg", "635.jpg", "626.jpg", "583.jpg", "582.jpg", "576.jpg", "561.jpg", "2000)11-2-0806.jpg", "2000) 11_2_0802.jpg", "2000)11-2-0713.jpg", "2000)10-2-1123.jpg", "s2000-10-10.jpg", "s2000 10 2st 20111004.jpg", "speetto2000_10_20110929_2_1.jpg", "speetto2000_9_20110822_2_1.jpg", "speetto2000_9_20110615_2_1.jpg", "speetto2000_9_20110711_2_1.jpg", "speetto2000_9_20110516_2_1.jpg", "speetto2000_9_20110322_2_1.jpg", "speetto2000_9_20110318_1_10.jpg", "speetto2000_8_20110216_2_1.jpg", "speetto2000_8_20110210_2_1.jpg", "speetto2000_8_20101014_2_1.jpg", "speetto2000_7_20100809_1_10.jpg", "speetto2000_7_20100726_2_1.jpg", "speetto2000_7_20100719_2_1.jpg", "speetto2000_7_20100617_1_10.jpg", "speetto2000_7_20100517_2_1.jpg", "speetto2000_7_20100330_2_1.jpg", "speetto2000_7_20100222_1_10.jpg", "speetto2000_7_20100208_2_1.jpg", "speetto2000_6_20100122_2_1.jpg", "speetto2000_6_20091201_2_1.jpg", "speetto2000_6_20091116_2_1.jpg", "speetto2000_6_20091014_2_1.jpg", "speetto2000_6_20090925_2_1.jpg", "speetto2000_6_20090903_2_1_yangju.jpg", "speetto2000_6_20090903_2_1_jangwi.JPG", "speetto2000_6_20090717_2_1.jpg", "speetto2000_6_20090622_2_1.jpg", "speetto2000_5_20090617_2_1.jpg", "speetto2000_5_20090519_2_1.jpg", "speetto2000_5_20090429_2_1.jpg", "speetto2000_5_20090417_2_1.jpg", "speetto2000_5_20090323_2_1.jpg", "speetto2000_5_20090205_2_1.jpg", "speetto2000_5_20090119_1_10.jpg", "speetto2000_3_20081226_2_1.jpg", "speetto2000_4_20081222_2_1.jpg", "speetto2000_4_20081216_2_1.jpg", "speetto2000_4_20081124_1_10.jpg", "speetto2000_4_20081121_2_1.jpg", "speetto2000_4_20081112_2_1_jeju.jpg", "speetto2000_4_20081112_2_1_onyang.jpg", "speetto2000_4_20081023_2_1.jpg", "speetto2000_4_20081013_2_1.jpg", "speetto2000_4_20080905_2_1.jpg", "speetto2000_4_20080902_2_1.jpg", "speetto2000_4_20080811_2_1.jpg", "speetto2000_4_20080723_2_1.jpg"
    };

    public static void main(String[] args) throws Exception {
        Path targetPath = new File(targetLocation).toPath();
        Stream<Path> targetList = Files.list(targetPath);

        // list.forEach(System.out::println);
        
        List<String> fileNameList = new ArrayList<String>(Arrays.asList(fileNames));

        targetList.forEach((ele) -> {
            final String fileName = ele.getFileName().toString();
            if (fileNameList.contains(fileName)) {
            	Path newPath = new File(destLocation, fileName).toPath();
            	try {
            		Files.copy(ele, newPath, StandardCopyOption.REPLACE_EXISTING);
            	} catch (IOException e) {
            		System.err.println(e);
            		System.exit(0);
            	}
            }
        });

        targetList.close();
    }
}
