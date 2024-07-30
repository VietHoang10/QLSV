package org.example;

import function.Function;
import function.Import;
import thread.ExportRunable;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

//        ExportRunable exportRunable = new ExportRunable();
//        Thread exportThread = new Thread(exportRunable);
//        exportThread.start();

//        while (true) {
//            System.out.println("=========================");
//            System.out.println("QLSV");
//            System.out.println("1.ADD STUDENT");
//            System.out.println("2.LIST STUDENT");
//            System.out.println("3.UPDATE STUDENT");
//            System.out.println("4.DELETE STUDENT");
//            System.out.println("5.EXIT");
//            System.out.println("==========================");
//            Scanner scanner = new Scanner(System.in);
//            int n = scanner.nextInt();
//            switch (n) {
//                case 1:
//                    Function.enterStudentInfo();
//                    break;
//                case 2:
//                    Function.displayStudent();
//                    break;
//                case 3:
//                    Function.updateS();
//                    break;
//                case 4:
//                    Function.deleteStudent();
//                    break;
//                case 5:
//                    System.out.println("Ok bye bye");
//                    break;
//                default:
//                    System.out.println("Please try again");
//                    break;
//            }
//            if (n==5) {
//                exportRunable.stop();
//                break;
//            }
//        }

        Import.importData();
    }
}