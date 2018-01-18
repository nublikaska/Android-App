package com.example.denis.holodos.modules.receipts;

import com.example.denis.holodos.modules.Model;

public class Document extends Model {
    private Receipt receipt;

    public Document() {

    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}