package com.codermages.NewsApp.domain.payment;

import java.math.BigDecimal;

public class CreditCardStrategy implements PaymentStrategy {
    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("Credit card payment " + amount + " done");
        return true;
    }
}
