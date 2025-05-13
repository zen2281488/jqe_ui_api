package Util;
import io.qameta.allure.Allure;
import pojo.Customers;
import pojo.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.function.Function;

public class WriteUtils {
    private static <T> void writeToCSV(List<T> items, String fileName, String header, Function<T, String> mapper) {
        String filePath;
        try {
            File directory = new File("output");
            if (!directory.exists()) {
                directory.mkdir();
            }

            filePath = directory.getAbsolutePath() + File.separator + fileName;

            try (FileWriter writer = new FileWriter(filePath)) {
                writer.append(header).append("\n");
                for (T item : items) {
                    writer.append(mapper.apply(item)).append("\n");
                }
            }

            try (InputStream is = new FileInputStream(filePath)) {
                Allure.addAttachment(fileName, "text/csv", is, ".csv");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
