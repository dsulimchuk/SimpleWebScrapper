package com.ds.testtask.config;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by ds on 01/08/15.
 */
public class ConfigEngineTest {

    @org.junit.Test
    public void testConfig1() throws Exception {
        String test = "http://www.cnn.com Greece,default -v -w -c -e";
        MainConfig conf = MainConfig.builder().build();
        conf = ConfigEngine.getConfig(conf, test);

        MainConfig expected = MainConfig.builder()
                .setPath("http://www.cnn.com")
                .setWords(new HashSet<>(Arrays.asList("Greece", "default")))
                .setVerbosity(true)
                .setCountWords(true)
                .setCountCharacters(true)
                .setExtractSentences(true)
                .build();

        assertEquals(expected, conf);

    }


    @org.junit.Test
    public void testConfig2() throws Exception {
        String test = "http://www.cnn.com Greece,default -v -w -e";
        MainConfig conf = MainConfig.builder().build();
        conf = ConfigEngine.getConfig(conf, test);

        MainConfig expected = MainConfig.builder()
                .setPath("http://www.cnn.com")
                .setWords(new HashSet<>(Arrays.asList("Greece", "default")))
                .setVerbosity(true)
                .setCountWords(true)
                .setCountCharacters(false)
                .setExtractSentences(true)
                .build();

        assertEquals(expected, conf);

    }

    @org.junit.Test
    public void testConfig3() throws Exception {
        String test = "http://www.cnn.com Greece,default,test1,test2 -v -w -e";
        MainConfig conf = MainConfig.builder().build();
        conf = ConfigEngine.getConfig(conf, test);

        MainConfig expected = MainConfig.builder()
                .setPath("http://www.cnn.com")
                .setWords(new HashSet<>(Arrays.asList("Greece", "default", "test1", "test2")))
                .setVerbosity(true)
                .setCountWords(true)
                .setCountCharacters(false)
                .setExtractSentences(true)
                .build();

        assertEquals(expected, conf);

    }
}