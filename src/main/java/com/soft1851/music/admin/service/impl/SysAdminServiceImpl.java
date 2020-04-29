package com.soft1851.music.admin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.common.ResultCode;
import com.soft1851.music.admin.domain.dto.LoginDto;
import com.soft1851.music.admin.domain.entity.SysAdmin;
import com.soft1851.music.admin.domain.entity.SysMenu;
import com.soft1851.music.admin.domain.entity.SysRole;
import com.soft1851.music.admin.exception.CustomException;
import com.soft1851.music.admin.mapper.SysAdminMapper;
import com.soft1851.music.admin.service.RedisService;
import com.soft1851.music.admin.service.SysAdminService;
import com.soft1851.music.admin.service.SysMenuService;
import com.soft1851.music.admin.service.SysRoleService;
import com.soft1851.music.admin.util.JwtTokenUtil;
import com.soft1851.music.admin.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    @Resource
    private RedisService redisService;

    @Override
    public Map<String, Object> login(LoginDto loginDto) {
        //根据查到基础信息，主要是要用密码来判定
        SysAdmin admin = sysAdminMapper.getSysAdminByName(loginDto.getName());
        if (admin != null) {
            //客户端密码加密后和数据库的比对
            String pass = Md5Util.getMd5(loginDto.getPassword(), true, 32);
            if (admin.getPassword().equals(pass)) {
                //登录成功，取得admin的完整信息（包含所有角色）
                SysAdmin adminWithRoles = sysAdminMapper.selectByName(loginDto.getName());
                //roles是个list，可能会是多个
                List<SysRole> roles = adminWithRoles.getRoles();
                String roleString = JSONObject.toJSONString(roles);
                log.info("管理员角色列表：" + roleString);
                //通过该管理员的id、roles、私钥、指定过期时间生成token
                String token = JwtTokenUtil.getToken(admin.getId(), JSONObject.toJSONString(roles), admin.getSalt(), new Date(System.currentTimeMillis() + 6000L * 10000L));
                log.info("## token={}", token);
                //将私钥存入redis，在后面JWT拦截器中可以取出来对客户端请求头中的token解密
                redisService.set(adminWithRoles.getId(), adminWithRoles.getSalt(), 100L);
                Map<String, Object> map = new TreeMap<>();
                map.put("admin", adminWithRoles);
                map.put("token", token);
                return map;
            } else {
                throw new CustomException("密码错误", ResultCode.USER_PASSWORD_ERROR);
            }
        } else {
            throw new CustomException("用户名不存在", ResultCode.USER_NOT_FOUND);
        }
    }


    @Override
    public SysAdmin getAdminAndRolesByName(String name) {
        return sysAdminMapper.selectByName(name);
    }

    @Override
    public String getToken(String adminId, String roles, String secret, Date expiresAt) {
        return JwtTokenUtil.getToken(adminId, roles, secret, expiresAt);
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
}