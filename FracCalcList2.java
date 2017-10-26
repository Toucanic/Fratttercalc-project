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
		String equation = eqRead.nextLine();
		String reck = produceAnswer(equation);
		String maybe = evalList(reck);
		System.out.println(maybe);
	}
	public static String produceAnswer(String orginalEquation) {
		// abolishing whole numbers
		String wholeNum = "";
		String numeratorOrg = "";
		String denominatorOrg = "";
		String fracOnlyEq = "";
		boolean mixfrac = false; 
		for (int i = 0; i < orginalEquation.length(); i++) {
			if (orginalEquation.charAt(i) == '_') {
				mixfrac = true;
				i++;
				while (orginalEquation.charAt(i) != '/') {
					numeratorOrg += orginalEquation.charAt(i);
					i++;
				}
				while (i < orginalEquation.length())	{
					i++;
					if (orginalEquation.charAt(i) == ' ' || orginalEquation.length() - 1 == i) {
						if (orginalEquation.length() - 1 == i) {
							denominatorOrg += orginalEquation.charAt(i);
						}
						int workingNumer = Integer.parseInt(wholeNum) * Integer.parseInt(denominatorOrg) + Integer.parseInt(numeratorOrg);
						String fracAndOppOrg = "";
						if (orginalEquation.length() - 1 == i) {
							fracAndOppOrg = "n" + workingNumer + "f" + denominatorOrg + "d";
						}
						else {
							fracAndOppOrg = "n" + workingNumer + "f" + denominatorOrg + "d " + orginalEquation.charAt(++i) + " ";
						}
						i += 2;
						fracOnlyEq += fracAndOppOrg;
						wholeNum = "";
						numeratorOrg = "";
						denominatorOrg = "";
						break;
					}
					denominatorOrg += orginalEquation.charAt(i);
				}	
			}
			if (orginalEquation.length() < i) {
				break;
			}
			wholeNum += orginalEquation.charAt(i);
		}
		if (!mixfrac) {
			String leftside = "";
			String fracAndOppOrg;
			String workingNumer;
			for (int i = 0; i < orginalEquation.length(); i++) {
				if (orginalEquation.charAt(i) == ' ') {
					if (orginalEquation.length() - 1 == i) {
						workingNumer = leftside;
						fracAndOppOrg = "n" + workingNumer + "f" + 1 + "d " + orginalEquation.charAt(++i) + " ";
					}
					else {
						workingNumer = leftside;
						fracAndOppOrg = "n" + workingNumer + "f" + 1 + "d " + orginalEquation.charAt(++i) + " ";
					}
					i+=2;
					fracOnlyEq += fracAndOppOrg;
					leftside = "";
				}
				else if(orginalEquation.charAt(i) == '/') {
					workingNumer = leftside;
					i++;
					while (i < orginalEquation.length())	{
						if (orginalEquation.charAt(i) == ' ' || orginalEquation.length() - 1 == i) {
							if (orginalEquation.length() - 1 == i) {
								denominatorOrg += orginalEquation.charAt(i);
								fracAndOppOrg = "n" + workingNumer + "f" + denominatorOrg + "d ";
							}
							else {
								fracAndOppOrg = "n" + workingNumer + "f" + denominatorOrg + "d " + orginalEquation.charAt(++i) + " ";
							}
							i += 2;
							fracOnlyEq += fracAndOppOrg;
							leftside = "";
							denominatorOrg = "";
							break;
						}
						denominatorOrg += orginalEquation.charAt(i);
						i++;
					}
				}
				if (i >= orginalEquation.length()) {
					break;
				}
				leftside += orginalEquation.charAt(i);
			}
		}
		return fracOnlyEq;
	}
	
	
	// Changing now to a list based format
	public static String evalList(String standardEQ) {
		return standardEQ;
	}

}
