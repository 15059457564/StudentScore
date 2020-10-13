package com.chenhao.stuscore.Util;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.chenhao.stuscore.domain.StudentExcel;
import com.chenhao.stuscore.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ch
 * @date 2020/10/9
 **/
@Component
@Scope("prototype")
public class WebStudentListener extends AnalysisEventListener<StudentExcel> {


    @Autowired
    StudentService studentService;

    List<StudentExcel> students=new ArrayList();
    @Override
    public void invoke(StudentExcel student, AnalysisContext analysisContext) {
            students.add(student);
            studentService.readExcel(students);
            students.clear();

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
