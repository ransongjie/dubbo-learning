package com.xcrj.framework.register;

import java.util.HashMap;
import java.util.Map;

public class LocalRegister {
    private static Map<String, Class> map = new HashMap<>();

    public static void regist(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    //多版本
    public static void regist(String interfaceName, Class implClass, Integer num) {
        map.put(interfaceName + num, implClass);
    }

    public static Class get(String interfaceName) {
        return map.get(interfaceName);
    }

    //多版本
    public static Class get(String interfaceName, Integer num) {
        return map.get(interfaceName + num);
    }
}
