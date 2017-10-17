package toBeFractcalc;

import java.util.*;


public class firstCalccyThing {

	public static final String realNums = "0123456789";
	public static final String validOperator = "_+-*/";

	public static void main(String[] args) {
		System.out.println("This is a fraction calculator. mixed numbers require underscores to function");
		Scanner reader = new Scanner(System.in);
		String startval = reader.nextLine();
		System.out.println(produceAnswer(startval));
	}
	
	public static String produceAnswer(String calcing) {
		String finishedANS = "test fail";
		String[] listValsAndOps = convertTOList(calcing);
		int DenomEqualizer = 1;
		int count = 0;
		for (String calcingPeice: listValsAndOps) {
			if (calcingPeice.substring(0, 5).contains("denom")) {
				DenomEqualizer *= Integer.parseInt(calcingPeice.substring(5, calcingPeice.length()));
				count++;
			}
		}
		// should give all fractions a common denimonator
		for (int numingEqualizer = 0; numingEqualizer < listValsAndOps.length; numingEqualizer++) {
			if (listValsAndOps[numingEqualizer].substring(0, 5).contains("numor")) {
				int workingVal = Integer.parseInt(listValsAndOps[numingEqualizer].substring(5, listValsAndOps[numingEqualizer].length())) 
						* (DenomEqualizer / Integer.parseInt(listValsAndOps[numingEqualizer + 2].substring(5, listValsAndOps[numingEqualizer + 2].length())));
				listValsAndOps[numingEqualizer] = "" + workingVal;
			}
			else {
				int fullcheck = 1;
				for (int wholeCheck = 0; wholeCheck < listValsAndOps[numingEqualizer].length(); wholeCheck++) {
					if (realNums.contains(listValsAndOps[numingEqualizer].substring(wholeCheck, wholeCheck + 1))) {
						fullcheck++;
					}
				}
				if (fullcheck == listValsAndOps[numingEqualizer].length()) {
					int workingVal = Integer.parseInt(listValsAndOps[numingEqualizer]) * DenomEqualizer;
					listValsAndOps[numingEqualizer] = "" + workingVal;
				}
			}
		}
		// should give the final ans
		int numoratorAndOpperandListLength = 0;
		for (String calcingPeice: listValsAndOps) {
			if (calcingPeice.substring(0, 5).contains("denom")) {
				numoratorAndOpperandListLength++;
			}
			else if (calcingPeice.equals("/")) {
				numoratorAndOpperandListLength++;
			}
		}
		String[] LastProcessingList = new String[listValsAndOps.length - numoratorAndOpperandListLength];
		int numFinishedANS = 0;
		for (int orderedListnums = 0; orderedListnums < listValsAndOps.length; orderedListnums++) {
			if (!listValsAndOps[orderedListnums].substring(0, 5).contains("denom") && !listValsAndOps[orderedListnums].equals("/")) {
				LastProcessingList[orderedListnums] = listValsAndOps[orderedListnums];
			}
		}
		for (int opperating = 0; opperating < LastProcessingList.length; opperating++) {
			if (LastProcessingList[opperating].equals("*") || LastProcessingList[opperating].equals(" / ")) {
				if (LastProcessingList[opperating].equals("*")) {
					numFinishedANS = Integer.parseInt(LastProcessingList[opperating - 1]) * Integer.parseInt(LastProcessingList[opperating + 1]);
				}
				if (LastProcessingList[opperating].equals(" / ")) {
					//issue at present please take note will not work due to truncation
					numFinishedANS = Integer.parseInt(LastProcessingList[opperating - 1]) / Integer.parseInt(LastProcessingList[opperating + 1]);
				}
			}
			// this is where you left off
		}
		
		return finishedANS;
	}
	public static String[] convertTOList(String calcing) {
		String listAdderVal = "";
		int listLength = 1;
		for (int listdecide = 0; listdecide < calcing.length(); listdecide++) {
			if (validOperator.contains(calcing.substring(listdecide, listdecide + 1))) {
				listLength += 2;
			}
		}
		String[] allThingsList = new String[listLength];
		int listIndexNum = 0;
		for (int addingToList = 0; addingToList < calcing.length(); addingToList++) {
			if (realNums.contains(calcing.substring(addingToList, addingToList + 1))) {
				listAdderVal += calcing.charAt(addingToList);
			}
			else if (calcing.charAt(addingToList) == '_') {
				allThingsList[listIndexNum] = listAdderVal;
				listIndexNum++;
				listAdderVal = "";
			}
			else if (calcing.charAt(addingToList) == ' ') {
				if (calcing.charAt(++addingToList) == '/') {
					listAdderVal = " / ";
					allThingsList[listIndexNum] = listAdderVal;
					listIndexNum++;
					listAdderVal = "";
					addingToList += 2;
				}
				else {
					continue;
				}
			}
			else if (calcing.charAt(addingToList) == '/') {
				allThingsList[listIndexNum] = "numer" + listAdderVal;
				listIndexNum++;
				listAdderVal = "/";
				allThingsList[listIndexNum] = listAdderVal;
				listIndexNum++;
				listAdderVal = "denom";
			}
			else if (calcing.charAt(addingToList) == '*') {
				allThingsList[listIndexNum] = listAdderVal;
				listIndexNum++;
				listAdderVal = "*";
				allThingsList[listIndexNum] = listAdderVal;
				listIndexNum++;
				listAdderVal = "";
			}
			else if (calcing.charAt(addingToList) == '-') {
				allThingsList[listIndexNum] = listAdderVal;
				listIndexNum++;
				listAdderVal = "+";
				allThingsList[listIndexNum] = listAdderVal;
				listIndexNum++;
				listAdderVal = "";
			}
			else if (calcing.charAt(addingToList) == '-') {
				allThingsList[listIndexNum] = listAdderVal;
				listIndexNum++;
				listAdderVal = "-";
				allThingsList[listIndexNum] = listAdderVal;
				listIndexNum++;
				listAdderVal = "";
			}
		}
		allThingsList[listIndexNum] = listAdderVal;
		return allThingsList;
	}
	
	public static String deSpacer(String anything) {
		String spaceLessVal = "";
		for (int firstParse = 0; firstParse < anything.length(); firstParse++) {
			if (anything.charAt(firstParse) != ' ') {
				spaceLessVal += anything.charAt(firstParse);
			}
		}
		return spaceLessVal;
	}
}
