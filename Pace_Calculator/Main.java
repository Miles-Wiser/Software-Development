import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    final double MILE = 0.6214;
    final double KILOMETER = 1.609;

    double pace;
    double time;
    double distance;
    
    String paceUnit;
    String distanceUnit;
    
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
            /// Time properties
            final Text TXT_TIME_ENTRY = new Text("Enter a Time");
            TextField tfTimeHr = new TextField("01");
            TextField tfTimeMin = new TextField("30");
            TextField tfTimeSec = new TextField("30");

            /// Pace properties
            final Text TXT_PACE_ENTRY = new Text("Enter a Pace");
            TextField tfPaceMin = new TextField("10");
            TextField tfPaceSec = new TextField("00");
            final Text TXT_PACE_ENTRY_UNIT = new Text("/min");
        
            /// Distance properties
            final Text TXT_DISTANCE_ENTRY = new Text("Enter a Distance");
            TextField tfDistance = new TextField("06");
        
            // Calculate properties
            Button btnCalTime = new Button("Calculate Time");
            Button btnCalPace = new Button("Calculate Pace");
            Button btnReturn = new Button("Return");

            Pane pane = new Pane();
   
        class Entry {
            GridPane entryGrid = new GridPane(10, 15);
            /**
             * Creates instance of {@code Entry}
             * Displays textfield entry boxes and buttons to calculate time and pace.
             */
            public Entry() {
            // Display Grid
            entryGrid.addRow(0, TXT_TIME_ENTRY, tfTimeHr, tfTimeMin, tfTimeSec);
            entryGrid.addRow(1, TXT_PACE_ENTRY, tfPaceMin, tfPaceSec, TXT_PACE_ENTRY_UNIT);
            entryGrid.addRow(2, TXT_DISTANCE_ENTRY, tfDistance);
                
            entryGrid.add(btnCalTime, 2, 3);
            entryGrid.add(btnCalPace, 3, 3);
            pane.getChildren().add(entryGrid);
            }
        }
 
        class PopularEvents {
            private GridPane grid = new GridPane(10, 15);
            private Text txtPace = new Text(toStringPace());
        
            /**
             * Create instance of {@code PopularEvents}.
             * Shows a list of times for different distances in a grid
             */
            public PopularEvents(String unit) {

                /// Pace
                grid.add(txtPace, 0, 0);

                /// Event distances
                grid.addColumn(0, new Text("400m"), new Text("800m"),
                    new Text("5k"), new Text("10k"), new Text("Marathon"));
                grid.addColumn(2, new Text(), new Text("1mi"), new Text("3mi"),
                    new Text("5mi"), new Text("10mi"), new Text("Half-Marathon"));

                /// Event times
                grid.addColumn(1, new Text(), new Text(toStringPace(.25)), // 400m
                    new Text(toStringPace(.5)),                            // 800m
                    new Text(toStringPace(5 * MILE)),                   // 5k
                    new Text(toStringPace(10 * MILE)),                  // 10k
                    new Text(toStringPace(26.2))                           // Marathon (26.2mi)
                );
                grid.addColumn(3, btnReturn, new Text(toStringPace(1)),     // 1mi
                    new Text(toStringPace(3)),                              // 3mi
                    new Text(toStringPace(5)),                              // 5mi
                    new Text(toStringPace(10)),                             // 10mi
                    new Text(toStringPace(13.1))                            // Half-Marathon (13.1mi)
                );
                pane.getChildren().clear();
                pane.getChildren().add(grid);
            }
        }

        class Splits {
            /**
             * Creates an instance of {@code Splits}.
             * Shows the splits of a distance evenly spread across 1mi/1km increments.
             */
            private GridPane grid = new GridPane(10, 15);
            private Text txtPace = new Text();
            private double kmPace;
            private double miPace;


            public Splits(String unit) {
                // Checks if the distance has a decimal for math later.
                int kmSplits;
                if (Double.parseDouble(tfDistance.getText()) % 1 == 0)
                    kmSplits = Integer.parseInt(tfDistance.getText());
                else
                    kmSplits = (int)(Double.parseDouble(tfDistance.getText())) + 1;

                // Checks if the distance has a decimal for math later.
                int miSplits;
                if (Double.parseDouble(tfDistance.getText()) % 1 == 0)
                    miSplits = Integer.parseInt(tfDistance.getText());
                else
                    miSplits = (int)(Double.parseDouble(tfDistance.getText())) + 1;

                grid.add(txtPace, 0, 0);

                // Kilometer splits
                for (int i = 1; i <= kmSplits; i++) {
                    grid.add(new Text(i + "km"), 0, i);
                    grid.add(new Text(toStringTime(i * MILE) + ""), 1, i);
                }

                grid.addRow(0, new Text(toStringTime()), new Text(), btnReturn);

                // Mile Splits
                for (int i = 1; i <= miSplits; i++) {
                    grid.add(new Text(i + "mi"), 2, i);
                    grid.add(new Text(toStringTime(i) + ""), 3, i);
                }
 
                pane.getChildren().clear();
                pane.getChildren().add(grid);
            }
        }
        /// Button Events
        btnCalTime.setOnAction(e -> {
                setTime(Double.parseDouble(tfPaceMin.getText()),
                    Double.parseDouble(tfPaceSec.getText()),
                    getDistance());
                Splits splits = new Splits(paceUnit);            
        });

        btnCalPace.setOnAction(e -> {
                setDistance(tfDistance);
                setPace(Double.parseDouble(tfTimeHr.getText()),
                    Double.parseDouble(tfTimeMin.getText()),
                    Double.parseDouble(tfTimeSec.getText()), getDistance());
                PopularEvents displayGrid = new PopularEvents(paceUnit);
        });

        btnReturn.setOnAction(e -> {
            pane.getChildren().clear();
            Entry entry = new Entry();
        });

        // Scene and Stage
        Entry entry = new Entry();
        Scene scene = new Scene(pane, 700, 250);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Split Calculator");
        primaryStage.show();
    }
    
    /// Pace Methods
    public void setPace(double hr, double min, double sec, double distance) {
        pace =  (hr * 3600 + min * 60 + sec) / distance;
    }

    public String toStringPace() {
        return (int)(pace / 60 % 60) + ":" + (double)Math.round(pace % 60 * 100) / 100;
    }
    public String toStringPace(double m) {
        double tempPace = m * pace;
        return (int)(tempPace / 60 % 60) + ":" +  (double)Math.round(tempPace % 60 * 100) / 100;
    }

    public void paceToKm() {
        pace *= 1.609;
    }

    public void paceToMi() {
        pace *= 0.6214;
    }

    /// Time methods
    public void setTime(double min, double sec, double distance) {
        time = (min * 60 + sec) * distance;
    }

    public String toStringTime() {
        return (int)(time / 3600 % 60) + ":" + (int)(time / 60 % 60) + ":" + (int)(time % 60);
    }
    public String toStringTime(double m) {
        double tempTime = time * m;
        return (int)(tempTime / 3600 % 60) + ":" + (int)(tempTime / 60 % 60) + ":" + (int)(tempTime % 60);
    }
    /// Distance methods
    public void setDistance(TextField tf) {
        distance = Double.parseDouble(tf.getText());
    }
    public double getDistance() {
        return distance;
    }
}