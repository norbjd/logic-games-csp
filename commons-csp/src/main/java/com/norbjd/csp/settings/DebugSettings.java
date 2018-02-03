package com.norbjd.csp.settings;

import org.chocosolver.solver.Settings;

public class DebugSettings implements Settings {

	@Override
	public boolean debugPropagation() {
		return true;
	}

	@Override
	public boolean warnUser() {
		return true;
	}

}
