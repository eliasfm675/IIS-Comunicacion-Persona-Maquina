package uo.cpm;

import java.awt.EventQueue;

import uo.cpm.service.Operator;
import uo.cpm.ui.Calculator;

public class Main {
	public static void main(String[] args) {
		/**
		 * Launch the application.
		 */
			Operator op = new Operator(0, 0);
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Calculator frame = new Calculator(op);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				
			}
		});
	}
	}

