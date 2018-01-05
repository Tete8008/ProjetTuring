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
        
        JPanel panelExec=new JPanel();
        panelExec.setBorder(BorderFactory.createTitledBorder("Exécution"));
        panelExec.setSize(new Dimension(super.getWidth(),200));
        
        PanelRuban p=new PanelRuban(modele);
        panelRuban.add(p);
        modele.addObserver(p);
        
        JLabel label=new JLabel("Ruban initial: ");
        JTextField textfield=new JTextField();
        textfield.setPreferredSize(new Dimension(100,30));
        
        
        JButton init=new JButton("Initialiser");
		
        init.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				for (int i=0;i<textfield.getText().length();i++) {
					modele.addChar(i, textfield.getText().charAt(i));
				}
			}
        	
        });
        
        
        JButton pas=new JButton("Faire un pas");
		
        pas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				modele.addChar(-(modele.ruban.getLeftSize())-1,Character.getName((int)Math.round(Math.random()*50)).toCharArray()[0]);
				modele.setOffset(modele.ruban.getLeftSize()*p.caseWidth+200);
				modele.setPosition(modele.getPosition()-1);

			}
        	
        });
        panelExec.add(label);
        panelExec.add(textfield);
        panelExec.add(init);
        panelExec.add(pas);
        panelExec.setLayout(new FlowLayout());
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
