-- -----------------------------------------------------------------------------
-- 24.07.2008
--
-- (C) Eclipse SP LLC. All rights reserved
-- 
-- This script creates default accounts 
--
-- $Revision: 855 $
-- $Author: dproshkin_tckh $
-- $Date: 2008-08-13 16:40:15 +0300 (Ср, 13 авг 2008) $
-- -----------------------------------------------------------------------------

insert into `role` values 
	('administrators', 255), 
	('users', 119);

insert into `account` values 
	(1, 'pmarinchev@eclipse-sp.com','Pavel', 'Marinchev', 'paulisio', '6ae5f9124b9e31ba2768af650fa0fcf7', 
		null, null,null, 'administrators', null);

insert into `account` values 
	(2, 'vpostrigan@eclipse-sp.com','Vova', 'Postrigan', 'tplanner', password('tplanner'), 
		null, null,null, 'users', null);

insert into `account` values 
	(3, 'dproshkin@eclipse-sp.com','Denis', 'Proshkin', 'Avalanch', '6ae5f9124b9e31ba2768af650fa0fcf7', 
		null, null,null, 'administrators', null);
