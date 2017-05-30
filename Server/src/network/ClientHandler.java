package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.ModelManager;
import model.MyModel;
import model.Problem;
import model.Solution;
import model.SolutionManager;
import tasks.Task;

/**
 * ClientHandler Class connect with some client and ment to deal with his problem and get the solution for him<br>
 * Has Socket socket data member
 */
public class ClientHandler implements Task {
	
	private Socket socket;
	
	/**
	 * c'tor gets Socket socket and initialize the this.socket by the given one
	 * @param socket
	 */
	public ClientHandler(Socket socket){
		this.socket = socket;
	}

	@Override
	public void doTask() {
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		
		try {
			in = new ObjectInputStream(socket.getInputStream());
			out = new ObjectOutputStream(socket.getOutputStream());
			
			Object obj = in.readObject();
			if (obj instanceof Problem){ // client wants to solve the domain
				Problem problem = (Problem)obj;
				MyModel model = new MyModel();
				model.selectDomain(problem.getDomainName(), problem.getDomainArgs());
				model.selectAlgorithm(problem.getAlgorithmName());
				model.selectHeuristic(problem.getHeuristic());

				ModelManager modelManager = ModelManager.getInstance();
				model.concatenateStringWithAlgorithm();
				modelManager.addModel(model);
				
				if (!problem.isGui())
					out.writeObject(model.getConcatenatedStringWithAlgorithm());
				
				model.solveDomain();
				
				if (problem.isGui()){
					Solution solution = SolutionManager.getInstance().getSolution(model.getConcatenatedStringWithAlgorithm());
					out.writeObject(solution);
				}
					
				out.close();
				out = null;

			} else if (obj instanceof String){ // client wants to view the solution
				
				String description = (String)obj; 
				Solution solution = SolutionManager.getInstance().getSolution(description);
				out.writeObject(solution);
			}
			
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null)
					out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}	
	}
	
	/**
	 * stops the activity of all the running models
	 */
	public void stopModelActivity(){
		ModelManager.getInstance().stopAllModelsActivity();
	}
}
