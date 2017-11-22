package fracCalc;

import java.util.*;

public class willThisWorkForOnceCalc {

	public static final String opps= "/*-+";
	public static int arrayoppCount = 0;

	public static void main(String[] args) {
		Scanner lineRead = new Scanner(System.in);
		String OGeq = lineRead.nextLine();
		int [][] valArray = array_afi(OGeq);
		char [][] opperators = oppArraying(OGeq);
		System.out.println(Arrays.deepToString(valArray));
		System.out.println(Arrays.deepToString(ValsToNumerators(valArray)));
		System.out.println(Arrays.deepToString(opperators));
		String userOutput = calculatingPortion(ValsToNumerators(valArray), opperators);
		System.out.println(userOutput);
	}
	public static int[][] array_afi(String eq) {
		// this determines the length of the array 
		Scanner eqRead = new Scanner(eq);
		int arrayValCount = 0;
		while (eqRead.hasNext()) {
			String currentTok = eqRead.next();
			if (opps.contains(currentTok)) {
				arrayoppCount++;
			}
			else {
				arrayValCount++;
			}
		}
		// this establishes the array
		int[][] parsedEQ = new int[arrayValCount][3];
		// fills the array
		Scanner lastEqRead = new Scanner(eq);
		int TokensCounted = 0;
		String leftside = "";
		boolean wholeNumcheck = true;
		while (lastEqRead.hasNext()) {
			String TokenParsing = lastEqRead.next();
			if (!opps.contains(TokenParsing)) {
				for (int i = 0; i < TokenParsing.length(); i++) {
					if (TokenParsing.charAt(i) == '_') {
						parsedEQ[TokensCounted][0] = Integer.parseInt(leftside);
						leftside = "";
					}
					else if (TokenParsing.charAt(i) == '/') {
						parsedEQ[TokensCounted][1] = Integer.parseInt(leftside);
						leftside = "";
						wholeNumcheck = false;
					}
					else if (i == TokenParsing.length() - 1) {
						if (wholeNumcheck) {
							leftside += TokenParsing.charAt(i);
							parsedEQ[TokensCounted][0] = Integer.parseInt(leftside);
						}
						else {
							leftside += TokenParsing.charAt(i);
							parsedEQ[TokensCounted][2] = Integer.parseInt(leftside);
							wholeNumcheck = true;
						}
					}
					else {
						leftside += TokenParsing.charAt(i);
					}
				}
				leftside = "";
				TokensCounted++;
			}
		}
		return parsedEQ;
	}
// does pretty much the same thing as array_afi
	public static char[][] oppArraying(String ogEQ) {
		Scanner oppReads = new Scanner(ogEQ);
		String current;
		char [][] AllOpps = new char[arrayoppCount][];
		int placeInAllOpp = 0;
		for (int i = 0; i < arrayoppCount; i++) {
			AllOpps[i] = new char[arrayoppCount - i];
		}
		while (oppReads.hasNext()) {
			current = oppReads.next();
			if (opps.contains(current)) {
				AllOpps[0][placeInAllOpp] = current.charAt(0);
				placeInAllOpp++;
			}
		}
		return AllOpps;
	}
	
/*	public static int deepArraylength(int[][] anArray) {
		// finds array length
				String StrValArray = Arrays.deepToString(anArray);
				Scanner counterOfOpperands = new Scanner(StrValArray);
				int countOfOpperands = 0;
				while(counterOfOpperands.hasNext()) {
					countOfOpperands++;
					counterOfOpperands.next();
				}
				countOfOpperands /= 3;
				return countOfOpperands;
	}
	public static int deepArraylength(char[][] anArray) {
		// finds array length
				String StrValArray = Arrays.deepToString(anArray);
				Scanner counterOfOpperands = new Scanner(StrValArray);
				int countOfOpperands = 0;
				while(counterOfOpperands.hasNext()) {
					countOfOpperands++;
					counterOfOpperands.next();
				}
				countOfOpperands /= 3;
				return countOfOpperands;
	}
*/	
	public static int theCommonDenom = 1;

	public static int[][] ValsToNumerators(int[][] valArray) {
		// finds array length
		int countOfOpperands = valArray.length;
		// this will quickly calculate the common denominator
		for (int i = 0; i < countOfOpperands; i++) {
			if (valArray[i][2] == 0) {
				continue;
			}
			theCommonDenom *= valArray[i][2];
		}

		// produces the new array of numerators
		int[][] NumeratoredArray = new int[countOfOpperands][];
		for (int i = 0; i < countOfOpperands; i++) {
			NumeratoredArray[i] = new int[countOfOpperands - i];
		}
		// fills array
		for (int i = 0; i < countOfOpperands; i++) {
			if (valArray[i][2] == 0) {
				NumeratoredArray[0][i] = (valArray[i][0] * theCommonDenom);
				continue;
			}
			NumeratoredArray[0][i] = (valArray[i][0] * theCommonDenom) + (valArray[i][1] * theCommonDenom / valArray[i][2]);
		}
		return NumeratoredArray;
	}
	
	public static String calculatingPortion(int[][] numerators, char[][] opperators) {
		int countyThing = 1;
		int movingDownVal;
		for(int timesThrough = 0; timesThrough < opperators.length; timesThrough++) {
			for(int oppCurrent = 0; oppCurrent < opperators.length; oppCurrent++) {
				if (opperators[timesThrough][oppCurrent] == '/' || opperators[timesThrough][oppCurrent] == '*') {
					if(opperators[timesThrough][oppCurrent] == '/') {
						divide(numerators, oppCurrent, timesThrough);
						for (int i = 0; i < opperators.length - timesThrough; i++) {
							if (i == oppCurrent) {
								numerators[countyThing][i] = numerators[timesThrough][i];
							}
							numerators[countyThing][i] = numerators[timesThrough][i] * numerators[timesThrough][oppCurrent + 1];
						}
					}
					else {
						movingDownVal = multiply(numerators, oppCurrent, timesThrough);
						for (int i = 0; i < opperators.length - timesThrough; i++) {
							if (i == oppCurrent) {
								numerators[countyThing][i] = movingDownVal;
								continue;
							}
							numerators[countyThing][i] = numerators[timesThrough][i];
						}
					}
				}
				else {
					if(opperators[timesThrough][oppCurrent] == '-') {
						movingDownVal = subtract(numerators, oppCurrent, timesThrough);			
					}
					else {
						movingDownVal = add(numerators, oppCurrent, timesThrough);
					}
					for (int i = 0; i < opperators.length - timesThrough; i++) {
						if (i == oppCurrent) {
							numerators[countyThing][i] = movingDownVal;
							continue;
						}
						numerators[countyThing][i] = numerators[timesThrough][i];
					}
				}
			}
			countyThing++;
		}
		return "" + numerators[opperators.length][0] + "/" + theCommonDenom;
	}
	
	public static void divide(int[][] vals, int oppLocation, int rowNum) {
		theCommonDenom *= vals[rowNum][oppLocation + 1];
	}
	public static int multiply(int[][] vals, int oppLocation, int rowNum) {
		int product = vals[rowNum][oppLocation] * vals[rowNum][oppLocation + 1];
		return product;
	}
	public static int subtract(int[][] vals, int oppLocation, int rowNum) {
		int minussed = vals[rowNum][oppLocation] - vals[rowNum][oppLocation + 1];
		return minussed;
	}
	public static int add(int[][] vals, int oppLocation, int rowNum) {
		int added = vals[rowNum][oppLocation] + vals[rowNum][oppLocation + 1];
		return added;
	}

}
