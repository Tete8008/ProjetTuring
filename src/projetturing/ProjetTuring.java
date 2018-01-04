/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetturing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.*;

/**
 *
 * @author theophile.candelier
 */

public class ProjetTuring extends JFrame{

    /**
     * @param args the command line arguments
     */
	Machine modele;
	
	public ProjetTuring(final Machine modeleinitial) {
		if (modeleinitial!=null) {
			modele=modeleinitial;
		}else {
			modele=new Machine();
		}
		
        Condition c=new Condition(0,'Z');
        modele.addRule(c, new Action(0,'N','>'));
        System.out.println(modele.getAction(new Condition(0,'Z')));
        
        modele.setChar(0, 'y');
        
        
        PanelRuban p=new PanelRuban(modele);
        
        modele.addObserver(p);
        
        Box box=new Box(BoxLayout.PAGE_AXIS);
        box.add(p);
        
        JButton add=new JButton("Ajouter charactère");
		
        add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				modele.setChar(modele.ruban.size(),Character.getName((int)Math.round(Math.random()*9)).toCharArray()[0]);
			}
        	
        });
        
        this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				// TODO Auto-generated method stub
				modele.scroll(arg0.getWheelRotation()*arg0.getScrollAmount());
			}
        	
        });
        box.add(add);
        
        this.getContentPane().add(box);
        this.pack();
        this.setTitle("Machine de Turing");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	
	
	
	
	
	//main
    public static void main(String[] args) {
        // Ici on mettra la récupération des données qu'on aura précédemment enregistrées (probablement en JSON ou XML)
    	new ProjetTuring(null);
        
    }
    
}
