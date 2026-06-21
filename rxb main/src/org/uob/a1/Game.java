package org.uob.a1;

import java.util.Scanner; 

public class Game {  
    
    public static void main(String args[]) {
		/* REMINDERS
		DO NOT JAVAC JUST RUN USING GIVEN COMMAND
		*/
		
		/*
		The Game class runs the main game loop. You can create any methods you feel you
		require but you need to use all the other classes to make the game work. The only
		required method is:
			• public static void main(String[] args)
		You can write this code in any way you want to but here is a hint for a possible
		approach:
			• Create some Room objects to store information about each Room in your game.
			• Create Inventory and Score objects.
			• Use a while loop for the main game loop.
			• Inside this loop use an if statement to check what commands the user has typed.
		Based on the command, the Room the user is in and what items the user has in
		their inventory output a different response and update the Inventory, Score and
		Map information if appropriate.

		The following is a minimum list of commands the game must be able to parse (values
		in angle brackets refer to arguments given to a command):
			• "move <direction>" - (<direction> can be "north", "south", "east", "west"). The
				player moves to a new room based on the direction.
			• "look" - Displays a description of the room the player is in.
			• "look <feature>" - Displays a more detailed description of a feature in a room.
				A feature is a fixed object in the room.
			• "look <item>" - Displays a description of an item. This should only work if the
				item is in the player’s inventory.
			• "inventory" - Displays a list of all items the player has obtained.
			• "score" - Displays the user’s current score.
			• "map" - Displays a text-based map of the current explored game world.
			• "help" - Displays a help message.
			• "quit" - Quits the game.
		*/

		/* GIT COMMANDS:
		./run.sh
		./test.sh
		git status
		git add .
		git commit -m "[Name of Commit]"
		git push
		*/

		/* NARRATIVE:
		Manor House Escape
			Goal:
				Finding a key to unlock the door to escape

			Puzzles:
				Numbers to unlock something - 3 numbers
				Rotating sword on the wall

			Gameplay:
				2 items being found in rooms */
		boolean gameOver = false;
		boolean southWingUnlocked = false;
		/*	
			Map layout:
				.....
				.....
				  .
				.....
				.....
        */
		Map map = new Map(5,5);
		
			//Inventory
		Inventory playerInv = new Inventory();
			//Score
		Score score = new Score(20);
			//PlayerPosition
		Position playerPos = new Position(2,2);
			// Items:
		Item[] items = new Item[11];
		
			//Rooms:
		Room[] rooms = new Room[17];
		
			//	Entrance - Note
		Position entrancePos = new Position(2,2);
        Room entrance = new Room("Entrance", "You are in a big bright entry hall, to your right there stands a small table with a piece of paper on. A family portrait lies looming above the entrance. There are two big doors, one to your left and one to your right, above each they are entitled North and South Wing. You hear a noise from the South Wing.", '⎆', entrancePos);
		map.placeRoom(entrancePos, entrance.getSymbol());
		rooms[0] = entrance;
		Item entranceNote = new Item("paper", entrancePos, false, "Thank you for watching our house whilst we are away! Could you go around the rooms, checking everything is in order. We forgot to unlock the South Wing because we were in a hurry, the key is in the safe in our bedroom, Have fun!");
		items[0] = entranceNote;
		
			//	Kitchen - S - Apples x3
		Position kitchenPos = new Position(4,1);
		Room kitchen = new Room("Kitchen", "You are in a bright farmhouse kitchen with wood counter tops and a kitchen island in the middle, there's a bowl of apples on the counter.", '⌂', kitchenPos);
		rooms[1] = kitchen;
		Item apples = new Item("apples", kitchenPos, false, "Apples.");
		items[1] = apples;
		
			//	Pantry - S -
		Position pantryPos = new Position(4,0);
		Room pantry = new Room("Pantry", "You are in a small dingy pantry, piled high with kitchen supplies, cans, spices and other cooking ingredients.", '▦', pantryPos);
		rooms[2] = pantry;
		
			//	Master Bedroom - N - Mirror (to show a num on inspection), Safe, South Wing Key, Painting
		Position masterBedPos = new Position(0,3);
		Room masterBed = new Room("Master Bedroom", "You are in the master bedroom, a lavish furnished bedroom, you notice a mirror stood on the bedside table. A safe peeking out from underneath the table.", '◉', masterBedPos);
		rooms[3] = masterBed;
		Item mirror = new Item("mirror", masterBedPos, true, "You look in the mirror and see the painting on the wall, you think you should go look at it.");
		items[2] = mirror;
		Item safe = new Item("safe", masterBedPos, true, "Locked safe... seems to be a 3 digit code. ['unlocksafe' to unlock the safe]");
		items[3] = safe;
		Item southWingKey = new Item("South Wing Key", masterBedPos, false, "The key unlocks the South Wing.");
		items[4] = southWingKey;
		Item painting = new Item("painting", masterBedPos, true, "The painting shows a pretty landscape and 3 birds flying across the sky.");
		items[5] = painting;
		
			//	Master Ensuite - N - 
		Position masterEnPos = new Position(0,4);
		Room masterEn = new Room("Master Ensuite", "You are in a glorious bathroom, fitting for the master bedroom.", '▤', masterEnPos);
		rooms[4] = masterEn;
		
			//	Kids Room - N - 
		Position kidsPos = new Position(0,1);
		Room kidsRoom = new Room("Kids Room", "You are in a kids room, its littered with toys. You notice something in the adjoining playroom!", '○', kidsPos);
		rooms[5] = kidsRoom;
		
			//	Playroom - N - birthday balloons
		Position playroomPos = new Position(0,0);
		Room playroom = new Room("Playroom", "You are in the messy playroom, it is covered in toys just like the kids bedroom! There is a number 7 balloon, you remember its the family's child's birthday. That's why they're away!", '★', playroomPos);
		rooms[6] = playroom;
		Item birthdayBalloons = new Item("Birthday Balloons", playroomPos, true, "Number 7 balloons for the family child's birthday");
		items[6] = birthdayBalloons;
		
			//	Dining Room - S - Dirty Rag
		Position diningPos = new Position(3,3);
		Room diningRoom = new Room("Dining Room", "You are in the dining room, a spectacularly detailed and well furnished room with a long wooden table. At one end is a dirty rag, seems a little out of place.", '█', diningPos);
		rooms[7] = diningRoom;
		Item dirtyRag = new Item("rag", diningPos, false, "The caretaker's dirty cleaning rag.");
		items[7] = dirtyRag;

		
			//	Guest Bedroom - S -
		Position guestBedPos = new Position(4,3);
		Room guestBed = new Room("Guest bedroom", "You are in the guest bedroom, its nowhere near as nice as the master bedroom. It's barely furnished, must be why no one stays over!", '☐', guestBedPos);
		rooms[8] = guestBed;
		
			//	Guest Ensuite - S - Bleach
		Position guestEnPos = new Position(4,4);
		Room guestEn = new Room("Guest Ensuite", "You are in the guest bedroom ensuite, its bare of any toiletries except for a bottle of bleach, seems a little strange.", '▤', guestEnPos);
		rooms[9] = guestEn;
		Item bleach = new Item("bleach", guestEnPos, false, "The caretaker's bleach bottle.");
		items[8] = bleach;
		
			//	Pet Room - N - Dog treats x5
		Position petPos = new Position(1,1);
		Room petRoom = new Room("Pet Room", "You are in the pet room, you're hit by the smell of wet dog. Gross. You notice 5 treats left on the side, where's the dog?", '⌁', petPos);
		rooms[10] = petRoom;
		Item dogTreats = new Item("treats", petPos, false, "Dog treats, maybe the dog would like them.");
		items[9] = dogTreats;
		
			//	Art Room - S - 
		Position artPos = new Position(3,1);
		Room artRoom = new Room("Art Room", "You are in a luxurious marble floored gallery with basalt columns holding up sculptures and lots of gold framed paintings on the wall. The caretaker is in here, you think you should go talk to him.", '⎔', artPos);
		rooms[11] = artRoom;
		Item caretaker = new Item("caretaker", artPos, true, "Thank you for opening the door to the art room. I got trapped in here yesterday! ... Oh you're trapped here too, I have a spare key to get out. But first I need you to go find me my [rag], [bleach] and bring me three [apples], then use [giveitems] to get the key from me.");
		items[10] = caretaker;
		
			//	Garden - N -
		Position gardenPos = new Position(1,3);
		Room garden = new Room("Garden", "You are in a small well maintained garden with lots of shrubbery.", '☘', gardenPos);
		rooms[12] = garden;
		
			//	HallwayN1 - N -
		Position hallwayN1Pos = new Position(1,2);
		Room hallwayN1 = new Room("Hallway", "You are in the North Wing hallway.", '▫', hallwayN1Pos);
		rooms[13] = hallwayN1;
		
			//	HallwayN2 - N - 
		Position hallwayN2Pos = new Position(0,2);
		Room hallwayN2 = new Room("Hallway", "You are in the North Wing hallway.", '▫', hallwayN2Pos);
		rooms[14] = hallwayN2;
		
			//	HallwayS1 - S 
		Position hallwayS1Pos = new Position(3,2);
		Room hallwayS1 = new Room("Hallway", "You are in the South Wing hallway.", '▫', hallwayS1Pos);
		rooms[15] = hallwayS1;
		
			//	HallwayS2 - S -
		Position hallwayS2Pos = new Position(4,2);
		Room hallwayS2 = new Room("Hallway", "You are in the South Wing hallway.", '▫', hallwayS2Pos);
		rooms[16] = hallwayS2;

		
		// pregame story info print
		System.out.println("You are here to babysit the house whilst the owners away, you step inside to begin the checks but the door closes behind you... you hear it lock and realise you left the key in the door! You'll have to find a spare to get out!");
		
		// display starting map
        System.out.println(map.display());
		
		// create scanner to get input command
		Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Type 'help' for commands.");
		// Main game loop
		while(!gameOver) {
			
			System.out.print("Enter Command: "); 

            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
            } 
			else {
                break; 
            }

            // normalise and split the input into parts
            // parts[0] is the command, parts[1] is the argument (if present)
            String[] parts = input.trim().toLowerCase().split("\\s+", 2);
            
            if (parts.length == 0 || parts[0].isEmpty()) {
                continue; 
            }

            String command = parts[0];
            // argument is everything after the first word, or an empty string if nothing follows
            String argument = parts.length > 1 ? parts[1] : ""; 

            // switch statement to handle the main command
            switch (command) {
                case "move":
                    // expects <direction>
					//debug to check argument
					//System.out.println(argument);
					// each direction changes the position then checks if it is a valid move, if not reverses the change, if so visits the room and updates the symbol on the map
					switch(argument) {
						case "north": 
							playerPos.x -= 1;
							if(map.returnSymbol(playerPos) == '!') {
								System.out.println("This is not a valid move!");
								playerPos.x += 1;
							}
							else if( map.returnSymbol(playerPos) == ' ') {
								System.out.println("This is not a valid move!");
								playerPos.x += 1;
							}
							else {
								// iterate through rooms to find the one we just moved into
								for(Room room : rooms) {
									if((room.getPosition()).x == playerPos.x && (room.getPosition()).y == playerPos.y) {
										map.placeRoom(room.getPosition(), room.getSymbol());
										score.visitRoom();
									}
								}
							}
							break;
						case "east": 
							playerPos.y += 1;
							if(map.returnSymbol(playerPos) == '!') {
								System.out.println("This is not a valid move!");
								playerPos.y -= 1;
							}
							else if( map.returnSymbol(playerPos) == ' ') {
								System.out.println("This is not a valid move!");
								playerPos.y -= 1;
							}
							else {
								// iterate through rooms to find the one we just moved into
								for(Room room : rooms) {
									if((room.getPosition()).x == playerPos.x && (room.getPosition()).y == playerPos.y) {
										map.placeRoom(room.getPosition(), room.getSymbol());
										score.visitRoom();
									}
								}
							}
							break;
						case "south": 
							playerPos.x += 1;
							if(map.returnSymbol(playerPos) == '!') {
								System.out.println("This is not a valid move!");
								playerPos.x -= 1;
							}
							else if( map.returnSymbol(playerPos) == ' ') {
								System.out.println("This is not a valid move!");
								playerPos.x -= 1;
							}
							else {
								// Check if they have access to the south wing
								if(!southWingUnlocked && playerPos.x == 3) {
									System.out.println("The South Wing is locked! Find the key to unlock it!");
									playerPos.x -= 1;
								break;
							}
								// iterate through rooms to find the one we just moved into
								for(Room room : rooms) {
									if((room.getPosition()).x == playerPos.x && (room.getPosition()).y == playerPos.y) {
										map.placeRoom(room.getPosition(), room.getSymbol());
										score.visitRoom();
									}
								}
							}
							break;
						case "west": 
							playerPos.y -= 1;
							if(map.returnSymbol(playerPos) == '!') {
								System.out.println("This is not a valid move!");
								playerPos.y += 1;
							}
							else if( map.returnSymbol(playerPos) == ' ') {
								System.out.println("This is not a valid move!");
								playerPos.y += 1;
							}
							else {
								// iterate through rooms to find the one we just moved into
								for(Room room : rooms) {
									if((room.getPosition()).x == playerPos.x && (room.getPosition()).y == playerPos.y) {
										map.placeRoom(room.getPosition(), room.getSymbol());
										score.visitRoom();
									}
								}
							}
							break;
						default:
							System.out.println("Please specify a direction");
							break;
					}
                    break;
                case "look":
                    // expects optional <feature> or <item>
					// if no argument required return the rooms description
					// if it does check the argument against the item names and prints the descriptions
					boolean found = false;
					if(argument == "") {
						for(Room room : rooms) {
							// iterate through rooms to find the one that matches where the player is stood
							if((room.getPosition()).x == playerPos.x && (room.getPosition()).y == playerPos.y) {
								System.out.println(room.getDescription());
							}
						}
					}
					else {
						for(Item item : items) {
							// find item and check it is in that room
							if(argument.equals(item.getName()) && playerPos.x == (item.getPosition()).x && playerPos.y == (item.getPosition()).y) {
								System.out.println(item.getDescription());
								found = true;
							}
						}
						if(found == false) {
							System.out.println("Enter a valid item or feature! Check capitalisation or spelling.");
						}
					}
					
                    break;
				case "pickup":
					// pick up an item
					// check an item is specified, return based on the name and add to inventory
					boolean pickedUp = false;
					if(argument == "") {
						System.out.println("pickup requires a valid <argument>");
					}
					else {
						for(Item item : items) {
							// find item and check it is in that room
							if(argument.equals(item.getName()) && playerPos.x == (item.getPosition()).x && playerPos.y == (item.getPosition()).y && !item.isFeature()) {
								playerInv.addItem(item.getName());
								System.out.println("Picked up " + item.getName() + ".");
								pickedUp = true;
							}
						}
						if(pickedUp == false) {
							System.out.println("Enter a valid item! Features can't be picked up. Check capitalisation or spelling for items.");
						}
					}
					break;
                case "inventory":
                    System.out.println(playerInv.displayInventory());
                    break;
				case "removeitem":
					playerInv.removeItem(argument);
					break;
                case "score":
                    System.out.println("Current score: " + score.getScore());
                    break;
                case "map":
                    System.out.println(map.display());
                    break;
                case "help":
                    System.out.println("Available commands: move <direction>, look, look <feature>, look <item>, inventory, removeitem <item>, score, map, help, quit");
                    break;
				case "unlocksafe":
					// update the south wing to be unlocked depending on if the safe is unlocked
					southWingUnlocked = unlockSafe(playerPos);
					if(southWingUnlocked) {
						score.solvePuzzle();
						playerInv.addItem("South Wing Key");
					}
					break;
				case "giveitems":
					// if the player has the items then pass through to the puzzle to be finished and game to end
					if(playerInv.hasItem("rag") != -1 && playerInv.hasItem("bleach") != -1 && playerInv.hasItem("apples") != -1) {
						gameOver = giveItems(playerPos);
					}
					if (gameOver) {
						score.solvePuzzle();
						System.out.println("Final score: " + score.getScore());
					}
					break;
                case "quit":
                    scanner.close();
                    gameOver = true; // exit the loop
					break;
                default:
                    System.out.println("Unknown command: '" + command + "'. Type 'help' for a list of commands.");
            }
		}
        /*
			Ideas:
				Start in main entrance
				Explore North wing first - 2 puzzles and 1 relevant item to unlock South wing
				South wing - caretaker locked in and wants you to help him in exchange for the key to escape the haunted house
				Backtrack to find items that he asks for (potentially piece of paper as a memo) and then go get more stuff (could've already decided to pick them up)
				Just as the caretaker gives you the key, the house's dog steals the key. Find the dog and offer a treat.
				
				Pieces of paper to give details

			Narrative:
				Enter the house as you are checking on it whilst the owners are away for the summer
				left the key in your car and the main door shut behind you. 
				You find a note with details about house sitting, south wing is locked but key in master bedroom.
				You hear a noise in south wing but the door is locked.
				You go through north wing to find the master bedroom. Pickup any items at will
				Once in master bedroom inspect and finds safe. 3 digits, key must be inside. 
				Look around north wing for numbers (3 digits). First is found in mirror reflection, second is in the kids playroom in the form of a birthday balloon/anniversary card, third is in the pet room 5 pet treats left out.
				Key is in safe, they have to trial and error through 8 combinations. Unlocks south wing.
				Once in south wing you investigate the noise, the cleaner was stuck in the art room.
				In exchange for the key to exit the house, the cleaner wants some items (Apples x3, Dirty rag, Bleach)
				Return the items for the key, but the dog takes it and runs to garden. Will only return the key in exchange for treats.
				Get dog treats from pet rom and get key and free cleaner and leave.
		*/
    }

		// puzzle 1
		// unlock the safe to open the next area
		public static boolean unlockSafe(Position pos) {
			if(pos.x != 0 && pos.y != 4){
				System.out.println("You have to be in the master bedroom to unlock the safe!");
				return false;
			}
			Scanner scanner = new Scanner(System.in);
	        int input;
			System.out.println("Enter the safe code: ");
			input = scanner.nextInt();
			if(input == 375) {
				System.out.println("Safe unlocked!");
				System.out.println("South Wing Key acquired, it is now unlocked!");
				return true;
			}
			else {
				System.out.println("Wrong code!");
			}
			return false;
		}

		// puzzle 2
		// end the game by giving items to caretaker
		public static boolean giveItems(Position pos) {
			if(pos.x != 3 && pos.y != 1){
				System.out.println("You have to be in the art room to give the items!");
				return false;
			}
			System.out.println("Thank you for bringing me the items, here is the key to get out. Thank you for your help!");
			return true;
		}
}