import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Этот класс является базовым. Он содержит main-функцию. 
 * @author Postrigan V.A.
 * @version 1.0
 */

public class Lab1_Main{	
	
	/**
	 * @param path Содержит путь вызываемой программы  
	 */
	public static String path = (System.getProperty("user.dir"));
	
	/**
	 * main-метод. Вызывает главное окно (JFrame)
	 * @param args Не используются
	 * @return Ничего 
	 */
	public static void main(String[] args){
		
	// Main Frame
		String Frame_name = "Лабораторная работа №2";
		Frame1_Main Frame1_Main_ = new Frame1_Main(Frame_name);		
		Frame1_Main_.setVisible(true);
		Frame1_Main_.setResizable(false);
		
		Frame1_Main_.JTabbedPane1.setBounds(10, 50, 
				Frame1_Main_.getWidth()-25, Frame1_Main_.getHeight()-90);		
	}
	

}
