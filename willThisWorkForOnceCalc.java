package toBeFractcalc;

import java.util.*;

public class willThisWorkForOnceCalc {

	public static final String opps= "/*-+";
	public static int arrayoppCount = 0;

	public static void main(String[] args) {
		while (true) {
			Scanner lineRead = new Scanner(System.in);
			String OGeq = lineRead.nextLine();
			if (OGeq.equals("quit"))
				break;
			produceAnswer(OGeq);
			System.out.println(produceAnswer(OGeq));
		}
		/*int [][] valArray = array_afi(OGeq);
		char [][] opperators = oppArraying(OGeq);
		System.out.println(Arrays.deepToString(valArray));
		System.out.println(Arrays.deepToString(ValsToMixFrac(valArray)));
		System.out.println(Arrays.deepToString(opperators));
		String[][] infixNotation = prePolishNotation(ValsToMixFrac(valArray), opperators);
		System.out.println(Arrays.deepToString(infixNotation));
		System.out.println(betterPrePN(opperators));
		System.out.println(infixToPostfix(betterPrePN(opperators)));
		String endFracUnSimiple = CalculatingTooTheEnd(infixToPostfix(betterPrePN(opperators)), ValsToMixFrac(valArray));
		System.out.println(endFracUnSimiple);
		System.out.println(simplify(endFracUnSimiple));
		//System.out.println(Arrays.deepToString(postPolishNotation(infixNotation)));
		*/
	}
	
	public static String produceAnswer(String OGeq) {
		int [][] valArray = array_afi(OGeq);
		char [][] opperators = oppArraying(OGeq);
		Arrays.deepToString(valArray);
		Arrays.deepToString(opperators);
		betterPrePN(opperators);
		infixToPostfix(betterPrePN(opperators));
		String endFracUnSimiple = CalculatingTooTheEnd(infixToPostfix(betterPrePN(opperators)), ValsToMixFrac(valArray));
		System.out.println(simplify(endFracUnSimiple));
		return simplify(endFracUnSimiple); 
	}
	
	public static String simplify(String mixfraction) {
		int center = mixfraction.indexOf('/');
		int a = Integer.parseInt(mixfraction.substring(0, center));
		int b = Integer.parseInt(mixfraction.substring(center + 1, mixfraction.length()));
		boolean absVal = false;
		boolean Valbissue = false;
		int t;
		if ((a<0 && b<0) || (a>0 && b>0))
			absVal = true;
		// absolute val statement
		else if (a<0 && b>0) {
			a=-a;
		}
		else if (b<0 && a>0) {
			b=-b;
			Valbissue = true;
		}
		// gcd() function
		int lowestdDenom = 1;
		if (a < b) {
			while (a != 0) {
				t = a;
				a = b % a;
				b = t;
			}
			lowestdDenom = b;
		}
		else if (b < a) {
			while (b != 0) {
				t = b;
				b = a % b;
				a = t;
			}
			lowestdDenom = a;
		}
		else
			return "1";
		
		int wholeNum = (Integer.parseInt(mixfraction.substring(0, center))/lowestdDenom) / (Integer.parseInt(mixfraction.substring(center + 1, mixfraction.length()))/lowestdDenom);
		int numer = (Integer.parseInt(mixfraction.substring(0, center))/lowestdDenom) % (Integer.parseInt(mixfraction.substring(center + 1, mixfraction.length()))/lowestdDenom);
		if (numer == 0)
			return wholeNum +"";
		else if (absVal && wholeNum != 0)
			return Math.abs(wholeNum) + "_" + Math.abs(numer) + "/" + Math.abs(Integer.parseInt(mixfraction.substring(center + 1, mixfraction.length()))/lowestdDenom);
		else if (wholeNum != 0 && (wholeNum < 0 && numer < 0))
			return wholeNum + "_" + (-numer) + "/" + (Integer.parseInt(mixfraction.substring(center + 1, mixfraction.length()))/lowestdDenom);
		else if (wholeNum != 0 && (wholeNum < 0) && Valbissue)
			return wholeNum + "_" + (numer) + "/" + Math.abs(Integer.parseInt(mixfraction.substring(center + 1, mixfraction.length()))/lowestdDenom);
		else
			return numer + "/" + (Integer.parseInt(mixfraction.substring(center + 1, mixfraction.length()))/lowestdDenom);
	}
	
	static int Prec(char ch) {
        switch (ch)
        {
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
            return 2;
        }
        return -1;
    }
	
	static int ReferenceLetter(char letter) {
		switch(letter) {
			case 'a': return 0;
			case 'b': return 1;
			case 'c': return 2;
			case 'd': return 3;
			case 'e': return 4;
			case 'f': return 5;
			case 'g': return 6;
			case 'h': return 7;
			case 'i': return 8;
			case 'j': return 9;
			case 'k': return 10;
			case 'l': return 11;
			case 'm': return 12;
			case 'n': return 13;
			case 'o': return 14;
			case 'p': return 15;
			case 'q': return 16;
			case 'r': return 17;
			case 's': return 18;
			case 't': return 19;
			case 'u': return 20;
			case 'v': return 21;
			case 'w': return 22;
			case 'x': return 23;
			case 'y': return 24;
			case 'z': return 25;
		}
		return -1;
	}

	public static String CalculatingTooTheEnd(String RPNeq, int[][] referenceFracs) {
		if ("a".contains(RPNeq))
			return referenceFracs[ReferenceLetter('a')][0] + "/" + referenceFracs[ReferenceLetter('a')][1];
		Stack<int[]> LastFractionHolder = new Stack<int[]>();
		int[] val1;
		int[] val2;
		for (int i = 0; i < RPNeq.length(); i++) {
			//checks to find opperands
			if(opps.contains(RPNeq.subSequence(i, i+1))) {
				// picks out the two values being evaled
				if (RPNeq.charAt(i - 2) == 'z' && RPNeq.charAt(i - 1) == 'z') {
					val2 = LastFractionHolder.pop();
					val1 = LastFractionHolder.pop();
				}
				else if (RPNeq.charAt(i - 2) == 'z') {
					val1 = LastFractionHolder.pop();
					val2 = referenceFracs[ReferenceLetter(RPNeq.charAt(i - 1))];
				}
				else if (RPNeq.charAt(i - 1) == 'z') {
					val1 = referenceFracs[ReferenceLetter(RPNeq.charAt(i - 2))];
					val2 = LastFractionHolder.pop();
				}
				else {
					val1 = referenceFracs[ReferenceLetter(RPNeq.charAt(i - 2))];
					val2 = referenceFracs[ReferenceLetter(RPNeq.charAt(i - 1))];
				}
				
				//does the actual math
				if (RPNeq.charAt(i) == '+')
					LastFractionHolder.push(add(val1, val2));
				else if (RPNeq.charAt(i) == '-')
					LastFractionHolder.push(subtract(val1, val2));
				else if (RPNeq.charAt(i) == '*')
					LastFractionHolder.push(multiply(val1, val2));
				else
					LastFractionHolder.push(divide(val1, val2));
				
				//will change the string to reflect the what has happened to it
				RPNeq = RPNeq.substring(0, i-2) + "z" + RPNeq.substring(i+1, RPNeq.length());
				i = RPNeq.indexOf('z');
			}
		}
		int[] placeHolder = LastFractionHolder.pop();
		return placeHolder[0] + "/" + placeHolder[1];
	}
	
	public static String betterPrePN(char [][] opperators) {
		// all this makes a equation that represents the actual equation
		char[] letterPull = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		String futureInput = "a";
		int count = 0;
		if (opperators.length == 0)
			return futureInput;
		while(count < opperators[0].length) {
			futureInput += opperators[0][count];
			count++;
			futureInput += letterPull[count];
		}
		return futureInput;
	}
	
	// The method that converts infix expression
    // to postfix expression. 
    public static String infixToPostfix(String formatEQ) {
        // initializing empty String for result
        String result = new String("");
         
        // initializing empty stack
        Stack<Character> stack = new Stack<>();
         
        for (int i = 0; i<formatEQ.length(); ++i)
        {
            char c = formatEQ.charAt(i);
             
             // If the scanned character is an operand, add it to output.
            if (Character.isLetterOrDigit(c))
                result += c;
            else {
                while (!stack.isEmpty() && Prec(c) <= Prec(stack.peek()))
                    result += stack.pop();
                stack.push(c);
            }
      
        }
      
        // pop all the operators from the stack
        while (!stack.isEmpty())
            result += stack.pop();
      
        return result;
    }
	
	public static String [][] postPolishNotation(String[][] infixNotation) {
		
		// initializing empty Array for result
        String[][] result = new String[infixNotation.length][];
        
        // initializing empty stack
        Stack<Character> stack = new Stack<>();
        
        String currentThing = "";
        String[] currentOpperater = {""};
        for (int i = 0; i < infixNotation.length; ++i) {
        	currentThing = infixNotation[i][0];
        	if (!opps.contains(currentThing)) {
        		result[i] = infixNotation[i];
        	}
        	else {
        		while (!stack.empty() && Prec(currentThing.charAt(0)) <= Prec(stack.peek())) {
        			currentOpperater[0] = "" + stack.pop();
        			result[i] = currentOpperater;
        			i++;
        		}
                stack.push(currentThing.charAt(0));
                i--;
        	}
        }
        return result;
	}
	
	public static String[][] prePolishNotation(int [][] mixfracArray, char [][] opperators) {
		int length = mixfracArray.length + opperators.length;
		String[][] backToAnEQarray = new String[length][];
		int momentaryCounter = 0;
		for (int[] frac : mixfracArray) {
			String[] momentaryArray = {""+frac[0], ""+frac[1]};
			backToAnEQarray[momentaryCounter] = momentaryArray;
			momentaryCounter += 2;
		}
		int momentaryCounter2 = 1;
		for (char opp : opperators[0]) {
			String[] momentaryArray = {""+opp};
			backToAnEQarray[momentaryCounter2] = momentaryArray;
			momentaryCounter2 += 2;
		}
		return backToAnEQarray;
	}
	
	public static int[][] array_afi(String eq) {
		arrayoppCount = 0;
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
	
	public static int[][] ValsToMixFrac(int[][] valArray) {
		// finds array length
		int countOfOpperands = valArray.length;
		// this will quickly change whole numbers to have a deominator
		for (int i = 0; i < countOfOpperands; i++) {
			if (valArray[i][2] == 0) {
				valArray[i][2] = 1;
			}
		}

		// produces the new array of numerators
		int[][] mixFracedArray = new int[countOfOpperands][];
		for (int i = 0; i < countOfOpperands; i++) {
			mixFracedArray[i] = new int[2];
		}
		// fills array
		for (int i = 0; i < countOfOpperands; i++) {
			if (valArray[i][0] < 0)
				valArray[i][1] *= -1;
			mixFracedArray[i][0] = valArray[i][1] + (valArray[i][0] * valArray[i][2]);
			mixFracedArray[i][1] = valArray[i][2];
		}
		return mixFracedArray;
	}
	
	
	public static int[] divide(int[] val1, int[] val2) {
		int[] quotient = {val1[0] * val2[1], val1[1] * val2[0]};
		return quotient;
	}
	public static int[] multiply(int[] val1, int[] val2) {
		int[] product = {val1[0] * val2[0], val1[1] * val2[1]};
		return product;
	}
	public static int[] subtract(int[] val1, int[] val2) {
		int equalDenoms = val1[1] * val2[1];
		int[] minussed = {(val1[0] * val2[1]) - (val2[0] * val1[1]), equalDenoms};
		return minussed;
	}
	public static int[] add(int[] val1, int[] val2) {
		int equalDenoms = val1[1] * val2[1];
		int[] added = {(val1[0] * val2[1]) + (val2[0] * val1[1]), equalDenoms};
		return added;
	}

}
