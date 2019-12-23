package com.test.exchange;

import java.util.Date;
import java.util.List;


public interface ExchangeService {
	
	List<ExchangeInformation> findTrack(Date dateRate, int days);

}
