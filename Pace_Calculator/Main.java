import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application{
    double time;
    String paceString;
    double paceInt;
    int paceMinute;
    int paceSeconds;
    String paceUnit;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        GridPane entryGrid = new GridPane(10, 15);
    
        /// Time properties
        final Text TXT_TIME_ENTRY = new Text("Enter a Time");
        TextField tfTimeEntryHr = new TextField("00");
        TextField tfTimeEntryMin = new TextField("6");
        TextField tfTimeEntrySec = new TextField("00");

        /// Pace properties
        final Text TXT_PACE_ENTRY = new Text("Enter a Pace");
        TextField tfPaceEntryMin = new TextField("08");
        TextField tfPaceEntrySec = new TextField("30");
        final ComboBox<String> CBO_PACE_ENTRY_UNIT = new ComboBox<>();
        final Text TXT_PACE_ENTRY_UNIT = new Text("/min");
    
        /// Distance properties
        final Text TXT_DISTANCE_ENTRY = new Text("Enter a Distance");
        TextField tfDistance = new TextField("01");
        final ComboBox<String> CBO_DISTANCE_ENTRY = new ComboBox<>();
    
        // Calculate properties
        Button btnCalTime = new Button("Calculate Time");
        Button btnCalPace = new Button("Calculate Pace");
    
        // Display Grid
        entryGrid.addRow(0, TXT_TIME_ENTRY, tfTimeEntryHr, tfTimeEntryMin, tfTimeEntrySec);
    
        CBO_PACE_ENTRY_UNIT.getItems().addAll("km", "mi");
        entryGrid.addRow(1, TXT_PACE_ENTRY, tfPaceEntryMin, tfPaceEntrySec, CBO_PACE_ENTRY_UNIT, TXT_PACE_ENTRY_UNIT);
            
        CBO_DISTANCE_ENTRY.getItems().addAll("km", "mi");
        entryGrid.addRow(2, TXT_DISTANCE_ENTRY, tfDistance, CBO_DISTANCE_ENTRY);
            
        entryGrid.add(btnCalTime, 2, 3);
        entryGrid.add(btnCalPace, 3, 3);
        Pane pane = new Pane();
        pane.getChildren().add(entryGrid);
    
        // Scene and Stage
        Scene scene = new Scene(pane, 700, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Split Calculator");
        primaryStage.show();

        /// Inner class
        class PopularEvents {
            private GridPane grid = new GridPane();
            private Text txtPace = new Text(getPace());
            private double kmPace;
            private double miPace;
            private int tempPaceMinute;
            private int tempPaceSeconds;
        
            public PopularEvents(String unit) {
                if (unit == "mi") {
                    kmPace = (int)(paceInt / 1.6093);
                    miPace = (int)paceInt;
                }
                else {
                    kmPace = (int)paceInt;
                    miPace = (int)(paceInt / 0.6214);
                }

                /// Pace
                grid.add(txtPace, 0, 0);

                /// Event distances
                grid.addColumn(0, new Text("400m"), new Text("800m"),
                    new Text("5k"), new Text("10k"), new Text("Marathon"));
                grid.addColumn(2, new Text(), new Text("1mi"), new Text("3mi"),
                    new Text("5mi"), new Text("10mi"), new Text("Half-Marathon"));

                /// Event times
                grid.addColumn(1, new Text(), new Text((conMin(false) / 4) + ":" + (conSec(false) / 4)), // 400m
                    new Text((conMin(false) / 2) + ":" + (conSec(false) / 2)),                                       // 800m
                    new Text((conMin(false, 5)) + ":" + (conSec(false, 5))),                                     // 5k
                    new Text((conMin(false, 10)) + ":" + (conSec(false, 10))),                                   // 10k
                    new Text((conMin(true, 26.2)) + ":" + (conSec(true, 26.2))));                                // Marathon (26.2mi)

                grid.addColumn(3, new Text(), new Text((conMin(true)) + ":" + (conSec(true))),           // 1mi
                new Text((conMin(true, 3)) + ":" + (conSec(true, 3))),                                           // 3mi
                new Text((conMin(true, 5)) + ":" + (conSec(true, 5))),                                           // 5mi
                new Text((conMin(true, 10)) + ":" + (conSec(true, 10))),                                         // 10mi
                new Text((conMin(true, 13.1)) + ":" + (conSec(true, 13.1))));                                    // Half-Marathon (13.1mi)

                pane.getChildren().clear();
                pane.getChildren().add(grid);
            }

            public int conMin(boolean mi) {
                if (!mi)
                    return tempPaceMinute = (int)(miPace / 60 % 60);
                else
                    return tempPaceMinute = (int)(kmPace / 60 % 60);
            }
            public int conSec(boolean mi) {
                if (!mi)
                    return tempPaceSeconds = (int)(miPace % 60);
                else
                    return tempPaceSeconds = (int)(kmPace % 60);
            }
            public int conMin(boolean mi, double c) {
                if (!mi) {
                    c *= .6214;

                    return tempPaceMinute = (int)(c * miPace / 60 % 60);
                }
                else {
                    c *= 1.609;
                    return tempPaceMinute = (int)(c * kmPace / 60 % 60);
                }
            }
            public int conSec(boolean mi, double c) {
                if (!mi) {
                    c *= .6214;

                    return tempPaceSeconds = (int)(c * miPace % 60);
                }
                else {
                    c *= 1.609;
                    return tempPaceSeconds = (int)(c * kmPace % 60);
                }
            }
        }

        /// Button Events
        btnCalTime.setOnAction(e -> {
            final double PACE = Double.parseDouble(tfPaceEntryMin.getText()) +
                Double.parseDouble(tfPaceEntrySec.getText()) / 60;
            final double DISTANCE = Double.parseDouble(tfDistance.getText());

            if (CBO_DISTANCE_ENTRY.getValue() != null && CBO_PACE_ENTRY_UNIT.getValue() != null) {
                setTime(PACE, DISTANCE);
                System.out.println(getTime());
            }
        });

        btnCalPace.setOnAction(e -> {
            final double TIME = Double.parseDouble(tfTimeEntryHr.getText()) * 3600 +
                Double.parseDouble(tfTimeEntryMin.getText()) * 60 +
                Double.parseDouble(tfTimeEntrySec.getText());
            final double DISTANCE = Double.parseDouble(tfDistance.getText());

            if (CBO_DISTANCE_ENTRY.getValue() != null) {
                setPace(TIME, DISTANCE, CBO_DISTANCE_ENTRY);
                // System.out.println(getPace());
                PopularEvents displayGrid = new PopularEvents(paceUnit);
            }
        });
    }
    
    public void setTime(double p, double d) {
        time = p * d;
    }
    public double getTime() {
        return time;
    }

    public void setPace(double t, double d, ComboBox<String> c) {
        paceInt = (t / d);
        paceMinute = (int)paceInt / 60 % 60;
        paceSeconds = (int)paceInt % 60;
        paceUnit = c.getValue();
        paceString = paceMinute + " min " + paceSeconds + " seconds / " + c.getValue();
    }
    public String getPace() {
        return paceString;
    }
}