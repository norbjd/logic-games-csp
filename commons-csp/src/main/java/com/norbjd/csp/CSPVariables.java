package com.norbjd.csp;

import org.chocosolver.solver.Model;

public abstract class CSPVariables {
    protected Model model;

    public CSPVariables(Model model) {
        this.model = model;
    }
}
