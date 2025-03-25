package com.receiptprocessor.receipt_processor.service;

public interface CacheService {
    int getReceiptPoints(String receiptId);
    void saveReceiptPoints(String receiptId, int points);
}
