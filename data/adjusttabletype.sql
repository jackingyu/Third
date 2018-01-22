ALTER TABLE `blaimar`.`salesorder` 
CHANGE COLUMN `weddingdate` `weddingdate` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `trydate` `trydate` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `photodate` `photodate` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `deliverydate` `deliverydate` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `orderdate` `orderdate` DATE NULL DEFAULT NULL ;

ALTER TABLE `blaimar`.`salesquotation` 
CHANGE COLUMN `weddingdate` `weddingdate` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `trydate` `trydate` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `photodate` `photodate` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `deliverydate` `deliverydate` DATE NULL DEFAULT NULL ;

ALTER TABLE `blaimar`.`orderentry` 
CHANGE COLUMN `deliverydate` `deliverydate` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `sizedate` `sizedate` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `trydate` `trydate` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `actual_trydate` `actual_trydate` DATE NULL DEFAULT NULL ;

ALTER TABLE `blaimar`.`customer` 
CHANGE COLUMN `birthday` `birthday` DATE NULL DEFAULT NULL ,
CHANGE COLUMN `weddingdate` `weddingdate` DATE NULL DEFAULT NULL ;
