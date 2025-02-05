package org.example.baozi.recyclingbook.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.baozi.recyclingbook.model.DTO.StudentRequestDTO;
import org.example.baozi.recyclingbook.model.Entity.Student.Student;
import org.example.baozi.recyclingbook.service.AdminService.AdminService;
import org.example.baozi.recyclingbook.ResponseMessage.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    // 分页获取所有学生
    @GetMapping("/getStudents")
    public IPage<Student> getAdmins(@RequestParam int pageNum, @RequestParam int pageSize) {
        return adminService.getUsersByPage(pageNum, pageSize);
    }

    // 删除学生
    @DeleteMapping("/delStudent")
    public ResponseMessage<Student> delStudent(@RequestParam StudentRequestDTO studentRequestDTO) {
        adminService.DeleteStu(studentRequestDTO.getStudentId());
        return ResponseMessage.success(204,"删除成功");
    }
//      Param方案
//    @DeleteMapping("/delStudent")
//    public ResponseMessage<Student> delStudent(@RequestParam String id) {
//        adminService.DeleteStu(id);
//        return ResponseMessage.success(204,"删除成功");
//    }
    // 学生信誉分改动
    @PatchMapping("/reputation")
    public ResponseMessage<StudentRequestDTO> changeReputation(@RequestBody @Valid StudentRequestDTO studentRequestDTO) {
        return ResponseMessage.success(
            adminService.ChangeReputation(
                    studentRequestDTO.getNewScore(),
                    studentRequestDTO.getStudentId()
            )
        );
    }

    // 重置学生密码
    @PatchMapping("/resetpassword")
    public ResponseMessage<Student> resetPassword(@RequestParam String studentId) {
        try{
            if(adminService.resetPassword(studentId)){
                return ResponseMessage.success(201,"密码重置成功");
            }
            else {
                return ResponseMessage.error("密码重置失败");
            }
        }catch (RuntimeException e)
        {
            return ResponseMessage.info(e.getMessage());
        }

    }
}
