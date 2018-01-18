package com.example.denis.holodos.modules.receipts;

public class Ticket {

    private String fiscalDriveId;
    private String fiscalDocumentNumber;
    private String fiscalId;

    public Ticket() {

    }

    public String getFiscalDriveId() {
        return fiscalDriveId;
    }

    public void setFiscalDriveId(String fiscalDriveId) {
        this.fiscalDriveId = fiscalDriveId;
    }

    public String getFiscalDocumentNumber() {
        return fiscalDocumentNumber;
    }

    public void setFiscalDocumentNumber(String fiscalDocumentNumber) {
        this.fiscalDocumentNumber = fiscalDocumentNumber;
    }

    public String getFiscalId() {
        return fiscalId;
    }

    public void setFiscalId(String fiscalId) {
        this.fiscalId = fiscalId;
    }
}
