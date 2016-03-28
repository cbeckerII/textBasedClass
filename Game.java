/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.08.10
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room porchFront, foyerGrand, hallDining, roomSun, study, kitchen, cellar, gardenGrand, graveyard, 
        mausoleum, hallwayUpstairs, attic, closetStorage, bedroomMaster, bathroomMaster, terrace, bedroomGuest, bathroomGuest;
       
        // create the rooms
        porchFront = new Room("outside the main entrance of the university");
        foyerGrand = new Room("in a lecture theater");
        hallDining = new Room("in the campus pub");
        roomSun = new Room("in a computing lab");
        study = new Room("in the computing admin office");
        kitchen = new Room ("DUMMY ROOM");
        cellar = new Room ("DUMMY ROOM");
        gardenGrand = new Room ("DUMMY ROOM");
        graveyard = new Room ("DUMMY ROOM");
        mausoleum = new Room ("DUMMY ROOM");
        hallwayUpstairs = new Room ("DUMMY ROOM");
        closetStorage = new Room ("DUMMY ROOM");
        bedroomMaster = new Room ("DUMMY ROOM");
        bathroomMaster = new Room ("DUMMY ROOM");
        terrace = new Room ("DUMMY ROOM");
        bedroomGuest = new Room ("DUMMY ROOM");
        bathroomGuest = new Room ("DUMMY ROOM");
        attic = new Room ("DUMMY ROOM");
        
        // initialise room exits
        porchFront.setExit("north", foyerGrand);
        
        foyerGrand.setExit("north east", hallwayUpstairs);
        foyerGrand.setExit("north west", hallwayUpstairs);
        foyerGrand.setExit("west", hallDining);
        foyerGrand.setExit("east", roomSun);
        
        hallDining.setExit("east", foyerGrand);
        hallDining.setExit("north", kitchen);
        
        kitchen.setExit("south", hallDining);
        kitchen.setExit("west", cellar);
        kitchen.setExit("north", gardenGrand);
        
        cellar.setExit("east", kitchen);
        
        roomSun.setExit("west", foyerGrand);
        roomSun.setExit("north", study);
        
        study.setExit("south", roomSun);
        study.setExit("north west", gardenGrand);
        
        gardenGrand.setExit("south west", kitchen);
        gardenGrand.setExit("south east", study);
        gardenGrand.setExit("east", graveyard);
        
        graveyard.setExit("west", gardenGrand);
        graveyard.setExit("east", mausoleum);
        
        mausoleum.setExit("west", graveyard);
        
        hallwayUpstairs.setExit("north", closetStorage);
        hallwayUpstairs.setExit("north east", bedroomMaster);
        hallwayUpstairs.setExit("up", attic);
        hallwayUpstairs.setExit("north west", bedroomGuest);
        hallwayUpstairs.setExit("sout west", foyerGrand);
        hallwayUpstairs.setExit("south east", foyerGrand);
        
        attic.setExit("down", hallwayUpstairs);
        
        closetStorage.setExit("south", hallwayUpstairs);
        
        bedroomMaster.setExit("south", hallwayUpstairs);
        bedroomMaster.setExit("east", bathroomMaster);
        bedroomMaster.setExit("north", terrace);
        
        terrace.setExit("south", bedroomMaster);
        terrace.setExit("down", gardenGrand);
        
        bathroomMaster.setExit("west", bedroomMaster);
        
        bedroomGuest.setExit("south", hallwayUpstairs);
        bedroomGuest.setExit("west", bathroomGuest);
        
        bathroomGuest.setExit("east", bedroomGuest);
        
        
        
        currentRoom = porchFront;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
