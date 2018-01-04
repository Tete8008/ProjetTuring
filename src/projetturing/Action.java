/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetturing;

/**
 *
 * @author theophile.candelier
 */
public class Action {
    private int newetat;
    private char newchar;
    private char deplacement;
    
    public Action(int ne,char nc,char dep){
        newetat=ne;
        newchar=nc;
        deplacement=dep;
    }
    
    public boolean equals(Action c){
        return (newetat==c.newetat && newchar==c.newchar && deplacement==c.deplacement);
    }
    
    public String toString(){
        return "[Action: nouvelEtat="+this.newetat+", nouveauSymbole="+this.newchar+", deplacement="+this.deplacement+"]";
    }
    
    public int getNewState(){
        return this.newetat;
    }
    
    public char getNewChar(){
        return this.newchar;
    }
    
    public char getMove(){
        return this.deplacement;
    }
    
}
