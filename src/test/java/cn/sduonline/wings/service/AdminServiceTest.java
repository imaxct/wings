package cn.sduonline.wings.service;

import cn.sduonline.wings.dao.mapper.StudentMapper;
import cn.sduonline.wings.model.Student;
import cn.sduonline.wings.service.impl.AdminServiceImpl;
import cn.sduonline.wings.vo.Result;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * Created by imaxct on 18-9-26.
 */
public class AdminServiceTest {
    @InjectMocks
    private AdminServiceImpl adminServiceImpl;

    @Mock
    private StudentMapper studentMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testImportStudent() {
        when(studentMapper.insertBatch(any(List.class))).thenAnswer((t) -> ((List) t.getArgument(0)).size());
        Result result = adminServiceImpl.importStudent(buildStudentList());
        System.out.println(result.getData());
    }

    private List<Student> buildStudentList() {
        List<Student> list = new ArrayList<>();
        for (int i = 0; i < 13; ++i) {
            Student student = new Student();
            student.setStudentNo(String.format("%d", i));
            list.add(student);
        }
        return list;
    }

}
