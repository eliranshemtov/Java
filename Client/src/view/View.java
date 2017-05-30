package view;

import tasks.Task;
import model.Solution;

/**
 * View Interface - extends Task<br>
 * defines the following functions:<br>
 * start() - enable to use non runnable classes as if they were<br>
 * displaySolution - Gets a solution and shows it <br>
 * getUserAction - returns the action that was input by the user <br>
 * printErr - gets a String and prints it <br>
 */
public interface View extends Task{
	void start();
	void displaySolution(Solution solution);
	String getUserAction();
	void printErr(String str);
}
