
/**
 * The class <b>DotInfo</b> is a simple helper class to store 
 * the state (e.g. clicked, mined, number of neighbooring mines...) 
 * at the dot position (x,y)
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Luke Germond
 * @author Kevin Jia
 */

public class DotInfo {
  
  // ADD YOUR INSTANCE VARIABLES HERE
  /**
   * represents if a square is covered or uncovered
   */
  private boolean covered;
  /**
   * represents if a square has a mine in it or not
   */
  private boolean mined;
  /**
   * represents the number of neighbooring mines of the square
   */
  private int neighbooringMines;
  
/**
   * represents if the square was clicked
   */
  private boolean wasClicked;
  /**
   * represents the column of a square
   */
  private int x;
  /**
   * represents the row of the square
   */
  private int y;
  
  
  /**
   * Constructor, used to initialize the instance variables
   * 
   * @param x
   *            the x coordinate
   * @param y
   *            the y coordinate
   */
  public DotInfo(int x, int y){
    
    // ADD YOU CODE HERE
    this.x = x;
    this.y = y;
    covered = true;
    wasClicked = false;
    mined = false;
    neighbooringMines = 0;
    
    
  }
  
  /**
   * Getter method for the attribute x.
   * 
   * @return the value of the attribute x
   */
  public int getX(){
    
    // ADD YOU CODE HERE
    return x;
  }
  
  
  /**
   * Getter method for the attribute y.
   * 
   * @return the value of the attribute y
   */
  public int getY(){
    
    // ADD YOU CODE HERE
    return y;
  }
  
  
  /**
   * Setter for mined
   */
  public void setMined() {
    
    // ADD YOU CODE HERE
    mined = true;
    
  }
  
  /**
   * Getter for mined
   *
   * @return mined
   */
  public boolean isMined() {
    
    // ADD YOU CODE HERE
    return mined;
    
  }
  
  
  /**
   * Setter for covered
   */
  public void uncover() {
    
    // ADD YOU CODE HERE
    covered = false;
  }
  
  /**
   * Getter for covered
   *
   * @return covered
   */
  public boolean isCovered(){
    
    // ADD YOU CODE HERE
    return covered;
  }
  
  
  
  /**
   * Setter for wasClicked
   */
  public void click() {
    
    // ADD YOU CODE HERE
    wasClicked = true;
    
  }
  
  
  /**
   * Getter for wasClicked
   *
   * @return wasClicked
   */
  public boolean hasBeenClicked() {
    
    // ADD YOU CODE HERE
    return wasClicked;
    
  }
  
  
  /**
   * Setter for neighbooringMines
   *
   * @param neighbooringMines
   *          number of neighbooring mines
   */
  public void setNeighbooringMines(int neighbooringMines) {
    
    // ADD YOU CODE HERE
    this.neighbooringMines = neighbooringMines;
    
  }
  
  /**
   * Get for neighbooringMines
   *
   * @return neighbooringMines
   */
  public int getNeighbooringMines() {
    
    // ADD YOU CODE HERE
    return neighbooringMines;
  }
  
}
