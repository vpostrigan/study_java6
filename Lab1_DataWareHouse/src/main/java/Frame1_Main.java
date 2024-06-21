import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * Главное окно программы. Соджержит: путь к БД; Кнопки создания агрегатов, 
 * факт таблиц; таблицу с данными базы
 */
class Frame1_Main extends JFrame implements ActionListener {
	
	private JLabel JLabel1;
	private JTextField JTextField1;
	private JButton JButton1;
	private JButton JButton2;
	private JButton JButton3;
	private JButton JButton4;
	private JLabel JLabel2;
	public JTabbedPane JTabbedPane1;
	
	private JLabel JLabel3;
	private JLabel JLabel4;	
	
	private JLabel JLabel5;
	private JLabel JLabel6;
	
	private final String XML_DB_PATH = "\\DB.xml";
	
	Frame1_Main(String Frame_name){
		super(Frame_name);
		init();
	}
	
	/**
	 * инициализация окна Frame1_Main
	 */
	public void init(){
		
		setExtendedState(Frame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null);
		getContentPane().setFont(new Font("Verdana", 0, 12));		
		
		{
			JLabel1 = new JLabel("Путь к БД:");
			JLabel1.setBounds(10, 14, 70, 14);			
			getContentPane().add(JLabel1);
		}
		{
			JTextField1 = new JTextField(Lab1_Main.path);
			JTextField1.setBounds(80,12,450,21);
			JTextField1.setFont(new Font("Verdana", Font.BOLD, 12));
			getContentPane().add(JTextField1);
		}				
		{
			JLabel4 = new JLabel("+\\DB.xml");
			JLabel4.setBounds(535, 27, 100, 14);		
			JLabel4.setFont(new Font("Verdana", Font.BOLD, 12));
			JLabel4.setForeground(Color.GRAY);
			getContentPane().add(JLabel4);
		}
		{
			JLabel3 = new JLabel("+\\file.mdb");
			JLabel3.setBounds(535, 5, 100, 14);
			JLabel3.setFont(new Font("Verdana", Font.BOLD, 12));
			JLabel3.setForeground(Color.GRAY);
			getContentPane().add(JLabel3);
		}
		{
			JLabel5 = new JLabel("Использовано: ");
			JLabel5.setBounds(625, 33, 350, 14);
			JLabel5.setFont(new Font("Verdana", Font.BOLD, 12));
			JLabel5.setForeground(Color.GRAY);
			JLabel5.setVisible(false);
			getContentPane().add(JLabel5);
		}
		{
			JLabel6 = new JLabel("Время: ");
			JLabel6.setBounds(732, 47, 350, 14);
			JLabel6.setFont(new Font("Verdana", Font.BOLD, 12));
			JLabel6.setForeground(Color.GRAY);
			JLabel6.setVisible(false);
			getContentPane().add(JLabel6);
		}
		{
			JButton1 = new JButton("Ок");
			JButton1.setBounds(625,9,55,21);
			JButton1.setFont(new Font("Verdana", Font.BOLD, 12));
			JButton1.setActionCommand("Ok");
			getContentPane().add(JButton1);
			
			JButton1.addActionListener(this);
		}
		{
			JButton2 = new JButton("Агрегаты");
			JButton2.setBounds(690,9,98,21);
			JButton2.setFont(new Font("Verdana", Font.BOLD, 12));
			JButton2.setActionCommand("Agregation");
			JButton2.setVisible(false);
			getContentPane().add(JButton2);
			
			JButton2.addActionListener(this);
		}
		{
			JButton3 = new JButton("Fact Table");
			JButton3.setBounds(795,9,103,21);
			JButton3.setFont(new Font("Verdana", Font.BOLD, 12));
			JButton3.setActionCommand("Fact");
			JButton3.setVisible(false);
			getContentPane().add(JButton3);
			
			JButton3.addActionListener(this);
		}
		{
			JButton4 = new JButton("Отчеты");
			JButton4.setBounds(905,9,103,21);
			JButton4.setFont(new Font("Verdana", Font.BOLD, 12));
			JButton4.setActionCommand("Report");
			JButton4.setVisible(false);
			getContentPane().add(JButton4);
			
			JButton4.addActionListener(this);
		}
		{
			JTabbedPane1 = new JTabbedPane();
			JTabbedPane1.setBounds(10, 50, 800, 500);
			getContentPane().add(JTabbedPane1);
		}
		
	}	
	
	public void actionPerformed(ActionEvent ActionEvent_){
		
		// Used resources
		long memory_before_gc = 0; 
		long memory_after_gc = 0;
		long time_before = 0;
		long time_after = 0;
		Runtime Runtime_ = Runtime.getRuntime();;
				
		if( (ActionEvent_.getActionCommand()).equals("Ok")){
			
			Runtime_.gc();	// rubbish
			memory_before_gc = Runtime_.freeMemory();
			time_before = System.currentTimeMillis();
			
			Ok_actionPerformed();
		}		
		
		if( (ActionEvent_.getActionCommand()).equals("Agregation")){
			
			Object[] options = {"Да, генерировать", "Нет, удалить"};
			
			int n = JOptionPane.showOptionDialog(this, 
						"Создать агрегаты, или удалить все имеющиеся*?\n"+
						"*Агрегаты НЕ будут удалены из таблицы фактов", "Агрегаты",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[1]);
						
			Runtime_.gc();	// rubbish
			memory_before_gc = Runtime_.freeMemory();
			time_before = System.currentTimeMillis();
			
			Agregation_actionPerformed(n);
		}
		
		if( (ActionEvent_.getActionCommand()).equals("Fact")){
			
			Object[] options = {"Да, создать", "Нет, удалить"};
			
			int n = JOptionPane.showOptionDialog(this, 
						"Создать таблицу фактов, или удалить данные из имеющейся*?\n"+
						"*Связные данные удалены небудут", "Таблица фактов",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
						null, options, options[1]);
			
			Runtime_.gc();	// rubbish
			memory_before_gc = Runtime_.freeMemory();
			time_before = System.currentTimeMillis();
			
			Fact_actionPerformed(n);
		}
		
		if( (ActionEvent_.getActionCommand()).equals("Report")){
						
			Runtime_.gc();	// rubbish
			memory_before_gc = Runtime_.freeMemory();
			time_before = System.currentTimeMillis();
			
			Report_actionPerformed();
		}
		
		memory_after_gc = Runtime_.freeMemory();
		time_after = System.currentTimeMillis();
		
		long used_memory = (memory_before_gc - memory_after_gc)/1024;
		double used_time = (time_after - time_before)/(double)1000;
		
		JLabel5.setText( "Использовано: ОЗУ: ~" + used_memory + " Kb" );				
		JLabel6.setText( "Время: ~" + used_time + " сек." );
	}
	
	public void Ok_actionPerformed(){
		
		JTabbedPane1.removeAll();
		
		Lab1_Main.path = JTextField1.getText();
		
		// DataBase structure
		XML_Reader XML_Reader_ = new XML_Reader();		
		
		Vector<Vector<String>> v = new Vector<Vector<String>>();
		try{
			v = XML_Reader_.XML_Read(Lab1_Main.path.concat(XML_DB_PATH));
		}catch(Exception e){
			System.out.println("Error" + e.toString());
		}
		
			// Enter new Tab
			
		for(int i=0; i < v.size(); i++){
			String T_Name = (v.get(i)).elementAt(0);
			JTabbedPane1.addTab( T_Name, new M_JPanel(T_Name, v.get(i)) );
		}
		
		JLabel5.setVisible(true);
		JLabel6.setVisible(true);
		
		JButton2.setVisible(true);
		JButton3.setVisible(true);
		JButton4.setVisible(true);
	}
	
	public void Agregation_actionPerformed(int n){
		try{
						
			if(n == 0){
				
				Agregation Agregation_ = new Agregation();
				Agregation_.CreateNew_Agregate();
				
				Ok_actionPerformed();
			}else{
				if(n == 1){
					
					Agregation Agregation_ = new Agregation();
					Agregation_.DeleteAll_Agregate();
					
					Ok_actionPerformed();
				}
			}
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Agrerate_Error  \n" + e.toString(),
					"Ошибка", JOptionPane.ERROR_MESSAGE);	
		}
	}
	
	public void Fact_actionPerformed(int n){
		
		try{	
						
			if(n == 0){
				
				Fact_Table Fact_Table_ = new Fact_Table();
				Fact_Table_.New_FactTable();
				
				Ok_actionPerformed();
			}else{
				if(n == 1){
					
					Fact_Table Fact_Table_ = new Fact_Table();
					Fact_Table_.Delete_FactTable();
					
					Ok_actionPerformed();
				}
			}
			
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, "Fact_Main_Error  \n" + e.toString(),
					"Ошибка", JOptionPane.ERROR_MESSAGE);	
		}
	}
	
	public void Report_actionPerformed(){
		Report Report_ = new Report();
		Report_.Rep(this);
	}
}
