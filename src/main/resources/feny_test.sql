CREATE SCHEMA `feny_test` ;

use feny_banking;

CREATE TABLE `exchange_rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_rate` date DEFAULT NULL,
  `currency_code_from` varchar(3) NOT NULL,
  `currency_code_to` varchar(3) NOT NULL,
  `rate` decimal(15,4) DEFAULT NULL,
  `creation_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `currency_code_from` (`currency_code_from`),
  KEY `currency_code_to` (`currency_code_to`),
  KEY `date_rate` (`date_rate`),
  CONSTRAINT `exchange_rate_ibfk_1` FOREIGN KEY (`currency_code_from`) REFERENCES `currency` (`code`) ON DELETE CASCADE,
  CONSTRAINT `exchange_rate_ibfk_2` FOREIGN KEY (`currency_code_to`) REFERENCES `currency` (`code`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8