package com.soft1851.music.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.soft1851.music.admin.domain.entity.RoleMenu;
import com.soft1851.music.admin.domain.entity.SysMenu;
import com.soft1851.music.admin.mapper.SysMenuMapper;
import com.soft1851.music.admin.service.RoleMenuService;
import com.soft1851.music.admin.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yy
 * @since 2020-04-21
 */
@Service
public class
SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;
    @Resource
    private RoleMenuService roleMenuService;

    @Override
    public SysMenu getMenuById(String id) {
        return sysMenuMapper.selectById(id);
    }

    @Override
    public List<SysMenu> getMenuListByRoleId(String roleId) {
        List<RoleMenu> roleMenus = roleMenuService.getRoleMenuByRoleId(roleId);
        List<SysMenu> sysMenus = new ArrayList<>(10);
        SysMenu sysMenu = null;
        for (RoleMenu roleMenu : roleMenus) {
            sysMenu = getMenuById(roleMenu.getMenuId().toString());
            sysMenus.add(sysMenu);
        }
        return sysMenus;
    }
}
