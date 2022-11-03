CREATE TABLE `delivery` (
  `id` int NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `tax` decimal(10,2) NOT NULL,
  `status` varchar(20) NOT NULL,
  `date_order` datetime NOT NULL,
  `date_completed` datetime DEFAULT NULL,
  `recipient_name` varchar(60) NOT NULL,
  `recipient_address` varchar(255) NOT NULL,
  `recipient_number` varchar(30) NOT NULL,
  `recipient_complement` varchar(60) DEFAULT NULL,
  `recipient_locality` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_delivery_customer_idx` (`customer_id`),
  CONSTRAINT `fk_delivery_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


    