package com.techelevator.controller;

import com.techelevator.model.*;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.time.LocalTime;


public class GolfControllerPKG {

    //handle logic and class mapping

    public Course map_course_by_row(SqlRowSet rs) {

        Course course = new Course();
        Address address = new Address();
        course.setCourse_id(rs.getInt("course_id"));
        course.setCourse_name(rs.getString("course_name"));
        course.setHoles(rs.getInt("holes"));
        course.setYardage(rs.getInt("yardage"));
        course.setSlope(rs.getDouble("slope"));
        course.setRating(rs.getDouble("rating"));
        course.setScore_to_par(rs.getInt("score_to_par"));

        //address
        address.setAddress_id(rs.getInt("address_id"));
        address.setStreet(rs.getString("street"));
        address.setCity(rs.getString("city"));
        address.setRegion(rs.getString("region"));
        address.setPostal(rs.getInt("postal"));
        address.setCountry(rs.getString("country"));

        course.setAddress(address);
        return course;
    }

    public League map_league_by_row(SqlRowSet rs) {
        League league = new League();
        Course course;

        league.setLeague_id(rs.getInt("league_id"));
        league.setLeague_name(rs.getString("league_name"));
        league.setScore_type(rs.getString("score_type"));

        //course mapping includes address
        course = map_course_by_row(rs);
        league.setCourse(course);

        return league;
    }

    public User map_user_by_row(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getLong("user_id"));
        user.setFirst_name(rs.getString("first_name"));
        user.setLast_name(rs.getString("last_name"));
        user.setUsername(rs.getString("username"));
        return user;
    }

    public Round map_round_with_score(SqlRowSet rs) {
        Round round = new Round();
        Score score = new Score();

        round.setRound_id(rs.getInt("round_id"));
        round.setCourse_id(rs.getInt("course_id"));
        round.setTee_date(rs.getDate("tee_date"));

        //league_id can be null
        //Integer leagueID = rs.getInt("league_id");
        round.setLeague_id(rs.getInt("league_id"));
        if(rs.wasNull()){
            round.setLeague_id(0);
        }
        //tee time can be null
//        LocalTime lt = new LocalTime(12,0,0);
//        round.setTee_time(rs.getTime("tee_time").toLocalTime());
//        if(rs.wasNull()){
//            round.setTee_time(LocalTime.parse("12:00:00"));
//        }
        //score can be null
        score.setScore(rs.getInt("score"));
        if(rs.wasNull()){
            score.setScore(0);
        }
        round.setScore(score);

        return round;
    }


}
