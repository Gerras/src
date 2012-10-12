/**
 * This program evaluates Lisp expressions using an algorithm
 * that I devised. It takes in a file with one Lisp expression
 * per line and prints every currStackValue obtained to standard output.
 * The outputs will be of a double currStackValue.
 * This class contains the executable portion of my program.
 *  
 * 
 * @author Kevin Brauen
 *
 */
public class LispEvaluator {
	
	private static LinkedList<Double> doubleStack;
	//private static int capacity;
	private static String currStackValue;
	
	/**
	 * Main function; run program from here
	 * 
	 * @param args
	 */
	/**
	public static void main(String[] args) throws IOException {
		String myFile = args[0];
		BufferedReader stringInput = new BufferedReader(new FileReader(myFile));
		String myString;
		while((myString = stringInput.readLine()) != null) {
			try{
				//capacity = myString.length();
				double evalExpression = LispEvaluator.evaluate(myString);
				System.out.println(evalExpression);	
			} catch(InvalidExpressionException e) {
				System.out.println("Invalid Expression");
			}
			
		}
		stringInput.close();
		
		
	} */
	/**
	 * This function evaluates the given Lisp Expression and returns a
	 * double currStackValue for it.
	 * 
	 * @param 	expression	The string lisp expression
	 * @return	double		The double currStackValue of the evaluated string
	 */
	public double evaluate(String expression) throws InvalidExpressionException {
		int stringLength = expression.length();
		doubleStack = new LinkedList<Double>(stringLength);
		LinkedList<String> stack = new LinkedList<String>(stringLength);
		String[] sarray = expression.split("");
		for(int i = stringLength; i > 0; i--) {
			stack.push(sarray[i]);
		}
		
		handleOperands(stack);
		if(doubleStack.isEmpty()) {
			throw new InvalidExpressionException();
		} else {
			double lispcurrStackValue = doubleStack.pop();
			return lispcurrStackValue;						
		}

	}
	/**
	 *  This function deals with operands like "(" or "/" for the evaluate function.
	 *  
	 * @param sarray The string array that is parsed
	 * @param list The stack 
	 */
	public void handleOperands(LinkedList<String> stack) {
		currStackValue = stack.pop();
		LispMath mathTime = new LispMath();
		if(currStackValue.equals("(")) {
			while(!(currStackValue.equals(")"))) {
				if(currStackValue.equals("+")){
					currStackValue = mathTime.add(stack, currStackValue, doubleStack);
				}
				else if(currStackValue.equals(" ") || currStackValue.equals("(")){
					if(stack.isEmpty()){
						throw new InvalidExpressionException();
					} else {
						currStackValue = stack.pop();	
					}
				}
				else if (currStackValue.equals("-")) {
					currStackValue = mathTime.sub(stack, currStackValue, doubleStack);
				}
				else if (currStackValue.equals("/")) {
					currStackValue = mathTime.divide(stack, currStackValue, doubleStack);
				}
				else if (currStackValue.equals("*")) {
					currStackValue = mathTime.multiply(stack, currStackValue, doubleStack);
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

