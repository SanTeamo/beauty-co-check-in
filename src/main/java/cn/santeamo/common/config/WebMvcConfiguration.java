package cn.santeamo.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(OtherPropertiesConfig.FileSavePath.RESOURCE_PATH + "/**")
                .addResourceLocations("file:" + OtherPropertiesConfig.FileSavePath.SAVE_DIR_PATH + "/");
    }
}
