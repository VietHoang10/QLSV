package function;

import obj.Connect;

import javax.xml.namespace.QName;
import java.sql.*;
import java.util.Scanner;

public class Function {


    public static void addStudent(String name, Integer age, String email) {
        String sql = "INSERT INTO hocsinh (name, age, email) VALUES (?, ?, ?)";
        try (Connection conn = Connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, email);
            pstmt.executeUpdate();
            System.out.println("Student added successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void enterStudentInfo() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student age: ");
        Integer age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        addStudent(name, age, email);
    }


    public static void displayStudent() {
        String sql = "SELECT * FROM hocsinh";
        try (Connection conn = Connect.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                System.out.printf("ID: %d, Name: %s, Age: %d, Email: %s%n", id, name, age, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  static void updateS() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id student");
        Integer id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        scanner.nextLine();

        System.out.print("Enter student age: ");
        Integer age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter student email: ");
        String email = scanner.nextLine();

        String sql = "UPDATE hocsinh SET name = ?, age = ?, email = ? WHERE id = ?";
        try (Connection conn = Connect.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setInt(2, age);
            pstmt.setString(3, email);
            pstmt.setInt(4,id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student with ID " + id + " does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public static void deleteStudent() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter id student");
        Integer id= scanner.nextInt();
        String sql = "DELETE FROM hocsinh WHERE id = ?";

        try  {
            Connection conn = Connect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void fixStudent(){
        String sql = "UPDATE hocsinh SET age=18 where id>10";
        try  {
            Connection conn = Connect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}


