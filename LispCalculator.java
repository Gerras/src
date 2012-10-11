import java.io.*;

/**
 * Main method; runs the program.
 * 
 * @author kevin.brauen
 *
 */
public class LispCalculator {

	public static void main(String[] args) throws IOException {
		String myFile = args[0];
		LispEvaluator lispEvaluator = new LispEvaluator();
		BufferedReader stringInput = new BufferedReader(new FileReader(myFile));
		String myString;
		while((myString = stringInput.readLine()) != null) {
			try{
				//capacity = myString.length();
				double evalExpression = lispEvaluator.evaluate(myString);
				System.out.println(evalExpression);	
			} catch(InvalidExpressionException e) {
				System.out.println("Invalid Expression");
			}
			
		}
		stringInput.close();
		
		
	}
	
}
