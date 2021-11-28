package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author ocean
 * @date 2021/9/29
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
