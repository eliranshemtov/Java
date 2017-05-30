package presenter;

//import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import model.MyModel;
import model.Solution;
import presenter.UserCommands.Command;
import view.BasicWindow;
import view.ChooseDifficultyWindow;
import view.GameView;
import view.View;
import view.WelcomeWindow;

/**
 * 
 * Presenter Class implements Observer interface<br>
 * defines the view and model which will extend observable
 *
 */
public class Presenter implements Observer {

	private View ui;
	private Model model;
	private UserCommands commands;
//	private ArrayList<Model> models;
	
	/**
	 * Presenter C'tor - Gets a view and model and sets the relevant data members according to them.
	 * @param ui
	 * @param model
	 */
	public Presenter(View ui, Model model) {
		this.ui = ui;
		this.model = model;
		this.commands = new UserCommands();
	//	this.models = new ArrayList<Model>();
	//	this.models.add(model);
	}
	
	/**
	 * Presenter C'tor that gets only a model and sets his model according to the given one
	 * @param model {@link Model}
	 */
	public Presenter(Model model){
		this.model = model;
		this.commands = new UserCommands();
	//	this.models = new ArrayList<Model>();
	}
	
	/**
	 * Setter for the view
	 * @param view {@link View}
	 */
	public void setView(View view){
		this.ui = view;
	}
	
	@Override
	public void update(Observable observable, Object arg1) {
		if (observable instanceof Model){
			if (arg1 instanceof Solution)
				this.ui.displaySolution((Solution)arg1);
			else if (arg1 instanceof String)
				this.ui.printErr((String)arg1);
		}
		else if (observable instanceof View){
			
			String userCommand = ui.getUserAction();
			String[] strArr = userCommand.split(" ");
			
			String commandName = strArr[0];
			String arguments = null;
			if (strArr.length > 1)
				arguments = strArr[1];
			
			Command command = commands.selectCommand(commandName); 
			if (command != null){
				Model m = command.doCommand(model, arguments);
				if (m != model){
					this.model = m;
				//	this.models.add(m);
					m.addObserver(this);
				}
			} 
			else this.ui.printErr("invalid input");
			
		} else if (observable instanceof WelcomeWindow){
			if (arg1 instanceof GameView){
				this.setView((GameView)arg1);
				((GameView)arg1).addObserver(this);
			}
			if (arg1 instanceof ChooseDifficultyWindow)
				((ChooseDifficultyWindow)arg1).addObserver(this);
				
		} else if (observable instanceof ChooseDifficultyWindow){
			if (arg1 instanceof GameView){
				this.setView((GameView)arg1);
				((GameView)arg1).addObserver(this);
			}
		}
	}

	public static void main(String[] args) {
		
//		GUI
		MyModel model = new MyModel();
		Presenter presenter = new Presenter(model);
	 	model.addObserver(presenter);
	 	
		BasicWindow ui = new WelcomeWindow(1024, 400, "Game Console");
		ui.addObserver(presenter);
		ui.run();
		
		
//		Console view
//		MyConsoleView ui = new MyConsoleView();
//		MyModel model = new MyModel();
//		 
//		Presenter presenter = new Presenter(ui, model);
//		model.addObserver(presenter);
//		ui.addObserver(presenter);
//		 
//		Thread t = new Thread(new TaskRunnable(ui));
//		t.start();
	}
	
}
