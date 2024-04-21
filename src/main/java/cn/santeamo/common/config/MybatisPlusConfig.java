package cn.santeamo.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author shenle
 */
@Configuration
@MapperScan("cn.santeamo.**.mapper")
@RequiredArgsConstructor
public class MybatisPlusConfig {

    @Component
    public static class MpMetaObjectHandler implements MetaObjectHandler {
        @Override
        public void insertFill(MetaObject metaObject) {
            final String createTime = "createTime";
            Class<?> createTimeType = null;
            try {
                createTimeType = metaObject.getGetterType(createTime);
            } catch (Exception e) {
                //
            }
            if (LocalDateTime.class.equals(createTimeType)) {
                this.setFieldValByName(createTime, LocalDateTime.now(), metaObject);
            } else if (Date.class.equals(createTimeType)) {
                this.setFieldValByName(createTime, new Date(), metaObject);
            }
            this.setFieldValByName("deleted", 0, metaObject);
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            final String updateTime = "updateTime";
            final Class<?> createTimeType = metaObject.getGetterType(updateTime);
            if (LocalDateTime.class.equals(createTimeType)) {
                this.setFieldValByName(updateTime, LocalDateTime.now(), metaObject);
            } else if (Date.class.equals(createTimeType)) {
                this.setFieldValByName(updateTime, new Date(), metaObject);
            }
        }
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        /*
         * 使用多个功能需要注意顺序关系,建议使用如下顺序
         *
         * 多租户,动态表名
         * 分页,乐观锁
         * sql 性能规范,防止全表更新与删除
         */
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        // 防全表更新与删除插件
        interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
        return interceptor;
    }
}
