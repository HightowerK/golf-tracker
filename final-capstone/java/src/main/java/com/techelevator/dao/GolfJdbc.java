package com.techelevator.dao;

import com.techelevator.controller.GolfControllerPKG;
import com.techelevator.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class GolfJdbc implements GolfDAO {

    private final JdbcTemplate jdbcTemplate;
    private final SQL sql = new SQL();
    private final GolfControllerPKG gcp = new GolfControllerPKG();
    Connection con;

    public GolfJdbc(JdbcTemplate jdbcTemplate) throws SQLException {
        this.jdbcTemplate = jdbcTemplate;
        this.con = jdbcTemplate.getDataSource().getConnection();
    }

    //prep statements and calls to DB
    //mapping logic can be handled separately
    //sql calls can go in their own class

    //untested right now, need sample data in
    @Override
    public List<Course> getAllCourses() {

        List<Course> courseList = new ArrayList<>();
        try {
            SqlRowSet courseResult = jdbcTemplate.queryForRowSet(sql.all_courses());
            while (courseResult.next()) {
                courseList.add(gcp.map_course_by_row(courseResult));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return courseList;
    }

    @Override
    public Course getCourseByID(int course_id) {
        Course course = new Course();
        try {
            SqlRowSet courseByID = jdbcTemplate.queryForRowSet(sql.course_by_id(), course_id);
            if (courseByID.next()) {
                course = gcp.map_course_by_row(courseByID);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return course;
    }

    @Override
    public List<League> getAllLeagues() {
        List<League> leagueList = new ArrayList<>();
        try {
            SqlRowSet leagueResult = jdbcTemplate.queryForRowSet(sql.all_leagues());
            while (leagueResult.next()) {
                //league sql pulls in league, course, and address data
                leagueList.add(gcp.map_league_by_row(leagueResult));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return leagueList;
    }

    public League leagueById(int league_id) {
        League league = new League();
        try {
            SqlRowSet leagueByID = jdbcTemplate.queryForRowSet(sql.league_by_id(), league_id);
            while (leagueByID.next()) {
                league = gcp.map_league_by_row(leagueByID);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return league;
    }

    @Override
    public ScoreBoard scoreBoard(int user_id) {
        ScoreBoard scoreBoard = new ScoreBoard();
        List<Round> roundList = new ArrayList<>();
        try {
            SqlRowSet scores = jdbcTemplate.queryForRowSet(sql.personal_scoreBoard(), user_id);
            boolean set_user = true;
            while (scores.next()) {
                //map user (do this only once?)
                if (set_user) {
                    User user = gcp.map_user_by_row(scores);
                    scoreBoard.setUser(user);
                    set_user = false;
                    //System.out.println("user set successfully");
                }
                //map round -> score inside round
                Round round = gcp.map_round_with_score(scores);
                //add to scoreboard
                roundList.add(round);
            }
            scoreBoard.setRounds(roundList);
        } catch (Exception e) {
            System.out.println(e + " ======= scoreboard");
        }
        return scoreBoard;
    }


    public void league(League league) {

        String leagueName = league.getLeague_name();
        String scoreType = league.getScore_type();
        int courseId = league.getCourse().getCourse_id();

        jdbcTemplate.update(sql.insert_into_league(),leagueName, scoreType, courseId);

    }

    @Override
    public void round(Round round, int id){
        int leagueId = round.getLeague_id();
        int courseId = round.getCourse_id();
        Date date = round.getTee_date();
        LocalTime time = round.getTee_time();

        System.out.println( "jdbcRound 137: " + round.getTee_date());

        //change sql to return round_id
        //prep statement to insert into user_round returned round_id and passed in user_id
        //jdbcTemplate.update(sql.insert_into_round(), leagueId, courseId, time, date);

        try {
            ResultSet rs;
            String[] queries = sql.insert_into_round();
            PreparedStatement prep = con.prepareStatement(queries[0]);
            prep.setInt(1,leagueId);
            prep.setInt(2,courseId);
            //this can throw null pointer
            prep.setTime(3, Time.valueOf(time));
            prep.setDate(4,date);
            rs = prep.executeQuery();
            if(rs.next()){
                round.setRound_id(rs.getInt("round_id"));
            }

            //insert into user_round

            prep = con.prepareStatement(queries[1]);
            prep.setInt(1,id);
            prep.setInt(2,round.getRound_id());
            prep.executeUpdate();
        }catch(SQLException e){
            System.out.println("round: " + e);
        }
    }



    //Leaderboard -> List of Leagues -> List of Users -> List of scores
    @Override
    public LeaderBoard getLeaderBoard(int id){

        LeaderBoard leaderBoard = new LeaderBoard();

        try {
            List<League> leagueList = new ArrayList<>();
            List<Integer> leagues = new ArrayList<>();
            String[] sqlCalls = sql.get_leaderBoard_by_user_id();
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sqlCalls[0], id);
            while(rs.next()){
                leagues.add(rs.getInt("league_id"));
            }

            //loop through each leagueid and store the results
            for(Integer leagueID : leagues){

                List<User> userList = new ArrayList<>();
                League singleLeague = new League();

                //all rounds for that particular league, clear rounds at end of loop
                singleLeague.setLeague_id(leagueID);

                //get all users in league
                List<Long> usersInLeague = new ArrayList<>();
                rs = jdbcTemplate.queryForRowSet(sqlCalls[3],leagueID);
                while(rs.next()){
                    usersInLeague.add(rs.getLong("user_id"));
                    //fix this to only set once
                    singleLeague.setLeague_name(rs.getString("league_name"));
                    singleLeague.setScore_type(rs.getString("score_type"));
                }
                //clear this list out at end of loop

                //store all round id's that have a league_id tag
                List<Integer> rounds = new ArrayList<>();
                rs = jdbcTemplate.queryForRowSet(sqlCalls[1],leagueID);
                while(rs.next()){
                    rounds.add(rs.getInt("round_id"));
                }

                //call for each round_id
                for(Integer roundID : rounds){

                    //calls each round
                    rs = jdbcTemplate.queryForRowSet(sqlCalls[2], roundID);
                    while(rs.next()){
                        //rows of each user_id and score for round
                        //round_id, user_id, score
                        //before instantiation of new user, check if user is already in userList
                        Long currentID = rs.getLong("user_id");
                        boolean addable = true;
                        for(User user : userList){
                            if(user.getId() == currentID){
                                //user is already in list
                                Score score = new Score();
                                score.setScore(rs.getInt("score"));
                                user.addScore(score);
                                addable = false;
                            }
                        }
                        if(addable) {
                            User user = new User();
                            Score score = new Score();
                            user.setId(rs.getLong("user_id"));
                            user.setUsername(rs.getString("username"));
                            user.setFirst_name(rs.getString("first_name"));
                            user.setLast_name(rs.getString("last_name"));
                            score.setScore(rs.getInt("score"));
                            user.addScore(score);

                            //check usersLeague list to see if current user is in league
                            //only add to userList if a match
                            for (Long userID : usersInLeague) {
                                if (userID == (user.getId())) {
                                    userList.add(user);
                                }
                            }
                        }
                    }
                    //System.out.println("sql[2] success");
                    //all users and scores are stored in userlist
                    //add userlist to single league
                }
                //add single league to leaguelist
                singleLeague.setUserList(userList);
                leagueList.add(singleLeague);
            }

            //after going through every league_id
            leaderBoard.setLeagueList(leagueList);

        }catch(Exception e){
            System.out.println(e);
        }
        return leaderBoard;
    }

    public void course(Course course) {
        String courseName = course.getCourse_name();
        int holes = course.getHoles();
        int yardage = course.getYardage();
        double slope = course.getSlope();
        double rating = course.getRating();
        int scoreToPar = course.getScore_to_par();
        int addressId = 0;

        //need to drop address in first and get address_id back;
        ResultSet result;
        PreparedStatement prep;
        try{
            prep = con.prepareStatement(sql.insert_into_address());
            prep.setString(1,course.getAddress().getStreet());
            prep.setString(2,course.getAddress().getCity());
            prep.setString(3,course.getAddress().getRegion());
            prep.setInt(4,course.getAddress().getPostal());
            prep.setString(5,course.getAddress().getCountry());
            result = prep.executeQuery();
            if(result.next()){
                addressId = result.getInt("address_id");
            }
        }catch(SQLException e){
            System.out.println(e + " course exception");
        }

        jdbcTemplate.update(sql.insert_into_course(),courseName, holes, addressId, yardage, slope, rating, scoreToPar);
    }

    @Override
    public void addScoreToSelf(int id, Round round) {
        //round comes in with date, course_id, score
        //insert into round date and course_id, returning round_id
        //insert into user_round, user_id, returned round_id, and score
        String[] queries = sql.add_score_to_self();
        PreparedStatement prep;
        ResultSet result;
        try {
            System.out.println(round.getTee_date());
            prep = con.prepareStatement(queries[0]);
            prep.setInt(1,round.getCourse_id());
            prep.setDate(2,round.getTee_date());
            //SqlRowSet result2 = jdbcTemplate.update(queries[0],round.getCourse_id(),round.getTee_date());
            result = prep.executeQuery();
            int returnedRoundID = 0;
            if(result.next()){
                //store round_id that comes back
                returnedRoundID = result.getInt("round_id");
            }
            prep = con.prepareStatement(queries[1]);
            prep.setInt(1,returnedRoundID);
            prep.setInt(2,id);
            prep.setInt(3,round.getScore().getScore());
            prep.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public void addScoreToUser(int id, Round round) {
        //round comes in with date, course_id, score
        //insert into round date and course_id, returning round_id
        //TODO pulling from add league scores, need league_id as well
        //insert into user_round, user_id, returned round_id, and score
        String[] queries = sql.add_score_to_user();
        PreparedStatement prep;
        ResultSet result;
        try {
            System.out.println(round.getTee_date());
            prep = con.prepareStatement(queries[0]);
            prep.setInt(1,round.getCourse_id());
            prep.setDate(2,round.getTee_date());
            prep.setInt(3,round.getLeague_id());
            //SqlRowSet result2 = jdbcTemplate.update(queries[0],round.getCourse_id(),round.getTee_date());
            result = prep.executeQuery();
            int returnedRoundID = 0;
            if(result.next()){
                //store round_id that comes back
                returnedRoundID = result.getInt("round_id");
            }
            prep = con.prepareStatement(queries[1]);
            prep.setInt(1,returnedRoundID);
            prep.setInt(2,id);
            prep.setInt(3,round.getScore().getScore());
            prep.executeUpdate();
        }catch(SQLException e){
            System.out.println(e);
        }
    }


    @Override
    public List<User> allUsers(){
        String query = sql.all_users();
        SqlRowSet users = jdbcTemplate.queryForRowSet(query);
        List<User> userList = new ArrayList<>();
        while(users.next()){
            User user = new User();
            user = gcp.map_user_by_row(users);
            userList.add(user);
        }
        return userList;
    }

    @Override
    public ResponseEntity addUserToLeague(int user_id, int league_id) {
        String addUser = sql.add_user_to_league();
        try{
            PreparedStatement prep = con.prepareStatement(addUser);
            prep.setInt(1,user_id);
            prep.setInt(2,league_id);
            prep.executeUpdate();
        }catch(SQLException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity upcomingRounds(int user_id) {
        String query = sql.upcoming_rounds();
        List<UpcomingRounds> ucl = new ArrayList<>();
        try{
            SqlRowSet rs = jdbcTemplate.queryForRowSet(query,user_id);
            while(rs.next()){
                Course course = new Course();
                Round round = new Round();
                //courseINFO
                //course_name,course_id,holes,yardage,slope,rating,score_to_par
                //course --- ignore address for now, really all NEED is course name
                //roundINFO
                //round_id,tee_time,tee_date,league_id,course_id
                //course NEED tee_time,tee_date : rest is good but optional if it comes to it

                course.setCourse_id(rs.getInt("course_id"));
                course.setCourse_name(rs.getString("course_name"));
                course.setHoles(rs.getInt("holes"));
                course.setYardage(rs.getInt("yardage"));
                course.setSlope(rs.getDouble("slope"));
                course.setRating(rs.getDouble("rating"));
                course.setScore_to_par(rs.getInt("score_to_par"));
                //ignore address

                round.setRound_id(rs.getInt("round_id"));
                round.setLeague_id(rs.getInt("league_id"));
                round.setCourse_id(rs.getInt("course_id"));
                Time tee_time = rs.getTime("tee_time");
                if(tee_time != null){
                    round.setTee_time(tee_time.toLocalTime());
                }
                //round.setTee_time(rs.getTime("tee_time").toLocalTime());
                round.setTee_date(rs.getDate("tee_date"));
                UpcomingRounds ur = new UpcomingRounds();
                ur.setRound(round);
                ur.setCourse(course);
                ucl.add(ur);
            }

        }catch(Exception e){
            //null pointer?
            System.out.println("upcoming rounds: " + e);
            return new ResponseEntity(HttpStatus.I_AM_A_TEAPOT);
        }


        return new ResponseEntity(ucl,HttpStatus.OK);
    }
}
