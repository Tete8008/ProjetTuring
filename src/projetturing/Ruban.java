package projetturing;

import java.util.ArrayList;

public class Ruban {
	private ArrayList<Character> rubandroit;
	private ArrayList<Character> rubangauche;
	private int positionTeteLecture;
	
	public Ruban() {
		rubandroit=new ArrayList<Character>();
		rubangauche=new ArrayList<Character>();
	}
	
	public void addChar(char c,int pos) {
		System.out.println(pos);
		if (pos<0) {
			for (int i=0;i<-pos-1;i++) {
				if (rubangauche.size()>i) {
					if (rubangauche.get(i)==null) {
						rubangauche.add(i,' ');
					}
				}else {
					rubangauche.add(i,' ');
				}
				
			}
			rubangauche.add(-pos-1, c);
		}else {
			for (int i=0;i<pos;i++) {
				if (rubandroit.size()>i) {
					if (rubandroit.get(i)==null) {
						rubandroit.add(i,' ');
					}
				}else {
					rubandroit.add(i,' ');
				}
				
			}
			rubandroit.add(pos, c);
		}
	}
	
	public Character getChar(int pos) {
		if (pos<0) {
			return rubangauche.get(-pos-1);
		}else {
			return rubandroit.get(pos);
		}
	}
	
	public int getSize() {
		return rubangauche.size()+rubandroit.size();
	}
	
	public int getRightSize() {
		return rubandroit.size();
	}
	
	public int getLeftSize() {
		return rubangauche.size();
	}
	
	
	
}
