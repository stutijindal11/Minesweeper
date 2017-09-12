# Minesweeper

Android - SQLite

### About

Minesweeper is a single player game. The objective of the game is to clear a minefield without
hitting a mine. The players will be presented with a grid of blocks and some of these
randomly contain mines. The player needs to uncover these blocks. The player wins if no mines are uncovered and looses
if any of the mines get uncovered.

### Features

* Registration: Each player needs to login before playing. Text messages containing user information are sent upon signup.
* User can play multiple games at a stretch.
* A timer starts once a new game begins. The time taken by the player pops up once the game is over.
* The players can set flags and question marks like the conventional minesweeper game.
* Players can view logs of their and other player's scores.

### Database

We have used a Database named `PlayerDB` in our project. It contains a table named `players`. The
schema diagram for this table is players
`(name varchar, password varchar);`

Basically a player needs to login before playing the game. We extract these user details
and insert them in our database. Thus if a player is already registered, he/she can directly
login. If a user in not registered, a corresponding message saying that he/she need to register first
is displayed. Once a player is registered, a message is sent to his mobile giving him his login
details, which can be used by the player the next time he logins. Also the database is used to
calculate and display the max-score of each player and the maximum score until now.

### Directions to Play

* Left click on a grid block opens the block.
* Right click on a grid block allows to mark a block as `flagged` (confirmed mine underneath); flagged
blocks can be marked with `question marks` (doubt about presence of mine), and question marked
blocks can be un-marked as well.
* First block never contains a mine underneath; this reduces the pain of guessing even the first
block.
* If an uncovered block is blank, nearby blocks are recursively opened till a numbered block is
opened; simulating a ripple effect.
* Clicking left-right/middle button on a block, where all mines in nearby blocks are already
flagged, uncovers all nearby covered blocks.
* Timer starts on clicking the first block and not after selecting a new game.
