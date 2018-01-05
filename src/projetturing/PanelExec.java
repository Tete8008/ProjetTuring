package projetturing;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.scene.control.Slider;

public class PanelExec extends JPanel implements Observer{
	private Machine modele;
	private static Timer timer;
	private JLabel vitesse;
	private JLabel etat;
	private JLabel position;
	
	public PanelExec(Machine modele) {
		this.modele=modele;
		this.setBorder(BorderFactory.createTitledBorder("Exécution"));
        this.setSize(new Dimension(super.getWidth(),200));
        JLabel label=new JLabel("Ruban initial: ");
        JTextField textfield=new JTextField();
        textfield.setPreferredSize(new Dimension(100,30));
        
        
        JButton init=new JButton("Initialiser");
		
        init.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				modele.ruban=new Ruban();
				modele.setPosition(0);
				modele.setState(0);
				
				for (int i=0;i<textfield.getText().length();i++) {
					modele.addChar(i, textfield.getText().charAt(i));
				}
			}
        	
        });
        
        
        JButton pas=new JButton("Exécuter pas à pas");
		
        pas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (modele.getPosition()<modele.ruban.getRightSize()-1 && modele.getPosition()>=-modele.ruban.getLeftSize()) {
					  modele.applyRule(modele.getState(), modele.getChar(modele.getPosition()));
					  modele.setOffset(-modele.getPosition()*modele.getCaseWidth()+230);
				  }

			}
        	
        });
        
        timer=new Timer();
        vitesse=new JLabel();
        JSlider slider=new JSlider();
        
        
        JButton auto=new JButton("Exécution automatique");
        auto.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent ae) {
        		slider.setEnabled(false);
        		timer.scheduleAtFixedRate(new TimerTask() {
      			  @Override
    			  public void run() {
      				  if (modele.getPosition()<modele.ruban.getRightSize()-1 && modele.getPosition()>=-modele.ruban.getLeftSize()) {
      					  modele.applyRule(modele.getState(), modele.getChar(modele.getPosition()));
      					  modele.setOffset(-modele.getPosition()*modele.getCaseWidth()+230);
      				  }else {
      					  timer.cancel();
      					  timer=new Timer();
      				  }
      					  
    			  }
    			}, 1000/modele.getSpeed(), 1000/modele.getSpeed());
        	}
        });
        
        
        
        slider.setMaximum(10);
        slider.setMinimum(1);
        slider.setValue(modele.getSpeed());
        vitesse.setText("Vitesse: "+String.valueOf(slider.getValue()));
        slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent arg0) {
				// TODO Auto-generated method stub
				JSlider source=(JSlider)arg0.getSource();
				modele.setSpeed(source.getValue());
				
				
				
			}
        	
        });
        
        JButton stop=new JButton("Stop");
        stop.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent ae) {
        		//task.cancel();
        		timer.cancel();
        		timer=new Timer();
        		slider.setEnabled(true);
        	}
        });
        
        etat=new JLabel();
        etat.setText("État: "+modele.getState());
        
        position=new JLabel();
        position.setText("Position: "+modele.getPosition());
        
        
        this.add(position);
        this.add(etat);
        this.add(vitesse);
        this.add(slider);
        this.add(auto);
        this.add(stop);
        this.add(label);
        this.add(textfield);
        this.add(init);
        this.add(pas);
        this.setLayout(new FlowLayout());
	}
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		etat.setText("État: "+modele.getState());
		vitesse.setText("Vitesse: "+String.valueOf(modele.getSpeed()));
		position.setText("Position: "+modele.getPosition());
		
	}

}
