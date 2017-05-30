package config;

import java.io.Serializable;

/**
 * @clientProperties 
 * This class sets the client's network properties
 * 
 */
@SuppressWarnings("serial") 
public class ClientProperties implements Serializable{

	private int port;
	private String ip;

	/**
	 * Default C'tor<br>
	 * 
	 */
	public ClientProperties() {

		}
	/**
	 * sets the client's port number.<br>
	 * sets the client's IP number
	 * ClientProperties C'tor
	 * @param port - int
	 * @param ip - String
	 */
	public ClientProperties(int port, String ip) {
		this.port = port;
		this.ip = ip;
	}

	/**
	 * 
	 * @return int - this.port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * sets this.port to the given int
	 * @param port - int 
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * 
	 * @return String - ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * sets this.ip to the given string
	 * @param ip - String
	 * 
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

}
