/**
 * Created by hl4350hb on 4/12/2017.
 */
public class Movie {

    String title;
    int year;
    int rating;

    public Movie(String title, int year, int rating) {
        this.title = title;
        this.year = year;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.title + " (" + this.year + ") - " + this.rating + " stars";
    }
}
