package com.example.denis.holodos.modules.receipts;

import com.example.denis.holodos.modules.Group;
import com.example.denis.holodos.modules.Model;
import com.example.denis.holodos.modules.User;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Receipt extends Model implements Serializable {

    private User owner;

    private Group group;

    private String operator;

    private String user;

    private String userInn;

    private double totalSum;

    private String fiscalDriveNumber;

    private String kktRegId;

    private String fiscalDocumentNumber;

    private double cashTotalType;

    private double ecashTotalSum;

    private String retailPlaceAddress;

    private String fiscalSign;

    private List<Item> items = new ArrayList<>();

    public Receipt() {

    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserInn() {
        return userInn;
    }

    public void setUserInn(String userInn) {
        this.userInn = userInn;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    public String getFiscalDriveNumber() {
        return fiscalDriveNumber;
    }

    public void setFiscalDriveNumber(String fiscalDriveNumber) {
        this.fiscalDriveNumber = fiscalDriveNumber;
    }

    public String getKktRegId() {
        return kktRegId;
    }

    public void setKktRegId(String kktRegId) {
        this.kktRegId = kktRegId;
    }

    public String getFiscalDocumentNumber() {
        return fiscalDocumentNumber;
    }

    public void setFiscalDocumentNumber(String fiscalDocumentNumber) {
        this.fiscalDocumentNumber = fiscalDocumentNumber;
    }

    public double getCashTotalType() {
        return cashTotalType;
    }

    public void setCashTotalType(double cashTotalType) {
        this.cashTotalType = cashTotalType;
    }

    public double getEcashTotalSum() {
        return ecashTotalSum;
    }

    public void setEcashTotalSum(double ecashTotalSum) {
        this.ecashTotalSum = ecashTotalSum;
    }

    public String getRetailPlaceAddress() {
        return retailPlaceAddress;
    }

    public void setRetailPlaceAddress(String retailPlaceAddress) {
        this.retailPlaceAddress = retailPlaceAddress;
    }

    public String getFiscalSign() {
        return fiscalSign;
    }

    public void setFiscalSign(String fiscalSign) {
        this.fiscalSign = fiscalSign;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
