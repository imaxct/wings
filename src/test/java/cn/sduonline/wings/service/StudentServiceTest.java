package cn.sduonline.wings.service;

import org.apache.shiro.util.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.service.impl.StudentServiceImpl;

/**
 * Created by imaxct on 18-10-2.
 */
public class StudentServiceTest {
    @InjectMocks
    StudentServiceImpl studentService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testParseStudent() {
        Student dbStudent = new Student();
        dbStudent.setId(1L);
        dbStudent.setStudentName("db");
        dbStudent.setIdNo("db");
        dbStudent.setSex("db");
        dbStudent.setGrade("db");
        dbStudent.setPoorLevel("db");

        Student student = new Student();
        student.setNation("s");
        student.setMajor("s");
        student.setClassName("s");
        student.setCampus("s");

        student = studentService.parseStudent(dbStudent, student);

        Assert.notNull(student.getCampus());
        Assert.notNull(student.getNation());
        Assert.notNull(student.getMajor());
        Assert.notNull(student.getClassName());
    }
}
