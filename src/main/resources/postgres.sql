CREATE TABLE public.mobile_home(
    id bigserial NOT NULL,
    brand varchar(25),
    license_plate varchar(25),
    model varchar(25),
    "type" varchar(25),
    vin varchar(35),
    year_of_release varchar(25),
    CONSTRAINT mobile_home_pk PRIMARY KEY (id)
	);
	
	CREATE TABLE public.person(
    id bigserial NOT NULL,
    birthday varchar(25) ,
    email varchar(25) ,
    "name" varchar(30) ,
    phone_number varchar(25) ,
    surname varchar(25) ,
    mobile_home_id integer,
    CONSTRAINT person_pk PRIMARY KEY (id),
    CONSTRAINT person_fk_home FOREIGN KEY (mobile_home_id) REFERENCES mobile_home (id)
);

CREATE TABLE public.repair_work(
    id bigserial NOT NULL,
    cost_work integer ,
    date varchar(10),
    master varchar(25) ,
    name_the_work varchar(155),
    home_id integer,
    CONSTRAINT work_pk PRIMARY KEY (id),
    CONSTRAINT work_fk_home FOREIGN KEY (home_id) REFERENCES mobile_home (id)
);

CREATE TABLE public.spare_part(
   id bigserial NOT NULL,
    article varchar(25),
    cost_part integer ,
    name_spare_part varchar(225),
   CONSTRAINT part_pk PRIMARY KEY (id)
);

CREATE TABLE public.parts_work(
    repair_work_id integer NOT NULL,
    spare_parts_id integer NOT NULL,
    CONSTRAINT part_fk FOREIGN KEY (spare_parts_id) REFERENCES spare_part (id),
    CONSTRAINT work_fk FOREIGN KEY (repair_work_id) REFERENCES repair_work (id)
);

CREATE TABLE public.user_data(
    id bigserial NOT NULL,
    "password" varchar(64) NOT NULL,
     username varchar(20),
     CONSTRAINT user_data_fk PRIMARY KEY (id)
);

CREATE TABLE public.user_role(
    id bigserial NOT NULL,
    "role" varchar(20) NOT NULL,
    CONSTRAINT user_fk PRIMARY KEY (id)
);

CREATE TABLE public.userdata_userrole(
    user_id integer NOT NULL,
    role_id integer NOT NULL,
    CONSTRAINT data_role_fk PRIMARY KEY (user_id, role_id),
    CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES user_data (id),
    CONSTRAINT role_fk FOREIGN KEY (role_id) REFERENCES user_role (id)
);

INSERT INTO user_role(id,role)VALUES (1,'ROLE_ADMIN');
INSERT INTO user_role(id,role)VALUES (2,'ROLE_MASTER');
INSERT INTO user_role(id,role)VALUES (3,'ROLE_MANAGER');
INSERT INTO user_role(id,role)VALUES (4,'ROLE_USER');
INSERT INTO user_data(id,username, password)VALUES (1,'ADMIN', '$2a$12$8Jn4XKdhE9St5/RgW/XY5eAaLMlmB3ZRNqOVpy7.cPpAeQzHa3dwq');
INSERT INTO user_data(id,username, password)VALUES (2,'MASTER', '$2a$12$NPgnZdguEuIHQsQdXygcy.T1h3w31/y6FgyMSzKwa3FuxaIwPrMsS');
INSERT INTO user_data(id,username, password)VALUES (3,'MANAGER', '$2a$12$8fx.xexDKZtqe71U2Gm.kOwIMsI.EDQ8HVDreOApe3jYLmox8EO9m');
INSERT INTO userdata_userrole(user_id, role_id)VALUES (1, 1);
INSERT INTO userdata_userrole(user_id, role_id)VALUES (2, 2);
INSERT INTO userdata_userrole(user_id, role_id)VALUES (3, 3);

INSERT INTO mobile_home(id, brand, license_plate, model, type, vin, year_of_release) VALUES (1,'ADRIA','A100OR99RUS','AVENTO','motorhome','xwe998zxcvbqwert','2010');
INSERT INTO mobile_home(id, brand, license_plate, model, type, vin, year_of_release) VALUES (2,'TABBERT','A777AA77RUS','TAB','camper','xwe234zxcvbqwert','2000');
INSERT INTO mobile_home(id, brand, license_plate, model, type, vin, year_of_release) VALUES (3,'KNAUS','A999OO999UK','MOBIL','module','xwe785zxcvbqwert','2020');

INSERT INTO person(id,birthday, email, name, phone_number, surname,mobile_home_id) VALUES (1,'01.01.1999','ooo@dog.ru','ADAM','89001234567','IVANOVICH',1);
INSERT INTO person(id,birthday, email, name, phone_number, surname,mobile_home_id) VALUES (2,'10.11.1988','aaa@cat.com','BOGDAN','89009876543','OLEGOVICH',2);
INSERT INTO person(id,birthday, email, name, phone_number, surname,mobile_home_id) VALUES (3,'30.05.1977','uuu@pet.com','ZAHAR','89009876543','ALEKSEEVICH',3);

INSERT INTO repair_work(id,cost_work, date, master, name_the_work, home_id) VALUES (1,'100','10.02.2022','IVANOV','suspension repair',2);
INSERT INTO repair_work(id,cost_work, date, master, name_the_work, home_id) VALUES (2,'200','20.07.2021','SMIRNOV','chassis repair',3);
INSERT INTO repair_work(id,cost_work, date, master, name_the_work, home_id) VALUES (3,'500','11.09.2021','DYNAMOV','body repair',3);

INSERT INTO spare_part(id, article, cost_part, name_spare_part) VALUES (1,'123/667','888','spare part suspension');
INSERT INTO spare_part(id, article, cost_part, name_spare_part) VALUES (2,'100/111','555','spare part chassis');
INSERT INTO spare_part(id, article, cost_part, name_spare_part) VALUES (3,'199/989','111','spare part body');
INSERT INTO spare_part(id, article, cost_part, name_spare_part) VALUES (4,'899/123','657','spare part chassis shock absorbers');

INSERT INTO parts_work(repair_work_id, spare_parts_id) VALUES (1,1);
INSERT INTO parts_work(repair_work_id, spare_parts_id) VALUES (2,2);
INSERT INTO parts_work(repair_work_id, spare_parts_id) VALUES (3,3);
INSERT INTO parts_work(repair_work_id, spare_parts_id) VALUES (2,4);

--  login: ADMIN, password: ADMIN
--  login: MASTER, password: MASTER
--  login: MANAGER, password: MANAGER