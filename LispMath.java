
public class LispMath {
	
	
	/**
	 * This function adds Lisp values together. I use a static running 
	 * accumulator for all of my functions, because I wasn't sure how to
	 * do it any other way when pushing the whole string onto the stack
	 * at the very beginning of evaluate.
	 * 
	 * @param stack The link-list string stack that holds the Lisp values
	 * @param value The value of that is currently being looked at
	 * @param doubleStack The link-list double stack that holds the accumulator
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
	
	/**
	 * This function subtracts Lisp values.  I use a static running 
	 * accumulator for all of my functions, because I wasn't sure how to
	 * do it any other way when pushing the whole string onto the stack
	 * at the very beginning of evaluate.
	 * 
	 * @param stack The link-list string stack that holds the Lisp values
	 * @param value The value of that is currently being looked at
	 * @param doubleStack The link-list double stack that holds the accumulator
	 */
	public String sub (LinkedList<String> stack, String value, LinkedList<Double> doubleStack) {
		double accumulator = 0;
		while (!(value.equals(")"))) {
			if(value.equals("-")) {
				doubleStack.push(accumulator);
				value = checkBoundaries(stack);
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

	public String multiply(LinkedList<String> stack, String value, LinkedList<Double> doubleStack) {
		double accumulator = 1;
		while(!(value.equals(")"))) {
			if(value.equals("*")) {
				doubleStack.push(accumulator);
				if(stack.isEmpty()){
					throw new InvalidExpressionException();
				} else {
					value = stack.pop();	
				}
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
		return value;
	}

	public String divide(LinkedList<String> stack, String value, LinkedList<Double> doubleStack) {
		double accumulator = 1;
		while(!(value.equals(")"))) {
			if(value.equals("/")) {
				doubleStack.push(accumulator);
				value = checkBoundaries(stack);
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
		return value;
	}
	
	public String checkBoundaries(LinkedList<String> stack){
		String stackTop = stack.top();
		if(stack.isEmpty()){
			throw new InvalidExpressionException();
		} 
		else if(stackTop.equals(")")){
			throw new InvalidExpressionException();
		}else {
			String value = stack.pop();
			return value;
		}
	}
}
