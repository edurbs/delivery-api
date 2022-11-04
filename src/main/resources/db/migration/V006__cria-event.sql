CREATE TABLE `delivery`.`event` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `delivery_id` INT NOT NULL,
  `description` TEXT NOT NULL,
  `date_event` DATETIME NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_event_delivery_idx` (`delivery_id` ASC) VISIBLE,
  CONSTRAINT `fk_event_delivery`
    FOREIGN KEY (`delivery_id`)
    REFERENCES `delivery`.`delivery` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
