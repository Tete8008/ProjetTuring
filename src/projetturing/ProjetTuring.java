/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetturing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

/**
 *
 * @author theophile.candelier
 */

public class ProjetTuring extends JFrame{

    /**
     * @param args the command line arguments
     */
	private Machine modele;
	
	public ProjetTuring(final Machine modeleinitial) {
		if (modeleinitial!=null) {
			modele=modeleinitial;
		}else {
			modele=new Machine();
		}
		
        Condition c=new Condition(0,'Z');
        modele.addRule(c, new Action(0,'N','>'));
        System.out.println(modele.getAction(new Condition(0,'Z')));
        
        
        JPanel panelRuban=new JPanel();
        panelRuban.setBorder(BorderFactory.createTitledBorder("Ruban"));
        panelRuban.setSize(new Dimension(super.getWidth(),200));
        panelRuban.setLayout(new FlowLayout());
        
        PanelExec panelExec=new PanelExec(modele);
        
        
        PanelRuban p=new PanelRuban(modele);
        panelRuban.add(p);
        modele.addObserver(p);
        modele.addObserver(panelExec);
        
        
        this.add(panelExec);
        this.add(panelRuban);
        
      
        
        this.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent arg0) {
				// TODO Auto-generated method stub
				modele.scroll(arg0.getWheelRotation()*arg0.getScrollAmount()*3);
				
			}
        	
        });
        
        //layout pour les 4 grands panels (regles, execution,ruban et historique)
        this.setLayout(new GridLayout(2,2));
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
