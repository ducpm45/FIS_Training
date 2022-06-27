package fis.repo.io;

import fis.core.Student;
import junit.framework.TestCase;

import java.io.IOException;
import java.util.List;

public class ReadStudentListFileTest extends TestCase {
    public final String studentPath = "../Ex_03_2_Mini_Exercises_1_2/src/students.xlsx";
    public void testReadExcel() throws IOException {
        ReadStudentListFile readStudentListFile = new ReadStudentListFile();
        List<Student> studentList = readStudentListFile.readExcel(studentPath);
        studentList.forEach(System.out::println);
    }
}