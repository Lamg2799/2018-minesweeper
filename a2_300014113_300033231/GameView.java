import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out an instance of  <b>BoardView</b> (the actual game) and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Luke Germond
 * @author Kevin Jia
 */

public class GameView extends JFrame {
  
  // ADD YOUR INSTANCE VARIABLES HERE
  /**
   * represents the array of squares (DotButtons) on the view
   */
  private DotButton[][] board;
  /**
   * An instance of the GameModel class
   */
  private GameModel gameModel;
  /**
   * A label displayed that shows the number of steps
   */
  private JLabel nbreOfStepsLabel;
  
  /**
   * Constructor used for initializing the Frame
   * 
   * @param gameModel
   *            the model of the game (already initialized)
   * @param gameController
   *            the controller
   */
  
  public GameView(GameModel gameModel, GameController gameController) {
    
    // ADD YOU CODE HERE
    
    
    JFrame mineFrame = new JFrame();
    mineFrame.setResizable(false);
    mineFrame.setTitle("MineSweeper it -- the ITI 1121 version");
    mineFrame.setLocationRelativeTo(null);
    mineFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    JPanel minePanel = new JPanel();
    minePanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
    minePanel.setLayout(new GridLayout(gameModel.getWidth(),gameModel.getHeigth()));
    this.gameModel = gameModel;
    board = new DotButton[gameModel.getWidth()][gameModel.getHeigth()]; //updated
    for(int i=0;i<gameModel.getWidth();i++){
      for(int j=0;j<gameModel.getHeigth();j++){
        board[i][j] = new DotButton(i,j,11); //set all squares to be covered
        board[i][j].setBorder(null);
        board[i][j].addActionListener(gameController);
        minePanel.add(board[i][j]);
      }}
    mineFrame.add(minePanel,BorderLayout.CENTER);
    JPanel other = new JPanel();
    other.setPreferredSize(new Dimension(200,50));
    mineFrame.add(other,BorderLayout.SOUTH);
    nbreOfStepsLabel = new JLabel("Number of steps: "+gameModel.getNumberOfSteps());
    other.add(nbreOfStepsLabel);
    JButton reset;
    reset = new JButton("reset");
    reset.addActionListener(gameController);
    other.add(reset);
    JButton quit;
    quit = new JButton("quit");
    quit.addActionListener(gameController);
    other.add(quit);
    mineFrame.pack();
    mineFrame.setVisible(true);
    
    
  }
  
  
  
  /**
   * update the status of the board's DotButton instances based 
   * on the current game model, then redraws the view
   */
  
  public void update(){
    // ADD YOU CODE HERE
    int count = 0;
    for(int i=0;i<gameModel.getWidth();i++){
      for(int j=0;j<gameModel.getHeigth();j++){
        if(gameModel.get(i,j).isCovered() == false){
          if(gameModel.get(i,j).isMined() == true && gameModel.get(i,j).hasBeenClicked() == true && count==0){
            board[i][j].setIconNumber(10);
            count++;
          }
          else if(gameModel.get(i,j).isMined() == true){
            board[i][j].setIconNumber(9);
          }else{
            int neigh = getIcon(i,j);
            board[i][j].setIconNumber(neigh);
          }
        }else{
          board[i][j].setIconNumber(11);
        }}}
    nbreOfStepsLabel.setText("Number of steps: "+gameModel.getNumberOfSteps());
    
    repaint();
    
  }
  
  /**
   * returns the icon value that must be used for a given dot 
   * in the game
   * 
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   * @return the icon to use for the dot at location (i,j)
   */   
  private int getIcon(int i, int j){
    
    // ADD YOU CODE HERE
    return gameModel.get(i,j).getNeighbooringMines();
    
    
  }
  
  
}
