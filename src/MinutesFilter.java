public class MinutesFilter implements Filter {
    private int myMinutesMin;
    private int myMinutesMax;
    public MinutesFilter(int minutesMin, int minutesMax){
        myMinutesMin = minutesMin;
        myMinutesMax = minutesMax;
    }
    @Override
    public boolean satisfies(String id){
        return myMinutesMin <= MovieDatabase.getMinutes(id) &&
                MovieDatabase.getMinutes(id) <= myMinutesMax;
    }
}
