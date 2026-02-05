package com.zomato.earnings.demo.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;



@Entity
@Table(name = "daily_earnings")
public class DailyEarnings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;

    @Column(name = "entry_month", length = 15, nullable = false)
    private String entryMonth;

    @Column(name = "entry_year", nullable = false)
    private Short entryYear;

    @Column(name = "petrol_cost", precision = 10, scale = 2)
    private BigDecimal petrolCost = BigDecimal.ZERO;

    @Column(name = "cash_on_delivery", precision = 10, scale = 2)
    private BigDecimal cashOnDelivery = BigDecimal.ZERO;

    @Column(name = "cash_deposit", precision = 10, scale = 2)
    private BigDecimal cashDeposit = BigDecimal.ZERO;

    @Column(name = "other_cash", precision = 10, scale = 2)
    private BigDecimal otherCash = BigDecimal.ZERO;

    @Column(name = "daily_with_draw_amount", precision = 10, scale = 2)
    private BigDecimal dailyWithDrawAmount = BigDecimal.ZERO;

    @Column(name = "total_earnings", precision = 10, scale = 2)
    private BigDecimal totalEarnings = BigDecimal.ZERO;

    @Column(name = "other_type", length = 50)
private String otherType;


    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    /* =========================
       Auto timestamp
       ========================= */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    /* =========================
       Getters & Setters
       ========================= */

       public String getOtherType() {
    return otherType;
}

public void setOtherType(String otherType) {
    this.otherType = otherType;
}


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public String getEntryMonth() {
        return entryMonth;
    }

    public void setEntryMonth(String entryMonth) {
        this.entryMonth = entryMonth;
    }

    public Short getEntryYear() {
        return entryYear;
    }

    public void setEntryYear(Short entryYear) {
        this.entryYear = entryYear;
    }

    public BigDecimal getPetrolCost() {
        return petrolCost;
    }

    public void setPetrolCost(BigDecimal petrolCost) {
        this.petrolCost = petrolCost;
    }

    public BigDecimal getCashOnDelivery() {
        return cashOnDelivery;
    }

    public void setCashOnDelivery(BigDecimal cashOnDelivery) {
        this.cashOnDelivery = cashOnDelivery;
    }

    public BigDecimal getCashDeposit() {
        return cashDeposit;
    }

    public void setCashDeposit(BigDecimal cashDeposit) {
        this.cashDeposit = cashDeposit;
    }

    public BigDecimal getOtherCash() {
        return otherCash;
    }

    public void setOtherCash(BigDecimal otherCash) {
        this.otherCash = otherCash;
    }

    public BigDecimal getDailyWithDrawAmount() {
        return dailyWithDrawAmount;
    }

    public void setDailyWithDrawAmount(BigDecimal dailyWithDrawAmount) {
        this.dailyWithDrawAmount = dailyWithDrawAmount;
    }

    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public void setTotalEarnings(BigDecimal totalEarnings) {
        this.totalEarnings = totalEarnings;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
