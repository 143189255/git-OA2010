package com.ssm.controller;
import com.ssm.pojo.*;
import com.ssm.service.*;
import com.ssm.shiro.MyRealm;
import com.ssm.utils.CreateFileUtils;
import com.ssm.utils.PageUtils;
import com.ssm.utils.ResultMessage;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@Scope("prototype")
public class EmpController {
    @Autowired
    MyRealm myRealm;
    @Autowired
     EmpService empService;
    @Autowired
    LoginlogService loginlogService;
    @Autowired
     RoleService roleService;
    @Autowired
     PerssionService perssionService;
    @Autowired
    StudentService studentService;
    @ResponseBody
    @RequestMapping("/emp_add")
    public ResultMessage addEmp(Integer[] roleIds,@Valid Emp emp,BindingResult result) {
        ResultMessage requestMessage = null;
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
            requestMessage = new ResultMessage(500,sb.toString());
        }else {
            List<String> Nos = empService.getAllEmpNo();
            boolean flag= true;
            for (String no : Nos) {
                if(emp.getNo().equals(no)) {
                    flag = true;
                } else
                    flag = false;
            }
            if(flag){
                requestMessage = new ResultMessage(500, "该员工已存在，请勿重复添加");
            }else{
                int count = empService.addEmp(emp, roleIds); //事务在业务逻辑层处理
                if (count > 0) {
                    requestMessage = new ResultMessage(200, "员工添加成功");
                } else {
                    requestMessage = new ResultMessage(500, "员工添加失败");
                }
            }
            }

        return requestMessage;
    }
    @RequestMapping("/emp_login")
    @ResponseBody//将返回值自动转化为JSON
    public ResultMessage login(Emp emp, String ip, String cityAndAddress, HttpServletResponse response) throws Exception {
        ResultMessage message = null;
        //获取员工人数存入session
        int empCount =empService.totalEmp();
        //获取学生人数存入session
        int studentCount=studentService.getStudentTotal();
        response.setContentType("text/html;charset=utf-8");
          Emp loginEmp = empService.login(emp);
            if (loginEmp != null) {
                if (loginEmp.getFlag() == 1) {
                     //获取主题
                    org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
                    //生成令牌
                    UsernamePasswordToken token = new UsernamePasswordToken(emp.getNo(), emp.getPass());
                    //告知Shiro
                    subject.login(token);//切记一定要执行  告诉Shiro认证成功
                    //登录用户存储到Session
                    Session session = subject.getSession();
                    session.setAttribute("loginEmp", loginEmp);
                    session.setAttribute("empCount",empCount);
                    session.setAttribute("studentCount",studentCount );
                    //获取登录后用户的角色信息
                    List<Role> roles = roleService.getRolesByEmpId(loginEmp.getId());
                    StringBuffer stringBuffer = new StringBuffer();
                    for (Role role : roles) {
                        stringBuffer.append(role.getRName()+" ");
                    }
                    session.setAttribute("roleNames",stringBuffer.toString());
                    //获取登录用户的角色菜单权限
                    List<Perssion> perssions = perssionService.getMenus(loginEmp.getId());
                    session.setAttribute("myMenus",perssions);
                    message = new ResultMessage(200, "登录成功");
                    Loginlog loginlog = new Loginlog(ip,emp.getNo(),cityAndAddress);
                    int count= loginlogService.addLoginlog(loginlog);
                    System.out.println(count>0?"添加日志成功":"添加日志失败");
                } else {
                    //response.getWriter().write("<script>alert('账号已经被禁用，请联系管理员!');location.href='login.jsp';</script>");
                      message = new ResultMessage(300, "账号已经被禁用，请联系管理员!");
                }
            } else {
                //response.getWriter().write("<script>alert('账号或密码错误!');location.href='login.jsp';</script>");
                 message = new ResultMessage(500, "账号或密码错误!");
            }
            //把message对象使用工具，转为JSON字符串，不用人工拼接
        return message;

    }
    @ResponseBody
    @RequestMapping("/getLoginlogs")
    public List<Loginlog> getLoginlogs(HttpSession session){
        return loginlogService.getLastLog(((Emp)session.getAttribute("loginEmp")).getNo());
    }
    //分页
    @RequestMapping("/emp_list/{pageIndex}/{pageSize}")
    public String emp_list(@PathVariable("pageIndex") long pageIndex, @PathVariable("pageSize") int pageSize, Model model){
        int totalCount = empService.getTotalCount();  //获取总条数
        List<Emp> emps = empService.getEmps((pageIndex-1)*pageSize,pageSize);
        PageUtils pageUtils = new PageUtils(pageIndex,pageSize,totalCount,emps);
        model.addAttribute("pageUtils",pageUtils);
        return "emplist";
    }
    //头像更改
    @RequestMapping("/emp_updatePhoto")
    public void updatePhoto(MultipartFile mFile,HttpSession session,HttpServletResponse response)throws Exception{
        response.setContentType("text/html;charset=utf-8");
        String oldName = mFile.getOriginalFilename();
        System.out.println("要导入的文件原始名称："+oldName);
        // 获得文件后缀名称
        String suffixName = oldName.substring(oldName.lastIndexOf(".") + 1).toLowerCase();
// 生成最新的uuid文件名称
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String newName = uuid + "."+ suffixName;
        //获取文件上传的真实的文件服务器路径
        String realPath = session.getServletContext().getRealPath("/media/upload");
        realPath=realPath+"\\"+newName;
        System.out.println("真实的要上传的文件名称："+realPath);
        //实现文件上传
        mFile.transferTo(new File(realPath));
      Emp empLogin = (Emp) session.getAttribute("loginEmp");  //获取头像更改前的session信息
        Emp emp = new Emp();
        emp.setId(empLogin.getId());
        emp.setName(empLogin.getName());
        emp.setPhoto(newName);
        empService.updatePhoto(emp);
        session.setAttribute("loginEmp",emp);  //把session中的信息更改为新头像
        response.getWriter().write("<script>alert('头像修改成功！');parent.location.href='/page_index';</script>");//parent.location.href跳转到父窗体
    }
    @RequestMapping("/emp_resetUser")
        public void resetUser(HttpSession session, HttpServletRequest request,HttpServletResponse response)throws Exception{
        org.apache.shiro.subject.Subject subject = SecurityUtils.getSubject();
        myRealm.clearCached();
        subject.logout();
          //session.invalidate();
        response.sendRedirect("login.jsp");
    }
    @RequestMapping("/emp_delete")
    @ResponseBody
    public ResultMessage deleteEmp(int id){
        ResultMessage resultMessage = null;
       int count= empService.deleteEmp(id);
        if(count>0){
            resultMessage = new ResultMessage(100,"员工删除成功");
        }else{
            resultMessage = new ResultMessage(200,"员工删除失败");

        }
        return resultMessage;
    }
    @ResponseBody
    @RequestMapping("/emp/uploade")
    public Map<String, Object> uploadePhoto(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        // 文件保存路径
        String photo = "";
        // 创建保存目录
        File createDir = CreateFileUtils.createDir(request.getSession().getServletContext());
        // 判断上传文件是否为空
        if (!file.isEmpty()) {
            // 获取上传文件的文件名
            String filename = file.getOriginalFilename();
            // 修改为随机文件名
            String createName = CreateFileUtils.createName(filename);
            File f = new File(createDir, createName);
            // 在数据库中保存路径为：\photos\2018-09-25\20180925142551_12.jpg
            photo = "media/photo/" + new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime()) + "/"
                    + createName;
            // 上传文件
            file.transferTo(f);
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("msg", photo); //上传成功之后的路径....
        map.put("code", 200); //状态码

        return map;
    }
    @RequestMapping("/empupdate_{id}")
    public String empupdate(@PathVariable("id") long id,HttpSession session){
        session.setAttribute("empid",id);
        return "empupdate";
    }
    @RequestMapping("/emp_update")
    @ResponseBody
    public ResultMessage emp_update(Integer[] roleIds,@Valid Emp emp,BindingResult result) {
        ResultMessage requestMessage = null;
        //        首先要看，有没有校验出错
        int errorCount = result.getErrorCount();
        if (errorCount > 0) {
//            获取所有错误
            List<FieldError> errors = result.getFieldErrors();
            //遍历所有错误
            StringBuffer sb = new StringBuffer();
            for (FieldError fieldError : errors) {
                String msg = fieldError.getDefaultMessage();
                sb.append(msg);
            }
            requestMessage = new ResultMessage(500, sb.toString());
        } else {
            int count = empService.empupdate(emp, roleIds); //事务在业务逻辑层处理
            if (count > 0) {
                requestMessage = new ResultMessage(200, "员工修改成功");
            } else {
                requestMessage = new ResultMessage(500, "员工修改失败");
            }
        }
        return requestMessage;
    }

}
