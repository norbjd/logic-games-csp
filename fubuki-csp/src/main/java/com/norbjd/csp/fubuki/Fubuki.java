package com.norbjd.csp.fubuki;

public class Fubuki {

    private int height;
    private int width;
    private int[] rowSums;
    private int[] colSums;
    private int[] cellsValues;

    public Fubuki(int height,
                  int width,
                  int[] rowSums,
                  int[] colSums,
                  int[] cellsValues) {
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int row = 0; row < height; row++) {
            for(int col = 0; col < width; col++) {
                sb.append(cellsValues[row * width + col]);
                sb.append("\t");
            }
            sb.append(rowSums[row]);
            sb.append("\n");
        }

        for(int col = 0; col < width; col++) {
            sb.append(colSums[col]);
            sb.append("\t");
        }

        return sb.toString();
    }

}
