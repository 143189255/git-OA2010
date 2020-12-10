import com.ssm.mapper.LoginlogMapper;
import com.ssm.pojo.Loginlog;
import com.ssm.service.LoginlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class LoginLogTest {
  @Autowired
    LoginlogMapper loginlogMapper;
    @Test
            public void loginTest(){
        Loginlog loginlog = new Loginlog("111","dss","sss");
        int count = loginlogMapper.addLoginlog(loginlog);
        System.out.println(count);
    }




}
