package uo.cpm.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import uo.cpm.service.Operator;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Calculator extends JFrame{
	private JPanel panel;
	private JTextField textDisplayedNumber;
	private JButton btnAdd;
	private JButton btnSub;
	private JButton btnMul;
	private JButton btnResult;
	private JButton btn0;
	private JButton btn1;
	private JButton btn2;
	private JButton btn3;
	private JButton btn4;
	private JButton btn5;
	private JButton btn6;
	private JButton btn7;
	private JButton btn8;
	private JButton btn9;
	private JButton btnDiv;
	private Operator op;
	private JButton btnClear;
	public Calculator(Operator op) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Calculator.class.getResource("/img/tutorial-preview-large.png")));
		setTitle("Calculadora");
		setResizable(false);
		getContentPane().setBackground(new Color(0, 0, 128));
		setBounds(new Rectangle(0, 0, 540, 670));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.op = op;
		getContentPane().setLayout(null);
		getContentPane().add(getPanel());
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			panel.setBackground(new Color(105, 105, 105));
			panel.setBounds(33, 24, 446, 498);
			panel.setLayout(null);
			panel.add(getTextDisplayedNumber());
			panel.add(getBtnAdd());
			panel.add(getBtnSub());
			panel.add(getBtnMul());
			panel.add(getBtnResult());
			panel.add(getBtn0());
			panel.add(getBtn1());
			panel.add(getBtn2());
			panel.add(getBtn3());
			panel.add(getBtn4());
			panel.add(getBtn5());
			panel.add(getBtn6());
			panel.add(getBtn7());
			panel.add(getBtn8());
			panel.add(getBtn9());
			panel.add(getBtnDiv());
			panel.add(getBtnClear());
		}
		return panel;
	}
	private JTextField getTextDisplayedNumber() {
		if (textDisplayedNumber == null) {
			textDisplayedNumber = new JTextField();
			textDisplayedNumber.setHorizontalAlignment(SwingConstants.RIGHT);
			textDisplayedNumber.setFont(new Font("Dialog", Font.BOLD, 74));
			textDisplayedNumber.setForeground(new Color(255, 255, 255));
			textDisplayedNumber.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			textDisplayedNumber.setEditable(false);
			textDisplayedNumber.setBackground(new Color(0, 255, 255));
			textDisplayedNumber.setBounds(73, 12, 361, 82);
			textDisplayedNumber.setColumns(10);
		}
		return textDisplayedNumber;
	}
	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("+\r\n");
			btnAdd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sum();
				}
			});
			btnAdd.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btnAdd.setForeground(new Color(255, 255, 255));
			btnAdd.setBackground(new Color(0, 255, 255));
			btnAdd.setFont(new Font("Dialog", Font.BOLD, 74));
			btnAdd.setBounds(330, 113, 87, 82);
		}
		return btnAdd;
	}
	private void sum() {
		String displayedValue = getTextDisplayedNumber().getText(); 
		op.editAValue(Integer.parseInt(displayedValue));
		op.editType("+");
		getTextDisplayedNumber().setText("");
	}
	private JButton getBtnSub() {
		if (btnSub == null) {
			btnSub = new JButton("\u2212");
			btnSub.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					sub();
				}
			});
			btnSub.setForeground(Color.WHITE);
			btnSub.setFont(new Font("Dialog", Font.BOLD, 99));
			btnSub.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btnSub.setBackground(Color.CYAN);
			btnSub.setBounds(330, 207, 87, 82);
		}
		return btnSub;
	}
	private void sub() {
		String displayedValue = getTextDisplayedNumber().getText(); 
		op.editAValue(Integer.parseInt(displayedValue));
		op.editType("-");
		getTextDisplayedNumber().setText("");
	}
	private JButton getBtnMul() {
		if (btnMul == null) {
			btnMul = new JButton("x");
			btnMul.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mul();
				}
			});
			btnMul.setVerticalAlignment(SwingConstants.BOTTOM);
			btnMul.setForeground(Color.WHITE);
			btnMul.setFont(new Font("Dialog", Font.BOLD, 74));
			btnMul.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btnMul.setBackground(Color.CYAN);
			btnMul.setBounds(330, 301, 87, 82);
		}
		return btnMul;
	}
	private void mul() {
		String displayedValue = getTextDisplayedNumber().getText(); 
		op.editAValue(Integer.parseInt(displayedValue));
		op.editType("*");
		getTextDisplayedNumber().setText("");
	}
	private JButton getBtnResult() {
		if (btnResult == null) {
			btnResult = new JButton("=");
			btnResult.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String displayedValue = getTextDisplayedNumber().getText();
					op.editBValue(Integer.parseInt(displayedValue));
					switch(op.getOperationType()) {
					case "+":
						op.editResult(op.add(op.getA(), op.getB()))    ;
						getTextDisplayedNumber().setText(String.valueOf(op.getResult()));
						break;
					case "-":
						op.editResult(op.sub(op.getA(), op.getB()))    ;
						getTextDisplayedNumber().setText(String.valueOf(op.getResult()));
						break;
					case "*":
						op.editResult(op.mul(op.getA(), op.getB()))    ;
						getTextDisplayedNumber().setText(String.valueOf(op.getResult()));
						break;
					case "/":
						op.editResult(op.div(op.getA(), op.getB()))    ;
						getTextDisplayedNumber().setText(String.valueOf(op.getResult()));
						break;
					default:
						break;
				}
					op.editAValue(op.getResult());
					op.editType("");
					}
					
				}
			);
			btnResult.setForeground(Color.WHITE);
			btnResult.setFont(new Font("Dialog", Font.BOLD, 99));
			btnResult.setBorder(new LineBorder(new Color(0, 0, 0), 5));
			btnResult.setBackground(new Color(139, 0, 0));
			btnResult.setBounds(127, 404, 170, 82);
		}
		return btnResult;
	}
	private JButton getBtn0() {
		if (btn0 == null) {
			btn0 = new JButton("0\r\n");
			btn0.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appendNumber(0);
				}
			});
			btn0.setForeground(Color.WHITE);
			btn0.setFont(new Font("Dialog", Font.BOLD, 74));
			btn0.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btn0.setBackground(new Color(0, 0, 0));
			btn0.setBounds(12, 404, 87, 82);
		}
		return btn0;
	}
	private void appendNumber(int n) {
		getTextDisplayedNumber().setText(getTextDisplayedNumber().getText()+String.valueOf(n));
	}
	private JButton getBtn1() {
		if (btn1 == null) {
			btn1 = new JButton("1\r\n");
			btn1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appendNumber(1);
				}
			});
			btn1.setForeground(Color.WHITE);
			btn1.setFont(new Font("Dialog", Font.BOLD, 74));
			btn1.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btn1.setBackground(Color.BLACK);
			btn1.setBounds(12, 301, 87, 82);
		}
		return btn1;
	}
	private JButton getBtn2() {
		if (btn2 == null) {
			btn2 = new JButton("2");
			btn2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appendNumber(2);
				}
			});
			btn2.setForeground(Color.WHITE);
			btn2.setFont(new Font("Dialog", Font.BOLD, 74));
			btn2.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btn2.setBackground(Color.BLACK);
			btn2.setBounds(111, 301, 87, 82);
		}
		return btn2;
	}
	private JButton getBtn3() {
		if (btn3 == null) {
			btn3 = new JButton("3");
			btn3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appendNumber(3);
				}
			});
			btn3.setForeground(Color.WHITE);
			btn3.setFont(new Font("Dialog", Font.BOLD, 74));
			btn3.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btn3.setBackground(Color.BLACK);
			btn3.setBounds(210, 301, 87, 82);
		}
		return btn3;
	}
	private JButton getBtn4() {
		if (btn4 == null) {
			btn4 = new JButton("4");
			btn4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appendNumber(4);
				}
			});
			btn4.setForeground(Color.WHITE);
			btn4.setFont(new Font("Dialog", Font.BOLD, 74));
			btn4.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btn4.setBackground(Color.BLACK);
			btn4.setBounds(12, 207, 87, 82);
		}
		return btn4;
	}
	private JButton getBtn5() {
		if (btn5 == null) {
			btn5 = new JButton("5");
			btn5.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appendNumber(5);
				}
			});
			btn5.setForeground(Color.WHITE);
			btn5.setFont(new Font("Dialog", Font.BOLD, 74));
			btn5.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btn5.setBackground(Color.BLACK);
			btn5.setBounds(111, 207, 87, 82);
		}
		return btn5;
	}
	private JButton getBtn6() {
		if (btn6 == null) {
			btn6 = new JButton("6");
			btn6.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appendNumber(6);
				}
			});
			btn6.setForeground(Color.WHITE);
			btn6.setFont(new Font("Dialog", Font.BOLD, 74));
			btn6.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btn6.setBackground(Color.BLACK);
			btn6.setBounds(210, 207, 87, 82);
		}
		return btn6;
	}
	private JButton getBtn7() {
		if (btn7 == null) {
			btn7 = new JButton("7");
			btn7.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appendNumber(7);
				}
			});
			btn7.setForeground(Color.WHITE);
			btn7.setFont(new Font("Dialog", Font.BOLD, 74));
			btn7.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btn7.setBackground(Color.BLACK);
			btn7.setBounds(12, 113, 87, 82);
		}
		return btn7;
	}
	private JButton getBtn8() {
		if (btn8 == null) {
			btn8 = new JButton("8");
			btn8.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appendNumber(8);
				}
			});
			btn8.setForeground(Color.WHITE);
			btn8.setFont(new Font("Dialog", Font.BOLD, 74));
			btn8.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btn8.setBackground(Color.BLACK);
			btn8.setBounds(111, 113, 87, 82);
		}
		return btn8;
	}
	private JButton getBtn9() {
		if (btn9 == null) {
			btn9 = new JButton("9");
			btn9.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					appendNumber(9);
				}
			});
			btn9.setForeground(Color.WHITE);
			btn9.setFont(new Font("Dialog", Font.BOLD, 74));
			btn9.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btn9.setBackground(Color.BLACK);
			btn9.setBounds(210, 113, 87, 82);
		}
		return btn9;
	}
	private JButton getBtnDiv() {
		if (btnDiv == null) {
			btnDiv = new JButton("/");
			btnDiv.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					div();
				}
			});
			btnDiv.setForeground(Color.WHITE);
			btnDiv.setFont(new Font("Dialog", Font.BOLD, 74));
			btnDiv.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btnDiv.setBackground(Color.CYAN);
			btnDiv.setBounds(330, 395, 87, 82);
		}
		return btnDiv;
	}
	private void div() {
		String displayedValue = getTextDisplayedNumber().getText(); 
		op.editAValue(Integer.parseInt(displayedValue));
		op.editType("/");
		getTextDisplayedNumber().setText("");
	}
	private JButton getBtnClear() {
		if (btnClear == null) {
			btnClear = new JButton("CLR");
			btnClear.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					clear();
				}
			});
			btnClear.setForeground(Color.WHITE);
			btnClear.setFont(new Font("Dialog", Font.BOLD, 17));
			btnClear.setBorder(new LineBorder(new Color(139, 0, 0), 5));
			btnClear.setBackground(Color.BLACK);
			btnClear.setBounds(12, 12, 49, 84);
		}
		return btnClear;
	}
	private void clear() {
		getTextDisplayedNumber().setText("");
		op.editAValue(0);
		op.editBValue(0);
		op.editResult(0);
		op.editType("");
	}
}
