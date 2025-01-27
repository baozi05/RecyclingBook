package org.example.baozi.recyclingbook.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.baozi.recyclingbook.model.DTO.StudentLoginDTO;
import org.example.baozi.recyclingbook.model.Entity.Student;

public interface IStudentService extends IService<Student> {
    /**
     * 学生首次登录
     * @param studentId
     */
    void studentLogin(String studentId);

    /**
     * 学生首次登录修改密码
     * @param studentId
     * @param newPassword
     * @return
     */
    boolean updateInitialPassword(String studentId, String newPassword);

    /**
     * 验证密码
     * @param studentId
     * @param password
     * @return
     */

    boolean verifyPassword(String studentId, String password);

    /**
     * 判断是否第一次登录
     * @param studentId
     * @return
     */
    boolean isFirstLogin(String studentId);

    /**
     * 更新首次登录状态
     * @param studentId
     */
    void updateLoginStatus(String studentId);


}
