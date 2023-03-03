import java.util.ArrayList;
import java.util.HashMap;

public class EfficientRater implements Rater{
    private String myID;
    private HashMap<String,Rating> myRatings; //the key in the HashMap is a movie id

    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<String,Rating>();
    }

    public void addRating(String item, double rating) {
        Rating r = new Rating(item, rating);
        //the value associated with the movie ID String item as the key in the
        //HashMap:
        myRatings.put(item,r);
    }

    public boolean hasRating(String item) {
        if (myRatings.containsKey(item)){
            return true;
        }
        return false;
    }

    public String getID() {
        return myID;
    }

    public double getRating(String item) {
        for(String s : myRatings.keySet()){
            if (s.equals(item)){
                Rating r = myRatings.get(item);
                return r.getValue();
            }
        }
        return -1;
    }

    public int numRatings() {
        return myRatings.size();
    }

    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for(Rating r : myRatings.values()){
            list.add(r.getItem());
        }
        return list;
    }
}
