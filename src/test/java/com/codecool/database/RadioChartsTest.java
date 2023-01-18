package com.codecool.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RadioChartsTest {

    private static final String DB_URL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private RadioCharts radioCharts;

    @BeforeEach
    void init() {
        radioCharts = new RadioCharts(DB_URL, DB_USER, DB_PASSWORD);
        initTables();
    }

    @Test
    void getArtists_emptyDatabase_emptyList() {
        assertTrue(radioCharts.getArtists().isEmpty());
    }

    @Test
    void getArtists_everySongOnce_emptyList() {
        createDummyData();
        assertEquals(List.of("Chris Isaak", "Son Lux"), radioCharts.getArtists());
    }

    @Test
    void getArtists_oneSongTwice_emptyList() {
        createDummyData();
        addExistingSong();
        assertEquals(List.of("Chris Isaak", "Son Lux"), radioCharts.getArtists());
    }

    @Test
    void getArtists_oneSongTwiceInDb_emptyList() {
        createDummyData();
        addNewSongNewArtistMostPlayed();
        assertEquals(List.of("Chris Isaak", "Radiohead", "Son Lux"), radioCharts.getArtists());
    }

    @Test
    void getMostPlayedSong_emptyDatabase_emptyString() {
        assertEquals("", radioCharts.getMostPlayedSong());
    }

    @Test
    void getMostActiveArtist_emptyDatabase_emptyString() {
        assertEquals("", radioCharts.getMostActiveArtist());
    }

    @Test
    void getMostPlayedSong_everySongOnce_firstSongAlphabetically() {
        createDummyData();
        assertEquals("Let Me Down Easy", radioCharts.getMostPlayedSong());
    }

    @Test
    void getMostActiveArtist_everyArtistOnce_firstArtistAlphabetically() {
        createDummyData();
        assertEquals("Chris Isaak", radioCharts.getMostActiveArtist());
    }

    @Test
    void getMostActiveArtist_oneSongTwice_firstArtistAlphabetically() {
        createDummyData();
        addExistingSong();
        assertEquals("Chris Isaak", radioCharts.getMostActiveArtist());
    }

    @Test
    void getMostPlayedSong_oneSongTwiceInDb_twicePlayedSong() {
        createDummyData();
        addExistingSong();
        assertEquals("Ransom", radioCharts.getMostPlayedSong());
    }

    @Test
    void getMostPlayedSong_oneSongTwicePlayed_twicePlayedSong() {
        createDummyData();
        addNewSongNewArtistMostPlayed();
        assertEquals("You", radioCharts.getMostPlayedSong());
    }

    @Test
    void getMostActiveArtist_oneArtistTwice_artistWithTwoSongs() {
        createDummyData();
        addNewSongExistingArtist();
        assertEquals("Son Lux", radioCharts.getMostActiveArtist());
    }

    @AfterEach
    void destruct() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "DROP TABLE IF EXISTS music_broadcast";
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void initTables() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = """
                    CREATE TABLE IF NOT EXISTS music_broadcast (
                    artist VARCHAR(255),
                    song VARCHAR(255),
                    times_aired INT
                    );
                    """;
            Statement statement = connection.createStatement();
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void insert(Connection con, String artist, String song, int aired) {
        try (PreparedStatement stmt = con.prepareStatement("INSERT INTO music_broadcast VALUES (?, ?, ?)")) {
            stmt.setString(1, artist);
            stmt.setString(2, song);
            stmt.setInt(3, aired);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    void createDummyData() {
        try (Connection connection = getConnection()) {
            insert(connection, "Chris Isaak", "Let Me Down Easy", 1);
            insert(connection, "Son Lux", "Ransom", 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    void addExistingSong() {
        try (Connection connection = getConnection()) {
            insert(connection, "Son Lux", "Ransom", 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addNewSongExistingArtist() {
        try (Connection connection = getConnection()) {
            insert(connection, "Son Lux", "Easy", 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addNewSongNewArtistMostPlayed() {
        try (Connection connection = getConnection()) {
            insert(connection, "Radiohead", "You", 2);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
