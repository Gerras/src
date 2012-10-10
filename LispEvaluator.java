
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
		if(value.equals("(")) {
			while(!(value.equals(")"))) {
				if(value.equals("+")){
					LispEvaluator.add(stack);
				}
				else if(value.equals(" ") || value.equals("(")){
					if(stack.isEmpty()){
						throw new InvalidExpressionException();
					} else {
						value = stack.pop();	
					}
					
				}
				
				else if (value.equals("-")) {
					LispEvaluator.sub(stack);
				}
				else if (value.equals("/")) {
					LispEvaluator.div(stack);
				}
				else if (value.equals("*")) {
					LispEvaluator.mul(stack);
				}
				else {
					throw new InvalidExpressionException();
				}
			}		
		}
		else
			throw new InvalidExpressionException();
	}
	
	/**
	 * This function adds Lisp values together. I use a static running 
	 * accumulator for all of my functions, because I wasn't sure how to
	 * do it any other way when pushing the whole string onto the stack
	 * at the very beginning of evaluate.
	 * 
	 * @param stack The link-list string stack that holds the Lisp values
	 */
	public static void add(LinkedList<String> stack) {
		double accumulator = 0;
		while(!(value.equals(")"))) {
			if(value.equals("+")) {
				doubleStack.push(accumulator);
				if(stack.isEmpty()){
					throw new InvalidExpressionException();
				} else {
					value = stack.pop();	
				}
				
			}
			else if(value.equals(" ")) {
				if(stack.isEmpty()) {
					throw new InvalidExpressionException();
				} else {
					value = stack.pop();	
				}
				
			}
			else if(value.equals("(")) {
				LispEvaluator.handleOperands(stack);
			}
			else if (value.equals("-")) {
				throw new InvalidExpressionException();
			}
			else if (value.equals("a")) {
				throw new InvalidExpressionException();
			}
			else if(Integer.parseInt(value) <= 9 && Integer.parseInt(value) >= 0 ) {
				if(!(doubleStack.isEmpty())) {
					accumulator = doubleStack.pop();
					accumulator += Double.parseDouble(value);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						value = stack.pop();
					}	
				} else {
					accumulator += Double.parseDouble(value);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						value = stack.pop();
					}
				}
			} else {
				throw new InvalidExpressionException();
			}
			
		}
		
	}
	
	public static void sub(LinkedList<String> stack) {
		double accumulator = 0;
		while (!(value.equals(")"))) {
			if(value.equals("-")) {
				doubleStack.push(accumulator);
				value = stack.pop();
			}
			else if(value.equals(" ")) {
				value = stack.pop();
			}
			else if (value.equals("(")) {
				LispEvaluator.handleOperands(stack);
			}
			else if (Integer.parseInt(value) <= 9 && Integer.parseInt(value) >= 0) {
				if(!(doubleStack.isEmpty())) {
					accumulator = doubleStack.pop();
					accumulator = accumulator - Double.parseDouble(value);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						value = stack.pop();	
					}
				} else {
					accumulator = Double.parseDouble(value);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						value = stack.pop();
					}
				}
			} else {
				throw new InvalidExpressionException();
			}
		}
	}
	
	public static void mul(LinkedList<String> stack) {
		double accumulator = 1;
		while(!(value.equals(")"))) {
			if(value.equals("*")) {
				doubleStack.push(accumulator);
				value = stack.pop();
			}
			else if(value.equals(" ")) {
				value = stack.pop();
			}
			else if(value.equals("(")) {
				LispEvaluator.handleOperands(stack);
			}
			else if (Integer.parseInt(value) <= 9 && Integer.parseInt(value) >= 0) {
				if(!(doubleStack.isEmpty())) {
					accumulator = doubleStack.pop();
					accumulator *= Double.parseDouble(value);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						value = stack.pop();
					}
				} else {
					accumulator *= Double.parseDouble(value);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						value = stack.pop();
					}
				}
			} else {
				throw new InvalidExpressionException();
			}
		}
	}
	
	public static void div(LinkedList<String> stack) {
		double accumulator = 1;
		while(!(value.equals(")"))) {
			if(value.equals("/") || value.equals(" ")) {
				value = stack.pop();
			}
			else if (value.equals("(")) {
				LispEvaluator.handleOperands(stack);
			}
			else if (Integer.parseInt(value) <= 9 && Integer.parseInt(value) >= 0) {
				if(!(doubleStack.isEmpty())) {
					accumulator = doubleStack.pop();
					accumulator /= Double.parseDouble(value);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						value = stack.pop();
					}
						
						
				} else {
					accumulator /= Double.parseDouble(value);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						value = stack.pop();
					}
				} 
			} else {
				throw new InvalidExpressionException();
			}
		}
	}
}

