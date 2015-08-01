package com.ds.testtask.scraperengine;

import com.ds.testtask.config.ScraperConfig;

import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by ds on 01/08/15.
 */
public class EngineImpl implements Engine {
    private static Logger log = Logger.getLogger(EngineImpl.class.getName());

    //pattern for extract text between tags
    private static Pattern pattern = Pattern.compile("<([^ ]+)[^>]*>([^<]+?)<\\/\\1>");

    @Override
    public void process(ScraperConfig config) {
        URL url;
        File f = new File(config.getPath());
        System.out.println("Entering path: " + f);
        if (f.exists()) {
            try {
                List<String> allLinesFromFile = Files.readAllLines(Paths.get(config.getPath()));
                System.out.println("Found next URL's: " + Arrays.toString(allLinesFromFile.toArray()));
                for (String line : allLinesFromFile) {
                    processUrl(new URL(line), config);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                url = new URL(config.getPath());
                processUrl(url, config);
            } catch (MalformedURLException e) {
                log.severe(e.getLocalizedMessage());
            }

        }
    }

    void processUrl(URL url, ScraperConfig config) {
        System.out.println("Processing " + url.toString());
        System.out.println("------------");

        long startTime = 0;

        if (config.isVerbosity()) {
            startTime = System.currentTimeMillis();
        }
        List<String> textArray = getTextFromHtml(url);
        //System.out.println(Arrays.toString(textArray.toArray()));
        if (config.isVerbosity()) {
            System.out.println("===Spend on data scraping = " + (System.currentTimeMillis() - startTime)/1000. + "ms.");
            startTime = System.currentTimeMillis();
        }
        findWordsInText(textArray, config);
        if (config.isVerbosity()) {
            System.out.println("===Spend on data processing = " + (System.currentTimeMillis() - startTime)/1000. + "ms.");
        }
    }

    private void findWordsInText(List<String> textArray, ScraperConfig config) {



        if (config.isCountWords()) {
            Map<String, Integer> wordsMap = new TreeMap<>();
            for (String word : config.getWords()) {
                wordsMap.put(word, 0);
            }
            textArray.stream()
                    .flatMap((s) -> Arrays.stream(s.split("\\s+")))
                    .filter((s) ->  config.getWords().contains(s))
                    .forEach((s) -> wordsMap.put(s, wordsMap.get(s) + 1));
            System.out.println("Words:");
            for(Map.Entry e : wordsMap.entrySet()) {
                System.out.println(e.getKey() + " = " + e.getValue());
            }
        }

        if (config.isExtractSentences()) {
            System.out.println("Printing sentences contains given words:");

            textArray.stream()
                    .forEach((s) -> {
                        for (String word : config.getWords()) {
                            if (s.contains(word)) {
                                System.out.println(s);
                                break;
                            }
                        }
                    });
        }

        if (config.isCountCharacters()) {
            long characters =
                    textArray.stream()
                            .flatMapToInt((s) -> s.replaceAll("\\s+", "").chars())
                            .count();
            System.out.println("Characters on web page = " + characters);
        }

    }


    public List<String> getTextFromHtml(URL url) {
        String inputLine = null;

        HttpURLConnection httpConn = null;
        ArrayList<String> result = new ArrayList<>();

        try {
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setRequestProperty("Accept-Charset", "UTF-8");

        } catch (IOException e) {
            log.severe(e.getLocalizedMessage());
        }
        if (httpConn != null) {
            try (BufferedReader in = new BufferedReader(
                   new InputStreamReader(httpConn.getInputStream(), "UTF-8"))) {
                inputLine = in.lines().collect(Collectors.joining());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (inputLine != null) {
            Matcher m = pattern.matcher(inputLine);
            while(m.find()) {
                if (!"script".equalsIgnoreCase(m.group(1))) {
                    result.add(m.group(2).replaceAll("&nbsp;", " "));
                }

            }
        }

        return result;
    }


}
