CREATE DATABASE spring_boot_security_base;

CREATE TABLE spring_boot_security_base.`person`(
    id             int(11) NOT NULL AUTO_INCREMENT,
    birthday       varchar(255) DEFAULT NULL,
    email          varchar(255) DEFAULT NULL,
    name           varchar(255) DEFAULT NULL,
    phone_number   varchar(255) DEFAULT NULL,
    surname        varchar(255) DEFAULT NULL,
    mobile_home_id int(11)      DEFAULT NULL,
    CONSTRAINT person_pk PRIMARY KEY (id),
    CONSTRAINT person_fk_home FOREIGN KEY (mobile_home_id) REFERENCES mobile_home (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE spring_boot_security_base.mobile_home(
    id           int(11) NOT NULL AUTO_INCREMENT,
    brand           varchar(255),
    license_plate   varchar(255),
    model           varchar(255),
    type            varchar(255),
    vin             varchar(255),
    year_of_release varchar(255),
    CONSTRAINT home_pk PRIMARY KEY (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE spring_boot_security_base.`repair_work`(
    id            int(11) NOT NULL AUTO_INCREMENT,
    cost_work     bigint(20)   DEFAULT NULL,
    date          varchar(10) DEFAULT NULL,
    master        varchar(255) DEFAULT NULL,
    name_the_work varchar(255) DEFAULT NULL,
    home_id       int(11)      DEFAULT NULL,
    CONSTRAINT work_pk PRIMARY KEY (id),
    CONSTRAINT work_fk_home FOREIGN KEY (home_id) REFERENCES mobile_home (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE spring_boot_security_base.`spare_part`(
    id              int(11) NOT NULL AUTO_INCREMENT,
    article         varchar(255) DEFAULT NULL,
    cost_part       bigint(20)   DEFAULT NULL,
    name_spare_part varchar(255) DEFAULT NULL,
    CONSTRAINT part_pk PRIMARY KEY (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE spring_boot_security_base. `parts_work`(
    repair_work_id int(11) NOT NULL,
    spare_parts_id int(11) NOT NULL,
    CONSTRAINT work_fk_part FOREIGN KEY (spare_parts_id) REFERENCES spare_part (id),
    CONSTRAINT part_fk_work FOREIGN KEY (repair_work_id) REFERENCES repair_work (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE spring_boot_security_base.`user_data`(
    id       int(11)     NOT NULL AUTO_INCREMENT,
    password varchar(64) NOT NULL,
    username varchar(20) DEFAULT NULL,
    CONSTRAINT user_pk PRIMARY KEY (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE spring_boot_security_base.`user_role`(
    id   int(11)     NOT NULL AUTO_INCREMENT,
    role varchar(20) NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE spring_boot_security_base.`userdata_userrole`(
    user_id int(11) NOT NULL,
    role_id int(11) NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT role_fk_user FOREIGN KEY (user_id) REFERENCES user_data (id),
    CONSTRAINT user_fk_role FOREIGN KEY (role_id) REFERENCES user_role (id)
) ENGINE = MyISAM
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

USE spring_boot_security_base;

INSERT INTO user_role(role)VALUES ('ROLE_ADMIN'),('ROLE_MASTER'), ('ROLE_MANAGER'),('ROLE_USER');
INSERT INTO user_data(username, password)VALUES ('ADMIN', '$2a$12$8Jn4XKdhE9St5/RgW/XY5eAaLMlmB3ZRNqOVpy7.cPpAeQzHa3dwq'),
                                                ('MASTER', '$2a$12$NPgnZdguEuIHQsQdXygcy.T1h3w31/y6FgyMSzKwa3FuxaIwPrMsS'),
                                                ('MANAGER', '$2a$12$8fx.xexDKZtqe71U2Gm.kOwIMsI.EDQ8HVDreOApe3jYLmox8EO9m');
INSERT INTO userdata_userrole(user_id, role_id)VALUES (1, 1),(2, 2), (3, 3);

INSERT INTO person(id, birthday, email, name, phone_number, surname,mobile_home_id)
 VALUES (1,'01.01.1999','ooo@dog.ru','ADAM','89001234567','IVANOVICH',1),
        (2,'10.11.1988','aaa@cat.com','BOGDAN','89009876543','OLEGOVICH',2),
        (3,'30.05.1977','uuu@pet.com','ZAHAR','89009876543','ALEKSEEVICH',3);

INSERT INTO mobile_home(id, brand, license_plate, model, type, vin, year_of_release)
 VALUES (1,'ADRIA','A100OR99RUS','AVENTO','motorhome','xwe998zxcvbqwert','2010'),
        (2,'TABBERT','A777AA77RUS','TAB','camper','xwe234zxcvbqwert','2000'),
        (3,'KNAUS','A999OO999UK','MOBIL','module','xwe785zxcvbqwert','2020');

INSERT INTO repair_work(id,cost_work, date, master, name_the_work, home_id)
 VALUES (1,'100','10.02.2022','IVANOV','suspension repair',2),
        (2,'200','20.07.2021','SMIRNOV','chassis repair',3),
        (3,'500','11.09.2021','DYNAMOV','body repair',3);

INSERT INTO spare_part(id, article, cost_part, name_spare_part) VALUES (1,'123/667','888','spare part suspension'),
(2,'100/111','555','spare part chassis'),
(3,'199/989','111','spare part body'),
(4,'899/123','657','spare part chassis shock absorbers');

INSERT INTO parts_work(repair_work_id, spare_parts_id) VALUES (1,1),(2,2),(3,3), (2,4);

--  login: ADMIN, password: ADMIN
--  login: MASTER, password: MASTER
--  login: MANAGER, password: MANAGER