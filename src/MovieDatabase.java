import java.util.HashMap;
import java.util.ArrayList;

public class MovieDatabase {
    //ourMovies maps a Movie ID String to a Movie object
    private static HashMap<String, Movie> ourMovies;

    //public initialization with a moviefile:
    public static void initialize(String moviefile){
        if(ourMovies == null){
            ourMovies = new HashMap<String, Movie>();
            loadMovies("data/"+ moviefile);
        }
    }

    //private initialization with a data/ratedmoviesfull.csv:
    private static void initialize(){
        if(ourMovies == null){
            ourMovies = new HashMap<String, Movie>();
            loadMovies("data/ratemoviesfull.csv");
        }
    }

    //A private loadMovies method to build the HashMap:
    private static void loadMovies(String filename){
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> list = fr.loadMovies(filename);
        for(Movie m : list){
            ourMovies.put(m.getID(), m);
        }
    }

    //A containsID method with one String parameter named id . This method returns
    //true if the id is a movie in the database, and false otherwise.
    public static boolean containsID(String id){
        initialize();
        return ourMovies.containsKey(id);
    }

    /* Several getter methods including getYear , getTitle , getMovie , getPoster ,
       getMinutes , getCountry , getGenres , and getDirector . Each of these takes a
       movie ID as a parameter and returns information about that movie*/
    public static int getYear(String id) {
        initialize();
        return ourMovies.get(id).getYear();
    }
    public static String getGenres(String id) {
        initialize();
        return ourMovies.get(id).getGenres();
    }
    public static String getTitle(String id) {
        initialize();
        return ourMovies.get(id).getTitle();
    }
    public static Movie getMovie(String id) {
        initialize();
        return ourMovies.get(id);
    }
    public static String getPoster(String id) {
        initialize();
        return ourMovies.get(id).getPoster();
    }
    public static int getMinutes(String id) {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    public static String getCountry(String id) {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    public static String getDirector(String id) {
        initialize();
        return ourMovies.get(id).getDirector();
    }
    //A size method that returns the number of movies in the database
    public static int size() {
        return ourMovies.size();
    }

    public static ArrayList<String> filterBy(Filter f){
        initialize();
        ArrayList<String> list = new ArrayList<String>();
        for(String id : ourMovies.keySet()){
            if(f.satisfies(id)){
                list.add(id);
            }
        }
        return list;
    }


}
