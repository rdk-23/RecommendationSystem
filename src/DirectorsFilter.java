
public class DirectorsFilter implements Filter {
    private String myDirectors;

    public DirectorsFilter(String directors){
        myDirectors = directors;
    }
    @Override
    public boolean satisfies(String id){
        if (MovieDatabase.getDirector(id).contains(",")) {
            String auxArray[] = MovieDatabase.getDirector(id).split(",");
            for (int i = 0; i < auxArray.length; i++) {
                String auxString1 = auxArray[i].trim();
                if (myDirectors.contains(auxString1)) {
                    return true;
                }
            }
        } else {
            String auxString2 = MovieDatabase.getDirector(id);
            if (myDirectors.contains(auxString2)) {
                return true;
            }
        }
        return false;
    }
}
