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
public class Condition {
    
    private int etat;
    private char car;
    
    public Condition(int etat,char car){
        this.etat=etat;
        this.car=car;
    }
    
    public boolean equals(Condition c){
        return (this.car==c.car && this.etat==c.etat);
    }
    
    public String toString(){
        return "[Condition: etat="+this.etat+", symbole="+this.car+"]";
    }
    
    public void meteorStrike(){
        System.out.println("METEOR STRIKE");
    }
    
}
