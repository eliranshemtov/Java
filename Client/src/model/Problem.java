package model;

import java.io.Serializable;

/**
 * 
 * Problem Class defines the problem description (Serializable)
 *
 */
@SuppressWarnings("serial")
public class Problem implements Serializable {
	
	private String heuristic;
	private String domainName;
	private String domainArgs;
	private String algorithmName;
	private String entireDomainDescription;
	private boolean gui;
	
	/**
	 * 
	 * @return this.domainName (String)
	 */
	public String getDomainName() {
		return domainName;
	}
	
	/**
	 * sets this.domainName to the given String
	 * @param domainName (String)
	 */
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	
	/**
	 * gets this.domainArgs - describes the domain properties
	 * @return domainArgs (String)
	 */
	public String getDomainArgs() {
		return domainArgs;
	}
	
	/**
	 * sets this.domainArgs to the given String - describes the domain properties
	 * @param domainArgs (String)
	 */
	public void setDomainArgs(String domainArgs) {
		this.domainArgs = domainArgs;
	}
	
	/**
	 * gets this.algorithmName
	 * @return algorithmName (String)
	 */
	public String getAlgorithmName() {
		return algorithmName;
	}
	
	/**
	 * sets this.algorithmName to the given String - describes the Algorithm name
	 * @param algorithmName (String)
	 */
	public void setAlgorithmName(String algorithmName) {
		this.algorithmName = algorithmName;
	}
	
	/**
	 * gets this.heuristic
	 * @return heuristic (String)
	 */
	public String getHeuristic() {
		return heuristic;
	}
	
	/**
	 * Sets this.Heuristic to the given String - describe the heuristic method that will be used during solving the domain
	 * @param heuristic (String)
	 */
	public void setHeuristic(String heuristic) {
		this.heuristic = heuristic;
	}
	
	/**
	 * returns all of the problem properties catenated together to one String.
	 * @return entireDomainDescription(String)
	 */
	public String getEntireDomainDescription() {
		return entireDomainDescription;
	}
	
	/**
	 * sets this.entireDomainDescription to the given String. defines all the problem properties in one catenated String
	 * @param entireDomainDescription
	 */
	public void setEntireDomainDescription(String entireDomainDescription) {
		this.entireDomainDescription = entireDomainDescription;
	}
	
	/**
	 * tells if the current view is GUI (True if yes)
	 * @return gui (Boolean)
	 */
	public boolean isGui() {
		return gui;
	}
	
	/**
	 * sets the current view to GUI or CMD - True for GUI.
	 * @param gui
	 */
	public void setGui(boolean gui) {
		this.gui = gui;
	}
}
