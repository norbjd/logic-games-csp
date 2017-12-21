package com.norbjd.csp.sudoku;

public class Sudoku {

    private int[] cellsValues;

    public Sudoku(int[] cellsValues) {
        setCellsValues(cellsValues);
    }

    public Sudoku(Sudoku sudoku) {
        setCellsValues(sudoku.getCellsValues());
    }

    public int[] getCellsValues() {
        return cellsValues;
    }

    public void setCellsValues(int[] cellsValues) {
        this.cellsValues = cellsValues;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                sb.append(cellsValues[i * 9 + j]);
                sb.append(" ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
