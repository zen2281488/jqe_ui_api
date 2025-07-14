package util;
import io.qameta.allure.Allure;
import pojo.Customers;
import pojo.Transaction;

import java.io.*;
import java.util.List;
import java.util.function.Function;

public class WriteUtils {
    private static <T> void writeToCSV(List<T> items, String fileName, String header, Function<T, String> mapper) {
        StringBuilder builder = new StringBuilder();
        builder.append(header).append("\n");
        for (T item : items) {
            builder.append(mapper.apply(item)).append("\n");
        }
        Allure.addAttachment(fileName, "text/csv",
                new ByteArrayInputStream(builder.toString().getBytes()), ".csv");
    }


    public static void writeTransactionsToCSV(List<Transaction> transactions) {
        writeToCSV(transactions,
                "transactions.csv",
                "Date,Amount,Transaction Type",
                transaction -> transaction.getDate() + "," + transaction.getAmount() + "," + transaction.getTransactionType());
    }

    public static void writeCustomersToCSV(List<Customers> customers) {
        writeToCSV(customers,
                "customers.csv",
                "First Name,Last Name,Post Code,Account Number",
                customer -> customer.getFirstName() + "," + customer.getLastName() + "," + customer.getPostCode() + "," + customer.getAccountNumber());
    }
}
