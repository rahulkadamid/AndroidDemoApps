package com.example.rahul.assignment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class MovieData {

    List<Map<String,?>> moviesList;

    public List<Map<String, ?>> getMoviesList() {
        return moviesList;
    }

    public int getSize(){
        return moviesList.size();
    }

    public HashMap getItem(int i){
        if (i >=0 && i < moviesList.size()){
            return (HashMap) moviesList.get(i);
        } else return null;
    }

    public MovieData() {
		Integer vote_count;
		Integer id;
		Double vote_avg;
		Double popularity;
		String title;
		String overview;
		String release;
		String language;

		moviesList = new ArrayList<Map<String, ?>>();

		// Adding 25 Movies
		// 1-5
		id = 19404;
		vote_count = 546;
		vote_avg = 9.0;
		title = "Dilwale Dulhania Le Jayenge";
		popularity = 20.185604;
		overview = "Raj is a rich, carefree, happy-go-lucky second generation NRI";
		release = "1992-06-15";
		language = "Hindi";
		moviesList.add(createMovie(id, R.drawable.ddlj, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 278;
		vote_count = 8054;
		vote_avg = 8.5;
		title = "The Shawshank Redemption";
		popularity = 32.118901;
		overview = "Framed in the 1940s for the double murder of his wife and her lover";
		release = "1994-09-23";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.tsr, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 238;
		vote_count = 5796;
		vote_avg = 8.4;
		title = "The Godfather";
		popularity = 20.185604;
		overview = "Spanning the years 1945 to 1955, a chronicle of the fictional Italian-American ";
		release = "1972-03-14";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.tg, vote_count,
				vote_avg, popularity, title,
				overview, release, language));

		id = 372058;
		vote_count = 861;
		vote_avg = 8.4;
		title = "Your Name.";
		popularity = 25.391544;
		overview = "High schoolers Mitsuha and Taki are complete strangers living separate lives.";
		release = "2016-08-26";
		language = "Japanese";
		moviesList.add(createMovie(id, R.drawable.yn, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 424;
		vote_count = 4228;
		vote_avg = 8.3;
		title = "Schindler's List";
		popularity = 21.560734;
		overview = "The true story of how businessman Oskar Schindler saved over a thousand ";
		release = "1993-11-29";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.sl, vote_count,
				vote_avg, popularity, title,
				overview, release, language));

		// 6-10
		id = 374430;
		vote_count = 165;
		vote_avg = 8.3;
		title = "Black Mirror: White Christmas";
		popularity = 13.925528;
		overview = "This feature-length special consists of three interwoven stories.";
		release = "2014-12-16";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.bmwc, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 244786;
		vote_count = 4117;
		vote_avg = 8.3;
		title = "Whiplash";
		popularity = 43.482411;
		overview = "Under the direction of a ruthless instructor, a talented young drummer begins ";
		release = "2014-10-10";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.w, vote_count,
				vote_avg, popularity, title,
				overview, release, language));

		id = 129;
		vote_count = 3719;
		vote_avg = 8.3;
		title = "Spirited Away";
		popularity = 27.917333;
		overview = "A ten year old girl who wanders away from her parents along a path that leads  ";
		release = "2001-07-20";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.sw, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 240;
		vote_count = 3269;
		vote_avg = 8.3;
		title = "The Godfather: Part II";
		popularity = 24.969233;
		overview = "In the continuing saga of the Corleone crime family, a young Vito Corleone ";
		release = "1974-12-20";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.tg2, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 455661;
		vote_count = 86;
		vote_avg = 8.3;
		title = "In a Heartbeat";
		popularity = 10.477499;
		overview = "A closeted boy runs the risk of being outed by his own heart ";
		release = "2017-06-01";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.iah, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		// 11-15
		id = 550;
		vote_count = 9154;
		vote_avg = 8.3;
		title = "Fight Club";
		popularity = 39.00882;
		overview = "A ticking-time-bomb insomniac and a slippery soap salesman channel ";
		release = "1999-10-15";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.fc, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 637;
		vote_count = 3411;
		vote_avg = 8.3;
		title = "Life Is Beautiful";
		popularity = 21.197114;
		overview = "A touching story of an Italian book seller of Jewish ancestry who lives ";
		release = "1997-12-20";
		language = "Italian";
		moviesList.add(createMovie(id, R.drawable.lib, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 432517;
		vote_count = 234;
		vote_avg = 8.3;
		title = "Sherlock: The Final Problem";
		popularity = 13.892394;
		overview = "Long buried secrets finally come to light as someone has been playing ";
		release = "2017-01-15";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.stfp, vote_count,
				vote_avg, popularity, title,
				overview, release, language));

		id = 680;
		vote_count = 8198;
		vote_avg = 8.3;
		title = "Pulp Fiction";
		popularity = 29.900875;
		overview = "A burger-loving hit man, his philosophical partner, a drug-addled";
		release = "1994-10-08";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.pf, vote_count,
				vote_avg, popularity, title,
				overview, release, language));

		id = 155;
		vote_count = 11804;
		vote_avg = 8.3;
		title = "The Dark Knight";
		popularity = 43.185651;
		overview = "With the help of Lt. Jim Gordon and District Attorney Harvey Dent";
		release = "2008-07-16";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.tdk, vote_count,
				vote_avg, popularity, title,
				overview, release, language));

		// 16-20
		id = 313106;
		vote_count = 287;
		vote_avg = 8.3;
		title = "Doctor Who: The Day of the Doctor";
		popularity = 11.977027;
		overview = "In 2013, something terrible is awakening in London's National";
		release = "2013-11-23";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.dw, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 510;
		vote_count = 2816;
		vote_avg = 8.3;
		title = "One Flew Over the Cuckoo's Nest";
		popularity = 29.898879;
		overview = "While serving time for insanity at a state mental hospital";
		release = "2008-07-16";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.ofotcn, vote_count,
				vote_avg, popularity, title,
				overview, release, language));

		id = 157;
		vote_count = 11806;
		vote_avg = 8.9;
		title = "The Dark Knight Rises";
		popularity = 43.185651;
		overview = "With the help of Lt. Jim Gordon and District Attorney Harvey Dent End";
		release = "2009-07-16";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.tdk, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 13;
		vote_count = 7679;
		vote_avg = 8.3;
		title = "Forrest Gump";
		popularity = 43.185651;
		overview = "A man with a low IQ has accomplished great things in his life a";
		release = "1994-07-06";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.fg, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 539;
		vote_count = 2236;
		vote_avg = 8.3;
		title = "Psycho";
		popularity = 20.613224;
		overview = "When larcenous real estate clerk Marion Crane goes on the lam with";
		release = "1960-06-16";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.p, vote_count,
				vote_avg, popularity, title,
				overview, release, language));

		// 21-25
		id = 389;
		vote_count = 2047;
		vote_avg = 8.3;
		title = "12 Angry Men";
		popularity = 16.096414;
		overview = "The defense and the prosecution have rested and the jury is filing ";
		release = "1957-03-25";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.am2, vote_count,
				vote_avg, popularity, title,
				overview, release, language));

		id = 346364;
		vote_count = 213;
		vote_avg = 8.3;
		title = "It";
		popularity = 20.613224;
		overview = "In a small town in Maine, seven children known as The Losers Club come ";
		release = "2017-08-17";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.it, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 427900;
		vote_count = 2;
		vote_avg = 3.8;
		title = "Home Again";
		popularity = 64.456735;
		overview = "Life for a single mom in Los Angeles takes an unexpected turn when she ";
		release = "2017-09-07";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.ha, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 342473;
		vote_count = 352;
		vote_avg = 7.1;
		title = "Ballerina";
		popularity = 60.486802;
		overview = "Set in 1879 Paris. An orphan girl dreams of becoming a ballerina ";
		release = "2016-12-14";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.b, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));

		id = 460790;
		vote_count = 33;
		vote_avg = 8.3;
		title = "Starship Troopers: Traitor of Mars";
		popularity = 46.60573;
		overview = "Federation trooper Johnny Rico is ordered to work with a group of new recruits";
		release = "2017-08-21";
		language = "English";
		moviesList.add(createMovie(id, R.drawable.st, vote_count,
				vote_avg, popularity, title,
				overview, release,language ));
    }


	private HashMap createMovie(Integer id, int image, Integer vCount, Double vAvg, Double pop,
								String title, String overview,
								String release, String language) {
		HashMap movie = new HashMap();
		movie.put("image", image);
		movie.put("id", id);
		movie.put("voteCount", vCount);
		movie.put("voteAverage", vAvg);
		movie.put("popularity", pop);
		movie.put("title", title);
		movie.put("overview", overview);
		movie.put("release", release);
		movie.put("language", language);
		movie.put("selection",false);
		return movie;
	}

	public void selectall()
	{
		for(Map<String, ?> map : moviesList)
		{
			HashMap<String,Boolean> temp= (HashMap<String,Boolean>)map;
			temp.put("selection", true);
		}

	}

	public void clearall()
	{
		for(Map<String, ?> map : moviesList)
		{
			HashMap<String,Boolean> temp= (HashMap<String,Boolean>)map;
			temp.put("selection", false);
		}
	}
	public int findFirst(String query)
	{
		for(int i = 0; i < moviesList.size(); i++)
		{
			Map<String, ?> movie = moviesList.get(i);
			if (movie.get("title").toString().toLowerCase().contains(query.toLowerCase()) == true)
				return i;
		}
		return -1;
	}
	public void deleteitems()
	{
		ArrayList clean = new ArrayList();
		for(Map<String, ?> map : moviesList)
		{
			HashMap<String,Boolean> temp= (HashMap<String,Boolean>)map;

			if (temp.get("selection") == true)
			{
				clean.add(map);
			}
		}
		moviesList.removeAll(clean);
	}
	public void addItem(int position,Map<String,?> map){
		moviesList.add(position,map);
	}
	public void sortItemsByYear()
	{
		Collections.sort(moviesList, Comparators.YEARSORT);
	}
	public void sortItemsByName()
	{
        Collections.sort(moviesList, Comparators.NAMESORT);
	}
	public static class Comparators {

		public static Comparator<Map<String, ?>> YEARSORT = new Comparator<Map<String, ?>>()
		{
			DateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			@Override
			public int compare(Map<String, ?> o1, Map<String, ?> o2)
			{
				try
				{
					String s1 = o1.get("release").toString();
					String s2 = o2.get("release").toString();
					return -f.parse(s1).compareTo(f.parse(s2));
				}
				catch (ParseException e)
				{
					throw new IllegalArgumentException(e);
				}
			}
		};
		public static Comparator<Map<String, ?>> NAMESORT = new Comparator<Map<String, ?>>() {
			@Override
			public int compare(Map<String, ?> o1, Map<String, ?> o2) {
				String str1 =  o1.get("title").toString() ;
				String str2 =  o2.get("title").toString() ;
				return str1.compareTo(str2);
			}
		};
	}

}
