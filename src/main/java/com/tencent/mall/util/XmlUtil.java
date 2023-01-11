package com.tencent.mall.util;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Xml工具类
 *
 * @author Jarry
 */
public class XmlUtil {

    /**
     * 生成xml
     *
     * @return
     */
    public static String genXml(Map<String, Object> map) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        map.keySet().forEach(k -> {
            Object value = map.get(k);
            sb.append("<").append(k).append(">").append(value).append("</").append(k).append(">");
        });
        sb.append("</xml>");
        return new String(sb.toString().getBytes(), "ISO8859-1");
    }

    /**
     * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
     *
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public static Map<String, String> doXMLParse(String strxml) throws JDOMException, IOException {
        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if ("".equals(strxml)) {
            return Collections.emptyMap();
        }

        Map<String, String> m = new HashMap<>();

        InputStream in = new ByteArrayInputStream(strxml.getBytes(StandardCharsets.UTF_8));
        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(in);
        Element root = doc.getRootElement();
        List list = root.getChildren();
        for (Object o : list) {
            Element e = (Element) o;
            String k = e.getName();
            String v = "";
            List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = XmlUtil.getChildrenText(children);
            }

            m.put(k, v);
        }

        //关闭流    
        in.close();

        return m;
    }

    /**
     * 获取子结点的xml
     *
     * @param children
     * @return String
     */
    public static String getChildrenText(List children) {
        StringBuilder sb = new StringBuilder();
        if (!children.isEmpty()) {
            for (Object child : children) {
                Element e = (Element) child;
                String name = e.getName();
                String value = e.getTextNormalize();
                List list = e.getChildren();
                sb.append("<").append(name).append(">");
                if (!list.isEmpty()) {
                    sb.append(XmlUtil.getChildrenText(list));
                }
                sb.append(value);
                sb.append("</").append(name).append(">");
            }
        }

        return sb.toString();
    }
}
