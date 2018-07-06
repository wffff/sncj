package com.sncj.web.controller;

import com.sncj.core.baseconfig.BasePage;
import com.sncj.core.baseconfig.ReturnMessage;
import com.sncj.core.student.entity.StudentEntity;
import com.sncj.core.student.service.IStudentService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018/7/6.
 */
@Controller
public class StudentController {
    @Resource
    private IStudentService iStudentService;

    @RequestMapping("/findAll")
    @ResponseBody
    public ReturnMessage<List<StudentEntity>> findAll(BasePage basePage, StudentEntity student) {
        Page<StudentEntity> page1 = iStudentService.findAll(basePage, student);
        return ReturnMessage.success((int) page1.getTotalElements(), page1.getContent());
    }
    @RequestMapping("/create")
    @ResponseBody
    public ReturnMessage<List<StudentEntity>> Create(StudentEntity student) {
        List<StudentEntity> list = iStudentService.create(student);
        return ReturnMessage.success(list.size(),list);
    }
}
