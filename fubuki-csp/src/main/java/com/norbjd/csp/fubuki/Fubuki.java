package com.norbjd.csp.fubuki;

public class Fubuki {

	public static final int NO_VALUE = -1;

	private int height;
	private int width;
	private int[] rowSums;
	private int[] colSums;
	private int[] cellsValues;

	public Fubuki(int height, int width, int[] rowSums, int[] colSums, int[] cellsValues) {
		setHeight(height);
		setWidth(width);
		setRowSums(rowSums);
		setColSums(colSums);
		setCellsValues(cellsValues);
	}

	public Fubuki(Fubuki fubuki) {
		setHeight(fubuki.getHeight());
		setWidth(fubuki.getWidth());
		setRowSums(fubuki.getRowSums());
		setColSums(fubuki.getColSums());
		setCellsValues(fubuki.getCellsValues());
	}

	public int getHeight() {
		return height;
	}

	private void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	private void setWidth(int width) {
		this.width = width;
	}

	public int[] getRowSums() {
		return rowSums;
	}

	private void setRowSums(int[] rowSums) {
		this.rowSums = rowSums;
	}

	public int[] getColSums() {
		return colSums;
	}

	private void setColSums(int[] colSums) {
		this.colSums = colSums;
	}

	public int[] getCellsValues() {
		return cellsValues;
	}

	public void setCellsValues(int[] cellsValues) {
		this.cellsValues = cellsValues;
	}

	public int getNumberOfCells() {
		return width * height;
	}

}
