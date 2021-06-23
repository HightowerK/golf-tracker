package com.techelevator.dao;

public class SQL {

    //statements to be passed back to jdbc that calls it

    public String all_courses() {
        String sql = "select * from course " +
                "left join address on address.address_id = course.address_id";
        return sql;
    }

    public String course_by_id() {
        String sql = "select * from course " +
                "left join address on address.address_id = course.address_id " +
                "where course_id = ? limit 1";
        return sql;
    }

    public String all_leagues() {
        String sql = "select * from league " +
                "left join course on league.course_id = course.course_id " +
                "left join address on course.address_id = address.address_id";
        return sql;
    }

    public String league_by_id(){
        String sql = "select *\n" +
                "from league\n" +
                "join course on course.course_id = league.course_id\n" +
                "join address on course.address_id = course.address_id\n" +
                "where league.league_id = ? limit 1";
        return sql;
    }

    public String personal_scoreBoard(){
        String sql = "select *\n" +
                "from user_round\n" +
                "join round on round.round_id = user_round.round_id\n" +
                "join users on user_round.user_id = users.user_id\n" +
                "where user_round.user_id = ? and tee_date < now()";
        return sql;
    }
     public String insert_into_league(){
        //if league_id is needed later concat RETURNING league_id to end of this statement
        String sql = "INSERT INTO league (league_name, score_type, course_id) VALUES (?, ?, ?)";
        return sql;
     }

    public String[] get_leaderBoard_by_user_id(){
        //will be multiple calls
        String[] sql = new String[4];
        //user id input -> returns league_id's
        sql[0] = "select league_id from user_league where user_id = ?";

        //get user_id's from league as well, only loop through users that appear in list

        sql[3] = "select user_id, league_name, score_type from user_league " +
                "join league on league.league_id = user_league.league_id " +
                "where user_league.league_id = ?";

        //take league id from above, insert and returns round_id's
        sql[1] = "select *\n" +
                "from round\n" +
                "where league_id = ?";

        //take round_id from above, returns user_id and score
        sql[2] = "select user_round.user_id, user_round.score, users.username, users.first_name, users.last_name from" +
                " user_round\n" +
                "join users on user_round.user_id = users.user_id\n" +
                "where round_id = ?";

        return sql;
    }

    public String[] insert_into_round(){
        String[] sql = new String[2];
        sql[0] = "INSERT INTO round (league_id, course_id, tee_time, tee_date) VALUES (?, ?, ?, ?) returning round_id";
        sql[1] = "insert into user_round (user_id,round_id) values(?,?)";
        return sql;
    }

    public String insert_into_course(){
        String sql = "INSERT INTO course (course_name, holes, address_id, yardage, slope, rating, score_to_par) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return sql;
    }

    public String insert_into_address(){
        String sql = "INSERT INTO address (street, city, region, postal, country) VALUES (?,?,?,?,?) returning address_id";
        return sql;
    }

    public String[] get_no_score_rounds_from_user(){
        String[] sql = new String[2];
        //get all rounds that do not have a score, take round ID and loop through with second call
        sql[0] = "select * from user_round where user_id = ? and score is null returning round_id";
        //looped round ID's, hydrate a round object and add to scoreboard object to return
        sql[1] = "select * from round where round.round_id = ?";
        return sql;
    }


    //not done
    public String[] add_score_to_self(){
        String[] sql = new String[2];

        sql[0] = "insert into round (course_id,tee_date) values(?,?) returning round_id";
        sql[1] = "insert into user_round (round_id,user_id,score) values (?,?,?)";
        return sql;
    }

    //not done
    public String[] add_score_to_user(){
        String[] sql = new String[2];

        sql[0] = "insert into round (course_id,tee_date,league_id) values(?,?,?) returning round_id";
        sql[1] = "insert into user_round (round_id,user_id,score) values (?,?,?)";
        return sql;
    }

    public String all_users(){
        String sql = "select * from users where first_name is not null";
        return sql;
    }

    public String add_user_to_league(){
        String sql = "insert into user_league (user_id,league_id) " +
                "values(?,?)";
        return sql;
    }

    public String upcoming_rounds(){
        String sql = "select distinct(course.course_name), *\n" +
                "from round \n" +
                "join user_round on round.round_id = user_round.round_id\n" +
                "join course on round.course_id = course.course_id\n" +
                "where tee_date > now() and user_round.user_id = ?;";
        return sql;
    }
}
