import java.util.ArrayList;

public class ThirdRatings {
    //Movies now will be stored in the MovieDatabase instead of the instance variable:
    //private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;

    // default constructor 1
    // default constructor will call another constructor
    // using this keyword from same class
    public ThirdRatings(){
        this(/*"data/ratedmovies_short.csv",*/ "data/ratings_short.csv");
    }
    // parameterized constructor 2
    // the constructor will no longer have a moviefile parameter.
    // Movies will be stored in the MovieDatabase class.
    public ThirdRatings(/*String moviefile,*/ String  ratingsfile){
        FirstRatings fr = new FirstRatings();
        //myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters("data/" + ratingsfile);
    }
    /*
    public int getMovieSize(){
        return myMovies.size();
    }
    */

    public int getRaterSize(){
        ArrayList<String> idCounter = new ArrayList<String>();
        for(Rater r : myRaters){
            if(!idCounter.contains(r.getID())){
                idCounter.add(r.getID());
            }
        }
        return idCounter.size();
    }

    //id - is a movie id;
    //minimalRaters - the minimum allowable number of users who have rated the movie;
    private double getAverageByID(String id, int minimalRaters){
        int ratersCounter = 0;
        double ratingsSum = 0;
        for(Rater r : myRaters){
            if(r.hasRating(id)){                    //if this movie id is present in the class Rater instance then...
                //System.out.println("ID = "+id);
                ratingsSum = ratingsSum + r.getRating(id);
                //System.out.println("RATING = "+r.getRating(id));
                ratersCounter++;
            }
        }
        //System.out.println("COUNTER= "+ratersCounter+" SUM "+ratingsSum);
        if(ratersCounter >= minimalRaters){
            //System.out.println("AVERAGE = "+ratingsSum/ratersCounter);
            return ratingsSum/ratersCounter;
        }
        else{
            return 0.0;
        }
    }
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        double average = 0.0;
        ArrayList<Rating> rl  = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for(String id : movies){
            average = getAverageByID(id, minimalRaters);
            Rating r = new Rating(id, average);
            rl.add(r);
        }
        /*
        for(Movie m : movies){
            average = getAverageByID(m.getID(), minimalRaters);
            Rating r = new Rating(m.getID(), average);
            rl.add(r);
        }
        */
        return rl;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        double average = 0.0;
        ArrayList<Rating> rl = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for(String id : movies){
            average = getAverageByID(id, minimalRaters);
            Rating r = new Rating(id, average);
            rl.add(r);
        }
        return rl;
    //==============================================================
    }
}

