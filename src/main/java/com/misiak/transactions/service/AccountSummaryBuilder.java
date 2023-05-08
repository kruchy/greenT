package com.misiak.transactions.service;

import com.misiak.transactions.model.AccountSummary;

import java.math.BigDecimal;

class AccountSummaryBuilder {
    private final String account;
    private int debitCount;
    private int creditCount;
    private BigDecimal balance;

    public AccountSummaryBuilder(String account) {
        this.account = account;
        this.debitCount = 0;
        this.creditCount = 0;
        this.balance = BigDecimal.ZERO;
    }

    public void debit(BigDecimal amount) {
        debitCount++;
        balance = balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        creditCount++;
        balance = balance.add(amount);
    }

    public AccountSummary build() {
        return new AccountSummary(account, debitCount, creditCount, balance);
    }
}