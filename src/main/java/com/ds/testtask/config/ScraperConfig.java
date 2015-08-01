package com.ds.testtask.config;

import java.util.Set;

/**
 * Created by ds on 01/08/15.
 */
public class ScraperConfig {

    private String path;

    private Set<String> words;

    @OptionalParam("-v")
    private boolean verbosity;

    @OptionalParam("-w")
    private boolean countWords;

    @OptionalParam("-c")
    private boolean countCharacters;

    @OptionalParam("-e")
    private boolean extractSentences;

    public ScraperConfig() {
    }

    /** Main constructor
     * @param path Path to web resource or text file
     * @param words Words to find
     */
    public ScraperConfig(String path, Set<String> words) {
        this.path = path;
        this.words = words;
    }

    public static MainConfigBuilder builder() {
        return new MainConfigBuilder();
    }

    //Builder
    public static class MainConfigBuilder {
        private String path;
        private Set<String> words;
        private boolean verbosity;
        private boolean countWords;
        private boolean countCharacters;
        private boolean extractSentences;

        public MainConfigBuilder setPath(String path) {
            this.path = path;
            return this;
        }

        public MainConfigBuilder setWords(Set<String> words) {
            this.words = words;
            return this;
        }

        public MainConfigBuilder setVerbosity(boolean verbosity) {
            this.verbosity = verbosity;
            return this;
        }

        public MainConfigBuilder setCountWords(boolean countWords) {
            this.countWords = countWords;
            return this;
        }

        public MainConfigBuilder setCountCharacters(boolean countCharacters) {
            this.countCharacters = countCharacters;
            return this;
        }

        public MainConfigBuilder setExtractSentences(boolean extractSentences) {
            this.extractSentences = extractSentences;
            return this;
        }

        public ScraperConfig build() {
            ScraperConfig conf = new ScraperConfig(path, words);
            conf.setVerbosity(verbosity);
            conf.setCountWords(countWords);
            conf.setCountCharacters(countCharacters);
            conf.setExtractSentences(extractSentences);
            return conf;
        }
    }

    //Getters
    public String getPath() {
        return path;
    }

    public Set<String> getWords() {
        return words;
    }

    public boolean isVerbosity() {
        return verbosity;
    }

    public boolean isCountWords() {
        return countWords;
    }

    public boolean isCountCharacters() {
        return countCharacters;
    }

    public boolean isExtractSentences() {
        return extractSentences;
    }

    //Setters


    public void setPath(String path) {
        this.path = path;
    }

    public void setWords(Set<String> words) {
        this.words = words;
    }

    public void setVerbosity(boolean verbosity) {
        this.verbosity = verbosity;
    }

    public void setCountWords(boolean countWords) {
        this.countWords = countWords;
    }

    public void setCountCharacters(boolean countCharacters) {
        this.countCharacters = countCharacters;
    }

    public void setExtractSentences(boolean extractSentences) {
        this.extractSentences = extractSentences;
    }

    @Override
    public String toString() {
        return "ScraperConfig{" +
                "path='" + path + '\'' +
                ", words=" + words +
                ", verbosity=" + verbosity +
                ", countWords=" + countWords +
                ", countCharacters=" + countCharacters +
                ", extractSentences=" + extractSentences +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScraperConfig config = (ScraperConfig) o;

        if (verbosity != config.verbosity) return false;
        if (countWords != config.countWords) return false;
        if (countCharacters != config.countCharacters) return false;
        if (extractSentences != config.extractSentences) return false;
        if (path != null ? !path.equals(config.path) : config.path != null) return false;
        return !(words != null ? !words.equals(config.words) : config.words != null);

    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (words != null ? words.hashCode() : 0);
        result = 31 * result + (verbosity ? 1 : 0);
        result = 31 * result + (countWords ? 1 : 0);
        result = 31 * result + (countCharacters ? 1 : 0);
        result = 31 * result + (extractSentences ? 1 : 0);
        return result;
    }
}
