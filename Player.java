import java.util.Stack;

/**
 * clase player encargada de ejecuta los comandos del juego y no game como teniamos antes
 * 
 * @author (Fernando) 
 * @version (23/03/2018 version 1.0)
 */
public class Player
{
    private Room currentRoom;
    // creamos una coleccionen en forma de pila para has rooms
    private Stack<Room>rooms;

    /**
     * Constructor for objects of class Player
     */
    public Player(Room exterior){
        currentRoom = exterior;
        //inicializamos la pila
        rooms = new Stack<>();

    }
   

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    public void goRoom(Command command){
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
            //añadimos a la pila la room que acabamos de entrar con go
            rooms.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /**
     * Metodo para ir hacia atras las veces que quieras hasta la primera sala.
     */
    public void goBack(){
        //Muestra el objeto del tope de la pila y lo borra
        if(!rooms.empty()){
            currentRoom = rooms.pop();
            printLocationInfo();
        }
        else{
            System.out.println("Avanza una habitacion para poder hacer un back");
        }
    }

    /**
     * nuevo metodo privado para evitar la repeticion de codigo entre printwelcome y goRoom
     */
    public void printLocationInfo(){ 
        System.out.println(currentRoom.getLongDescription());  
    }

}