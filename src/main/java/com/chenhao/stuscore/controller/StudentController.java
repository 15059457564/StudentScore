package com.chenhao.stuscore.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.chenhao.stuscore.Util.WebStudentListener;
import com.chenhao.stuscore.domain.Clazz;
import com.chenhao.stuscore.domain.College;
import com.chenhao.stuscore.domain.Student;
import com.chenhao.stuscore.domain.StudentExcel;
import com.chenhao.stuscore.service.ClazzService;
import com.chenhao.stuscore.service.CollegeService;
import com.chenhao.stuscore.service.StudentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/9/4
 **/
@Controller
public class StudentController {

    @Autowired
    WebStudentListener webStudentListener;
    @Autowired
    StudentService studentService;
    @Autowired
    ClazzService clazzService;
    @Autowired
    CollegeService collegeService;

        //学生查询
    @GetMapping("/student")
    public ModelAndView student(ModelAndView mv,
                                @RequestParam(required = true,name = "page",defaultValue = "1")Integer page,
                                @RequestParam(name = "size",defaultValue = "10") Integer size
            ,@RequestParam(value = "name",defaultValue = "") String name){
        List<College> colleges = collegeService.getAll();
        List<Clazz> clazzs = clazzService.getAll();
        List<Student> students = studentService.Pagination(page,size,name);
        PageInfo pageInfo = new PageInfo(students);
        pageInfo.setPageSize(size);
        mv.addObject("pageInfo", pageInfo);
        mv.addObject("colleges",colleges);
        mv.addObject("clazzs",clazzs);
        mv.addObject("students",students);
        mv.setViewName("student/student");
        return mv;
    }

    @RequestMapping("/addStudents")
    public ModelAndView addStudents(ModelAndView mv){
        mv.setViewName("student/addStudents");
        return mv;
    }
    //读取excel学生表里的内容
    @RequestMapping("/studentRead")
    public String studentRead(MultipartFile files){
        try {
            //工作簿
            ExcelReaderBuilder readWorkBook = EasyExcel.read(files.getInputStream(), StudentExcel.class, webStudentListener);
            //工作表
            readWorkBook.sheet().doRead();
            //读取数据

            return "redirect:/student";
        } catch (IOException e) {
            e.printStackTrace();
            return "fail";
        }

    }

    @RequestMapping("/studentWrite")
    public void studentRead(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        String fileName = URLEncoder.encode("学生信息","UTF-8");
        response.setHeader("Content-Disposition","attachment;filename*=UTF-8''"+
                fileName + ".xlsx");
        ServletOutputStream outputStream = response.getOutputStream();
        ExcelWriterBuilder writeWorkBook = EasyExcel.write(outputStream, StudentExcel.class);
        ExcelWriterSheetBuilder sheet = writeWorkBook.sheet();
        List<Student> students =studentService.getAll();
        List<StudentExcel> studentExcels =new ArrayList<>();
        for (Student student:students
             ) {
            StudentExcel studentExcel = new StudentExcel();
            studentExcel.setClazz(clazzService.findClazzNameByid(student.getClazzid()));
            studentExcel.setCollege(collegeService.findCollegeNameById(student.getCid()));
            studentExcel.setAge(student.getAge());
            studentExcel.setName(student.getName());
            studentExcel.setPassword(student.getPassword());
            studentExcel.setSex(student.getSex());
            studentExcel.setSno(student.getSno());
            studentExcels.add(studentExcel);

        }

        sheet.doWrite(studentExcels);
    }


    @PostMapping("/addStudent")
    public String addStudent(Student student){
        studentService.addStudent(student);

        return "redirect:/student";
    }
    //到修改表单  修改学生信息
    @ResponseBody
    @GetMapping("/editStudentPage")
    public ModelAndView editClazzPage (ModelAndView mv,Integer id){
        List<Clazz> clazzs = clazzService.getAll();
        Student student = studentService.findById(id);
        List<College> colleges = collegeService.getAll();
        mv.addObject("colleges",colleges);
        mv.addObject("student",student);
        mv.addObject("clazzs",clazzs);
        mv.setViewName("/student/editStudent");
        return mv;
    }

    //修改学生信息
    @PostMapping("/editStudent")
    public String editStudent(Student student){
//        System.out.println(student);
        studentService.edit(student);

        return "redirect:/student";
    }
    //删除班级
    @GetMapping("/deleteStudent/{id}")
    public String deleteTeacher(@PathVariable("id") Integer id){
        studentService.deleteStudent(id);
        return "redirect:/student";
    }
//通过ajax实现级联查询
    @ResponseBody
    @GetMapping("/getClazz")
    public Object getClazz(@RequestParam("collegeid") Integer collegeid){

        List<Clazz> clazzs = clazzService.findByCollegeId(collegeid);
        return clazzs;
    }
}
