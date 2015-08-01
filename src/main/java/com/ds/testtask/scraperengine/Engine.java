package com.ds.testtask.scraperengine;

import com.ds.testtask.config.ScraperConfig;

import java.net.URL;
import java.util.Set;

/**
 * Created by ds on 01/08/15.
 */
public interface Engine {
    void process(ScraperConfig config);
}
