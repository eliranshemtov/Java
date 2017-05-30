package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import tasks.TaskRunnable;
import config.HandleProperties;
import config.ServerProperties;

/**
 * handle the sever properties
 */
public class MyTCPIPServer implements Runnable{
	private ServerSocket server;
	private ExecutorService executor;
	private int maximumClients;
	private int port;
	private boolean stop;
	private ClientHandler handler;

	/**
	 * default c'tor<br>
	 * initialize the port and the maximum clients by 
	 */
	public MyTCPIPServer(){
		ServerProperties properties = HandleProperties.readProperties();
		this.port = properties.getPort();
		this.maximumClients = properties.getMaximumClients();
	}

	/**
	 * c'tor gets int port and int maximum clients and initialize it's data member with the given parameter.
	 * @param port (int)
	 * @param maximumClients (int)
	 */
	public MyTCPIPServer(int port, int maximumClients) {
		this.port = port;
		this.maximumClients = maximumClients;
	}

	/**
	 * start the connection with the client<br>
	 * each client will run in a different thread <br>
	 */
	public void startServer() {
		this.stop = false;
		
		try{
			this.server = new ServerSocket(this.port);
			this.server.setSoTimeout(400000);
			executor = Executors.newFixedThreadPool(this.maximumClients);
			while(!stop) {
				try {
					Socket socket = server.accept();
			
					if (socket != null) {
						handler = new ClientHandler(socket);
						executor.submit(new TaskRunnable(handler));
					}
						
				} catch (SocketTimeoutException e) {
					System.out.println();
					System.out.println("timed out!");
					this.server.close();
				} catch (SocketException e){
					break;
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
			
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	/**
	 * stop the server activity and close the connection
	 */
	public void stopServer() {
		this.stop = true;
		try {
			this.server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (handler != null)
			this.handler.stopModelActivity();
		this.executor.shutdown();
	}

	@Override
	public void run() {
		this.startServer();
	}

}
