package ru.rekklez.model;

public enum Option {
    SHORT_DESC("-s", false),
    FULL_DESC("-f", false),
    APPEND("-a", false),
    PREFIX("-p", true),
    OUTPUT("-o", true);

    private final String name;
    private final boolean hasValue;

    Option(String name, boolean hasValue) {
        this.name = name;
        this.hasValue = hasValue;
    }

    public boolean isHasValue() {
        return hasValue;
    }

    public static Option getByName(String name) {
        for (Option option : Option.values()) {
            if (option.name.equals(name)) {
                return option;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Option{" +
                "name='" + name + '\'' +
                ", hasValue=" + hasValue +
                '}';
    }
}