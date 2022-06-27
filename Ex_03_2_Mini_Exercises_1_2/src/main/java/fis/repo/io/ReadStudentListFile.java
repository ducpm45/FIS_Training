package fis.repo.io;

import fis.core.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ReadStudentListFile {
    public List<Student> readExcel(String excelFilePath) throws IOException {
        List<Student> listStudent = new ArrayList<>();
        // Get file to read
        FileInputStream fis = new FileInputStream(new File(excelFilePath));
        // Get workbook
        Workbook workbook = getWorkbook(fis, excelFilePath);
        // Get sheet
        Sheet sheet = workbook.getSheetAt(0);
        // Get all rows
        for (Row nextRow : sheet) {
            if (nextRow.getRowNum() == 0) {
                // Ignore header
                continue;
            }
            // Get all cells
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            // Read cells and set value for book object
            Student student = new Student();
            while (cellIterator.hasNext()) {
                //Read cell
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                if (cellValue == null || cellValue.toString().isEmpty()) {
                    continue;
                }
                // Set value for book object
                int columnIndex = cell.getColumnIndex();
                switch (columnIndex) {
                    case 0 -> student.setId((String) getCellValue(cell));
                    case 1 -> student.setFullName((String) getCellValue(cell));
                    case 2 -> student.setPaymentCode((String) getCellValue(cell));
                    case 3 -> student.setAmount(new BigDecimal((String) getCellValue(cell)));
                    default -> {
                    }
                }
            }
            listStudent.add(student);
        }

        workbook.close();
        fis.close();

        return listStudent;
    }

    // Get Workbook
    private Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
        Workbook workbook = null;
        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    //  Get cell value
    private Object getCellValue(Cell cell) {
        CellType cellType = cell.getCellType();
        Object cellValue = null;
        if (cellType == CellType.STRING) {
            cellValue = cell.getStringCellValue();
        }
        return cellValue;
    }
}
