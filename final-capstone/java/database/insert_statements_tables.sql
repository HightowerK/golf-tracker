
INSERT INTO address (address_id, street, city, region, postal, country) VALUES (?,?,?,?,?,?)
INSERT INTO course (course_id, course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES (?,?,?,?,?,?,?,?)
INSERT INTO league (league_id, league_name, score_type, course_id) VALUES (?,?,?,?)
INSERT INTO round (round_id, league_id, course_id, tee_time, tee_date) VALUES (?,?,?,?,?)
INSERT INTO score (score_id, user_round_id, score) VALUES (?,?,?)
INSERT INTO user_info (user_id, first_name, last_name) VALUES (?,?,?)
INSERT INTO user_league (user_id, league_id) VALUES (?,?)
INSERT INTO user_round (user_round_id, round_id, user_id) VALUES (?,?,?)
INSERT INTO users (user_id, username, password_hash, role) VALUES (?,?,?,?)

INSERT INTO address (address_id, street, city, region, postal, country) VALUES (?, ?, ?, ?, ?, ?);
INSERT INTO course (course_id, course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES (?, ?, ?, ?, ?, ?, ?, ?);
INSERT INTO league (league_id, league_name, score_type, course_id) VALUES (?, ?, ?, ?);
INSERT INTO round (round_id, league_id, course_id, tee_time, tee_date) VALUES (?, ?, ?, ?, ?);
INSERT INTO score (score_id, user_round_id, score) VALUES (?, ?, ?);
INSERT INTO user_info (user_id, first_name, last_name) VALUES (?, ?, ?);
INSERT INTO user_league (user_id, league_id) VALUES (?, ?);
INSERT INTO user_round (user_round_id, round_id, user_id) VALUES (?, ?, ?);
INSERT INTO users (user_id, username, password_hash, role) VALUES (?, ?, ?, ?);

