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
        if(!this.output.isEmpty()) throw new DuplicateOptionException("Повторение опции: -o. Данная опция будет пропущена.");
        verifyValue(output);
        this.output = output;
    }

    public void setPrefix(String prefix) throws InvalidValueException, DuplicateOptionException {
        if(!this.prefix.isEmpty()) throw new DuplicateOptionException("Повторение опции: -p. Данная опция будет пропущена.");
        verifyValue(prefix);
        this.prefix = prefix;
    }

    public void setAppendable(boolean appendable) throws DuplicateOptionException {
        if(this.appendable == appendable) throw new DuplicateOptionException("Повторение опции: -a. Данная опция будет пропущена.");
        this.appendable = appendable;
    }

    public void setShortDesc(boolean shortDesc) throws DuplicateDescriptionOptionException, DuplicateOptionException {
        if(this.shortDesc) throw new DuplicateOptionException("Повторение опции: -s. Данная опция будет пропущена.");
        if(this.fullDesc) throw new DuplicateDescriptionOptionException("Повторение опции статистики. Уже установлена опция -f.");
        this.shortDesc = shortDesc;
    }

    public void setFullDesc(boolean fullDesc) throws DuplicateDescriptionOptionException, DuplicateOptionException {
        if(this.fullDesc) throw new DuplicateOptionException("Повторение опции: -f. Данная опция будет пропущена.");
        if(this.shortDesc) throw new DuplicateDescriptionOptionException("Повторение опции статистики. Уже установлена опция -s.");
        this.fullDesc = fullDesc;
    }

    private void verifyValue(String value) throws InvalidValueException {
        Option option = Option.getByName(value);
        if (option != null) {
            throw new InvalidValueException("Параметр не должен быть равен имени опции.");
        }
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
