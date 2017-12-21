package com.norbjd.csp;

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
