package ru.rekklez.model;

import java.util.DoubleSummaryStatistics;
import java.util.IntSummaryStatistics;
import java.util.LongSummaryStatistics;

public class StatisticPrinter {
    private final boolean isShortDesc;
    private final boolean isFullDesc;

    public StatisticPrinter(boolean isShortDesc, boolean isFullDesc) {
        this.isFullDesc = isFullDesc;
        this.isShortDesc = isShortDesc;
    }

    public void printStatistic(Statistic statistic) {
        if (isShortDesc || isFullDesc) {
            if(statistic.hasInts()) {
                System.out.println("Статистика для записанных целочисленных значений:");
                LongSummaryStatistics intStats = statistic.getIntStats();
                printNumericStats(intStats.getCount(), intStats.getMin(), intStats.getMax(), intStats.getSum(), intStats.getAverage());
            }

            if(statistic.hasFloats()) {
                System.out.println("Статистика для записанных чисел с плавающей точкой:");
                DoubleSummaryStatistics floatStats = statistic.getFloatStats();
                printNumericStats(floatStats.getCount(), floatStats.getMin(), floatStats.getMax(), floatStats.getSum(), floatStats.getAverage());
            }

            if(statistic.hasStrings()) {
                System.out.println("Статистика для записанных строк:");
                IntSummaryStatistics stringStats = statistic.getStringStats();
                printStringStats(stringStats.getCount(), stringStats.getMin(), stringStats.getMax());
            }
        }
    }

    private void printNumericStats(long count, double min, double max, double sum, double avg) {
        System.out.printf("\tКоличество элементов: %d\n", count);
        if (isFullDesc) {
            System.out.printf("\tМинимальное число: %s\n", min);
            System.out.printf("\tМаксимальное число: %s\n", max);
            System.out.printf("\tСумма всех значений: %s\n", sum);
            System.out.printf("\tСреднее значение: %f\n\n", avg);
        }
    }

    private void printStringStats(long count, int min, int max) {
        System.out.printf("\tКоличество элементов: %d\n", count);
        if (isFullDesc) {
            System.out.printf("\tДлина самой короткой строки: %d\n", min);
            System.out.printf("\tДлина самой длинной строки: %d\n", max);
        }
    }
}
