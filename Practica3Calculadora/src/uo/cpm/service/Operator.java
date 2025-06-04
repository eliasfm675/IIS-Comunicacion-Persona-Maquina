package uo.cpm.service;

public class Operator {
	private int a;
	private int b;
	private String operationType;
	private int result;
	
	public int getResult() {
		return result;
	}
	private void setResult(int result) {
		this.result = result;
	}
	public String getOperationType() {
		return operationType;
	}
	private void setOperationType(String operationType) {
		this.operationType = operationType;
	}
	public Operator(int a, int b) {
		setA(a);
		setB(b);
	}
	public int getA() {
		return a;
	}
	private void setA(int a) {
		this.a = a;
	}
	public int getB() {
		return b;
	}
	private void setB(int b) {
		this.b = b;
	}
	public void editAValue(int newA) {
		this.a = newA;
	}
	public void editBValue(int newB) {
		this.b = newB;
	}
	public void editType(String type) {
		this.operationType = type;
	}
	public void editResult(int n) {
		this.result = n;
	}
	public int add(int a1, int b1) {
		return a1 + b1;
	}
	public int sub(int a1, int b1) {
		return a1 - b1;
	}
	public int mul(int a1, int b1) {
		return a1 * b1;
	}
	public int div(int a1, int b1) {
		return a1 / b1;
	}
	
}
