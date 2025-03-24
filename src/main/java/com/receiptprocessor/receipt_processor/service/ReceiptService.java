package com.receiptprocessor.receipt_processor.service;

import com.receiptprocessor.receipt_processor.entity.Receipt;

public interface ReceiptService {
    String saveReceipt(Receipt receipt);
    double getReceiptPoints(String receiptId);
}
