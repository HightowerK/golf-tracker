package com.techelevator.controller;

import com.fasterxml.jackson.databind.deser.DataFormatReaders;
import com.techelevator.dao.GolfDAO;
import com.techelevator.model.Course;
import com.techelevator.model.League;
import com.techelevator.model.Round;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
//TODO authorization for methods
public class RequestController {

    //handle request mapping, send all objects and requests to jdbc controller
    //login and register are handled in authentication controller

    @Autowired
    GolfDAO golfDAO;

    //no function, just tests connectivity
    @RequestMapping(path = "/test", method = RequestMethod.GET)
    public void test_server_connection() {
        System.out.println(HttpStatus.OK);
    }

    //all authorized
    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN','GOLFER')")
    @RequestMapping(path = "/courses", method = RequestMethod.GET)
    public ResponseEntity allCourses() {
        if (golfDAO.getAllCourses().size() == 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(golfDAO.getAllCourses(), HttpStatus.OK);
        }
    }

    //all authorized
    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN','GOLFER')")
    @RequestMapping(path = "/courses/{id}", method = RequestMethod.GET)
    public ResponseEntity getCourseByID(@PathVariable("id") int id) {
        if (golfDAO.getCourseByID(id).getCourse_id() == 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(golfDAO.getCourseByID(id), HttpStatus.OK);
        }
    }

    //all leagues authorization restricted to admin/league admin
    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN', 'GOLFER')")
    @RequestMapping(path = "/leagues", method = RequestMethod.GET)
    public ResponseEntity allLeagues() {
        if (golfDAO.getAllLeagues().size() == 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(golfDAO.getAllLeagues(), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN','GOLFER')")
    @RequestMapping(path = "/leagues/{id}", method = RequestMethod.GET)
    public ResponseEntity leagueByID(@PathVariable("id") int id) {
        if (golfDAO.leagueById(id).getLeague_id() == 0) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity(golfDAO.leagueById(id), HttpStatus.OK);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN','GOLFER')")
    @RequestMapping(path = "/scoreboard/{id}", method = RequestMethod.GET)
    public ResponseEntity scoreBoardByID(@PathVariable("id") int id) {
        return new ResponseEntity(golfDAO.scoreBoard(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN')")
    @RequestMapping(path = "/leagues", method = RequestMethod.POST)
    public ResponseEntity addLeagues(@RequestBody League league) {
        golfDAO.league(league);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN','GOLFER')")
    @RequestMapping(path = "/leaderboard/{id}", method = RequestMethod.GET)
    public ResponseEntity displayLeaderBoard(@PathVariable("id") int id) {
        return new ResponseEntity(golfDAO.getLeaderBoard(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN','GOLFER')")
    @RequestMapping(path = "/schedule-round/{user_id}", method = RequestMethod.POST)
    public ResponseEntity addRound(@RequestBody Round round, @PathVariable("user_id") int id) {
        golfDAO.round(round,id);
        return new ResponseEntity(HttpStatus.CREATED);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(path = "/courses", method = RequestMethod.POST)
    public ResponseEntity addCourse(@RequestBody Course course) {
        golfDAO.course(course);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN','GOLFER')")
    @RequestMapping(path="/round/{id}", method = RequestMethod.POST)
    public ResponseEntity addScoreToSelf(@PathVariable("id") int id, @RequestBody Round round){
        golfDAO.addScoreToUser(id,round);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN','GOLFER')")
    @RequestMapping(path="/all-users", method = RequestMethod.GET)
    public ResponseEntity all_users(){
        return new ResponseEntity(golfDAO.allUsers(),HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN')")
    @RequestMapping(path="/user-league/{user_id}/{league_id}", method = RequestMethod.POST)
    public ResponseEntity addUserToLeague(@PathVariable("user_id")int user_id,
                                          @PathVariable("league_id")int league_id){
        return golfDAO.addUserToLeague(user_id,league_id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','LEAGUE_ADMIN','GOLFER')")
    @RequestMapping(path="/upcoming/{user_id}", method = RequestMethod.GET)
    public ResponseEntity upcomingRounds(@PathVariable("user_id")int user_id){
        //return list of packaged round/course object
        return golfDAO.upcomingRounds(user_id);
    }

}
