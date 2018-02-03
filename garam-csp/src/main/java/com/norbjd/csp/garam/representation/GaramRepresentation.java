package com.norbjd.csp.garam.representation;

import com.norbjd.csp.garam.Garam;

public interface GaramRepresentation {
	public Garam get() throws InvalidGaramRepresentationException;
}
