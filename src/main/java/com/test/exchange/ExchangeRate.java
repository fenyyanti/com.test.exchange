package com.test.exchange;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table(name = "exchange_rate")
@Entity(name = "ExchangeRate")
public class ExchangeRate {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

	@Column(name = "date_rate")
	@Temporal(TemporalType.DATE)
    private Date dateRate;
	
    @Column(name = "currency_code_from")
    private String currencyFrom;
    
    @Column(name = "currency_code_to")
    private String currencyTo;
	
    @Column(name = "rate")
    private BigDecimal rate;

    public ExchangeRate() {
    	super();
    }
	public ExchangeRate(Date date_rate, String currencyFrom, String currencyTo, BigDecimal rate) {
		super();
		this.dateRate = date_rate;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.rate = rate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDateRate() {
		return dateRate;
	}

	public void setDateRate(Date date_rate) {
		this.dateRate = date_rate;
	}

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

	public BigDecimal getRate() {
		return rate;
	}

	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
  
	@Override
    public String toString() {
        return "ExchangeRate{" + "id='" + id + '\'' +
        		", dateRate='" + dateRate + '\'' + 
        		", currencyFrom='" + currencyFrom + '\''+
                ", currencyTo='" + currencyTo + '\'' +  
                ", rate='" + rate + '\'' +'}';
    }
}
