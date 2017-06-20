package com.common.util;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by weber on 2017/4/10.
 * <p>
 * 配置文件读取工具类
 */
public class Configs {
    public static void main(String[] args) {
        String s = Configs.getConfig("product_name");
        System.out.println(s);
    }

    private static Logger log = LoggerFactory.getLogger(Configs.class);

    private static String[] config_property = new String[] { "common-config.properties", "config.properties"};

    private static String config_xml = "config/constant.xml";

    public static Map<String, Object> configs = new LinkedHashMap<String, Object>();

    public static Map<String, Object> full_configs = new LinkedHashMap<String, Object>();

    public static Boolean isDebug = false;

    static {
        try {
            for (String cfg : config_property) {
                Properties p = loadProperties(cfg, false, false);
                readPropertiesConfig(p);
            }
            readXmlConfigs();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 对特别字段进行替换处理
        for (Iterator<String> it = configs.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            Object v = configs.get(key);
            if (!(v instanceof String)) {
                continue;
            }
            String str = v.toString().trim();
            int index = -1;
            while ((index = str.indexOf("{", index + 1)) != -1) {
                int tmp = str.indexOf("}", index);
                if (tmp == -1) {
                    continue;
                }
                String var = str.substring(index + 1, tmp);

                if (var.matches("\\d+")) {
                    continue;
                }
                if (configs.get(var) != null) {
                    str = str.replace("{" + var + "}", configs.get(var).toString());
                }
            }
            configs.put(key, str);
            full_configs.put(key, str);
        }

        isDebug = Boolean.valueOf(full_configs.get("is_debug").toString());

        log.info("config配置项列表");
        for (Iterator<String> it = full_configs.keySet().iterator(); it.hasNext();) {
            String key = it.next();
            log.info(key + "=" + full_configs.get(key));
        }

    }

    private static void readPropertiesConfig(Properties p) {
        for (Map.Entry<Object, Object> entry : p.entrySet()) {
            String valur = entry.getValue().toString().trim();
            String key = entry.getKey().toString().trim();

            configs.put(key, valur);
            full_configs.put(key, valur);
        }
    }

    private static void readXmlConfigs() throws Exception {
        SAXReader saxReader = new SAXReader();
        InputStream in = loadResource(config_xml, false, false);

        InputStreamReader isr = new InputStreamReader(in, "utf-8");
        Document docs = saxReader.read(isr);
        Element root = docs.getRootElement();
        for (Iterator<Element> it = root.elementIterator(); it.hasNext();) {
            Element cfg = it.next();
            String initToPage = cfg.attributeValue("initToPage");
            String key = cfg.attributeValue("id");

            full_configs.put(key, cfg.getTextTrim());
            if ("true".equals(initToPage)) {
                configs.put(key, cfg.getTextTrim());
            }
        }
    }

    /**
     * 获取配置项的值
     *
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public static String getConfig(String key) {
        Object v = full_configs.get(key);
        return v == null ? null : String.valueOf(v);
    }

    /**
     * properties配置文件
     *
     * @param fileName 文件路径，不能以 / 开头
     * @param allowMultiFile 是否允许存在多个同名文件
     * @param optional 文件是否可选
     * @return
     */
    public static Properties loadProperties(String fileName, boolean allowMultiFile, boolean optional) {
        Properties properties = new Properties();
        if (fileName.startsWith("/")) {
            try {
                FileInputStream input = new FileInputStream(fileName);
                try {
                    properties.load(input);
                } finally {
                    input.close();
                }
            } catch (Throwable e) {
                log.error("Failed to load " + fileName + " file from " + fileName + "(ingore this file): " + e.getMessage());
            }
            return properties;
        }

        List<java.net.URL> list = new ArrayList<java.net.URL>();
        try {
            Enumeration<java.net.URL> urls = ClassHelper.getClassLoader().getResources(fileName);
            list = new ArrayList<java.net.URL>();
            while (urls.hasMoreElements()) {
                list.add(urls.nextElement());
            }
        } catch (Throwable t) {
            log.error("Fail to load " + fileName + " file: " + t.getMessage(), t);
        }

        if (list.size() == 0) {
            if (!optional) {
                log.error("No " + fileName + " found on the class path.");
            }
            return properties;
        }

        if (!allowMultiFile) {
            if (list.size() > 1) {
                String errMsg = String.format("only 1 %s file is expected, but %d  files found on class path: %s", fileName, list.size(), list.toString());
                log.error(errMsg);
            }

            try {
                properties.load(ClassHelper.getClassLoader().getResourceAsStream(fileName));
            } catch (Throwable e) {
                log.error("Failed to load " + fileName + " file from " + fileName + "(ingore this file): " + e.getMessage());
            }
            return properties;
        }

        log.info("load " + fileName + " properties file from " + list);

        for (java.net.URL url : list) {
            try {
                Properties p = new Properties();
                InputStream input = url.openStream();
                if (input != null) {
                    try {
                        p.load(input);
                        properties.putAll(p);
                    } finally {
                        try {
                            input.close();
                        } catch (Throwable t) {
                        }
                    }
                }
            } catch (Throwable e) {
                log.error("Fail to load " + fileName + " file from " + url + "(ingore this file): " + e.getMessage());
            }
        }

        return properties;
    }

    /**
     * 文件对应的输入流
     *
     * @param fileName 文件路径，不能以 / 开头
     * @param allowMultiFile 是否允许存在多个同名文件
     * @param optional 文件是否可选
     * @return
     */
    public static InputStream loadResource(String fileName, boolean allowMultiFile, boolean optional) {
        InputStream input = null;
        if (fileName.startsWith("/")) {
            try {
                input = new FileInputStream(fileName);
                return input;
            } catch (Throwable e) {
                // log.debug("Failed to load " + fileName + " file from " + fileName + "(ingore this file): " + e.getMessage());
            }

        }

        List<java.net.URL> list = new ArrayList<java.net.URL>();
        try {
            Enumeration<java.net.URL> urls = ClassHelper.getClassLoader().getResources(fileName);
            list = new ArrayList<java.net.URL>();
            while (urls.hasMoreElements()) {
                list.add(urls.nextElement());
            }
        } catch (Throwable t) {
            log.debug("Fail to load " + fileName + " file: " + t.getMessage(), t);
        }

        if (list.size() == 0) {
            if (!optional) {
                log.error("No " + fileName + " found on the class path.");
            }
            return input;
        }

        if (!allowMultiFile) {
            if (list.size() > 1) {
                String errMsg = String.format("only 1 %s file is expected, but %d  files found on class path: %s", fileName, list.size(), list.toString());
                log.debug(errMsg);
            }

            return ClassHelper.getClassLoader().getResourceAsStream(fileName);
        }

        for (java.net.URL url : list) {
            try {
                return url.openStream();
            } catch (Throwable e) {
                log.debug("Fail to load " + fileName + " file from " + url + "(ingore this file): " + e.getMessage());
            }
        }
        if (input == null) {
            throw new RuntimeException(fileName + "不存在");
        }
        return input;
    }
}
