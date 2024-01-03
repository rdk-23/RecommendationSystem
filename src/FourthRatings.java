
import java.util.*;

public class FourthRatings {
    private double getAverageByID(String id, int minimalRaters){

        ArrayList<Rater> raters = RaterDatabase.getRaters();
        int ratersCounter = 0;
        double ratingsSum = 0;

        for(Rater r : raters){
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
        ArrayList<Rating> rl = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        //System.out.println("TEST"+movies);
        for(String id : movies){
            average = getAverageByID(id, minimalRaters);
            Rating r = new Rating(id, average);
            rl.add(r);
        }
        /*
        for(Movie m : myMovies){
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
            average = getAverageByID(id,minimalRaters);
            Rating r = new Rating(id, average);
            rl.add(r);
        }

        return rl;
    }

    private double dotProduct(Rater me, Rater r){
        double dotProd = 0;
        for(String s1 : me.getItemsRated()){
            if(r.hasRating(s1)){
                dotProd = dotProd + (me.getRating(s1)-5)*(r.getRating(s1)-5);
            }
        }
        return dotProd;
    }

    private ArrayList<Rating> getSimilarities(String id){
        ArrayList<Rating> ra = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        Rater me = RaterDatabase.getRater(id);

        for(Rater r : raters){
            if(!r.getID().equals(id)){
                double dot = dotProduct(me,r);
                if(dot >= 0){
                    Rating ratingAux = new Rating(r.getID(),dot);
                    ra.add(ratingAux);
                }
            }
        }
        System.out.println("RA TEST: "+ ra);
        Collections.sort(ra, Collections.reverseOrder());
        //ra.sort(Collections.reverseOrder());
        System.out.println("RA TEST AFTER: "+ ra);
        return ra;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        // here we write down the rater id (anItem) and dot product(aValue):
        ArrayList<Rating> ra = getSimilarities(id);
        //movies id ArrayList
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        //Raters id ArrayList
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        //Here we write down the movie id(anItem) and movie weight(aValue)
        ArrayList<Rating> wMovies = new ArrayList<Rating>();
        for(String mID : movies){
            int nRatings = 0;
            double wTotal = 0.0;
            for(int i=0; i<numSimilarRaters; i++){
                Rating r = ra.get(i);
                String uID = r.getItem();
                Rater simRater = RaterDatabase.getRater(uID);
                System.out.println("simRater :"+"Movie: "+mID+" User: "+uID+" "+simRater.getItemsRated());
                if(simRater.hasRating(mID)){
                    nRatings++;
                    double ratingSim = simRater.getRating(mID);
                    System.out.println("ratingSim: "+ratingSim);
                    double weight = r.getValue();
                    System.out.println("weight: "+weight);
                    wTotal += ratingSim * weight;
                    System.out.println("total: "+wTotal);
                    //System.out.println("mID "+mID);
                    //System.out.println("uID "+uID);
                    //System.out.println("Weight: "+r.getValue());
                    //System.out.println("MovieRating: "+simRater.getRating(mID));
                }
            }
            if(nRatings>=minimalRaters && !RaterDatabase.getRater(id).hasRating(mID)){
                double wAverage = wTotal/nRatings;
                wMovies.add(new Rating(mID,wAverage));
            }
        }
        Collections.sort(wMovies,Collections.reverseOrder());
        return wMovies;
    }

    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ra = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        ArrayList<Rating> wMovies = new ArrayList<Rating>();
        //System.out.println("TEST");
        for(String mID : movies){
            int nRatings = 0;
            double wTotal = 0.0;
            for(int i=0; i<numSimilarRaters; i++){
                Rating r = ra.get(i);
                String uID = r.getItem();
                Rater simRater = RaterDatabase.getRater(uID);
                if(simRater.hasRating(mID)){
                    nRatings++;
                    double ratingSim = simRater.getRating(mID);
                    double weight = r.getValue();
                    wTotal += ratingSim * weight;
                }
            }
            if(nRatings>=minimalRaters/*&& !RaterDatabase.getRater(id).hasRating(mID)*/ ){
                double wAverage = wTotal/nRatings;
                wMovies.add(new Rating(mID,wAverage));
            }
        }
        Collections.sort(wMovies,Collections.reverseOrder());
        return wMovies;
    }



}
