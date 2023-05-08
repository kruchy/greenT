package com.kruchy.transactions.service;

import com.kruchy.transactions.model.AccountSummary;
import com.kruchy.transactions.model.Transaction;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.TreeMap;

@Service
public class TransactionProcessor {

    public List<AccountSummary> processTransactions(List<Transaction> transactions) {
        TreeMap<String, AccountSummary> accountSummaryMap = new TreeMap<>();

        for (Transaction transaction : transactions) {
            AccountSummary debitAccountSummary = accountSummaryMap.getOrDefault(transaction.debitAccount(),
                    new AccountSummary(transaction.debitAccount(), 0, 0, BigDecimal.ZERO));
            debitAccountSummary = debitAccountSummary.withDebit(transaction.amount());

            accountSummaryMap.put(transaction.debitAccount(), debitAccountSummary);

            AccountSummary creditAccountSummary = accountSummaryMap.getOrDefault(transaction.creditAccount(),
                    new AccountSummary(transaction.creditAccount(), 0, 0, BigDecimal.ZERO));
            creditAccountSummary = creditAccountSummary.withCredit(transaction.amount());

            accountSummaryMap.put(transaction.creditAccount(), creditAccountSummary);
        }

        return accountSummaryMap.values().stream().toList();
    }

}