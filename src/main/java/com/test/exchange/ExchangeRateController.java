package com.test.exchange;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.util.Utils;

import javassist.tools.web.BadHttpRequest;

@RestController
@RequestMapping
public class ExchangeRateController {
	
	static Logger logger = Logger.getLogger(ExchangeRateController.class);
    
	
    @Autowired
	@Qualifier("exchangeRepository")
    private ExchangeRepository exchangeRepository;

    
    @GetMapping("/all")
    public List<ExchangeRate> findAll() {
    	List<ExchangeRate> rt = (List<ExchangeRate>) exchangeRepository.findAll();
		
    	logger.info("request:all");
		for(ExchangeRate er : rt) {
			logger.info(er.getDateRate()+"->"+er.getCurrencyFrom()+"->"+er.getCurrencyTo());
		}	
        return rt;
    }

    @PostMapping(consumes = "application/json")
    public ExchangeRate create(@RequestBody ExchangeRate exrate) {
        return exchangeRepository.save(exrate);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
    	exchangeRepository.delete(id);
    }

    @PutMapping("/update/{id}")
    public ExchangeRate update(@PathVariable("id") String id, @RequestBody Map<String, String> body) throws BadHttpRequest {
        if (exchangeRepository.exists(Integer.parseInt(id))) {
        	int exId = Integer.parseInt(id);
           
            ExchangeRate ex = exchangeRepository.findOne(exId);
            //format YYYY-MM-DD 
            
            ex.setDateRate(Utils.StringtoDate(body.get("dateRate"), "yyyy-MM-dd"));
            ex.setCurrencyFrom(body.get("currencyFrom"));
            ex.setCurrencyTo(body.get("currencyTo"));
            ex.setRate(new BigDecimal(body.get("rate")) );
                 
            return exchangeRepository.save(ex);
           
        } else {
            throw new BadHttpRequest();
        }
    }
}
