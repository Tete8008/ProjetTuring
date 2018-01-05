package projetturing;

import java.awt.Color;
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
	public int caseWidth;
	private int marginSide;
	private int offset;
	public PanelRuban(Machine m) {
		super();
		modele=m;
		marginTop=10;
		caseWidth=modele.getCaseWidth();
		marginSide=0;
		offset=0;
		this.setPreferredSize(new Dimension(500,caseWidth+10+marginTop*2));
		modele.setOffset(-modele.getPosition()*modele.getCaseWidth()+230);
        
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
	
		this.repaint();
		
		
		
		
		
	}
	
	public void paint(Graphics g) {
		//fixe
		g.setColor(Color.black);
		super.paintComponent(g);
		g.drawLine(marginSide, marginTop,this.getWidth()-marginSide, marginTop);
		g.drawLine(marginSide, marginTop+caseWidth,this.getWidth()-marginSide, marginTop+caseWidth);
		g.drawLine(marginSide, marginTop,marginSide, marginTop+caseWidth);
		//g.drawLine(this.getWidth()-marginSide, marginTop,this.getWidth()-marginSide, marginTop+caseWidth);

		
				
		
		//cases
		
		offset=modele.getOffset()%caseWidth;
		
		for (int i=0;i<=(Math.round((this.getWidth()-marginSide*2)/caseWidth)+1);i++) {
			
			g.drawLine(offset+marginSide+i*caseWidth, marginTop, offset+marginSide+i*caseWidth, marginTop+caseWidth);
		}
		
		//écriture des charactères:
		for (int i=-modele.ruban.getLeftSize();i<modele.ruban.getRightSize();i++) {
			g.drawString(modele.ruban.getChar(i).toString(),modele.getOffset()+marginSide+caseWidth/2+caseWidth*i,marginTop+caseWidth/2);
		}
		
		
		//tête de lecture:
		g.setColor(Color.red);
		g.drawRect(modele.getOffset()+marginSide+modele.getPosition()*caseWidth+2, marginTop-5, caseWidth-4,caseWidth+10);
		
	}

}
