package com.norbjd.csp.garam.representation;

import com.norbjd.csp.garam.Garam;
import com.norbjd.csp.garam.representation.exception.GaramInvalidRepresentationException;

public interface GaramRepresentation {
	Garam get() throws GaramInvalidRepresentationException;
}
