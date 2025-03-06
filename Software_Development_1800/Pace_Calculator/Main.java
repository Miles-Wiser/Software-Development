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
    String pace;
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        GridPane entryGrid = new GridPane(10, 15);
    
        /// Time properties
        final Text TXT_TIME_ENTRY = new Text("Enter a Time");
        TextField tfTimeEntryHr = new TextField("01");
        TextField tfTimeEntryMin = new TextField("30");
        TextField tfTimeEntrySec = new TextField("25");

        /// Pace properties
        final Text TXT_PACE_ENTRY = new Text("Enter a Pace");
        TextField tfPaceEntryMin = new TextField("08");
        TextField tfPaceEntrySec = new TextField("30");
        final ComboBox<String> CBO_PACE_ENTRY_UNIT = new ComboBox<>();
        final Text TXT_PACE_ENTRY_UNIT = new Text("/min");
    
        /// Distance properties
        final Text TXT_DISTANCE_ENTRY = new Text("Enter a Distance");
        TextField tfDistance = new TextField("08");
        final ComboBox<String> CBO_DISTANCE_ENTRY = new ComboBox<>();
    
        // Calculate properties
        Button btnCalTime = new Button("Calculate Time");
        Button btnCalPace = new Button("Calculate Pace");
    
        // Display Grid
        entryGrid.addRow(0, TXT_TIME_ENTRY, tfTimeEntryHr, tfTimeEntryMin, tfTimeEntrySec);
    
        CBO_PACE_ENTRY_UNIT.getItems().addAll("m", "km", "mi");
        entryGrid.addRow(1, TXT_PACE_ENTRY, tfPaceEntryMin, tfPaceEntrySec, CBO_PACE_ENTRY_UNIT, TXT_PACE_ENTRY_UNIT);
            
        CBO_DISTANCE_ENTRY.getItems().addAll("m", "km", "mi");
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
                setPace(TIME, DISTANCE);
                System.out.println(getPace());
            }
        });
    }
    
    public void setTime(double p, double d) {
        time = p * d;
    }
    public double getTime() {
        return time;
    }

    public void setPace(double t, double d) {
        int minute = (int)(t / d) / 60 % 60;
        int seconds = (int)(t / d) % 60;
        pace = minute + " min " + seconds + "seconds /mi";
    }
    public String getPace() {
        return pace;
    }
}