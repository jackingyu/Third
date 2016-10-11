package org.third.mybatis.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.third.domain.User;

public interface UserMapper {
 @Select("SELECT * FROM users WHERE id = #{userId}")
 User getUser(@Param("userId") String userId);
}
