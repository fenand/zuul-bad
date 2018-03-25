import java.util.Stack;
import java.util.ArrayList;
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
<<<<<<< HEAD
    private Player player;
=======
    private Room currentRoom;
    // creamos una coleccionen en forma de pila para has rooms
    private Stack<Room>rooms;
    //creamos un arraylist de los objetos que vamos a ir a�adiendo a la mochilo
    private ArrayList<Item> mochilo;
    //Peso Maximo que puede llevar el jugador en una constante por eso va en mayusculas.
    private static final float PESO_MAXIMO = 50;
    //peso que tiene en la mochilo actualmente
    private float pesoMochilo;
>>>>>>> cogersoltar

    /**
     * Create the game and initialise its internal map.
     */
    public Game(){
        parser = new Parser();

<<<<<<< HEAD
        player = new Player(createRooms());
=======
        //inicializamos la pila
        rooms = new Stack<>();

        // inicializamos el arraylist y el peso que tienes en la mochila
        mochilo = new ArrayList<>();
        pesoMochilo = 0;
>>>>>>> cogersoltar
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private Room createRooms(){

        //objetos room
        Room exterior,foso1, foso2, muralla, patio, salones, aposentos, torreon1, torreon2, mazmorras;

        // // ccreamos las rooms  y objetos mas peso de los objetos
        // ahora los items son a�adidos desde el array de items creado en la clase Item llamando al metodo additem de la clase room

        exterior = new Room("parte exterior del castillo");

        foso1 = new Room("foso");
<<<<<<< HEAD
<<<<<<< HEAD
        foso1.addItem("Serpientes","Serpientes con mordeduras muy venenosas", 20);
=======
        foso1.addItem("Serpientes","Serpientes con mordeduras muy venenosas", 20,false);
>>>>>>> ramaObjetos

        foso2 = new Room("foso");
        foso2.addItem("Cocodrilos","Cocodrilos hambrientos y muy feroces", 20,false);
        foso2.addItem("Serpientes","Serpientes con mordeduras muy venenosas", 20,false);

        muralla = new Room("muralla del castillo");
<<<<<<< HEAD
        muralla.addItem("Soldados","Solados muy feroces", 10);
=======
        foso1.addItem("serpientes",20,false);

        foso2 = new Room("foso");
        foso2.addItem("cocodrilos", 20,false);
        foso2.addItem("serpientes", 20,false);

        muralla = new Room("muralla del castillo");
        muralla.addItem("soldados", 10,false);
>>>>>>> cogersoltar
=======
        muralla.addItem("Soldados","Solados muy feroces", 10,false);
>>>>>>> ramaObjetos

        patio = new Room("patio del castillo");

        salones = new Room("salones del castillo");
<<<<<<< HEAD
<<<<<<< HEAD
        salones.addItem("Cofre","Cofre de oro uy valioso", 5);

        aposentos = new Room("aposentos del rey");
        aposentos.addItem("Llave","Llave de la mazmorra", 1);
=======
        salones.addItem("cofre_de_oro", 25,true);

        aposentos = new Room("aposentos del rey");
        aposentos.addItem("llave_de_la_mazmorra", 1,true);
        aposentos.addItem("cofre_de_oro", 25,true);
>>>>>>> cogersoltar
=======
        salones.addItem("Cofre","Cofre de oro uy valioso", 25,true);

        aposentos = new Room("aposentos del rey");
        aposentos.addItem("Llave","Llave de la mazmorra", 10,true);
        aposentos.addItem("Cofre","Cofre de oro uy valioso", 25,true);
>>>>>>> ramaObjetos

        torreon1 = new Room("primera torre");
        torreon1.addItem("cofre_de_oro", 15,true);
        torreon1.addItem("cofre_de_plata", 15,true);

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

        torreon1.setExit("north",salones );
        torreon1.setExit("east",torreon2 );

        torreon2.setExit("northwest",patio );
        torreon2.setExit("north",aposentos );
        torreon2.setExit("west",torreon1 );

        mazmorras.setExit("north",patio );

        // comienzo del juego

        return exterior;

    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play(){            
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
        System.out.println("Welcome to the World of Zuulo!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
<<<<<<< HEAD
        System.out.println();
        player.printLocationInfo();
=======
        printLocationInfo();
>>>>>>> cogersoltar
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
            back();
        }
        else if(commandWord.equals("take")){
<<<<<<< HEAD
            take(command);           
        }
        else if(commandWord.equals("items")){
            objetosMochilo(command);            
        }
        else if(commandWord.equals("drop")){
            drop(command);           
=======
            player.take(command);

        }
        else if(commandWord.equals("items")){
        player.objetosMochilo(command);

        }
        else if(commandWord.equals("drop")){
        player.drop(command);

>>>>>>> ramaObjetos
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
<<<<<<< HEAD
        player.goRoom(command);
=======
        if(!command.hasSecondWord()){
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }
        String direccion = command.getSecondWord();

        // Try to leave current room.

        Room nextRoom = currentRoom.getExit(direccion);
        if (nextRoom == null){
            System.out.println("There is no door (Hodoor)!");
        }
        else{
            //a�adimos a la pila la room actual
            rooms.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
>>>>>>> cogersoltar
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

    /**
     * Metodo que imprime por pantalla la informacion que esta el metodo printLocationInfo()
     */
    private void look(){
        player.printLocationInfo();
    }

    /**
     * Metodo para a�adir el comando eat (comer) que, al ejecutarse, 
     * se limite a imprimir el mensaje "You have eaten now and you are not hungry any more" 
     * (acabas de comer y ya no tienes hambre).
     */
    private void eat(){
<<<<<<< HEAD
        System.out.println("You have eaten now and you not hungry any more -- Acabas de comer y ya no tienes hambre gordo!!");
=======
        player.eat();
>>>>>>> ramaObjetos
    }

    /**
<<<<<<< HEAD
     * comando back
     */
    private void back(){
        player.goBack();
=======
     * comando back para volver atras en una sala
     */
    private void back(){
        //Muestra el objeto del tope de la pila y lo borra
        if(!rooms.empty()){
            currentRoom = rooms.pop();
            look();
        }
        else{
            System.out.println("Avanza una habitacion para poder hacer un back");
        }
    }

    /**
     * comando take para recoger un itme de la sala y guardarlo en la mochilo
     */
    private void take(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Escribe el objeto que quieres guardar!!");
            return;
        }

        String objetoACoger = command.getSecondWord();
        if(currentRoom.getItem(objetoACoger) != null){
            if (currentRoom.getCantidadDeItems() > 0) {
                //condicion para coger un objeto si existe o no ,con el segundo comando escrito despues de take y la condicion de poder cogerlos o no
                if(currentRoom.getItem(objetoACoger).getSePuedeCoger()){
                    //condicion para la capacida de la mochilo
                    if(pesoMochilo + currentRoom.getItem(objetoACoger).getItemWeight() <= PESO_MAXIMO){
                        mochilo.add(currentRoom.getItem(objetoACoger));
                        pesoMochilo += currentRoom.getItem(objetoACoger).getItemWeight();
                        currentRoom.borrarItem(objetoACoger);
                        System.out.println("Has cogido este item: " + objetoACoger);
                    }
                    else{
                        System.out.println("Mochilas mochales,tu mochila esta llega no puedes cargar mas peso" + "\n" + "Limite de peso: " + PESO_MAXIMO +"\n" + "Peso actual :" + pesoMochilo);
                    }
                }
                else{
                    System.out.println("Mochilas mochales,tu mochilo no acepta ese objeto");
                }
            }
            else{
                System.out.println("La sala esta desierta, cambia de sala para encontrar algo!!");
            }
        }
        else{
            System.out.println("El objeto no existe!!");
        }
    }

    /**
     * metodo para imprimir los items que tengo en la mochila
     */
    private void objetosMochilo(Command command){
        int contadorTareas = 0;

        if(!mochilo.isEmpty()){
            for(Item objetosEnLaMochilo : mochilo){
                System.out.println((contadorTareas + 1) + "- " + objetosEnLaMochilo.getItemDescription() + "\n" + "Cantidad de Items: " + objetosEnLaMochilo.getItemWeight() + "\n" 
                    + "Capacidad mochilo: " + (PESO_MAXIMO - pesoMochilo));
                contadorTareas++;
            }
        }
        else{
            System.out.println("Mochilas mochales, No tienes objetos en tu MOCHILO" + "\n" 
                + "Capacidad mochilo: " + (PESO_MAXIMO - pesoMochilo));
        }
    }

    /**
     * metodo para dejar objetos en las habitaciones
     */
    private void drop(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Escribe el objeto que quieres dejar!!");
            return;
        }

        String objetoADejar = command.getSecondWord();
        int objeto = 0;
        boolean objetoSoltado = false;
        for(int i = 0 ; i < mochilo.size() ; i++ ){
            if(mochilo.get(i).getItemDescription().equalsIgnoreCase(objetoADejar)){
                objeto = i;
                pesoMochilo -= mochilo.get(objeto).getItemWeight();
                currentRoom.addItem(mochilo.get(objeto).getItemDescription(), mochilo.get(objeto).getItemWeight(), true);
                mochilo.remove(objeto);
            }
            else{
                System.out.println("No puedes dejar un objeto que no tienes en tu mochilo");
            }
        }
>>>>>>> cogersoltar
    }
}

