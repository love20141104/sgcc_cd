package com.sgcc.config;

import com.sgcc.dto.RolePermissionDTO;
import com.sgcc.entity.query.UserQueryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * FilterInvocationSecurityMetadataSource 的作用是用来储存请求与权限的对应关系。
 */
@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private UserQueryEntity userQueryEntity;

    /**
     * 每一个资源所需要的角色 Collection<ConfigAttribute>决策器会用到
     */
    private static HashMap<String, Collection<ConfigAttribute>> map =null;

    /**
     * 返回请求的资源需要的角色
     *
     * 当接收到一个http请求时, filterSecurityInterceptor会调用的方法.
     * 参数object是一个包含url信息的HttpServletRequest实例.
     * 这个方法要返回请求该url所需要的所有权限集合。
     *
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        if (null == map) {
            loadResourceDefine();
        }
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        for (Iterator<String> it = map.keySet().iterator() ; it.hasNext();) {
            String url = it.next();
            if (new AntPathRequestMatcher( url ).matches( request )) {
                return map.get( url );
            }
        }
        return null;
    }

    /**
     * Spring容器启动时自动调用, 一般把所有请求与权限的对应关系也要在这个方法里初始化, 保存在一个属性变量里。
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        // 初始化 所有资源 对应的角色
        loadResourceDefine();
        return null;
    }

    /**
     * 指示该类是否能够为指定的方法调用或Web请求提供ConfigAttributes。
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    /**
     * 初始化 所有资源 对应的角色
     */
    public void loadResourceDefine() {
        map = new HashMap<>();
        //权限资源 和 角色对应的表  也就是 角色权限 中间表
        List<RolePermissionDTO> rolePermissionBOS = userQueryEntity.getRolePermissions();
        //某个资源 可以被哪些角色访问
        for (RolePermissionDTO rolePermissionDTO : rolePermissionBOS) {
            String url = rolePermissionDTO.getUrl();
            String roleCode = rolePermissionDTO.getRoleCode();
            ConfigAttribute role = new SecurityConfig(roleCode);
            if(map.containsKey(url)){
                map.get(url).add(role);
            }else{
                List<ConfigAttribute> list =  new ArrayList<>();
                list.add( role );
                map.put( url , list );
            }
        }
    }

}
