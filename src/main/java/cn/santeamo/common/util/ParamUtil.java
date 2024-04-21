package cn.santeamo.common.util;

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * 通用工具类
 *
 * @author shenle
 */
public class ParamUtil {

    public static boolean isBlank(@Nullable String str) {
        return !isNotBlank(str);
    }

    public static boolean isNotBlank(@Nullable String str) {
        return StringUtils.hasText(str);
    }

    public static boolean isEmpty(@Nullable Object obj) {
        return ObjectUtils.isEmpty(obj);
    }

    public static boolean isNotEmpty(@Nullable Object obj) {
        return !isEmpty(obj);
    }
}
