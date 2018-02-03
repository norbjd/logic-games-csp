package com.norbjd.csp.fubuki.representation;

import com.norbjd.csp.fubuki.Fubuki;
import com.norbjd.csp.fubuki.representation.exception.FubukiInvalidRepresentationException;

public interface FubukiRepresentation {
	Fubuki get() throws FubukiInvalidRepresentationException;
}