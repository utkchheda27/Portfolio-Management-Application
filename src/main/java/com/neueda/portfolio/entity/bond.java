package com.neueda.portfolio.Entity;

import jakarta.persistence.*;

@Entity
public class bond {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Ticker_Symbol", nullable = false, length = 1024)
    private String tickerSymbol;

    @Column(name = "Issuer", nullable = false, length = 1024)
    private String issuer;

    @Column(name = "Coupon_Rate", nullable = false)
    private double couponRate;

    @Column(name = "Face_Value", nullable = false)
    private long faceValue;

    @Column(name = "Maturity_Date", nullable = false, length = 1024)
    private String maturityDate;

    @Column(name = "Credit_Rating", nullable = false, length = 1024)
    private String creditRating;

    @Column(name = "Bond_Price", nullable = false)
    private double bondPrice;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public double getCouponRate() {
        return couponRate;
    }

    public void setCouponRate(double couponRate) {
        this.couponRate = couponRate;
    }

    public long getFaceValue() {
        return faceValue;
    }

    public void setFaceValue(long faceValue) {
        this.faceValue = faceValue;
    }

    public String getMaturityDate() {
        return maturityDate;
    }

    public void setMaturityDate(String maturityDate) {
        this.maturityDate = maturityDate;
    }

    public String getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }

    public double getBondPrice() {
        return bondPrice;
    }

    public void setBondPrice(double bondPrice) {
        this.bondPrice = bondPrice;
    }
}

