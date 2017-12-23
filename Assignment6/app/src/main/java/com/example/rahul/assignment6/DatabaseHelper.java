package com.example.rahul.assignment6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    // DB version
    private static final int DB_VERSION = 1;
    // DB Name
    private static final String DB_NAME = "myMovies7.db";
    // TABLE Names
    private static final String TABLE_MOVIES = "movies";
    private static final String TABLE_GENRES = "genres";
    private static final String TABLE_MOVIE_GENRE = "movie_genre";
    // drop command
    private static final String DROP_CMD = "DROP TABLE IF EXISTS";
    // common Column name
    private static final String KEY_ID = "id";

    // Movies Table Column names
    private static final String KEY_VOTE_CNT = "vote_cnt";
    private static final String KEY_MOVIEID = "movieid";
    private static final String KEY_VOTE_AVG = "vote_avg";
    private static final String KEY_TITLE = "title";
    private static final String KEY_POPULARITY = "popularity";
    private static final String KEY_POSTER = "poster";
    private static final String KEY_BACKDROP = "backdrop";
    private static final String KEY_OVERVIEW = "overview";
    private static final String KEY_RELEASE = "release";
    // Genres Table name
    private static final String KEY_GENRE = "gname";
    // MOVIE_GENRE Table names
    private static final String KEY_MOVIE_ID = "movie_id";
    private static final String KEY_GENRE_ID = "genre_id";
    // Table Create Statements
    // Table Movies
    private static final String CREATE_TABLE_MOVIES = "CREATE TABLE " +
            TABLE_MOVIES
            + " ( " + KEY_ID + " INTEGER PRIMARY KEY , "
            + KEY_MOVIEID + " INTEGER , "
            + KEY_TITLE + " TEXT , "
            + KEY_VOTE_CNT + " INTEGER , "
            + KEY_VOTE_AVG + " REAL , "
            + KEY_POPULARITY + " REAL , "
            + KEY_POSTER + " TEXT , "
            + KEY_BACKDROP + " TEXT , "
            + KEY_OVERVIEW + " TEXT , "
            + KEY_RELEASE + " TEXT "
            + " )";
    // Table Genres
    private static final String CREATE_TABLE_GENRES = "CREATE TABLE " +
            TABLE_GENRES
            + " ( " + KEY_ID + " INTEGER PRIMARY KEY , "
            + KEY_GENRE + " TEXT "
            + " )";
    // Table Movie Genre
    private static final String CREATE_TABLE_MOVIE_GENRE = "CREATE TABLE " +
            TABLE_MOVIE_GENRE
            + " ( " + KEY_ID + " INTEGER PRIMARY KEY , "
            + KEY_MOVIE_ID + " INTEGER , "
            + KEY_GENRE_ID + " INTEGER "
            + " )";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIES);
        sqLiteDatabase.execSQL(CREATE_TABLE_GENRES);
        sqLiteDatabase.execSQL(CREATE_TABLE_MOVIE_GENRE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // drop older tables
        sqLiteDatabase.execSQL(DROP_CMD + TABLE_MOVIES);
        sqLiteDatabase.execSQL(DROP_CMD + TABLE_GENRES);
        sqLiteDatabase.execSQL(DROP_CMD + TABLE_MOVIE_GENRE);
        // create new tables
        onCreate(sqLiteDatabase);
        initializeTables();
    }

    public void initializeTables() {
        // insert 25 movie data into the db
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_GENRES;

        Cursor c = db.rawQuery(query, null);

        if (c.getCount() <= 0) {
            insertAllGenres();
        }

        query = "SELECT * FROM " + TABLE_MOVIES;

        c = db.rawQuery(query, null);

        if (c.getCount() <= 0) {
            insertAllMovies();
        }
    }

    public void insertAllGenres() {
        int id;
        String gname;

        // #1
        id = 28;
        gname = "Action";
        Genre genre1 = new Genre(id, gname);
        createNewGenre(genre1);

        // #2
        id = 12;
        gname = "Adventure";
        Genre genre2 = new Genre(id, gname);
        createNewGenre(genre2);

        // #3
        id = 16;
        gname = "Animation";
        Genre genre3 = new Genre(id, gname);
        createNewGenre(genre3);

        // #4
        id = 35;
        gname = "Comedy";
        Genre genre4 = new Genre(id, gname);
        createNewGenre(genre4);

        // #5
        id = 80;
        gname = "Crime";
        Genre genre5 = new Genre(id, gname);
        createNewGenre(genre5);

        // #6
        id = 99;
        gname = "Documentary";
        Genre genre6 = new Genre(id, gname);
        createNewGenre(genre6);

        // #7
        id = 18;
        gname = "Drama";
        Genre genre7 = new Genre(id, gname);
        createNewGenre(genre7);

        // #8
        id = 10751;
        gname = "Family";
        Genre genre8 = new Genre(id, gname);
        createNewGenre(genre8);

        // #9
        id = 14;
        gname = "Fantasy";
        Genre genre9 = new Genre(id, gname);
        createNewGenre(genre9);

        // #10
        id = 36;
        gname = "History";
        Genre genre10 = new Genre(id, gname);
        createNewGenre(genre10);

        // #11
        id = 27;
        gname = "Horror";
        Genre genre11 = new Genre(id, gname);
        createNewGenre(genre11);

        // #12
        id = 10402;
        gname = "Music";
        Genre genre12 = new Genre(id, gname);
        createNewGenre(genre12);

        // #13
        id = 9648;
        gname = "Mystery";
        Genre genre13 = new Genre(id, gname);
        createNewGenre(genre13);

        // #14
        id = 10749;
        gname = "Romance";
        Genre genre14 = new Genre(id, gname);
        createNewGenre(genre14);

        // #15
        id = 878;
        gname = "Science Fiction";
        Genre genre15 = new Genre(id, gname);
        createNewGenre(genre15);

        // #16
        id = 10770;
        gname = "TV Movie";
        Genre genre16 = new Genre(id, gname);
        createNewGenre(genre16);

        // #17
        id = 53;
        gname = "Thriller";
        Genre genre17 = new Genre(id, gname);
        createNewGenre(genre17);

        // #18
        id = 10752;
        gname = "War";
        Genre genre18 = new Genre(id, gname);
        createNewGenre(genre18);

        // #19
        id = 37;
        gname = "Western";
        Genre genre19 = new Genre(id, gname);
        createNewGenre(genre19);

    }

    public int createNewGenre(Genre genre) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, genre.getId());
        values.put(KEY_GENRE, genre.getGname());

        int index = (int) db.insert(TABLE_GENRES, null, values);

        return (int) index;
    }

    // insert all my movie data into Movies table !!!
    public void insertAllMovies() {
        int id;
        int vote_count;
        double vote_avg;
        double popularity;
        String title;
        String poster_path;
        String backdrop_path;
        String overview;
        String release;
        List<Integer> genres;

        // #1
        id = 19404;
        vote_count = 666;
        vote_avg = 9.1;
        title = "Dilwale Dulhania Le Jayenge";
        popularity = 30.469554;
        poster_path = "/2gvbZMtV1Zsl7FedJa5ysbpBx2G.jpg";
        overview = "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to ";
        genres = new ArrayList<Integer>(Arrays.asList(35, 18, 10749));
        backdrop_path = "/nl79FQ8xWZkhL3rDr1v2RFFR6J0.jpg";
        release = "1995-10-20";
        MyMovie movie1 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie1, genres);

        // #2
        id = 278;
        vote_count = 8341;
        vote_avg = 8.5;
        title = "The Shawshank Redemption";
        popularity = 49.64781;
        poster_path = "/9O7gLzmreU0nGkIB6K3BsJbzvNv.jpg";
        overview = "Framed in the 1940s for the double murder of his wife and her lover, upstanding banker Andy Dufresne begins a new life at the Shawshank prison";
        genres = new ArrayList<Integer>(Arrays.asList(18, 80));
        backdrop_path = "/xBKGJQsAIeweesB79KC89FpBrVr.jpg";
        release = "1994-09-23";
        MyMovie movie2 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie2, genres);


        // #3
        id = 372058;
        vote_count = 1029;
        vote_avg = 8.5;
        title = "Your Name.";
        popularity = 42.016606;
        poster_path = "/xq1Ugd62d23K2knRUx6xxuALTZB.jpg";
        overview = "High schoolers Mitsuha and Taki are complete strangers living separate lives. But one night, they suddenly switch places.";
        genres = new ArrayList<Integer>(Arrays.asList(10749, 16, 18));
        backdrop_path = "/mMtUybQ6hL24FXo0F3Z4j2KG7kZ.jpg";
        release = "2016-08-26";
        MyMovie movie3 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie3, genres);

        // #4
        id = 238;
        vote_count = 6000;
        vote_avg = 8.5;
        title = "The Godfather";
        popularity = 42.46639;
        poster_path = "/rPdtLWNsZmAtoZl9PK7S2wE3qiS.jpg";
        overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American Corleone crime family.";
        genres = new ArrayList<Integer>(Arrays.asList(18, 80));
        backdrop_path = "/6xKCYgH16UuwEGAyroLU6p8HLIn.jpg";
        release = "1972-03-14";
        MyMovie movie4 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie4, genres);

        // #5
        id = 424;
        vote_count = 4427;
        vote_avg = 8.3;
        title = "Schindler's List";
        popularity = 38.959768;
        poster_path = "/yPisjyLweCl1tbgwgtzBCNCBle.jpg";
        overview = "The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves";
        genres = new ArrayList<Integer>(Arrays.asList(18, 36, 10752));
        backdrop_path = "/rIpSszng8P0DL0TimSzZbpfnvh1.jpg";
        release = "1993-11-29";
        MyMovie movie5 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie5, genres);

        // #6
        id = 129;
        vote_count = 3973;
        vote_avg = 8.3;
        title = "Spirited Away";
        popularity = 36.55833;
        poster_path = "/dL11DBPcRhWWnJcFXl9A07MrqTI.jpg";
        overview = "A young girl, Chihiro, becomes trapped in a strange new world of spirits. When her parents undergo a mysterious transformation, she";
        genres = new ArrayList<Integer>(Arrays.asList(16, 10751, 14));
        backdrop_path = "/mnpRKVSXBX6jb56nabvmGKA0Wig.jpg";
        release = "2001-07-20";
        MyMovie movie6 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie6, genres);


        // #7
        id = 244786;
        vote_count = 4338;
        vote_avg = 8.3;
        title = "Whiplash";
        popularity = 59.857212;
        poster_path = "/lIv1QinFqz4dlp5U4lQ6HaiskOZ.jpg";
        overview = "Under the direction of a ruthless instructor, a talented young drummer begins to pursue perfection at any cost, even his humanity.";
        genres = new ArrayList<Integer>(Arrays.asList(18));
        backdrop_path = "/6bbZ6XyvgfjhQwbplnUh1LSj1ky.jpg";
        release = "2014-10-10";
        MyMovie movie7 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie7, genres);

        // #8
        id = 240;
        vote_count = 3419;
        vote_avg = 8.3;
        title = "The Godfather: Part II";
        popularity = 36.694396;
        poster_path = "/bVq65huQ8vHDd1a4Z37QtuyEvpA.jpg";
        overview = "In the continuing saga of the Corleone crime family, a young Vito Corleone grows up in Sicily and in 1910s New York.";
        genres = new ArrayList<Integer>(Arrays.asList(18, 80));
        backdrop_path = "/gLbBRyS7MBrmVUNce91Hmx9vzqI.jpg";
        release = "1974-12-20";
        MyMovie movie8 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie8, genres);

        // #9
        id = 374430;
        vote_count = 194;
        vote_avg = 8.3;
        title = "Black Mirror: White Christmas";
        popularity = 24.776591;
        poster_path = "/he609rnU3tiwBjRklKNa4n2jQSd.jpg";
        overview = "This feature-length special consists of three interwoven stories. In a mysterious and remote snowy outpost, Matt and Potter ";
        genres = new ArrayList<Integer>(Arrays.asList(18, 27, 9648, 878, 53, 10770));
        backdrop_path = "/rMCew7St2vy9iV3QOPzx15sAkFJ.jpg";
        release = "2014-12-16";
        MyMovie movie9 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie9, genres);

        // #10
        id = 550;
        vote_count = 9597;
        vote_avg = 8.3;
        title = "Fight Club";
        popularity = 53.43144;
        poster_path = "/adw6Lq9FiC9zjYEpOqfq03ituwp.jpg";
        overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel primal male aggression into a shocking new form of therapy.";
        genres = new ArrayList<Integer>(Arrays.asList(18));
        backdrop_path = "/8uO0gUM8aNqYLs1OsTBQiXu0fEv.jpg";
        release = "1999-10-15";
        MyMovie movie10 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie10, genres);

        // #11
        id = 455661;
        vote_count = 133;
        vote_avg = 8.3;
        title = "In a Heartbeat";
        popularity = 24.729068;
        poster_path = "/wJUJROdLOtOzMixkjkx1aaZGSLl.jpg";
        overview = "A closeted boy runs the risk of being outed by his own heart after it pops out of his chest to chase down the boy of his dreams.";
        genres = new ArrayList<Integer>(Arrays.asList(10751, 16, 10749, 35));
        backdrop_path = "/4datX8btikWEikf7esqEe6fhw.jpg";
        release = "2017-06-01";
        MyMovie movie11 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie11, genres);

        // #12
        id = 680;
        vote_count = 8581;
        vote_avg = 8.3;
        title = "Pulp Fiction";
        popularity = 146.243535;
        poster_path = "/dM2w364MScsjFf8pfMbaWUcWrR.jpg";
        overview = "A burger-loving hit man, his philosophical partner, a drug-addled gangster's moll and a washed-up boxer converge in this sprawling";
        genres = new ArrayList<Integer>(Arrays.asList(53, 80));
        backdrop_path = "/9rZg1J6vMQoDVSgRyWcpJa8IAGy.jpg";
        release = "1994-09-10";
        MyMovie movie12 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie12, genres);

        // #13
        id = 637;
        vote_count = 3635;
        vote_avg = 8.3;
        title = "Life Is Beautiful";
        popularity = 37.210246;
        poster_path = "/f7DImXDebOs148U4uPjI61iDvaK.jpg";
        overview = "A touching story of an Italian book seller of Jewish ancestry who lives in his own little fairy tale.";
        genres = new ArrayList<Integer>(Arrays.asList(18, 35));
        backdrop_path = "/bORe0eI72D874TMawOOFvqWS6Xe.jpg";
        release = "1997-12-20";
        MyMovie movie13 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie13, genres);

        // #14
        id = 155;
        vote_count = 12243;
        vote_avg = 8.3;
        title = "The Dark Knight";
        popularity = 98.546305;
        poster_path = "/1hRoyzDtpgMU7Dz4JF22RANzQO7.jpg";
        overview = "Batman raises the stakes in his war on crime. With the help of Lt. Jim Gordon and District Attorney Harvey Dent,";
        genres = new ArrayList<Integer>(Arrays.asList(18, 28, 80, 53));
        backdrop_path = "/hqkIcbrOHL86UncnHIsHVcVmzue.jpg";
        release = "2008-07-16";
        MyMovie movie14 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie14, genres);

        // #15
        id = 539;
        vote_count = 2378;
        vote_avg = 8.2;
        title = "Psycho";
        popularity = 35.224946;
        poster_path = "/81d8oyEFgj7FlxJqSDXWr8JH8kV.jpg";
        overview = "When larcenous real estate clerk Marion Crane goes on the lam with a wad of cash and hopes of starting a new life";
        genres = new ArrayList<Integer>(Arrays.asList(18, 27, 53));
        backdrop_path = "/3md49VBCeqY6MSNyAVY6d5eC6bA.jpg";
        release = "1960-06-16";
        MyMovie movie15 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie15, genres);

        // #16
        id = 311;
        vote_count = 1101;
        vote_avg = 8.2;
        title = "Once Upon a Time in America";
        popularity = 33.677428;
        poster_path = "/fqP3Q7DWMFqW7mh11hWXbNwN9rz.jpg";
        overview = "A former Prohibition-era Jewish gangster returns to the Lower East Side of Manhattan over thirty years later,";
        genres = new ArrayList<Integer>(Arrays.asList(18, 80));
        backdrop_path = "/vnT6HzjLSDrAweHn9xWykb8Ii6T.jpg";
        release = "1984-02-16";
        MyMovie movie16 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie16, genres);

        // #17
        id = 510;
        vote_count = 2983;
        vote_avg = 8.2;
        title = "One Flew Over the Cuckoo's Nest";
        popularity = 35.779433;
        poster_path = "/2Sns5oMb356JNdBHgBETjIpRYy9.jpg";
        overview = "While serving time for insanity at a state mental hospital, implacable rabble-rouser, Randle Patrick McMurphy inspires";
        genres = new ArrayList<Integer>(Arrays.asList(18));
        backdrop_path = "/4E7YQcwui0PfNXguf4V2X8YocPC.jpg";
        release = "1975-11-18";
        MyMovie movie17 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie17, genres);

        // #18
        id = 13;
        vote_count = 8073;
        vote_avg = 8.2;
        title = "Forrest Gump";
        popularity = 43.697079;
        poster_path = "/yE5d3BUhE8hCnkMUJOo1QDoOGNz.jpg";
        overview = "A man with a low IQ has accomplished great things in his life and been present during significant historic events ";
        genres = new ArrayList<Integer>(Arrays.asList(18, 35, 10749));
        backdrop_path = "/d5ud5KadGTrY6UQzzNIuK6L0Xyo.jpg";
        release = "1994-07-06";
        MyMovie movie18 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie18, genres);

        // #19
        id = 389;
        vote_count = 2112;
        vote_avg = 8.2;
        title = "12 Angry Men";
        popularity = 24.105543;
        poster_path = "/3W0v956XxSG5xgm7LB6qu8ExYJ2.jpg";
        overview = "The defense and the prosecution have rested and the jury is filing into the jury room to decide if a young Spanish-American";
        genres = new ArrayList<Integer>(Arrays.asList(18));
        backdrop_path = "/lH2Ga8OzjU1XlxJ73shOlPx6cRw.jpg";
        release = "1957-03-25";
        MyMovie movie19 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie19, genres);

        // #20
        id = 769;
        vote_count = 3205;
        vote_avg = 8.2;
        title = "GoodFellas";
        popularity = 22.593729;
        poster_path = "/hAPeXBdGDGmXRPj4OZZ0poH65Iu.jpg";
        overview = "The true story of Henry Hill, a half-Irish, half-Sicilian Brooklyn kid who is adopted by neighbourhood gangsters at an early age";
        genres = new ArrayList<Integer>(Arrays.asList(18, 80));
        backdrop_path = "/xDEOxA01480uLTWuvQCw61VmDBt.jpg";
        release = "1990-09-12";
        MyMovie movie20 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie20, genres);

        // #21
        id = 346364;
        vote_count = 2039;
        vote_avg = 8.2;
        title = "IT";
        popularity = 7.4;
        poster_path = "/9E2y5Q7WlCVNEhP5GiVTjhEhx1o.jpg";
        overview = "In a small town in Maine, seven children known as The Losers Club come face to face with life problems, bullies ";
        genres = new ArrayList<Integer>(Arrays.asList(18, 12, 27));
        backdrop_path = "/tcheoA2nPATCm2vvXw2hVQoaEFD.jpg";
        release = "2017-09-05";
        MyMovie movie21 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie21, genres);

        // #22
        id = 335984;
        vote_count = 636;
        vote_avg = 7.7;
        title = "Blade Runner 2049";
        popularity = 319.702307;
        poster_path = "/aMpyrCizvSdc0UIMblJ1srVgAEF.jpg";
        overview = "Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret";
        genres = new ArrayList<Integer>(Arrays.asList(28, 9648, 878, 53));
        backdrop_path = "/mVr0UiqyltcfqxbAUcLl9zWL8ah.jpg";
        release = "2017-10-04";
        MyMovie movie22 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie22, genres);

        // #23
        id = 281338;
        vote_count = 1696;
        vote_avg = 6.7;
        title = "War for the Planet of the Apes";
        popularity = 237.156665;
        poster_path = "/3vYhLLxrTtZLysXtIWktmd57Snv.jpg";
        overview = "Caesar and his apes are forced into a deadly conflict with an army of humans led by a ruthless Colonel. ";
        genres = new ArrayList<Integer>(Arrays.asList(18, 878, 10752));
        backdrop_path = "/ulMscezy9YX0bhknvJbZoUgQxO5.jpg";
        release = "2017-07-11";
        MyMovie movie23 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie23, genres);

        // #24
        id = 343668;
        vote_count = 571;
        vote_avg = 7.4;
        title = "Kingsman: The Golden Circle";
        popularity = 195.970713;
        poster_path = "/pKESfn2Pdy0b7drvZHQb7UzgqoY.jpg";
        overview = "When an attack on the Kingsman headquarters takes place and a new villain rises, Eggsy and Merlin are forced to work";
        genres = new ArrayList<Integer>(Arrays.asList(28, 12, 35));
        backdrop_path = "/uExPmkOHJySrbJyJDJylHDqaT58.jpg";
        release = "2017-09-20";
        MyMovie movie24 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie24, genres);

        // #25
        id = 339403;
        vote_count = 2101;
        vote_avg = 7.2;
        title = "Baby Driver";
        popularity = 177.845219;
        poster_path = "/dN9LbVNNZFITwfaRjl4tmwGWkRg.jpg";
        overview = "After being coerced into working for a crime boss, a young getaway driver finds himself taking part in a heist doomed to fail.";
        genres = new ArrayList<Integer>(Arrays.asList(28, 80));
        backdrop_path = "/goCvLSUFz0p7k8R10Hv4CVh3EQv.jpg";
        release = "2017-06-28";
        MyMovie movie25 = new MyMovie(id, vote_count, vote_avg, popularity,
                title, poster_path, backdrop_path, overview, release);
        createNewMovie(movie25, genres);
    }

    // Create ( Insert ) a new movie
    public int createNewMovie(MyMovie movie, List<Integer> genres) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MOVIEID, movie.getMovie_id());
        values.put(KEY_VOTE_CNT, movie.getVote_cnt());
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_VOTE_AVG, movie.getVote_avg());
        values.put(KEY_POPULARITY, movie.getPopularity());
        values.put(KEY_POSTER, movie.getPoster());
        values.put(KEY_BACKDROP, movie.getBackdrop());
        values.put(KEY_OVERVIEW, movie.getOverview());
        values.put(KEY_RELEASE, movie.getRelease());

        int index = (int) db.insert(TABLE_MOVIES, null, values);

        for (int gid : genres)
            createMovieGenre(index, gid);

        return (int) index;
    }

    public void createMovieGenre(int index, int gid) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MOVIE_ID, index);
        values.put(KEY_GENRE_ID, gid);

        db.insert(TABLE_MOVIE_GENRE, null, values);
    }

    // Get ( fetch ) a matched movie with an unique id
    public MyMovie getMovie(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_MOVIES + " WHERE " + KEY_ID + "= " + id;
        Cursor c = db.rawQuery(query, null);

        if (c == null)
            return null;
        c.moveToFirst();
        MyMovie movie = new MyMovie();
        movie.setId(c.getInt(c.getColumnIndex(KEY_ID)));
        movie.setMovie_id(c.getInt(c.getColumnIndex(KEY_MOVIEID)));
        movie.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
        movie.setVote_cnt(c.getInt(c.getColumnIndex(KEY_VOTE_CNT)));
        movie.setVote_avg(c.getDouble(c.getColumnIndex(KEY_VOTE_AVG)));
        movie.setPopularity(c.getDouble(c.getColumnIndex(KEY_POPULARITY)));
        movie.setPoster(c.getString(c.getColumnIndex(KEY_POSTER)));
        movie.setBackdrop(c.getString(c.getColumnIndex(KEY_BACKDROP)));
        movie.setOverview(c.getString(c.getColumnIndex(KEY_OVERVIEW)));
        movie.setRelease(c.getString(c.getColumnIndex(KEY_RELEASE)));

        return movie;
    }

    // Get ( fetch ) a matched movie with an unique id
    public int getMovieName(String name) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_MOVIES + " WHERE " + KEY_TITLE + "= " + name;
        Cursor c = db.rawQuery(query, null);

        if (c == null)
            return 0;
        c.moveToFirst();

        int poistion = c.getInt(c.getColumnIndex(KEY_ID));

        return poistion;
    }

    // Get ( fetch ) all movies
    public List<MyMovie> getAllMovies() {
        List<MyMovie> movies = new ArrayList<MyMovie>();

        String query = "SELECT * FROM " + TABLE_MOVIES;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(query, null);

        if (c == null)
            return null;

        if (c.moveToFirst()) {
            do {
                MyMovie movie = new MyMovie();
                // same statements in the getMovie method
                movie.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                movie.setMovie_id(c.getInt(c.getColumnIndex(KEY_MOVIEID)));
                movie.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                movie.setVote_cnt(c.getInt(c.getColumnIndex(KEY_VOTE_CNT)));
                movie.setVote_avg(c.getDouble(c.getColumnIndex(KEY_VOTE_AVG)));
                movie.setPopularity(c.getDouble(c.getColumnIndex(KEY_POPULARITY)));
                movie.setPoster(c.getString(c.getColumnIndex(KEY_POSTER)));
                movie.setBackdrop(c.getString(c.getColumnIndex(KEY_BACKDROP)));
                movie.setOverview(c.getString(c.getColumnIndex(KEY_OVERVIEW)));
                movie.setRelease(c.getString(c.getColumnIndex(KEY_RELEASE)));
                movies.add(movie);
            }
            while (c.moveToNext());
        }

        return movies;
    }

    public List<MyMovie> getMatchedGenresMovies(String genre_name) {
        Log.d("SEARCH", "getMatchedGenresMovies start ");
        List<MyMovie> movies = new ArrayList<MyMovie>();
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT movie_genre.genre_id as id , genres.gname as genre "
                + " FROM movie_genre "
                + " JOIN genres on movie_genre.genre_id = genres.id "
                + " WHERE genres.gname = " + genre_name;

        Cursor c = db.rawQuery(query, null);

        if (c == null)
            return null;

        if (c.moveToFirst()) {
            do {
                MyMovie movie = new MyMovie();
                // same statements in the getMovie method
                movie.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                movie.setMovie_id(c.getInt(c.getColumnIndex(KEY_MOVIEID)));
                movie.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                movie.setVote_cnt(c.getInt(c.getColumnIndex(KEY_VOTE_CNT)));
                movie.setVote_avg(c.getDouble(c.getColumnIndex(KEY_VOTE_AVG)));
                movie.setPopularity(c.getDouble(c.getColumnIndex(KEY_POPULARITY)));
                movie.setPoster(c.getString(c.getColumnIndex(KEY_POSTER)));
                movie.setBackdrop(c.getString(c.getColumnIndex(KEY_BACKDROP)));
                movie.setOverview(c.getString(c.getColumnIndex(KEY_OVERVIEW)));
                movie.setRelease(c.getString(c.getColumnIndex(KEY_RELEASE)));
                movies.add(movie);
            }
            while (c.moveToNext());
        }
        Log.d("SEARCH", "getMatchedGenresMovies end ");
        Log.d("SEARCH", "movies size - " + movies.size());
        return movies;
    }


    // Delete a movie
    public void deleteMovie(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_MOVIES, KEY_ID + " =?", new String[]
                {String.valueOf(id)});
    }

    // Update a movie
    public void updateMovie(MyMovie movie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_MOVIEID, movie.getMovie_id());
        // assign all movie info to values ...
        values.put(KEY_VOTE_CNT, movie.getVote_cnt());
        values.put(KEY_TITLE, movie.getTitle());
        values.put(KEY_VOTE_AVG, movie.getVote_avg());
        values.put(KEY_POPULARITY, movie.getPopularity());
        values.put(KEY_POSTER, movie.getPoster());
        values.put(KEY_BACKDROP, movie.getBackdrop());
        values.put(KEY_OVERVIEW, movie.getOverview());
        values.put(KEY_RELEASE, movie.getRelease());

        db.insert(TABLE_MOVIES, null, values);
    }

    public List<Genre> getMatchedGenres(int mid) {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT movie_genre.genre_id as id , genres.gname as genre "
                + " FROM movie_genre "
                + " JOIN genres on movie_genre.genre_id = genres.id "
                + " WHERE movie_genre.movie_id = " + mid;

        Cursor c = db.rawQuery(query, null);

        if (c.moveToFirst()) {
            List<Genre> gs = new ArrayList<Genre>();
            do {
                Genre g = new Genre();
                g.setId(c.getInt(c.getColumnIndex(KEY_ID)));
                g.setGname(c.getString(c.getColumnIndex(KEY_GENRE)));

                gs.add(g);
            }
            while (c.moveToNext());
            return gs;
        }

        return null;

    }


    public void logCat() {
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + TABLE_MOVIES;

        Cursor c = db.rawQuery(query, null);

        Log.d("TEST", "Total Number of Movies: " + Integer.toString(c.getCount()));

        int index = 0;
        if (c.moveToFirst()) {
            do {
                String movieTitle = c.getString(c.getColumnIndex(KEY_TITLE));
                index++;
                Log.d("TEST", Integer.toString(index) + ". " + movieTitle);
            }
            while (c.moveToNext());
        }

    }

}
