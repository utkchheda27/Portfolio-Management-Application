package com.neueda.portfolio.Entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Cashflow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="tickerSymbol", length=10)
    @NotNull
    private String tickerSymbol;

    @Column
    @NotNull
    private double pnl;

    @Column
    @NotNull
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name="tickerSymbol", insertable = false, updatable = false)
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
    public double getPnl() {
        return pnl;
    }

    public void setPnl(@NotNull double pnl) {
        this.pnl = pnl;
    }

    public @NotNull Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
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
        return "Cashflow{" +
                "id=" + id +
                ", tickerSymbol='" + tickerSymbol + '\'' +
                ", pnl=" + pnl +
                ", transactionDate=" + transactionDate +
                '}';
    }
}
