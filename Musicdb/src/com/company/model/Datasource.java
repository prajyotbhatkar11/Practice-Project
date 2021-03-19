package com.company.model;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;

import java.io.PipedReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

public class Datasource {
    public static final String DB_NAME = "music.db";
    public static final String CONNECTION = "jdbc:sqlite:C:\\Users\\prajy\\IdeaProjects\\Musicdb\\" + DB_NAME;
    public static final String TABLE_ALBUMS = "albums";
    public static final String COLUMN_ALBUM_ID = "_id";
    public static final String COLUMN_ALBUM_NAME = "name";
    public static final String COLUMN_ALBUM_ARTIST = "artist";

    public static final int INDEX_ALBUM_ID = 1;
    public static final int INDEX_ALBUM_NAME = 2;
    public static final int INDEX_ALBUM_ARTIST = 3;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COLUMN_ARTISTS_ID = "_id";
    public static final String COLUMN_ARTISTS_NAME = "name";

    public static final int INDEX_ARTISTS_ID = 1;
    public static final int INDEX_ARTISTS_NAME = 2;

    public static final String TABLE_SONGS = "songs";
    public static final String COLUMN_SONGS_ID = "_id";
    public static final String COLUMN_SONGS_TRACK = "track";
    public static final String COLUMN_SONGS_TITLE = "title";
    public static final String COLUMN_SONGS_ALBUM = "album";
    public static final int INDEX_SONGS_ID = 1;
    public static final int INDEX_SONGS_Track = 2;
    public static final int INDEX_SONGS_TITLE = 3;
    public static final int INDEX_SONGS_ALBUM = 4;

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    // QUERY SECTION
    public static final String QUERY_ALBUMS_BY_ARTIST_START = "SELECT " + TABLE_ARTISTS + "." + COLUMN_ALBUM_NAME +
            " FROM " + TABLE_ALBUMS +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "."
            + COLUMN_ARTISTS_ID + " WHERE " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + " = \"";

    public static final String QUERY_ALBUMS_BY_ARTIST_SORT = " ORDER BY " + TABLE_ALBUMS + "."
            + COLUMN_ALBUM_NAME + " COLLATE NOCASE ";

    public static final String QUERY_ARTIST_FOR_SONG_START = "SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME
            + " , " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " , " + TABLE_SONGS + "." + COLUMN_SONGS_TRACK + " FROM " + TABLE_SONGS
            + " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONGS_ALBUM + " = " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ID
            + " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID
            + " WHERE " + TABLE_SONGS + "." + COLUMN_SONGS_TITLE + " =\"";

    public static final String QUERY_ARTIST_FOR_SONG_START_ORDER_BY =
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + " COLLATE NOCASE ";


    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";
    public static final String CREATE_ARTIST_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " + TABLE_ARTIST_SONG_VIEW +
            " AS SELECT " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME + " , " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME + " AS " + TABLE_ALBUMS + ", "
            + TABLE_SONGS + "." + COLUMN_SONGS_TRACK + " , " + TABLE_SONGS + "." + COLUMN_SONGS_TITLE + " FROM " + TABLE_SONGS
            + " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COLUMN_SONGS_ALBUM + " = " +
            TABLE_ALBUMS + "." + COLUMN_ALBUM_ID
            + " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COLUMN_ALBUM_ARTIST + " = "
            + TABLE_ARTISTS + "." + COLUMN_ARTISTS_ID +
            " ORDER BY " + TABLE_ARTISTS + "." + COLUMN_ARTISTS_NAME +
            " , " + TABLE_ALBUMS + "." + COLUMN_ALBUM_NAME +
            " , " + TABLE_SONGS + "." + COLUMN_SONGS_TRACK;

    public static final String QUERY_VIEW_SONG_INFO = " SELECT " + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_NAME +
            ", " + COLUMN_SONGS_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONGS_TITLE + " = \"";

    public static final String QUERY_ARTIST_FOR_SONG_PREP = " SELECT " + COLUMN_ALBUM_NAME + ", " + COLUMN_ALBUM_NAME +
            ", " + COLUMN_SONGS_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + COLUMN_SONGS_TITLE + " = ?";

    public static final String INSERT_ARTIST = "INSERT INTO " + TABLE_ARTISTS + "(" + COLUMN_ARTISTS_NAME + ") VALUES(?)";

    public static final String INSERT_ALBUMS = "INSERT INTO " + TABLE_ALBUMS + "(" + COLUMN_ARTISTS_NAME + ", " + COLUMN_ALBUM_ARTIST + ") VALUES(? ,?)";

    public static final String INSERT_SONGS = "INSERT INTO " + TABLE_SONGS + "(" + COLUMN_SONGS_TRACK + ", " + COLUMN_SONGS_TITLE + ", " + COLUMN_SONGS_ALBUM +
            ") VALUES(?, ?, ?)";
    public static final String QUERY_ARTIST = "SELECT "+COLUMN_ARTISTS_ID+" FROM "+TABLE_ARTISTS+ " WHERE "+COLUMN_ARTISTS_NAME+" = ?";

    public static final String QUERY_ALBUMS = "SELECT "+COLUMN_ALBUM_ID+" FROM "+TABLE_ALBUMS+ " WHERE "+COLUMN_ALBUM_NAME+" = ?";
    //Prepared Statements
    private PreparedStatement queryViewSongInfo;
    private PreparedStatement insertIntoArtist;
    private PreparedStatement insertIntoAlbums;
    private PreparedStatement insertIntoSongs;
    private PreparedStatement queryARTIST;
    private PreparedStatement queryALBUMS;
    private Connection conn;

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION);
            queryViewSongInfo = conn.prepareStatement(QUERY_ARTIST_FOR_SONG_PREP);
            insertIntoArtist = conn.prepareStatement(INSERT_ARTIST,Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbums = conn.prepareStatement(INSERT_ALBUMS,Statement.RETURN_GENERATED_KEYS);
            insertIntoSongs = conn.prepareStatement(INSERT_SONGS);
            queryARTIST = conn.prepareStatement(QUERY_ARTIST);
            queryALBUMS = conn.prepareStatement(QUERY_ALBUMS);
            return true;
        } catch (SQLException e) {
            System.out.println("Could not connect to Database" + DB_NAME + ": " + e.getMessage());
            return false;
        }
    }

    public void stop() {
        try {
            if (queryViewSongInfo != null)
                queryViewSongInfo.close();
            if (insertIntoArtist != null)
                insertIntoArtist.close();
            if (insertIntoAlbums!=null)
                insertIntoAlbums.close();
            if (insertIntoSongs !=null)
                insertIntoSongs.close();
            if (queryARTIST !=null)
                queryARTIST.close();
            if (queryALBUMS !=null)
                queryALBUMS.close();
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Something Went Wrong" + e.getMessage());
        }
    }

    public List<Artists> queryArtist(int sortOrder) {
        StringBuilder sb = new StringBuilder("SELECT * FROM ");
        sb.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(" ORDER BY ");
            sb.append(COLUMN_ARTISTS_NAME);
            sb.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC)
                sb.append(" DESC ");
            else
                sb.append(" ASC ");
        }


        try (Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sb.toString())) {
            List<Artists> artists = new ArrayList<>();
            while (result.next()) {
                Artists artist = new Artists();
                artist.setId(result.getInt(INDEX_ARTISTS_ID));
                artist.setName(result.getString(INDEX_ARTISTS_NAME));
                artists.add(artist);
            }
            return artists;

            //artist.stream().forEach(result.next() -> {})
        } catch (SQLException e) {
            System.out.println("Query Failed " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAlbumsForArtist(String artistName, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_ALBUMS_BY_ARTIST_START);
        sb.append(artistName);
        sb.append("\"");
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ALBUMS_BY_ARTIST_SORT);
            if (sortOrder == ORDER_BY_DESC)
                sb.append(" DESC");
            else
                sb.append(" ASC ");

        }
        System.out.println("SQL Statement=" + sb.toString());
        try (Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sb.toString())) {
            List<String> album = new ArrayList<>();
            while (result.next()) {

                album.add(result.getString(1));
            }
            return album;

            //artist.stream().forEach(result.next() -> {})
        } catch (SQLException e) {
            System.out.println("Query Failed " + e.getMessage());
            return null;
        }
    }

    public List<SongsArtists> querySongArtists(String songName, int sortOrder) {
        StringBuilder sb = new StringBuilder(QUERY_ARTIST_FOR_SONG_START);
        sb.append(songName);
        sb.append("\"");
        if (sortOrder != ORDER_BY_NONE) {
            sb.append(QUERY_ARTIST_FOR_SONG_START_ORDER_BY);
            if (sortOrder == ORDER_BY_DESC)
                sb.append(" DESC");
            else
                sb.append(" ASC ");

        }
        System.out.println("SQL Statement=" + sb.toString());
        try (Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sb.toString())) {
            List<SongsArtists> songsArtists = new ArrayList<>();
            while (result.next()) {

                SongsArtists sa = new SongsArtists();
                sa.setArtistName(result.getString(INDEX_ARTISTS_NAME));
                sa.setAlbumName(result.getString(INDEX_ALBUM_NAME));
                sa.setTrack(result.getInt(INDEX_SONGS_Track));
                songsArtists.add(sa);
            }
            return songsArtists;

            //artist.stream().forEach(result.next() -> {})
        } catch (SQLException e) {
            System.out.println("Query Failed " + e.getMessage());
            return null;
        }
    }

    public void querySongMetaData() {
        String sql = "SELECT * FROM " + TABLE_SONGS;

        try (Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            ResultSetMetaData metaData = result.getMetaData();
            int numColumns = metaData.getColumnCount();
            for (int i = 1; i <= numColumns; i++) {
                System.out.format("Column %d in the songs table is name %s\n",
                        i,
                        metaData.getColumnName(i));
            }

        } catch (SQLException e) {
            System.out.println("Query Failed " + e.getMessage());
        }
    }

    public int getCount(String table) {
        String sql = "SELECT COUNT(*) AS COUNT, MIN(_id) AS MIN_ID FROM " + table;

        try (Statement statement = conn.createStatement();
             ResultSet result = statement.executeQuery(sql)) {
            int count = result.getInt("COUNT");
            int min = result.getInt("MIN_ID");
            System.out.format("Count = %d, Min =%d\n", count, min);
            return count;

        } catch (SQLException e) {
            System.out.println("Query Failed " + e.getMessage());
            return -1;
        }
    }

    public boolean createViewSongArtist() {
        try (Statement statement = conn.createStatement()) {
            statement.execute(CREATE_ARTIST_SONG_VIEW);
            return true;

        } catch (SQLException e) {
            System.out.println("Create View Fail :  " + e.getMessage());
            return false;
        }
    }

    public List<SongsArtists> queryViewSongInfo(String title) {


        try {
            queryViewSongInfo.setString(1, title);
            ResultSet result = queryViewSongInfo.executeQuery();

            List<SongsArtists> songsArtists = new ArrayList<>();
            while (result.next()) {

                SongsArtists sa = new SongsArtists();
                sa.setArtistName(result.getString(1));
                sa.setAlbumName(result.getString(2));
                sa.setTrack(result.getInt(3));
                songsArtists.add(sa);
            }
            return songsArtists;

            //artist.stream().forEach(result.next() -> {})
        } catch (SQLException e) {
            System.out.println("Query Failed " + e.getMessage());
            return null;
        }
    }

    private int insertArtist(String Name) throws Exception{
        queryARTIST.setString(1,Name);
        ResultSet result =queryARTIST.executeQuery();
        if(result.next())
            return result.getInt(1);
        else {
            insertIntoArtist.setString(1,Name);
            int affectedRows =insertIntoArtist.executeUpdate();
            if (affectedRows !=1)
                throw new SQLException("Could not insert artist!");
            ResultSet generateKeys = insertIntoArtist.getGeneratedKeys();
            if (generateKeys.next())
                return generateKeys.getInt(1);
            else
                throw new SQLException("Could not get _ID for artist");

        }
    }

    private int insertAlbums(String Name, int artistId) throws Exception{
        queryALBUMS.setString(1,Name);
        ResultSet result =queryALBUMS.executeQuery();
        if(result.next())
            return result.getInt(1);
        else {
            insertIntoAlbums.setString(1,Name);
            insertIntoAlbums.setInt(2,artistId);
            int affectedRows =insertIntoAlbums.executeUpdate();
            if (affectedRows !=1)
                throw new SQLException("Could not insert albums!");
            ResultSet generateKeys = insertIntoAlbums.getGeneratedKeys();
            if (generateKeys.next())
                return generateKeys.getInt(1);
            else
                throw new SQLException("Could not get _ID for albums");

        }
    }
    public void insertSong(String title, String artist, String album, int track) throws Exception{
        try{
            conn.setAutoCommit(false);
            int artistId = insertArtist(artist);
            int albumId = insertAlbums(album,artistId);
            insertIntoSongs.setInt(1,track);
            insertIntoSongs.setString(2,title);
            insertIntoSongs.setInt(3,albumId);
            int affectedRows =insertIntoArtist.executeUpdate();
            if (affectedRows ==1)
               conn.commit();
            else
                throw new SQLException(" The Song insert failed");
        }catch (Exception e)
        {
            System.out.println("Insert song exception "+ e.getMessage());
            try {
                System.out.println("Calling RollBack!");
                conn.rollback();
            }catch (SQLException e2){
                System.out.println("Can not roll Back!" +e2.getMessage());
            }
        }
        finally {
            try {
                System.out.println("Resenting default commit behaviour");
                conn.setAutoCommit(true);
            }catch (SQLException e3){
                System.out.println("Could not reset auto commit"+ e3.getMessage());
            }
        }

    }
}
