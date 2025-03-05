import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        // Scene and Stage
        Scene scene = new Scene(showEntryGrid(), 300, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Split Calculator");
        primaryStage.show();
    }

    public static GridPane showEntryGrid() {
        GridPane entryGrid = new GridPane(10, 15);

        /// "Enter a Time" row
        Text txtTimeEntry = new Text("Enter a Time");
        TextField tfTimeEntryHr = new TextField("01");
        TextField tfTimeEntryMin = new TextField("30");
        TextField tfTimeEntrySec = new TextField("25");

        entryGrid.add(txtTimeEntry, 0, 0);
        entryGrid.add(tfTimeEntryHr, 1, 0);
        entryGrid.add(tfTimeEntryMin, 2, 0);
        entryGrid.add(tfTimeEntrySec, 3, 0);

        /// "Enter a Pace" row
        Text txtPaceEntry = new Text("Enter a Pace");
        TextField tfPaceEntryMin = new TextField("08");
        TextField tfPaceEntrySec = new TextField("30");
        TextField tfPaceEntryUnit = new TextField("mi"); // Later set to a drop down menu thing. A controlBox?
        Text txtPaceEntryUnit = new Text("/min");

        entryGrid.add(txtPaceEntry, 0, 1);
        entryGrid.add(tfPaceEntryMin, 1, 1);
        entryGrid.add(tfPaceEntrySec, 2, 1);
        entryGrid.add(tfPaceEntryUnit, 3, 1);
        entryGrid.add(txtPaceEntryUnit, 4, 1);

        /// "Enter a Distance" row
        Text txtDistanceEntry = new Text("Enter a Distance");
        TextField tfDistance = new TextField("08");
        TextField tfDistanceEntryUnit = new TextField("mi"); // Later set to a drop down menu thing. A controlBox?

        entryGrid.add(txtDistanceEntry, 0, 2);
        entryGrid.add(tfDistance, 1, 2);
        entryGrid.add(tfDistanceEntryUnit, 2, 2);

        
        return entryGrid;
    }
}
