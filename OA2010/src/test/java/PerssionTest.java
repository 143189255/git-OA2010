import com.ssm.mapper.PerssionMapper;
import com.ssm.pojo.Perssion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PerssionTest {
    @Autowired
    private PerssionMapper perssionMapper;
    @Test
    public void testMyMenus(){
        List<Perssion> perssionList =  perssionMapper.getMenus(2);
        for (Perssion perssion : perssionList) {
            System.out.println(perssion);

        }
    }
}
