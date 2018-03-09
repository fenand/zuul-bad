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
 * @author  Michael Kölling and David J. Barnes Y fernando
 * @version 09/03/2018
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
        // //Room outside, theater, pub, lab, office;
        Room exterior,foso1, foso2, muralla, patio, salones, aposentos, torreon1, torreon2, mazmorras;

        // // create the rooms

        exterior = new Room("parte exterior del castillo");
        foso1 = new Room("foso");
        foso2 = new Room("foso ");
        muralla = new Room("muralla del castillo");
        patio = new Room("patio del castillo");
        salones = new Room("salones del castillo");
        aposentos = new Room("aposentos del rey");
        torreon1 = new Room("primera torre");
        torreon2 = new Room("segundo torreon");
        mazmorras = new Room(" la mazmorrra ");

        // // outside = new Room("outside the main entrance of the university");
        // // theater = new Room("in a lecture theater");
        // // pub = new Room("in the campus pub");
        // // lab = new Room("in a computing lab");
        // // office = new Room("in the computing admin office");

        // // initialise room exits
        //                   Room north, Room east,  Room south,  Room west  Room southeast
        // exterior.setExits(null,       null,       null,        null          null    );

        //exterior,foso1, foso2, muralla, patio, salones, aposentos, torreon1, torreon2, mazmorras
        exterior.setExits(null, null, muralla, null,foso2); //salida sureste foso2
        foso1.setExits(null, muralla, null, null,null);
        foso2.setExits(null, null, null, muralla,null);
        muralla.setExits(exterior, foso2, patio, foso1,null);
        patio.setExits(muralla, aposentos, mazmorras, salones,torreon2); // salida sureste torreon2
        salones.setExits(null, patio, torreon1, null,null);
        aposentos.setExits(null, null, torreon2, patio,null);
        torreon1.setExits(salones, torreon2, null, null,null);
        torreon2.setExits(aposentos, null, null, torreon1,null);
        mazmorras.setExits(patio, null, null, null,null);

        // // outside.setExits(null, theater, lab, pub);
        // // theater.setExits(null, null, null, outside);
        // // pub.setExits(null, outside, null, null);
        // // lab.setExits(outside, office, null, null);
        // // office.setExits(null, null, null, lab);

        // //currentRoom = outside;  // start game outside
        currentRoom = exterior; // comienzo del juego
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
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
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
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direccion = command.getSecondWord();

        // Try to leave current room.

        //puedo borrar el null que el geter ya lo usa si no hay nada en esa dirreccion
        // // Room nextRoom = null;
        //modificacion y simplidficado de esta parte del codigo

        // // if(direction.equals("north")) {
        // // nextRoom = currentRoom.northExit;
        // // }
        // // if(direction.equals("east")) {
        // // nextRoom = currentRoom.eastExit;
        // // }
        // // if(direction.equals("south")) {
        // // nextRoom = currentRoom.southExit;
        // // }
        // // if(direction.equals("west")) {
        // // nextRoom = currentRoom.westExit;
        // // }
        // // if(direction.equals("southeast")) {
        // // nextRoom = currentRoom.southeastExit;
        // // }

        Room nextRoom = currentRoom.getExit(direccion);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();
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

    /**
     * nuevo metodo privado para evitar la repeticion de codigo entre printwelcome y goRoom
     */
    private void printLocationInfo(){

        System.out.println("You are " + currentRoom.getDescription());
        System.out.println(currentRoom.getExitString());

    }
}
