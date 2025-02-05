package org.example.baozi.recyclingbook.service.StudentService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.example.baozi.recyclingbook.mapper.Student.StudentMapper;
import org.example.baozi.recyclingbook.model.DTO.StudentInfoDTO;
import org.example.baozi.recyclingbook.model.Entity.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService extends ServiceImpl<StudentMapper, Student> implements IStudentService {
    private final StudentMapper studentMapper;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // 密码验证

    @Override
    public void studentLogin(String studentId) {
        // 生成默认密码 zjut+后六位学号
        String initialPassword = "zjut"+studentId.substring(studentId.length()-6);
        // 加密
        String encodedPassword = encoder.encode(initialPassword);

        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        // 设置密码
        updateWrapper.eq("studentId",studentId)
                    .set("password", encodedPassword);
        update(updateWrapper);
    }

    /**
     * 信息更新或绑定
     * @param studentInfoDTO
     * @return
     */
    @Override
    public void updateStudentInfo(StudentInfoDTO studentInfoDTO) {
        if(!studentInfoDTO.getConfirmPassword().equals(studentInfoDTO.getNewPassword())){
            throw new RuntimeException("确认密码与新密码不一致");
        }
        // 因为传入参数为DTO类，但部分数据库操作需要entity类，且为了数据封装安全，此处需要加入entity的学生类接收DTO类内部数据
        Student studentPojo = studentMapper.selectById(studentInfoDTO.getStudentId());
        String newPassword = encoder.encode(studentInfoDTO.getNewPassword());

        UpdateWrapper<Student> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("studentId", studentInfoDTO.getStudentId())
                .set("password", newPassword)
                .set("phone", studentInfoDTO.getStudentPhone())
                .set("campus", studentInfoDTO.getStudentCampus())
                .set("dormitory", studentInfoDTO.getStudentDormitory())
                .set("payment_method", studentInfoDTO.getPayment_method());
        update(studentPojo, updateWrapper);

    }

    @Override
    public boolean verifyPassword(String studentId, String password) {
        Student student = lambdaQuery().eq(Student::getStudentId, studentId).one();
        return encoder.matches(password, student.getPassword()); // 密码加密
    }

    @Override
    public boolean isFirstLogin(String studentId) {
        Student student = lambdaQuery().eq(Student::getStudentId, studentId).one();
        return student.getIsFirstLogin();
    }

    /**
     * 更新首次登录状态
     * @param studentId
     */
    @Override
    public void updateLoginStatus(String studentId) {
        Student student = lambdaQuery().eq(Student::getStudentId, studentId).one();
        student.setIsFirstLogin(false);
        updateById(student);
    }


}
