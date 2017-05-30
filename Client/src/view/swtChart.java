package view;

import java.util.ArrayList;
import java.util.Observable;

import model.Solution;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.swtchart.Chart;
import org.swtchart.IAxisSet;
import org.swtchart.ISeries;
import org.swtchart.ISeries.SeriesType;
import org.swtchart.ISeriesSet;

public class swtChart extends Observable implements Runnable, View{

	protected Display display;
	protected Shell shell;
	private ArrayList<Integer> numof;
	private Chart chart;
	
	public swtChart(Display display, int width, int height, String title, ArrayList<Integer> numof) {
		this.display = display;
		shell = new Shell(display);
		shell.setSize(width, height);
		shell.setText(title);
		this.numof = numof;
		
		
	}
	@Override
	public void run() {
		initWidgets();
		
	}
	
	public void initWidgets(){
		this.shell.open();
		this.shell.setLayout(new GridLayout(1, true));
		chart = new Chart(shell, SWT.NONE);
		chart.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		double[] ySeries = new double[numof.size()];
		for (int i=0; i<numof.size(); i++){
			ySeries[i] = numof.get(i);
		}
		ISeriesSet seriesSet = chart.getSeriesSet();
		ISeries series = seriesSet.createSeries(SeriesType.LINE, "line series");
		series.setYSeries(ySeries);
		IAxisSet axisSet = chart.getAxisSet();
		axisSet.adjustRange();
	}
	
	public void update(){
		
	}
	
	
	@Override
	public void doTask() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void displaySolution(Solution solution) {
		double[] ySeries = new double[numof.size()];
		for (int i=0; i<numof.size(); i++){
			ySeries[i] = numof.get(i);
		}
		ISeriesSet seriesSet = chart.getSeriesSet();
		ISeries series = seriesSet.createSeries(SeriesType.LINE, "line series");
		series.setYSeries(ySeries);
		IAxisSet axisSet = chart.getAxisSet();
		axisSet.adjustRange();
		chart.update();
		chart.redraw();
		
	}
	@Override
	public String getUserAction() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void printErr(String str) {
		// TODO Auto-generated method stub
		
	}
}
