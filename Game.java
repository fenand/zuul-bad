import java.util.Stack;
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
 * @author  Michael KÃ¶lling and David J. Barnes Y fernando
 * @version 09/03/2018
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    // creamos una coleccionen en forma de pila para has rooms
    private Stack<Room>rooms;

    /**
     * Create the game and initialise its internal map.
     */
    public Game(){
        createRooms();
        parser = new Parser();

        //inicializamos la pila
        rooms = new Stack<>();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms(){

        //objetos room
        Room exterior,foso1, foso2, muralla, patio, salones, aposentos, torreon1, torreon2, mazmorras;

        // // ccreamos las rooms  y objetos mas peso de los objetos
        // ahora los items son añadidos desde el array de items creado en la clase Item llamando al metodo additem de la clase room

        exterior = new Room("parte exterior del castillo");
        exterior.addItem(null,0);

        foso1 = new Room("foso");
        foso1.addItem("Serpientes", 20);

        foso2 = new Room("foso");
        foso2.addItem("Cocodrilos", 20);
        foso2.addItem("Serpientes", 20);

        muralla = new Room("muralla del castillo");
        muralla.addItem("Soldados", 10);

        patio = new Room("patio del castillo");

        salones = new Room("salones del castillo");
        salones.addItem("Cofre de oro", 5);

        aposentos = new Room("aposentos del rey");
        aposentos.addItem("Llave de la mazmorra", 1);

        torreon1 = new Room("primera torre");

        torreon2 = new Room("segundo torreon");

        mazmorras = new Room("la mazmorrra");

        //Room north, Room east,  Room south,  Room west     Room southeast  Room northwest
        //exterior,foso1, foso2, muralla, patio, salones, aposentos, torreon1, torreon2, mazmorras  

        // salidas y entradas de las habitaciones 
        exterior.setExit("south", muralla);
        exterior.setExit("southeast", foso2);

        foso1.setExit("east",muralla );

        foso2.setExit("west",muralla );
        foso2.setExit("northwest", exterior);

        muralla.setExit("north", exterior);
        muralla.setExit("east", foso2 );
        muralla.setExit("south", patio);
        muralla.setExit("west", foso1); 

        patio.setExit("north",muralla );
        patio.setExit("east",aposentos );
        patio.setExit("south",mazmorras );
        patio.setExit("west",salones );
        patio.setExit("southeast",torreon2 ); 

        salones.setExit("south",torreon2 );
        salones.setExit("east",patio ); 

        aposentos.setExit("west",patio );
        aposentos.setExit("south",torreon2 );

        torreon1.setExit("north",aposentos );
        torreon1.setExit("east",torreon2 );
        torreon1.setExit("northwest",patio );

        mazmorras.setExit("north",patio );

        // comienzo del juego
        currentRoom = exterior; 

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() {            
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
    private void printWelcome(){
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if(command.isUnknown()){
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();

        if (commandWord.equals("help")){
            printHelp();
        }
        else if (commandWord.equals("go")){
            //añadimos a la pila la room que acabamos de entrar con go
            rooms.push(currentRoom);
            goRoom(command);
        }
        else if (commandWord.equals("quit")){
            wantToQuit = quit(command);
        }
        else if(commandWord.equals("look")){
            look();
        }

        else if(commandWord.equals("eat")){
            eat();
        }

        else if(commandWord.equals("back")){
            //Muestra el objeto del tope de la pila sin extraerlo, en nuestro caso la ultima room visitada
            
            if(!rooms.empty()){
                currentRoom = rooms.peek();
                look();
            }
            else{
                System.out.println("Avanza una habitacion para poder volver");
            }
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp(){
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the castle.");
        System.out.println("Your command words are:");
        System.out.println(parser.showCommands());
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command){
        if(!command.hasSecondWord()){
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        String direccion = command.getSecondWord();

        // Try to leave current room.

        Room nextRoom = currentRoom.getExit(direccion);
        if (nextRoom == null){
            System.out.println("There is no door!");
        }
        else{
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command){
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }

    // mejorando el acoplamiento de esta parte del codigo para no tener que cambiar las dos clases cuando modifiques la clase room
    /**
     * nuevo metodo privado para evitar la repeticion de codigo entre printwelcome y goRoom
     */
    private void printLocationInfo(){ 
        System.out.println(currentRoom.getLongDescription());  //metodo nuevo creado en room     
    }

    /**
     * Metodo que imprime por pantalla la informacion que esta el metodo printLocationInfo()
     */
    private void look(){
        printLocationInfo();
    }

    /**
     * Metodo para añadir el comando eat (comer) que, al ejecutarse, 
     * se limite a imprimir el mensaje "You have eaten now and you are not hungry any more" 
     * (acabas de comer y ya no tienes hambre).
     */
    private void eat(){
        System.out.println("You have eaten now and you not hungry any more -- Acabas de comer y ya no tienes hambre");
    }

    // /**
    // * comando back
    // * 
    // */
    // private void back(){

    // roomActual = currentRoom.getExit(direccion);

    // }
}
