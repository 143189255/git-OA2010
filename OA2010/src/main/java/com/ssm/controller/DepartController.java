package com.ssm.controller;

import com.alibaba.druid.sql.PagerUtils;
import com.ssm.pojo.Depart;
import com.ssm.service.DepartService;
import com.ssm.utils.PageUtils;
import com.ssm.utils.ResultMessage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.transform.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@Scope("prototype")
public class DepartController {
    @Autowired
    DepartService departService;
    //分页
    @RequestMapping("/depart_list/{pageIndex}/{pageSize}")
    public String depart_list(@PathVariable("pageIndex") long pageIndex,@PathVariable("pageSize") int pageSize,Model model){
        int totalCount = departService.getTotalCount();  //获取总条数
        List<Depart> departs = departService.getDeparts((pageIndex-1)*pageSize,pageSize);
        for (Depart depart : departs) {
            int count = departService.getDepartCountById(depart.getId());
            depart.setCount(count);
//            departService.departUpdateById(depart);
        }
        PageUtils pageUtils = new PageUtils(pageIndex,pageSize,totalCount,departs);

        model.addAttribute("pageUtils",pageUtils);
        return "departList";
    }
@RequestMapping("/depart_add")
@ResponseBody
    public ResultMessage addDepart(@Valid Depart depart,BindingResult result){
    ResultMessage resultMessage = null;
    //        首先要看，有没有校验出错
    int errorCount = result.getErrorCount();
    if(errorCount>0){
//            获取所有错误
        List<FieldError> errors = result.getFieldErrors();
        //遍历所有错误
        StringBuffer sb = new StringBuffer();
        for(FieldError fieldError:errors){
            String msg = fieldError.getDefaultMessage();
            sb.append(msg);
        }
        resultMessage = new ResultMessage(200,sb.toString());
    }else {

//        boolean flag = true;
//        List<Depart> departs = departService.findAllDeparts();
//        for (int i = 0; i < departs.size(); i++) {
//            if (departs.get(i).getName().equals(depart.getName())) {
//                flag = false;
//                break;
//            } else {
//                flag = true;
//            }
//        }
//        if (flag) {
            int count = departService.addDepart(depart);
            if (count > 0) {
                resultMessage = new ResultMessage(100, "新增部门成功");

            } else {
                resultMessage = new ResultMessage(200, "新增部门失败");
            }
//        } else {
//            resultMessage = new ResultMessage(200, "部门名称重复");
//        }
    }
    return resultMessage;
}

//删除方法
//   @RequiresPermissions("depart:delete")
    @RequestMapping("/depart_delete")
 @ResponseBody
    public ResultMessage depart_delete(int id){
        ResultMessage resultMessage = null;
        int count=departService.deleteDepart(id);

        System.out.println(count);
        if(count>0){
            resultMessage = new ResultMessage(100,"部门删除成功");
        }else{
            resultMessage = new ResultMessage(200,"部门删除失败");

        }
        return resultMessage;
    }

//设置员工添加页面的部门下拉框
@RequestMapping("/depart_findAllDeparts")
@ResponseBody
    public List<Depart> findAllDeparts(){

        return departService.findAllDeparts();
    }
@RequestMapping("/depart_update_{id}")
    public String departUpdateById(@PathVariable("id") long id, HttpSession session) {
        session.setAttribute("departUpdate",id);
      return "departupdate";
  }
  @RequestMapping("/depart_update")
    @ResponseBody
    public ResultMessage departUpdate(Depart depart){
        ResultMessage resultMessage=null;
      Depart depart1 = departService.getDepartById(depart.getId());
      System.out.println("depart1"+depart1);
      System.out.println("depart"+depart);
        if (depart.getName()==null | depart.getName().length()==0) {
            depart.setName(depart1.getName());
        }
      if (depart.getManager()==null | depart.getManager().length()==0) {
          depart.setManager(depart1.getManager());
      }
      if (depart.getCreatetime()==null | depart.getCreatetime().length()==0) {
          depart.setCreatetime(depart1.getCreatetime());
      }
      int count=departService.departUpdateById(depart);
      if(count>0){
          resultMessage = new ResultMessage(100,"部门修改成功");
      }else{
          resultMessage = new ResultMessage(200,"部门修改失败");
      }
      return resultMessage;
  }
}
