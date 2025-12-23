package ru.rekklez.model;

import ru.rekklez.util.DuplicateDescriptionOptionException;

public class Options {

    private String resultPath;
    private String prefix;
    private boolean appendable;
    private boolean shortDesc;
    private boolean fullDesc;

    public Options() {
    }

    /*public Options(Builder builder) {
        this.resultPath = builder.resultPath;
        this.prefix = builder.prefix;
        this.appendable = builder.appendable;
        this.shortDesc = builder.shortDesc;
        this.fullDesc = builder.fullDesc;
    }*/

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
        if(shortDesc) throw new DuplicateDescriptionOptionException("Duplicate description option. Description is already set to 'short'");
        this.fullDesc = fullDesc;
    }

    /*public static class Builder {
        private String resultPath;
        private String prefix;
        private boolean appendable;
        private boolean shortDesc;
        private boolean fullDesc;

        public Builder resultPath(String resultPath) {
            this.resultPath = resultPath;
            return this;
        }

        public Builder prefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public Builder appendable(boolean appendable) {
            this.appendable = appendable;
            return this;
        }

        public Builder shortDesc(boolean shortDesc) throws DescriptionException {
            if(fullDesc) throw new DescriptionException("Description is already set to 'full'");
            this.shortDesc = shortDesc;
            return this;
        }

        public Builder fullDesc(boolean fullDesc) throws DescriptionException {
            if(shortDesc) throw new DescriptionException("Description is already set to 'short'");
            this.fullDesc = fullDesc;
            return this;
        }

        public Options build() {
            return new Options(this);
        }
    }*/

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
