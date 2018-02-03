package com.norbjd.csp.fubuki.representation;

import com.norbjd.csp.fubuki.Fubuki;

public interface FubukiRepresentation {
	public Fubuki get() throws InvalidFubukiRepresentationException;
}