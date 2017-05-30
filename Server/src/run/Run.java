package run;

import java.util.Scanner;

import model.SolutionManager;
import network.MyTCPIPServer;
import serverGui.ServerWindow;

public class Run {
	
	protected MyTCPIPServer server = null;
	private ServerWindow serverWin;
	
	/**
	 * getter for the server (MyTCPIPServer)
	 * @return MyTCPIPServer
	 */
	public MyTCPIPServer getServer() {
		return server;
	}

	public static void main(String[] args) {
		Run run = new Run();
		run.serverWin = new ServerWindow(600,400,"Server Off",run.getServer());
		run.serverWin.run();
		if (run.serverWin.getClosed())
			return;
		
		String command = null;
		Scanner scanner = new Scanner(System.in);
		do
		{
			System.out.print("Enter command: ");
			command = scanner.nextLine().toLowerCase();		
			run.handleCommand(command);
			
		} while (!(command.equals("exit")));
		
		scanner.close();
	}

	/**
	 * gets the String user command and handle it. <br>
	 * case of "start" - the server will display a massage saying the server is already running.<br>
	 * case of "exit" - the server 
	 * @param command (String)
	 */
	private void handleCommand(String command) {
		
		String str[] = command.split(" ");
		
		if (str[0].equals("start")) {
	
			System.out.println("Start Command is not avalable any more through CMD");
			System.out.println("Server is already running. Enter Exit to kill it");
					
		} else if (str[0].equals("exit")) {
			server = serverWin.getServer(); 
			if (server != null){
				server.stopServer();
				
				System.out.println("Hadad & Shem Tov server is shuting down");
				SolutionManager.getInstance().saveSolutionInFile();
			}
		} else 
			System.out.println("Invalid command. Try again");
		
	}
}
