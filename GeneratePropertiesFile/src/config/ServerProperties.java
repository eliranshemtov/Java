package config;

import java.io.Serializable;

/**
 * 
 * ServerProperties class creates the server's network properties<br>
 * private int - port<br>
 * private int - maximumClient number
 *
 */
@SuppressWarnings("serial")
public class ServerProperties implements Serializable{

	private int port;
	private int maximumClients;
	
	/**
	 * Default C'tor
	 */
	public ServerProperties() {
	}
	
	/**
	 * C'tor - Gets port(int) and maximumClient(int) that defines the class data members
	 * @param port
	 * @param maximumClients
	 */
	public ServerProperties(int port, int maximumClients) {
		this.port = port;
		this.maximumClients = maximumClients;
	}

	/**
	 * 
	 * @return port (int)
	 */
	public int getPort() {
		return port;
	}
	
	/**
	 * Gets port and sets this.port to the given int
	 * @param port (int)
	 */
	public void setPort(int port) {
		this.port = port;
	}
	
	/**
	 * 
	 * @return this.maximumClient (int) - the number of maximum clients that can connect to server at a time
	 */
	public int getMaximumClients() {
		return maximumClients;
	}
	
	/**
	 * Sets this.maximumClient to the given int
	 * @param maximumClients
	 */
	public void setMaximumClients(int maximumClients) {
		this.maximumClients = maximumClients;
	}	
}
