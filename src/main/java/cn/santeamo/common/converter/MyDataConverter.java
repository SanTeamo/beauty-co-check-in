package cn.santeamo.common.converter;

import cn.santeamo.common.util.LocalDateUtil;
import cn.santeamo.common.util.ParamUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class MyDataConverter {

    interface StringToLocalDateConverter extends Converter<String, LocalDate> {
    }

    @Bean
    public StringToLocalDateConverter localDateConverter() {
        return source -> ParamUtil.isBlank(source) ? null : LocalDateUtil.parseDate(source);
    }

    interface StringToLocalDateTimeConverter extends Converter<String, LocalDateTime> {
    }

    @Bean
    public StringToLocalDateTimeConverter localDateTimeConverter() {
        return source -> ParamUtil.isBlank(source) ? null : LocalDateUtil.parseDateTime(source);
    }
}
