package perso.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import perso.Personalausweis;

public class GUI extends Application {
	
	private static final String PATH_ICON = "icon.png";
	
	private static final String PATH_ICON_INFO = "info_64.png";
	
	private Image icon = new Image(PATH_ICON);
	
	private Image iconInfo = new Image(PATH_ICON_INFO);

	private TextField behoerdenkennzahl;

	private TextField laufendeNummer;

	private DatePicker geburtsdatum;

	private DatePicker ablaufdatum;

	private ComboBox<String> staatsangehoerigkeit;

	private CheckBox vorlaeufig;

	private Label label1;

	private Label label2;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		int row = 0;

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10.0, 0.0, 0.0, 10.0));
		grid.setHgap(10);
		grid.setVgap(10);
		// grid.setGridLinesVisible(true);
//		ColumnConstraints col1 = new ColumnConstraints();
//		col1.setPercentWidth(40);
//		ColumnConstraints col2 = new ColumnConstraints();
//		col2.setPercentWidth(40);
//		ColumnConstraints col3 = new ColumnConstraints();
//		col3.setPercentWidth(20);
//		grid.getColumnConstraints().addAll(col1, col2, col3);

		grid.add(new Label("Behördenkennzahl:"), 0, row);
		behoerdenkennzahl = new TextField();
		behoerdenkennzahl.textProperty().addListener((obs, oldText, newText) -> {
			if (newText.length() > 4) {
				behoerdenkennzahl.setText(oldText);
			}
			if (!newText.isEmpty() && !newText.matches("[LMNPRTVWXYlmnprtvwxy][0-9A-Za-z]*")) {
				behoerdenkennzahl.setText(oldText);
			}
			behoerdenkennzahl.setText(behoerdenkennzahl.getText().toUpperCase());
		});
		grid.add(behoerdenkennzahl, 1, row);
		Button behoerdenkennzahlHilfe = new Button("?");
		behoerdenkennzahlHilfe.setOnAction(e -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(iconInfo);
			alert.setTitle("Behördenkennziffer");
			alert.setHeaderText(null);
			alert.setContentText("Die Seriennummer beginnt zur Unterscheidung unterschiedlicher Dokumententypen zwingend mit einem Buchstaben. Bei Personalausweisen beginnt die Seriennummer mit den Buchstaben L, M, N, P, R, T, V, W, X, Y. Bei Reisepässen sowie Dienst- und Diplomatenpässen beginnt die Seriennummer mit den Buchstaben C, F, G, H, J, K. Vorläufige Reisepässe beginnen mit A oder B, Kinderpässe beginnen mit E, G oder F.");
			alert.showAndWait();
		});
		grid.add(behoerdenkennzahlHilfe, 2, row);
		row++;

		grid.add(new Label("laufende Nummer:"), 0, row);
		laufendeNummer = new TextField();
		laufendeNummer.textProperty().addListener((obs, oldText, newText) -> {
			if (newText.length() > 5) {
				laufendeNummer.setText(oldText);
			}
			if (!newText.matches("[0-9CFGHJKLMNPRTVWXYZcfghjklmnprtvwxyz]*")) {
				laufendeNummer.setText(oldText);
			}
			laufendeNummer.setText(laufendeNummer.getText().toUpperCase());
		});
		grid.add(laufendeNummer, 1, row);
		Button laufendeNummerHilfe = new Button("?");
		laufendeNummerHilfe.setOnAction(e -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(iconInfo);
			alert.setTitle("Behördenkennziffer");
			alert.setHeaderText(null);
			alert.setContentText("Die Seriennummer in neuen seit November 2010 ausgegebenen deutschen Personalausweisen und Reisepässen setzt sich aus den Ziffern 0 bis 9 und ausgewählten Buchstaben des lateinischen Alphabets zusammen, wobei insgesamt 27 alphanumerische Zeichen Verwendung finden. Zur Vermeidung sinntragender Wörter wurde auf die Verwendung von Vokalen und bestimmten Buchstaben (B, D, Q, S) verzichtet. Die Seriennummern werden demnach aus den folgenden Zeichen gebildet: 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, C, F, G, H, J, K, L, M, N, P, R, T, V, W, X, Y und Z. Somit handelt es sich bei dem Zeichen 0 immer um die Ziffer Null.");
			alert.showAndWait();
		});
		grid.add(laufendeNummerHilfe, 2, row);
		Button zufall = new Button("Zufall");
		zufall.setOnAction(e -> laufendeNummer.setText(Personalausweis.getZufallLaufendeNummer()));
		grid.add(zufall, 3, row);
		row++;

		grid.add(new Label("Geburtsdatum:"), 0, row);
		geburtsdatum = new DatePicker();
//		geburtsdatum.setPromptText("TT.MM.JJJJ");
		geburtsdatum.setEditable(false);
//		geburtsdatum.setValue(LocalDate.now());
		grid.add(geburtsdatum, 1, row);
		row++;

		grid.add(new Label("Ablaufdatum:"), 0, row);
		ablaufdatum = new DatePicker();
//		ablaufdatum.setPromptText("TT.MM.JJJJ");
		ablaufdatum.setEditable(false);
//		ablaufdatum.setValue(LocalDate.now());
		grid.add(ablaufdatum, 1, row);
		Button ablaufdatumHilfe = new Button("?");
		ablaufdatumHilfe.setOnAction(e -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(iconInfo);
			alert.setTitle("Ablaufdatum");
			alert.setHeaderText(null);
			alert.setContentText("Ein Personalausweis für Personen ab 24 Jahren ist 10 Jahre gültig, für jüngere nur 6 Jahre. Ein vorläufiger Personalausweis ist höchstens 3 Monate gültig.");
			alert.showAndWait();
		});
		grid.add(ablaufdatumHilfe, 2, row);
		row++;

		grid.add(new Label("Staatsangehörigkeit:"), 0, row);
		ObservableList<String> options = FXCollections.observableArrayList("deutsch");
		staatsangehoerigkeit = new ComboBox<String>(options);
		staatsangehoerigkeit.setValue(options.get(0));
		grid.add(staatsangehoerigkeit, 1, row);
		row++;

		grid.add(new Label("vorläufig:"), 0, row);
		vorlaeufig = new CheckBox();
		grid.add(vorlaeufig, 1, row);
		row++;

		Button btn = new Button("berechne");
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (behoerdenkennzahl.getText().length() < 4) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Behördenkennziffer zu kurz");
					alert.setHeaderText(null);
					alert.setContentText("Bitte geben Sie eine 4-stellige Behördenkennziffer ein!");
					alert.showAndWait();
					return;
				}
				if (laufendeNummer.getText().length() < 5) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("laufende Nummer zu kurz");
					alert.setHeaderText(null);
					alert.setContentText("Bitte geben Sie eine 5-stellige laufende Nummer ein!");
					alert.showAndWait();
					return;
				}
				if  (geburtsdatum.getValue() == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("fehlendes Geburtsdatum");
					alert.setHeaderText(null);
					alert.setContentText("Bitte wählen Sie ein Geburtsdatum!");
					alert.showAndWait();
					return;
				}
				if  (ablaufdatum.getValue() == null) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("fehlendes Ablaufdatum");
					alert.setHeaderText(null);
					alert.setContentText("Bitte wählen Sie ein Ablaufdatum!");
					alert.showAndWait();
					return;
				}
				Personalausweis perso = new Personalausweis(behoerdenkennzahl.getText(),
						laufendeNummer.getText(), geburtsdatum.getValue(), ablaufdatum.getValue(),
						'D', vorlaeufig.isSelected());
				String[] ersteNumemr = perso.getErsteNummer();
				String ersteZeile = new String();
				for (String string : ersteNumemr) {
					ersteZeile += string;
				}
				label1.setText(ersteZeile);
				label1.setFont(new Font("Lucida Console", label1.getFont().getSize()));
				String[] zweiteNummer = perso.getZweiteNummer();
				String zweiteZeile = new String();
				for (String string : zweiteNummer) {
					zweiteZeile += string;
				}
				label2.setText(zweiteZeile);
				label2.setFont(new Font("Lucida Console", label2.getFont().getSize()));
			}
		});
		HBox hbBtn = new HBox(10);
		hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn.getChildren().add(btn);
		grid.add(hbBtn, 1, row);
		row++;

		label1 = new Label();
		grid.add(label1, 0, row);
		row++;

		label2 = new Label();
		grid.add(label2, 0, row);
		row++;

		BorderPane border = new BorderPane(grid);
		border.setTop(initMenuBar());
		// border.setBottom(statusBar);

		Scene scene = new Scene(border);
//
//		btn.getScene().getAccelerators().put(new KeyCodeCombination(KeyCode.ENTER), new Runnable() {
//			
//			@Override
//			public void run() {
//				btn.fire();
//			}
//		});
//		
		primaryStage.setScene(scene);
		// primaryStage.setResizable(false);
		primaryStage.setTitle("JPerso");
		primaryStage.getIcons().add(icon);
		primaryStage.show();
	}

	private Node initMenuBar() {
		MenuItem beenden = new MenuItem("_Beenden");
		beenden.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
		beenden.setOnAction(e -> Platform.exit());
		Menu datei = new Menu("_Datei", null, beenden);
		MenuItem hilfe = new MenuItem("_Hilfe anzeigen");
		hilfe.setAccelerator(KeyCombination.keyCombination("F1"));
		hilfe.setOnAction(e -> {
			getHostServices().showDocument("https://de.wikipedia.org/wiki/Ausweisnummer");
		});
		MenuItem info = new MenuItem("_Info");
		info.setOnAction(e -> {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setGraphic(new ImageView(icon));
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("About");
			alert.setHeaderText(MetaInfo.TITLE + " " + MetaInfo.VERSION + " by " + MetaInfo.AUTHOR);
			alert.setContentText(
					"Email: " + MetaInfo.EMAIL + '\n' + "Repository: " + MetaInfo.REPOSITORY);
			alert.showAndWait();
		});
		Menu q = new Menu("_?", null, hilfe, new SeparatorMenuItem(), info);
		return new MenuBar(datei, q);
	}

	public static void main(String[] args) {
		launch(args);
	}

}
