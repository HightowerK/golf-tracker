BEGIN TRANSACTION;

DROP TABLE IF EXISTS users cascade;
drop table if exists address cascade;
drop table if exists course cascade;
drop table if exists league cascade;
drop table if exists round cascade;
drop table if exists user_round cascade;
drop table if exists user_league cascade;

DROP SEQUENCE IF EXISTS seq_user_id;
DROP SEQUENCE IF EXISTS seq_round_id;
DROP SEQUENCE IF EXISTS seq_course_id;
DROP SEQUENCE IF EXISTS seq_address_id;
DROP SEQUENCE IF EXISTS seq_league_id;



CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_round_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_course_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_address_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;
  
CREATE SEQUENCE seq_league_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
        first_name varchar,
        last_name varchar,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

create table round (
        round_id int DEFAULT nextval('seq_round_id'::regclass) NOT NULL,
        league_id int,
        course_id int not null, --alter table foreign key
        tee_time time,
        tee_date date,
        constraint pk_round_id primary key (round_id)
);

create table user_round ( --m2m
        round_id int not null, --alter table foreign key
        user_id int not null, --alter table foreign key
        score int,
        primary key (round_id, user_id),
        foreign key (round_id) references round (round_id),
        foreign key (user_id) references users (user_id)
);

create table course (
        course_id int DEFAULT nextval('seq_course_id'::regclass) NOT NULL,
        course_name varchar not null,
        holes int,
        address_id int, --alter table foreign key
        yardage int,
        slope decimal,
        rating decimal,
        score_to_par int,
        constraint pk_course_id primary key (course_id)
);

create table address (
        address_id int DEFAULT nextval('seq_address_id'::regclass) NOT NULL,
        street varchar,
        city varchar,
        region varchar,
        postal int,
        country varchar(3), --maybe remove char limit
        constraint pk_address_id primary key (address_id)
);

create table league (
        league_id int DEFAULT nextval('seq_league_id'::regclass) NOT NULL,
        league_name varchar,
        score_type varchar,
        course_id int not null, --alter table foreign key
        constraint pk_league_id primary key (league_id)
);

create table user_league ( --m2m
        user_id int not null,
        league_id int not null,
        primary key (user_id,league_id),
        foreign key (user_id) references users (user_id),
        foreign key (league_id) references league (league_id)
);

alter table round
add foreign key (course_id)
references course (course_id);

alter table course
add foreign key (address_id)
references address (address_id);

alter table league 
add foreign key (course_id)
references course (course_id);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','GOLFER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ADMIN');


COMMIT TRANSACTION;
