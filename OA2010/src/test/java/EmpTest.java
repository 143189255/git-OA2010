import com.alibaba.fastjson.JSONObject;
import com.ssm.mapper.EmpMapper;
import com.ssm.pojo.Emp;
import com.ssm.utils.GetCharAndNumr;
import com.ssm.utils.TongJi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class EmpTest {
    @Autowired
    private EmpMapper empMapper;
    @Test
    public void login(){
        Emp emp = new Emp();
        emp.setNo("123");
        emp.setPass("123");
       Emp emp1= empMapper.login(emp);
        System.out.println(emp1.getFlag());
    }
    @Test
    public void testGetEmps(){
        List<Emp> empList = empMapper.getEmps(0,5);
        for(Emp emp:empList){
            System.out.println(emp);
        }
    }

    @Test
    public void getData(){
        List<TongJi> tongjis=empMapper.getData();
        for (TongJi tongji : tongjis) {
            System.out.println(tongji);

        }
        //对象转成json语句
        String jsonName= JSONObject.toJSONString(tongjis);
        System.out.println(jsonName);
    }
    @Test
    public void insertEmp(){

         for(int i=0;i<50;i++){
             Emp emp = new Emp();
             emp.setNo(GetCharAndNumr.getRanCode(8));
             emp.setName("张云"+i);
             emp.setSex("女");
             emp.setEmail("1431839255@qq.com");
             emp.setQq("1431839255");
             emp.setPhone("18851162803");
             emp.setCratedate("2020-11-16");
             emp.setPhoto("cc61bcc62e9f4743970ed33bfabef9bc.jpg");

         }

    }
    @Test
    public void testRole(){
      //  empMapper.addEmpRoles(52,2);
    }
    @Test
    public void getTotalCount(){
        System.out.println(empMapper.totalEmp());
    }
}
