package ru.rekklez.model;

import java.util.Arrays;

public class ParsedArguments {

    private String[] files;
    private Options options;

    public ParsedArguments() {
    }

    public ParsedArguments(String[] files, Options options) {
        this.files = files;
        this.options = options;
    }

    public String[] getFiles() {
        return files;
    }

    public void setFiles(String[] files) {
        this.files = files;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "ParsedArguments{" +
                "files=" + Arrays.toString(files) +
                ", " + options +
                '}';
    }
}

