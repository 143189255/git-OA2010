package com.ssm.controller;

import com.ssm.pojo.TClass;
import com.ssm.service.TClassService;
import com.ssm.utils.ResultMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@Scope("prototype")

public class TClassController {
  @Autowired
  private TClassService tClassService;
    @RequestMapping("/class_add")
    @ResponseBody
//       @Valid注解功能为参数添加验证，@BindingResult注解功能为输出错误信息
    public ResultMessage ClassAdd(@Valid TClass c, BindingResult result){

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
        }else{
            int count= tClassService.addClass(c);

            if(count>0){
                resultMessage = new ResultMessage(100,"添加成功");
            }else{
                resultMessage = new ResultMessage(200,"添加失败");
            }

        }
        return resultMessage;

    }
    @RequestMapping("/tclass_getAllClasses")
     @ResponseBody
        public List<TClass> getAllClasses(Model model, HttpSession session){
        List<TClass> clss = tClassService.getClasses();
       model.addAttribute("cla",clss);
       session.setAttribute("cla",clss);
        return clss;

    }
    @RequestMapping("/class_list/{pageIndex}/{pageSize}")
    public String getClassList(@PathVariable("pageIndex") long pageIndex,@PathVariable("pageSize") long pageSize,Model model){
        //获取总条数
        int count = tClassService.getTotalCount();
           return "";
    }
}
