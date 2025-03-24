package com.receiptprocessor.receipt_processor.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Receipt {

    private String retailer;
    private String purchaseDate;
    private String purchaseTime;
    private List<Item> items;
    private String total;

}
