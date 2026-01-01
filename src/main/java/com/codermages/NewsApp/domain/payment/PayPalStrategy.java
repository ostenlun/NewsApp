package com.codermages.NewsApp.domain.payment;

import java.math.BigDecimal;

public class PayPalStrategy implements PaymentStrategy {
    @Override
    public boolean pay(BigDecimal amount) {
        System.out.println("PayPal payment " + amount + " done");
        return true;
    }
}
