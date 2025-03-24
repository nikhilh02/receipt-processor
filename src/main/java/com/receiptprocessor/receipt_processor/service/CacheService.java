package com.receiptprocessor.receipt_processor.service;

public interface CacheService {
    double getReceiptPoints(String receiptId);
    void saveReceiptPoints(String receiptId, Double points);
}
