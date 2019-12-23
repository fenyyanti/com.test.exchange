package com.test.exchange;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.util.Utils;

@RestController
@RequestMapping
public class ExchangeServiceController {
	
	static Logger logger = Logger.getLogger(ExchangeServiceController.class);
    
	
    @Autowired
    private ExchangeService exchangeService;
       
    @GetMapping("/findbyTren/{dateRate}") 
    public List<ExchangeInformation> findByTrend(@PathVariable("dateRate") String dateRate) {
    	return exchangeService.findTrack(Utils.StringtoDate(dateRate,"yyyy-MM-dd"), 7);
    }
}
