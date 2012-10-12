
public class LispMath extends LispEvaluator {
	
	
	/**
	 * This function adds Lisp currStackValues together. I use a static running 
	 * accumulator for all of my functions, because I wasn't sure how to
	 * do it any other way when pushing the whole string onto the stack
	 * at the very beginning of evaluate.
	 * 
	 * @param stack The link-list string stack that holds the Lisp currStackValues
	 * @param currStackValue The currStackValue of that is currently being looked at
	 * @param doubleStack The link-list double stack that holds the accumulator
	 */
	public String add (LinkedList<String> stack, String currStackValue, LinkedList<Double> doubleStack) {
		double accumulator = 0;
		while(!(currStackValue.equals(")"))) {
			if(currStackValue.equals("+")) {
				doubleStack.push(accumulator);
				if(stack.isEmpty()){
					throw new InvalidExpressionException();
				} else {
					currStackValue = stack.pop();	
				}
			}
			else if(currStackValue.equals(" ")) {
				if(stack.isEmpty()) {
					throw new InvalidExpressionException();
				} else {
					currStackValue = stack.pop();	
				}
			}
			else if(currStackValue.equals("(")) {
				handleOperands(stack);
			}
			else if (currStackValue.equals("-")) {
				throw new InvalidExpressionException();
			}
			else if (currStackValue.equals("a")) {
				throw new InvalidExpressionException();
			}
			else if (currStackValue.equals("*") || currStackValue.equals("/") || currStackValue.equals("-")) {
				throw new InvalidExpressionException();
			}
			else if(Integer.parseInt(currStackValue) <= 9 && Integer.parseInt(currStackValue) >= 0 ) {
				if(!(doubleStack.isEmpty())) {
					accumulator = doubleStack.pop();
					accumulator += Double.parseDouble(currStackValue);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						currStackValue = stack.pop();
					}	
				} else {
					accumulator += Double.parseDouble(currStackValue);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						currStackValue = stack.pop();
					}
				}
			} else {
				throw new InvalidExpressionException();
			}
		}
		return currStackValue;
	}
	
	/**
	 * This function subtracts Lisp currStackValues.  I use a static running 
	 * accumulator for all of my functions, because I wasn't sure how to
	 * do it any other way when pushing the whole string onto the stack
	 * at the very beginning of evaluate.
	 * 
	 * @param stack The link-list string stack that holds the Lisp currStackValues
	 * @param currStackValue The currStackValue of that is currently being looked at
	 * @param doubleStack The link-list double stack that holds the accumulator
	 */
	public String sub (LinkedList<String> stack, String currStackValue, LinkedList<Double> doubleStack) {
		double accumulator = 0;
		double midaccum;

		while (!(currStackValue.equals(")"))) {
			if(currStackValue.equals("-")) {
				doubleStack.push(accumulator);
				currStackValue = checkBoundaries(stack);
			}
			else if(currStackValue.equals(" ")) {
				currStackValue = stack.pop();
			}
			else if (currStackValue.equals("(")) {
				handleOperands(stack);
			}
			else if (currStackValue.equals("*") || currStackValue.equals("/") || currStackValue.equals("+")) {
				throw new InvalidExpressionException();
			}
			else if (Integer.parseInt(currStackValue) <= 9 && Integer.parseInt(currStackValue) >= 0) {
				while(!(currStackValue.equals(")"))) {
					if(currStackValue.equals(" ")){
						currStackValue = stack.pop();
					} else {
						doubleStack.push(Double.parseDouble(currStackValue));
						currStackValue = stack.pop();
					}
				}
				while(!(doubleStack.isEmpty())) {
					midaccum = doubleStack.pop();
					if (doubleStack.isEmpty()) {
						accumulator = accumulator - midaccum;
					} else {
						accumulator = doubleStack.pop() - midaccum ;
					}
				} 
			} else {
				throw new InvalidExpressionException();
			}
		}
		doubleStack.push(accumulator);
		return currStackValue;
	}

	public String multiply(LinkedList<String> stack, String currStackValue, LinkedList<Double> doubleStack) {
		double accumulator = 1;
		while(!(currStackValue.equals(")"))) {
			if(currStackValue.equals("*")) {
				doubleStack.push(accumulator);
				if(stack.isEmpty()){
					throw new InvalidExpressionException();
				} else {
					currStackValue = stack.pop();	
				}
			}
			else if(currStackValue.equals(" ")) {
				currStackValue = stack.pop();
			}
			else if(currStackValue.equals("(")) {
				handleOperands(stack);
			}
			else if (currStackValue.equals("-") || currStackValue.equals("/") || currStackValue.equals("+")) {
				throw new InvalidExpressionException();
			}
			else if (Integer.parseInt(currStackValue) <= 9 && Integer.parseInt(currStackValue) >= 0) {
				if(!(doubleStack.isEmpty())) {
					accumulator = doubleStack.pop();
					accumulator *= Double.parseDouble(currStackValue);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						currStackValue = stack.pop();
					}
				} else {
					accumulator *= Double.parseDouble(currStackValue);
					doubleStack.push(accumulator);
					if(stack.isEmpty()) {
						throw new InvalidExpressionException();
					} else {
						currStackValue = stack.pop();
					}
				}
			} else {
				throw new InvalidExpressionException();
			}
		}
		
		return currStackValue;
	}

	public String divide(LinkedList<String> stack, String currStackValue, LinkedList<Double> doubleStack) {
		double accumulator = 1;
		double midaccum;
		while(!(currStackValue.equals(")"))) {
			if(currStackValue.equals("/")) {
				doubleStack.push(accumulator);
				currStackValue = checkBoundaries(stack);
			}
			else if(currStackValue.equals(" ")) {
				currStackValue = stack.pop();
			}
			else if (currStackValue.equals("(")) {
				handleOperands(stack);
			}
			else if (currStackValue.equals("*") || currStackValue.equals("-") || currStackValue.equals("+")) {
				throw new InvalidExpressionException();
			}
			else if (Integer.parseInt(currStackValue) <= 9 && Integer.parseInt(currStackValue) >= 0) {
				while(!(currStackValue.equals(")"))) {
					if (currStackValue.equals(" ")) {
						currStackValue = stack.pop();
					} else {
						doubleStack.push(Double.parseDouble(currStackValue));
						currStackValue = stack.pop();
					}
				}
				while(!(doubleStack.isEmpty())) {
					midaccum = doubleStack.pop();
					if (doubleStack.isEmpty()) {
						accumulator = accumulator / midaccum;
					} else {
						accumulator = doubleStack.pop() / midaccum;
					}
				}
			} else {
				throw new InvalidExpressionException();
			}
		}
		doubleStack.push(accumulator);
		return currStackValue;
	}
}
