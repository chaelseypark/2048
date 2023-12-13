# 2048

  RunTwentyFortyEight: This class sets up the top-level frame and widgets for the GUI. It adds a
  board panel for the game itself, adds a status panel at the bottom for the score, and adds a
  control panel at the top for the reset, undo, and save progress buttons. It also adds an
  instructions pop-up that appears at the start of the game. It also helps determine the board
  state using the user's inputs (e.g. user's decision to load previous progress).

  GameBoard: This class instantiates the board object. It holds all the files that I added
  for the board and tiles. The board object can repaint itself when called, and acts as
  accordingly when a key is clicked (GameCourt then notifies board to update). It can add tiles,
  change the board to duplicate another 2d array board, and can return its 2d array state.

  GameCourt: This class holds the game logic, basically putting together the game. It manages
  the game state, handles inputs (key clicks), tells the board when and how to update, and also
  help build the frontend. It keeps track of the scores, handles all other implementations
  (undo, save/retrieve functions, etc.), and updates the GUI.
