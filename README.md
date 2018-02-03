# Logic games solving with Constraint Programming

This repository aims to present Constraint Programming (CP) by modeling
and solving small and easy logic games. This is used as an introduction to
CP.

Logic games, represented as CSP (Constraint Satisfaction Problem), are
solved using [Choco Solver](http://www.choco-solver.org/), in Java.
Currently, the logic games solved are :

- [Sudoku](https://en.wikipedia.org/wiki/Sudoku)
- [Garam](https://www.garam.fr)
- Fubuki, [example](http://www.thepuzzleclub.com/fubuki.php)

Code duplication and architecture duplication between each module
are deliberate, since the focus is done on CP, and over-designing by
abstracting may have cluttered up reading. The main goal here by doing
this is that each module can be read separately.

Classes ending with `CSP` are the main classes to read for CP examples.
For the same readability reasons previously stated, each step of CP (variables
initialization, constraints initialization, model solving) are defined
in the same class.