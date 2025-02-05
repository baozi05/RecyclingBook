package org.example.baozi.recyclingbook.mapper.Admin;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.baozi.recyclingbook.model.DTO.StudentRequestDTO;
import org.example.baozi.recyclingbook.model.Entity.Admin.Admin;
import org.example.baozi.recyclingbook.model.Entity.Student.Student;



@Mapper
public interface AdminMapper extends BaseMapper<Admin> {
    void ChangeReputation(@Param("newScore") int newScore, @Param("studentId") String studentId);
}
