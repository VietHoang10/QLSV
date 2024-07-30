package function;

import obj.Connect;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Import {
    public static void importData() {
        String excelFilePath = "Book2.xlsx";
        FileInputStream inputStream;
        Workbook workbook = null;

        try {
            inputStream = new FileInputStream(new File(excelFilePath));
            workbook = new XSSFWorkbook(inputStream);

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;


                Integer id = (int) row.getCell(0).getNumericCellValue();
                String name = row.getCell(1).getStringCellValue();
                Integer age = (int) row.getCell(2).getNumericCellValue();
                String email = row.getCell(3).getStringCellValue();


                saveData(id, name, age, email);
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void saveData(Integer id, String name, Integer age, String email) throws SQLException {
        String sql = "INSERT INTO hocsinh (id,name,age,email) VALUES (?,?,?,?)";
        try {
            Connection conn = Connect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
            System.out.println("A new record inserted successfully!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}





