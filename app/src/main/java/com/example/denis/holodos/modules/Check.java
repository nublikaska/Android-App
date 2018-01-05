package com.example.denis.holodos.modules;

import org.threeten.bp.LocalDateTime;

import java.math.BigDecimal;

public class Check extends Model {

    private static final long serialVersionUID = 1L;

    private User user;

    private LocalDateTime dateTime;

    private String type;

    private BigDecimal totalPrice;

    private String fnNumber;

    private String fdNumber;

    private String fpdNumber;

    public Check() {

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getFnNumber() {
        return fnNumber;
    }

    public void setFnNumber(String fnNumber) {
        this.fnNumber = fnNumber;
    }

    public String getFdNumber() {
        return fdNumber;
    }

    public void setFdNumber(String fdNumber) {
        this.fdNumber = fdNumber;
    }

    public String getFpdNumber() {
        return fpdNumber;
    }

    public void setFpdNumber(String fpdNumber) {
        this.fpdNumber = fpdNumber;
    }
}
