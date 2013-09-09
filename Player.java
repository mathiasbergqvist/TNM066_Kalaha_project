/*****************************************************
TNM006 - Dataprocesser i kognitiva system
Projekt - Player.java
Av: Mathias Bergqvist, MT3a
mail: matbe371@student.liu.se
*****************************************************/

import java.util.*;

public class Player {
	
	//instansvariabler
	private String name;
	private int score;
	public Pot[] pots;
	

    //konstruktor
    public Player(String n) {
    	name=n;
    	score = 0; //noll poäng i början.
    	pots = new Pot[7];
    	setPots();
    }
    
    //klassmetoder
    public void setPots(){	 	
    	for (int i=0; i<pots.length; i++){
    		pots[i] = new Pot(i);
    	}	
    }
    
    public String getName(){
    	return name;
    }
    
    public int getScore(){
    	return pots[6].getNoBeans();
    }
    
    public int getBeans(int i){
    	return pots[i].getNoBeans();
    }
}
