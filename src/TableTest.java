import java.awt.Component;
import java.awt.Event;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.RowLayout;



public class TableTest{
	

//	protected Shell shell;
//	//private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
//	private Table table_1;
//	protected Component frame;
	
	private static final ArrayList<String> COLUMN_TITLES = 
			new ArrayList<String>(Arrays.asList("Appliance", "Part #",
					"Watt Rating", "Current Price", "Total Watts"));
	
	private static ArrayList<Appliance> AppInfo = new ArrayList();
	
	
	public static void main (String [] args) {
		Display display = new Display ();
		Shell shell = new Shell (display);
		shell.setLayout (new RowLayout (SWT.VERTICAL));
		final Table table = new Table (shell, SWT.BORDER | SWT.MULTI);
		table.setHeaderVisible (true);
		for (int i = 0; i < COLUMN_TITLES.size() - 1; i++) {
			TableColumn column = new TableColumn (table, SWT.NONE);
			column.setText (COLUMN_TITLES.get(i));
		}
		final TableColumn [] columns = table.getColumns ();
		for (int i = 0; i < 12; i++) {
			TableItem item = new TableItem (table, SWT.NONE);
			for (int j=0; j<columns.length; j++) {
				item.setText (j, "Item " + i);
			}
		}
		for (int i=0; i<columns.length; i++) columns [i].pack ();

		shell.pack ();
		shell.open ();
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		display.dispose ();
	}
	
	public static ArrayList<Appliance> fillAppList () {
		return AppInfo;
		
	}

//	/**
//	 * Launch the application.
//	 * 
//	 * @param args
//	 */
//	public static void main(String[] args) {
//		
//		try {
//			UI window = new UI();
//			window.open();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	/**
//	 * Open the window.
//	 */
//	public void open() {
//		Display display = Display.getDefault();
//		createContents();
//		shell.open();
//		shell.layout();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//	}
//
//	/**
//	 * Create contents of the window.
//	 * @wbp.parser.entryPoint
//	 */
//	protected void createContents() {
//		
//		
//		shell = new Shell();
//		shell.setImage(SWTResourceManager.getImage(UI.class, "/com/test/icon/smartImage.PNG"));
//		shell.setSize(1737, 1058);
//		shell.setText("Smart Grid for Schools");
//		shell.setLayout(new org.eclipse.swt.layout.FormLayout());
//
//		
//
//		
//	
//			
//
//
//		
//		
//		
//		
//		
//		
//		
//		table_1 = new Table(shell, SWT.VIRTUAL | SWT.FULL_SELECTION);
//		FormData fd_table_1 = new FormData();
//		fd_table_1.top = new FormAttachment(0, 52);
//		fd_table_1.right = new FormAttachment(100, -86);
//		fd_table_1.bottom = new FormAttachment(100, -369);
//		fd_table_1.left = new FormAttachment(0, 391);
//		table_1.setLayoutData(fd_table_1);
//		table_1.setHeaderVisible(true);
//		table_1.setLinesVisible(true);
//		
//		
////		table_1.setItemCount (1000000);
////		table_1.addListener (SWT.SetData, new Listener () {
////		      public void handleEvent (Event event) {
////		          TableItem item = (TableItem) event.item;
////		          int index = table_1.indexOf (item);
////		          item.setText ("Item " + index);
////		          System.out.println (item.getText ());
////		      }
////
////			@Override
////			public void handleEvent(org.eclipse.swt.widgets.Event arg0) {
////				// TODO Auto-generated method stub
////				
////			}
////		  }); 
//		
//		TableColumn tblclmnAppliance = new TableColumn(table_1, SWT.NONE);
//		tblclmnAppliance.setWidth(152);
//		tblclmnAppliance.setText("Appliance");
//		
//		TableColumn tblclmnPartNumber = new TableColumn(table_1, SWT.NONE);
//		tblclmnPartNumber.setWidth(70);
//		tblclmnPartNumber.setText("Part #");
//		
//		TableColumn tblclmnWattRating = new TableColumn(table_1, SWT.NONE);
//		tblclmnWattRating.setWidth(107);
//		tblclmnWattRating.setText("Watt Rating");
//		
//		TableColumn tblclmnCurrentPrice = new TableColumn(table_1, SWT.NONE);
//		tblclmnCurrentPrice.setWidth(118);
//		tblclmnCurrentPrice.setText("Current Price");
//		
//		TableColumn tblclmnTotalWatts = new TableColumn(table_1, SWT.NONE);
//		tblclmnTotalWatts.setWidth(167);
//		tblclmnTotalWatts.setText("Total Watts");
//		
//		TableItem tableItem_7 = new TableItem(table_1, SWT.NONE);
//		tableItem_7.setText("One");
//		
//		TableItem tableItem = new TableItem(table_1, SWT.NONE);
//		tableItem.setText("Two");
//		
//		TableItem tableItem_6 = new TableItem(table_1, 0);
//		tableItem_6.setText("Three");
//		
//		TableItem tableItem_1 = new TableItem(table_1, 0);
//		tableItem_1.setText("Four");
//		
//		TableItem tableItem_2 = new TableItem(table_1, 0);
//		tableItem_2.setText("Five");
//		
//		TableItem tableItem_3 = new TableItem(table_1, 0);
//		tableItem_3.setText("Six");
//		
//		TableItem tableItem_4 = new TableItem(table_1, 0);
//		tableItem_4.setText("Seven");
//		
//		TableItem tableItem_5 = new TableItem(table_1, 0);
//		tableItem_5.setText("Eight");
//	
//		TableItem tableItem_8 = new TableItem(table_1, 1);
//		tableItem_8.setText("Nine");	
//	}
}
