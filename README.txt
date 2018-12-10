Group # 34

Authors:
Luke Paltzer
Karol Stolarski
Ayush Patel

Version 5.0:

In this version, several new features have been implemented.
There is now the ability to craft items. If a player finds a recipe
and the necessary materials to craft an item, they can use the craft
command to build it. These items can be used in various ways in the game
environment. This includes weapons to fight other denizens or armor
to increase their durability in the treacherous catacombs.
Potions may have restorative properties or augment a player's 
abilities.

There are also new types of NPC's that roam the dungeons.
They are able to cast spells and take or deal damage to one another.
At the beginning of the game, there is a prompt to choose the amount
of characters that are active. Some of these are non-playable but
one may or may not run into them at some point.

Some of the places in the game also have an effect on adventurers.
One should proceed with caution, as some doors are booby-trapped
and may cause the player to lose health. Some rooms are dark and
will require a torch to illuminate them. There is also a portal
that will send the player back to the starting place of the game, but only once.


Version 4.0:

The Game now has different characters that are able to roam around
the game space. Note that commands can only be executed with one argument
at a time. Also if one of the players exits the game, the whole game is over.
Running the program as previous versions works. AI control the Non-player
characters and they all interact with the environment.


Version 3.0:

The player may now discover and pick up items in the environment.
Some of these items are keys, which have the ability to be used
on door locks, provided that the key pattern matches the lock pattern.
There are also master-keys that are supported; these will open
any door. They player also has a health bar and an carrying
capacity limit. If the player has less than 100 health, they
can drink potions to replenish their health bars. There is also a
limit on carrying capacity, so the player must carefully choose
which items are worth picking up. If the player picks up a leather
bag, this will support more items to be carried.

SETUP: Running the program with a filename (WITH NO SPACES)
as a system argument should parse the file and set up the game
for the player. Otherwise, a filename will need to be provided
by the player via standard input.


Version 1.0:

This program runs from the GameTester class. It has a hardcoded set of places
that make up the game world. For now, a player is able to move
from place to place and explore the game world. Upon start of
the game, the player will see a name and description of the
current place they are in. They are able to execute the following commands:
Look (returns a name and description of the current room),
Quit/Exit (These will terminate the game), or they can provide a direction
(full word or just the first character) to go in, optionally preceded by
the word "go". The directions supported currently are the four main
cardinal directions, as well as up and down, as we were told that is
all we need for the current deliverable. If a player chooses to
move in a direction, it must satisfy two criteria: (1) that it is
a direction that has a way out of the current place you're in,
(2) if the direction exists and is valid, that it is also unlocked.
Locked directions are inaccessible. All commands the player
inputs are case insensitive so the player so so long as the player
adheres to the input criteria, case shouldn't matter. Exceptions are
thrown when attempting to follow a locked direction.