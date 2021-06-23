package com.techelevator.model;

import java.sql.Date;
import java.time.LocalTime;

public class Round {

    private int round_id;
    private int league_id;
    private int course_id;
    private LocalTime tee_time;
    private Date tee_date;

    //only for scoreboard usage
    private Score score;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public int getRound_id() {
        return round_id;
    }

    public void setRound_id(int round_id) {
        this.round_id = round_id;
    }

    public int getLeague_id() {
        return league_id;
    }

    public void setLeague_id(int league_id) {
        this.league_id = league_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public LocalTime getTee_time() {
        return tee_time;
    }

    public void setTee_time(LocalTime tee_time) {
        this.tee_time = tee_time;
    }

    public Date getTee_date() {
        return tee_date;
    }

    public void setTee_date(Date tee_date) {
        this.tee_date = tee_date;
    }
}
