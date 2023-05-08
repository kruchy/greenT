package com.misiak.transactions.service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.misiak.transactions.model.AccountSummary;
import com.misiak.transactions.model.Transaction;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionProcessorTest {

    @Test
    public void testProcessTransactions() throws IOException {
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction("32309111922661937852684864", "06105023389842834748547303", new BigDecimal("10.90")));
        transactions.add(new Transaction("31074318698137062235845814", "66105036543749403346524547", new BigDecimal("200.90")));
        transactions.add(new Transaction("66105036543749403346524547", "32309111922661937852684864", new BigDecimal("50.10")));

        TransactionProcessor processor = new TransactionProcessor();
        List<AccountSummary> accountSummaries = processor.processTransactions(transactions);

        List<AccountSummary> expectedAccountSummaries = new ArrayList<>();
        expectedAccountSummaries.add(new AccountSummary("06105023389842834748547303", 0, 1, new BigDecimal("10.90")));
        expectedAccountSummaries.add(new AccountSummary("31074318698137062235845814", 1, 0, new BigDecimal("-200.90")));
        expectedAccountSummaries.add(new AccountSummary("32309111922661937852684864", 1, 1, new BigDecimal("39.20")));
        expectedAccountSummaries.add(new AccountSummary("66105036543749403346524547", 1, 1, new BigDecimal("150.80")));
        assertEquals(expectedAccountSummaries, accountSummaries);
    }
}
