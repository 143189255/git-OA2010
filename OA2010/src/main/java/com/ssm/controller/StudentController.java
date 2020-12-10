package com.ssm.controller;

import com.ssm.pojo.Student;
import com.ssm.pojo.TClass;
import com.ssm.service.StudentService;
import com.ssm.service.TClassService;
import com.ssm.utils.ExcelUtils;
import com.ssm.utils.PageUtils;
import com.ssm.utils.ResultMessage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.xml.transform.Result;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Scope("prototype")  //多例
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TClassService tClassService;
    @RequestMapping("/student_list/{pageIndex}/{pageSize}")
    public String getStudents(@PathVariable("pageIndex") Long pageIndex, @PathVariable("pageSize") Long pageSize, @RequestParam(value = "classId",defaultValue = "0") Long classId,@RequestParam(value = "name",defaultValue = "") String name, Model model)throws Exception{
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("pageStart",(pageIndex-1)*pageSize);
        map.put("pageSize",pageSize);
        map.put("classId",classId);
        map.put("name","%"+name+"%");
        int totalCount = studentService.getTotalCount(map);
        List<Student> students = studentService.getStudents(map);
        PageUtils pageUtils = new PageUtils(pageIndex,pageSize,totalCount,students);
        model.addAttribute("pageUtils",pageUtils);
        return "studentlist";
    }
    @RequestMapping("/student_getClasses")
    @ResponseBody
    public List<TClass> getClasses(){
           return tClassService.getClasses();
    }
    //导出数据到excel
    @RequestMapping("/student_excel")
     public List<Student> getExcelData(@RequestParam(value = "classId",defaultValue = "0") Long classId, @RequestParam(value = "name",defaultValue = "") String name, HttpServletResponse response){
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("classId",classId);
        map.put("name","%"+name+"%");
         List<Student> students = studentService.getExcels(map);
        System.out.println(students);
        //要导出的数据
        String[][] content = new String[students.size()][8];
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            content[i][0] = String.valueOf(s.getId());
            content[i][1] = String.valueOf(s.getNo());
            content[i][2] = String.valueOf(s.getName());
            content[i][3] = String.valueOf(s.getSex());
            content[i][4] = String.valueOf(s.getEmail());
            content[i][5] = String.valueOf(s.getPhone());
            content[i][6] = String.valueOf(s.gettClass().getClassName());
            content[i][7] = String.valueOf(s.getSchool());
        }

        String[] title = {"序号", "学号", "姓名", "性别", "邮箱", "电话", "班级", "学校"};
        //需要把查询到的集合中的数据，生成一个Excel表格，然后响应给用户！
        HSSFWorkbook wb = ExcelUtils.getHSSFWorkbook("学生信息统计", title, content);
        //生成Excel
        //响应到客户端
        try {
            //excel文件名
            String fileName = "学生信息表" + System.currentTimeMillis() + ".xls";
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);//响应给客户端
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
         return students;

    }
    /**
     * 发送响应流方法
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @RequestMapping("/student_importExcel")
    public void importExcel(Integer classId, MultipartFile mFile, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        System.out.println("要上传的Excel文件名是:" + mFile.getOriginalFilename());
        System.out.println("要上传的班级是:" + classId);
        InputStream inputStream = mFile.getInputStream();
        //解析Excel文件中的内容
        HSSFWorkbook wb = new HSSFWorkbook(inputStream);// 创建Excel2003文件对象
        HSSFSheet sheet = wb.getSheetAt(0);// 取出第一个工作表，索引是0
        List<Student> students=new ArrayList<>();
        // 开始循环遍历行，表头不处理，从1开始
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            Student s = new Student();
            HSSFRow row = sheet.getRow(i);// 获取行对象
            if (row == null) {// 如果为空，不处理
                continue;
            }
            String cellStr = "";
            // 循环遍历单元格
            for (int j = 0; j < row.getLastCellNum(); j++) {
                HSSFCell cell = row.getCell(j);// 获取单元格对象
                if (cell == null) {// 单元格为空设置cellStr为空串
                    cellStr = "";
                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
                    cellStr = String.valueOf(cell.getBooleanCellValue());
                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
                    cellStr = cell.getNumericCellValue() + "";
                } else {// 其余按照字符串处理
                    cellStr = cell.getStringCellValue();
                }
                if (j == 0) {
                    s.setNo(cellStr);
                } else if (j == 1) {
                    s.setName(cellStr);
                }else if(j==2){
                    s.setSex(cellStr);
                }else if(j==3){
                    s.setEmail(cellStr);
                }else if(j==4){
                    s.setPhone(cellStr);
                }else if(j==5){
                    s.setClassId(classId);
                }else if(j==6){
                    s.setSchool(cellStr);
                }
            }
            System.out.println(s);
            students.add(s);
        }
        //把这些对象新增到数据库！
        studentService.inserStudents(students);
        inputStream.close();

        response.getWriter().write("<script>alert('导入成功！');location.href='/student_list/1/5';</script>");

    }
    @RequestMapping("/student_insert")
    @ResponseBody
    public ResultMessage insertStudent(@Valid Student student,BindingResult result){
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
            int count= studentService.insertStudent(student);
            if(count>0){
                resultMessage = new ResultMessage(100,"添加成功");
            }else{
                resultMessage = new ResultMessage(200,"添加失败");
            }
        }
        return resultMessage;

    }
}
