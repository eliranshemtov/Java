package view;

import java.util.Observable;
import java.util.Scanner;

import model.Solution;
import model.algorithms.Action;

/**
 * MyConsoleView Class Extends Observable and implements View<br>
 * Not relevant for GUI
 */
public class MyConsoleView extends Observable implements View {

	private String command;
	
	@Override
	/**
	 * start() prints in command line the user's options, and will scan for input.<br>
	 * it will notify the observer about the new command the was input from user
	 */
	public void start() {
		// present to the user the optional commands
		System.out.println("Available commands:");
		System.out.println("1) SelectDomain SlidePuzzleDomain:3 (the dimension of the puzzle)");
		System.out.println("   SelectDomain MazeDomain:8,8,10 (height,width,amount of walls)");
		System.out.println("2) SelectAlgorithm Astar / SelectAlgorithm BFS (if you choose Astar, choose the suitable heuristic afterwards)");
		System.out.println("3) SelectHeuristic MazeHeuristic / SelectHeuristic EightPuzzleHeuristic / SelectHeuristic SlidePuzzleHeuristic");
		System.out.println("4) SolveDomain");
		System.out.println("5) DisplaySolution 1 (the number of the problem you wish to solve)");
		System.out.println("6) exit");
		System.out.println();
		
		command = null;
		Scanner scanner = new Scanner(System.in);
		do
		{
			System.out.print("Enter command: ");
			command = scanner.nextLine().toLowerCase();
			
			if (!(command.equals("exit"))){
				this.setChanged();
				this.notifyObservers();
			}
			
		} while (!(command.equals("exit")));
		
		scanner.close();
	}

	@Override
	public void displaySolution(Solution solution) {
		// print the domain
		for (String s: solution.getDisplayStrings())
			System.out.println(s);
		
		// print the solution
		for (Action a : solution.getActions())
			System.out.println(a);
	}

	@Override
	public String getUserAction() {
		return this.command;
	}

	@Override
	public void doTask() {
		this.start();
	}

	@Override
	public void printErr(String str) {
		System.out.println(str);
	}

}
