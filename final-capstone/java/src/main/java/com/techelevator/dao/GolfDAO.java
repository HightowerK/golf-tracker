package com.techelevator.dao;

import com.techelevator.model.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GolfDAO {

    List<Course> getAllCourses();
    Course getCourseByID(int course_id);
    List<League> getAllLeagues();
    League leagueById(int league_id);
    ScoreBoard scoreBoard(int user_id);
    void league(League league);
    LeaderBoard getLeaderBoard(int id);
    void round(Round round, int id);
    void course(Course course);
    void addScoreToSelf(int id, Round round);
    void addScoreToUser(int id, Round round);
    List<User> allUsers();
    ResponseEntity addUserToLeague(int user_id,int league_id);
    ResponseEntity upcomingRounds(int user_id);
    //ScoreBoard unscoredRounds(int id);
}
