import java.util.Date;
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
    private Objects currentObject;
    private Date date = new Date();
    public long time;
    public boolean win = false;
        
    /**
     * Create the game and initialise its internal map (AND OBJECTS).
     */
    public Game() 
    {
        createRooms();
        createObjects();
        time = date.getTime();
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
        porchFront = new Room("The front porch smells of Lilacs and Roses with a hint of wet dog.", "on the front porch of an intimidating mansion with various flowers spread about. There is a knife on the porch. It is currently raining");
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
        Objects watch, pendant, key, knife, carcass, chair, armor, camera, chemicals, puzzleHintOne, puzzleHintTwo, puzzleHintThree, flowers,
        toiletMaster, sinkGuest, tombStone, rope, wardrobe;
        
        //Initializes objects (Name, Description, Scent, Taste, Use, Hit)
        pendant = new Objects("Pendant", "An old pendant.", "It smells like brass.", "It tastes rather metallic.", "There is an old photo inside of the pendant.", "The metallic exterior hurts your hand as you punch it.");
                
        watch = new Objects("Watch", "A rusted gold pocket watch.", "It smells of memories, but seriously it smells like metal.", "You can taste how stupid it is to be licking a pocket watch.", 
        "The pocket watch seems to be stuck on 11:59.", "Hitting the watch appears to have little affect on it.");
                   
        key = new Objects("Key", "A shiny brass key.", "This key smells an awful lot like rotting carcass.", "The key tastes inconceivably awful. You gag.", 
        "You take the key. You now have a key.", "You hurt yourself by punching the key. Congratulations genius!");
        
        knife = new Objects("Knife", "A sharp and smelly knife coated with blood.", "The knife smells lovely like fresh cut roses", "There is no way you're going to lick this knife. You might cut yourself.",
        "You take the knife. You have a knife now.", "You decide punching a knife wouldn't be the smartest thing you could do.");             
        
        carcass = new Objects("Carcass", "There is a corpse, with a sword run through it. It is unrecognizable.", "The carcass absolutely reeks, it must have been here for weeks.", "You'd have to be crazy to lick that. No. Ew. No.", "Use", "Hit");
        
        chair = new Objects("Chair", "An Oak chair with a nice velvet cushion.", "The chair smells like the food that may have been laid at the table.", "The chair tastes rather woodlike.", "You sit down briefly before feeling uncomfortable and standing right back up.", "You punch the chair. Nothing Happens.");
        
        armor = new Objects("Armor", "Description", "It smeels like armor. Metal.", "Why would you lick that?", "You shine the armor and look at your reflection ion it. Aren't you good lookin?", "You kick the armor and hurt your foot stumbling about.");
        
        camera = new Objects("Camera", "An old timey camera.", "It smells like vanilla?", "You decide it'd be stupid to lick that and dont.", "Use", "You punch the camera, but its sharp corners indent your knuckles bruising them.");
        
        chemicals = new Objects("Chemicals", "A variety of cleaning solutions.", "It smells like bleach.", "You decide it'd be dumb to lick that.", "You pour some cleaning solution out. Nothing extravagent happens.", "You hit the bottle of chemicals. Nothing happened.");
        
        puzzleHintOne = new Objects("Letter", "The letter reads that the answer is found within yourself.", "Smells like paper.", "Taste", "The paper resists your advances to 'use' it.", "Why would you punch a letter?");
        
        puzzleHintTwo = new Objects("Parchment", "The parchment says that the heart holds the key.", "Smells like paper... that someone stained with coffee and other various things.", "You lick the parchment. Feel accomplished?", "You attempt to use the letter, but it don't need no man. Nothing happens.", "Why would you punch parchment?");
        
        puzzleHintThree = new Objects("Mail", "The mail reads that the answer you see is 'less than three', whatever that means.", "Smells like paper", "You lick the mail. The sticky bit still has flavor.", "You attempt to 'use' the mail but it isn't interested.", "Why would you punch mail?");
        
        flowers = new Objects("Flowers", "There is a variety of flowers, all of which are wet.", "Your nose is assulted by a variety of flowers.", "You are not sure if these are poisonous so you decide not to.", "You pick one of the hundreds of flowers, but it wilts in your hand. Odd.", "You punch and kick flowers to feel better about your sad existance.");
        
        toiletMaster = new Objects("Toilet", "A toilet that appears to never have been flushed, nor cleaned in months.", "It smells like crap. Literally.", "Why on God's green earch would you even think of that? NO!", "You 'use' the toilet.", "You punch the porcelain throne. Ow.");
        
        sinkGuest = new Objects("Sink", "The sink's hot water lever seems to be broken. Overall it looks functional though.", "The sink smells like vomit. Almost as if someone had previously vomitted in it, and then rinsed it out.", "You don't think that's a good idea.", "You wash your hands with cold water.", "You punch the sink. Yeah! You show it who's boss.");
        
        tombStone = new Objects("Tombstone", "A tombstone with the inscriptions on it scrawled out.", "It rocks that you're trying to smell this tombstone, but it's ineffective", "It tastes rather stoney. What a hard thing to descern.", "You don't know how you'd use that per say.", "You hurt yourself hitting the tombstone, but it seems to be unaffected.");
        
        rope = new Objects("Rope", "A rope coiled up.", "Smells like hemp.", "Tastes like something unfamiliar.", "The rope is glued in place. What a cruel joke.", "Why would you hit a rope?");
        
        wardrobe = new Objects("Wardrobe", "A tall Oak wardrobe.", "Smells like wood.", "Tastes like a cheesy kid's book.", "The doors are stuck.", "You punch the wardrobe hurting your hand.");
        
        //Sets what objects should be in which rooms ascociated via room name.
                      
        knife.setLocates("Grand Foyer", watch);
        
        chair.setLocates("Grand Foyer", watch);
        chair.setLocates("Kitchen", key);
        
        key.setLocates("Dining Hall", chair);
        key.setLocates("Cellar", carcass);
        key.setLocates("Grand Garden", flowers);
        
        flowers.setLocates("Sun Room", camera);
        flowers.setLocates("Graveyard", tombStone);
        flowers.setLocates("Kitchen", key);
        
        tombStone.setLocates("Grand Garden", flowers);
        tombStone.setLocates("Mausoleum", puzzleHintThree);
        
        puzzleHintThree.setLocates("Graveyard", tombStone);
        
        watch.setLocates("Dining Hall", chair);
        watch.setLocates("Upstairs Hallway", armor);
        watch.setLocates("Sun Room", camera);
        
        camera.setLocates("Grand Foyer", watch);
        camera.setLocates("Study", puzzleHintOne);
        
        armor.setLocates("Grand Foyer", watch);
        armor.setLocates("Storage Closet", chemicals);
        armor.setLocates("Guest Bedroom",wardrobe);
        armor.setLocates("Master Bedroom", puzzleHintTwo);
        armor.setLocates("Attic", pendant);
        
        pendant.setLocates("Upstairs Hallway", armor);
        
        chemicals.setLocates("Upstairs Hallway", armor);
        
        puzzleHintTwo.setLocates("Upstairs Hallway", armor);
        puzzleHintTwo.setLocates("Master Bathroom", toiletMaster);
        puzzleHintTwo.setLocates("Terrace", rope);
        
        toiletMaster.setLocates("Master Bedroom", puzzleHintTwo);
        
        rope.setLocates("Master Bedroom", puzzleHintTwo);
        rope.setLocates("Grand Garden", flowers);
        
        wardrobe.setLocates("Upstairs Hallway", armor);
        wardrobe.setLocates("Guest Bathroom", sinkGuest);
        
        sinkGuest.setLocates("Guest Bedroom", wardrobe);
        
        puzzleHintOne.setLocates("Sun Room", camera);
        puzzleHintOne.setLocates("Grand Garden", flowers);
        
        currentObject = knife;
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
        
        // This initializes the movement/ command counter
        int moveCount = 0;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            //upticks the command/ move count
            moveCount++;
            if (win == true){
                finished = true;
            }
        }
        long completeTime = date.getTime();
        System.out.println("You conquered the mansion in " + moveCount + " moves. Congratulations!");
        System.out.println("It only took you " + ((completeTime - time/1000) + " Milliseconds"));
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
                
            case EXAMINE:
                examine(command);
                break;
                
            case HIT:
                hit(command);
                break;
                
            case SMELL:
                smellThing(command);
                break;
                
            case USE:
                use(command);
                break;
                
            case TASTE:
                taste(command);
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
        // This sets "title" equal to the current room which will check to see if the next room should have the object.
        
        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);
        

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            
            String title = currentRoom.getName();
            Objects nextObject = currentObject.getNextObject(title);
            currentObject = nextObject;
            System.out.println("The is a(n) " + currentObject.getName() + ".");
            System.out.println(currentRoom.getLongDescription());
        }
    }
    
    private void smellThing(Command command)
    {
        if (!command.hasSecondWord()){   
            System.out.println(currentRoom.getScent());            
            return;
        }
        
        else {
                if (command.getSecondWord().equalsIgnoreCase(currentObject.getName())){
                    System.out.println(currentObject.getScent());
                    return;
                }
        }
    }
    
    private void taste(Command command)
    {
        String name = currentRoom.getName();
        if(!command.hasSecondWord()){
        System.out.println("Lick what?");
        return;
        }
        
        else{
            if (command.getSecondWord().equalsIgnoreCase(currentObject.getName())){
                    System.out.println(currentObject.getTaste());
                    return;
                }
        }
    }
    
    private void hit(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("Hit what?");
            return;
        }
        
        else{
        if (command.getSecondWord().equalsIgnoreCase(currentObject.getName())){
                    System.out.println(currentObject.getHit());
                    return;
                }
        }
    }
    
    private void examine(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println(currentRoom.getLongDescription());
            return;
        }
        else{
            if (command.getSecondWord().equalsIgnoreCase(currentObject.getName())){
                    System.out.println(currentObject.getScent());
                    return;
                }
        }
    }
    
    private void use(Command command)
    {
        if(!command.hasSecondWord()){
            System.out.println("Use what?");
            return;
        }
        
        else{
            if(command.getSecondWord().equalsIgnoreCase("love") && currentRoom.getName().equals("Mausoleum"))
            {
                win = true;
            }
            
            else if (command.getSecondWord().equalsIgnoreCase(currentObject.getName())){
                    System.out.println(currentObject.getScent());
                    return;
                }
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
