#Tic-Tac-Toe
##Project Specification

###Gameplay
This program will allow players to play games of tic-tac-toe against other players.
####Players
The players may be human users or computers. The number of players should be at least one.
####Game Board
The game will be played on a square grid game board, which should have a side length of at least one.
####Turns
During each player's turn, the player will place one piece unique to the player on an empty position of the game board. The order each player plays will always be the same.
####End State
After each turn, the program will check if the board is in an end state. The default winning end state is one where one column, row, or diagonal is completely filled with a unique piece. The winning state may be defined by the user. If the board is in a winning state, the player who owns the piece has won the game, and the game is now over. If the board is completely filled with pieces without a winning state, then the game has been tied, is in an end state, and is now over.

###Game Interface
The user will interact with this program with a GUI window. Note: The words save and export are used interchangeably.
####On Opening the window
The game that was last played will be loaded. If not the user will be given the options to import a game, start a new game, or analyze a game.
####On Closing the window
The current game, if not saved, will be saved. Changes to the window settings will be saved.
####Menu
The window will have a menu bar that contains options to export a game, import a game, analyze a game, start a new game, undo a move, set window settings, show help, and show information about the program. Actions under an analyze menu will become available once analysis mode is entered.
####Controlling the Current Game
There will be two buttons allowing the user to go back a move and go forward a move. There will be a display showing the current turn or who has won. There will be then be grid of buttons each corresponding to a position on the game board. The pieces on the positions of the game board will be shown on the buttons, and clicking a button during a user's turn will place the user's piece there. Certain buttons will be highlighted showing where the previous players placed their piece. If there is a winning position the row, column, or diagonal will be highlighted. If the game is in an end state and is over, the user will be given the options to undo the last move, export the game, analyze the game, show the end game board, start a new game, or quit the program.
####Export Game Interface
The user will be presented with a dialog. If the game has been saved before, the user has the option to automatically save to the same file. Otherwise, the user will be presented with a file chooser where the user can specify the name and path of the game file.
####Import Game Interface
The user will be presented with a dialog with a list of previous saved games that the user can choose to open. If otherwise the user will be presented with a file chooser where the user can specify the name and path of the game file to be opened.
####Analyzing a Game
There will be a grid of positions representing positions on a game board. The actions under the analysis menu will now be available. There will be actions allowing the user to move forward a move and backward a move, export the game at the current position, and a display showing the current turn and player. Positions will be highlighted showing a better choice of moves, and a list of moves will be shown in a sidebar.
####Window Settings
The user will be presented with a dialog to configure options about the window, like whether or not to highlight tiles or the display size and font.
####Showing help and information
Showing help will open up the default web browser to a Wikipedia page. Showing information will open a dialog.

###Managing Games
####Saving Games
The user will be able to save the current game being played at any point in the game, after any player's turn. The game will be exported into a .game file. The file will contain the game board's size, players, and the list of past moves.
####Opening Games
The user will be able to open a saved .game file. The game will be loaded at the current turn.
