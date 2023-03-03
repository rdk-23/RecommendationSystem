import java.util.ArrayList;

public class MovieRunnerAverage {
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        //test:
        System.out.println("N of movies "+sr.getMovieSize());
        System.out.println("N of raters "+sr.getRaterSize());

        ArrayList<Rating> input = new ArrayList<Rating>();
        ArrayList<Rating> output = new ArrayList<Rating>();

        input = sr.getAverageRatings(3);
        //System.out.println("Example input "+input);


        while(!input.isEmpty()){
            //Rating minElement = getSmallestAverage(input);
            Rating minElement = input.get(0);
            for(Rating r : input){
                if(r.getValue() < minElement.getValue()){
                    minElement = r;
                }
            }
            input.remove(minElement);
            output.add(minElement);
        }
        //System.out.println("Example output "+output);
        for(Rating r : output){
            if(r.getValue() != 0.0){
                String title = sr.getTitle(r.getItem());
                double value = r.getValue();
                System.out.println(value +" "+title);

            }
        }
    }
    public void getAverageRatingOneMovie(){
        //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        //SecondRatings sr = new SecondRatings("data/1_moviequiz.csv", "data/1_ratingsquiz.csv");
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        //String title = "The Godfather";
        //String title = "Her";
        //String title = "No Country for Old Men";
        //String title = "Inside Llewyn Davis";
        //String title = "The Maze Runner";
        //String title = "Moneyball";
        String title = "Vacation";
        String id = "";
        ArrayList<Rating> input = new ArrayList<Rating>();
        ArrayList<Rating> output = new ArrayList<Rating>();

        input = sr.getAverageRatings(1);

        while(!input.isEmpty()){
            //Rating minElement = getSmallestAverage(input);
            Rating minElement = input.get(0);
            for(Rating r : input){
                if(r.getValue() < minElement.getValue()){
                    minElement = r;
                }
            }
            input.remove(minElement);
            output.add(minElement);
        }
        id = sr.getID(title);
        if(!id.equals("NO SUCH TITLE")){
            for(Rating r : output){
                if(r.getItem().equals(id)){
                    System.out.println("The average rating for "+title+" is "+ r.getValue());
                }
            }
        }
    }
}
