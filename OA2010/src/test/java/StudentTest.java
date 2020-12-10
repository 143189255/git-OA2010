import com.ssm.mapper.StudentMapper;
import com.ssm.pojo.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class StudentTest {
    @Autowired
    private StudentMapper studentMapper;
    @Test
    public void testStudents(){
        long pageIndex = 1;
        long pageSize = 5;
        long classId = 1;
        String name = "Vue";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageStart",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);
        map.put("classId",classId);
        map.put("name",name);
        List<Student> studentList = studentMapper.getStudents(map);
        for (Student student : studentList) {
            System.out.println(student);
        }
        System.out.println(studentList);
    }
    @Test
    public void testTotalCount(){
        long pageIndex = 1;
        long pageSize = 5;
        long classId = 1;
        String name = "Vue";
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("pageStart",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);
        map.put("classId",classId);
        map.put("name",name);
        int count = studentMapper.getTotalCount(map);
        System.out.println(count);
    }
    @Test
    public void getAllStudents(){
        System.out.println(studentMapper.getAllNo());
    }
    @Test
    public void insertStudent(){
        Student student = new Student();
        student.setNo("556");
        student.setName("景甜");
        student.setClassId(1);
        student.setSchool("盐城工学院");
        studentMapper.insertStudent(student);
    }
}
