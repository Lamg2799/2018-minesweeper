import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.LinkedList;
import java.awt.*;
import javax.swing.*;


/**
 * The class <b>GameController</b> is the controller of the game. It is a listener
 * of the view, and has a method <b>play</b> which computes the next
 * step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Luke Germond
 * @author Kevin Jia
 */


public class GameController implements ActionListener {
  
  // ADD YOUR INSTANCE VARIABLES HERE
  /**
   * Instance of the GameModel class
   */
  private GameModel gameModel;
  /**
   * Instance of the GameView class
   */
  private GameView gameView;
  
  /**
   * Constructor used for initializing the controller. It creates the game's view 
   * and the game's model instances
   * 
   * @param width
   *            the width of the board on which the game will be played
   * @param height
   *            the height of the board on which the game will be played
   * @param numberOfMines
   *            the number of mines hidden in the board
   */
  public GameController(int width, int height, int numberOfMines) {
    
    // ADD YOU CODE HERE
    
    gameModel = new GameModel(width,height,numberOfMines);
    gameView = new GameView(gameModel,this);
    
  }
  
  
  /**
   * Callback used when the user clicks a button (reset or quit)
   *
   * @param e
   *            the ActionEvent
   */
  
  public void actionPerformed(ActionEvent e) {
    
    // ADD YOU CODE HERE
    String name = ((JButton)e.getSource()).getActionCommand().toString();
    if(name.equals("reset") || name.equals("Play again") ){
      reset();
      if(name.equals("Play again")){
      }
    }else if(name.equals("quit")){
      System.exit(0);
    }else{
      DotButton button = (DotButton) e.getSource();
      int y = button.getRow();
      int x = button.getColumn();
      if(gameModel.get(x,y).hasBeenClicked() == false){
      play(x,y);
      }}}
 
      
      
  /**
   * resets the game
   */
  private void reset(){
    
    // ADD YOU CODE HERE
    gameModel.reset();
    gameView.update();
    
  }
  
  /**
   * <b>play</b> is the method called when the user clicks on a square.
   * If that square is not already clicked, then it applies the logic
   * of the game to uncover that square, and possibly end the game if
   * that square was mined, or possibly uncover some other squares. 
   * It then checks if the game
   * is finished, and if so, congratulates the player, showing the number of
   * moves, and gives to options: start a new game, or exit
   * @param width
   *            the selected column
   * @param heigth
   *            the selected line
   */
  private void play(int width, int heigth){
    
    // ADD YOU CODE HERE
    if(gameModel.get(width,heigth).hasBeenClicked() == false){
      gameModel.step();
      gameModel.get(width,heigth).click();
      gameModel.get(width,heigth).uncover();
      if(gameModel.get(width,heigth).isMined() == true){
        gameModel.uncoverAll();
        gameView.update();
        JFrame newFrame = new JFrame("Boom!");
        JPanel panelBottom = new JPanel();
        JPanel panelMiddle = new JPanel();
        panelBottom.setSize(400,250);
        panelMiddle.setSize(400,250);
        newFrame.setLocationRelativeTo(null);
        newFrame.setSize(500,300);
        JLabel newLabel = new JLabel("<html>Ouch, you lost in "+gameModel.getNumberOfSteps()+" steps!<br/> Would you like to play again?</html>");
        newLabel.setFont(new Font("", Font.PLAIN, 25));
        panelMiddle.add(newLabel,BorderLayout.CENTER);
        JButton[] buttons = new JButton[2];
        newLabel.setPreferredSize(new Dimension(350,100));
        buttons[0] = new JButton("quit");
        buttons[0].setPreferredSize(new Dimension(80,40));
        buttons[1] = new JButton("Play again");
        buttons[1].setPreferredSize(new Dimension(120,40));
        buttons[0].addActionListener(this);
        buttons[1].addActionListener(this);
        panelBottom.add(buttons[0], BorderLayout.CENTER);
        panelBottom.add(buttons[1],BorderLayout.CENTER);
        newFrame.add(panelBottom, BorderLayout.SOUTH);
        newFrame.add(panelMiddle, BorderLayout.CENTER);
        newFrame.setVisible(true); 
      }else{
        if(gameModel.isBlank(width,heigth) == true){
            clearZone(gameModel.get(width,heigth));
            gameView.update();
        }
        if(gameModel.isFinished() == true){
          gameModel.uncoverAll();
          gameView.update();
          JFrame newFrame = new JFrame("Winner!");
          JPanel panelBottom = new JPanel();
          JPanel panelMiddle = new JPanel();
          panelBottom.setSize(400,250);
          panelMiddle.setSize(400,250);
          newFrame.setLocationRelativeTo(null);
          newFrame.setSize(500,300);
          JLabel newLabel = new JLabel("<html>Congratulations, you won in "+gameModel.getNumberOfSteps()+" steps!<br/> Would you like to play again?</html>");
          newLabel.setFont(new Font("", Font.PLAIN, 25));
          panelMiddle.add(newLabel,BorderLayout.CENTER);
          JButton[] buttons = new JButton[2];
          newLabel.setPreferredSize(new Dimension(350,100));
          buttons[0] = new JButton("quit");
          buttons[0].setPreferredSize(new Dimension(80,40));
          buttons[1] = new JButton("Play again");
          buttons[1].setPreferredSize(new Dimension(120,40));
          buttons[0].addActionListener(this);
          buttons[1].addActionListener(this);
          panelBottom.add(buttons[0], BorderLayout.CENTER);
          panelBottom.add(buttons[1],BorderLayout.CENTER);
          newFrame.add(panelBottom, BorderLayout.SOUTH);
          newFrame.add(panelMiddle, BorderLayout.CENTER);
          newFrame.setVisible(true); }
        }
        
        
        
        
      }
    gameView.update();
  }
  /**
   * <b>clearZone</b> is the method that computes which new dots should be ``uncovered'' 
   * when a new square with no mine in its neighborood has been selected
   * @param initialDot
   *      the DotInfo object corresponding to the selected DotButton that
   * had zero neighbouring mines
   */
  private void clearZone(DotInfo initialDot) {
    Stack<DotInfo> s = new GenericArrayStack<DotInfo>(9);
    s.push(initialDot);
    while(s.isEmpty() == false){
      DotInfo temp = s.pop();
      for(int k=temp.getY()-1;k<=temp.getY()+1;k++){
        for(int h=temp.getX()-1;h<=temp.getX()+1;h++){
          if(h==-1 || h==gameModel.getWidth() || k == -1 || k==gameModel.getHeigth()){
            continue;
          }else if(k==temp.getY() && h==temp.getX()){
            continue;
          }
          else{
            if(gameModel.get(h,k).isCovered() == true){
              gameModel.get(h,k).uncover();
              if(gameModel.getNeighbooringMines(h,k) == 0){
                s.push(gameModel.get(h,k));
              }
            }}}}}}
  
  
  
  
  // ADD YOU CODE HERE
  
}




