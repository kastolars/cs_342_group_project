Ayush Patel
apate324

HOMEWORK #3: Addition of Characters and Inheritance
_____________________________________________________________________________

How to Run?
To run the program run, first compile the Makefile using "make" command in the terminal then run "java GameTester ..." to run the program. The "..." is the GDF filename that needs to be read to load game data.

Once the program is running the user will be provided with basic instructions to input the direction you would like to move in, look for artifacts, pickup and drop items, use them, and look through user inventory. The instruction to input the direction is provided below as well for reference.

The following commands are accepted:

1. GO dir 	(dir -- Abbrivations or Full Name)
2. LOOK		(Displays the Artifacts present in the Room and Room Description)
3. DISPLAY	(Displays all the info about the current character and some details about the room it is in.)
4. GET artifact		(Picks up the artifact named)
5. DROP artifact	(Drops the the artifact named)
6. USE artifact		(Uses the artifact named in the Current room)
7. INVENTORY or INVE	(List the artifacts in your possession and it's values)	
8. QUIT or EXIT + 'ALL' will result exit from the game. To Exit just the current player use EXIT or QUIT along without ALL

***** Use Caps, Don't Use Caps its up to You, Who am I to judge?

Useful Tip: Display provides relevent info that will help you make make your next move. You get 3 hints (or Displays) after that you are on your own. Displays also come at a cost of a turn. Using a display will mean you get the info but lose that turn.

NOTE: Each player gets one turn and then they wait until all the players in the game have had their turn. Using any of the above commands counts as turn excluding LOOK and INVENTORY/INVE. Caps lock are not required for any commands and the program will ignore any caps in the commands. The ONLY way to look at the artifacts in the room is by using "LOOK", when the player enters a new room the artifacts WON'T be shown with description. The idea is to to let the player explore the room rather than giving them all the infomation.

DIRECTIONS: N, S, E, W, U, P, NE, NW, SE, SW, NNE, NNW, ENE, WNW, ESE, WSW, SSE, SSW
_____________________________________________________________________________

Code Details

In this homework Scanner is passed between classes to read the Game Data File and store the Places, Directions, Characters and Artifacts. To create an interconnected Places, Directions, and Artifacts, ArrayList is used to store all the different object types within the Place Class. Collection of Places is the only variable that is static and is accessible to all the other classes the other two (Direction and Artifacts) are private Arraylist and are only accessible inside Place class. A brief description of all the Class is provided below along with some key new functions that were implemented.

EXTRA FEATURES:

In this program I have implemented a system of Hints that will help the user when they need help. The Player will get 3 hints to began and will use and everytime they use "DISPLAY" movement they will lose a hint and their current turn. Display prints out all the information the user will need about the character and the room they are in. This will be further explored in the next project.

-----------------------------------------------------------------------------

Artifact Class:
Holds the constructor for artifacts and adds them to the Artifacts collection in Place class. Additionally has the use() function that will use the artifact in the current room (currently only keys). When reading through the file if the placeID is less than 0 that means Artifact is in player's possession, it's greater than 0 than it in room with ID and if it's 0 we assign a random room to the artifact and put it there.

Key Functions:

use():
uses the artifact:
--> in case of the Key -- get Current place, from game class --> pass the key to useKey() of the curr place

-----------------------------------------------------------------------------

ArtificialDecision Class:

This class implements DecisionMaker for AI. It has getMove() function that takes in a Character and place to determine if what move the NPC AI can make. Using a random number it will pick from GO, GET, DROP, and USE movements to execute.

--> GO: generates a string with "G0 (random Direction)"
--> GET: generates a string with "GET (random artifact in the place)"
--> DROP: generates a string with "DROP (random artifact in character inventory)"
--> USE: generates a string with "USE (random artifact in the character inventory)"

For the purpose of the project we will assume that the NPC knows all the charaters present in the room and in the player's inventory since it can't look, display, or view inventory. Returns an Move object that has the movetype and the arguments (direction, artifact name)

-----------------------------------------------------------------------------

Character Class:

This class will construct a character (player or NPC) and store then them in a arraylist. There are two constructors for this class. First one takes in a Scanner, double, and int to read the characters from the data file. Second one takes in int, String, String and int (ID, name, desc, placeID) to create characters if the user wants more than the characters present in the gdf file. This class also now has play capabilities of the play() method in Game class.

Some key methods are:

 --> getCharacterbyID: takes in an int value and returns the Character associated with ID. If no character can be found returns null.
 --> makeMove(): encapsulates the capablility of the play() method from game class. Determines if the character is of type Player or of type Character and calls appropriate desicionmaker for it to get the move. It will subpress some of the moves of the NPC while still showing moves like get, drop, and use.
--> display(): prints out the description of the character
--> getArtifact(): returns a random artifact from the character's inventory.
--> exit(): Updates the Collection of Characters in Character class and Game class. If a player character exits this will remove it from the both classes and update the internal counter to determine when to exit the game, that is, if no player characters are present the game will stop playing even if there are NPC present in the game.

-----------------------------------------------------------------------------

CleanLineScanner class:

Seperates some key data parsing functions from regular classes.
Some key functions are:

--> getCleanLine():  Takes the scanner from any of the class and reads the line and returns the clean line that is removes alls comments, double spaces, tabs, and trailing whitespaces. if the line is empty or it only has comments it returns "0" indicating there is not thing to be read in that line. Can be called from any class and maintains the scanner position throughout
--> getDescription(): Takes the scanner and reads the number of lines in description and loops and for that many times and gets all the description lines and returns the string with all the description lines from the file correctly formated (new line). The function can be used by Artifact class and character since the the they follow the same format for description as Places.

-----------------------------------------------------------------------------

DecisionMaker Interface:

Contains the declaration of the function getMove that will be used by ArtificialDecision and UserDecision. This is an interface class.

-----------------------------------------------------------------------------

Direction Class:

Holds the enum DirType with 18 different directions and 1 for NONE incase the a room leads nowhere.
The enum has following functions:

--> toString: returns the text version of the direction
--> match(String): returns true if the string is same as text or abbr
--> convert: converts the direction string in to DirType to maintain compatibility with other functions.
--> tagToString: converts a direction tag to it's String. Only used for randomizing NPC directions

-----------------------------------------------------------------------------

Game Class:

Runs for infinite time asking swtiching between characters and calling makeMove on them. This class for this project is the starting point which will delegate things to the other classes including asking and making moves that the user will provide. This no longer constains a arraylit of user artifacts since each character will have it's on collection now. This class also no longer has getCurrPlace() since each character will have it's own currPlace. Game will now have static collection of all characters so it can cycle through them and make moves based on User or AI moves.

Game has one new function:
 --> charUpdate(): Removes character when from the personal list when a character (player or NPC) exits the game.

Key Addition:

Game(Scanner, double): Loops until there are lines in the file that can be read. It will divide the the different sections of the file into PLACES, DIRECTIONS, and ARTIFACTS by looking for those words in each line. Once found it will go through the switch case and call constructor for that object until it has created an constructor for all the of that object type. This is done by keeping track of how many were created and how many are there in file. Each class has a public static counter that counts how many objects were created.

---- Added ability to read, store and create Characters from the data file.

-----------------------------------------------------------------------------

GameTester Class:

To start the program, open the file, initialize the scanner to read the file. Will pass thethe scanner off to game class where it will be used to add places, direction and artifacts. Takes in command line argument as the file and if it can't open that file will ask the user to input the file again. The only new addition in this class is implementation of opening and loading the Game Data File and the Scanner to read. When the program begins it will try to load  the file passed in at through the command line and if it can't open that file it will prompt the user to input the file name again.

New Additions: Game Tester now has abitlity to take in number of players the user wants to start the game as and if the GDF didn't have that many players it will create additional to satisfy that need.

-----------------------------------------------------------------------------

KeyboardScanner Class:

KeyboardScanner class: This is a Singleton class that will return a Scanner to read from the keyboard. If a Scanner already exist for this it will call return that if not it will create a new scanner and return it.

-----------------------------------------------------------------------------

Move Class:

Holds the MoveType Enum that has a list of moves either a player or a NPC can make. The Enum has type() method which takes in a string and returns MoveType that it matches. Move constructor takes in a string and generates a Move getting the MoveType associated to it and the arguments passed through that string. Other than this Move also has to methods to return MoveType and agruements.

-----------------------------------------------------------------------------

NPC class:

NPC is child of Character and it inherits most if not all of it functions and variables. When creating a character for GDF, the game will check if the character is NPC or player and then create that character by creating either a NPC or Player constructor which in turn will call Character constructor. This way Character has a list of all the characters of either type NPC or Player.

-----------------------------------------------------------------------------

Place Class: 
Holds the the ArrayList for Directions, Artifacts and Places. Place ArrayList is static so that it can be accessed from all the classes. Additionally has holds two static functions -- getDescription and getPlaceByID so that they can be called from other classes to have reusable functions. Descriptions for the functions are provided below. Added 4 other functions.

Key Additions:

static getDescription(Scanner):
Takes the scanner passed by Place() and reads the number of lines in description and loops and for that many times and gets all the description lines and returns the string with all the description lines from the file correctly formated (new line). The function can be used by Artifact class since the the Artifacts follow the same format for description as Places.

static getPlaceByID(int):
This function is used to get Place from ID. Called from direction class to get the "to" and "from" places for the direction. Returns the Place that has the same ID as the ID sent in.

Other New Functions:
 --> addArtifacts - adds the artifacts to the this places collection			 --> useKey - uses the key send in from artifacts class on all the directions in this place
 --> pickArtifact - takes in a string and returns the artifact with that name and removes that artifact from the collection to indicated user has picked up the item
 --> lookArtifact - prints out the artifacts in the places collection

New Additions: The only new Addition in this is class that Place now has a list of characters that are present in that place. This gets updated as Characters move from place to place.

-----------------------------------------------------------------------------

Player Class:

Player is child of Character and it inherits most if not all of it functions and variables. When creating a character for GDF, the game will check if the character is NPC or player and then create that character by creating either a NPC or Player constructor which in turn will call Character constructor. This way Character has a list of all the characters of either type NPC or Player.

-----------------------------------------------------------------------------

UserDecision Class:

This class implements DecisionMaker for the Player. This will ask the user to put in the move they want to make and then create an Move using that input and return it.

_____________________________________________________________________________