package cn.sduonline.wings.constant;

import java.util.Arrays;
import java.util.List;

/**
 * Created by imaxct on 18-9-27.
 */
public class SettingName {

    /**
     * 公告
     */
    public static final String ANNOUNCEMENT = "ANNOUNCEMENT";
    /**
     * 本期选课上限
     */
    public static final String SELECT_LIMIT = "SELECT_LIMIT";

    /**
     * 站点开关 true=开 false=关
     */
    public static final String SITE_OPEN = "SITE_OPEN";

    /**
     * 设置名集合
     */
    public static final List<String> SETTING_NAME = Arrays.asList(ANNOUNCEMENT, SELECT_LIMIT, SITE_OPEN);
}
