package com.kruchy.transactions.model;

import java.math.BigDecimal;

public record AccountSummary(String account, int debitCount, int creditCount, BigDecimal balance) {
    public AccountSummary withDebit(BigDecimal amount) {
        return new AccountSummary(account, debitCount + 1, creditCount, balance.subtract(amount));
    }

    public AccountSummary withCredit(BigDecimal amount) {
        return new AccountSummary(account, debitCount, creditCount + 1, balance.add(amount));
    }
}