package com.neueda.portfolio.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity
public class Instrument {
    @Id
    @Column(length=100)
    private String tickerSymbol;
    @Column(length=100)
    @NotNull
    private String companyName;
    @Column
    private double averageBuyPrice;
    @Column
    private int volume;
    @Column
    private String type;

    public Instrument(){

    }
    public Instrument(String tickerSymbol, String companyName, double averageBuyPrice, int volume, String type) {
        this.tickerSymbol = tickerSymbol;
        this.companyName = companyName;
        this.averageBuyPrice = averageBuyPrice;
        this.volume = volume;
        this.type = type;
    }
    
    @Override
    public String toString() {
        return "Instrument{" +
                "tickerSymbol='" + tickerSymbol + '\'' +
                ", companyName='" + companyName + '\'' +
                ", averageBuyPrice=" + averageBuyPrice +
                ", volume=" + volume +
               ", type=" + type +
                '}';
    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public @NotNull String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(@NotNull String companyName) {
        this.companyName = companyName;
    }

    public double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public void setAverageBuyPrice(double averageBuyPrice) {
        this.averageBuyPrice = averageBuyPrice;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}
}
