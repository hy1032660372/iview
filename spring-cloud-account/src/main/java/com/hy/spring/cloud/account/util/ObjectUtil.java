package com.hy.spring.cloud.account.util;

import com.hy.spring.cloud.account.domain.SysRoleImpl;
import com.hy.spring.cloud.account.domain.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName: ObjectConvert
 * Author:   hy103
 * Date:     2018/8/23 22:28
 * Description: canvert obj
 */
public class ObjectUtil{

    //内省
    public static Object mapToObjectIntrospector(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }

        return obj;
    }

    public static Map<String, Object> objectToMapIntrospector(Object obj) throws Exception {
        if(obj == null)
            return null;

        Map<String, Object> map = new HashMap<String, Object>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter!=null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

    //反射
    public static Object mapToObjectInstance(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null)
            return null;

        Object obj = beanClass.newInstance();

        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            int mod = field.getModifiers();
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                continue;
            }

            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }

        return obj;
    }

    public static Map<String, Object> objectToMapInstance(Object obj) throws Exception {
        if(obj == null){
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();

        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj));
        }
        return map;
    }

    public static User getUser(Principal principal){
        OAuth2Authentication oa = ((OAuth2Authentication) principal);
        HashMap userInfo = (HashMap)oa.getUserAuthentication().getDetails();
        User user = new User((Map)userInfo.get("principal"));
        return user;
    }

    public static SysRoleImpl getRoleTree(Principal principal, List<SysRoleImpl> roleList){
        User user = getUser(principal);
        //找到根节点
        SysRoleImpl sysRole = new SysRoleImpl();
        for(int i = 0; i < roleList.size(); i++){
            sysRole = roleList.get(i);
            if(user.getCurrentRole().getRoleCode().equals(sysRole.getCode())){
                break;
            }
        }
        TreeUtil.converseToTree(roleList,sysRole);
        return sysRole;
    }

}
