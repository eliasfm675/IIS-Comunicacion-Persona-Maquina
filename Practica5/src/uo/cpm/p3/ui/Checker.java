package uo.cpm.p3.ui;

import java.time.LocalDate;

import javax.swing.*;

public class Checker {
	public boolean checkTextField(JTextField j) {
		return (j.getText().isBlank() || j.getText().isEmpty()) ? false: true; 
	}
	public boolean checkPasswordEmpty(JPasswordField j) {
		return (String.valueOf(j.getPassword()).isBlank() || String.valueOf(j.getPassword()).isEmpty()) ? false: true; 
	}
	public boolean checkPasswordTheSame(JPasswordField j1, JPasswordField j2) {
		return (String.valueOf(j1.getPassword()).equals(String.valueOf(j2.getPassword())));
	}
	public boolean checkButtons(JRadioButton j) {
		return j.isSelected();
	}
	public boolean checkYear(JComboBox j) {
		return LocalDate.now().getYear() - Integer.parseInt(j.getSelectedItem().toString()) < 18;
		
	}
}
