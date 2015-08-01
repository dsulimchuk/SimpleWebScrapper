package com.ds.testtask.config;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ds on 01/08/15.
 */
public class ScraperConfigEngine {
    public static ScraperConfig getConfig(ScraperConfig config, String[] args) {
        config.setPath(args[0]);
        config.setWords(new HashSet<>(Arrays.asList(args[1].split(","))));


        Set<String> flags = new HashSet<>();
        for(int i = 2; i < args.length; i++) {
            flags.add(args[i]);
        }

        for(Field f : config.getClass().getDeclaredFields()) {

            if (f.isAnnotationPresent(OptionalParam.class)) {
                String flag = f.getDeclaredAnnotation(OptionalParam.class).value();
                if (flags.contains(flag)) {
                    f.setAccessible(true);
                    try {
                        f.set(config, Boolean.TRUE);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
//                    try {
//                        Method method = config.getClass().getDeclaredMethod("set"+f.getName(), Boolean.class);
//                        method.invoke(config, true);
//                    } catch (NoSuchMethodException e) {
//                        e.printStackTrace();
//                    } catch (InvocationTargetException e) {
//                        e.printStackTrace();
//                    } catch (IllegalAccessException e) {
//                        e.printStackTrace();
//                    }
                }
            }
        }


        return config;
    }
}
