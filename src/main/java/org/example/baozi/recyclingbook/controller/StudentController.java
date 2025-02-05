package org.example.baozi.recyclingbook.controller;

import org.example.baozi.recyclingbook.model.DTO.StudentInfoDTO;
import org.example.baozi.recyclingbook.model.DTO.StudentLoginDTO;
import org.example.baozi.recyclingbook.model.Entity.Student.Student;
import org.example.baozi.recyclingbook.service.StudentService.StudentService;
import org.example.baozi.recyclingbook.ResponseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    // 登录
    @PostMapping("/login")
    public ResponseMessage<Student> login(@RequestBody @Valid StudentLoginDTO studentLoginDTO){
        String userid= studentLoginDTO.getStudentId();
        Student stu=studentService.lambdaQuery().eq(Student::getStudentId, userid).one(); //先查找是否已注册
        if(stu==null){
            System.out.println("can`t find id:"+userid);
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
                return  ResponseMessage.info(401,"密码错误");
        }
    }

    // 修改个人信息--包括首次登录绑定与后续进行个人修改
    // 同时绑定两个mapping
    @RequestMapping(value = {"/infotied","/infomod"},method = {RequestMethod.POST,RequestMethod.PUT})
    public ResponseMessage<Student> studentUpdate(@RequestBody @Valid StudentInfoDTO studentInfoDTO){
        Student studentPojo=studentService.lambdaQuery().eq(Student::getStudentId, studentInfoDTO.getStudentId()).one();
        try {
            studentService.updateStudentInfo(studentInfoDTO);
            if(studentPojo.getIsFirstLogin()) {
                // 首次登录更新首次登录状态
                studentService.updateLoginStatus(studentPojo.getStudentId());
            }
            return ResponseMessage.success(204,"修改成功");
        }catch (RuntimeException e){
            return ResponseMessage.error("两次密码不一致");
        }
    }
}
