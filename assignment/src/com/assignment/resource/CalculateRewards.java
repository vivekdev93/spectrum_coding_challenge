package com.assignment.resource;

import com.assignment.model.Customer;
import com.assignment.model.Transaction;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Main class to read data and process rewards.
 */
public class CalculateRewards {

    private static final List<Transaction> transactionList = new ArrayList<>();


    /**
     * method to get total rewards per customer from given list of transactions.
     */
    public static void getTotalRewardsPerCustomer() {
        Map<String, Double> map = transactionList.stream().collect(Collectors.toMap(txn -> txn.getCustomer().getUserName(),
                txn -> getRewards(txn.getAmount()), Double::sum));
        System.out.println(map.toString());
    }


    /**
     * Method to get rewards per month per customer from given data
     */
    public static void getRewardsPerMonthPerCustomer() {
        Map<String, List<Transaction>> map = new HashMap<>();
        for (Transaction transaction : transactionList) {
            List<Transaction> transactions = map.getOrDefault(transaction.getCustomer().getUserName(), new ArrayList<>());
            transactions.add(transaction);
            map.putIfAbsent(transaction.getCustomer().getUserName(), transactions);
        }
        Map<String, Map<String, Double>> resultantMap = new HashMap<>();
        for (Map.Entry<String, List<Transaction>> entry : map.entrySet()) {
            List<Transaction> transactions = entry.getValue();
            Map<String, Double> rewardsMonthWise = transactions.stream().collect(Collectors.toMap(txn -> getMonth(txn.getDateOfPurchase()),
                    txn -> getRewards(txn.getAmount()), Double::sum));
            resultantMap.putIfAbsent(entry.getKey(), rewardsMonthWise);
        }

        System.out.println(resultantMap.toString());
    }


    public static void main(String[] args) {
        int index = 0;

        //sample Data (can be taken from CSV/DB etc)
        transactionList.add(new Transaction(index++, new Customer(1, "Vivek"), 100.0, new Date()));
        transactionList.add(new Transaction(index++, new Customer(2, "Carl"), 50.0, new Date()));
        transactionList.add(new Transaction(index++, new Customer(3, "MVivek"), 120.0, new Date()));
        transactionList.add(new Transaction(index++, new Customer(4, "MVivek"), 300, new Date()));
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 11, 10);
        transactionList.add(new Transaction(index, new Customer(4, "MVivek"), 300, calendar.getTime()));
        getRewardsPerMonthPerCustomer();
        getTotalRewardsPerCustomer();

    }

    private static String getMonth(Date dateOfPurchase) {
        return new SimpleDateFormat("MMM").format(dateOfPurchase);
    }

    /**
     * Method to calculate the rewards based on price.
     *
     * @param amount amount for that transaction
     * @return rewards earned by the user
     */
    private static double getRewards(double amount) {
        if (amount >= 50 && amount <= 100) {
            return amount - 50;
        } else if (amount > 100) {
            return (2 * (amount - 100) + 50);
        }
        return 0;
    }
}
