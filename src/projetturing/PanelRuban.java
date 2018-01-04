package projetturing;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.*;

public class PanelRuban extends JPanel implements Observer{
	private Machine modele;
	private int marginTop;
	private int caseWidth;
	private int marginSide;
	private int offset;
	private int trueoffset;
	public PanelRuban(Machine m) {
		super();
		modele=m;
		marginTop=150;
		caseWidth=40;
		marginSide=80;
		offset=0;
		
		this.setPreferredSize(new Dimension(500,500));
		JButton btn=new JButton("Faire dérouler");
		
        btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				offset+=3;
				trueoffset+=3;
				update(null,null);
			}
        	
        });
        this.add(btn);
        JButton add=new JButton("Ajouter charactère");
		
        add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				modele.setChar(modele.ruban.size(),Character.getName((int)Math.round(Math.random()*9)).toCharArray()[0]);
			}
        	
        });
        this.add(add);
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
		if (arg1 instanceof Vector) {
			
		}
		this.repaint();
		
		
	}
	
	public void paint(Graphics g) {
		//fixe
		super.paintComponent(g);
		g.drawLine(marginSide, marginTop,this.getWidth()-marginSide, marginTop);
		g.drawLine(marginSide, marginTop+caseWidth,this.getWidth()-marginSide, marginTop+caseWidth);
		g.drawLine(marginSide, marginTop,marginSide, marginTop+caseWidth);
		g.drawLine(this.getWidth()-marginSide, marginTop,this.getWidth()-marginSide, marginTop+caseWidth);

		
				
		
		//cases
		
		if (offset+marginSide+Math.round((this.getWidth()-marginSide*2)/caseWidth)*caseWidth>(this.getWidth()-marginSide)) {
			
			offset-=caseWidth;
			
		}
		int start=0;
		if (offset<0) {
			start=1;
		}
		for (int i=start;i<=(Math.round((this.getWidth()-marginSide*2)/caseWidth));i++) {
			
			g.drawLine(offset+marginSide+i*caseWidth, marginTop, offset+marginSide+i*caseWidth, marginTop+caseWidth);
		}
		
		//écriture des charactères:
		for (int i=0;i<modele.ruban.size();i++) {
			g.drawString(modele.ruban.get(i).toString(),trueoffset+marginSide+caseWidth/2+caseWidth*i,marginTop+caseWidth/2);
		}
		
	}

}
