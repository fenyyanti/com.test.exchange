package com.test.exchange;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ExchangeServiceImpl implements ExchangeService {

	static Logger log = Logger.getLogger(ExchangeServiceImpl.class);
    
	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<ExchangeInformation> findTrack(Date dateRate, int days) {
		String sql = " SELECT t2.currency_code_from currencyFrom, t2.currency_code_to currencyTo, "+
				"		IFNULL(t2.average, 'insufficient data') avgRate, t2.variance "+
	            "	FROM "+
				"("+ 
				"	SELECT count(1) as total_days, currency_code_from, currency_code_to, "+
	            "  (MAX(rate)-MIN(rate)) as variance,"+
	            "   if(count(1)>=7, AVG(rate),NULL) average "+
				"	FROM exchange_rate "+
				"	WHERE rate is not null and "+
				"	(date_rate > DATE_ADD(?1, interval -?2 day) and date_rate <= ?1) "+
				"	GROUP BY currency_code_from, currency_code_to "+
	            ") as t2";
		Query q = em.createNativeQuery(sql);
		q.setParameter(1, dateRate);
		q.setParameter(2, days == 0 ? 7 : days);
		
		List<Object[]> ls = q.getResultList();
		List<ExchangeInformation> list = new ArrayList<ExchangeInformation>();
		
		log.info("findTrack query size:"+ls.size());
		for(Object[] e : ls) {
			BigDecimal variance = new BigDecimal(e[3].toString());
			
			ExchangeInformation o = new ExchangeInformation();
			o.setCurrencyFrom((String)e[0]);
			o.setCurrencyTo((String)e[1]);
			o.setXdaysRate(days);
			o.setVariance(variance);
			
			list.add(o);
			
		}
		return list;
	}

	/*public List<ExchangeRate> findByDateRate(Date dateRate) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<ExchangeRate> criteria = builder.createQuery(ExchangeRate.class);
		Root<ExchangeRate> exchangeRate = criteria.from(ExchangeRate.class);
		
		criteria.where(builder.equal(exchangeRate.get("dateRate"), dateRate));
		log.info("Implementasi findByDateRate :"+ dateRate);
		
		TypedQuery<ExchangeRate> query = em.createQuery(criteria);
		try {
			List<ExchangeRate> erates = query.getResultList();
			log.info("list size:"+erates.size());
		
			return erates;
		} catch(NoResultException e) {
			log.error(e.getMessage());
			return null;
		}
		
	}*/

}
