/**
 * Client class will connect to server class created and will take server input 
 * CLient will also create the buttons and layout of the game
 * Client will display if user wins,loses or ties, displays the score of user and computer
 * Client will set images to the buttons
 * Client will take input from the user and computer and use that to determine win conditions
 */
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import javax.swing.*;

public class Client extends JFrame implements ActionListener{
	
	/** fire Button */
	private JButton fireButt;
	/** water Button */
	private JButton waterButt;
	/**grass Button*/
	private JButton grassButt;
	/** quit Button */
	private JButton quitButt;
	/**menu display button*/
	private JButton menuButt;
	/**player input */
	private String playerInput;
	/** fire attack from user */
	private JLabel userFireAtk;
	/** water attack from user */
	private  JLabel userWaterAtk;
	/**grass attack from user*/
	private JLabel userGrassAtk;
	/**fire attack from computer */
	private JLabel compFireAtk;
	/**water attack from computer*/
	private JLabel compWaterAtk;
	/**grass attack from computer*/
	private JLabel compGrassAtk;
	/**score of user*/
	private JLabel scoreUser;
	/**score of computer*/
	private JLabel scoreComp;
	/**user win speech*/
	private JLabel userWin;
	/**user lose speech*/
	private JLabel userLose;
	/**user tie speech*/
	private JLabel userTie;
	/**delcaration of of printstream*/
	private PrintStream out;
	/**declaration of bufferedreader*/
	private BufferedReader in;
	/**counter to keep score of user*/
	private int counterUser = 0;
	/**counter to keep score of computer*/
	private int counterComp = 0;
	/**set done to true for a while loop*/
	private boolean done = true;
	
	/**
	 * Constructor for the client class where we will make a frame of 1000x1000
	 * We'll create 4 buttons, fire, water, grass and a quit and add listeners to get respond to the mouse click
	 * we will then take the images and scale them down to fit within the buttons and set the buttons to the images
	 * We will then generate the text using JLabels for win,lose,tie,attack,computer attack and set the bounds in the right places
	 * Set all text to false to not show unless function calls for it to be true and add them to panel and then we will display from the Panel
	 */
	public Client()
	{
		
		setBounds(0,0,1000,1000);
		setTitle("FIRE WATER GRASS GAME");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		fireButt = new JButton();
		fireButt.setBounds(100, 100, 200, 200);
		waterButt = new JButton();
		waterButt.setBounds(400,100, 200, 200);
		grassButt = new JButton();
		grassButt.setBounds(700,100, 200, 200);
		quitButt = new JButton();
		quitButt.setBounds(0, 600, 100, 100);
		menuButt = new JButton();
		menuButt.setBounds(250,400,500,200);
		
		fireButt.addActionListener(this);
		waterButt.addActionListener(this);
		grassButt.addActionListener(this);
		quitButt.addActionListener(this);
		
		ImageIcon fireIcon = new ImageIcon("fireemo.png");
		Image scaleFire = fireIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		fireIcon = new ImageIcon(scaleFire);
		
		ImageIcon waterIcon = new ImageIcon("wateremo.jpg");
		Image scaleWater = waterIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		waterIcon = new ImageIcon(scaleWater);
		
		ImageIcon grassIcon = new ImageIcon("grassemo.png");
		Image scaleGrass = grassIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		grassIcon = new ImageIcon(scaleGrass);
		
		ImageIcon quitIcon = new ImageIcon("quit.jpg");
		Image scaleQuit = quitIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		quitIcon = new ImageIcon(scaleQuit);
		
		fireButt.setIcon(fireIcon);
		waterButt.setIcon(waterIcon);
		grassButt.setIcon(grassIcon);
		quitButt.setIcon(quitIcon);
		
		userWin = new JLabel("You win!");
		userLose = new JLabel("You lose!");
		userTie = new JLabel("You tied!");
		
		scoreUser = new JLabel();
		scoreComp = new JLabel();
		userFireAtk = new JLabel("You used Fire!");
		userWaterAtk = new JLabel("You used Water!");
		userGrassAtk = new JLabel("You used Grass!");
		
		compFireAtk = new JLabel("Computer used Fire!");
		compWaterAtk = new JLabel("Computer used Water!");
		compGrassAtk = new JLabel("Computer used Grass!");
		
		userWin.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		userLose.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		userTie.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		scoreUser.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		scoreComp.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		userFireAtk.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		userWaterAtk.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		userGrassAtk.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		compFireAtk.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		compWaterAtk.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		compGrassAtk.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		
		userWin.setBounds(430, 400, 500, 200);
		userLose.setBounds(430, 400, 500, 200);
		userTie.setBounds(430, 400, 500, 200);
		
		scoreUser.setBounds(300, 400, 500, 200);
		scoreComp.setBounds(300, 420, 500, 200);
		
		userFireAtk.setBounds(430, 370, 500, 200);
		userWaterAtk.setBounds(430, 370, 500, 200);
		userGrassAtk.setBounds(430, 370, 500, 200);
		
		compFireAtk.setBounds(430, 385, 500, 200);
		compWaterAtk.setBounds(430, 385, 500, 200);
		compGrassAtk.setBounds(430, 385, 500, 200);
		
		userWin.setVisible(false);
		userLose.setVisible(false);
		userTie.setVisible(false);
		
		scoreUser.setVisible(false);
		scoreComp.setVisible(false);
		
		userFireAtk.setVisible(false);
		userWaterAtk.setVisible(false);
		userGrassAtk.setVisible(false);
		
		compFireAtk.setVisible(false);
		compWaterAtk.setVisible(false);
		compGrassAtk.setVisible(false);
		
				
		panel.add(fireButt);
		panel.add(waterButt);
		panel.add(grassButt);
		panel.add(quitButt);
		panel.add(userFireAtk);
		panel.add(userWaterAtk);
		panel.add(userGrassAtk);
		panel.add(compFireAtk);
		panel.add(compWaterAtk);
		panel.add(compGrassAtk);
		scoreUser.setText("User Score: "+ counterUser);
		scoreComp.setText("Computer Score:  " + counterComp);
		panel.add(scoreComp);
		panel.add(scoreUser);
		panel.add(userWin);
		panel.add(userLose);
		panel.add(userTie);
		panel.add(menuButt);
	

		getContentPane().add(panel);
		
		try
		{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setVisible(true);
			boolean loop = true;
			Socket sock = new Socket("localhost", 1235);
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintStream(sock.getOutputStream());
			while(loop)
			{
				String compInput = in.readLine();
				getWin(playerInput, compInput);
	
			}

		}
		
		catch(IOException e)
		{
			System.out.println("Error file.");
		}
	
		
		
	}
	
	/**
	 * Main function will create a new client and to connect to the server and to display the client. 
	 * Function will also read the prediction from the server side and use the method getWin to determine if the user or computer wins
	 */
	public static void main(String[] args)
	{
		new Client();
		
	}
	
	/**
	 * Function getWin will take the choice of the user and take the prediction of the computer
	 * Function will compare if User chooses F,W,G and Comp chooses W,G,F in order to determine that user wins
	 * If input matches the case then we set attack speeches and win speeches to true and increment score of user by 1
	 * Next Function will compare if User and Computer chooses the same F,W,G and will mean that it is a tie
	 * If input matches the case then we set attack speech and tie speeches to true and keep scores the same
	 * Next Function will then know that the user has lost and computer has made the correct prediction to win
	 * If input matches the case then we set attack speeches and lose speeches to true and increment computer score by 1
	 * @param choice - will take the userInput
	 * @param computerCheck - will take the computer prediction
	 */
	public void getWin(String choice, String computerCheck)
	{
		if(choice.equals("F") && computerCheck.equals("G"))
		{
			userFireAtk.setVisible(true);
			userWaterAtk.setVisible(false);
			userGrassAtk.setVisible(false);
			compGrassAtk.setVisible(true);
			compWaterAtk.setVisible(false);
			compFireAtk.setVisible(false);
			scoreUser.setText("User Score: "+ ++counterUser);
			scoreComp.setText("Computer Score:  " + counterComp);
			scoreUser.setVisible(true);
			scoreComp.setVisible(true);
			userWin.setVisible(true);
			userLose.setVisible(false);
			userTie.setVisible(false);
		}
			
		else if(choice.equals("W") && computerCheck.equals("F"))
		{
			userWaterAtk.setVisible(true);
			userFireAtk.setVisible(false);
			userGrassAtk.setVisible(false);
			compFireAtk.setVisible(true);
			compWaterAtk.setVisible(false);
			compGrassAtk.setVisible(false);
			scoreUser.setText("User Score: "+ ++counterUser);
			scoreComp.setText("Computer Score:  " + counterComp);
			scoreUser.setVisible(true);
			scoreComp.setVisible(true);
			userWin.setVisible(true);
			userLose.setVisible(false);
			userTie.setVisible(false);
		}
			
		else if( choice.equals("G") && computerCheck.equals("W"))
		{
			userGrassAtk.setVisible(true);
			userFireAtk.setVisible(false);
			userWaterAtk.setVisible(false);
			compWaterAtk.setVisible(true);
			compFireAtk.setVisible(false);
			compGrassAtk.setVisible(false);
			scoreUser.setText("User Score: "+ ++counterUser);
			scoreComp.setText("Computer Score:  " + counterComp);
			scoreUser.setVisible(true);
			scoreComp.setVisible(true);
			userWin.setVisible(true);
			userLose.setVisible(false);
			userTie.setVisible(false);
		}
			
		else if(choice.equals("F") && computerCheck.equals("F") || choice.equals("W") && computerCheck.equals("W")  || choice.equals("G") && computerCheck.equals("G")) 
		{
			if(choice.equals("F") && computerCheck.equals("F"))
			{
				userFireAtk.setVisible(true);
				userWaterAtk.setVisible(false);
				userGrassAtk.setVisible(false);
				compFireAtk.setVisible(true);
				compWaterAtk.setVisible(false);
				compGrassAtk.setVisible(false);
				scoreUser.setText("User Score: "+ counterUser);
				scoreComp.setText("Computer Score:  " + counterComp);
				scoreUser.setVisible(true);
				scoreComp.setVisible(true);
				userWin.setVisible(false);
				userLose.setVisible(false);
				userTie.setVisible(true);
			}
			
			else if( choice.equals("W") && computerCheck.equals("W"))
			{
				userWaterAtk.setVisible(true);
				userFireAtk.setVisible(false);
				userGrassAtk.setVisible(false);
				compWaterAtk.setVisible(true);
				compFireAtk.setVisible(false);
				compGrassAtk.setVisible(false);
				scoreUser.setText("User Score: "+ counterUser);
				scoreComp.setText("Computer Score:  " + counterComp);
				scoreUser.setVisible(true);
				scoreComp.setVisible(true);
				userWin.setVisible(false);
				userLose.setVisible(false);
				userTie.setVisible(true);
			}
			
			else if(choice.equals("G") && computerCheck.equals("G"))
			{
				userGrassAtk.setVisible(true);
				userWaterAtk.setVisible(false);
				userFireAtk.setVisible(false);
				compGrassAtk.setVisible(true);
				compWaterAtk.setVisible(false);
				compFireAtk.setVisible(false);
				scoreUser.setText("User Score: "+ counterUser);
				scoreComp.setText("Computer Score:  " + counterComp);
				scoreUser.setVisible(true);
				scoreComp.setVisible(true);
				userWin.setVisible(false);
				userLose.setVisible(false);
				userTie.setVisible(true);
			}
		}
			
		else //(choice == 1 && computerCheck == "W" || choice == 2 && computerCheck == "G" || choice == 3 && computerCheck == "F")
		{
			if(choice.equals("F"))
			{
				userFireAtk.setVisible(true);
				userWaterAtk.setVisible(false);
				userGrassAtk.setVisible(false);
				compWaterAtk.setVisible(true);
				compFireAtk.setVisible(false);
				compGrassAtk.setVisible(false);
				scoreUser.setText("User Score: "+ counterUser);
				scoreComp.setText("Computer Score:  " + ++counterComp);
				scoreUser.setVisible(true);
				scoreComp.setVisible(true);
				userWin.setVisible(false);
				userLose.setVisible(true);
				userTie.setVisible(false);
				
			}
			else if(choice.equals("W"))
			{
				userWaterAtk.setVisible(true);
				userFireAtk.setVisible(false);
				userGrassAtk.setVisible(false);
				compGrassAtk.setVisible(true);
				compWaterAtk.setVisible(false);
				compFireAtk.setVisible(false);
				scoreUser.setText("User Score: "+ counterUser);
				scoreComp.setText("Computer Score:  " + ++counterComp);
				scoreUser.setVisible(true);
				scoreComp.setVisible(true);
				userWin.setVisible(false);
				userLose.setVisible(true);
				userTie.setVisible(false);
			}
			else if(choice.equals("G")){
				userGrassAtk.setVisible(true);
				userWaterAtk.setVisible(false);
				userFireAtk.setVisible(false);
				compFireAtk.setVisible(true);
				compWaterAtk.setVisible(false);
				compGrassAtk.setVisible(false);
				scoreUser.setText("User Score: "+ counterUser);
				scoreComp.setText("Computer Score:  " + ++counterComp);
				scoreUser.setVisible(true);
				scoreComp.setVisible(true);
				userWin.setVisible(false);
				userLose.setVisible(true);
				userTie.setVisible(false);
			}
		}
	}

	/**
	 * Function arction performed will take what the user clicks and sets the input to be F,W or G and will take this input and send it to the server
	 * server will take input in order to determine computer prediction
	 * @param e - what the user clicks
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == fireButt)
		{
			playerInput = "F";
			out.println(playerInput);
			//getWin(playerInput,response);
		}
		
		if(e.getSource() == waterButt)
		{
			playerInput = "W";
			out.println(playerInput);
			//getWin(playerInput,response);
		}
		
		if(e.getSource() == grassButt)
		{
			playerInput = "G";
			out.println(playerInput);
			//getWin(playerInput,response);
		}
		
		if(e.getSource() == quitButt)
		{
			playerInput = "Q";
			out.println(playerInput);
		}
		
	}

}
