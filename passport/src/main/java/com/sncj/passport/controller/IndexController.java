package com.sncj.passport.controller;

import com.sncj.core.baseconfig.utils.SecurityUserUtils;
import com.sncj.core.baseconfig.constants.WebConstants;
import com.sncj.core.baseconfig.utils.RegexUtils;
import com.sncj.core.auth_center.dto.PermissionDTO;
import com.sncj.core.auth_center.enums.PermissionTypeEnum;
import com.sncj.core.auth_center.service.IPermissionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Danny on 2018/7/12.
 */
@Controller
public class IndexController {
    @Resource
    private IPermissionService iPermissionService;

    @RequestMapping("/")
    public String index() {
        return "home";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/principal")
    @ResponseBody
    public Principal principal(Principal principal) {
        return principal;
    }

    @RequestMapping("/main")
    public String home(@RequestParam(value = "page", required = false) String page, Model model, HttpServletRequest request) {
        model.addAttribute(WebConstants.MAIN_PAGE, !RegexUtils.notNull(page) ? WebConstants.MAIN_PAGE : page);
        Map<String, Object> menus = this.menusBuilder(page);
        model.addAllAttributes(menus);
        model.addAllAttributes(request.getParameterMap());
        request.getSession().setAttribute("func", menus.get("func"));
        request.getSession().setAttribute("tabs", menus.get("tabs"));
        request.getSession().setAttribute("menus", menus.get("menus"));
        return WebConstants.INDEX_PAGE;
    }

    @ResponseBody
    @RequestMapping("/menus")
    public Map<String, Object> menusBuilder(String page) {
        List<PermissionDTO> menus = null;
        List<PermissionDTO> tabs = null;
        List<PermissionDTO> func = null;
        try {
            menus = iPermissionService.listTreeByUser(SecurityUserUtils.getSecurityUser().getUsername());
            tabs = new ArrayList<>();
            func = new ArrayList<>();

            if (null != menus) {
                //如果页面传入地址遍历主菜单资源
                for (PermissionDTO menu : menus) {
                    List<PermissionDTO> children = menu.getChildren();
                    //遍历二级菜单资源
                    if (children == null) {
                        continue;
                    }
                    for (PermissionDTO subMenu : children) {
                        if (subMenu == null) {
                            continue;
                        }
                        //判断二级菜单的子资源
                        List<PermissionDTO> tabOrFunc = subMenu.getChildren();
                        if (null != tabOrFunc) {
                            for (PermissionDTO tof : tabOrFunc) {
                                if (tof == null) {
                                    continue;
                                }
                                if (PermissionTypeEnum.TABS == tof.getType()) {
                                    //装载选项卡资源集合
                                    if (subMenu.getUrl().equals(page) && tof.getParentId().equals(subMenu.getId())) {
                                        subMenu.setSpread(true);
                                        menu.setSpread(true);
                                        tabs.add(tof);
                                    }
                                    List<PermissionDTO> subFunc = tof.getChildren();
                                    if (subFunc == null) {
                                        continue;
                                    }
                                    //装载选项卡的子功能模块资源集合
                                    for (PermissionDTO function : subFunc) {
                                        if (function == null) {
                                            continue;
                                        }
                                        if (PermissionTypeEnum.FUNC == function.getType()) {
                                            if (subMenu.getUrl().equals(page) && function.getParentId().equals(tof.getId())) {
                                                func.add(function);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数据结构发生错误，请先检查是否是异常操作资源！");
        }
        Map<String, Object> map = Map.of("menus", menus, "tabs", tabs, "func", func
        );

        return map;
    }
}
