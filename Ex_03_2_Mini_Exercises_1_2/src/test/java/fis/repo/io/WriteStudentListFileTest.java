package fis.repo.io;

import fis.core.Student;
import junit.framework.TestCase;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class WriteStudentListFileTest extends TestCase {
    public final String studentPath = "../Ex_03_2_Mini_Exercises_1_2/src/students.xlsx";
    public void testWriteStudentListTofile() throws IOException {
        Map<String, Student> studentMap = new HashMap<>();
        Student s1 = new Student("Phạm Minh Đức", new BigDecimal(500));
        Student s2 = new Student("Nguyễn Thị Thu Thủy", new BigDecimal(1000));
        Student s3 = new Student("Nguyễn Văn Ngôn", new BigDecimal(750));
        Student s4 = new Student("Lê Thọ Dũng", new BigDecimal(600));
        Student s5 = new Student("Lê Thế Long", new BigDecimal(650));
        Student s6 = new Student("Trần Thị Ngà", new BigDecimal(550));
        studentMap.put(s1.getId(), s1);
        studentMap.put(s2.getId(), s2);
        studentMap.put(s3.getId(), s3);
        studentMap.put(s4.getId(), s4);
        studentMap.put(s5.getId(), s5);
        studentMap.put(s6.getId(), s6);
        WriteStudentListFile writeStudentListFile = new WriteStudentListFile();
        writeStudentListFile.writeStudentListTofile(studentMap, studentPath);
        System.out.println("Phạm Minh Đức");
    }
}