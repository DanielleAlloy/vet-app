//****************************
//Very good work.
//
//Commenting. Should have used Javadoc comments above methods in Visit.java (not normal comments).
//
//Note: Coding style observation. Instead of using lines 108-118 to get/print the data for the VisitProcedure you could just use the following line instead:
//System.out.println(visitByIndex.toString());
//*****************************
package hyland.bcs345.hwk.vet.presentation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.Scanner;

import hyland.bcs345.hwk.vet.business.Appointment;
import hyland.bcs345.hwk.vet.business.Pet;
import hyland.bcs345.hwk.vet.business.Procedure;
import hyland.bcs345.hwk.vet.business.Visit;
import hyland.bcs345.hwk.vet.business.VisitProcedure;

/**
 * Contains the main program code. 
 * 
 * @author Danielle Hyland
 * @version 1.3
 * @since 11/15/2018
 */

public class Main {
/**
 * Displays a menu to the user, that will create instances of VisitProcedureConsoleUI or VisitConsoleUI 
 * and then calls ShowUI on the corresponding instance
 * 
 * @author Danielle Hyland
 * @version 1.3
 * @since 11/15/2018
 * @param args
 */
	public static void main(String[] args)  {
		
		Scanner keyboardScanner = new Scanner(System.in);
		int choice = 0;
		
		//Shows the user interface and handles choices
		while (choice != 4) {
			System.out.println("Choose UI");
			System.out.println("---------");
			System.out.println("1 – VisitProcedureConsoleUI");
			System.out.println("2 – VisitConsoleUI");
			System.out.println("3 – VetGraphicalUI");
			System.out.println("4 – Exit");
			
			System.out.println("Enter Choice:");
			
			choice = keyboardScanner.nextInt();
			
			//Input Validation for choice
			if (choice < 1 || choice > 4) {
				System.out.println("Sorry, that is not a valid choice. Please try selecting a number between 1-3");
			}
			
			//Switch Statement that handles and processes users choices 
			switch(choice) {
				
				//Creates an instance of VisitProcedureConsoleUI and calls ShowUI on it
				case 1:
					VisitProcedureConsoleUI newProcedureProgram = new VisitProcedureConsoleUI(); 
					newProcedureProgram.ShowUI();
					break;
					
				//Creates an instance of VisitConsoleUI and calls ShowUI on it
				case 2:
					VisitConsoleUI newProgram = new VisitConsoleUI();
					newProgram.ShowUI();
					break;
					
				//Creates an instance of VisitConsoleUI and calls ShowUI on it
				case 3:
					VetGraphicalUI newGraphicalProgram = new VetGraphicalUI();
					newGraphicalProgram.ShowUI();
					break;
			}	
			
		}

		
		//Code from Previous Versions below
		/*
		VisitProcedureConsoleUI newProgram = new VisitProcedureConsoleUI(); 
		//Calls ShowUI, which will display the user interface and handle user choices
		newProgram.ShowUI();
		*/
	
		/*Testing for the Appointment Class
		//START Automated Testing of the Appointment Class
		
		Appointment myAppointment = new Appointment();
		
		//Tests the Get/Set methods for the Month member variable in Appointment Class
		int testMonth = 10;
		myAppointment.SetMonth(testMonth);
	
		if (testMonth == myAppointment.GetMonth()){
			System.out.println("Appointment Get/Set Month: PASS");
		}
		else {
			System.out.println("Appointment Get/Set Month: FAIL!");
		}
		
		//Tests the Get/Set methods for the Day member variable in Appointment Class
		int testDay = 15;
		myAppointment.SetDay(testDay);
	
		if (testDay == myAppointment.GetDay()){
			System.out.println("Appointment Get/Set Day: PASS");
		}
		else {
			System.out.println("Appointment Get/Set Day: FAIL!");
		}
		
		//Tests the Get/Set methods for the Year member variable in Appointment Class
		int testYear = 2018;
		myAppointment.SetYear(testYear);
	
		if (testYear == myAppointment.GetYear()){
			System.out.println("Appointment Get/Set Year: PASS");
		}
		else {
			System.out.println("Appointment Get/Set Year: FAIL!");
		}
		//Tests the Get/Set methods for the Pet member variable in Appointment Class
		String testName = "Bob";
		String testSpecies = "Cat";
		String testGender = "Female";
		
		myAppointment.SetPet(testName, testSpecies, testGender);
		
		//Tests Get/Set Pet Name
		if (testName.equals(myAppointment.GetPetName())){
			System.out.println("Appointment Pet Name Get/Set Year: PASS");
				}
		else {
			System.out.println("Appointment Pet Name Get/Set Year: FAIL!");
		}
		
		//Tests Get/Set Pet Species
		if (testSpecies.equals(myAppointment.GetPetSpecies())){
			System.out.println("Appointment Pet Species Get/Set Year: PASS");
				}
		else {
			System.out.println("Appointment Pet Species Get/Set Year: FAIL!");
		}
		
		//Test Get/Set Pet Gender
		if (testGender.equals(myAppointment.GetPetGender())){
			System.out.println("Appointment Pet Gender Get/Set Year: PASS");
				}
		else {
			System.out.println("Appointment Pet Gender Get/Set Year: FAIL!");
		}
		//END automated testing of the Appointment class
		
		//Runs the Appointment Constructor in the Appointment Class
		Appointment testAppointment = new Appointment (3, 16, 2017, "John", "Dog", "Male");
		
		//Runs the Read Method in the Appointment class, and fills in the Appointment Member Variables
		//with data from hard coded input file name 
		String newinFilename = "Appointment.txt";
		Scanner anotherInputFile = null;
		try {
			anotherInputFile = new Scanner(new FileReader(newinFilename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		myAppointment.Read(anotherInputFile);

		
		//Runs the Write method in the Appointment Class
		// with hard coded output file name
		 
		String newOutFilename = "AppointmentOut.txt";
		PrintStream newOut = null;
		
		try
		{
			newOut = new PrintStream(newOutFilename);
		}
		catch (Exception e)
		{
			System.out.println("ERROR. Could not open file!");
		}		
		myAppointment.Write(newOut);
		
		
		//Tests the previously ran Write method in the Appointment Class for errors by using Read method
 
		Scanner inputFile = null;
		try {
			inputFile = new Scanner(new FileReader(newOutFilename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		myAppointment.Read(inputFile);

		//Runs the GetJSON Method in the Appointment Class
		String newJSONAppointment;
		newJSONAppointment = myAppointment.GetJSON();
		System.out.println("\nTesting JSON for Appointment Class");
		System.out.println(newJSONAppointment);
		
		//Runs the toString Method in the Appointment Class
		String toStringTest = myAppointment.toString();
		System.out.println("\nTesting toString for Appointment Class");
		System.out.println(toStringTest);
		*/
		
		
		/*Testing for the VisitProcedure Class
		
		//Runs the constructor for VisitProcedure
		VisitProcedure testVisit = new VisitProcedure ("Checkup", 100.00, 2.0, true, .25);
		
		//Runs the CalculateProcedureAmount
		double procedureAmount = testVisit.CalculateProcedureAmount();
		System.out.printf("The calculated procedure amount is %.2f\n", procedureAmount);
		
		//Runs the CalculateProcedureAmountCovered
		double procedureAmountCovered = testVisit.CalculateProcedureAmountCovered();
		System.out.printf("The calculated procedure amount covered is %.2f\n", procedureAmountCovered);

		//Runs CalculateProcedureAmountDue
		double procedureAmountDue = testVisit.CalculateProcedureAmountDue();
		System.out.printf("The calculated procedure amount covered is %.2f\n", procedureAmountDue);
		*/
		
		
		/* Previous Assignment 2 Guts Automated Testing for the Pet Class
		Pet myPet = new Pet();
		Procedure newProcedure = new Procedure();
		
		//Start Automated Testing
		//  Tests the Get/Set methods for the m_Name member variable in Pet Class
		//String testPetName = "Pickles";
		//myPet.SetName(testPetName);
	
		//if (testPetName.equals(myPet.GetName())){
		//	System.out.println("Pet Get/Set Name: PASS");
		//}
		//else {
		//	System.out.println("Pet Get/Set Name: FAIL!");
		//}
		
		
		//Tests the Get/Set methods for the m_Species member variable in the Pet Class
		//String testSpecies = "Cat";
		//myPet.SetSpecies(testSpecies);
		
		//if(testSpecies.equals(myPet.GetSpecies())) {
		//	System.out.println("Pet Get/Set Species: PASS");
		//}
		//else {
		//	System.out.println("Pet Get/Set Species: FAIL!");
		//}
		
		 // Tests the Get/Set methods for the m_Gender member variable in the Pet Class
		//String testGender = "Female";
		//myPet.SetGender(testGender);
		
		//if(testGender.equals(myPet.GetGender())) {
		//	System.out.println("Pet Get/Set Gender: PASS");
		//}
		//else {
		//	System.out.println("Pet Get/Set Gender: FAIL!");
		//}
		
		//Tests the Get/Set methods for the m_Name member variable in the Procedure Class
//		String testProcedureName = "Rabies Vaccine";
//		newProcedure.SetName(testProcedureName);
		
//		if(testProcedureName.equals(newProcedure.GetName())) {
//			System.out.println("Procedure Get/Set Name: PASS");
//		}
//		else {
		//	System.out.println("Procedure Get/Set Name: FAIL!");
	//	}	
		
	//	
	//	//Tests the Get/Set methods for the m_Price member variable in the Procedure Class
	//	double testProcedurePrice = 50.99;
	//	newProcedure.SetPrice(testProcedurePrice);
		
	//	if(testProcedurePrice == newProcedure.GetPrice()) {
	//		System.out.println("Procedure Get/Set Price: PASS");
	//	}
	//	else {
		//	System.out.println("Procedure Get/Set Price: FAIL!");
		}	
		//End Automated Testing
		
	//	
	//	  Runs the Pet Constructor in the Pet Class
	//	 
	//	Pet testPet = new Pet("John", "Dog", "Male");
		
	//	
	//	  Runs the Procedure Constructor for the Procedure Class
	//	 
	//	Procedure testProcedure = new Procedure("X-Ray", 95.50);
		
	//	
	//	  Runs the Read Method in the Pet Class, and fills in the Pet Member Variables
	//	  with data from hard coded input file name
	//	 
	//	String inFilename = "Pet.txt";
	//	Scanner inputFile = new Scanner(new FileReader(inFilename));
		
	//	myPet.Read(inputFile);

//		
//		  Runs the Write method in the Pet Class
//		  with hard coded output file name
//		 
//		String outFilename = "PetOut.txt";
//		PrintStream out = null;
//		
//		try
//		{
//			out = new PrintStream(outFilename);
//		}
//		catch (Exception e)
//		{
//			System.out.println("ERROR. Could not open file!");
//		}
//		
//		myPet.Write(out);
//		
//		
//		  Tests the previously ran Write method in the Pet Class for errors by using Read method
//		 
//		inputFile = new Scanner(new FileReader(outFilename));
//		myPet.Read(inputFile);
//		
//		
//		 Runs the Read Method in the Procedure class, and fills in the Procedure Member Variables
//		 with data from hard coded input file name
//		 
//		String newinFilename = "Procedure.txt";
//		Scanner anotherInputFile = new Scanner(new FileReader(newinFilename));
//		
//		newProcedure.Read(anotherInputFile);
//		
//		
//		 Runs the Write method in the Procedure Class
//		 with hard coded output file name
//		 
//		String newOutFilename = "ProcedureOut.txt";
//		PrintStream newOut = null;
//		
//		try
//		{
//			newOut = new PrintStream(newOutFilename);
//		}
//		catch (Exception e)
//		{
//			System.out.println("ERROR. Could not open file!");
//		}
//		
//		newProcedure.Write(newOut);
//		
//		
//		  Tests the previously ran Write method in the Procedure Class for errors by using Read method
//		 
//		inputFile = new Scanner(new FileReader(newOutFilename));
//		newProcedure.Read(inputFile);
//		
//		
//		 Runs the GetJSON Method in the Pet Class
//		 
//		String newJSONPet;
//		newJSONPet = myPet.GetJSON();
//		System.out.println(newJSONPet);
//		
//		
//		 Runs the GetJSON Method in the Procedure Class
//		 
//		String newJSONProcedure;
//		newJSONProcedure = myPet.GetJSON();
//		System.out.println(newJSONProcedure);
//
//		
//		 Runs the toString Method in the Pet Class
//		 
//		String toStringTest = myPet.toString();
//		System.out.println(toStringTest);

//		 Runs the toString Method in the Pet Class	
		toStringTest = newProcedure.toString();
		System.out.println(toStringTest);	*/
		

	
}
}
