/**
 * @(#)random.java
 *
 *
 * @author 
 * @version 1.00 2010/5/6
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;
import javax.swing.border.LineBorder;
import java.lang.*;
import java.util.*;

public class random {

    public static void main(String[] arg){
    	
    	int[] a = new int[50];
    	
    	for(int i=0; i<a.length; i++){
    		a[i] = (int)(Math.random()*6);
    		System.out.println(a[i]);
    		System.out.println("\n");
    	} 
    	
    }
    
    
}