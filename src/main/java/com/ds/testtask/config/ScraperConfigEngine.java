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
    public static ScraperConfig getConfig(ScraperConfig config, String args) {
        Scanner scanner = new Scanner(args).useDelimiter("\\s+");
        String path = scanner.next();
        String words = scanner.next();
        String flagsString = scanner.nextLine();

        config.setPath(path);
        config.setWords(new HashSet<>(Arrays.asList(words.split(","))));


        Set<String> flags = new HashSet<>();
        Pattern flagsPattern = Pattern.compile("(\\-\\w+)");
        Matcher m = flagsPattern.matcher(flagsString);
        while(m.find()) {
            flags.add(m.group());
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
