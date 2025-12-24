package ru.rekklez.model;

import ru.rekklez.util.DuplicateDescriptionOptionException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Options {

    private String resultPath = "";
    private String prefix = "";
    private boolean appendable;
    private boolean shortDesc;
    private boolean fullDesc;

    public Options() {
    }

    public String getResultPath() {
        return resultPath;
    }

    public String getPrefix() {
        return prefix;
    }

    public boolean isAppendable() {
        return appendable;
    }

    public boolean isShortDesc() {
        return shortDesc;
    }

    public boolean isFullDesc() {
        return fullDesc;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
        try {
            Files.createDirectories(Paths.get(resultPath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setAppendable(boolean appendable) {
        this.appendable = appendable;
    }

    public void setShortDesc(boolean shortDesc) throws DuplicateDescriptionOptionException {
        if(this.fullDesc) throw new DuplicateDescriptionOptionException("Duplicate description option. Description is already set to 'full'");
        this.shortDesc = shortDesc;
    }

    public void setFullDesc(boolean fullDesc) throws DuplicateDescriptionOptionException {
        if(this.shortDesc) throw new DuplicateDescriptionOptionException("Duplicate description option. Description is already set to 'short'");
        this.fullDesc = fullDesc;
    }

    @Override
    public String toString() {
        return "Options{" +
                "resultPath='" + resultPath + '\'' +
                ", prefix='" + prefix + '\'' +
                ", appendable=" + appendable +
                ", shortDesc=" + shortDesc +
                ", fullDesc=" + fullDesc +
                '}';
    }
}
