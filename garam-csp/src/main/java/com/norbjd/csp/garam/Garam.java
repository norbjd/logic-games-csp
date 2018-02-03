package com.norbjd.csp.garam;

public class Garam {
	private char[] operators;
	private int[] cellsValues;

	public static final int NO_VALUE = -1;

	public Garam(char[] operators, int[] cellsValues) {
		setOperators(operators);
		setCellsValues(cellsValues);
	}

	public Garam(Garam garam) {
		setOperators(garam.getOperators());
		setCellsValues(garam.getCellsValues());
	}

	private char[] getOperators() {
		return operators;
	}

	public char getOperator(int index) {
		return operators[index];
	}

	private void setOperators(char[] operators) {
		this.operators = operators;
	}

	public int[] getCellsValues() {
		return cellsValues;
	}

	public void setCellsValues(int[] cellsValues) {
		this.cellsValues = cellsValues;
	}

	public int getNumberOfCells() {
		return cellsValues.length;
	}

}
