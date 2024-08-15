package com.neueda.portfolio.Entity;
import jakarta.persistence.*;

@Entity
public class stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String tickerSymbol;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private double stockPrice;

    @Column(nullable = false)
    private double week52High;

    @Column(nullable = false)
    private double week52Low;

    @Column(nullable = false)
    private long averageVolume;

    @Column(nullable = false)
    private String industry;

    @Column(nullable = false)
    private String marketExchange;

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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public double getWeek52High() {
        return week52High;
    }

    public void setWeek52High(double week52High) {
        this.week52High = week52High;
    }

    public double getWeek52Low() {
        return week52Low;
    }

    public void setWeek52Low(double week52Low) {
        this.week52Low = week52Low;
    }

    public long getAverageVolume() {
        return averageVolume;
    }

    public void setAverageVolume(long averageVolume) {
        this.averageVolume = averageVolume;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getMarketExchange() {
        return marketExchange;
    }

    public void setMarketExchange(String marketExchange) {
        this.marketExchange = marketExchange;
    }
}

