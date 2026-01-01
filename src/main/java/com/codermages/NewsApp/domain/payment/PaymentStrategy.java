package com.codermages.NewsApp.domain.payment;

import java.math.BigDecimal;

public interface PaymentStrategy {
    public boolean pay(BigDecimal amount);
}
