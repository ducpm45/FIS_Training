package fis.repo.io;

import fis.core.Report;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class WriteReportFile {
    private final String[] header = new String[] {"ID", "Name", "Debt"};

    /**
     * Ghi sao kê vào file excel
     * @param reportList
     * @param reportPath
     * @throws IOException
     */
    public void writeReportFile(List<Report> reportList, String reportPath) throws IOException {
        // create workbook
        Workbook workbook = createWorkBook(reportPath);
        // create sheet
        Sheet sheet = workbook.createSheet();
        // create header
        int rowIndex = 0;
        writeHeader(sheet, rowIndex, header.length);
        // write data
        rowIndex++;
        for(Report r : reportList) {
            // create row
            Row row = sheet.createRow(rowIndex);
            // write data on row
            writeData(r, row);
            rowIndex++;
        }
        // Auto rezise column
        for (int columnIndex = 0; columnIndex < header.length; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }

        createOutputFile(workbook, reportPath);
        System.out.println("Write success");
    }

    private void writeData(Report report, Row row) {
        Cell cell = row.createCell(0);
        cell.setCellValue(report.getId());

        cell = row.createCell(1);
        cell.setCellValue(report.getName());

        cell = row.createCell(2);
        cell.setCellValue(String.valueOf(report.getDebt()));

    }

    private void writeHeader(Sheet sheet, int rowIndex, int cellNumber) {
        // Create header row
        Row row = sheet.createRow(rowIndex);

        // Create cells

        for(int i = 0; i < cellNumber; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(header[i]);
        }
    }

    private Workbook createWorkBook(String reportPath) throws IOException {
        Workbook workbook;
        if(reportPath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if(reportPath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("File de luu khong dung dinh dang file excel!");
        }
        return workbook;
    }

    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
