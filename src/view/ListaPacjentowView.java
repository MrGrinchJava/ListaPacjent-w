package view;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import model.BazaDanych;

/**
 * Klasa widoku ListyPacjentów
 *
 */
public class ListaPacjentowView extends JPanel
{
	JScrollPane scroll;
    private JTable	jTable=null;
    private BazaDanych	model =null;
    private JButton mAddButton=null,mRemoveButton=null;
    private ListSelectionModel selectionModel;
    /**
     * konstruktor klasy 
     */
    public ListaPacjentowView()
    {
    	 model = new BazaDanych();
 	    this.jTable=new JTable(model);
 	    selectionModel = jTable.getSelectionModel();
 	    selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 	    
 	    setSize(480,460);
 	    
 	    scroll=new JScrollPane(jTable);
 	    add(scroll);
 	    scroll.setPreferredSize(new Dimension(460,400));
 	    scroll.setMinimumSize(new Dimension(460,400)); 
 	  
 	    this.mAddButton = new JButton("Add"); 
 	    add(this.mAddButton);
 	    this.mAddButton.setBounds(100, 410, 80, 20);
 	    this.mRemoveButton = new JButton("Remove"); 
 	    this.mRemoveButton.setBounds(185, 410, 80, 20);
 	    add(this.mRemoveButton);
    } 

	public JButton getmAddButton() {
		return mAddButton;
	}


	public JButton getmRemoveButton() {
		return mRemoveButton;
	}


	public JScrollPane getScroll() {
		return scroll;
	}


	public JTable getmList() {
		return jTable;
	}


	public ListSelectionModel getSelectionModel() {
		return selectionModel;
	}


	public BazaDanych getModel() {
		return model;
	}
	
	
	
	
    
    
    
}