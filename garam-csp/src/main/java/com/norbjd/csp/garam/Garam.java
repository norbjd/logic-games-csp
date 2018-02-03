package com.norbjd.csp.garam;

public class Garam {
	private char[] operators;
	private int[] cellsValues;

	public Garam(char[] operators, int[] cellsValues) {
		setOperators(operators);
		setCellsValues(cellsValues);
	}

	public Garam(Garam garam) {
		setOperators(garam.getOperators());
		setCellsValues(garam.getCellsValues());
	}

	public char[] getOperators() {
		return operators;
	}

	public char getOperator(int index) {
		return operators[index];
	}

	public void setOperators(char[] operators) {
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

	public String getCellValue(int index) {
		int cellValue = getCellsValues()[index];

		if (cellValue == -1) {
			return "X";
		} else {
			return String.valueOf(cellValue);
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		/*
		 * for(int i = 0; i < cellsValues.length - 1; i++) { sb.append(cellsValues[i]);
		 * sb.append(","); } sb.append(cellsValues[cellsValues.length - 1]);
		 */

		sb.append(getCellValue(0)).append("\t").append(operators[0]).append("\t").append(getCellValue(1))
				.append("\t=\t").append(getCellValue(2));
		sb.append("\t\t\t\t");
		sb.append(getCellValue(3)).append("\t").append(operators[1]).append("\t").append(getCellValue(4))
				.append("\t=\t").append(getCellValue(5));
		sb.append("\n");

		sb.append(operators[2]).append("\t\t\t\t").append(operators[3]).append("\t\t\t\t").append(operators[4])
				.append("\t\t\t\t").append(operators[5]);
		sb.append("\n");

		sb.append(getCellValue(6)).append("\t\t\t\t").append(getCellValue(7)).append("\t").append(operators[6])
				.append("\t").append(getCellValue(8)).append("\t=\t").append(getCellValue(9)).append("\t\t\t\t")
				.append(getCellValue(10));
		sb.append("\n");

		sb.append("=\t\t\t\t=\t\t\t\t=\t\t\t\t=");
		sb.append("\n");

		sb.append(getCellValue(11)).append("\t\t\t\t").append(getCellValue(12)).append("\t\t\t\t")
				.append(getCellValue(13)).append("\t\t\t\t").append(getCellValue(14));
		sb.append("\n");

		sb.append(getCellValue(15)).append("\t").append(operators[7]).append("\t").append(getCellValue(16))
				.append("\t=\t").append(getCellValue(17));
		sb.append("\t\t\t\t");
		sb.append(getCellValue(18)).append("\t").append(operators[8]).append("\t").append(getCellValue(19))
				.append("\t=\t").append(getCellValue(20));
		sb.append("\n");

		sb.append("\t\t").append(operators[9]).append("\t\t\t\t\t\t\t\t").append(operators[10]);
		sb.append("\n");

		sb.append("\t\t").append(getCellValue(21)).append("\t\t\t\t\t\t\t\t").append(getCellValue(22));
		sb.append("\n");

		sb.append("\t\t=\t\t\t\t\t\t\t\t=");
		sb.append("\n");

		sb.append(getCellValue(23)).append("\t").append(operators[11]).append("\t").append(getCellValue(24))
				.append("\t=\t").append(getCellValue(25));
		sb.append("\t\t\t\t");
		sb.append(getCellValue(26)).append("\t").append(operators[12]).append("\t").append(getCellValue(27))
				.append("\t=\t").append(getCellValue(28));
		sb.append("\n");

		sb.append(operators[13]).append("\t\t\t\t").append(operators[14]).append("\t\t\t\t").append(operators[15])
				.append("\t\t\t\t").append(operators[16]);
		sb.append("\n");

		sb.append(getCellValue(29)).append("\t\t\t\t").append(getCellValue(30)).append("\t").append(operators[17])
				.append("\t").append(getCellValue(31)).append("\t=\t").append(getCellValue(32)).append("\t\t\t\t")
				.append(getCellValue(33));
		sb.append("\n");

		sb.append("=\t\t\t\t=\t\t\t\t=\t\t\t\t=");
		sb.append("\n");

		sb.append(getCellValue(34)).append("\t\t\t\t").append(getCellValue(35)).append("\t\t\t\t")
				.append(getCellValue(36)).append("\t\t\t\t").append(getCellValue(37));
		sb.append("\n");

		sb.append(getCellValue(38)).append("\t").append(operators[18]).append("\t").append(getCellValue(39))
				.append("\t=\t").append(getCellValue(40));
		sb.append("\t\t\t\t");
		sb.append(getCellValue(41)).append("\t").append(operators[19]).append("\t").append(getCellValue(42))
				.append("\t=\t").append(getCellValue(43));
		sb.append("\n");

		return sb.toString();
	}
}
