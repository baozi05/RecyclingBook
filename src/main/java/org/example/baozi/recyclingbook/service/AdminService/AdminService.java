package org.example.baozi.recyclingbook.service.AdminService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.example.baozi.recyclingbook.mapper.Admin.AdminMapper;
import org.example.baozi.recyclingbook.mapper.Student.StudentMapper;

import org.example.baozi.recyclingbook.model.DTO.StudentRequestDTO;
import org.example.baozi.recyclingbook.model.Entity.Student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class AdminService implements IAdminService {

    private final AdminMapper adminMapper;

    private final StudentMapper studentMapper;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // 密码验证

    @Override
    public void DeleteStu(String id) {
        studentMapper.deleteById(id);
    }
    @Override
    public Page<Student> getUsersByPage(int page, int size) {
        Page<Student> pages = new Page<>(page, size);
        QueryWrapper<Student> queryWrapper = new QueryWrapper<>();
        return studentMapper.selectPage(pages,queryWrapper);
    }

    @Override
    public StudentRequestDTO ChangeReputation(int val, String id) {
        // 更新信誉分
        adminMapper.ChangeReputation(val, id);
        StudentRequestDTO studentRequestDTO = new StudentRequestDTO();
        studentRequestDTO.setStudentId(id);
        studentRequestDTO.setNewScore(val);
        if(val<60)
            studentRequestDTO.setStatus(false);
        return studentRequestDTO;
    }

    @Override
    public boolean resetPassword(String studentId) {
        Student student = studentMapper.selectById(studentId);
        if(student==null){
            throw new RuntimeException("学生不存在");
        }
        String initialPassword = "zjut"+studentId.substring(studentId.length()-6);
        String encodedPassword = encoder.encode(initialPassword);
        student.setPassword(encodedPassword);
        student.setIsFirstLogin(true); // 默认密码更新后需要自己修改密码
        studentMapper.updateById(student);
        return true;
    }
}
