public class Main {
    public static void main(String[] args){
        //FirstRatings fr = new FirstRatings();
        MovieRunnerAverage mra = new MovieRunnerAverage();

        /*
        //Test loadRaters
        System.out.println(fr.loadRaters("data/ratings_short.csv"));
        for(Rater r : fr.loadRaters("data/ratings_short.csv")){
            System.out.println(r.getID());
        }*/

        //fr.testLoadMovies();
        //fr.testLoadRaters();
        long startTime = System.nanoTime();
        mra.printAverageRatings();
        long stopTime = System.nanoTime();
        System.out.println("Execution time is "+(stopTime-startTime)/1000000 +" ms");
        //mra.getAverageRatingOneMovie();
    }

}
