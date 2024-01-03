import java.sql.SQLOutput;
import java.util.ArrayList;

//This class helps to find the average rating of movies using a different filters.

public class MovieRunnerWithFilters {
    public void printAverageRatings(){
        //Creating ThirdRating object with the name of a file with ratings data(one parameter only):
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("data/ratings.csv");

        //Print the number of raters after creating a ThirdsRating object:
        System.out.println("read data for "+ tr.getRaterSize()+" raters");

        //Calling MovieDatabase initialize with a movie file to set up the movie database:
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");

        //Print the number of movies in the database:
        System.out.println("read data for "+ MovieDatabase.size()+ " movies");

        //Two ArrayLists creation:
        ArrayList<Rating> input = new ArrayList<Rating>();
        ArrayList<Rating> output = new ArrayList<Rating>();

        //Calling getAverageRatings with a minimal number of raters:
        input = tr.getAverageRatings(1);
        //{test}System.out.println("Example input "+input);
        //Calling sortByAverage to sort "input" ArrayList from the smallest to the biggest rating:
        output = sortByAverage(input);

        //Count how many movies with ratings are returned:
        int counter = 0;
        for(Rating r : output){
            if(r.getValue() != 0.0){
                counter++;
            }
        }
        //Printing out how many movies with ratings are returned:
        System.out.println("found "+counter+" movies");


        //{test}System.out.println("Example output "+output);
        for(Rating r : output){
            if(r.getValue() != 0.0){
                String title = MovieDatabase.getTitle(r.getItem());
                double value = r.getValue();
                System.out.println(value +" "+title);
            }
        }
    }
    //Auxiliary method helps to find the smallest value in the ArrayList of Ratings:
    public Rating getSmallestAverage(ArrayList<Rating> rl){
        Rating min = rl.get(0);
        for (Rating r : rl){
            if(r.getValue()< min.getValue()){
                min = r;
            }
        }
        return min;
    }
    //Auxiliary method helps to sort an ArrayList of Ratings from the smallest to the biggest value:
    public ArrayList<Rating> sortByAverage(ArrayList<Rating> in){
        ArrayList<Rating> out = new ArrayList<Rating>();
        while(!in.isEmpty()){
            //Calling getSmallestAverage method:
            Rating minElement = getSmallestAverage(in);
            in.remove(minElement);
            out.add(minElement);
        }
        return out;

    }
    //This method prints out a sorted list of movies with respect of a filtering criteria
    //It is similar to the PrintAverageMethod which was represented above:
    public void printAverageByYear(){
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters");

        //MovieDatabase.initialize("ratedmoviesfull.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for "+MovieDatabase.size()+" movies");
        ArrayList<Rating> input = new ArrayList<Rating>();
        ArrayList<Rating> output = new ArrayList<Rating>();

        //Filter definition:
        Filter filter = new YearAfterFilter(2000);
        input = tr.getAverageRatingsByFilter(1, filter);
        output = sortByAverage(input);

        int counter = 0;
        for(Rating r : output){
            if(r.getValue() !=0.0){
                counter++;
            }
        }
        System.out.println("found "+counter+" movies");

        for(Rating r : output){
            if(r.getValue() != 0.0){
                String title = MovieDatabase.getTitle(r.getItem());
                double value = r.getValue();
                int year = MovieDatabase.getYear(r.getItem());
                System.out.println(value +" "+ year +" "+title);

            }
        }

    }
    public void printAverageByGenre(){
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters");

        //MovieDatabase.initialize("ratedmoviesfull.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        System.out.println("read data for "+MovieDatabase.size()+" movies");
        ArrayList<Rating> input = new ArrayList<Rating>();
        ArrayList<Rating> output = new ArrayList<Rating>();

        //Filter definition:
        //Filter genreF = new GenreFilter("Comedy");
        Filter genreF = new GenreFilter("Crime");
        input = tr.getAverageRatingsByFilter(1, genreF);
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
            if(r.getValue() != 0.0){
                String title = MovieDatabase.getTitle(r.getItem());
                double value = r.getValue();
                String genre = MovieDatabase.getGenres(r.getItem());
                System.out.println(value +" "+ title + newLine +"       "+genre);

            }
        }

    }
    public void printAverageRatingsByMinutes(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+MovieDatabase.size()+" movies");

        ArrayList<Rating> input = new ArrayList<Rating>();
        ArrayList<Rating> output = new ArrayList<Rating>();

        Filter minutesF = new MinutesFilter(110,170);

        input = tr.getAverageRatingsByFilter(1,minutesF);
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
                int minutes = MovieDatabase.getMinutes(r.getItem());
                System.out.println(value+" "+" Time: "+ minutes +" "+ title);


            }
        }
    }

    public void printAverageRatingsByDirectors(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+MovieDatabase.size()+" movies");

        ArrayList<Rating> input = new ArrayList<Rating>();
        ArrayList<Rating> output = new ArrayList<Rating>();

        //Filter directorsF = new DirectorsFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        Filter directorsF = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        input = tr.getAverageRatingsByFilter(1,directorsF);

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
                String director = MovieDatabase.getDirector(r.getItem());
                System.out.println(value + " " + title + newLine +"    "+director);
            }
        }
    }
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters");

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

        input = tr.getAverageRatingsByFilter(1,allF);

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

    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings tr = new ThirdRatings("ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("ratings.csv");
        System.out.println("read data for "+tr.getRaterSize()+" raters");

        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        System.out.println("read data for "+MovieDatabase.size()+" movies");

        ArrayList<Rating> input = new ArrayList<Rating>();
        ArrayList<Rating> output = new ArrayList<Rating>();

        AllFilters allF = new AllFilters();

        //Filter directorsF = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        //Filter directorsF = new DirectorsFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        Filter directorsF = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        Filter minutesF = new MinutesFilter(30,170);
        allF.addFilter(directorsF);
        allF.addFilter(minutesF);

        input = tr.getAverageRatingsByFilter(1,allF);

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
                int time = MovieDatabase.getMinutes(r.getItem());
                //int year = MovieDatabase.getYear(r.getItem());
                String director = MovieDatabase.getDirector(r.getItem());
                //String genre = MovieDatabase.getGenres(r.getItem());
                System.out.println(value + " Time: "+ time +" "+ title + newLine +"    " + director);
            }
        }

    }
}
