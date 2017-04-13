import javax.swing.*;
import java.util.ArrayList;

/**
 * Created by hl4350hb on 4/12/2017.
 */
public class GUI extends JFrame {
    private JPanel rootPanel;
    private JLabel titleLabel;
    private JTextField titleTextField;
    private JLabel yearLabel;
    private JTextField yearTextField;
    private JLabel ratingLabel;
    private JTextField ratingTextField;
    private JList<Movie> movieJList;

    private JButton addButton;
    private JButton editButton;

    private JScrollPane movieScroller;
    private DefaultListModel<Movie> movieModel;
    private Controller controller;

    GUI(Controller controller) {
        this.controller = controller;

        titleLabel = new JLabel("Movie Title:");
        titleTextField = new JTextField();
        yearLabel = new JLabel("Year Released:");
        yearTextField = new JTextField();
        ratingLabel = new JLabel("Your Rating:");
        ratingTextField = new JTextField();
        movieJList = new JList<Movie>();

        addButton = new JButton("Add");
        editButton = new JButton("Edit");

        movieScroller = new JScrollPane(movieJList);
        rootPanel = new JPanel();

        BoxLayout layoutBox = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
        rootPanel.setLayout(layoutBox);

        rootPanel.add(titleLabel);
        rootPanel.add(titleTextField);
        rootPanel.add(yearLabel);
        rootPanel.add(yearTextField);
        rootPanel.add(ratingLabel);
        rootPanel.add(ratingTextField);
        rootPanel.add(movieJList);
        rootPanel.add(addButton);
        rootPanel.add(editButton);
        rootPanel.add(movieScroller);

        movieModel = new DefaultListModel<Movie>();
        movieJList.setModel(movieModel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(rootPanel);
        pack();
        setVisible(true);
    }

    void setListData(ArrayList<Movie> data) {
        movieModel.clear();
        for (Movie movie : data) {
            movieModel.addElement(movie);
        }
    }
}
