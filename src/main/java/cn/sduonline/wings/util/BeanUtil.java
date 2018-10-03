package cn.sduonline.wings.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by imaxct on 18-10-3.
 */
public class BeanUtil {
    private static final Logger log = LoggerFactory.getLogger(BeanUtil.class);

    public static <T> T parseObject(T dbObject, T object, Class<? extends T> clazz) {

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            for (PropertyDescriptor descriptor : beanInfo.getPropertyDescriptors()) {
                if (null == descriptor.getReadMethod().invoke(dbObject)) {
                    descriptor.getWriteMethod().invoke(dbObject, descriptor.getReadMethod().invoke(object));
                }
            }
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException e) {
            log.info("转换实体失败", "{0} {1} {2}", clazz.getName(), dbObject, object);
            log.error("转换实体失败", e);
            throw new IllegalArgumentException("保存失败");
        }
        return dbObject;
    }
}
