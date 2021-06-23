--run scripts to test sql statements

--used for league_by_id
select *
from league
join course on course.course_id = league.course_id
join address on course.address_id = course.address_id
where league.league_id = ? limit 1;

--come back to this
select distinct * 
from user_round 
join users on users.user_id = user_round.user_id
join user_league on users.user_id = user_league.user_id
join league on user_league.league_id = league.league_id
join round on user_round.round_id = round.round_id
join course on course.course_id = round.course_id
where user_league.league_id = 2;

select *
from user_round
join round on round.round_id = user_round.round_id
join users on user_round.user_id = users.user_id
where user_round.user_id = 5;


--one gigantic join just filtering the info we want

select *
from user_league
join users on users.user_id = user_league.user_id
join league on league.league_id = user_league.league_id
join user_round on user_round.user_id = users.user_id
join round on user_round.round_id = round.round_id
where user_league.user_id = 5;

select * from user_round
join round on round.round_id = user_round.round_id
join users on user_round.user_id = users.user_id
join user_league on user_league.user_id = users.user_id
join league on league.league_id = user_league.league_id
where user_league.league_id = 1;

insert into user_league(user_id,league_id) values (5,1);
--need all the leagues the user is in and their scores grouped

--this doesnt work either, I need ALL users that matches user_id for the league.
--FUCK
select * 
from users
join user_round on user_round.user_id = users.user_id
join user_league on user_league.user_id = users.user_id
join league on league.league_id = user_league.league_id
where league.league_id = 2;

--split it into multiple calls, idk any other way

--get league id's for user
select league_id from user_league where user_id = 4;
--loop through league ID's

--store user_id's and use that for calls? 
select user_id, league_name, score_type from user_league
join league on league.league_id = user_league.league_id where user_league.league_id = 2;

--get all rounds played by league
select *
from round
where league_id = 2;
--loop through round_ids

--get user_id and score from results
--need to filter out users that are not in user_league
select user_round.user_id, user_round.score, users.username, users.first_name, users.last_name from user_round
join users on user_round.user_id = users.user_id
where round_id = ?;

--in this example only users 3 and 5 are in the selected league

update users set role = 'ADMIN' where username = 'admin';
update users set role = 'GOLFER' where username = 'user';


insert into league(league_name,score_type,course_id) values ('postgres ftw', 'why isnt this working', 25);

insert into user_round(round_id,user_id) values(5,5);

--get all rounds that do not have a score
select * from user_round where user_id = 5 and score is null;
--returns round_id's

--take round ID's from first call
--get course information about that round
--loop through and add round
select * from round where round.round_id = 5;

delete from user_round where user_id = 5 and score is null;

insert into round(course_id, tee_date) values (15,'2021-10-10') returning round_id;

select * 
from user_round
join users on users.user_id = user_round.user_id
join round on user_round.round_id = round.round_id
where user_round.user_id = 12;

select * from users
where first_name is not null;

select * from user_round where score is null;

select distinct(course.course_name), *
from round 
join user_round on round.round_id = user_round.round_id
join course on round.course_id = course.course_id
where tee_date > now() and user_round.user_id = 12;

delete from user_round where round_id = 16;


