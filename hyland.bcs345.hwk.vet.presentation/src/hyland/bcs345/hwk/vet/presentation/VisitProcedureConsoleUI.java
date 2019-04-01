package hyland.bcs345.hwk.vet.presentation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

import hyland.bcs345.hwk.vet.business.VisitProcedure;
/**
 * Contains the VisitProcedureConsoleUI class. This class contains the ShowUI method. 
 * This method contains the user program for the VisitProcedure. 
 * It shows the user interface and handles user choices.
 * 
 * @author Danielle Hyland
 * @version 1.1
 * @since 10/15/18
 *
 */
public class VisitProcedureConsoleUI {
	
	//Shows the user interface and handles user choices
	public void ShowUI() { 
		
		Scanner keyboardScanner = new Scanner (System.in);
		int choice = 0;
		VisitProcedure myVisit = new VisitProcedure();
		String outFilename;
		String inFilename;

		
		while (choice != 5) {
			System.out.println("Visit Procedure UI\n");
			System.out.println("------------------");
			System.out.println("1 – Read visit procedure from file");
			System.out.println("2 – Write visit procedure to file");
			System.out.println("3 – Show visit procedure data with descriptive text on screen");
			System.out.println("4 – Show visit procedure JSON on screen");
			System.out.println("5 - Exit");
			System.out.println("Enter Choice:");
			
			choice = keyboardScanner.nextInt();

			
			switch(choice) 
			{
				//Reads in one visit procedure’s data into a VisitProcedure instance from a user-specified file. 
				case 1:
					//Gets the input filename from the user
					System.out.println("Enter the filename you would like to open");
					inFilename = keyboardScanner.next();
					
					Scanner inputScanner = null;
					
					//Opens the input file and checks for exceptions
					try {
						inputScanner = new Scanner(new FileReader(inFilename));
					}
					catch (FileNotFoundException fnf) {
						System.out.println("ERROR! The specified file could not be found");
					}
			
					myVisit.Read(inputScanner);
					
					break;
				
				//Writes data from the VisitProcedure instance to a user-specified file	
				case 2:
					//Gets the output filename from the user
					System.out.printf("Enter the output filename: ");
					outFilename = keyboardScanner.next();
					
					//Opens the output file and checks for FileNotFound Exceptions
					PrintStream outFile = null;
					
					try{
						outFile = new PrintStream(outFilename);
					}
					catch (FileNotFoundException fnf){
						System.out.println("ERROR! The output file could not be opened!");
					}
					
					myVisit.Write(outFile);
					break;
				
				//Show all data from the VisitProcedure instance data on the screen. 	
				case 3:
					String toString = myVisit.toString();
					System.out.printf("\n%s\n\n",toString);
					break;
				
				//Show VisitProcedure JSON output on the screen.
				case 4:
					String json = myVisit.GetJSON();
					System.out.printf("\n%s\n\n",json);
					break;
			}	
		}

	}
}
