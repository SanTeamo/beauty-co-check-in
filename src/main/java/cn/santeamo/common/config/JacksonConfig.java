package cn.santeamo.common.config;

import cn.santeamo.common.util.LocalDateUtil;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.PackageVersion;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * jackson 配置
 *
 * @author shenle
 */
@Configuration
public class JacksonConfig {

    @Bean
    public Module getJavaTimeModule() {
        return JavaTimeModule.INSTANCE;
    }

    public static class JavaTimeModule extends SimpleModule {
        public static final JavaTimeModule INSTANCE = new JavaTimeModule();

        private JavaTimeModule() {
            super(PackageVersion.VERSION);
            this.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(LocalDateUtil.DATETIME_FORMATTER));
            this.addDeserializer(LocalDate.class, new LocalDateDeserializer(LocalDateUtil.DATE_FORMATTER));
            this.addDeserializer(LocalTime.class, new LocalTimeDeserializer(LocalDateUtil.TIME_FORMATTER));
            this.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(LocalDateUtil.DATETIME_FORMATTER));
            this.addSerializer(LocalDate.class, new LocalDateSerializer(LocalDateUtil.DATE_FORMATTER));
            this.addSerializer(LocalTime.class, new LocalTimeSerializer(LocalDateUtil.TIME_FORMATTER));
        }
    }
}
