package com.neueda.portfolio.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
@Entity
@Table(name="Orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(length=10)
    @NotNull
    private String tickerSymbol;

    @Column
    @NotNull
    private int volume;

    @Column(length=10)
    @NotNull
    private String action;

    @Column
    @NotNull
    private Date transactionDate;

    @Column
    @NotNull
    private double pricePerShare;
    
    

    @NotNull
    public double getPricePerShare() {
        return pricePerShare;
    }

    public void setPricePerShare(@NotNull double pricePerShare) {
        this.pricePerShare = pricePerShare;
    }

    @NotNull
    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(@NotNull double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Column
    @NotNull
    private double totalMoney;


    @ManyToOne
    @JoinColumn(name="tickerSymbol",referencedColumnName = "tickerSymbol",insertable = false,updatable = false)
    private Instrument instrument;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(@NotNull String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    @NotNull
    public int getVolume() {
        return volume;
    }

    public void setVolume(@NotNull int volume) {
        this.volume = volume;
    }

    public @NotNull String getAction() {
        return action;
    }

    public void setAction(@NotNull String action) {
        this.action = action;
    }

    public @NotNull Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(@NotNull Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", volume=" + volume +
                ", action='" + action + '\'' +
                ", transactionDate=" + transactionDate +
                ", pricePerShare=" +pricePerShare +
                ", totalMoney="+totalMoney +
                '}';
    }

}
