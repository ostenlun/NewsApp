package com.codermages.NewsApp.domain.payment;

import java.math.BigDecimal;

import com.codermages.NewsApp.domain.NewsStorageRequest;

public class StorageCashier {
    private final BigDecimal STORAGE_FEE = new BigDecimal(10);
    
    public boolean process(NewsStorageRequest newsStorageRequest) {
        PaymentStrategy paymentStrategy = getPaymentStrategy(newsStorageRequest);

        if (paymentStrategy != null) {
            return paymentStrategy.pay(STORAGE_FEE);
        } else {
            throw new PaymentException();
        }
    }

    private PaymentStrategy getPaymentStrategy(NewsStorageRequest request) {
        if (null == request.getPaymentMethod()) {
            return null;
        } else switch (request.getPaymentMethod()) {
            case CREDIT_CARD:
                return new CreditCardStrategy();
            case PAYPAL:
                return new PayPalStrategy();
            default:
                return null;
        }
    }
}
