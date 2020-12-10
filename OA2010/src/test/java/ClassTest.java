import com.ssm.mapper.TClassMapper;
import com.ssm.pojo.TClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class ClassTest {
    @Autowired
    private TClassMapper tClassMapper;
    @Test
    public void addClass(){
        TClass tClass = new TClass();
        tClass.setMajorId(1);
        tClass.setClassAddress("c105");
        tClass.setClassName("java");
        tClassMapper.addClass(tClass);
    }
    @Test
    public void getAllClass(){
     List<TClass> classList = tClassMapper.getClasses();
        for (TClass tClass : classList) {
            System.out.println(tClass);
        }
        System.out.println(classList);
    }
    @Test
    public void getAllClasses(){
        List<TClass> classes = tClassMapper.getAllClass();
        for (TClass aClass : classes) {
            System.out.println(aClass);

        }
    }
}
