package cn.santeamo.common.config;

import cn.santeamo.common.util.ParamUtil;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Properties;

/**
 * 上传属性
 *
 * @author shenle
 */
public class OtherPropertiesConfig {

    private static final Properties PROPERTIES;

    static {
        Resource resource = new ClassPathResource("application-other.yml");
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(resource);
        PROPERTIES = yamlFactory.getObject();
        assert PROPERTIES != null;
    }

    public static class FileSavePath {

        public static final String ROOT;
        public static final String RESOURCE_PATH;
        public static final String UPLOAD_DIR;
        public static final String SAVE_DIR_PATH;

        static {
            ROOT = PROPERTIES.getProperty("filesavepath.root.windows");
            RESOURCE_PATH = PROPERTIES.getProperty("filesavepath.resource-path");
            UPLOAD_DIR = PROPERTIES.getProperty("filesavepath.upload-dir");
            SAVE_DIR_PATH = ROOT + UPLOAD_DIR;
        }
    }
}
