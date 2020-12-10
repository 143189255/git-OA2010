import com.ssm.mapper.RoleMapper;
import com.ssm.pojo.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RoleTest {
    @Autowired
    private RoleMapper roleMapper;
    @Test
    public void getRolesByEmpId(){
        int empId = 2;
       List<Role> rolelist= roleMapper.getRolesByEmpId(empId);
        for (Role role : rolelist) {
            System.out.println(role);

        }
    }
}
