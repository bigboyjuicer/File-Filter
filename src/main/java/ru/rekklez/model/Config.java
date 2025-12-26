package ru.rekklez.model;

import ru.rekklez.util.exception.DuplicateDescriptionOptionException;
import ru.rekklez.util.exception.DuplicateOptionException;
import ru.rekklez.util.exception.InvalidValueException;

import java.util.ArrayList;
import java.util.List;

public class Config {

    private String output = "";
    private String prefix = "";
    private boolean appendable;
    private boolean shortDesc;
    private boolean fullDesc;
    private final List<String> files = new ArrayList<>();

    public void setFile(String name) {
        files.add(name);
    }

    public void setOutput(String output) throws InvalidValueException, DuplicateOptionException {
        if(!this.output.isEmpty()) throw new DuplicateOptionException("Duplicate option: -o");
        verifyValue(output);
        this.output = output;
    }

    public void setPrefix(String prefix) throws InvalidValueException, DuplicateOptionException {
        if(!this.prefix.isEmpty()) throw new DuplicateOptionException("Duplicate option: -p");
        verifyValue(prefix);
        this.prefix = prefix;
    }

    public void setAppendable(boolean appendable) throws DuplicateOptionException {
        if(this.appendable == appendable) throw new DuplicateOptionException("Duplicate option: -a");
        this.appendable = appendable;
    }

    public void setShortDesc(boolean shortDesc) throws DuplicateDescriptionOptionException, DuplicateOptionException {
        if(this.shortDesc) throw new DuplicateOptionException("Duplicate option: -s");
        if(this.fullDesc) throw new DuplicateDescriptionOptionException("Duplicate description option. Description is already set to -f");
        this.shortDesc = shortDesc;
    }

    public void setFullDesc(boolean fullDesc) throws DuplicateDescriptionOptionException, DuplicateOptionException {
        if(this.fullDesc) throw new DuplicateOptionException("Duplicate option: -f");
        if(this.shortDesc) throw new DuplicateDescriptionOptionException("Duplicate description option. Description is already set to -s");
        this.fullDesc = fullDesc;
    }

    private void verifyValue(String value) throws InvalidValueException {
        Option option = Option.getByName(value);
        if (option != null) throw new InvalidValueException("Value cannot be equal to the option name");
    }

    public String getOutput() {
        return output;
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

    public List<String> getFiles() {
        return files;
    }

    @Override
    public String toString() {
        return "Config{" +
                "resultPath='" + output + '\'' +
                ", prefix='" + prefix + '\'' +
                ", appendable=" + appendable +
                ", shortDesc=" + shortDesc +
                ", fullDesc=" + fullDesc +
                ", files=" + files +
                '}';
    }
}
