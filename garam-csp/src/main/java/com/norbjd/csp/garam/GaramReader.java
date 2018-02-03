package com.norbjd.csp.garam;

import com.norbjd.csp.garam.representation.GaramRepresentation;
import com.norbjd.csp.garam.representation.exception.GaramInvalidRepresentationException;

public class GaramReader {

	public static Garam readFromRepresentation(GaramRepresentation GaramRepresentation)
			throws GaramInvalidRepresentationException {
		return GaramRepresentation.get();
	}

}
