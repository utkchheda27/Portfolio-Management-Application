package com.neueda.portfolio.entity;


public class AssetBook {
    private String tickerSymbol;
    private int volume;
    private String companyName;
    private double averageBuyPrice;
    private double currentMarketPrice;
    private double pnl;
    private double pnlPercentage;

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public void setAverageBuyPrice(double averageBuyPrice) {
        this.averageBuyPrice = averageBuyPrice;
    }

    public double getCurrentMarketPrice() {
        return currentMarketPrice;
    }

    public void setCurrentMarketPrice(double currentMarketPrice) {
        this.currentMarketPrice = currentMarketPrice;
    }

    public double getPnl() {
        return pnl;
    }

    public void setPnl(double pnl) {
        this.pnl = pnl;
    }

    public double getPnlPercentage() {
        return pnlPercentage;
    }

    public void setPnlPercentage(double pnlPercentage) {
        this.pnlPercentage = pnlPercentage;
    }



    // Getters and setters

    @Override
    public String toString() {
        return "OrderSummary{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", volume=" + volume +
                ", averageBuyPrice=" + averageBuyPrice +
                ", currentMarketPrice=" + currentMarketPrice +
                ", pnl=" + pnl +
                ", pnlPercentage=" + pnlPercentage +
                '}';
    }




}
