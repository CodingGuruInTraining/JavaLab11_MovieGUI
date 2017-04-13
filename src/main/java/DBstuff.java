import java.sql.*;
import java.util.ArrayList;

/**
 * Created by hl4350hb on 4/12/2017.
 */
public class DBstuff {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:3306/notIMDB";
    private static final String USER = "";
    private static final String PASSWORD = "";
    private static final String TBL = "movieTbl";
    private static final String TITLE_COL = "MovieTitle";
    private static final String YEAR_COL = "YearReleased";
    private static final String RATE_COL = "Rating";

    DBstuff() {
        try {
            Class.forName(JDBC_DRIVER);
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println("Can't instantiate driver class");
            cnfe.printStackTrace();
            System.exit(-1);
        }
    }

    void createTable() {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
            Statement statement = conn.createStatement()) {
            String createTableString = "create table if not exists %s (%s varchar(75), %s int , %s int)";
            String createTableSQL = String.format(createTableString, TBL, TITLE_COL, YEAR_COL, RATE_COL);

            statement.executeUpdate(createTableSQL);

            statement.close();
            conn.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
    }

    void addRecord(Movie movieobj) {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {
            String addSQL = "insert into " + TBL + " values ( ?, ?, ?)";
            PreparedStatement addPS = conn.prepareStatement(addSQL);
            addPS.setString(1, movieobj.title);
            addPS.setInt(2, movieobj.year);
            addPS.setInt(3, movieobj.rating);
            addPS.execute();
            addPS.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
    }

    ArrayList<Movie> fetchAllRecords() {
        ArrayList<Movie> allRecords = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD);
        Statement statement = conn.createStatement()) {
            String selectAllSQL = "select * from " + TBL;
            ResultSet rs = statement.executeQuery(selectAllSQL);

            while (rs.next()) {
                String title = rs.getString(TITLE_COL);
                int year = rs.getInt(YEAR_COL);
                int rating = rs.getInt(RATE_COL);
                Movie newRecord = new Movie(title, year, rating);
                allRecords.add(newRecord);
            }
            rs.close();
            statement.close();
            conn.close();
            return allRecords;
        }
        catch (SQLException se) {
            se.printStackTrace();
            return null;
        }
    }

    void delete(Movie movieobj) {
        try (Connection conn = DriverManager.getConnection(DB_CONNECTION_URL, USER, PASSWORD)) {
            String deleteSQLstr = "delete from %s where %s = ?";
            String deleteSQL = String.format(deleteSQLstr, TBL, TITLE_COL);
            PreparedStatement deletePS = conn.prepareStatement(deleteSQL);
            deletePS.setString(1, movieobj.title);
            deletePS.execute();
            deletePS.close();
            conn.close();
        }
        catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
