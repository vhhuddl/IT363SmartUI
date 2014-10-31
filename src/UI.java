import java.awt.Component;

import javax.swing.JOptionPane;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Combo;



public class UI {

	protected Shell shell;
	//private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
	private Table table_1;
	private Simulation simulation;
	private StartSimulation start;
	private ResumeSimulation resume;
	private ResetSimulation reset;
	private PauseSimulation pause;
	private TODController todController;
	private LengthController lengthController;
	private int hour = 0;
	private int min = 0;
	private int time = 0;
	private String ampm;
	protected Component frame;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			UI window = new UI();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		
		simulation = new Simulation();
		lengthController = new LengthController();
		todController = new TODController();
		start = new StartSimulation();
		resume = new ResumeSimulation();
		reset = new ResetSimulation();
		pause = new PauseSimulation();
		
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(UI.class, "/com/test/icon/smartImage.PNG"));
		shell.setSize(1737, 1058);
		shell.setText("Smart Grid for Schools");
		shell.setLayout(new org.eclipse.swt.layout.FormLayout());

		Label lblSimulationSettings = new Label(shell, SWT.NONE);
		lblSimulationSettings.setAlignment(SWT.CENTER);
		lblSimulationSettings.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		org.eclipse.swt.layout.FormData fd_lblSimulationSettings = new org.eclipse.swt.layout.FormData();
		lblSimulationSettings.setLayoutData(fd_lblSimulationSettings);
		lblSimulationSettings.setText("Set Simulation Time of Day");
		
		//CHANGES OCTOBER 21ST
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		org.eclipse.swt.layout.FormData fd_btnNewButton_1 = new org.eclipse.swt.layout.FormData();
		fd_btnNewButton_1.bottom = new FormAttachment(100, -9);
		fd_btnNewButton_1.left = new FormAttachment(0, 17);
		btnNewButton_1.setLayoutData(fd_btnNewButton_1);
		btnNewButton_1.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] choices = { "30 Minutes", "1 Hour", "2 Hours", "4 Hours"};
			    String input = (String) JOptionPane.showInputDialog(null, "Choose now...",
			        "Set simulation length", JOptionPane.QUESTION_MESSAGE, null,choices,choices[0]);
			    int length = 0;
			    String simLength;
			    // Initial choice
			    if (input == "30 Minutes"){
				    length = 15;
			    } else {
			    	simLength = input.substring(0, 1);
			    	length = Integer.parseInt(simLength);
			    	length = (length * 60 / 2);
			    }
			    System.out.println(length);
			    lengthController.setLength(simulation, length);
			  }
			}
		);
		btnNewButton_1.setText("Set Simulation Length");
		
				//CHANGES OCTOBER 21ST
				Button btnNewButton_2 = new Button(shell, SWT.NONE);
				fd_btnNewButton_1.right = new FormAttachment(btnNewButton_2, -10);
				org.eclipse.swt.layout.FormData fd_btnNewButton_2 = new org.eclipse.swt.layout.FormData();
				fd_btnNewButton_2.bottom = new FormAttachment(100, -10);
				fd_btnNewButton_2.left = new FormAttachment(0, 284);
				btnNewButton_2.setLayoutData(fd_btnNewButton_2);
				btnNewButton_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
				btnNewButton_2.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						pause.pauseSimulation(simulation);
						Object[] options = {"Yes",
	                    "No"};
						int n = JOptionPane.showOptionDialog(null,"Are you sure you want to reset?", 
								"Reset",
								JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE,
								null,     //do not use a custom Icon
								options,  //the titles of buttons
								options[0]); //default button title
						if(n == 0){
						reset.resetSimulation(simulation);
						}else{
						start.startSimulation(simulation);
						}
					}
				});
				btnNewButton_2.setText("Reset");
			

		Button btnStartSimulation = new Button(shell, SWT.NONE);
		fd_btnNewButton_2.right = new FormAttachment(btnStartSimulation, -6);
		org.eclipse.swt.layout.FormData fd_btnStartSimulation = new org.eclipse.swt.layout.FormData();
		fd_btnStartSimulation.top = new FormAttachment(btnNewButton_2, 0, SWT.TOP);
		fd_btnStartSimulation.bottom = new FormAttachment(btnNewButton_2, 0, SWT.BOTTOM);
		fd_btnStartSimulation.left = new FormAttachment(0, 547);
		btnStartSimulation.setLayoutData(fd_btnStartSimulation);
		btnStartSimulation.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		btnStartSimulation.setText("Start Simulation");
		btnStartSimulation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				start.startSimulation(simulation);
			}
		});

		Button btnPauseSimulation = new Button(shell, SWT.NONE);
		fd_btnStartSimulation.right = new FormAttachment(100, -911);
		org.eclipse.swt.layout.FormData fd_btnPauseSimulation = new org.eclipse.swt.layout.FormData();
		fd_btnPauseSimulation.top = new FormAttachment(btnNewButton_2, 0, SWT.TOP);
		fd_btnPauseSimulation.bottom = new FormAttachment(btnNewButton_2, 0, SWT.BOTTOM);
		fd_btnPauseSimulation.left = new FormAttachment(btnStartSimulation, 6);
		fd_btnPauseSimulation.right = new FormAttachment(100, -648);
		btnPauseSimulation.setLayoutData(fd_btnPauseSimulation);
		btnPauseSimulation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				pause.pauseSimulation(simulation);
			}
		});
		btnPauseSimulation.setFont(SWTResourceManager.getFont("Segoe UI", 9,
				SWT.BOLD));
		btnPauseSimulation.setText("Pause Simulation");
		
		table_1 = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		FormData fd_table_1 = new FormData();
		fd_table_1.bottom = new FormAttachment(100, -373);
		fd_table_1.top = new FormAttachment(0, 48);
		fd_table_1.left = new FormAttachment(0, 1085);
		fd_table_1.right = new FormAttachment(100, -6);
		table_1.setLayoutData(fd_table_1);
		table_1.setHeaderVisible(true);
		table_1.setLinesVisible(true);
		
		TableItem item;
		
		TableColumn tblclmnAppliance = new TableColumn(table_1, SWT.NONE);
		tblclmnAppliance.setWidth(152);
		tblclmnAppliance.setText("Appliance");
		
		TableColumn tblclmnPartNumber = new TableColumn(table_1, SWT.NONE);
		tblclmnPartNumber.setWidth(70);
		tblclmnPartNumber.setText("Part #");
		
		TableColumn tblclmnWattRating = new TableColumn(table_1, SWT.NONE);
		tblclmnWattRating.setWidth(107);
		tblclmnWattRating.setText("Watt Rating");
		
		TableColumn tblclmnCurrentPrice = new TableColumn(table_1, SWT.NONE);
		tblclmnCurrentPrice.setWidth(118);
		tblclmnCurrentPrice.setText("Current Price");
		
		TableColumn tblclmnTotalWatts = new TableColumn(table_1, SWT.NONE);
		tblclmnTotalWatts.setWidth(167);
		tblclmnTotalWatts.setText("Total Watts");
		
		final Combo combo = new Combo(shell, SWT.NONE);
		fd_btnNewButton_1.top = new FormAttachment(0, 837);
		fd_lblSimulationSettings.left = new FormAttachment(combo, 0, SWT.LEFT);
		fd_lblSimulationSettings.bottom = new FormAttachment(combo, -6);
		combo.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		combo.setItems(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"});
		FormData fd_combo = new FormData();
		fd_combo.left = new FormAttachment(0, 21);
		fd_combo.top = new FormAttachment(0, 751);
		combo.setLayoutData(fd_combo);
		combo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = combo.getSelectionIndex();
				hour = Integer.parseInt(combo.getItem(index));
				hour = hour * 100;
			}
		}
		);
		
		final Combo combo_1 = new Combo(shell, SWT.NONE);
		fd_btnNewButton_2.top = new FormAttachment(combo_1, 17);
		combo_1.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		combo_1.setItems(new String[] {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59"});
		FormData fd_combo_1 = new FormData();
		fd_combo_1.top = new FormAttachment(0, 751);
		combo.setText("Hour");
		combo_1.setLayoutData(fd_combo_1);
		combo_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = combo_1.getSelectionIndex();
				min = Integer.parseInt(combo_1.getItem(index));
				time = hour + min;
			}
		});
		combo_1.setText("Minutes");
		
		final Combo combo_2 = new Combo(shell, SWT.NONE);
		fd_lblSimulationSettings.right = new FormAttachment(combo_2, 0, SWT.RIGHT);
		combo_2.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		combo_2.setItems(new String[] {"AM", "PM"});
		FormData fd_combo_2 = new FormData();
		fd_combo_2.top = new FormAttachment(0, 751);
		fd_combo_2.left = new FormAttachment(0, 414);
		combo_2.setLayoutData(fd_combo_2);
		combo_2.setText("AM/PM");
		combo_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int index = combo_2.getSelectionIndex();
				ampm = combo_2.getItem(index);
			}
		});
		
		Label label = new Label(shell, SWT.NONE);
		fd_combo_1.left = new FormAttachment(0, 196);
		label.setFont(SWTResourceManager.getFont("Segoe UI", 22, SWT.NORMAL));
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(combo, 3, SWT.TOP);
		fd_label.right = new FormAttachment(combo_1, -6);
		label.setLayoutData(fd_label);
		label.setText(":");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		FormData fd_btnNewButton = new FormData();
		fd_btnNewButton.top = new FormAttachment(lblSimulationSettings, -6, SWT.TOP);
		fd_btnNewButton.bottom = new FormAttachment(btnStartSimulation, -16);
		fd_btnNewButton.right = new FormAttachment(table_1, -217);
		fd_btnNewButton.left = new FormAttachment(lblSimulationSettings, 14);
		btnNewButton.setLayoutData(fd_btnNewButton);
		btnNewButton.setText("Set Simulation Time");
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				todController.setTimeOfDay(simulation, time, ampm);
			}
		});
		
	}
}
