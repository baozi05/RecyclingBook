package org.example.baozi.recyclingbook.controller;

import org.example.baozi.recyclingbook.model.DTO.StudentLoginDTO;
import org.example.baozi.recyclingbook.model.Entity.Student;
import org.example.baozi.recyclingbook.service.StudentService;
import org.example.baozi.recyclingbook.view.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/login")
    public ResponseMessage<Student> login(@RequestBody @Valid StudentLoginDTO studentLoginDTO){
        String userid= studentLoginDTO.getStudentId();
        Student stu=studentService.lambdaQuery().eq(Student::getStudentId, userid).one(); //先查找是否已注册
        if(stu==null){
            return ResponseMessage.error("学号不存在");
        }
        if(stu.getIsFirstLogin()){
            // 首次登录
            studentService.studentLogin(userid);
            if(studentService.verifyPassword(userid,studentLoginDTO.getPassword())){
                return ResponseMessage.success(201,"用户首次登录");
            }
            return  ResponseMessage.info("密码错误");

        }
        else {
            String password = studentLoginDTO.getPassword();
            if(studentService.verifyPassword(userid, password)){
                return ResponseMessage.success(200,"登录成功");
            }
            else
                return  ResponseMessage.info("密码错误");
        }
    }
}
