package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

@SuppressWarnings("serial")

/**
 * 
 * ProblemManager class is a singleTone Class which will map the solution descriptions (Strings) to the solution{@link Solution}<br><br>
 *
 */
public class SolutionManager implements Serializable {

	private static SolutionManager instance = null;
	private HashMap<String, Solution> solutionsMap;
	private static final String FILE_NAME = "resources/solution.dat";
	private boolean thereIsSomethingToSave;
	
	/**
	 * default c'tor<br>
	 * initialize the solutionsMap hashMap
	 */
	protected SolutionManager() {
		this.loadSoluitonFromFile();
		if (solutionsMap == null)
			solutionsMap = new HashMap<String, Solution>();
		this.thereIsSomethingToSave = false;
	}
	
	/**
	 * @return the only (SingleTone) instance of the class
	 */
	public static SolutionManager getInstance() {
		if (instance == null)
			instance = new SolutionManager();
		return instance;
	}

	/**
	 * gets a solution and add it to solutionsMap hashMap
	 * @param solution {@link Solution}
	 */
	public void addSolution(Solution solution){
		this.solutionsMap.put(solution.getConcatenatedString(), solution);
		this.thereIsSomethingToSave = true;
	}
	
	/**
	 * gets a String key and returns the right solution from the hashMap 
	 * @param displayStrings (String)
	 * @return {@link Solution}
	 */
	public Solution getSolution(String displayStrings){
		return this.solutionsMap.get(displayStrings);
	}
	
	@SuppressWarnings("unchecked")
	/**
	 * read the data from a file stored in resources (the path of the file is in the FILE_NAME data member)<br>
	 * the method write the data in to the hashMap
	 */
	private void loadSoluitonFromFile(){
		FileInputStream in = null;
		ObjectInputStream ois = null;
		
		try {
			in = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(in);
			this.solutionsMap = (HashMap<String, Solution>)ois.readObject();    
			
		} catch (FileNotFoundException e) {
				return;
		} catch (IOException e) {
			// TODO Auto-generated catch block
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		} finally{
			try {
				if (in != null){
					in.close();
					ois.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/**
	 * read the data from the hashMap and write it in to a file (the path of the file is in the FILE_NAME data member)<br>
	 */
	public void saveSolutionInFile(){
		FileOutputStream out = null;
		ObjectOutputStream oos = null;
		if (this.solutionsMap.isEmpty() || !this.thereIsSomethingToSave)
			return;
		
		try {
			out = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(out);
			oos.writeObject(solutionsMap);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (out != null){
				try {
					oos.close();
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
	}
}
