package ru.rekklez.model;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;

public class Statistic {

    private final LongSummaryStatistics intStats = new LongSummaryStatistics();
    private final DoubleSummaryStatistics floatStats = new DoubleSummaryStatistics();
    private final IntSummaryStatistics stringStats = new IntSummaryStatistics();

    public void addInt(long value) {
        intStats.accept(value);
    }

    public void addFloat(double value) {
        floatStats.accept(value);
    }

    public void addString(int length) {
        stringStats.accept(length);
    }

    public LongSummaryStatistics getIntStats() {
        return intStats;
    }

    public DoubleSummaryStatistics getFloatStats() {
        return floatStats;
    }

    public IntSummaryStatistics getStringStats() {
        return stringStats;
    }

    public boolean hasInts() {
        return intStats.getCount() > 0;
    }

    public boolean hasFloats() {
        return floatStats.getCount() > 0;
    }

    public boolean hasStrings() {
        return stringStats.getCount() > 0;
    }

}
