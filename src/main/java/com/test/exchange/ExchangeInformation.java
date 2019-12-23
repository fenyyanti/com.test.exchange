package com.test.exchange;

import java.math.BigDecimal;

public class ExchangeInformation {
	
	private String currencyFrom;
    private String currencyTo;
	private String avgRate;
	private int xdaysRate;
	private BigDecimal variance;
	

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(String currency_from) {
		this.currencyFrom = currency_from;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(String currency_to) {
		this.currencyTo = currency_to;
	}
	  
	public String getAvgRate() {
		return avgRate;
	}

	public void setAvg(String avgRate) {
		this.avgRate = avgRate;
	}
	
	public int getXdaysRate() {
		return xdaysRate;
	}

	public void setXdaysRate(int xdaysRate) {
		this.xdaysRate = xdaysRate;
	}

	public BigDecimal getVariance() {
		return variance;
	}

	public void setVariance(BigDecimal variance) {
		this.variance = variance;
	}
	

	@Override
    public String toString() {
        return "ExchangeInformation {" +

        		"currency_from='" + currencyFrom + '\''+
                ", currency_to='" + currencyTo + '\'' +  
                ", avg='" + avgRate + '\'' +
                ", xdaysavg='" + xdaysRate + '\'' +
                ", variance='" + variance + '\''+
                '}';
    }
}
