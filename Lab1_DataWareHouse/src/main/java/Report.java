import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


public class Report {
	
	public void Rep(JFrame fr){
		Rep1(fr);
		Rep2(fr);
		Rep3(fr);
	}
	
	public void Rep1(JFrame fr){
		FileOutputStream fout;
		String InputFile = "Time.html";
		try{		
			fout = new FileOutputStream(InputFile);					
		}catch(FileNotFoundException eout){		
			JOptionPane.showMessageDialog(fr, "Ошибка при записи в файл\n" + eout);
			return;
		}
		// Sale_1.Quantity, Sale_1.Price
		
		try{
			Object[][] data = DB_Connect.getData(
				"SELECT Time_2.Year, Time_2.Month, Sale_1.Quantity, Sale_1.Price "+
				"FROM Time_2, Sale_1 "+
				"WHERE (Time_2.Time_ID = Sale_1.Time_ID) AND (Time_2.LEVEL_ = 1) " , 5);
			
		
	String Top = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">	" +
	"<META http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1251\" />" +
						"<HTML><HEAD><TITLE>Отчет</TITLE></HEAD><BODY>" +
						"<H1><CENTER>Продажи по месяцам</CENTER></H1><BR><TABLE BORDER=1>";
				
			fout.write( Top.getBytes("Cp1251") );
				
			String Text = "";
				
			Text += "<TR><TD><CENTER>Год</CENTER></TD>" +
			    	"<TD><CENTER>Месяц</CENTER></TD>" +
			    	"<TD><CENTER>Количество</CENTER></TD>" +
			    	"<TD><CENTER>Цена</CENTER></TD></TR>";
			fout.write( Text.getBytes("Cp1251") );
			Text = "";
				
			for(int i=0; i < data.length; i++){
				Text += "<TR><TD><CENTER>"+data[i][0]+"</CENTER></TD>" +
							   "<TD>"+data[i][1]+"</TD>" +
							   "<TD>"+data[i][2]+"</TD>" +
							   "<TD>"+data[i][3]+"</TD></TR>";			
			}
			fout.write( Text.getBytes("Cp1251") );
				
			String End = "</TABLE></BODY></HTML>";
			fout.write( End.getBytes("Cp1251") );
			fout.close();
			
		}catch(Exception ei){
			JOptionPane.showMessageDialog(fr,
					"Ошибка при записи в выходной файл \n"+InputFile+"\n"+ ei);
		}
	}
	
	public void Rep2(JFrame fr){
		FileOutputStream fout;
		String InputFile = "Time2.html";
		try{		
			fout = new FileOutputStream(InputFile);					
		}catch(FileNotFoundException eout){		
			JOptionPane.showMessageDialog(fr, "Ошибка при записи в файл\n" + eout);
			return;
		}
		// Sale_1.Quantity, Sale_1.Price
		
		try{
			Object[][] data = DB_Connect.getData(
				"SELECT Time_2.Year, Sale_1.Quantity, Sale_1.Price "+
				"FROM Time_2, Sale_1 "+
				"WHERE (Time_2.Time_ID = Sale_1.Time_ID) AND (Time_2.LEVEL_ = 2) " , 4);
			
		
	String Top = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">	" +
	"<META http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1251\" />" +
						"<HTML><HEAD><TITLE>Отчет</TITLE></HEAD><BODY>" +
						"<H1><CENTER>Продажи по годам</CENTER></H1><BR><TABLE BORDER=1>";
				
			fout.write( Top.getBytes("Cp1251") );
				
			String Text = "";
				
			Text += "<TR><TD><CENTER>Год</CENTER></TD>" +
			    	"<TD><CENTER>Количество</CENTER></TD>" +
			    	"<TD><CENTER>Цена</CENTER></TD></TR>";
			fout.write( Text.getBytes("Cp1251") );
			Text = "";
				
			for(int i=0; i < data.length; i++){
				Text += "<TR><TD><CENTER>"+data[i][0]+"</CENTER></TD>" +
							   "<TD>"+data[i][1]+"</TD>" +
							   "<TD>"+data[i][2]+"</TD></TR>";			
			}
			fout.write( Text.getBytes("Cp1251") );
				
			String End = "</TABLE></BODY></HTML>";
			fout.write( End.getBytes("Cp1251") );
			fout.close();
			
		}catch(Exception ei){
			JOptionPane.showMessageDialog(fr,
					"Ошибка при записи в выходной файл \n"+InputFile+"\n"+ ei);
		}
	}
	
	public void Rep3(JFrame fr){
		FileOutputStream fout;
		String InputFile = "Time3.html";
		try{		
			fout = new FileOutputStream(InputFile);					
		}catch(FileNotFoundException eout){		
			JOptionPane.showMessageDialog(fr, "Ошибка при записи в файл\n" + eout);
			return;
		}
		// Sale_1.Quantity, Sale_1.Price
				
		try{
			
			String Q = "SELECT Product_3.Product_Group, Product_3.Trademark, Sum(Quantity), Sum(Price) "+
			"FROM Sale_1, Product_3, Time_2 "+
			"WHERE Time_2.LEVEL_=0 AND Product_3.LEVEL_=0" + 
			"AND Sale_1.Product_ID=Product_3.Product_ID "+
			"AND Time_2.Time_ID=Sale_1.Time_ID GROUP BY Product_3.Product_Group, Product_3.Trademark" ;
			
			
			Object[][] data = DB_Connect.getData(Q , 5);
			
		
	String Top = "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">	" +
	"<META http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1251\" />" +
						"<HTML><HEAD><TITLE>Отчет</TITLE></HEAD><BODY>" +
						"<H1><CENTER>Продажи по книг по месяцам</CENTER></H1><BR><TABLE BORDER=1>";
				
			fout.write( Top.getBytes("Cp1251") );
				
			String Text = "";
				
			Text += "<TR><TD><CENTER>Группа</CENTER></TD>" +
			    	"<TD><CENTER>Марка</CENTER></TD>" +
			    	"<TD><CENTER>Количество</CENTER></TD>" +
			    	"<TD><CENTER>Цена</CENTER></TD></TR>";
			fout.write( Text.getBytes("Cp1251") );
			Text = "";
				
			for(int i=0; i < data.length; i++){
				Text += "<TR><TD><CENTER>"+data[i][0]+"</CENTER></TD>" +
							   "<TD>"+data[i][1]+"</TD>" +
							   "<TD>"+data[i][2]+"</TD>" +
							   "<TD>"+data[i][3]+"</TD></TR>";			
			}
			fout.write( Text.getBytes("Cp1251") );
				
			String End = "</TABLE></BODY></HTML>";
			fout.write( End.getBytes("Cp1251") );
			fout.close();
			
		}catch(Exception ei){
			JOptionPane.showMessageDialog(fr,
					"Ошибка при записи в выходной файл \n"+InputFile+"\n"+ ei);
		}
	}
}
