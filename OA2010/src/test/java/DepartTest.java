import com.alibaba.fastjson.JSONObject;
import com.ssm.mapper.DepartMapper;
import com.ssm.pojo.Depart;
import com.ssm.service.DepartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.LocalDate.now;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class DepartTest {
    @Autowired
    private DepartMapper departMapper;
    @Autowired
    private DepartService departService;
    @Test
    public void addDepart(){
            Depart depart = new Depart();
            depart.setName("赵云");
            depart.setManager("赵云");

            int count = departMapper.addDepart(depart);

    }

    @Test
    public void findAllDeparts() {
        List<Depart> departList = departMapper.findAllDeparts();
        List<String> dnames = new ArrayList<String>();
        for(Depart depart:departList){
            dnames.add(depart.getName());

        }
        System.out.println(dnames);
        //对象转成json语句
        String jsonName= JSONObject.toJSONString(dnames);
        System.out.println(jsonName);
    }
    @Test
    public void departUpdateById(){
        Depart depart = new Depart();
        depart.setId(2);
        depart.setCount(0);
        depart.setManager("张敏");
        departMapper.departUpdateById(depart);
    }
    @Test
    public void getDepartById(){
        System.out.println(departMapper.getDepartById(3));

    }
    @Test
    public void test() {
        Depart depart = new Depart();

//       int  pageIndex = 1;
//       int pageSize = 5;
//        List<Depart> departs = departService.getDeparts((pageIndex-1)*pageSize,pageSize);
//        System.out.println("------------------"+departs.size());
//        for (Depart depart : departs) {
//
//            System.out.println("aaaaaaaaaaaaaaaaaa"+depart.getId());
       int count  = departService.getDepartCountById(5);
        System.out.println(count);
//            System.out.println("====================="+depart.getCount());
//            departService.departUpdateById(depart);
//
//        }
//        System.out.println(departs);
//    }
    }
}
