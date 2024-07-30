package function;


import obj.Connect;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Export {
    public static void exportData() {
        String excelFilePath = "data.xlsx";
        System.out.println("File Excel save of: " + new File(excelFilePath).getAbsolutePath());

        String startTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        System.out.println("Start export at: " + startTime);

        int recordCount = 0;

        try {
            Connection connection = Connect.getConnection();
            String sql = "SELECT * FROM hocsinh";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Hoc sinh");

            writeHeader(resultSet, sheet);

            recordCount = writeData(resultSet, sheet);

            try {
                FileOutputStream outputStream = new FileOutputStream(excelFilePath);
                workbook.write(outputStream);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            workbook.close();
            statement.close();
            resultSet.close();


        } catch (SQLException | IOException e) {
            e.printStackTrace();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        System.out.println("Number of record export:" + recordCount);
        String endTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
        System.out.println("Finish export record at: " + endTime);
        System.out.println("========================================================");
    }

    private static void writeHeader(ResultSet resultSet, Sheet sheet) throws Exception {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        Row headerRow = sheet.createRow(0);

        for (int i = 1; i <= numberOfColumns; i++) {
            Cell headerCell = headerRow.createCell(i - 1);
            headerCell.setCellValue(metaData.getColumnName(i));
        }

    }

    private static int writeData(ResultSet resultSet, Sheet sheet) throws Exception {
        int rowIndex = 1;
        int recordCount = 0;

        while (resultSet.next()) {
            Row row = sheet.createRow(rowIndex++);

            for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
                Cell cell = row.createCell(i - 1);
                cell.setCellValue(resultSet.getString(i));
            }

            recordCount++;
        }

        return recordCount;
    }


}

