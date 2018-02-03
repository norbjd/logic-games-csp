package com.norbjd.csp.garam.representation;

import com.norbjd.csp.garam.Garam;
import com.norbjd.csp.garam.representation.exception.GaramInvalidRepresentationException;

import java.util.Arrays;

public class GaramStringRepresentation implements GaramRepresentation {

	private String representation;

	GaramStringRepresentation(String representation) {
		setRepresentation(representation);
	}

	private void setRepresentation(String representation) {
		this.representation = representation;
	}

	public Garam get() throws GaramInvalidRepresentationException {
		try {
			String[] lines = representation.split("\n");

			char[] operands = lines[0].toCharArray();

			String[] valuesLine = lines[1].split(",");
			int[] cellsValues = Arrays.stream(valuesLine).mapToInt(i -> {
				if (i.equals("_"))
					return -1;
				else
					return Integer.parseInt(i);
			}).toArray();

			return new Garam(operands, cellsValues);
		} catch (Exception e) {
			throw new GaramInvalidRepresentationException(e.getMessage());
		}
	}

}
