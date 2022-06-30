package com.ljc.eurekasupport.mapper;

import com.ljc.eurekasupport.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    @Select("select * from user where id = #{id}")
    User findUserById(int id);
}
