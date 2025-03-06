import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class EntryGrid extends GridPane {
    private GridPane entryGrid = new GridPane(10, 15);

    /// Time properties
    private final Text TXT_TIME_ENTRY = new Text("Enter a Time");
    private TextField tfTimeEntryHr = new TextField("01");
    private TextField tfTimeEntryMin = new TextField("30");
    private TextField tfTimeEntrySec = new TextField("25");

    /// Pace properties
    private final Text TXT_PACE_ENTRY = new Text("Enter a Pace");
    private TextField tfPaceEntryMin = new TextField("08");
    private TextField tfPaceEntrySec = new TextField("30");
    private final ComboBox<String> CBO_PACE_ENTRY_UNIT = new ComboBox<>();
    private final Text TXT_PACE_ENTRY_UNIT = new Text("/min");

    /// Distance properties
    private final Text TXT_DISTANCE_ENTRY = new Text("Enter a Distance");
    private TextField tfDistance = new TextField("08");
    private final ComboBox<String> CBO_DISTANCE_ENTRY = new ComboBox<>();

    // Calculate properties
    private Button btnCalTime = new Button("Calculate Time");
    private Button btnCalPace = new Button("Calculate Pace");
    
    public EntryGrid() {
        entryGrid.addRow(0, TXT_TIME_ENTRY, tfTimeEntryHr, tfTimeEntryMin, tfTimeEntrySec);

        CBO_PACE_ENTRY_UNIT.getItems().addAll("m", "km", "mi");
        entryGrid.addRow(1, TXT_PACE_ENTRY, tfPaceEntryMin, tfPaceEntrySec, CBO_PACE_ENTRY_UNIT, TXT_PACE_ENTRY_UNIT);

        CBO_DISTANCE_ENTRY.getItems().addAll("m", "km", "mi");
        entryGrid.addRow(2, TXT_DISTANCE_ENTRY, tfDistance, CBO_DISTANCE_ENTRY);

        entryGrid.add(btnCalTime, 2, 3);
        entryGrid.add(btnCalPace, 3, 3);

        getChildren().add(entryGrid);
    }

    public double calcTime() {
        final double PACE = Double.parseDouble(tfPaceEntryMin.getText()) +
            Double.parseDouble(tfPaceEntrySec.getText()) / 60;
        final double DISTANCE = Double.parseDouble(tfDistance.getText());

        double time = PACE * DISTANCE;
        btnCalTime.setOnAction(e -> {
            if (CBO_DISTANCE_ENTRY.getValue() != null && CBO_PACE_ENTRY_UNIT.getValue() != null)
                System.out.println(time + " minutes");
            });

        return time;
    }
}
