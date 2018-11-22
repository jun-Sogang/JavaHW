package hw3;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.color.*;

public class s20121622hw3 implements ActionListener {
	Frame f;
	Panel pTop, pDown, pLeft1, pLeft2, pLeft3, pLeft4, pLeft5;
	TextArea t;
	TextArea resultT;
	Button b[] = new Button[20];
	String bt[] = {"7", "8", "9", "/", "C", "4", "5", "6", "*", "<-", "1", "2", "3", "-", "(", "0", ".", "=", "+", ")"};
	String input = "";
	int stackInfo[];
	

	s20121622hw3() {
		f = new Frame("20121622");
		f.setLayout(new BorderLayout(5, 4));
		f.setBackground(new Color(222, 222, 222));
		
		pTop = new Panel();
		pDown = new Panel();
		
		t = new TextArea("", 1, 30, TextArea.SCROLLBARS_NONE);
		t.getScrollbarVisibility();
		t.setBackground(new Color(255, 255, 255));
		pTop.add(t);
		
		resultT = new TextArea("result", 2, 30, TextArea.SCROLLBARS_NONE);
		resultT.setBackground(new Color(255, 255, 255));
		pTop.add(resultT);
		
		pDown.setLayout(new GridLayout(4, 5, 5, 5));
		
		for (int i = 0; i < b.length; ++i) {
			b[i] = new Button(bt[i]);
			b[i].setBackground(new Color(220, 220, 220));
			b[i].addActionListener(this);
			
			pDown.add(b[i]);
			
		}
		
		
		f.add(pTop, BorderLayout.NORTH);
		f.add(pDown, BorderLayout.CENTER);
		
		f.setSize(300, 300);
		f.setVisible(true);
		
		windowDestroyer listener = new windowDestroyer();
		f.addWindowListener(listener);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command;
		command = e.getActionCommand();
		if (input.length() > 0 && input.charAt(input.length() - 1) == '=') {
			input = "";
		}
		switch(command) {
			case "0":
			case "1":
			case "2":
			case "3":
			case "4":
			case "5":
			case "6":
			case "7":
			case "8":
			case "9":	
			case "+":
			case "-":
			case "*":
			case "/":
			case "(":
			case ")":
			case ".":
				input = input + command;
				t.setText(input);
				break;
			case "<-":
				input = input.substring(0, input.length() - 1);
				t.setText(input);
				break;
			case "C":
				input = "";
				t.setText(input);
				break;
			case "=":
				input += command;
				t.setText(input);

				Stack <Integer> stack = new Stack<>();
				stackInfo = new int[input.length()];
				
				for (int i = 0; i < input.length(); ++i) {
					stackInfo[i] = -1;
					if (input.charAt(i) == '(') {
						stack.push(i);
					}
					else if (input.charAt(i) == ')') {
						if (stack.size() > 0) {
							stackInfo[stack.pop()] = i;
						} else {
							stack.push(i);
							break;
						}
					}
				}
				if (stack.size() > 0) {
					t.setText("invalid operator");
				} else {
					resultT.setText(String.valueOf(calculate(0, input.length())));
				}
				break;
			default:
				t.setText("invalid operator");
				break;
			
		}
	}
	
	public double calculate(int from, int to) {
		double result = 0;
		String num = "";
		char oper = ':';
		System.out.println("from " + from + " to : " + to);
		for (int i = from; i < to; ++i) {
			char ch = input.charAt(i);
			
			
			switch(ch) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '.':
					num += ch;
					break;
				case '+':
				case '-':
				case '*':
				case '/':
					if (i > 0 && (input.charAt(i - 1) == '+' || input.charAt(i - 1) == '-' || input.charAt(i - 1) == '*' || input.charAt(i - 1) == '/')) {
						t.setText("invalid operator");
						return 0;
					}
					// 처음 음수일 때
					if (num == "" && ch == '-') {
						result = -1;
						ch = '*';
					}
					else if (oper == ':') {
						result = Double.parseDouble(num);
					}
					else if (oper == '+'){
						result += Double.parseDouble(num);
					}
					else if (oper == '-') {
						result -= Double.parseDouble(num);
					}
					else if (oper == '*') {
						result *= Double.parseDouble(num);
					}
					else if (oper == '/') {
						result /= Double.parseDouble(num);
					} 
					
					oper = ch;

					num = "";
					break;
				case '(':
					num = String.valueOf(calculate(i + 1, stackInfo[i] + 1));
					i = stackInfo[i];
					break;
				case ')':
					break;
				case '=':
					break;
				default:
					break;
				
			}

		}
		if (num == "") {
			t.setText("invalid operator");
			return 0;
		}
		else if (oper == ':') {
			result = Double.parseDouble(num);
		}
		else if (oper == '+'){
			result += Double.parseDouble(num);
		}
		else if (oper == '-') {
			result -= Double.parseDouble(num);
		}
		else if (oper == '*') {
			result *= Double.parseDouble(num);
		}
		else if (oper == '/') {
			result /= Double.parseDouble(num);
		}
		System.out.println("result : " + result);
		return result;
	}
	
	public class windowDestroyer extends WindowAdapter {
		public void windowClosing(WindowEvent e) {
		 System.exit(0);
		}
	
	}
	
	public static void main(String args[]) {
		s20121622hw3 cal = new s20121622hw3();
	}
}

