package com.company;

import com.company.model.Artists;
import com.company.model.Datasource;
import com.company.model.SongsArtists;

import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
        Datasource datasource =new Datasource();
        if(!datasource.open())
        {
            System.out.println("Can not open Datasource");
            return;
        }
        List<Artists> artistsList =datasource.queryArtist(Datasource.ORDER_BY_ASC);
        if (artistsList == null){
            System.out.println("No Artist!");
            return;
        }
        for (Artists artists : artistsList){
            System.out.println("ID = "+artists.getId()+ " Name =" + artists.getName());
        }

        List<String> albums = datasource.queryAlbumsForArtist("Iron Maiden", Datasource.ORDER_BY_ASC);

        for (String album : albums){
            System.out.println(album);
        }
        List<SongsArtists> songsArtists = datasource.querySongArtists("Go Your Own Way", Datasource.ORDER_BY_ASC);
        if (songsArtists.isEmpty()){
            System.out.println("No Song-Artists!");
            return;
        }
        for (SongsArtists sa: songsArtists){
            System.out.println("Artist Name = "+sa.getArtistName() +
                    " Album Name = "+sa.getAlbumName()+
                    " Song Tack = "+sa.getTrack());
        }
        datasource.querySongMetaData();
        int count = datasource.getCount(Datasource.TABLE_SONGS);
        System.out.println("No. of songs is = "+count);
        datasource.createViewSongArtist();

        songsArtists = datasource.queryViewSongInfo("Go Your Own Way");
        if (songsArtists.isEmpty()){
            System.out.println("No Song-Artists!");
            return;
        }
        for (SongsArtists sa: songsArtists){
            System.out.println("From View - Artist Name = "+sa.getArtistName() +
                    " Album Name = "+sa.getAlbumName()+
                    " Song Tack = "+sa.getTrack());
        }

        datasource.insertSong("Touch of Grey", "Grateful Dead", "In the Dark", 1);

        datasource.stop();
    }
}
