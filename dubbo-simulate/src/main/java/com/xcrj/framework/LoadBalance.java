package com.xcrj.framework;

import java.util.List;
import java.util.Random;

public class LoadBalance {
    public static URL random(List<URL> list) {
        Random random = new Random();
        int i = random.nextInt(list.size());
        return list.get(i);
    }
}
