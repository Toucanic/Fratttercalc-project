package fracCalc;

import java.util.*;

public class FracCalcList2 {

public static final char[] realNums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static Boolean contained(char[] let, char check) {
		for (char Num : let) {
			if (check == Num) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Scanner eqRead = new Scanner(System.in);
		System.out.print("type equation: ");
		String reck = produceAnswer(equation);
		System.out.println(reck);
	}
	
	public static String produceAnswer(Scanner eqRead) {
		String[] elementList = new String[99];
		for ()
		String equation = eqRead.next();
		return null;
	}

}
