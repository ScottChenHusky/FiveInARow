package model;

/**
 * Created by ScottChen on 15/11/29.
 */

import com.sun.org.apache.bcel.internal.generic.MONITORENTER;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Random;
import java.util.TreeMap;

/**
 * to represent the Five In a Row Game implementation
 */
public class FiveInARow implements Model {
  private int gameSize;
  private Stone[][] board;
  private GameStatus state;
  private Stone lastMoveP1;
  private Stone lastMoveP2;
  private AI ai;
  private Siri siri;

  /**
   * to construct a Five In A Row Game
   *
   * @param gameSize   the size of the baord
   * @param board      the internal representation of this game
   * @param state      the game state
   * @param lastMoveP1 the last move of player 1
   * @param lastMoveP2 the last move of player 2
   * @param ai         of this game
   */
  private FiveInARow(int gameSize, Stone[][] board, GameStatus state,
                     Stone lastMoveP1, Stone lastMoveP2, AI ai, Siri siri) {
    if (gameSize < 10) {
      throw new IllegalArgumentException("The Board is too small");
    }
    this.gameSize = gameSize;
    this.board = board;
    this.state = state;
    this.lastMoveP1 = lastMoveP1;
    this.lastMoveP2 = lastMoveP2;
    this.ai = ai;
    this.siri = siri;
  }

  @Override
  public int getGameSize() {
    return gameSize;
  }

  @Override
  public Stone[][] getBoard() {
    return board;
  }

  @Override
  public GameStatus getState() {
    return state;
  }

  @Override
  public Stone getLastMoveP1() {
    return this.lastMoveP1;
  }

  @Override
  public Stone getLastMoveP2() {
    return this.lastMoveP2;
  }

  @Override
  public AI getAI() {
    return ai;
  }

  @Override
  public Siri getSiri() {
    return this.siri;
  }

  /**
   * to represent the nest class of Five in a row game
   */
  public static class FiveInARowBuilder {
    private int gameSize;
    private Stone[][] board;
    private GameStatus state;
    private Stone lastMoveP1;
    private Stone lastMoveP2;
    private AI ai;
    private Siri siri;

    /**
     * to construct a Five in a row builder
     */
    public FiveInARowBuilder() {
      this.gameSize = Model.GAME_SIZE;
      this.board = new Stone[Model.GAME_SIZE][Model.GAME_SIZE];
      this.state = GameStatus.PLAYER1;
      this.lastMoveP1 = null;
      this.lastMoveP2 = null;
      this.ai = AI.NoAI;
      this.siri = new Siri(-1, -1, -1);
    }

    /**
     * to set the game size
     *
     * @param gameSize the size of the game
     * @return the FiveInARowBuilder with the given size
     */
    public FiveInARowBuilder setGameSize(int gameSize) {
      this.gameSize = gameSize;
      return this;

    }

    /**
     * to set the board of this game
     *
     * @param board the board of this game
     * @return the FiveInARowBuilder with the given board
     */
    public FiveInARowBuilder setBoard(Stone[][] board) {
      this.board = board;
      return this;
    }

    /**
     * to set the state of this game
     *
     * @param state the state of this game
     * @return the FiveInARowBuilder with the given state
     */
    public FiveInARowBuilder setState(GameStatus state) {
      this.state = state;
      return this;
    }

    /**
     * to set the last move of this builder
     *
     * @param lastMoveP1 the last move of this builder
     * @return the FiveInARowBuilder with the given lastMove
     */
    public FiveInARowBuilder setLastMoveP1(Stone lastMoveP1) {
      this.lastMoveP1 = lastMoveP1;
      return this;
    }

    /**
     * to set the last move of this builder
     *
     * @param lastMoveP2 the last move of this builder
     * @return the FiveInARowBuilder with the given lastMove
     */
    public FiveInARowBuilder setLastMoveP2(Stone lastMoveP2) {
      this.lastMoveP2 = lastMoveP2;
      return this;
    }

    /**
     * to set the ai of this builder
     *
     * @param ai the ai of this builder
     * @return the FiveInARowBuilder with the given ai
     */
    public FiveInARowBuilder setAI(AI ai) {
      this.ai = ai;
      return this;
    }

    /**
     * to set siri of this builder
     * @param siri the siri of this builder
     * @return the FiveInARowBuilder with the given siri
     */
    public FiveInARowBuilder setSiri(Siri siri) {
      this.siri = siri;
      return this;
    }

    /**
     * to build the Five in a row game with the given parameters
     *
     * @return a Five In A Row game with all the give parameters
     */
    public FiveInARow build() {
      return new FiveInARow(this.gameSize, this.board, this.state, this.lastMoveP1, this.lastMoveP2,
              this.ai, this.siri);
    }


  }

  @Override
  public boolean isGameOver() {
    this.horizontalWin();
    this.verticalWin();
    this.tl2br();
    this.tr2bl();
    if (this.state == GameStatus.P1WINS || this.state == GameStatus.P2WINS) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * to check is there a winner in the horizontal line
   */
  public void horizontalWin() {
    Stone lastMove;
    if (this.state == GameStatus.PLAYER1) {
      lastMove = this.lastMoveP1;
    } else if (this.state == GameStatus.PLAYER2) {
      lastMove = this.lastMoveP2;
    } else {
      lastMove = null;
    }
    if (lastMove != null) {
      int x = lastMove.getX();
      int y = lastMove.getY();
      Players p = lastMove.getTakenBy();
//      int numOfStone = 1;
//      int idx = 0;
//      while (idx <= x) {
//        if (this.board[x - idx][y] == null) {
//          break;
//        } else if (this.board[x - idx][y].getTakenBy() != p) {
//          break;
//        }
//        numOfStone += 1;
//        idx++;
//      }
      int numOfStone = this.leftCounter(x, y, p, false).getScore() + this.rightCounter(x, y, p, false).getScore();
//      idx = 0;
//      while (idx <= (Model.GAME_SIZE - x) - 1) {
//        System.out.println(x);
//        if (this.board[x + idx][y] == null) {
//          break;
//        } else if (this.board[x + idx][y].getTakenBy() != p) {
//          break;
//        }
//        numOfStone += 1;
//        idx++;
//      }
      if(this.state == GameStatus.PLAYER2) {
        System.out.println(numOfStone);
      }
      if (numOfStone >= 6 && lastMove.getTakenBy() == Players.PLAYER1) {
        this.state = GameStatus.P1WINS;
      } else if (numOfStone >= 6 && lastMove.getTakenBy() == Players.PLAYER2) {
        this.state = GameStatus.P2WINS;
      }
    }
  }

  /**
   * to check is there a winner in the vertical direction
   */
  public void verticalWin() {
    Stone lastMove;
    if (this.state == GameStatus.PLAYER1) {
      lastMove = this.lastMoveP1;
    } else if (this.state == GameStatus.PLAYER2) {
      lastMove = this.lastMoveP2;
    } else {
      lastMove = null;
    }
    if(lastMove != null) {
      int x = lastMove.getX();
      int y = lastMove.getY();
      Players p = lastMove.getTakenBy();
//      int numOfStone = 1;
//      int idx = 0;
//      while(idx <= y) {
//        if(this.board[x][y-idx] == null) {
//          break;
//        } else if (this.board[x][y-idx].getTakenBy() != p) {
//          break;
//        }
//        numOfStone+=1;
//        idx++;
//      }
      int numOfStone = this.topCounter(x, y, p, false).getScore() + this.botCounter(x, y, p, false).getScore();
//      idx = 0;
//      while(idx <= (Model.GAME_SIZE - y) - 1) {
//        if(this.board[x][y+idx] == null) {
//          break;
//        } else if (this.board[x][y+idx].getTakenBy() != p) {
//          break;
//        }
//        numOfStone+=1;
//        idx++;
//      }
      if (numOfStone >= 6 && lastMove.getTakenBy() == Players.PLAYER1) {
        this.state = GameStatus.P1WINS;
      } else if (numOfStone >= 6 && lastMove.getTakenBy() == Players.PLAYER2) {
        this.state = GameStatus.P2WINS;
      }
    }
  }

  /**
   * to check is there a winner in the top left to bottom right direction
   */
  public void tl2br() {
    Stone lastMove;
    if (this.state == GameStatus.PLAYER1) {
      lastMove = this.lastMoveP1;
    } else if (this.state == GameStatus.PLAYER2) {
      lastMove = this.lastMoveP2;
    } else {
      lastMove = null;
    }
    if(lastMove != null) {
      int x = lastMove.getX();
      int y = lastMove.getY();
      Players p = lastMove.getTakenBy();
//      int numOfStone = 1;
//      int idx = 0;
//      while(idx <= y && idx <= x) {
//        if(this.board[x-idx][y-idx] == null) {
//          break;
//        } else if (this.board[x-idx][y-idx].getTakenBy() != p) {
//          break;
//        }
//        numOfStone+=1;
//        idx++;
//      }
      int numOfStone = this.tlCounter(x, y, p, false).getScore() + this.brCounter(x, y, p, false).getScore();
//      idx = 0;
//      while(idx <= (Model.GAME_SIZE - x) - 1 && idx <= (Model.GAME_SIZE - y) - 1) {
//        if(this.board[x+idx][y+idx] == null) {
//          break;
//        } else if (this.board[x+idx][y+idx].getTakenBy() != p) {
//          break;
//        }
//        numOfStone+=1;
//        idx++;
//      }
      if (numOfStone >= 6 && lastMove.getTakenBy() == Players.PLAYER1) {
        this.state = GameStatus.P1WINS;
      } else if (numOfStone >= 6 && lastMove.getTakenBy() == Players.PLAYER2) {
        this.state = GameStatus.P2WINS;
      }
    }
  }

  /**
   * to check is there a winner in the top right to bottom left direction
   */
  public void tr2bl() {
    Stone lastMove;
    if (this.state == GameStatus.PLAYER1) {
      lastMove = this.lastMoveP1;
    } else if (this.state == GameStatus.PLAYER2) {
      lastMove = this.lastMoveP2;
    } else {
      lastMove = null;
    }
    if(lastMove != null) {
      int x = lastMove.getX();
      int y = lastMove.getY();
      Players p = lastMove.getTakenBy();
//      int numOfStone = 1;
//      int idx = 0;
//      while(idx <= (Model.GAME_SIZE - y) - 1 && idx <= x) {
//        if(this.board[x-idx][y+idx] == null) {
//          break;
//        } else if (this.board[x-idx][y+idx].getTakenBy() != p) {
//          break;
//        }
//        numOfStone+=1;
//        idx++;
//      }
      int numOfStone = this.trCounter(x, y, p, false).getScore() + this.blCounter(x, y, p, false).getScore();
//      idx = 0;
//      while(idx <= (Model.GAME_SIZE - x) - 1 && idx <= y) {
//        if(this.board[x+idx][y-idx] == null) {
//          break;
//        } else if (this.board[x+idx][y-idx].getTakenBy() != p) {
//          break;
//        }
//        numOfStone+=1;
//        idx++;
//      }
      if (numOfStone >= 6 && lastMove.getTakenBy() == Players.PLAYER1) {
        this.state = GameStatus.P1WINS;
      } else if (numOfStone >= 6 && lastMove.getTakenBy() == Players.PLAYER2) {
        this.state = GameStatus.P2WINS;
      }
    }
  }

  @Override
  public String winnerIs() {
    if (this.state == GameStatus.P1WINS) {
      return this.lastMoveP1.getTakenBy().toString();
    } else if (this.state == GameStatus.P2WINS) {
      return this.lastMoveP2.getTakenBy().toString();
    } else {
      return "Still Playing";
    }
  }

  @Override
  public void toPlay(int x, int y) {
    if (this.state == GameStatus.P1WINS || this.state == GameStatus.P2WINS) {
      throw new IllegalArgumentException("The game is over, can't make any move");
    }
    Stone position = this.board[x][y];
    if (position == null && this.state == GameStatus.PLAYER1) {
      position = new Stone(Players.PLAYER1, x, y);
      this.board[x][y] = position;
      this.lastMoveP1 = position;
      if (!this.isGameOver()) {
        this.state = GameStatus.PLAYER2;
      }
    } else if (position == null && this.state == GameStatus.PLAYER2) {
      position = new Stone(Players.PLAYER2, x, y);
      this.board[x][y] = position;
      this.lastMoveP2 = position;
      if (!this.isGameOver()) {
        this.state = GameStatus.PLAYER1;
      }
    } else {
      throw new IllegalArgumentException("This place is occupied" + x + " " + y + " " + this.state.toString());
    }
  }

  @Override
  public void replay() {
    this.gameSize = Model.GAME_SIZE;
    this.board = new Stone[Model.GAME_SIZE][Model.GAME_SIZE];
    this.state = GameStatus.PLAYER1;
    this.lastMoveP1 = null;
    this.lastMoveP2 = null;
    this.ai = AI.NoAI;
  }

  @Override
  public void opps() {
    if (this.state == GameStatus.PLAYER1) {
      this.board[this.lastMoveP2.getX()][this.lastMoveP2.getY()] = null;
      this.lastMoveP2 = null;
      this.state = GameStatus.PLAYER2;
    } else if (this.state == GameStatus.PLAYER2) {
      this.board[this.lastMoveP1.getX()][this.lastMoveP1.getY()] = null;
      this.lastMoveP1 = null;
      this.state = GameStatus.PLAYER1;
    }
  }

  @Override
  public void aiSwitch() {
    if (this.ai == AI.NoAI && this.state == GameStatus.PLAYER1) {
      this.ai = AI.AIPlayer1;
      this.aiThinking();
    } else if (this.ai == AI.NoAI && this.state == GameStatus.PLAYER2) {
      this.ai = AI.AIPlayer2;
      this.aiThinking();
    } else if (this.ai == AI.AIPlayer1 || this.ai == AI.AIPlayer2) {
      this.ai = AI.NoAI;
    } else {
      throw new IllegalArgumentException("AI is Zzzzzzz...");
    }
  }

  @Override
  public void aiThinking() {
    ArrayList<Posn> source;
    if (this.ai == AI.AIPlayer1) {
      source = this.lop(this.lastMoveP1);
      for (Posn posn : source) {
        int score = this.scorer(posn.getX(), posn.getY(), Players.PLAYER1);
        if(score > this.siri.getScore()) {
          this.siri = new Siri(posn.getX(), posn.getY(), score);
        }
      }
      if (this.siri.getScore() != 431057700) {
        for (Posn posn : this.lop(this.lastMoveP2)) {
          int score = this.scorer(posn.getX(), posn.getY(), Players.PLAYER2);
          if(score > this.siri.getScore()) {
            this.siri = new Siri(posn.getX(), posn.getY(), score);
          }
        }
      }
    } else {
      source = this.lop(this.lastMoveP2);
      for (Posn posn : source) {
        int score = this.scorer(posn.getX(), posn.getY(), Players.PLAYER2);
        if(score > this.siri.getScore()) {
          this.siri = new Siri(posn.getX(), posn.getY(), score);
        }
      }
      if (this.siri.getScore() != 431057700) {
        for (Posn posn : this.lop(this.lastMoveP1)) {
          int score = this.scorer(posn.getX(), posn.getY(), Players.PLAYER1);
          if(score > this.siri.getScore()) {
            this.siri = new Siri(posn.getX(), posn.getY(), score);
          }
        }
      }
    }
    if (this.siri.getScore() == -1) {
      this.checkAll();
    }
    this.toPlay(this.siri.getX(), this.siri.getY());
    this.siri = new Siri(-1, -1, -1);
  }

  @Override
  public int scorer(int x, int y, Players p) {
//    Players pAI;
//    Players pPlayer;
//    if (this.ai == AI.AIPlayer1) {
//      pAI = Players.PLAYER1;
//      pPlayer = Players.PLAYER2;
//    } else {
//      pAI = Players.PLAYER2;
//      pPlayer = Players.PLAYER1;
//    }

    Players p2;
    if (p == Players.PLAYER1) {
      p2 = Players.PLAYER2;
    } else {
      p2 = Players.PLAYER1;
    }
    // AI
    Counter horLA = this.leftCounter(x, y, p, true);
    Counter horRA = this.rightCounter(x, y, p, true);
    Counter verTA = this.topCounter(x, y, p, true);
    Counter verBA = this.botCounter(x, y, p, true);
    Counter tlbrTLA = this.tlCounter(x, y, p, true);
    Counter tlbrBRA = this.brCounter(x, y, p, true);
    Counter trblTRA = this.trCounter(x, y, p, true);
    Counter trblBLA = this.blCounter(x, y, p, true);
    // PLAYER
    Counter horLP = this.leftCounter(x, y, p2, true);
    Counter horRP = this.rightCounter(x, y, p2, true);
    Counter verTP = this.topCounter(x, y, p2, true);
    Counter verBP = this.botCounter(x, y, p2, true);
    Counter tlbrTLP = this.tlCounter(x, y, p2, true);
    Counter tlbrBRP = this.brCounter(x, y, p2, true);
    Counter trblTRP = this.trCounter(x, y, p2, true);
    Counter trblBLP = this.blCounter(x, y, p2, true);
    // RULES
    // HORIZONTAL
    int answer1 = this.comparator(horLA, horRA);
    if (answer1 >= 431057700) {
      return answer1;
    } else {
      answer1 = answer1 + this.comparator(horLP, horRP);
    }
    // VERTICAL
    int answer2 = this.comparator(verTA, verBA);
    if (answer2 >= 431057700) {
      return answer2;
    } else {
      answer2 = answer2 + this.comparator(verTP, verBP);
    }
    // TL2BR
    int answer3 = this.comparator(tlbrTLA, tlbrBRA);
    if (answer3 >= 431057700) {
      return answer3;
    } else {
      answer3 = answer3 + this.comparator(tlbrTLP, tlbrBRP);
    }
    // TR2BL
    int answer4 = this.comparator(trblTRA, trblBLA);
    if (answer4 >= 431057700) {
      return answer4;
    } else {
      answer4 = answer4 + this.comparator(trblTRP, trblBLP);
    }
    return answer1 + answer2 + answer3 + answer4;
  }

  @Override
  public ArrayList<Posn> lop(Stone stone) {
    ArrayList<Posn> answer = new ArrayList<Posn>();
    if (stone != null) {
      int x = stone.getX();
      int y = stone.getY();

      if (x >= 1) {
        if (this.board[x - 1][y] == null) {
          answer.add(new Posn(x-1, y));
        } else if (this.board[x - 1][y].getTakenBy() == stone.getTakenBy()) {
          answer = new ArrayListUtils(answer).append(this.lop(this.board[x - 1][y]));
        }

        if (y>=1) {
          if (this.board[x - 1][y - 1] == null) {
            answer.add(new Posn(x-1, y-1));
          } else if (this.board[x - 1][y - 1].getTakenBy() == stone.getTakenBy()) {
            answer = new ArrayListUtils(answer).append(this.lop(this.board[x - 1][y]));
          }
        }
      }

      if (y>=1) {
        if (this.board[x][y - 1] == null) {
          answer.add(new Posn(x, y-1));
        } else if (this.board[x][y - 1].getTakenBy() == stone.getTakenBy()) {
          answer = new ArrayListUtils(answer).append(this.lop(this.board[x - 1][y]));
        }

        if (x < GAME_SIZE - 1) {
          if (this.board[x + 1][y - 1] == null) {
            answer.add(new Posn(x+1, y-1));
          } else if (this.board[x + 1][y - 1].getTakenBy() == stone.getTakenBy()) {
            answer = new ArrayListUtils(answer).append(this.lop(this.board[x - 1][y]));
          }
        }
      }

      if (x < GAME_SIZE - 1) {
        if (this.board[x + 1][y] == null) {
          answer.add(new Posn(x+1, y));
        } else if (this.board[x + 1][y].getTakenBy() == stone.getTakenBy()) {
          answer = new ArrayListUtils(answer).append(this.lop(this.board[x - 1][y]));
        }

        if (y < GAME_SIZE - 1) {
          if (this.board[x + 1][y + 1] == null) {
            answer.add(new Posn(x+1, y+1));
          } else if (this.board[x + 1][y + 1].getTakenBy() == stone.getTakenBy()) {
            answer = new ArrayListUtils(answer).append(this.lop(this.board[x - 1][y]));
          }
        }
      }

      if (y < GAME_SIZE - 1) {
        if (this.board[x][y + 1] == null) {
          answer.add(new Posn(x, y+1));
        } else if (this.board[x][y + 1].getTakenBy() == stone.getTakenBy()) {
          answer = new ArrayListUtils(answer).append(this.lop(this.board[x - 1][y]));
        }

        if (x >= 1 ) {
          if (this.board[x - 1][y + 1] == null) {
            answer.add(new Posn(x-1, y+1));
          } else if (this.board[x - 1][y + 1].getTakenBy() == stone.getTakenBy()) {
            answer = new ArrayListUtils(answer).append(this.lop(this.board[x - 1][y]));
          }
        }
      }
    }
    return answer;
  }

  @Override
  public void checkAll() {
    if (this.board[GAME_SIZE/2][GAME_SIZE/2] == null) {
      this.siri = new Siri(GAME_SIZE/2, GAME_SIZE/2, 0);
      return;
    }
    int x = 0;
    int y = 0;
    boolean flag = true;
    while (x >= 0 && x < GAME_SIZE && flag) {
      while (y >= 0 && y < GAME_SIZE && flag) {
        if (this.board[x][y] == null) {
          this.siri = new Siri(x, y, 0);
          flag = false;
        }
        y++;
      }
      x++;
    }
  }

  @Override
  public Counter leftCounter(int x, int y, Players p, boolean countScore) {
    int numOfStone = 0;
    String player = "";
    int idx = 0;
    if (countScore) {
      this.board[x][y] = new Stone(p, x, y);
    }
    while (idx <= x) {
      if (this.board[x - idx][y] == null) {
        player = "null";
        break;
      } else if (this.board[x - idx][y].getTakenBy() != p) {
        player = this.board[x - idx][y].getTakenBy().toString();
        break;
      }
      numOfStone += 1;
      idx++;
    }
    if (countScore) {
      this.board[x][y] = null;
    }
    return new Counter(numOfStone, player);
  }

  @Override
  public Counter rightCounter(int x, int y, Players p, boolean countScore) {
    int numOfStone = 0;
    String player = "";
    int idx = 0;
    if (countScore) {
      this.board[x][y] = new Stone(p, x, y);
    }
    while (idx <= (Model.GAME_SIZE - x) - 1) {
      if (this.board[x + idx][y] == null) {
        player = "null";
        break;
      } else if (this.board[x + idx][y].getTakenBy() != p) {
        player = this.board[x + idx][y].getTakenBy().toString();
        break;
      }
      numOfStone += 1;
      idx++;
    }
    if(countScore) {
      this.board[x][y] = null;
    }
    return new Counter(numOfStone, player);
  }

  @Override
  public Counter topCounter(int x, int y, Players p, boolean countScore) {
    int numOfStone = 0;
    String player = "";
    int idx = 0;
    if (countScore) {
      this.board[x][y] = new Stone(p, x, y);
    }
    while(idx <= y) {
      if(this.board[x][y-idx] == null) {
        player = "null";
        break;
      } else if (this.board[x][y-idx].getTakenBy() != p) {
        player = this.board[x][y-idx].getTakenBy().toString();
        break;
      }
      numOfStone+=1;
      idx++;
    }
    if (countScore) {
      this.board[x][y] = null;
    }
    return new Counter(numOfStone, player);
  }

  @Override
  public Counter botCounter(int x, int y, Players p, boolean countScore) {
    int numOfStone = 0;
    String player = "";
    int idx = 0;
    if (countScore) {
      this.board[x][y] = new Stone(p, x, y);
    }
    while(idx <= (Model.GAME_SIZE - y) - 1) {
      if(this.board[x][y+idx] == null) {
        player = "null";
        break;
      } else if (this.board[x][y+idx].getTakenBy() != p) {
        player = this.board[x][y+idx].getTakenBy().toString();
        break;
      }
      numOfStone+=1;
      idx++;
    }
    if (countScore) {
      this.board[x][y] = null;
    }
    return new Counter(numOfStone, player);
  }

  @Override
  public Counter tlCounter(int x, int y, Players p, boolean countScore) {
    int numOfStone = 0;
    String player = "";
    int idx = 0;
    if (countScore) {
      this.board[x][y] = new Stone(p, x, y);
    }
    while(idx <= y && idx <= x) {
      if(this.board[x-idx][y-idx] == null) {
        player = "null";
        break;
      } else if (this.board[x-idx][y-idx].getTakenBy() != p) {
        player = this.board[x-idx][y-idx].getTakenBy().toString();
        break;
      }
      numOfStone+=1;
      idx++;
    }
    if (countScore) {
      this.board[x][y] = null;
    }
    return new Counter(numOfStone, player);
  }

  @Override
  public Counter brCounter(int x, int y, Players p, boolean countScore) {
    int numOfStone = 0;
    String player = "";
    int idx = 0;
    if (countScore) {
      this.board[x][y] = new Stone(p, x, y);
    }
    while(idx <= (Model.GAME_SIZE - x) - 1 && idx <= (Model.GAME_SIZE - y) - 1) {
      if(this.board[x+idx][y+idx] == null) {
        player = "null";
        break;
      } else if (this.board[x+idx][y+idx].getTakenBy() != p) {
        player = this.board[x+idx][y+idx].getTakenBy().toString();
        break;
      }
      numOfStone+=1;
      idx++;
    }
    if (countScore) {
      this.board[x][y] = null;
    }
    return new Counter(numOfStone, player);
  }

  @Override
  public Counter trCounter(int x, int y, Players p, boolean countScore) {
    int numOfStone = 0;
    String player = "";
    int idx = 0;
    if (countScore) {
      this.board[x][y] = new Stone(p, x, y);
    }
    while(idx <= (Model.GAME_SIZE - y) - 1 && idx <= x) {
      if(this.board[x-idx][y+idx] == null) {
        player = "null";
        break;
      } else if (this.board[x-idx][y+idx].getTakenBy() != p) {
        player = this.board[x-idx][y+idx].getTakenBy().toString();
        break;
      }
      numOfStone+=1;
      idx++;
    }
    if (countScore) {
      this.board[x][y] = null;
    }
    return new Counter(numOfStone, player);
  }

  @Override
  public Counter blCounter(int x, int y, Players p, boolean countScore) {
    int numOfStone = 0;
    String player = "";
    int idx = 0;
    if (countScore) {
      this.board[x][y] = new Stone(p, x, y);
    }
    while(idx <= (Model.GAME_SIZE - x) - 1 && idx <= y) {
      if(this.board[x+idx][y-idx] == null) {
        player = "null";
        break;
      } else if (this.board[x+idx][y-idx].getTakenBy() != p) {
        player = this.board[x+idx][y-idx].getTakenBy().toString();
        break;
      }
      numOfStone+=1;
      idx++;
    }
    if (countScore) {
      this.board[x][y] = null;
    }
    return new Counter(numOfStone, player);
  }

  @Override
  public int comparator(Counter c1, Counter c2) {
    int score = 0;
    if (c1.getScore() + c2.getScore() >= 6) {
      return 431057700;
    } else if (c1.getScore() + c2.getScore() == 5 && c1.getPlayer().equals("null") &&
            c2.getPlayer().equals("null")) {
      score = score + 5321700;
    } else if (c1.getScore() + c2.getScore() == 4 && c1.getPlayer().equals("null") &&
            c2.getPlayer().equals("null")) {
      score = score + 65700;
    } else if (c1.getScore() + c2.getScore() == 5 && !c1.getPlayer().equals(c2.getPlayer()) &&
            (c1.getPlayer().equals("null") || c2.getPlayer().equals("null"))) {
      score = score + 810;
    } else if (c1.getScore() + c2.getScore() == 3 && c1.getPlayer().equals("null") &&
            c2.getPlayer().equals("null")) {
      score = score + 10;
    }
    return score;
  }


}
