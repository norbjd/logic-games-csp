package com.norbjd.csp.fubuki;

import com.norbjd.csp.fubuki.representation.FubukiRepresentation;
import com.norbjd.csp.fubuki.representation.exception.FubukiInvalidRepresentationException;

public class FubukiReader {

	public static Fubuki readFromRepresentation(FubukiRepresentation fubukiRepresentation)
			throws FubukiInvalidRepresentationException {
		return fubukiRepresentation.get();
	}

}
