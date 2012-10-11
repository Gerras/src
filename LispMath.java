
public class LispMath {
	
	
	/**
	 * This function adds Lisp values together. I use a static running 
	 * accumulator for all of my functions, because I wasn't sure how to
	 * do it any other way when pushing the whole string onto the stack
	 * at the very beginning of evaluate.
	 * 
	 * @param stack The link-list string stack that holds the Lisp values
	 */
	public String add (LinkedList<String> stack, String value, LinkedList<Double> doubleStack) {
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
		return value;
	}
	
	public String sub (LinkedList<String> stack, String value, LinkedList<Double> doubleStack) {
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
		return value;
	}
}
