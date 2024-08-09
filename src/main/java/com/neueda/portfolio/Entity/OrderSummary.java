package com.neueda.portfolio.Entity;

import java.util.Date;

public class OrderSummary {
	  private String instrumentName;
	    private Date dateOfPurchase;
	    private int volume;
	    private double boughtPrice;
	    private double currentMarketPrice;
	    private double currentMarketValue;
	    private double pnl;
	    private double pnlPercentage;

	    // Getters and setters

	    @Override
	    public String toString() {
	        return "OrderSummary{" +
	                "instrumentName='" + instrumentName + '\'' +
	                ", dateOfPurchase=" + dateOfPurchase +
	                ", volume=" + volume +
	                ", boughtPrice=" + boughtPrice +
	                ", currentMarketPrice=" + currentMarketPrice +
	                ", currentMarketValue=" + currentMarketValue +
	                ", pnl=" + pnl +
	                ", pnlPercentage=" + pnlPercentage +
	                '}';
	    }

		public String getInstrumentName() {
			return instrumentName;
		}

		public void setInstrumentName(String instrumentName) {
			this.instrumentName = instrumentName;
		}

		public Date getDateOfPurchase() {
			return dateOfPurchase;
		}

		public void setDateOfPurchase(Date dateOfPurchase) {
			this.dateOfPurchase = dateOfPurchase;
		}

		public int getVolume() {
			return volume;
		}

		public void setVolume(int volume) {
			this.volume = volume;
		}

		public double getBoughtPrice() {
			return boughtPrice;
		}

		public void setBoughtPrice(double boughtPrice) {
			this.boughtPrice = boughtPrice;
		}

		public double getCurrentMarketPrice() {
			return currentMarketPrice;
		}

		public void setCurrentMarketPrice(double currentMarketPrice) {
			this.currentMarketPrice = currentMarketPrice;
		}

		public double getCurrentMarketValue() {
			return currentMarketValue;
		}

		public void setCurrentMarketValue(double currentMarketValue) {
			this.currentMarketValue = currentMarketValue;
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
	    
	    

}
