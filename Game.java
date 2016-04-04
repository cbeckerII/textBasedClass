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
     * Create the game and initialise its internal map (AND OBJECTS).
     */
    public Game() 
    {
        createRooms();
        createObjects();
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
        porchFront = new Room("The front porch smells of Lilacs and Roses with a hint of wet dog.", "on the front porch of an intimidating mansion with various flowers spread about. It is currently raining");
        foyerGrand = new Room("The smell of this room is giving you vibes of your grandparent's summer home.", "in the grand foyer of this beautiful mansion. Above your head is a chandelier");
        hallDining = new Room("It smells quite nice in here like freshly prepared food.", "standing in the dining hall");
        roomSun = new Room("This room smells like someone caked the walls in sunscreen.", "in what appears to be a sun room... that is if there were any sun");
        study = new Room("The smell of dust and books is all you can make out.", "within a study cluttered with several books and other various junk");
        kitchen = new Room ("The room is polluted with the smell of rotting carcasses.", "inside the kitchen, there are various animal carcasses scattered about");
        cellar = new Room ("This room smells heavily of bleach.", "in a dark cellar, but you can make out that there are several-hundred wine bottles behind a thick pane of glass");
        gardenGrand = new Room ("Your nose is assaulted by the scent of hundreds of wet flowers.", "standing in a garden populated by a variety of foliage and insects");
        graveyard = new Room ("There isn't much of a particular scent you can make out.", "within a graveyard on the property. It is strange that there are so many tomb stones");
        mausoleum = new Room ("It smells just like moth balls, and old people. Ew.", "inside of a mausoleum. It is quite spooky");
        hallwayUpstairs = new Room ("It smells strongly of moth balls and the elderly.", "standing within a hallway");
        closetStorage = new Room ("It smells like wet dog in here....", "in a closet. Why?");
        bedroomMaster = new Room ("The room smells overwhelmingly of cigarette smoke.", "inside of the master bedroom. The bed is quite lovely draped in red silk sheets");
        bathroomMaster = new Room ("Oh god. It smells like nobody has flushed in months.", "in the master bathroom. It doesn't appear to have been cleaned in years");
        terrace = new Room ("The terrace smells mostly like wet stone. Very earthy.", "standing atop the terrace. There seems to be a patch of daisies you could land in if you needed to jump off safely");
        bedroomGuest = new Room ("The room has no discernable scent.", "in the guest bedroom. It is not quite as nice as you would imagine... there isn't even a bed, just a wardrobe");
        bathroomGuest = new Room ("It smells like a wide variety of chemicals.", "in the guest bathroom. It at least has a toilet and shower. It also appears to not be clean");
        attic = new Room ("You make out what you believe to be the smell of bat crap.", "in the attic. It's very difficult to see up here at the moment");
        
        // initialise room exits
        
        porchFront.setExit("North", foyerGrand);
        
        foyerGrand.setExit("NorthEast", hallwayUpstairs);
        foyerGrand.setExit("NorthWest", hallwayUpstairs);
        foyerGrand.setExit("West", hallDining);
        foyerGrand.setExit("East", roomSun);
        
        hallDining.setExit("East", foyerGrand);
        hallDining.setExit("North", kitchen);
        
        kitchen.setExit("South", hallDining);
        kitchen.setExit("West", cellar);
        kitchen.setExit("North", gardenGrand);
        
        cellar.setExit("East", kitchen);
        
        roomSun.setExit("West", foyerGrand);
        roomSun.setExit("North", study);
        
        study.setExit("South", roomSun);
        study.setExit("North West", gardenGrand);
        
        gardenGrand.setExit("SouthWest", kitchen);
        gardenGrand.setExit("SouthEast", study);
        gardenGrand.setExit("East", graveyard);
        
        graveyard.setExit("West", gardenGrand);
        graveyard.setExit("East", mausoleum);
        
        mausoleum.setExit("West", graveyard);
        
        hallwayUpstairs.setExit("North", closetStorage);
        hallwayUpstairs.setExit("NorthEast", bedroomMaster);
        hallwayUpstairs.setExit("Up", attic);
        hallwayUpstairs.setExit("NorthWest", bedroomGuest);
        hallwayUpstairs.setExit("soutWest", foyerGrand);
        hallwayUpstairs.setExit("SouthEast", foyerGrand);
        
        attic.setExit("Down", hallwayUpstairs);
        
        closetStorage.setExit("South", hallwayUpstairs);
        
        bedroomMaster.setExit("South", hallwayUpstairs);
        bedroomMaster.setExit("East", bathroomMaster);
        bedroomMaster.setExit("North", terrace);
        
        terrace.setExit("South", bedroomMaster);
        terrace.setExit("down", gardenGrand);
        
        bathroomMaster.setExit("West", bedroomMaster);
        
        bedroomGuest.setExit("South", hallwayUpstairs);
        bedroomGuest.setExit("West", bathroomGuest);
        
        bathroomGuest.setExit("East", bedroomGuest);
        
        //Initializes room names so they can be called for easy reference.
        
        porchFront.setName("Front Porch");
        
        foyerGrand.setName("Grand Foyer");
        
        hallDining.setName("Dining Hall");
        
        cellar.setName("Cellar");
        
        kitchen.setName("Kitchen");
        
        roomSun.setName("Sun Room");
        
        study.setName("Study");
        
        gardenGrand.setName("Grand Garden");
        
        graveyard.setName("Graveyard");
        
        mausoleum.setName("Mausoleum");
        
        hallwayUpstairs.setName("Upstairs Hallway");
        
        attic.setName("Attic");
        
        closetStorage.setName("Storage Closet");
        
        bedroomMaster.setName("Master Bedroom");
        
        terrace.setName("Terrace");
        
        bathroomMaster.setName("Master Bathroom");
        
        bedroomGuest.setName("Guest Bedroom");
        
        bathroomGuest.setName("Guest Bathroom");
        
        currentRoom = porchFront;  // start game outside
    }
    
    private void createObjects()
    {
        Objects watch, pendant;
        
        //Initializes objects (Name, and then Description)
        pendant = new Objects("Pendant", "An old pendant.");
        watch = new Objects("Pocket Watch", "A rusted gold pocket watch.");
        
        
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
        System.out.println("Welcome to the mansion of Count Laurel the Fourth!");
        System.out.println("This mansion is so old that the townsfolk don't remember it ever not being here.");
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
                
            case SMELL:
                smellThing(command);
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
        System.out.println("around the mansion.");
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
    
    private void smellThing(Command command)
    {
        String name = currentRoom.getName();
        if (!command.hasSecondWord()){   
            System.out.println(currentRoom.getScent());            
            return;
        }
        
        else {
                if (name.equals("Front Porch")){
                    System.out.println("Smelly");
                    return;
                }
        }
    }
    
    private void taste(Command command)
    {
        String name = currentRoom.getName();
        if(!command.hasSecondWord()){
        System.out.println("Lick what?");
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
