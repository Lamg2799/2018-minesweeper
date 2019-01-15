import java.util.Random;

/**
 * The class <b>GameModel</b> holds the model, the state of the systems. 
 * It stores the following information:
 * - the state of all the ``dots'' on the board (mined or not, clicked
 * or not, number of neighbooring mines...)
 * - the size of the board
 * - the number of steps since the last reset
 *
 * The model provides all of this informations to the other classes trough 
 *  appropriate Getters. 
 * The controller can also update the model through Setters.
 * Finally, the model is also in charge of initializing the game
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 * @author Luke Germond
 * @author Kevin Jia
 */
public class GameModel {
  
  
  // ADD YOUR INSTANCE VARIABLES HERE
  /**
   * represents the width of the board
   */
  private int sizeWidth;
  /**
   * represents the height of the board
   */
  private int sizeHeight;
  /**
   * represents the total number of mines on the board
   */
  private int numberOfMines;
  /**
   * represents the number of squares clicked
   */
  private int numberOfSteps;
  /**
   * represents the number of squares that are uncovered
   */
  private int numberUncovered;
  /**
   * represents the array of squares
   */
  private DotInfo[][] model;
  
  /**
   * Constructor to initialize the model to a given size of board.
   * 
   * @param width
   *            the width of the board
   * 
   * @param heigth
   *            the heigth of the board
   * 
   * @param numberOfMines
   *            the number of mines to hide in the board
   */
  public GameModel(int width, int heigth, int numberOfMines) {
    
    // ADD YOU CODE HERE
    sizeWidth = width;
    sizeHeight = heigth;
    this.numberOfMines = numberOfMines; 
    numberUncovered = 0;
    numberOfSteps = 0;
    model = new DotInfo[sizeWidth][sizeHeight];
    for(int k=0;k<sizeWidth;k++){
      for(int d=0;d<sizeHeight;d++){
        model[k][d] = new DotInfo(k,d);
      }}
    Random rand = new Random();
    int count=0;
    while(count <numberOfMines){
      int tempRow = rand.nextInt(sizeHeight);
      int tempColumn = rand.nextInt(sizeWidth);
      if(model[tempColumn][tempRow].isMined() == false){
        model[tempColumn][tempRow].setMined();
        count++;
      }}
    for(int u=0;u<sizeHeight;u++){
      for(int f=0;f<sizeWidth;f++){
        model[f][u].setNeighbooringMines(getNeighbooringMines(f,u));
      }}
    
    
    
    
    
  }
  
  
  
  /**
   * Resets the model to (re)start a game. The previous game (if there is one)
   * is cleared up . 
   */
  public void reset(){
    numberOfSteps=0;
    DotInfo[][] modelNew = new DotInfo[sizeWidth][sizeHeight];
    Random rand = new Random();
    for (int k = 0; k < sizeWidth; k++) {
      for (int d = 0; d < sizeHeight; d++) {
        modelNew[k][d] = new DotInfo(k, d);
      }
    }
    model = modelNew;
    int count = 0;
    while (count < numberOfMines) {
      // CHANGE TO COUNT IF NUMBER OF MINES MINES WERE PUT IN NEW SPOTS
      int tempRow = rand.nextInt(sizeHeight);
      int tempColumn = rand.nextInt(sizeWidth);
      if (model[tempColumn][tempRow].isMined() == false) {
        model[tempColumn][tempRow].setMined();
        count++;
      }
    }
    for (int u = 0; u < sizeHeight; u++) {
      for (int f = 0; f < sizeWidth; f++) {
        model[f][u].setNeighbooringMines(getNeighbooringMines(f, u));
      }
    }
  }
  
  
  
  // ADD YOU CODE HERE
  
  
  
  
  /**
   * Getter method for the heigth of the game
   * 
   * @return the value of the attribute heigthOfGame
   */   
  public int getHeigth(){
    
    // ADD YOU CODE HERE
    return sizeHeight;
    
  }
  
  /**
   * Getter method for the width of the game
   * 
   * @return the value of the attribute widthOfGame
   */   
  public int getWidth(){
    
    // ADD YOU CODE HERE
    return sizeWidth; 
  }
  
  /**
   * returns true if the dot at location (i,j) is mined, false otherwise
   * 
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   * @return the status of the dot at location (i,j)
   */   
  public boolean isMined(int i, int j){
    
    // ADD YOU CODE HERE
    if(model[i][j].isMined() == true){
      return true;
    }
    return false;
  }
  
  /**
   * returns true if the dot  at location (i,j) has 
   * been clicked, false otherwise
   * 
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   * @return the status of the dot at location (i,j)
   */   
  public boolean hasBeenClicked(int i, int j){
    
    // ADD YOU CODE HERE
    if(model[i][j].hasBeenClicked() == true){
      return true;
    }
    return false;
  }
  
  /**
   * returns true if the dot  at location (i,j) has zero mined 
   * neighboor, false otherwise
   * 
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   * @return the status of the dot at location (i,j)
   */   
  public boolean isBlank(int i, int j){
    
    // ADD YOU CODE HERE
    for(int k=j-1;k<=j+1;k++){
      for(int h=i-1;h<=i+1;h++){
        if(h==-1 || h==sizeWidth || k == -1 || k==sizeHeight){
          continue;
        }else if(k==j && h==i){
          continue;
        }
        else{
          if(model[h][k].isMined() == true){
            return false;}
          else{
            continue;
          }
        }}}
    return true;
  }
  
  
  
  
  
  
  /**
   * returns true if the dot is covered, false otherwise
   * 
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   * @return the status of the dot at location (i,j)
   */   
  public boolean isCovered(int i, int j){
    
    // ADD YOU CODE HERE
    if(model[i][j].isCovered() == true){
      return true;
    }
    return false;
  }
  
  /**
   * returns the number of neighbooring mines os the dot  
   * at location (i,j)
   *
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   * @return the number of neighbooring mines at location (i,j)
   */   
  public int getNeighbooringMines(int i, int j){
    
    // ADD YOU CODE HERE
    int counter=0;
    for(int k=j-1;k<=j+1;k++){
      for(int h=i-1;h<=i+1;h++){
        if(h==-1 || h==sizeWidth || k == -1 || k==sizeHeight){
          continue;
        }else{
          if(model[h][k].isMined() == true){
            counter++;}
          else{
            continue;
          }
        }}}
    return counter;}
  
  
  
  
  
  /**
   * Sets the status of the dot at location (i,j) to uncovered
   * 
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   */   
  public void uncover(int i, int j){
    
    // ADD YOU CODE HERE
    model[i][j].uncover();
    
  }
  
  /**
   * Sets the status of the dot at location (i,j) to clicked
   * 
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   */   
  
  public void click(int i, int j){
    
    // ADD YOU CODE HERE
    model[i][j].click();
  }
  /**
   * Uncover all remaining covered dot
   */   
  public void uncoverAll(){
    
    // ADD YOU CODE HERE  //CHANGED IT SO IT UNCOVERS ALL THE MINES, CAUSE IT IS USELESS TO UNCOVER EVERY SINGLE SQUARE AS THERE 
    // IS NEVER A NEED FOR THAT IN THE GAME
    int count = 0;
    for(int i=0;i<sizeWidth;i++){
      for(int j=0;j<sizeHeight;j++){
        model[i][j].uncover();
        click(i,j);
      }}}
  
  
  
  /**
   * Getter method for the current number of steps
   * 
   * @return the current number of steps
   */   
  public int getNumberOfSteps(){
    
    // ADD YOU CODE HERE
    return numberOfSteps;
    
  }
  
  
  
  /**
   * Getter method for the model's dotInfo reference
   * at location (i,j)
   *
   * @param i
   *            the x coordinate of the dot
   * @param j
   *            the y coordinate of the dot
   *
   * @return model[i][j]
   */   
  public DotInfo get(int i, int j) {
    
    // ADD YOU CODE HERE
    return model[i][j];
    
  }
  
  
  /**
   * The metod <b>step</b> updates the number of steps. It must be called 
   * once the model has been updated after the payer selected a new square.
   */
  public void step(){
    
    // ADD YOU CODE HERE
    numberOfSteps++;
    
  }
  
  /**
   * The metod <b>isFinished</b> returns true iff the game is finished, that
   * is, all the nonmined dots are uncovered.
   *
   * @return true if the game is finished, false otherwise
   */
  public boolean isFinished(){
    
    // ADD YOU CODE HERE
    int count=0;
    for(int j=0;j<sizeHeight;j++){
      for(int i=0;i<sizeWidth;i++){
        if(model[i][j].isCovered() == true){
          count++;
        }
      }}
            
    if(count==numberOfMines){
      return true;}
    else{
      return false;
    }
    
  }
  
  
  /**
   * Builds a String representation of the model
   *
   * @return String representation of the model
   */
  public String toString(){
    
    // ADD YOU CODE HERE
    return "";
    
    
  }
}
