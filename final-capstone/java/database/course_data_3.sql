BEGIN TRANSACTION;
INSERT INTO address ( street, city, postal, region, country) VALUES ('14700 s.82 ave','Orland Park',60462,'IL','USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('13070 McCarthy Rd.','Palos Park', 60464, 'IL', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('800 Kedzie Avenue','Flossmoor',60422, 'IL', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('One Mulligan Drive','Woodridge', 60517, 'IL', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('16310 S. Central Ave','Oak Forest',60452, 'IL', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('16325 Broken Arrow Dr.', 'Lockport', 60441, 'IL', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('7205 W. 115th St.', 'Worth',60482, 'IL', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('2241 IL Rte 59','West Chicago',60185, 'IL', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('3500 Midwest Rd','Oak Brook',60523, 'IL', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('6n001 Medinah Rd', 'Medinah',60157, 'IL', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('1201 Club House Dr.', 'Chesapeake',23322, 'VA', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('2973 Heron Ridge Dr.', 'Virginia Beach', 23456, 'VA', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('2700 Atwoodtown Rd', 'Virginia Beach', 23456, 'VA', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('2500 S. Independence Blvd.', 'Virginia Beach', 23456, 'VA', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('1144 Prospering Rd', 'Virginia Beach', 23451, 'VA', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('1 Bide-A-Wee Ln', 'Portsmouth', 23701, 'VA', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('4797 Indian River Rd', 'Virginia Beach', 23456, 'VA', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('4700 Sleepy Hole Rd', 'Suffolk', 23435, 'VA', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('660 Ruthven Rd', 'Norfolk', 23505, 'VA', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('5340 Club Head Rd', 'Virginia Beach', 23455, 'VA', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('12227 Sandborn Rd', 'Portland', 48875, 'MI', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('36000 W. Seven Mile Rd', 'Livonia', 48152, 'MI', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('30500 W. 13 Mile Rd', 'Farmington Hills', 48334, 'MI', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('27925 Golf Pointe Blvd.', 'Farmington Hills', 48331, 'MI', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('2666 W. 14 Mile Rd', 'Birmingham', 48009, 'MI', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('2036 Rochester Rd', 'Royal Oak', 48073, 'MI', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('25501 Johns Rd', 'South Lyon', 48178, 'MI', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('5000 W. Shore Dr', 'West Bloomfield', 48178, 'MI', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('1960 Stonycroft Ln', 'Bloomfield Hills', 48304, 'MI', 'USA');
INSERT INTO address ( street, city, postal, region, country) VALUES ('40941 Eight Mile Rd', 'Northville', 48167, 'MI', 'USA');

INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Silver Lakes', 18, 1, 5948, 122, 68.6, 70);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Glen Eagles', 18, 2, 5990, 122, 68.8, 70);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Coyote Run', 18, 3, 5900, 118, 67.0, 71);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Seven Bridges', 18, 4, 6306, 135, 70.7, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('George W. Dunn National', 18, 5, 6148, 130, 70.3, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Broken Arrow', 18, 6, 6348, 130, 70.7, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Waters Edge', 18, 7, 6904, 130, 74.6, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('St. Andrews', 18, 8, 6418, 120, 70.2, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Willow Crest', 18, 9, 6433, 134, 72.8, 70);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Medinah C.C.', 18, 10, 6213, 129, 70.2, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Chesapeake', 18, 11, 5670, 120, 67.4, 71);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Heron Ridge', 18, 12, 7017, 135, 73.0, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Hells Point', 18, 13, 6776, 122, 69.7, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('HoneyBee', 18, 14, 5609, 121, 67.9, 70);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Red Wing Lake', 18, 15, 6133, 115, 69.3, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Bide-A-Wee', 18, 16, 6940, 130, 73.5, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Stumpy Lake', 18, 17, 6374, 123, 69.6, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Sleepy Hole', 18, 18, 5950, 121, 70.0, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Sewells Point', 18, 19, 5969, 117, 68.4, 71);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Cypress Point', 18, 20, 6210, 118, 69.0, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Willow Wood', 18, 21, 5547, 108, 66.0, 71);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Fox Creek', 18, 22, 6134, 118, 69.7, 71);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Glen Oaks', 18, 23, 5714, 134, 73.7, 70);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Copper Creek', 9, 24, 2912, 122, 33.9, 35);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Lincoln Hills', 9, 25, 2847, 112, 66.8, 35);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Red Run', 18, 26, 6195, 129, 70.5, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Walnut Creek', 18, 27, 6011, 121, 68.8, 72);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Orchard Lake', 18, 28, 6464, 130, 71.5, 71);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Stonycroft Hills', 9, 29, 2766, 124, 67.4, 35);
INSERT INTO course(course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES('Meadowbrook', 18, 30, 7026, 142, 74.8, 72);

COMMIT;

--UPDATE address SET region = 'IL' WHERE address_id= 10;