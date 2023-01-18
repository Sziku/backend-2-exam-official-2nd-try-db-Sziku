package com.codecool.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RadioCharts {
    private final String dbUrl;
    private final String dbUser;
    private final String dbPassword;

    public RadioCharts(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }


    public List<String> getArtists() {
    }

    public String getMostPlayedSong() {
    }

    public String getMostActiveArtist() {
    }
}