import java.util.ArrayList;

/**
 * Created by hl4350hb on 4/12/2017.
 */
public class Controller {
    static GUI mygui;
    static DBstuff mydb;

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.startApp();
    }

    private void startApp() {
        mydb = new DBstuff();
        mydb.createTable();
        ArrayList<Movie> allMoviesData = mydb.fetchAllRecords();
        mygui = new GUI(this);
        mygui.setListData(allMoviesData);
    }

    ArrayList<Movie> getAllData() {return mydb.fetchAllRecords();}
    void addRecordToDatabase(Movie movieobj) {mydb.addRecord(movieobj);}
    void delete(Movie movieobj) {mydb.delete(movieobj);}
}
