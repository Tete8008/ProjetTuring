/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetturing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;
import java.util.Vector;

/**
 *
 * @author theophile.candelier
 */
public class Machine extends Observable{

    public ArrayList<Character> ruban;
    private Map<Condition, Action> regles;
    private int position;
    private int etat;
    private int vitesse;
    private int etape;

    public Machine() {
        this.ruban = new ArrayList<Character>();
        this.regles = new HashMap<Condition, Action>();
        this.position = 0;
        this.etat = 0;
        this.vitesse = 1;
        this.etape = 0;
    }

    public char getChar(int pos) {
        return (char) ruban.get(pos);
    }

    public void setChar(int pos, char c) {
        ruban.add(pos, c);
        this.setChanged();
        this.notifyObservers(new Vector(pos,c));
    }

    public void addRule(Condition a, Action c) {
        regles.put(a, c);
    }

    public void applyRule(int etat, char car) {
        Action c=this.getAction(new Condition(etat,car));
        this.setState(c.getNewState());
        this.setChar(this.position, c.getNewChar());
        if (c.getMove()=='>'){
            this.setPosition(this.position+1);
        }else
        if (c.getMove()=='<'){
            this.setPosition(this.position-1);
        }
        
    }

    public int getState() {
        return this.etat;
    }

    public void modifyRule(Condition a, Action c) {

        regles.replace(a, c);
    }

    public void deleteRule(Action c) {
        regles.remove(c);
    }

    public void save() {

    }

    public void load() {

    }

    public void updateHistoric() {

    }

    public void setSpeed(int v) {
        this.vitesse = v;
    }

    public int getSpeed() {
        return this.vitesse;
    }

    public String getStep() {
        return "";
    }
    
    

    public Action getAction(Condition c) {
        Set<Entry<Condition, Action>> setHm = regles.entrySet();
        Iterator<Entry<Condition, Action>> it = setHm.iterator();
        Action act=null;
        while (it.hasNext() && act==null) {
            Entry<Condition, Action> e = it.next();
            if (e.getKey().equals(c)){
                act=e.getValue();
            }
        }
        return act;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int p) {
        this.position = p;
    }
    
    public void launchAutomaticExecution(){
        
    }
    
    public void setState(int state){
        this.etat=state;
    }

}