//The interface has only one signature for the method satisfies:
public interface Filter {
    /* The method satisfies has one string parameter named id representing a movie ID
    This method returns the TRUE if the movie satisfies the criteria in the method
    and returns FALSE otherwise. */
    public boolean satisfies(String id);
}
