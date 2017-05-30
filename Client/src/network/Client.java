package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import config.ClientProperties;
import config.HandleProperties;
import model.Problem;
import model.Solution;

/**
 * 
 * Client Class starts a connection with the server and ment to send a problem and get a solution (from the server)<br>
 * Has port(int) and serverAddress(ip String) data members 
 */
public class Client {
	
	private String serverAddress;
	private int port;
	
	/**
	 * Default C'tor<br>
	 * initialize the connection properties by using HandleProperties's readProperties function which will read a given xml file and return clientProperties object
	 */
	public Client() {
		ClientProperties properties = HandleProperties.readProperties();
		this.port = properties.getPort();
		this.serverAddress = properties.getIp();
	}
	
	/**
	 * runSolution function is not relevant to GUI<br>
	 * creates a connection with the server.
	 * gets a problem and sends it to the server which will solve it.
	 * @param problem {@link Problem}
	 * @return a String desctiption of the problem with all its data.
	 */
	public String runSolution(Problem problem) { // client wants to solve the domain
		Socket socket = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		
		try {
			socket = new Socket(serverAddress, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			out.writeObject(problem);
			return (String)in.readObject();
								
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} finally {
			try {
				out.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}	
		return null;
	}
	
	/**
	 * creates a connection with the server.
	 * gets a Problem contains a String description of the problem and sends it to the server which will send back it's solution.
	 * @param problem {@link Problem}
	 * @return {@link Solution}
	 */
	public Solution getSolution(Problem problem){ // client wants to get the solution
		Socket socket = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;

		try {
			socket = new Socket(serverAddress, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			out.writeObject(problem.getEntireDomainDescription());
			Solution solution = (Solution)in.readObject();
			
			return solution;
								
		} catch (IOException e) {
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * solveAndDisplay function gets a Problem, creates a connection with the server, send the problem to the server.
	 * <br> the server will send back the problem's solution.
	 * @param problem {@link Problem}
	 * @return {@link Solution}
	 */
	public Solution solveAndDisplay(Problem problem){
		Socket socket = null;
		ObjectOutputStream out = null;
		ObjectInputStream in = null;
		
		try {
			socket = new Socket(serverAddress, port);		
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			
			out.writeObject(problem);
			return (Solution)in.readObject();
								
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} finally {
			try {
				out.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}	
		return null;
	}
}
