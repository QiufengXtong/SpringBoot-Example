package cn.xtong.example.util;

import cn.hutool.core.util.ObjectUtil;
import cn.xtong.example.user.enmus.SysUserSexEnum;
import cn.xtong.example.user.entity.SysUser;
import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Wrapper 工具类
 *
 * @param <T> 实体类
 */
public class WrapperUtil {

    private static final String GET = "get";
    private static final String skipParameter = "serialVersionUID";

    /**
     * 通过反射实现实体类转Wrapper
     *
     * @param obj 实体类
     * @return Wrapper
     */
    public static QueryWrapper entityToQueryWrapper(Object obj) {
        QueryWrapper wrapper = new QueryWrapper();
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            String fieldName = field.getName();
            try {
                if (ObjectUtil.equal(skipParameter, fieldName)) {
                    continue;
                }
                TableField tableFieldAnnotation = field.getAnnotation(TableField.class);
                TableId tableIdAnnotation = field.getAnnotation(TableId.class);
                String dbFieldName = ObjectUtil.isEmpty(tableFieldAnnotation) ? tableIdAnnotation.value() : tableFieldAnnotation.value();
                Method method = aClass.getDeclaredMethod(GET + StrUtil.firstLetterToUpper(fieldName));
                Object returnValue = method.invoke(obj);

                if (returnValue instanceof Enum) {
                    Class<?> enumClass = returnValue.getClass();
                    Field[] enumFields = enumClass.getDeclaredFields();
                    for (Field enumField : enumFields) {
                        EnumValue annotation = enumField.getAnnotation(EnumValue.class);
                        if (ObjectUtil.isNotEmpty(annotation)) {
                            Method enumMethod = enumClass.getDeclaredMethod(GET + StrUtil.firstLetterToUpper(enumField.getName()));
                            Object enumReturnValue = enumMethod.invoke(returnValue);
                            wrapper.eq(ObjectUtil.isNotEmpty(enumReturnValue), dbFieldName, enumReturnValue);
                        }
                    }
                } else {
                    wrapper.eq(ObjectUtil.isNotEmpty(returnValue), dbFieldName, returnValue);
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return wrapper;
    }

    public static void main(String[] args) {
        SysUser sysUser = new SysUser();
        sysUser.setSysUserSexEnum(SysUserSexEnum.MAN);
        QueryWrapper queryWrapper = entityToQueryWrapper(sysUser);
    }
}
