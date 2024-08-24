package com.neueda.portfolio.entity;

import java.util.Date;

public class CashflowBook {
    private String tickerSymbol;
    private String companyName;
    private int volume;
    private double currentMarketPrice;


    private double averageBuyPrice;
    private double pnl;
    private Date transactionDate;

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public double getCurrentMarketPrice() {
        return currentMarketPrice;
    }

    public void setCurrentMarketPrice(double currentMarketPrice) {
        this.currentMarketPrice = currentMarketPrice;
    }

    public double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public void setAverageBuyPrice(double averageBuyPrice) {
        this.averageBuyPrice = averageBuyPrice;
    }

    public double getPnl() {
        return pnl;
    }

    public void setPnl(double pnl) {
        this.pnl = pnl;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public CashflowBook(String tickerSymbol, String companyName, int volume, double currentMarketPrice, double averageBuyPrice, double pnl, Date transactionDate) {
        this.tickerSymbol = tickerSymbol;
        this.companyName = companyName;
        this.volume = volume;
        this.currentMarketPrice = currentMarketPrice;
        this.averageBuyPrice = averageBuyPrice;
        this.pnl = pnl;
        this.transactionDate = transactionDate;
    }
    public CashflowBook(){

    }


}
