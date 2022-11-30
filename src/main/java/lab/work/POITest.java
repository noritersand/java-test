package lab.work;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class POITest {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        String inputPath = "c:/dev/temp/excel-upload-test/read-me.xlsx";
        String outputPath = "c:/dev/temp/excel-upload-test/result.txt";

        File file = new File(inputPath);

        StringBuffer buffer = execute(file);

        BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath));
        writer.write(buffer.toString());
        writer.close();
    }

    private static StringBuffer execute(File input) throws IOException, InvalidFormatException {
        Workbook wb = new XSSFWorkbook(input);
        Sheet sheet = wb.getSheetAt(0);

        StringBuffer buffer = new StringBuffer();
        buffer.append(read(sheet, 0, 0));
        buffer.append(read(sheet, 0, 1));
        buffer.append(read(sheet, 1, 0));
        buffer.append(read(sheet, 1, 1));
        buffer.append(read(sheet, 2, 0));
        buffer.append(read(sheet, 2, 1));
        buffer.append(read(sheet, 3, 0));
        buffer.append(read(sheet, 3, 1));
        buffer.append(read(sheet, 4, 0));
        buffer.append(read(sheet, 4, 1));
        buffer.append(read(sheet, 5, 0));
        buffer.append(read(sheet, 5, 1));

//        wb.close(); // 이거 풀면 엑셀 파일 열린 상태에서 실행 불가

        return buffer;
    }

    private static StringBuffer read(Sheet sheet, int rowNum, int colNum) {
        StringBuffer buff = new StringBuffer();
        buff.append("\n\n===================================\n");

        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);

        CellAddress address = cell.getAddress();
        buff.append("address: " + address + "\n");

        CellType cellTypeEnum = cell.getCellTypeEnum();
        buff.append("cellTypeEnum: " + cellTypeEnum + "\n");

        Comment cellComment = cell.getCellComment();
        buff.append("cellComment: " + cellComment + "\n");


        try {
            CellRangeAddress arrayFormulaRange = cell.getArrayFormulaRange();
            buff.append("arrayFormulaRange: " + arrayFormulaRange + "\n");
        } catch (Exception e) {
            // 무시
        }

        try {
            String cellFormula = cell.getCellFormula();
            buff.append("cellFormula: " + cellFormula + "\n");
        } catch (Exception e) {
            // 무시
        }

        try {
            double numericCellValue = cell.getNumericCellValue();
            buff.append("numericCellValue: " + numericCellValue + "\n");
            Date javaDate = DateUtil.getJavaDate(numericCellValue);
            buff.append("javaDate: " + javaDate + "\n");
        } catch (Exception e) {
            // 무시
        }

        try {
            String stringCellValue = cell.getStringCellValue();
            buff.append("stringCellValue: " + stringCellValue + "\n");
        } catch (Exception e) {
            // 무시
        }

        try {
            RichTextString richStringCellValue = cell.getRichStringCellValue();
            buff.append("richStringCellValue: " + richStringCellValue + "\n");
        } catch (Exception e) {
            // 무시
        }

        try {
            Date dateCellValue = cell.getDateCellValue();
            buff.append("dateCellValue: " + dateCellValue + "\n");
        } catch (Exception e) {
            // 무시
        }

        try {
            byte errorCellValue = cell.getErrorCellValue();
            buff.append("errorCellValue: " + errorCellValue + "\n");
        } catch (Exception e) {
            // 무시
        }

        try {
            boolean booleanCellValue = cell.getBooleanCellValue();
            buff.append("booleanCellValue: " + booleanCellValue + "\n");
        } catch (Exception e) {
            // 무시
        }

        CellStyle cellStyle = cell.getCellStyle();
        String dataFormatString = cellStyle.getDataFormatString();
        buff.append("dataFormatString: " + dataFormatString + "\n");

        return buff;
    }
}
