package toBeFractcalc;

import java.util.Scanner;

public class OnlyStringProcessing {
	public static final char[] realNums = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
	
	public static Boolean cotained(char[] let, char check) {
		for (char Num : realNums) {
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
		System.out.println(reck);
	}
	public static String produceAnswer(String orginalEquation) {
		// abolishing whole numbers
		String wholeNum = "";
		String numeratorOrg = "";
		String denominatorOrg = "";
		String fracOnlyEq = "";
		for (int i = 0; i < orginalEquation.length(); i++) {
			if (orginalEquation.charAt(i) == '_') {
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
		/*for (int i = 0; i < orginalEquation.length(); i++) {
			if (cotained(realNums, orginalEquation.charAt(i))) {
				
			}
		}*/
		return fracOnlyEq;
	}
}
