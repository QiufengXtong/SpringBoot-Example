package cn.xtong.example.config;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

/**
 * Mybatis-Plus 自动注入数据
 */
@Configuration
public class AutoFillFieldValueConfig implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        // 默认设置所有创建人都是ADMIN
        String userId = "ADMIN";
        this.fillStrategy(metaObject,"create_user",userId);
        this.fillStrategy(metaObject,"create_time", DateUtil.date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 默认设置所有修改人都是ADMIN
        String userId = "ADMIN";
        this.fillStrategy(metaObject,"update_user",userId);
        this.fillStrategy(metaObject,"update_user",DateUtil.date());
    }
}
