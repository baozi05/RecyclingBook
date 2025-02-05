package org.example.baozi.recyclingbook.service.AdminService;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.example.baozi.recyclingbook.model.DTO.StudentRequestDTO;
import org.example.baozi.recyclingbook.model.Entity.Student.Student;


public interface IAdminService {

    /**
     * 根据id删除学生
     * @param id
     */
    void DeleteStu(String id);

    /**
     * 分页查找学生
     * @param page
     * @param size
     * @return
     */
    Page<Student> getUsersByPage(int page, int size);

    /**
     * 学生信誉分变更---同时更新账号状态
     * @param val 新的信誉分
     * @param id 学生ID
     * @return 更新后的学生信息
     */
    StudentRequestDTO ChangeReputation(int val, String id);

    /**
     * 重置密码为zjut+学号后六位
     * @param id
     * @return 是否重置成功
     */
    boolean resetPassword(String id);
}
