package com.chj9.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chj9.cms.dao.mapper.UsersMapper;
import com.chj9.cms.api.entity.UsersEntity;
import com.chj9.cms.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 后台用户表 服务实现类
 * </p>
 *
 * @author GuoJingtao
 * @since 2019-03-20
 */
@Service
@Transactional
public class UsersServiceImpl extends ServiceImpl<UsersMapper, UsersEntity> implements UsersService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public UsersEntity selectUserByOpenId(String userName) {
        LambdaQueryWrapper<UsersEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UsersEntity::getUsername, userName);
        queryWrapper.orderByDesc(UsersEntity::getId);
        List<UsersEntity> selectList = baseMapper.selectList(queryWrapper);
        if (selectList.size() > 0) {
            return selectList.get(0);
        }
        return null;
    }

    @Override
    public UsersEntity register(String userName, String password) {
        try {
            UsersEntity users = selectUserByOpenId(userName);
            if (users != null) {
                return users;
            }
            users = new UsersEntity();
            users.setUsername(userName);
            users.setStatus(1);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encode = encoder.encode(password);
            users.setPassword(encode);
            save(users);
            return users;
        } catch (Exception e) {
            logger.info("保存用户信息发生异常{}", e.getMessage());
            return null;
        }


    }


}
