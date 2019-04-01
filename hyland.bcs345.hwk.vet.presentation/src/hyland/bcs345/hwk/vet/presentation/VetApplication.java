package hyland.bcs345.hwk.vet.presentation;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

import hyland.bcs345.hwk.vet.business.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Contains the VetApplication Class, which is extending the Application Class
 * This contains the code for the GUI 
 * 
 * @author DanielleAlloy
 * @since 12/11/18
 * @version 1.0
 *
 */
public class VetApplication extends Application {
	//***********************************************
	//Member Variables for the VisitApplication Class
	//***********************************************

	private Visit Visit;
	
	private VBox vbox;

	//MenuBar Member variables
	private MenuBar menuBar;
	private Menu fileMenu;
	private SeparatorMenuItem sep ;
	private MenuItem openMenuItem;
	private MenuItem saveAsMenuItem;
	private MenuItem saveReportMenuItem;
	private MenuItem exportJSONMenuItem;
	private MenuItem exitMenuItem;
	
	//TabPane Member Variable
	private TabPane tabPane;
	private Tab overviewTab;
	private Tab visitProcedureTab;

	private ListView<String> list;
	private ObservableList<String> items = FXCollections.observableArrayList ();
	
	private FileChooser fileChooser;

	private File file;
	private String inputFileName;
	private Scanner inputScanner;
	
	private FileOutputStream fileOut;
	private FileInputStream fileIn;
	
	
	private TextField veterinarianText;
	private TextField petNameText;
	private TextField petSpeciesText;
	private TextField petGenderText;
	private TextField totalProcedureAmountText;
	private TextField totalProcedureAmountCoveredText;
	private TextField totalProcedureAmountDueText;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		vbox = new VBox();
		Visit = new Visit();
		
		//*************************
		//MenuBar Setup
		//*************************
		menuBar = new MenuBar();
		vbox.getChildren().add(menuBar);
		
		sep = new SeparatorMenuItem();
		
		fileMenu = new Menu("File");
		menuBar.getMenus().add(fileMenu);
		
		//*********************
		//Open Menu Item Setup
		//*********************
		openMenuItem = new MenuItem("Open...");
		openMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				
				//Creates a new instance of FileChooser 
				fileChooser = new FileChooser();
				//Sets Title for the FileChooser window 
				fileChooser.setTitle("Open Visit File");
				
				//Adds the FileChooser to the Stage
				
				file = fileChooser.showOpenDialog(primaryStage);
				
				//Handles the NullPointerException if user presses cancel on the FileChooser window
				//Will only continue if a filename was entered in the FileChooser and cancel was not selected
				if (file != null) {
					inputFileName = file.getName();
					
					try {
						inputScanner = new Scanner(new FileReader(inputFileName));
					} catch (FileNotFoundException e) {
						System.err.println("ERROR! The specified file could not be found");
					}
					
					//Calls the Read method inside the Visit Class to read the data from the file into Visit and populate member variables
					Visit.Read(inputScanner);
					
					veterinarianText.setText(Visit.GetVeterinarian());
					petNameText.setText(Visit.GetAppointment().GetPetName());
					petSpeciesText.setText(Visit.GetAppointment().GetPetSpecies());
					petGenderText.setText(Visit.GetAppointment().GetPetGender());
					
					totalProcedureAmountText.setText(String.valueOf(Visit.CalculateProcedureAmount()));
					totalProcedureAmountCoveredText.setText(String.valueOf(Visit.CalculateProcedureAmountCovered()));
					totalProcedureAmountDueText.setText(String.valueOf(Visit.CalculateProcedureAmountDue()));

					//Clears the items in the Observable List
					items.clear();
					
					//Loops through the VisitProcedure Array
					for (int i=0; i < Visit.GetVisitProcedureArray().length; i++) {
						//Add a data to the ObservableCollection
						items.add(i,Visit.GetByIndex(i).toString());
						}
					}
				}
			}
		);
		//Add openMenuItem to Menu
		fileMenu.getItems().add(openMenuItem);
		
		// Add separator menu item to menu.
		fileMenu.getItems().add(sep);

		//***********************
		//Save As Menu Item Setup
		//***********************
		saveAsMenuItem = new MenuItem("Save As...");
		saveAsMenuItem.setOnAction(new EventHandler<ActionEvent>() {	
			public void handle(ActionEvent t) {

				//Creates a new instance of FileChooser 
				FileChooser fileChooser = new FileChooser();
				//Sets Title for the FileChooser window 
				fileChooser.setTitle("Save As Visit");
				
				
				//Opens a FileChooser prompt and passes the user entered filename into FileOutputStream
				try {
					fileOut = new FileOutputStream(fileChooser.showSaveDialog(primaryStage));
				} 
				catch (FileNotFoundException e) {
					System.err.println("ERROR! The specified file could not be found");
				}
				//Handles the NullPointerException if user presses cancel on the FileChooser window
				catch (NullPointerException npe) {
					return;
				}
				
				//As long as the filename isn't null, passes FileOutputStream into an instance of PrintStream, 
				//and then passes that instance of PrintStream into Visit.Write method to write data from 
				//Visit instance to the selected file 
				if (fileOut != null) { 
					PrintStream outFile = new PrintStream(fileOut);
					Visit.Write(outFile);
					}
				}
			}
		);
		//Add saveAsMenuItem to Menu
		fileMenu.getItems().add(saveAsMenuItem);

		//***************************
		//Save Report Menu Item Setup
		//***************************
		saveReportMenuItem = new MenuItem("Save Report...");
		saveReportMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				
				//Creates a new instance of FileChooser 
				FileChooser fileChooser = new FileChooser();
				//Sets Title for the FileChooser window 
				fileChooser.setTitle("Save Visit Report");
				
				//Opens a FileChooser prompt and passes the user entered filename into FileOutputStream
				try {
					fileOut = new FileOutputStream(fileChooser.showSaveDialog(primaryStage));
				} 
				catch (FileNotFoundException e) {
					System.err.println("ERROR! The specified file could not be found");
				}
				//Handles the NullPointerException if user presses cancel on the FileChooser window
				catch (NullPointerException npe) {
					return;
				}
				
				//As long as the filename isn't null, passes FileOutputStream into an instance of PrintStream, 
				//and then passes that instance of PrintStream into Visit.Report method to 
				//write a properly formatted report to the selected file
				if (fileOut != null) { 
					PrintStream outFile = new PrintStream(fileOut);
					Visit.Report(outFile);
					}
				}
		  	}
		);
		//Add saveReportMenuItem to Menu
		fileMenu.getItems().add(saveReportMenuItem);		
		
		// Add separator menu item to menu.
		sep = new SeparatorMenuItem();
		fileMenu.getItems().add(sep);
		
		//******************************
		//Export as JSON Menu Item setup
		//******************************
		exportJSONMenuItem = new MenuItem("Export As JSON...");
		exportJSONMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {

				//Creates a new instance of FileChooser 
				FileChooser fileChooser = new FileChooser();
				//Sets Title for the FileChooser window 
				fileChooser.setTitle("Export As JSON");
				
				//Opens a FileChooser prompt and passes the user entered filename into FileOutputStream
				try {
					fileOut = new FileOutputStream(fileChooser.showSaveDialog(primaryStage));
				} 
				catch (FileNotFoundException e) {
					System.err.println("ERROR! The specified file could not be found");
				}
				//Handles the NullPointerException if user presses cancel on the FileChooser window
				catch (NullPointerException npe) {
					return;
				}
				
				//As long as the filename isn't null, passes the FileOutputStream into an instance of PrintStream, 
				//gets the JSON data for the given instance of Visit, then writes that JSON data to selected file
				if (fileOut != null) { 
					PrintStream outFile = new PrintStream(fileOut);
					String json = Visit.GetJSON();
					
					outFile.print(json);
					}
				}
			}
		);
		//Add exportJSONMenuItem to Menu
		fileMenu.getItems().add(exportJSONMenuItem);	
		
		// Add separator menu item to menu.
		sep = new SeparatorMenuItem();
		fileMenu.getItems().add(sep);
		
		//********************
		//Exit Menu Item Setup
		//********************
		exitMenuItem = new MenuItem("Exit");
		exitMenuItem.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				//Exits out of the GUI window
				Platform.exit();
            	}
		  	}
		);
		//Add exitMenuItem to Menu
		fileMenu.getItems().add(exitMenuItem);
		
		//*************************
		//TabPane Setup
		//*************************
		
		// Create a new TabPane control
		tabPane = new TabPane();
		
		//Add TabPane as a child in the scene
		vbox.getChildren().add(tabPane);

		//*********************
		//Overview Tab Setup
		//*********************
		//Creates Overview Tab
		overviewTab = new Tab();
		//Sets Label for Overview Tab
		overviewTab.setText("Overview");
		
		//Root pane for the tab
		//VBox overviewVBox = new VBox();
		GridPane overViewPane = new GridPane();
		overViewPane.setPadding(new Insets (10,10,10,10)); 
		//Sets the vertical spacing between rows
		overViewPane.setVgap(8);
		//Sets the horizontal spacing between columns
		overViewPane.setHgap(100);

		// Setup the Labels and TextFields for Overview Tab		
		Label veterinarianLabel = new Label("Veterinarian Name");
		veterinarianText = new TextField(Visit.GetVeterinarian());
		Label petNameLabel = new Label("Pet Name");
		petNameText = new TextField(Visit.GetAppointment().GetPetName());
		Label petSpeciesLabel = new Label("Pet Species");
		petSpeciesText = new TextField(Visit.GetAppointment().GetPetSpecies());
		Label petGenderLabel = new Label("Pet Gender");
		petGenderText = new TextField(Visit.GetAppointment().GetPetGender());
		
		
		Label totalProcedureAmountLabel = new Label("Total Procedure Amount");
		totalProcedureAmountText = new TextField(String.valueOf(Visit.CalculateProcedureAmount()));
		Label totalProcedureAmountCoveredLabel = new Label("Total Procedure Amount Covered");
		totalProcedureAmountCoveredText = new TextField(String.valueOf(Visit.CalculateProcedureAmountCovered()));
		Label totalProcedureAmountDueLabel = new Label("Total Procedure Amount Due");
		totalProcedureAmountDueText = new TextField(String.valueOf(Visit.CalculateProcedureAmountDue()));

		//Add the Labels and TextFields to the OverViewPane
		overViewPane.add(veterinarianLabel, 0, 0);
		overViewPane.add(veterinarianText, 1, 0);
		overViewPane.add(petNameLabel, 0, 1);
		overViewPane.add(petNameText, 1, 1);
		overViewPane.add(petSpeciesLabel, 0, 2);
		overViewPane.add(petSpeciesText, 1, 2);
		overViewPane.add(petGenderLabel, 0, 3);
		overViewPane.add(petGenderText, 1, 3);
		overViewPane.add(totalProcedureAmountLabel, 0, 4);
		overViewPane.add(totalProcedureAmountText, 1, 4);
		overViewPane.add(totalProcedureAmountCoveredLabel, 0, 5);
		overViewPane.add(totalProcedureAmountCoveredText, 1, 5);
		overViewPane.add(totalProcedureAmountDueLabel, 0, 6);
		overViewPane.add(totalProcedureAmountDueText, 1, 6);

		// Set the GridPane as the root of the Overview tab
		overviewTab.setContent(overViewPane);

		// Add the Overview tab to the tab pane
		tabPane.getTabs().add(overviewTab);
		
		//Removes the X from table header and will not allow tab to be closed
		overviewTab.setClosable(false);

		//**************************
		//Visit Procedure Tab Setup
		//**************************
		//Create the Visit Procedure Data Tab
		visitProcedureTab = new Tab();
		//Sets Label for Overview Tab
		visitProcedureTab.setText("Visit Procedure Data");
		
		//Root pane for the tab
		VBox visitProcedureVBox = new VBox();
		
		// Set the VBox as the root of the Overview tab
		visitProcedureTab.setContent(visitProcedureVBox);
		
		// Add the Overview tab to the tab pane
		tabPane.getTabs().add(visitProcedureTab);
		
		//Removes the X from table header and will not allow tab to be closed
		visitProcedureTab.setClosable(false);

		//*************************
		//ListView Setup
		//*************************
		
		// Creates a ListView of String
		list = new ListView<String>();
		
		// Holds data that will appear in the ListView
		//items = FXCollections.observableArrayList (); 

		// Associate with the ListView
		list.setItems(items);

		//Loops through the VisitProcedure Array
		for (int i=0; i < Visit.GetVisitProcedureArray().length; i++) {
			//Add a data to the ObservableCollection
			items.add(i,Visit.GetByIndex(i).toString());
			}
		
		// Sets the orientation of list items to vertical
		list.setOrientation(Orientation.VERTICAL);
		//}
		
		//Adds list to the VBox
		visitProcedureVBox.getChildren().add(list);

		//*************************
		//Scene Setup
		//*************************
		Scene scene = new Scene(vbox, 500, 500);
		
		primaryStage.setScene(scene);
		//Adds a title to the Window
		primaryStage.setTitle("Vet Application");

		primaryStage.show();
	}

}
