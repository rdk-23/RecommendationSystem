public class Main {
    public static void main(String[] args){
        //FirstRatings fr = new FirstRatings();
        //MovieRunnerAverage mra = new MovieRunnerAverage();
        //MovieRunnerWithFilters mrwf =  new MovieRunnerWithFilters();
        MovieRunnerSimilarRatings mrsr = new MovieRunnerSimilarRatings();
        /*
        //Test loadRaters
        System.out.println(fr.loadRaters("data/ratings_short.csv"));
        for(Rater r : fr.loadRaters("data/ratings_short.csv")){
            System.out.println(r.getID());
        }*/

        //fr.testLoadMovies();
        //fr.testLoadRaters();
        long startTime = System.nanoTime();
        //mra.printAverageRatings();
        //mrwf.printAverageRatings();
        //mrwf.printAverageByGenre();
        //mrwf.printAverageRatingsByMinutes();
        //mrwf.printAverageRatingsByDirectors();
        //mrwf.printAverageRatingsByYearAfterAndGenre();
        //mrwf.printAverageRatingsByDirectorsAndMinutes();
        //mrsr.printAverageRatings();
        //mrsr.printAverageRatingsByYearAfterAndGenre();
        //mrsr.printSimilarRatings();
        //mrsr.printSimilarRatingsByGenre();
        //mrsr.printSimilarRatingsByDirector();
        //mrsr.printSimilarRatingsByGenreAndMinutes();
        mrsr.printSimilarRatingsByYearAfterAndMinutes();

        long stopTime = System.nanoTime();
        System.out.println("Execution time is "+(stopTime-startTime)/1000000 +" ms");
        //mra.getAverageRatingOneMovie();
    }

}
