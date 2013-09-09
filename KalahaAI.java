/*****************************************************
TNM006 - Dataprocesser i kognitiva system
Projekt - Kalaha.java
Av: Mathias Bergqvist, MT3a
mail: matbe371@student.liu.se
*****************************************************/

import java.awt.*;
import java.awt.event.*; //Eventhanterare, actionlistener
import javax.swing.*; //Grafiska program
import javax.swing.border.LineBorder;//Inneh�ller klasser f�r ramar
import java.util.*;
import java.lang.*;
import java.io.*;
import java.net.*;

public class KalahaAI extends JFrame implements ActionListener{
	
	//Grafiska komponenter
	private JLabel lblStartScreen, lblBoard, lblMark, lblCPUPoints, lblPlayerPoints, lblGameText, lblGameOverVictory, lblGameOverDefeat, lblGameOverDraw, lblBackground;
	private JLabel pot1, pot2, pot3, pot4, pot5, pot6, mainPot;
	private JLabel CPUpot1, CPUpot2, CPUpot3, CPUpot4, CPUpot5, CPUpot6, mainPotCPU;
	private JButton btnStart, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnOK, btnNewGame, btnEndGame;
	private ImageIcon startScreen, start, background, board, mark, gameOverVictory, gameOverDefeat, gameOverDraw, newGameIcon, endGameIcon;
	private JMenuBar menuBar;
	private JMenu gameMenu, helpMenu;
	private JMenuItem about, newGame, end, help;   
	
	//Variabler
	private String page; //H�ller koll p� vilken sida som man befinner sig p�
	
	//Referensvariabler
	private Player p;
	private Player cpu;
	private	Player temp; 
	private	Player tempCPU;
	
	//Global variabel
	int random;
	int AIchoise;
		
//Konstruktor---------------------------------------------------------------------------------
    public KalahaAI() {
    	
    	Container c = getContentPane();
    	c.setLayout(null);
    	
    	//Menyer-------------------------------------------------------------------------------
    	menuBar = new JMenuBar();
    	gameMenu = new JMenu("Spel");
    	helpMenu = new JMenu("Hj�lp");
    	about = new JMenuItem("Om");
    	newGame = new JMenuItem("Nytt spel");
    	end = new JMenuItem("Avsluta");
    	help = new JMenuItem("Visa hj�lp");
    	
    	gameMenu.add(about);
    	gameMenu.add(newGame);
    	gameMenu.add(end);
    	helpMenu.add(help);
    	
    	menuBar.add(gameMenu);
    	menuBar.add(helpMenu);
    	
    	setJMenuBar(menuBar);
    	
    	//Stratsk�rm---------------------------------------------------------------------------
    	page = "start"; //initialv�rde f�r page �r startsidan
    	startScreen = new ImageIcon("images/startscreen2.png");
    	start = new ImageIcon("images/startbutton.png");
    	
    	lblStartScreen = new JLabel();
    	lblStartScreen.setBounds(0,0,800,600);
    	lblStartScreen.setIcon(startScreen);
    	
    	btnStart = new JButton();
    	btnStart.setBounds(600,490,120,50);
    	btnStart.setIcon(start);
    	
    	//Spelsk�rm----------------------------------------------------------------------------
    	background = new ImageIcon("images/background.png");
    	board = new ImageIcon("images/kalahaboard.png");
    	mark = new ImageIcon("images/mark_green2.png");
    	
    	lblBackground = new JLabel();
    	lblBackground.setIcon(background);
    	lblBackground.setBounds(0,0,800,600);
    	lblGameText = new JLabel();
    	lblGameText.setBounds(190,390,200,30);
    	lblGameText.setFont(new Font("SanSerif", Font.PLAIN, 18));
    	lblGameText.setText("Chose a pot.");
    	lblGameText.setOpaque(true);
		lblGameText.setBackground(Color.white);
		lblGameText.setBorder(new LineBorder(Color.black));
    	lblBoard = new JLabel();
    	lblBoard.setBounds(100,30,600,300);
    	lblBoard.setIcon(board);
    	lblMark = new JLabel();
    	lblMark.setIcon(mark);
    	lblPlayerPoints = new JLabel();
    	lblPlayerPoints.setBounds(100,440,150,50);
    	lblPlayerPoints.setFont(new Font("SanSerif", Font.BOLD, 26));
    	lblCPUPoints = new JLabel();
    	lblCPUPoints.setBounds(100,480,150,50);
    	lblCPUPoints.setFont(new Font("SanSerif", Font.BOLD, 26));
    	
    	btnOK = new JButton("OK");
    	btnOK.setBounds(400,390,60,30);
    	
    	pot1 = new JLabel();
    	pot2 = new JLabel();
    	pot3 = new JLabel();
    	pot4 = new JLabel();
    	pot5 = new JLabel();
    	pot6 = new JLabel();
    	mainPot = new JLabel();
    	CPUpot1 = new JLabel();
    	CPUpot2 = new JLabel();
    	CPUpot3 = new JLabel();
    	CPUpot4 = new JLabel();
    	CPUpot5 = new JLabel();
    	CPUpot6 = new JLabel();
    	mainPotCPU = new JLabel();
    	
    	pot1.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	pot2.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	pot3.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	pot4.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	pot5.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	pot6.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	mainPot.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	CPUpot1.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	CPUpot2.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	CPUpot3.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	CPUpot4.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	CPUpot5.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	CPUpot6.setFont(new Font("SanSerif", Font.PLAIN, 24));
    	mainPotCPU.setFont(new Font("SanSerif", Font.PLAIN, 24));
    
    	pot1.setBounds(212,263,30,30);
    	pot2.setBounds(285,263,30,30);
    	pot3.setBounds(358,263,30,30);
    	pot4.setBounds(431,263,30,30);
    	pot5.setBounds(504,263,30,30);
    	pot6.setBounds(574,263,30,30);
    	mainPot.setBounds(642,167,30,30);
    	
    	mainPotCPU.setBounds(142,167,30,30);
    	CPUpot6.setBounds(212,72,30,30);
    	CPUpot5.setBounds(285,72,30,30);
    	CPUpot4.setBounds(358,72,30,30);
    	CPUpot3.setBounds(431,72,30,30);
    	CPUpot2.setBounds(504,72,30,30);
    	CPUpot1.setBounds(574,72,30,30);
    	 	
    	btnOne = new JButton("1");
    	btnTwo = new JButton("2");
    	btnThree = new JButton("3");
    	btnFour = new JButton("4");
    	btnFive = new JButton("5");
    	btnSix = new JButton("6");
       
    	btnOne.setBounds(190,335,60,40);
    	btnTwo.setBounds(265,335,60,40);
    	btnThree.setBounds(335,335,60,40);
    	btnFour.setBounds(405,335,60,40);
    	btnFive.setBounds(480,335,60,40);
    	btnSix.setBounds(550,335,60,40);
   
    	//Spelslut-----------------------------------------------------------------------------
    	gameOverVictory = new ImageIcon("images/endscreen_victory.png");
    	gameOverDefeat = new ImageIcon("images/endscreen_defeat.png");
    	gameOverDraw = new ImageIcon("images/endscreen_no_winner.png");
    	newGameIcon = new ImageIcon("images/btn_new.png");
    	endGameIcon = new ImageIcon("images/btn_end.png");
    	
    	lblGameOverVictory = new JLabel();
    	lblGameOverVictory.setBounds(0,0,800,600);
    	lblGameOverVictory.setIcon(gameOverVictory);
    	lblGameOverDefeat = new JLabel();
    	lblGameOverDefeat.setBounds(0,0,800,600);
    	lblGameOverDefeat.setIcon(gameOverDefeat);
    	lblGameOverDraw = new JLabel();
    	lblGameOverDraw.setBounds(0,0,800,600);
    	lblGameOverDraw.setIcon(gameOverDraw);
    	
    	btnNewGame = new JButton("");
    	btnEndGame = new JButton("");
    	btnNewGame.setBounds(325,455,200,100);
    	btnEndGame.setBounds(560,455,200,100);
    	btnNewGame.setIcon(newGameIcon);
    	btnEndGame.setIcon(endGameIcon);
    	
    	//Actionlisteners----------------------------------------------------------------------
    	btnStart.addActionListener(this);
    	btnOne.addActionListener(this);
    	btnTwo.addActionListener(this);
    	btnThree.addActionListener(this);
    	btnFour.addActionListener(this);
    	btnFive.addActionListener(this);
    	btnSix.addActionListener(this);
    	btnOK.addActionListener(this);
    	btnEndGame.addActionListener(this);
    	btnNewGame.addActionListener(this);
    	about.addActionListener(this);
    	newGame.addActionListener(this);
    	end.addActionListener(this);
    	help.addActionListener(this);
    
    	//Containern---------------------------------------------------------------------------
    	c.add(lblCPUPoints);
    	c.add(lblPlayerPoints);
    	c.add(lblGameText);
    	c.add(lblMark);
    	c.add(pot1);
    	c.add(pot2);
    	c.add(pot3);
    	c.add(pot4);
    	c.add(pot5);
    	c.add(pot6);
    	c.add(CPUpot1);
    	c.add(CPUpot2);
    	c.add(CPUpot3);
    	c.add(CPUpot4);
    	c.add(CPUpot5);
    	c.add(CPUpot6);
    	c.add(mainPot);
    	c.add(mainPotCPU);
    	c.add(btnOK);
    	c.add(btnStart);
    	c.add(btnOne);
    	c.add(btnTwo);
    	c.add(btnThree);
    	c.add(btnFour);
    	c.add(btnFive);
    	c.add(btnSix);
    	c.add(btnNewGame);
    	c.add(btnEndGame);
    	c.add(lblStartScreen);//Bakgrundsbilderna placeras sist i containern.
    	c.add(lblGameOverVictory);
    	c.add(lblGameOverDefeat);
    	c.add(lblGameOverDraw);
    	c.add(lblBoard);
    	c.add(lblBackground);
    	
    	getPage("start");
    	
    	setSize(800,640);
    	setResizable(false);
    	setTitle("Kalaha beta");
    	setVisible(true);
    	setDefaultCloseOperation(EXIT_ON_CLOSE);
    	
    }
    
    //Eventhanterare---------------------------------------------------------------------------
    public void actionPerformed(ActionEvent e){
    	
    	if (e.getSource() == end){
    		int i = JOptionPane.showConfirmDialog(this, "Vill du verkligen avsluta Kalaha?", "Avsluta", JOptionPane.YES_NO_OPTION);
    		if (i == 0){
				System.exit(0);
			}
    	}
    	else if (e.getSource() == newGame){
    		int i = JOptionPane.showConfirmDialog(this, "Vill du avsluta p�g�ende spel f�r att starta ett nytt?", "Nytt spel", JOptionPane.YES_NO_OPTION);
    		if (i == 0){
    			getPage("game");
    			newGame();
    		}
    	}
    	else if (e.getSource() == about){
    		JOptionPane.showMessageDialog(this, "Kalaha �r skapat av Mathias Bergqvist 2010 i kursen \nTMN066 - Dataprocesser i kognitiva system vid \nLink�pings Universitet, Campus Norrk�ping.", "Om Kalaha", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else if (e.getSource() == help){
    		JOptionPane.showMessageDialog(this, "Kalaha spelas p� en spelplan best�ende av tolv sm� och tv� stora gropar. De sm�\n groparna kallas ambo och de stora kallas kalaha. Vid start ligger fyra kulor i varje\n ambo. Spelet g�r ut p� att samla s� m�nga av kulorna som m�jligt i sin egen kalaha.\n Kulorna i kalahan utg�r en spelares po�ng.\n\nSpelare A har amborna l�ngst ner och spelare B har amborna h�gst upp. Ett drag\n g�r till s� att en spelare tar upp alla kulor ur en av sina ambon och l�gger ut dem\n moturs en och en i efterf�ljande ambon. Om man n�r fram till sin egen kalaha, s�\n placerar man en kula �ven h�r och forts�tter �ver till motst�ndarens sida.\n Skulle man �ven n� fram till motst�ndarens kalaha s� l�gger man aldrig en kula d�r,\n utan man forts�tter placera ut kulorna p� sin egen sida.\n\nOm den sista kulan hamnar i en tom ambo p� ens egen sida, s� f�r man ta den kulan\n och alla kulor fr�n motst�ndarens motsatta grop och l�gga dem i sin kalaha. D�refter\n �r det motst�ndarens tur.\n\n Spelet �r slut n�r en spelare som �r vid draget inte kan utf�ra n�got drag, det vill\n s�ga alla hans ambon �r tomma. N�r spelet �r slut f�r man ta alla kulor som man\n eventuellt har kvar i amborna p� sin egen sida och l�gga dem i sin kalaha.", "Hj�lp", JOptionPane.INFORMATION_MESSAGE);
    		
    	}
    	else if (e.getSource() == btnStart){
			getPage("game");
			newGame();
    	}
    	else if (e.getSource() == btnOne){
    		move(0);
    	}
    	else if (e.getSource() == btnTwo){
    		move(1);
    	}
    	else if (e.getSource() == btnThree){
    		move(2);
    	}
    	else if (e.getSource() == btnFour){
    		move(3);
    	}
    	else if (e.getSource() == btnFive){
    		move(4);
    	}
    	else if (e.getSource() == btnSix){
    		move(5);
    	}
    	else if(e.getSource() == btnOK){
    		//CPUmove(random);
    		CPUmove(AIchoise);
    	}
    	else if (e.getSource() == btnEndGame){
    		System.exit(0);
    	}
    	else if (e.getSource() == btnNewGame){
    		getPage("start");
    	}
    }
    
	//Metoder--------------------------------------------------------------------------------------
	public void newGame(){
		
		String name = JOptionPane.showInputDialog("Spelarens namn: ");
	
		//Skapar nya spelare
		p = new Player(name);
    	String cpuName = "CPU";
    	cpu = new Player(cpuName);
    	lblGameText.setText(name + " spelar.");
    	updateScore(p.getScore(), cpu.getScore());
    	updatePots();
    	updateButtons();
	}
	
	public void roundPlayer(){
		updateScore(p.getScore(), cpu.getScore());
		boolean check = checkGameOver();
		if(check == false){
			lblMark.setVisible(false);
			btnOK.setVisible(false);
			updateButtons();
			lblGameText.setText(p.getName() + " spelar.");
		}
	}
	
	public void roundCPU(){
		updateScore(p.getScore(), cpu.getScore());
		boolean check = checkGameOver();
		if(check == false){
			closeButtons();
			AIchoise = getAIChoise();
			lblMark.setVisible(true);
			btnOK.setVisible(true);
			int choise = AIchoise + 1;
			getMarkPosition(choise);
			lblGameText.setText("CPU v�ljer ambo nr. " + choise);
		}		
	}
	
	public void move(int i){

		int noBeans = p.getBeans(i);
		
		for(int j = i+1; j<=noBeans+i; j++){
			
			if(j>6 && j<=12){ //om man har passerat sitt eget bo
				int beans1 = cpu.getBeans(j-7) + 1;
				cpu.pots[j-7].setNoBeans(beans1); 
			}
			else if(j>12){ //om man har paserat motst�ndarens bo
				int beans2 = p.getBeans(j-13) + 1;
				p.pots[j-13].setNoBeans(beans2);
			}
			else{
				int beans3 = p.getBeans(j) + 1;
				p.pots[j].setNoBeans(beans3);
			}	
		}
					
		int lastIndex = noBeans + i;
		
		if(lastIndex >= 0 && lastIndex <= 5){
			
			if(p.getBeans(lastIndex) == 1 && lastIndex != 6){ //Om man la sista kulan i en tom kruka som inte �r egna boet.
				int mainPotBeans = p.getBeans(6) + 1;
				p.pots[6].setNoBeans(mainPotBeans);
				p.pots[lastIndex].setNoBeans(0);
				transferBeans(lastIndex);
			}
		}
		else if(lastIndex >= 13 && lastIndex <= 18){
			
				if(p.getBeans(lastIndex-13) == 1 && (lastIndex-13) != 6){ //Om man la sista kulan i en tom kruka som inte �r egna boet.
				int mainPotBeans = p.getBeans(6) + 1;
				p.pots[6].setNoBeans(mainPotBeans);
				p.pots[lastIndex-13].setNoBeans(0);
				transferBeans(lastIndex-13);
			}
		}
		
		p.pots[i].setNoBeans(0);
		updatePots();
		
		if(lastIndex == 6){ //Om man avslutar i boet f�r man spela igen.
			roundPlayer();
		}
		else{
			roundCPU();
		}
	}
	
	public void CPUmove(int i){
		int noBeans = cpu.getBeans(i);
		
		for(int j = i+1; j<=noBeans+i; j++){
			
			if(j>6 && j<=12){ //om man har passerat sitt eget bo
				int beans1 = p.getBeans(j-7) + 1;
				p.pots[j-7].setNoBeans(beans1); 
			}
			else if(j>12){ //om man har paserat motst�ndarens bo
				int beans2 = cpu.getBeans(j-13) + 1;
				cpu.pots[j-13].setNoBeans(beans2);
			}
			else{
				int beans3 = cpu.getBeans(j) + 1;
				cpu.pots[j].setNoBeans(beans3);
			}	
		}
					
		int lastIndex = noBeans + i;
		
		if(lastIndex >= 0 && lastIndex <= 5){
			if(cpu.getBeans(lastIndex) == 1 && lastIndex != 6){ //Om man la sista kulan i en tom kruka som inte �r egna boet.
				int mainPotBeans = cpu.getBeans(6) + 1;
				cpu.pots[6].setNoBeans(mainPotBeans);
				cpu.pots[lastIndex].setNoBeans(0);
				transferBeansCPU(lastIndex);
			}
		}
		else if(lastIndex >= 13 && lastIndex <= 18){
				if(cpu.getBeans(lastIndex-13) == 1 && (lastIndex-13) != 6){ //Om man la sista kulan i en tom kruka som inte �r egna boet.
				int mainPotBeans = cpu.getBeans(6) + 1;
				cpu.pots[6].setNoBeans(mainPotBeans);
				cpu.pots[lastIndex-13].setNoBeans(0);
				transferBeansCPU(lastIndex-13);
			}
		}
		
		cpu.pots[i].setNoBeans(0);
		updatePots();
			
		if(lastIndex == 6){ //Om man avslutar i boet f�r man spela igen.
			roundCPU();
		}
		else{
			roundPlayer();
		}	
	}
	
	public void transferBeans(int i)nh{
		if(i == 0 || i == 13){
			int beans = cpu.getBeans(5);
			cpu.pots[5].setNoBeans(0);
			int mainPotBeans = p.getBeans(6) + beans;
			p.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 1 || i == 14){
			int beans = cpu.getBeans(4);
			cpu.pots[4].setNoBeans(0);
			int mainPotBeans = p.getBeans(6) + beans;
			p.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 2|| i== 15){
			int beans = cpu.getBeans(3);
			cpu.pots[3].setNoBeans(0);
			int mainPotBeans = p.getBeans(6) + beans;
			p.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 3 || i == 16){
			int beans = cpu.getBeans(2);
			cpu.pots[2].setNoBeans(0);
			int mainPotBeans = p.getBeans(6) + beans;
			p.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 4 || i == 17){
			int beans = cpu.getBeans(1);
			cpu.pots[1].setNoBeans(0);
			int mainPotBeans = p.getBeans(6) + beans;
			p.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 5|| i == 18){
			int beans = cpu.getBeans(0);
			cpu.pots[0].setNoBeans(0);
			int mainPotBeans = p.getBeans(6) + beans;
			p.pots[6].setNoBeans(mainPotBeans);
		}
		
	}
	
	public void transferBeansCPU(int i){
		if(i == 0 || i == 13){
			int beans = p.getBeans(5);
			p.pots[5].setNoBeans(0);
			int mainPotBeans = cpu.getBeans(6) + beans;
			cpu.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 1 || i == 14){
			int beans = p.getBeans(4);
			p.pots[4].setNoBeans(0);
			int mainPotBeans = cpu.getBeans(6) + beans;
			cpu.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 2|| i== 15){
			int beans = p.getBeans(3);
			p.pots[3].setNoBeans(0);
			int mainPotBeans = cpu.getBeans(6) + beans;
			cpu.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 3 || i == 16){
			int beans = p.getBeans(2);
			p.pots[2].setNoBeans(0);
			int mainPotBeans = cpu.getBeans(6) + beans;
			cpu.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 4 || i == 17){
			int beans = p.getBeans(1);
			p.pots[1].setNoBeans(0);
			int mainPotBeans = cpu.getBeans(6) + beans;
			cpu.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 5|| i == 18){
			int beans = p.getBeans(0);
			p.pots[0].setNoBeans(0);
			int mainPotBeans = cpu.getBeans(6) + beans;
			cpu.pots[6].setNoBeans(mainPotBeans);
		}	
	}
	
	public boolean checkGameOver(){
		if(p.getBeans(0) == 0 && p.getBeans(1) == 0 && p.getBeans(2) == 0 && p.getBeans(3) == 0 && p.getBeans(4) == 0 && p.getBeans(5) == 0){
			int beans = cpu.getBeans(6) + cpu.getBeans(5) + cpu.getBeans(4) + cpu.getBeans(3) + cpu.getBeans(2) + cpu.getBeans(1) + cpu.getBeans(0); 
			cpu.pots[6].setNoBeans(beans);
			updateScore(p.getScore(), cpu.getScore());
			setGameOver(p.getScore(), cpu.getScore());
			return true;
		}
		else if(cpu.getBeans(0) == 0 && cpu.getBeans(1) == 0 && cpu.getBeans(2) == 0 && cpu.getBeans(3) == 0 && cpu.getBeans(4) == 0 && cpu.getBeans(5) == 0){
			int beans = p.getBeans(6) + p.getBeans(5) + p.getBeans(4) + p.getBeans(3) + p.getBeans(2) + p.getBeans(1) + p.getBeans(0); 
			p.pots[6].setNoBeans(beans);
			updateScore(p.getScore(), cpu.getScore());
			setGameOver(p.getScore(), cpu.getScore());
			return true;
		}
		else{
			return false;
		}
	}
	
	public void setGameOver(int a, int b){
		if(a > b){
			getPage("gameOverVictory");
		}
		else if(b > a){
			getPage("gameOverDefeat");
		}
		else if(b == a){
			getPage("gameOverDraw");
		}
	}
	
	public void getMarkPosition(int i){
		if(i == 6){
			lblMark.setBounds(181,46,80,80);
		}
		else if(i == 5){
			lblMark.setBounds(253,46,80,80);
		}
		else if(i == 4){
			lblMark.setBounds(325,46,80,80);
		}
		else if(i == 3){
			lblMark.setBounds(398,46,80,80);
		}
		else if(i == 2){
			lblMark.setBounds(469,46,80,80);
		}
		else if(i == 1){
			lblMark.setBounds(540,46,80,80);
		}
	}
	
	public void updatePots(){
		
		pot1.setText(String.valueOf(p.getBeans(0)));
		pot2.setText(String.valueOf(p.getBeans(1)));
		pot3.setText(String.valueOf(p.getBeans(2)));
		pot4.setText(String.valueOf(p.getBeans(3)));
		pot5.setText(String.valueOf(p.getBeans(4)));
		pot6.setText(String.valueOf(p.getBeans(5)));
		mainPot.setText(String.valueOf(p.getBeans(6)));
		
		CPUpot1.setText(String.valueOf(cpu.getBeans(0)));
		CPUpot2.setText(String.valueOf(cpu.getBeans(1)));
		CPUpot3.setText(String.valueOf(cpu.getBeans(2)));
		CPUpot4.setText(String.valueOf(cpu.getBeans(3)));
		CPUpot5.setText(String.valueOf(cpu.getBeans(4)));
		CPUpot6.setText(String.valueOf(cpu.getBeans(5)));
		mainPotCPU.setText(String.valueOf(cpu.getBeans(6))); 
			
	}
	
	public void updateScore(int scorePlayer, int scoreCPU){
		lblPlayerPoints.setText(p.getName() + ": " + scorePlayer);
		lblCPUPoints.setText(cpu.getName() + ": " + scoreCPU);
	}
	
	//AI-metoder------------------------------------------------------

	public int getAIChoise(){
		
		/*for(int i=0; i<pots.length; i++){
			if(pots[i].getNoBeans() == i+1){
				return i;
			}
		}
		if(pots[0].getNoBeans() == 6){
			return 0;
		}
		else if(pots[1].getNoBeans() == 5){
			return 1;
		}
		else if(pots[2].getNoBeans() == 4){
			return 2;
		}
		else if(pots[3].getNoBeans() == 3){
			return 3;
		}
		else if(pots[4].getNoBeans() == 2){
			return 4;
		}
		else if(pots[5].getNoBeans() == 1){
			return 5;
		}
		else{
			return getRandom();
		}*/
		
		temp = new Player("temp");
		tempCPU = new Player("tempCPU");
		getSettings();
		int choise = countScore();
		return choise;
		
	}
	
	public void getSettings(){
		for(int i=0; i<6; i++){
			int beans = p.getBeans(i);
			temp.pots[i].setNoBeans(beans);
			int beansCPU = cpu.getBeans(i);
			tempCPU.pots[i].setNoBeans(beansCPU);
		}
	}
	
	public int countScore(){ //Game search
		int[][] score = new int[6][6];
		for(int i=0; i<6; i++){
			for(int j=0; j<6; j++){
					score[i][j] = getAIScore(i,j);
			}
		}
		//H�r kommer sj�lva "minmax procedure"
		int potChoise = minmax(score);
		return potChoise;
	}
	
	public int minmax(int[][] s){
		int tempMin;
		int tempMax;
		int index = 0; //s�tter till ett litet tal, som kommer bli �verskrivet
		int max = -100;
		int[] minlist = new int[6];
		
		for(int j=0; j<minlist.length; j++){ //Fyller minlistan med h�ga v�rden som sen ska skrivas �ver.
			minlist[j] = 100;
		}
		
		for(int i=0; i<6; i++){ //Tar fram de minsta v�rdena ur varje kolonn, minimize score
			for(int j=0; j<6; j++){
				tempMin = s[i][j];
				if(tempMin <= minlist[i]){
					minlist[i] = tempMin;
				}
			}
		}
		
		for(int k=0; k<minlist.length; k++){//Tar fram det st�rsta v�rdet av de som tagits fram, maximize score. Om det �r samma score v�ljs krukan med h�gst index.
			tempMax = minlist[k];
			if(tempMax >= max){
				max = tempMax;
				index = k;
			}
		}
	
		return index; 
	}
	
	public int getAIScore(int i, int j){
		
		getSettings();
		int score, score2, finalScore;
		int subtracter = tempCPU.getBeans(6);
		int noBeans = tempCPU.getBeans(i);
		int noBeans2 = temp.getBeans(j);
		
		
		//Ber�knar f�r CPU...
		if(noBeans != 0 && noBeans2 != 0){
			
			for(int k = i+1; k<=noBeans+i; k++){
			
			if(k>6 && k<=12){ //om man har passerat sitt eget bo
				int beans1 = temp.getBeans(k-7) + 1;
				temp.pots[k-7].setNoBeans(beans1); 
			}
			else if(k>12){ //om man har paserat motst�ndarens bo
				int beans2 = tempCPU.getBeans(k-13) + 1;
				tempCPU.pots[k-13].setNoBeans(beans2);
			}
			else{
				int beans3 = tempCPU.getBeans(k) + 1;
				tempCPU.pots[k].setNoBeans(beans3);
			}		
			}
						
		int lastIndex = noBeans + i;
		
		if(lastIndex >= 0 && lastIndex <= 5){
			
			if(tempCPU.getBeans(lastIndex) == 1 && lastIndex != 6){ //Om man la sista kulan i en tom kruka som inte �r egna boet.
				int mainPotBeans = tempCPU.getBeans(6) + 1;
				tempCPU.pots[6].setNoBeans(mainPotBeans);
				tempCPU.pots[lastIndex].setNoBeans(0);
				transferBeansAI(lastIndex);
			}
		}
		else if(lastIndex >= 13 && lastIndex <= 18){
			
				if(tempCPU.getBeans(lastIndex-13) == 1 && (lastIndex-13) != 6){ //Om man la sista kulan i en tom kruka som inte �r egna boet.
				int mainPotBeans = tempCPU.getBeans(6) + 1;
				tempCPU.pots[6].setNoBeans(mainPotBeans);
				tempCPU.pots[lastIndex-13].setNoBeans(0);
				transferBeansAI(lastIndex-13);
			}
		}
		
		
		tempCPU.pots[i].setNoBeans(0);
		score = tempCPU.getBeans(6) - subtracter; //Score efter f�rsta valet.
		if(lastIndex == 6){ //Om man avslutar i boet blir scoren v�rd lite extra (4 po�ng)
			score = score + 4;
		}
		
		noBeans2 = temp.getBeans(j);
		int subtracter2 = temp.getBeans(6);
		
		//Ber�knar f�r Player...
		for(int k = j+1; k<=noBeans2+j; k++){
			
			if(k>6 && k<=12){ //om man har passerat sitt eget bo
				int beans1 = tempCPU.getBeans(k-7) + 1;
				tempCPU.pots[k-7].setNoBeans(beans1); 
			}
			else if(k>12){ //om man har paserat motst�ndarens bo
				int beans2 = temp.getBeans(k-13) + 1;
				temp.pots[k-13].setNoBeans(beans2);
			}
			else{
				int beans3 = temp.getBeans(k) + 1;
				temp.pots[k].setNoBeans(beans3);
			}	
		}
					
		int lastIndex2 = noBeans2 + j;
		
		if(lastIndex2 >= 0 && lastIndex2 <= 5){
			
			if(temp.getBeans(lastIndex2) == 1 && lastIndex2 != 6){ //Om man la sista kulan i en tom kruka som inte �r egna boet.
				int mainPotBeans = temp.getBeans(6) + 1;
				temp.pots[6].setNoBeans(mainPotBeans);
				temp.pots[lastIndex2].setNoBeans(0);
				transferBeansAIPlayer(lastIndex2);//M�ste fixa transfterbansgrejjerna s� att de blir specifika f�r AI...
			}
		}
		else if(lastIndex2 >= 13 && lastIndex2 <= 18){
			
				if(temp.getBeans(lastIndex2-13) == 1 && (lastIndex2-13) != 6){ //Om man la sista kulan i en tom kruka som inte �r egna boet.
				int mainPotBeans = temp.getBeans(6) + 1;
				temp.pots[6].setNoBeans(mainPotBeans);
				temp.pots[lastIndex2-13].setNoBeans(0);
				transferBeansAIPlayer(lastIndex2-13);
			}
		}
		
		score2 = temp.getBeans(6) - subtracter2; //Score efter f�rsta valet.
		if(lastIndex2 == 6){ //Om man avslutar i boet blir scoren v�rd lite extra (4 po�ng)
			score2 = score2 + 4;
		}
		
		//H�r ber�knas sj�lva score:en...
		finalScore = score - score2;
		return finalScore;
		
	}
	else if(noBeans == 0){
		return -999;
	}
	else{
		return 999;
	}	
	}
	
		public void transferBeansAI(int i){
		if(i == 0 || i == 13){
			int beans = temp.getBeans(5);
			temp.pots[5].setNoBeans(0);
			int mainPotBeans = tempCPU.getBeans(6) + beans;
			tempCPU.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 1 || i == 14){
			int beans = temp.getBeans(4);
			temp.pots[4].setNoBeans(0);
			int mainPotBeans = tempCPU.getBeans(6) + beans;
			tempCPU.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 2|| i== 15){
			int beans = temp.getBeans(3);
			temp.pots[3].setNoBeans(0);
			int mainPotBeans = tempCPU.getBeans(6) + beans;
			tempCPU.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 3 || i == 16){
			int beans = temp.getBeans(2);
			temp.pots[2].setNoBeans(0);
			int mainPotBeans = tempCPU.getBeans(6) + beans;
			tempCPU.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 4 || i == 17){
			int beans = temp.getBeans(1);
			temp.pots[1].setNoBeans(0);
			int mainPotBeans = tempCPU.getBeans(6) + beans;
			tempCPU.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 5|| i == 18){
			int beans = temp.getBeans(0);
			temp.pots[0].setNoBeans(0);
			int mainPotBeans = tempCPU.getBeans(6) + beans;
			tempCPU.pots[6].setNoBeans(mainPotBeans);
		}
		
	}
	
	public void transferBeansAIPlayer(int i){
		if(i == 0 || i == 13){
			int beans = tempCPU.getBeans(5);
			tempCPU.pots[5].setNoBeans(0);
			int mainPotBeans = temp.getBeans(6) + beans;
			temp.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 1 || i == 14){
			int beans = tempCPU.getBeans(4);
			tempCPU.pots[4].setNoBeans(0);
			int mainPotBeans = temp.getBeans(6) + beans;
			temp.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 2|| i== 15){
			int beans = tempCPU.getBeans(3);
			tempCPU.pots[3].setNoBeans(0);
			int mainPotBeans = temp.getBeans(6) + beans;
			temp.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 3 || i == 16){
			int beans = tempCPU.getBeans(2);
			tempCPU.pots[2].setNoBeans(0);
			int mainPotBeans = temp.getBeans(6) + beans;
			temp.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 4 || i == 17){
			int beans = tempCPU.getBeans(1);
			tempCPU.pots[1].setNoBeans(0);
			int mainPotBeans = temp.getBeans(6) + beans;
			temp.pots[6].setNoBeans(mainPotBeans);
		}
		else if(i == 5|| i == 18){
			int beans = tempCPU.getBeans(0);
			tempCPU.pots[0].setNoBeans(0);
			int mainPotBeans = temp.getBeans(6) + beans;
			temp.pots[6].setNoBeans(mainPotBeans);
		}
		
	}

	/*--Slumpmetoder som inte anv�nds i slutprogrammet--
	public int getRandom(){
		int random = randomize(); //Slumpat tal mellan 0 och 5.
		while(cpu.getBeans(random) == 0){
			random = randomize();
		}
		return random;
	}
	
	public int randomize(){
		return (int)(Math.random()*6);
	}
	*/
    
	
	//Uppdateringsmetoder-----------------------------------------------------
	public void updateButtons(){
		
    	btnOne.setEnabled(true);
    	btnTwo.setEnabled(true);
    	btnThree.setEnabled(true);
    	btnFour.setEnabled(true);
    	btnFive.setEnabled(true);
   		btnSix.setEnabled(true);		
		
		for(int i=0; i<6; i++){
			if(p.getBeans(i) == 0){
				setButton(i);
			}
		}
    		
	}
	
	public void closeButtons(){
		btnOne.setEnabled(false);
    	btnTwo.setEnabled(false);
    	btnThree.setEnabled(false);
    	btnFour.setEnabled(false);
    	btnFive.setEnabled(false);
   		btnSix.setEnabled(false);
	}
	
	public void setButton(int i){
		if(i==0){
			btnOne.setEnabled(false);
		}
		else if (i==1){
			btnTwo.setEnabled(false);
		}
		else if (i==2){
			btnThree.setEnabled(false);
		}
		else if (i==3){
			btnFour.setEnabled(false);
		}
		else if (i==4){
			btnFive.setEnabled(false);
		}
		else if (i==5){
			btnSix.setEnabled(false);
		}
	}
	
	public void invicible(){
		
		lblStartScreen.setVisible(false);
    	lblBoard.setVisible(false);
    	lblCPUPoints.setVisible(false);
    	lblPlayerPoints.setVisible(false);
    	lblGameOverVictory.setVisible(false);
    	lblGameOverDefeat.setVisible(false);
    	lblGameOverDraw.setVisible(false);
    	lblGameText.setVisible(false);
    	lblMark.setVisible(false);
    	lblBackground.setVisible(false);
    	pot1.setVisible(false);
    	pot2.setVisible(false);
    	pot3.setVisible(false);
    	pot4.setVisible(false);
    	pot5.setVisible(false);
    	pot6.setVisible(false);
    	CPUpot1.setVisible(false);
    	CPUpot2.setVisible(false);
    	CPUpot3.setVisible(false);
    	CPUpot4.setVisible(false);
    	CPUpot5.setVisible(false);
    	CPUpot6.setVisible(false);
    	mainPot.setVisible(false);
    	mainPotCPU.setVisible(false);
    	btnOK.setVisible(false);
    	btnStart.setVisible(false);
    	btnOne.setVisible(false);
    	btnTwo.setVisible(false);
    	btnThree.setVisible(false);
    	btnFour.setVisible(false);
    	btnFive.setVisible(false);
    	btnSix.setVisible(false);
    	btnNewGame.setVisible(false);
    	btnEndGame.setVisible(false);
	}
	
	public void getPage(String s){
		
		if (s == "start"){
			invicible();
			lblStartScreen.setVisible(true);
			btnStart.setVisible(true);
		}
		else if (s == "game"){
			invicible();
			lblBackground.setVisible(true);
			lblBoard.setVisible(true);
			lblPlayerPoints.setVisible(true);
			lblCPUPoints.setVisible(true);
			lblGameText.setVisible(true);
			pot1.setVisible(true);
			pot2.setVisible(true);
			pot3.setVisible(true);
			pot4.setVisible(true);
			pot5.setVisible(true);
			pot6.setVisible(true);
			CPUpot1.setVisible(true);
			CPUpot2.setVisible(true);
			CPUpot3.setVisible(true);
			CPUpot4.setVisible(true);
			CPUpot5.setVisible(true);
			CPUpot6.setVisible(true);
			mainPot.setVisible(true);
			mainPotCPU.setVisible(true);
			btnOne.setVisible(true);
			btnTwo.setVisible(true);
			btnThree.setVisible(true);
			btnFour.setVisible(true);
			btnFive.setVisible(true);
			btnSix.setVisible(true);
		}
		else if (s == "gameOverVictory"){
			invicible();
			lblGameOverVictory.setVisible(true);
			btnNewGame.setVisible(true);
			btnEndGame.setVisible(true);
			lblCPUPoints.setVisible(true);
    		lblPlayerPoints.setVisible(true);
		}
		else if(s == "gameOverDefeat"){
			invicible();
			lblGameOverDefeat.setVisible(true);
			btnNewGame.setVisible(true);
			btnEndGame.setVisible(true);
			lblCPUPoints.setVisible(true);
    		lblPlayerPoints.setVisible(true);
		}
		else if(s == "gameOverDraw"){
			invicible();
			lblGameOverDraw.setVisible(true);
			btnNewGame.setVisible(true);
			btnEndGame.setVisible(true);
			lblCPUPoints.setVisible(true);
    		lblPlayerPoints.setVisible(true);
		}
			
			
	}    
//Programanrop---------------------------------------------------------------------------------
public static void main(String[] args) {
	KalahaAI k = new KalahaAI();
}
}    
    
