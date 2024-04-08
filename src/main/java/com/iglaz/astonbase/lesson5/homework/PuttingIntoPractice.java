package com.iglaz.astonbase.lesson5.homework;

import java.util.*;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        System.out.println("1. Найти все транзакции за 2011 год и отсортировать их по сумме (от меньшей к большей).");
        var collect = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted((transaction1, transaction2) -> transaction1.getValue() - transaction2.getValue())
                .toList();
        collect.forEach(System.out::println);

        System.out.println("2. Вывести список неповторяющихся городов, в которых работают трейдеры.");
        transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .forEach(System.out::println);

        System.out.println("3 .Найти всех трейдеров из Кембриджа и отсортировать их по именам.");

        transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .sorted(Comparator.comparing(Trader::getName))
                .forEach(System.out::println);

        System.out.println("4. Вернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке.");

        var namesTraders = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .map(Trader::getName)
                .sorted()
                .collect(Collectors.joining(","));

        System.out.println("namesTraders = " + namesTraders);

        System.out.println("5. Выяснить, существует ли хоть один трейдер из Милана.");

        var isPresent = transactions.stream()
                .map(Transaction::getTrader)
                .distinct()
                .anyMatch(trader -> trader.getCity().equals("Milan"));
        System.out.println("isPresent trader from Milan = " + isPresent);

        System.out.println("6. Вывести суммы всех транзакций трейдеров из Кембриджа.");

        var summaryValue = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.groupingBy(
                        Transaction::getTrader,
                        Collectors.summingInt(Transaction::getValue)
                ));

        System.out.println("summaryValue = " + summaryValue);

        System.out.println("7. Какова максимальная сумма среди всех транзакций?");

        var maxSummary = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.groupingBy(
                        Transaction::getTrader,
                        Collectors.summingInt(Transaction::getValue)
                )).values().stream().max(Integer::compareTo);

        System.out.println("maxSummary = " + maxSummary);

        System.out.println("8. Найти транзакцию с минимальной суммой.");

        var minSummary = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .collect(Collectors.groupingBy(
                        Transaction::getTrader,
                        Collectors.summingInt(Transaction::getValue)
                )).values().stream().min(Integer::compareTo);

        System.out.println("minSummary = " + minSummary);








    }
}
