
import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;
public class FirstRatings {
    public static ArrayList<Movie> loadMovies(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Movie> alm = new ArrayList<Movie>();
        for(CSVRecord rec : parser){
            String id = rec.get("id");
            String title = rec.get("title");
            String year = rec.get("year");
            String genre = rec.get("genre");
            String director = rec.get("director");
            String country = rec.get("country");
            String poster = rec.get("poster");
            int minutes = Integer.parseInt(rec.get("minutes"));
            Movie m = new Movie(id, title, year, genre, director, country, poster, minutes);
            alm.add(m);
        }
        return alm;
    }

    public static ArrayList<Rater> loadRaters(String filename){
        FileResource fr = new FileResource(filename);
        CSVParser parser = fr.getCSVParser();
        ArrayList<Rater> alRater = new ArrayList<Rater>();
        for(CSVRecord rec : parser){
            String rId = rec.get("rater_id");
            String mId = rec.get("movie_id");
            double rating = Double.parseDouble(rec.get("rating"));

            //PlainRater r = new PlainRater(rId);
            EfficientRater r = new EfficientRater(rId);
            r.addRating(mId, rating);

            alRater.add(r);
        }
        return alRater;
    }
    public static void testLoadMovies(){
        ArrayList<Movie> localMovie = new ArrayList<Movie>();
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        localMovie = loadMovies("data/ratedmovies_short.csv");
        //System.out.println(localMovie);
        int genresCounter = 0;
        int movieDurationCounter =0;
        for(Movie m : localMovie){
            if(m.getGenres().contains("Comedy")){
                genresCounter++;
            }

            if(m.getMinutes()>150){
                movieDurationCounter++;
            }

            if(m.getDirector().contains(",")){
                String[] strAr = m.getDirector().split(",");
                for(int i=0; i<strAr.length; i++){
                    String auxStr = strAr[i].trim();
                    if(!map.containsKey(auxStr)){
                        map.put(auxStr,1);
                    }
                    else{
                        map.put(auxStr, map.get(auxStr)+1);
                    }
                }
            }
            else{
                String auxStr = m.getDirector().trim();
                if(!map.containsKey(auxStr)){
                    map.put(auxStr,1);
                }
                else{
                    map.put(auxStr,map.get(auxStr)+1);
                }
            }
        }
        int mostNumber = 0;
        int movieCounter = 0;
        for(String s : map.keySet()){
            if(map.get(s)> mostNumber){
                mostNumber = map.get(s);
            }
        }
        for(String s : map.keySet()){
            if(map.get(s) == mostNumber){
                System.out.println("The name of the director is "+ s);
                movieCounter++;
            }
        }
        System.out.println("Number of movies in the comedy genre is " + genresCounter);
        System.out.println("Number of movies longer than 150 min is " + movieDurationCounter);
        System.out.println("Most films made by one director = "+ mostNumber);
        System.out.println("Number of directors who made "+mostNumber+" films = "+ movieCounter);

    }
    public static void testLoadRaters(){
        ArrayList<Rater> localRaters = loadRaters("data/ratings_short.csv");
        ArrayList<String> idCounter = new ArrayList<String>();
        String raterID = "193";
        String movieID = "1798709";
        int ratingsCount = 0;
        int uniqueMovieRaterCount = 0;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<String> uniqueMoviesId = new ArrayList<String>();
        int uniqueMoviesIdCount = 0;

        for(Rater r : localRaters) {
            //System.out.println(r.getID());
            if (!idCounter.contains(r.getID())) {
                idCounter.add(r.getID());
            }
            if (r.getID().equals(raterID)) {
                ratingsCount++;
            }
            if (!map.containsKey(r.getID())) {
                map.put(r.getID(), 1);
            } else {
                map.put(r.getID(), map.get(r.getID()) + 1);
            }

            if (r.hasRating(movieID)) {
                uniqueMovieRaterCount++;
            }
            ArrayList<String> al = r.getItemsRated();
            for (String s : al) {
                if (!uniqueMoviesId.contains(s)) {
                    uniqueMoviesId.add(s);
                }
            }
        }
        int mostRatingsNumber = 0;
        int uniqueIdRatingsCount = 0;
        for(String s : map.keySet()){
            //System.out.println("MAP "+map.get(s));
            if(map.get(s)>mostRatingsNumber){
                mostRatingsNumber=map.get(s);
            }
        }
        for(String s : map.keySet()){
            if(map.get(s)==mostRatingsNumber){
                System.out.println("The id of the most active rater is "+ s);
                uniqueIdRatingsCount++;
            }
        }
        System.out.println("Number of raters with unique id is: "+idCounter.size());
        System.out.println("Rater with id "+raterID+" made "+ratingsCount+ " ratings.");
        System.out.println("The maximum number of ratings made by one rater is "+mostRatingsNumber);
        System.out.println("The number of raters with the maximum number of ratings is "+uniqueIdRatingsCount);
        System.out.println("A movie with the id = "+movieID+" was rated by "+uniqueMovieRaterCount+" raters");
        System.out.println("Number of movies with unique id is " + uniqueMoviesId.size());
    }

}
