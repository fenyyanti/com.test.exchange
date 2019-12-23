package com.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.test.exchange.ExchangeInformation;
import com.test.exchange.ExchangeRate;
import com.test.exchange.ExchangeRepository;
import com.test.exchange.ExchangeService;
import com.test.util.Utils;



@SpringBootApplication
@ComponentScan("com.test.Exchange")
@EnableJpaRepositories("com.test.Exchange")
public class MainApplicationClass {

	static Logger log = Logger.getLogger(MainApplicationClass.class);
    
	
    public static void main(String[] args) {
    	ConfigurableApplicationContext context = SpringApplication.run(MainApplicationClass.class, args);
    	Date dt = Utils.StringtoDate("2018-08-12", "yyyy-MM-dd");
    	
    	ExchangeRepository repo = context.getBean(ExchangeRepository.class );

    	log.info("Main Date :"+dt);
    	List<ExchangeRate> rate = repo.findAll();
		log.info("size findAll="+rate.size());
		for (ExchangeRate e : rate) {
			log.info("Id= "+e.getId()+",Date="+e.getDateRate()+",rate="+e.getRate()+",currency= "+e.getCurrencyFrom()+" - "+e.getCurrencyTo()+"\n");
		}
		
		
		//update
		ExchangeRate er = repo.findOne(10);
		log.info("Update for Id= "+er.getId()+",Date="+er.getDateRate()+",rate="+er.getRate()+",currency= "+er.getCurrencyFrom()+" - "+er.getCurrencyTo()+"\n");
		er.setRate(new BigDecimal(2));
		log.info(repo.save(er));
		
		ExchangeService exchangeService = context.getBean(ExchangeService.class );
		List<ExchangeInformation> info = exchangeService.findTrack(dt, 7);
		log.info("size findbyTren="+info.size());
		for (ExchangeInformation e : info) {
			log.info("avg="+e.getAvgRate()+",variances="+ e.getVariance()+",currency="+e.getCurrencyFrom()+" - "+e.getCurrencyTo()+"\n");
		}
		
		
	
    }

    
    
}