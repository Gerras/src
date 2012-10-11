
import java.io.*;

/**
 * This program evaluates Lisp expressions using an algorithm
 * that I devised. It takes in a file with one Lisp expression
 * per line and prints every value obtained to standard output.
 * The outputs will be of a double value.
 * This class contains the executable portion of my program.
 *  
 * 
 * @author Kevin Brauen
 *
 */
public class LispEvaluator {
	
	private static LinkedList<Double> doubleStack;
	private static int capacity;
	private static String value;
	
	/**
	 * Main function; run program from here
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {
		String myFile = args[0];
		BufferedReader stringInput = new BufferedReader(new FileReader(myFile));
		String myString;
		while((myString = stringInput.readLine()) != null) {
			try{
				capacity = myString.length();
				double evalExpression = LispEvaluator.evaluate(myString);
				System.out.println(evalExpression);	
			} catch(InvalidExpressionException e) {
				System.out.println("Invalid Expression");
			}
			
		}
		stringInput.close();
		
		
	}
	/**
	 * This function evaluates the given Lisp Expression and returns a
	 * double value for it.
	 * 
	 * @param 	expression	The string lisp expression
	 * @return	double		The double value of the evaluated string
	 */
	public static double evaluate(String expression) throws InvalidExpressionException {
		doubleStack = new LinkedList<Double>(capacity);
		LinkedList<String> stack = new LinkedList<String>(capacity);
		String[] sarray = expression.split("");
		for(int i = capacity; i > 0; i--) {
			stack.push(sarray[i]);
		}
		
		LispEvaluator.handleOperands(stack);
		if(doubleStack.isEmpty()) {
			throw new InvalidExpressionException();
		} else {
			double lispValue = doubleStack.pop();
			return lispValue;						
		}

	}
	/**
	 *  This function deals with operands like "(" or "/" for the evaluate function.
	 *  
	 * @param sarray The string array that is parsed
	 * @param list The stack 
	 */
	public static void handleOperands(LinkedList<String> stack) {
		value = stack.pop();
		LispMath mathTime = new LispMath();
		if(value.equals("(")) {
			while(!(value.equals(")"))) {
				if(value.equals("+")){
					value = mathTime.add(stack, value, doubleStack);
				}
				else if(value.equals(" ") || value.equals("(")){
					if(stack.isEmpty()){
						throw new InvalidExpressionException();
					} else {
						value = stack.pop();	
					}
				}
				else if (value.equals("-")) {
					value = mathTime.sub(stack, value, doubleStack);
				}
				else if (value.equals("/")) {
					value = mathTime.divide(stack, value, doubleStack);
				}
				else if (value.equals("*")) {
					value = mathTime.multiply(stack, value, doubleStack);
				}
				else {
					throw new InvalidExpressionException();
				}
			}		
		}
		else {
			throw new InvalidExpressionException();
		}
	}
}

