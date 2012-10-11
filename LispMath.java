
public class LispMath {
	
	public void add (LinkedList<String> stack, String value, LinkedList<Double> doubleStack) {
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
}
