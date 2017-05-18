package view;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import controller.Commons;
/**
 * 
 *klasa dziedziczaca po JFrame
 */
public class KomunikatView extends JFrame{
	public KomunikatView(){	
	}
	/**
	 * funkcja tworzaca komunikaty wysletlany potczas uzytkowania programu
	 * @param infoMessage tresc wiadomosci 
	 * @param titleBar		Tytu³ wiadomosci
	 */
	public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }	
}
