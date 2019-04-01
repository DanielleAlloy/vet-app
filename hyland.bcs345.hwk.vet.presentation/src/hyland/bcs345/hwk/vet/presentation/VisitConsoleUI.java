package hyland.bcs345.hwk.vet.presentation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

import hyland.bcs345.hwk.vet.business.Visit;
import hyland.bcs345.hwk.vet.business.VisitProcedure;

/**
 * 
 * Contains the VisitConsoleUI class. This class contains the ShowUI method. 
 * ShowUI contains the main program for the VisitConsoleUI. 
 * It shows the user interface and handles user choices.
 *  
 * @author Danielle Hyland
 * @version 1.0
 * @since 11/16/18
 * 
 */
public class VisitConsoleUI {
	//Shows the user interface and handles user choices
	public void ShowUI() { 
		
		Scanner keyboardScanner = new Scanner (System.in);
		PrintStream ps = new PrintStream (System.out);
		int choice = 0;
		Visit myVisit = new Visit();
		String outFilename;
		String inFilename;

		//Menu is displayed while the user does not select 8
		while (choice != 8) {
			System.out.println("Visit UI");
			System.out.println("------------------");
			System.out.println("1 – Read visit info from file");
			System.out.println("2 – Write visit info to file");
			System.out.println("3 – Show visit procedure by index");
			System.out.println("4 – Show visit procedure with highest amount due");
			System.out.println("5 - Show visit report on screen");
			System.out.println("6 - Show visit as JSON string on screen");
			System.out.println("7 - Show visit toString on screen");
			System.out.println("8 - Exit");

			System.out.println("Enter Choice:");
			
			choice = keyboardScanner.nextInt();
			
			//Input validation for choice
			if (choice < 1 || choice > 8) {
				System.out.println("Sorry, that is not a valid choice. Please try selecting a number between 1-8");
			}

			//Switch Statement that handles and processes users choices 
			switch(choice) 
			{
				//Reads in data for the instance of Visit from a user-specified file. 
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
						System.err.println("ERROR! The specified file could not be found");
					}
					
					myVisit.Read(inputScanner);
					
					break;
				
				//Writes all data from the Visit instance to a user-specified file	
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
				
				//Show VisitProcedure by index 	
				case 3:
					
					int index = 0;

					try {
						System.out.println("Enter an index of VisitProcedure");
						index = keyboardScanner.nextInt();
						
						VisitProcedure visitByIndex = myVisit.GetByIndex(index);
						
						System.out.println(visitByIndex.toString());
					}
					catch (Exception ofb) {
						System.err.println("That is not a valid index of this VisitProcedure Array.");
						
					}
					break;
				
				//Shows VisitProcedure with highest amount due
				case 4:
					 VisitProcedure highestVisit = myVisit.GetHighestProcedureAmountDue();
					 
					 System.out.println("\nThe details of the procedure that had the highest amount due are:");
					 System.out.printf("%s\n\n",highestVisit.toString());

					break;
					
				//Shows the Visit report on the screen
				case 5:
					myVisit.Report(ps);
					break;
				
				//Shows Visit as JSON string
				case 6:
					String json = myVisit.GetJSON();
					System.out.printf("\n%s\n\n",json);
					break;	
				
				//Shows what Visit toString returns on screen
				case 7:
					String toString = myVisit.toString();
					System.out.printf("\n%s\n\n",toString);
					break;	
			}	
		}

	}

}
