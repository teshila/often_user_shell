DROP TRIGGER
	IF EXISTS UPDATESTS;
	CREATE TRIGGER UPDATESTS BEFORE UPDATE 
	ON BUY 
	FOR EACH ROW
		BEGIN
			IF NEW.`CODE` LIKE '000%' THEN
			SET NEW.MARKETTYPE = '4609';
			SET NEW.EXCHANGE_TYPE = '2';

			ELSEIF NEW.`CODE` LIKE '002%' THEN
			SET NEW.MARKETTYPE = '4614';
			SET NEW.EXCHANGE_TYPE = '2';

			ELSEIF NEW.`CODE` LIKE '300%' THEN
			SET NEW.MARKETTYPE = '4621';
			SET NEW.EXCHANGE_TYPE = '2';

			ELSEIF NEW.`CODE` LIKE '60%' THEN
			SET NEW.MARKETTYPE = '4353';
			SET NEW.EXCHANGE_TYPE = '1';
		END
	IF;
END
