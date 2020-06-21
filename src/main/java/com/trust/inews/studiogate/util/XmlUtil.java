package com.trust.inews.studiogate.util;

import javax.xml.bind.JAXB;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * xml文件与java对象互转
 */
public class XmlUtil {
    /**
     * xml字符串转java对象
     * @param xml xml字符串
     * @param clz 转换后类型
     * @param <T> 转换后类型
     * @return 返回该类型的对象
     */
    public static <T> T xmlToBean(String xml, Class<T> clz) {
        return JAXB.unmarshal(new StringReader(xml.trim()), clz);
    }

    /**
     * java对象转xml字符串
     * @param object java对象
     * @return xml字符串
     */
    public static String beanToXml(Object object) {
        StringWriter xml = new StringWriter();
        JAXB.marshal(object, xml);
        return xml.toString();
    }
}
