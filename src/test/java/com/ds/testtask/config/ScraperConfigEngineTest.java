package com.ds.testtask.config;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by ds on 01/08/15.
 */
public class ScraperConfigEngineTest {

    @org.junit.Test
    public void testConfig1() throws Exception {
        String test = "http://www.cnn.com Greece,default -v -w -c -e";
        ScraperConfig conf = ScraperConfig.builder().build();
        conf = ScraperConfigEngine.getConfig(conf, test);

        ScraperConfig expected = ScraperConfig.builder()
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
        ScraperConfig conf = ScraperConfig.builder().build();
        conf = ScraperConfigEngine.getConfig(conf, test);

        ScraperConfig expected = ScraperConfig.builder()
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
        ScraperConfig conf = ScraperConfig.builder().build();
        conf = ScraperConfigEngine.getConfig(conf, test);

        ScraperConfig expected = ScraperConfig.builder()
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