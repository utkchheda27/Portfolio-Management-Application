package com.neueda.portfolio.Entity;

import jakarta.persistence.*;

@Entity
public class StockData {


<<<<<<< HEAD:src/main/java/com/neueda/portfolio/entity/StockData.java
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

>>>>>>> e476deae3ada5dae1f083d46c57f93f34073f741:src/main/java/com/neueda/portfolio/Entity/StockData.java
    @Column(name = "tickerSymbol", nullable = false)
    private String tickerSymbol;


    @Column(name = "timestamp", nullable = false)
    private String timestamp;

    @Column(name = "open")
    private Double open;

    @Column(name = "high")
    private Double high;

    @Column(name = "low")
    private Double low;

    @Column(name = "close")
    private Double close;

    @Column(name = "volume")
    private Long volume;
    public StockData(){

    }
    public StockData(long id,String tickerSymbol, String timestamp, Double open, Double high, Double low, Double close, Long volume) {
        this.tickerSymbol = tickerSymbol;
        this.id=id;
        this.timestamp = timestamp;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters and Setters
    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public void setTickerSymbol(String tickerSymbol) {
        this.tickerSymbol = tickerSymbol;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Double getOpen() {
        return open;
    }

    public void setOpen(Double open) {
        this.open = open;
    }

    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getClose() {
        return close;
    }

    public void setClose(Double close) {
        this.close = close;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }
}

