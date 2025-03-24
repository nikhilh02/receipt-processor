package com.receiptprocessor.receipt_processor.controller;

import com.receiptprocessor.receipt_processor.entity.Receipt;
import com.receiptprocessor.receipt_processor.service.ReceiptService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    ReceiptController(ReceiptService receiptService){
        this.receiptService = receiptService;
    }

    @GetMapping("/{id}/points")
    public Map<String, Double> getReceiptPointsById(@PathVariable String id) {
        Map<String, Double> responseMap = new HashMap<>();
        double responsePoints = receiptService.getReceiptPoints(id);
        responseMap.put("points", responsePoints);
        return responseMap;
    }

    @PostMapping("/process")
    public Map<String, String> processReceipt(@RequestBody Receipt receipt){
        System.out.println(receipt.getPurchaseDate());
        Map<String, String> responseId = new HashMap<>();
        String receiptId = receiptService.saveReceipt(receipt);
        responseId.put("id", receiptId);
        return responseId;
    }
}
