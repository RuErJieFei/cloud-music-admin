package com.soft1851.music.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.dto.LoginDto;
import com.soft1851.music.admin.entity.SysAdmin;
import com.soft1851.music.admin.entity.SysMenu;
import com.soft1851.music.admin.entity.SysRole;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.mapper.SysAdminMapper;
import com.soft1851.music.admin.service.SysAdminService;
import com.soft1851.music.admin.service.SysMenuService;
import com.soft1851.music.admin.service.SysRoleService;
import com.soft1851.music.admin.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
@Service
@Slf4j
public class SysAdminServiceImpl extends ServiceImpl<SysAdminMapper, SysAdmin> implements SysAdminService {
    @Resource
    private SysAdminMapper sysAdminMapper;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysMenuService sysMenuService;

    @Override
    public boolean login(LoginDto loginDto) {
        //注册一个wrapper用于查询
        QueryWrapper<SysAdmin> wrapper = new QueryWrapper<>();
        //查询的参数是name
        wrapper.eq("name", loginDto.getName());
        //查询的结果是password
        wrapper.select("password");
        SysAdmin admin1 = sysAdminMapper.selectOne(wrapper);
        if (admin1 != null) {
            String pass = Md5Util.getMd5(loginDto.getPassword(), true, 32);
            if (admin1.getPassword().equals(pass)) {
                return true;
            } else {
                log.error("密码错误");
                throw new CustomException("密码错误", ResultCode.USER_PASSWORD_ERROR);
            }
        } else {
            log.error("用户名不存在");
            throw new CustomException("用户名不存在", ResultCode.USER_NOT_FOUND);
        }
    }


    @Override
    public SysAdmin getAdmin(String name) {
//        Map<String, Object> params = new HashMap<>(8);
//        params.put("name", name);
//        List<SysAdmin> admins = sysAdminMapper.selectByMap(params);
//        if (admins.size() > 0) {
//            return sysAdminMapper.selectByMap(params).get(0);
//        } else {
//            return null;
//        }
        return sysAdminMapper.selectByName(name);
    }

    @Override
    public List<Map<String, List<SysMenu>>> getMenusListByAdminId(String adminId) {
        List<SysRole> sysRoles = sysRoleService.getRoleListById(adminId);
        if (sysRoles == null) {
            log.error("用户名不存在");
            throw new CustomException("用户名不存在", ResultCode.USER_NOT_FOUND);
        } else {
            List<SysMenu> sysMenusTemp = new ArrayList<>(10);
            Map<String, List<SysMenu>> map = new HashMap<>(10);
            List<Map<String, List<SysMenu>>> sysMenus = new ArrayList<>(10);
            for (SysRole sysRole : sysRoles) {
                sysMenusTemp = sysMenuService.getMenuListByRoleId(sysRole.getRoleId().toString());
                map.put(sysRole.getRoleName(), sysMenusTemp);
                sysMenus.add(map);
            }
            return sysMenus;
        }
    }

    @Override
    public SysAdmin getAdminAndRolesByName(String name) {
        return sysAdminMapper.selectByName(name);
    }
}