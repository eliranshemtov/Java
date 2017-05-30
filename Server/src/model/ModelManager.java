package model;

import java.util.ArrayList;

/**
 * 
 * ModelManager class is a singleTone Class which will manage all the models that running
 *
 */
public class ModelManager {

	private static ModelManager instance = null;
	private ArrayList<Model> modelsList;
	
	/**
	 * default c'tor<br>
	 * initialize the models arrayList
	 */
	protected ModelManager() {
		modelsList = new ArrayList<Model>();
	}

	/**
	 * @return the only (SingleTone) instance of the class
	 */
	public static ModelManager getInstance() {
		if (instance == null)
			instance = new ModelManager();
		return instance;
	}
	
	/**
	 * gets a Model and add it to model arrayList
	 * @param Model {@link Model}
	 */
	public void addModel(Model model){
		modelsList.add(model);
	}
	
	/**
	 * the method runs over all of the existing models in the array list and stops their algorithm activity 
	 */
	public void stopAllModelsActivity(){
		for (int i=0; i < modelsList.size(); i++) {
		    modelsList.get(i).getAlgorithm().stop();
		}
	}
}
