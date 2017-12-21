package com.norbjd.csp;

import org.chocosolver.memory.trailing.EnvironmentTrailing;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Settings;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.explanations.ExplanationEngine;

public abstract class CSP {
    private String name = "CSP";
    private boolean debug = false;

    protected CSPModel cspModel;

    public CSP(String name, boolean debug, CSPModel cspModel) {
        this.name = name;
        this.debug = debug;
        this.cspModel = cspModel;
    }

    private void initModel() {
        Settings settings;
        if(debug) {
            settings = new DebugSettings();
        } else {
            settings = new DefaultSettings();
        }

        cspModel.model = new Model(new EnvironmentTrailing(),
                name,
                settings
        );
    }

    public void initCSP() {
        initModel();
        cspModel.initVariables();
        cspModel.initConstraints();

        cspModel.constraints.getConstraints().forEach(Constraint::post);
    }

    public void solve() {
        Solver solver = cspModel.model.getSolver();

        solver.showDecisions();
        solver.showSolutions();
        solver.showContradiction();

        solver.printStatistics();
        solver.setExplainer(new ExplanationEngine(cspModel.model, true, true));

        solver.solve();
    }

    public abstract void printSolution();

}
