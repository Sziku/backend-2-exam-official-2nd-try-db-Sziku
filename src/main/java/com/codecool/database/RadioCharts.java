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
        final String SQL = "SELECT artist from music_broadcast group by artist order by artist ASC;";

        try(Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword)){
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();

            List<String> artist = new ArrayList<>();

            while (rs.next()){
                artist.add(rs.getString(1));
            }
            return artist;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getMostPlayedSong() {
        final String SQL = "SELECT song, times_aired from music_broadcast group by song, times_aired order by times_aired DESC limit 1; ";

        try(Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword)){
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();


            while (rs.next()){
                return rs.getString(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getMostActiveArtist() {
        final String SQL = "select artist, count(artist) as mostSong from music_broadcast group by artist order by count(artist) limit 1";

        try(Connection con = DriverManager.getConnection(dbUrl,dbUser,dbPassword)){
            PreparedStatement st = con.prepareStatement(SQL);
            ResultSet rs = st.executeQuery();


            while (rs.next()){
                return rs.getString(1);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
}