package org.example.baozi.recyclingbook.mapper.Student;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.example.baozi.recyclingbook.model.Entity.Student.Student;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
