package com.norbjd.csp.fubuki;

import java.io.PrintStream;

public class FubukiPrinter {

	private PrintStream out;

	FubukiPrinter(PrintStream out) {
		this.out = out;
	}

	private String cellValueToString(int cellValue) {
		if (cellValue == Fubuki.NO_VALUE) {
			return "_";
		} else {
			return String.valueOf(cellValue);
		}
	}

	private String toString(Fubuki fubuki) {
		StringBuilder sb = new StringBuilder();
		for (int row = 0; row < fubuki.getHeight(); row++) {
			for (int col = 0; col < fubuki.getWidth(); col++) {
				int cellValue = fubuki.getCellsValues()[row * fubuki.getWidth() + col];
				sb.append(cellValueToString(cellValue));
				sb.append("\t");
			}
			sb.append("=\t");
			sb.append(fubuki.getRowSums()[row]);
			sb.append("\n");
		}

		for (int col = 0; col < fubuki.getWidth(); col++) {
			sb.append("=");
			sb.append("\t");
		}

		sb.append("\n");

		for (int col = 0; col < fubuki.getWidth(); col++) {
			sb.append(fubuki.getColSums()[col]);
			sb.append("\t");
		}

		return sb.toString();
	}

	public void printFubuki(Fubuki fubuki) {
		out.println(toString(fubuki));
	}

}
