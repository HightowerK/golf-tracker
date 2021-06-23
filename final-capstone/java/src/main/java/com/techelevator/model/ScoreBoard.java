package com.techelevator.model;

import java.util.List;

public class ScoreBoard {

    private User user;
    List<Round> rounds; //each includes score

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

}
