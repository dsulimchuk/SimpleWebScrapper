package com.ds.testtask.scraperengine;

import com.ds.testtask.config.ScraperConfigEngine;
import com.ds.testtask.config.ScraperConfig;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ds on 01/08/15.
 */
public class EngineImplTest {

    private ScraperConfig conf;
    @Before
    public void setUp() throws Exception {
        String[] test = {"http://www.cnn.com",  "Greece,dies,plane,die",  "-v",  "-w",  "-c",  "-e"};
        conf = ScraperConfigEngine.getConfig(new ScraperConfig(), test);
    }

    @Test
    public void testProcessWeb() throws Exception {
        EngineImpl engine = new EngineImpl();
        engine.process(conf);
    }

    @Test
    public void testProcessTextFile() throws Exception {
        EngineImpl engine = new EngineImpl();
        conf.setPath("target/test-classes/web.txt");
        System.out.println(new File(".").getAbsolutePath());
        engine.process(conf);
    }

    @Test
    public void testGetTextFromHtml() throws Exception {
        EngineImpl engine = new EngineImpl();
        List<String> actual = engine.getTextFromHtml(new URL("http://www.afisha.ru/"));
        //System.out.println(Arrays.toString(actual.toArray()));
        assertTrue(actual.size() > 10);

    }


}