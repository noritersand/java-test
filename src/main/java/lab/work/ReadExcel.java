package lab.work;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellAddress;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class ReadExcel {
    
    public static void main(String[] args) throws IOException, InvalidFormatException {
        File file = new File("c:/dev/temp/test/read-me.xlsx");
        Workbook wb = new XSSFWorkbook(file);
        Sheet sheet = wb.getSheetAt(0);
        int numOfRows = sheet.getPhysicalNumberOfRows();
        read(sheet, 0, 0);
        read(sheet, 0, 1);
        read(sheet, 1, 0);
        read(sheet, 1, 1);
        read(sheet, 2, 0);
        read(sheet, 2, 1);
        read(sheet, 3, 0);
        read(sheet, 3, 1);
        read(sheet, 4, 0);
        read(sheet, 4, 1);
    }

    private static void read(Sheet sheet, int rowNum, int colNum) {
        log.debug("");log.debug("");
        log.debug("===================================");

        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);

        CellAddress address = cell.getAddress();
        log.debug("address: {}", address);

        CellType cellTypeEnum = cell.getCellTypeEnum();
        log.debug("cellTypeEnum: {}", cellTypeEnum);

        Comment cellComment = cell.getCellComment();
        log.debug("cellComment: {}", cellComment);


        try {
            CellRangeAddress arrayFormulaRange = cell.getArrayFormulaRange();
            log.debug("arrayFormulaRange: {}", arrayFormulaRange);
        } catch (Exception e) {
//            log.debug("arrayFormulaRange: [{}][{}] 셀은 array formula가 아님", rowNum, colNum);
        }

        try {
            String cellFormula = cell.getCellFormula();
            log.debug("cellFormula: {}", cellFormula);
        } catch (Exception e) {
//            log.debug("arrayFormulaRange: [{}][{}] 셀은 formula가 아님", rowNum, colNum);
        }

        try {
            double numericCellValue = cell.getNumericCellValue();
            log.debug("numericCellValue: {}", numericCellValue);
            Date javaDate = DateUtil.getJavaDate(numericCellValue);
            log.debug("javaDate: {}", javaDate);
        } catch (Exception e) {
//            log.debug("arrayFormulaRange: [{}][{}] 셀은 numeric이 아님", rowNum, colNum);
        }

        try {
            String stringCellValue = cell.getStringCellValue();
            log.debug("stringCellValue: {}", stringCellValue);
        } catch (Exception e) {
//            log.debug("arrayFormulaRange: [{}][{}] 셀은 string이 아님", rowNum, colNum);
        }

        try {
            RichTextString richStringCellValue = cell.getRichStringCellValue();
            log.debug("richStringCellValue: {}", richStringCellValue);
        } catch (Exception e) {
//            log.debug("arrayFormulaRange: [{}][{}] 셀은 string이 아님", rowNum, colNum);
        }

        try {
            Date dateCellValue = cell.getDateCellValue();
            log.debug("dateCellValue: {}", dateCellValue);
        } catch (Exception e) {
//            log.debug("arrayFormulaRange: [{}][{}] 셀은 date(로 변환 가능한 numeric)가 아님", rowNum, colNum);
        }

        try {
            byte errorCellValue = cell.getErrorCellValue();
            log.debug("errorCellValue: {}", errorCellValue);
        } catch (Exception e) {
//            log.debug("arrayFormulaRange: [{}][{}] 셀은 error가 아님", rowNum, colNum);
        }

        try {
            boolean booleanCellValue = cell.getBooleanCellValue();
            log.debug("booleanCellValue: {}", booleanCellValue);
        } catch (Exception e) {
//            log.debug("arrayFormulaRange: [{}][{}] 셀은 boolean이 아님", rowNum, colNum);
        }

        CellStyle cellStyle = cell.getCellStyle();
        String dataFormatString = cellStyle.getDataFormatString();
        log.debug("dataFormatString: {}", dataFormatString);


    }
}
