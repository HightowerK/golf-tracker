package com.techelevator.model;

import java.util.List;

public class LeaderBoard {

    //list of league -> league has list of users -> users has list of scores

    List<League> leagueList;

    public List<League> getLeagueList() {
        return leagueList;
    }

    public void setLeagueList(List<League> leagueList) {
        this.leagueList = leagueList;
    }


}
