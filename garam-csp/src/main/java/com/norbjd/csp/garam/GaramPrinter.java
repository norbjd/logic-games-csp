package com.norbjd.csp.garam;

import java.io.PrintStream;

public class GaramPrinter {

	PrintStream out;

	GaramPrinter(PrintStream out) {
		this.out = out;
	}

	private String cellValueToString(int cellValue) {
		if (cellValue == Garam.NO_VALUE) {
			return "_";
		} else {
			return String.valueOf(cellValue);
		}
	}

	private String getCellValueToString(Garam garam, int index) {
		int cellValue = garam.getCellsValues()[index];

		return cellValueToString(cellValue);
	}

	private String getOperatorToString(Garam garam, int index) {
		return String.valueOf(garam.getOperator(index));
	}

	private String toString(Garam garam) {
		StringBuilder sb = new StringBuilder();

		sb.append(getCellValueToString(garam, 0)).append("\t").append(getOperatorToString(garam, 0)).append("\t").append(getCellValueToString(garam, 1))
				.append("\t=\t").append(getCellValueToString(garam, 2));
		sb.append("\t\t\t\t");
		sb.append(getCellValueToString(garam, 3)).append("\t").append(getOperatorToString(garam, 1)).append("\t").append(getCellValueToString(garam, 4))
				.append("\t=\t").append(getCellValueToString(garam, 5));
		sb.append("\n");

		sb.append(getOperatorToString(garam, 2)).append("\t\t\t\t").append(getOperatorToString(garam, 3)).append("\t\t\t\t").append(getOperatorToString(garam, 4))
				.append("\t\t\t\t").append(getOperatorToString(garam, 5));
		sb.append("\n");

		sb.append(getCellValueToString(garam, 6)).append("\t\t\t\t").append(getCellValueToString(garam, 7)).append("\t").append(getOperatorToString(garam, 6))
				.append("\t").append(getCellValueToString(garam, 8)).append("\t=\t").append(getCellValueToString(garam, 9)).append("\t\t\t\t")
				.append(getCellValueToString(garam, 10));
		sb.append("\n");

		sb.append("=\t\t\t\t=\t\t\t\t=\t\t\t\t=");
		sb.append("\n");

		sb.append(getCellValueToString(garam, 11)).append("\t\t\t\t").append(getCellValueToString(garam, 12)).append("\t\t\t\t")
				.append(getCellValueToString(garam, 13)).append("\t\t\t\t").append(getCellValueToString(garam, 14));
		sb.append("\n");

		sb.append(getCellValueToString(garam, 15)).append("\t").append(getOperatorToString(garam, 7)).append("\t").append(getCellValueToString(garam, 16))
				.append("\t=\t").append(getCellValueToString(garam, 17));
		sb.append("\t\t\t\t");
		sb.append(getCellValueToString(garam, 18)).append("\t").append(getOperatorToString(garam, 8)).append("\t").append(getCellValueToString(garam, 19))
				.append("\t=\t").append(getCellValueToString(garam, 20));
		sb.append("\n");

		sb.append("\t\t").append(getOperatorToString(garam, 9)).append("\t\t\t\t\t\t\t\t").append(getOperatorToString(garam, 10));
		sb.append("\n");

		sb.append("\t\t").append(getCellValueToString(garam, 21)).append("\t\t\t\t\t\t\t\t").append(getCellValueToString(garam, 22));
		sb.append("\n");

		sb.append("\t\t=\t\t\t\t\t\t\t\t=");
		sb.append("\n");

		sb.append(getCellValueToString(garam, 23)).append("\t").append(getOperatorToString(garam, 11)).append("\t").append(getCellValueToString(garam, 24))
				.append("\t=\t").append(getCellValueToString(garam, 25));
		sb.append("\t\t\t\t");
		sb.append(getCellValueToString(garam, 26)).append("\t").append(getOperatorToString(garam, 12)).append("\t").append(getCellValueToString(garam, 27))
				.append("\t=\t").append(getCellValueToString(garam, 28));
		sb.append("\n");

		sb.append(getOperatorToString(garam, 13)).append("\t\t\t\t").append(getOperatorToString(garam, 14)).append("\t\t\t\t").append(getOperatorToString(garam, 15))
				.append("\t\t\t\t").append(getOperatorToString(garam, 16));
		sb.append("\n");

		sb.append(getCellValueToString(garam, 29)).append("\t\t\t\t").append(getCellValueToString(garam, 30)).append("\t").append(getOperatorToString(garam, 17))
				.append("\t").append(getCellValueToString(garam, 31)).append("\t=\t").append(getCellValueToString(garam, 32)).append("\t\t\t\t")
				.append(getCellValueToString(garam, 33));
		sb.append("\n");

		sb.append("=\t\t\t\t=\t\t\t\t=\t\t\t\t=");
		sb.append("\n");

		sb.append(getCellValueToString(garam, 34)).append("\t\t\t\t").append(getCellValueToString(garam, 35)).append("\t\t\t\t")
				.append(getCellValueToString(garam, 36)).append("\t\t\t\t").append(getCellValueToString(garam, 37));
		sb.append("\n");

		sb.append(getCellValueToString(garam, 38)).append("\t").append(getOperatorToString(garam, 18)).append("\t").append(getCellValueToString(garam, 39))
				.append("\t=\t").append(getCellValueToString(garam, 40));
		sb.append("\t\t\t\t");
		sb.append(getCellValueToString(garam, 41)).append("\t").append(getOperatorToString(garam, 19)).append("\t").append(getCellValueToString(garam, 42))
				.append("\t=\t").append(getCellValueToString(garam, 43));
		sb.append("\n");

		return sb.toString();
	}

	public void printGaram(Garam Garam) {
		out.println(toString(Garam));
	}

}
