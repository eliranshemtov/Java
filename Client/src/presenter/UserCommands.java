package presenter;

import java.util.HashMap;

import model.Model;
import model.MyModel;

/**
 * 
 * UserCommands Class is a command factory that uses factory pattern in order to activate of the right command that the user asked for.
 * <br> contains a hashMap that maps between a String that the user sent to a command (interface that defines the function doCommand()).
 *
 */
public class UserCommands {

	private HashMap<String, Command> commands = new HashMap<String, Command>();
	
	/**
	 * Default C'tor - initialize the contained hashMap (commands = HashMap<String,Command>)
	 */
	public UserCommands() {
		commands.put("SelectDomain".toLowerCase(), new SelectDomainCommand());
		commands.put("SelectAlgorithm".toLowerCase(), new SelectAlgorithmCommand());
		commands.put("SelectHeuristic".toLowerCase(), new SelectHeuristicCommand());
		commands.put("SolveDomain".toLowerCase(), new SolveDomain());
		commands.put("SolveDomainAndDisplay".toLowerCase(), new SolveDomainAndDisplay());
		commands.put("DisplaySolution".toLowerCase(), new DisplaySolutionCommand());
	}
	
	/**
	 * gets a String that describe the command that the user wants to commit and gets the suitable command from the commands hashMap
	 * @param commandName (String)
	 * @return Command
 	 */
	public Command selectCommand(String commandName){
		return commands.get(commandName);
	}
	
	/**
	 * Command interface - enables the doCommand function
	 * <br> each instance of a class that will override doCommand function will enable to use "someclass".doCommand()
	 * 
	 */
	public interface Command
	{	
		/**
		 * interface function
		 * @param model (Model)
		 * @param arguments (String)
		 * @return Model
		 */
		Model doCommand(Model model, String arguments);
	}
	
	/**
	 * 
	 * part of the UserCommands factory, implements Command and overrides doCommand
	 *
	 */
	private class SelectDomainCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String arguments) {
			Model m = new MyModel();
			String[] str = arguments.split(":");
			m.selectDomain(str[0],str[1]);
			return m;
		}
	}
	
	/**
	 * 
	 * part of the UserCommands factory, implements Command and overrides doCommand
	 *
	 */
	private class SelectAlgorithmCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String arguments) {
			model.selectAlgorithm(arguments);
			return model;
		}
	}
	
	
	/**
	 * 
	 * part of the UserCommands factory, implements Command and overrides doCommand
	 *
	 */
	private class SelectHeuristicCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String arguments) {
			model.selectHeuristic(arguments);
			return model;
		}
	}
	
	/**
	 * 
	 * part of the UserCommands factory, implements Command and overrides doCommand
	 *
	 */
	private class SolveDomain implements Command
	{
		@Override
		public Model doCommand(Model model, String arguments) {
			model.solveDomain();
			return model;
		}
	}
	
	/**
	 * 
	 * part of the UserCommands factory, implements Command and overrides doCommand
	 *
	 */
	private class SolveDomainAndDisplay implements Command
	{
		@Override
		public Model doCommand(Model model, String arguments) {
			model.solveDomainAndDisplay();
			return model;
		}
	}
	
	/**
	 * 
	 * part of the UserCommands factory, implements Command and overrides doCommand
	 *
	 */
	private class DisplaySolutionCommand implements Command
	{
		@Override
		public Model doCommand(Model model, String arguments) {
			int i = Integer.parseInt(arguments);
			model.getSolution(i);
			return model;
		}
		
	}
}
