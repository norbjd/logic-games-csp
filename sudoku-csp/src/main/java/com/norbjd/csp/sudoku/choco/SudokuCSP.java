package com.norbjd.csp.sudoku.choco;

import com.norbjd.csp.settings.DebugSettings;
import com.norbjd.csp.settings.DefaultSettings;
import com.norbjd.csp.sudoku.Sudoku;
import com.norbjd.csp.sudoku.exception.SudokuInitializationException;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.chocosolver.memory.trailing.EnvironmentTrailing;
import org.chocosolver.solver.Model;
import org.chocosolver.solver.Settings;
import org.chocosolver.solver.Solver;
import org.chocosolver.solver.constraints.Constraint;
import org.chocosolver.solver.explanations.ExplanationEngine;
import org.chocosolver.solver.variables.IntVar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SudokuCSP {

	private Sudoku sudoku;
	private Model model;

	private IntVar[] cells;

	private boolean debug = true;

	public SudokuCSP(Sudoku sudoku) {
		setSudoku(sudoku);
		initModel();
	}

	public void setSudoku(Sudoku sudoku) {
		this.sudoku = sudoku;
	}

	private IntVar[] extractSudokuLine(int lineIndex) {
		return Arrays.asList(cells)
				.subList(lineIndex * Sudoku.SUDOKU_SIDE_LENGTH, (lineIndex + 1) * Sudoku.SUDOKU_SIDE_LENGTH)
				.toArray(new IntVar[Sudoku.SUDOKU_SIDE_LENGTH]);
	}

	private IntVar[] extractSudokuColumn(int columnIndex) {
		return IntStream.range(0, Sudoku.SUDOKU_NB_CELLS).mapToObj(i -> new ImmutablePair<>(i, cells[i]))
				.filter((Pair<Integer, IntVar> pair) -> pair.getLeft() % Sudoku.SUDOKU_SIDE_LENGTH == columnIndex)
				.map(Pair::getRight).toArray(IntVar[]::new);
	}

	private IntVar[] extractSudokuBlock(int blockIndex) {
		int blockLine = blockIndex / Sudoku.SUDOKU_BLOCK_SIDE_LENGTH;
		int blockColumn = blockIndex % Sudoku.SUDOKU_BLOCK_SIDE_LENGTH;

		List<IntVar> blockCells = new ArrayList<>(Sudoku.SUDOKU_SIDE_LENGTH);

		for (int i = blockLine * Sudoku.SUDOKU_BLOCK_SIDE_LENGTH; i < (blockLine + 1)
				* Sudoku.SUDOKU_BLOCK_SIDE_LENGTH; i++) {
			for (int j = blockColumn * Sudoku.SUDOKU_BLOCK_SIDE_LENGTH; j < (blockColumn + 1)
					* Sudoku.SUDOKU_BLOCK_SIDE_LENGTH; j++) {
				blockCells.add(cells[i * Sudoku.SUDOKU_SIDE_LENGTH + j]);
			}
		}

		return blockCells.toArray(new IntVar[Sudoku.SUDOKU_SIDE_LENGTH]);
	}

	private List<Constraint> presetValuesConstraints() {
		Stream<Pair<IntVar, Integer>> zippedCellsWithValues = IntStream
				.range(0, Sudoku.SUDOKU_SIDE_LENGTH * Sudoku.SUDOKU_SIDE_LENGTH)
				.mapToObj(i -> new ImmutablePair<>(cells[i], sudoku.getCellsValues()[i]));

		Stream<Pair<IntVar, Integer>> zippedCellsWithPresetValues = zippedCellsWithValues
				.filter((Pair<IntVar, Integer> pair) -> pair.getRight() != Sudoku.NO_VALUE);

		return zippedCellsWithPresetValues
				.map((Pair<IntVar, Integer> pair) -> model.arithm(pair.getLeft(), "=", pair.getRight()))
				.collect(Collectors.toList());
	}

	private List<Constraint> allDifferentsNumbersOnLinesConstraints() {
		return IntStream.range(0, Sudoku.SUDOKU_SIDE_LENGTH)
				.mapToObj(line -> model.allDifferent(extractSudokuLine(line))).collect(Collectors.toList());
	}

	private List<Constraint> allDifferentsNumbersOnColumnsConstraints() {
		return IntStream.range(0, Sudoku.SUDOKU_SIDE_LENGTH)
				.mapToObj(column -> model.allDifferent(extractSudokuColumn(column))).collect(Collectors.toList());
	}

	private List<Constraint> allDifferentsNumbersOnBlocksConstraints() {
		return IntStream.range(0, Sudoku.SUDOKU_SIDE_LENGTH)
				.mapToObj(block -> model.allDifferent(extractSudokuBlock(block))).collect(Collectors.toList());
	}

	private Model initModel() {
		Settings settings;

		if (debug) {
			settings = new DebugSettings();
		} else {
			settings = new DefaultSettings();
		}

		model = new Model(new EnvironmentTrailing(), "Sudoku", settings);

		cells = model.intVarArray(Sudoku.SUDOKU_SIDE_LENGTH * Sudoku.SUDOKU_SIDE_LENGTH, 1, Sudoku.SUDOKU_SIDE_LENGTH);

		presetValuesConstraints().forEach(Constraint::post);
		allDifferentsNumbersOnLinesConstraints().forEach(Constraint::post);
		allDifferentsNumbersOnColumnsConstraints().forEach(Constraint::post);
		allDifferentsNumbersOnBlocksConstraints().forEach(Constraint::post);

		return model;
	}

	public Sudoku solve() throws SudokuInitializationException {
		Solver solver = model.getSolver();

		if (debug) {
			solver.showDecisions();
			solver.showSolutions();
			solver.showContradiction();

			solver.printStatistics();
			solver.setExplainer(new ExplanationEngine(model, true, true));
		}

		solver.solve();

		int[] cellsValues = Stream.of(cells).mapToInt(IntVar::getValue).toArray();

		return new Sudoku(cellsValues);
	}

}
