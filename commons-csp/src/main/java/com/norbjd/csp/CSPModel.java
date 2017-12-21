package com.norbjd.csp;

import org.chocosolver.solver.Model;

public abstract class CSPModel {

    protected Model model;
    public CSPVariables variables;
    public CSPConstraints constraints;

    protected abstract void initVariables();
    protected abstract void initConstraints();

}
