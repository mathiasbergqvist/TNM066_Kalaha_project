/*****************************************************
TNM006 - Dataprocesser i kognitiva system
Projekt - Pot.java
Av: Mathias Bergqvist, MT3a
mail: matbe371@student.liu.se
*****************************************************/


public class Pot {
	
	//instansvariabler
	public int index;
	public int noBeans;

    //konstruktor
    public Pot(int i) {
    	
    	if(i==6){ //Boet �r tomt fr�n b�rjan
    		index = i;
    		noBeans = 0;
    	}
    	else{
    		index = i;
    		noBeans = 4; //Startv�rde
    	}	
    }
    
    //klassmetoder
    public int getIndex(){
    	return index;
    }
    
    public int getNoBeans(){
    	return noBeans;
    }
    
    public void setNoBeans(int i){
    	noBeans = i;
    }
    
    
    
}