package com.ds.testtask;

import com.ds.testtask.config.ScraperConfig;
import com.ds.testtask.config.ScraperConfigEngine;
import com.ds.testtask.scraperengine.EngineImpl;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        if (args.length < 3) {
            System.out.println("Usage: java –jar scraper.jar http://www.cnet.com/news/ BBC,Windows -v -w -c -e");
            System.exit(0);
        }
        ScraperConfig conf = ScraperConfigEngine.getConfig(new ScraperConfig(), args);
        EngineImpl engine = new EngineImpl();
        engine.process(conf);
    }
}
