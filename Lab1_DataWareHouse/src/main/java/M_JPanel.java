import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.AbstractTableModel;


public class M_JPanel extends JPanel {
	
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	private int Height;
	private int Weight;
		
	// Model for table
	class M_AbstractTableModel extends AbstractTableModel{
		
		public String[] colNames;
		public Object[][] data;			
		
		M_AbstractTableModel(String[] c, Object[][] d){
			colNames = c;		
			data = d;
		}	
		
		public void set_data(Object[][] d){			
			data = d;
		}
		
		public int getRowCount(){
			return data.length;
		}
		
		public int getColumnCount(){
			return colNames.length;
		}
		
		public String getColumnName(int col) { 
            return colNames[col]; 
        }
		
		public boolean isCellEditable(int row, int column) { 
            return true;    
        }
			
		// Data type, stored in column
		public Class getColumnClass(int column){
			switch(column){					
				default: return Object.class;
			}
		}
		
		// Data in cell
		public Object getValueAt(int row, int column){
			return data[row][column];	
		}
		
		public void setValueAt(Object value, int row, int col) {
	        data[row][col] = value;
	        fireTableCellUpdated(row, col);
	    }
	}
	
	public M_JPanel(String TableName, Vector<String> v){
		
		this.setLayout(new BorderLayout());
		
		Iterator<String> it = v.iterator();
		String colName[] = new String[v.size()-1];
		int i=0;
		String Query = "Select ";
		
		it.next();
		while(it.hasNext()){
			String n = it.next();
			colName[i++] = n;
			Query = Query + n + ",";	
		}		
		
		String Q = Query.substring(0, Query.length()-1);
		
		try{
			
			Object data[][] = DB_Connect.getData(Q + " From " + TableName 
					+ " ORDER BY " + v.get(1), v.size());
			
			M_AbstractTableModel JTablModel = new M_AbstractTableModel(colName, data);
			JTablModel.set_data(data);
			jTable1 = new JTable(JTablModel);
			
			jTable1.setFont(new java.awt.Font("Verdana",0,12));
			jTable1.getTableHeader().setReorderingAllowed(false);
			jTable1.setSelectionBackground(Color.LIGHT_GRAY);
		
			int v_ = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
			int h_ = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
			jScrollPane1 = new JScrollPane(jTable1,v_,h_);
			
			add(jScrollPane1);	
		
		}catch(Exception e){
			JOptionPane.showMessageDialog(this, "Error  \n" + e.toString(),
					"Ошибка", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
}
