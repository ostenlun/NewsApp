package com.codermages.NewsApp.domain;

@lombok.Data
public class NewsStorageRequest {
    private String userId;
    private PaymentMethod paymentMethod;
    private String data;
    private int responseCode;
}
