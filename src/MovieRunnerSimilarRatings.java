import java.util.*;

public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){

        FourthRatings fr = new FourthRatings();
        //System.out.println("N of movies " + sr.getMovieSize());
        RaterDatabase.initialize("ratings_short.csv");
        //RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for " + RaterDatabase.size() +" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for " + MovieDatabase.size() + " movies");


        ArrayList<Rating> input = new ArrayList<Rating>();
        ArrayList<Rating> output = new ArrayList<Rating>();

        input = fr.getAverageRatings(1);
        //System.out.println("INPUT "+input);
        //Rating min = input.get(0);
        //System.out.println("Example " +lr.get(0)+lr.get(1));
        output = sortByAverage(input);

        int counter = 0;
        for(Rating r : output){
            if(r.getValue() !=0.0){
                counter++;
            }
        }
        System.out.println("found "+counter+" movies");
        //System.out.println("OUTPUT "+output);
        for(Rating r : output){
            if(r.getValue() != 0.0){
                String title = MovieDatabase.getTitle(r.getItem());
                double value = r.getValue();
                System.out.println(value +" "+title);
            }
        }
    }

    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fr = new FourthRatings();

        RaterDatabase.initialize("ratings_short.csv");
        //RaterDatabase.initialize("ratings.csv");
        System.out.println("read data for "+ RaterDatabase.size()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+MovieDatabase.size()+" movies");

        ArrayList<Rating> input = new ArrayList<Rating>();
        ArrayList<Rating> output = new ArrayList<Rating>();

        AllFilters allF = new AllFilters();

        Filter yearF = new YearAfterFilter(1900);
        Filter genreF = new GenreFilter("Drama");
        allF.addFilter(yearF);
        allF.addFilter(genreF);

        input = fr.getAverageRatingsByFilter(1,allF);

        output = sortByAverage(input);

        String newLine = System.getProperty("line.separator");

        int counter = 0;

        for(Rating r : output){
            if(r.getValue() !=0.0){
                counter++;
            }
        }
        System.out.println("found "+counter+" movies");
        for(Rating r : output){
            if(r.getValue() !=0.0){
                String title = MovieDatabase.getTitle(r.getItem());
                double value = r.getValue();
                int year = MovieDatabase.getYear(r.getItem());
                String genre = MovieDatabase.getGenres(r.getItem());
                System.out.println(value + " "+ year +" "+ title + newLine +"    " + genre);
            }
        }
    }

//=====================SIMILAR RATINGS==============================
    public void printSimilarRatings(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings_short.csv");
        //RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> rating = new ArrayList<Rating>();
        rating = fr.getSimilarRatings("2", 4, 1);
        //rating = fr.getSimilarRatings("65", 20, 5);
        for(Rating r: rating){
            System.out.println(MovieDatabase.getTitle(r.getItem())+" "+r.getValue());
        }
    }

    public void printSimilarRatingsByGenre(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> rating = new ArrayList<Rating>();
        Filter fByGenre = new GenreFilter("Action");

        rating = fr.getSimilarRatingsByFilter("65", 20, 5, fByGenre);
        String newLine = System.getProperty("line.separator");
        for(Rating r: rating){
            System.out.println(MovieDatabase.getTitle(r.getItem())+
                    " "+r.getValue()+
                    newLine +"    "+ MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printSimilarRatingsByDirector(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> rating = new ArrayList<Rating>();
        Filter fByDirectors = new DirectorsFilter("Clint Eastwood,Sydney Pollac,David Cronenberg,Oliver Stone");
        rating = fr.getSimilarRatingsByFilter("1034", 10, 3, fByDirectors);
        String newLine = System.getProperty("line.separator");
        for(Rating r: rating){
            System.out.println(MovieDatabase.getTitle(r.getItem())+
                    " "+r.getValue()+
                    newLine +"    "+ MovieDatabase.getDirector(r.getItem()));
        }
    }

    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> rating = new ArrayList<Rating>();
        AllFilters fAll = new AllFilters();
        Filter fByGenre = new GenreFilter("Adventure");
        Filter fByMinutes = new MinutesFilter(99,201);
        fAll.addFilter(fByMinutes);
        fAll.addFilter(fByGenre);
        rating = fr.getSimilarRatingsByFilter("65", 10, 5, fAll);
        String newLine = System.getProperty("line.separator");
        for(Rating r: rating){
            System.out.println(MovieDatabase.getTitle(r.getItem())+
                    " "+MovieDatabase.getMinutes(r.getItem())+"min"+
                    " "+r.getValue()+
                    newLine +"   "+MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fr = new FourthRatings();
        RaterDatabase.initialize("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        ArrayList<Rating> rating = new ArrayList<Rating>();
        AllFilters fAll = new AllFilters();
        Filter fYearAfter = new YearAfterFilter(2000);
        Filter fByMinutes = new MinutesFilter(80,100);
        fAll.addFilter(fByMinutes);
        fAll.addFilter(fYearAfter);
        rating = fr.getSimilarRatingsByFilter("65", 10, 5, fAll);
        String newLine = System.getProperty("line.separator");
        for(Rating r: rating){
            System.out.println(MovieDatabase.getTitle(r.getItem())+
                    " "+MovieDatabase.getYear(r.getItem())+
                    " "+MovieDatabase.getMinutes(r.getItem())+"min"+
                    " "+r.getValue());
        }
    }

//================private auxiliary methods========================
    private Rating getSmallestAverage(ArrayList<Rating> rl) {
        Rating min = rl.get(0);
        for (Rating r : rl) {
            if (r.getValue() < min.getValue()) {
                min = r;
            }
        }
        return min;
    }

    private ArrayList<Rating> sortByAverage(ArrayList<Rating> in) {
        ArrayList<Rating> out = new ArrayList<Rating>();
        while(!in.isEmpty()) {
            Rating minElement = getSmallestAverage(in);
            in.remove(minElement);
            out.add(minElement);
        }
        return out;
    }


}
