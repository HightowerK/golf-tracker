--insert into tableName (columns,columns) values (columnvalue,columnvalue);

--users -> userinfo, league (requires courseID), round, 
--users and rounds -> add to user-round, once in user-round add scores by user_id
--users and leagues -> add to user-league

INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('Shooter', 'Shooter', 'McGavin', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'LEAGUE_ADMIN');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('Happy', 'Happy', 'Gilmore', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'LEAGUE_ADMIN');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('Al', 'Al', 'Czervik', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'GOLFER');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('Ty', 'Ty', 'Webb', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'GOLFER');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('Danny', 'Danny', 'Noonan', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'GOLFER');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('Judge', 'Judge', 'Smails', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'GOLFER');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('Carl', 'Carl', 'Spackler', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'GOLFER');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('Bagger', 'Bagger', 'Vance', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'GOLFER');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('Rannulph', 'Rannulph', 'Junuh', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'GOLFER');
INSERT INTO users (username, first_name, last_name, password_hash, role) VALUES ('Roy','Roy', 'McAvoy', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'GOLFER');

INSERT INTO league (league_name, score_type, course_id) VALUES ('Ty Webb Invitational', 'Stroke Play', 1); 
INSERT INTO league (league_name, score_type, course_id) VALUES ('Happy Gilmore Championship', 'Stroke Play', 10); 

INSERT INTO user_league (user_id, league_id) VALUES (4, 2);
INSERT INTO user_league (user_id, league_id) VALUES (5, 2);
INSERT INTO user_league (user_id, league_id) VALUES (7, 1);
INSERT INTO user_league (user_id, league_id) VALUES (9, 1);
INSERT INTO user_league (user_id, league_id) VALUES (3, 1);
INSERT INTO user_league (user_id, league_id) VALUES (6, 1);
INSERT INTO user_league (user_id, league_id) VALUES (8, 2);
INSERT INTO user_league (user_id, league_id) VALUES (10,2);

INSERT INTO round (league_id, course_id, tee_time, tee_date) VALUES (2, 1, '02:00', '2021-06-04');
INSERT INTO round (league_id, course_id, tee_time, tee_date) VALUES (2, 2, '02:30', '2021-06-04');
INSERT INTO round (league_id, course_id, tee_time, tee_date) VALUES (1, 3, '01:00', '2021-06-05');
INSERT INTO round (league_id, course_id, tee_time, tee_date) VALUES (1, 4, '01:30', '2021-06-05');

INSERT INTO user_round (round_id, user_id, score) VALUES (1, 4, 74);
INSERT INTO user_round (round_id, user_id, score) VALUES (2, 5, 73);
INSERT INTO user_round (round_id, user_id, score) VALUES (3, 7, 80);
INSERT INTO user_round (round_id, user_id, score) VALUES (4, 9, 81);
INSERT INTO user_round (round_id, user_id, score) VALUES (1, 5, 80);
INSERT INTO user_round (round_id, user_id, score) VALUES (1, 6, 85);
INSERT INTO user_round (round_id, user_id, score) VALUES (1, 7, 90);
INSERT INTO user_round (round_id, user_id, score) VALUES (2, 8, 95);
INSERT INTO user_round (round_id, user_id, score) VALUES (2, 10,75);
INSERT INTO user_round (round_id, user_id, score) VALUES (2, 11,85);
INSERT INTO user_round (round_id, user_id, score) VALUES (3, 3, 75);
INSERT INTO user_round (round_id, user_id, score) VALUES (3, 4, 80);
INSERT INTO user_round (round_id, user_id, score) VALUES (3, 5, 75);

