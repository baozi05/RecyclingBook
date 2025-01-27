package org.example.baozi.recyclingbook.service;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.baozi.recyclingbook.mapper.StudentMapper;
import org.example.baozi.recyclingbook.model.DTO.StudentLoginDTO;
import org.example.baozi.recyclingbook.model.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentService extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    @Autowired
    private StudentMapper studentMapper;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // 密码验证

    @Override
    public void studentLogin(String studentId) {
        String initialPassword = "zjut"+studentId.substring(studentId.length()-6);
        String encodedPassword = encoder.encode(initialPassword);
        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("password", encodedPassword);
        update(updateWrapper);
    }

    @Override
    public boolean updateInitialPassword(String studentId, String newPassword) {
        return false;
    }

    @Override
    public boolean verifyPassword(String studentId, String password) {
        Student student = lambdaQuery().eq(Student::getStudentId, studentId).one();
        return encoder.matches(password, student.getPassword());
    }

    @Override
    public boolean isFirstLogin(String studentId) {
        Student student = lambdaQuery().eq(Student::getStudentId, studentId).one();
        return student.getIsFirstLogin();
    }

    @Override
    public void updateLoginStatus(String studentId) {
        Student student = lambdaQuery().eq(Student::getStudentId, studentId).one();
        student.setIsFirstLogin(false);
    }


}
