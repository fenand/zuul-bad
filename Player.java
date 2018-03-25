import java.util.Stack;
import java.util.ArrayList;

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
    //creamos un arraylist de los objetos que vamos a ir añadiendo a la mochilo
    private ArrayList<Item> mochilo;

    //Peso Maximo que puede llevar el jugador en una constante por eso va en mayusculas.
    //private static final float PESO_MAXIMO = 50;

    //peso que tiene en la mochilo actualmente
    private float pesoMochilo;

    // 1-Implementa la posibilidad de que el jugador pueda coger objetos sin tener en cuenta el peso del objeto. 
    // Haz un commit dentro de la rama (commit r01). hecho--------------------

    // 2-Implementa la posibilidad de que haya objetos en el juego que no se puedan coger. 
    // Haz un commit dentro de la rama (commit r02).

    // 3-Implementa un comando items que haga que se impriman por pantalla todos los objetos que lleva ahora mismo el jugador, 
    // junto con su peso total. Haz un commit dentro de la rama (commit r03).

    // 4-Implementa la posibilidad de que el jugador pueda soltar objetos. 
    // Haz un commit dentro de la rama (commit r04).

    // 5-Implementa que el jugador pueda llevar objetos hasta un peso máximo especificado cuando se construye el jugador. 
    // El peso máximo que cada jugador puede llevar es un atributo del jugador. Haz un commit dentro de la rama (commit r05)

    /**
     * Constructor for objects of class Player
     */
    public Player(Room exterior){
        currentRoom = exterior;
        //inicializamos la pila
        rooms = new Stack<>();
        // inicializamos el arraylist y el peso que tienes en la mochila
        mochilo = new ArrayList<>();
        pesoMochilo = 0;

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
     * Metodo para añadir el comando eat (comer) que, al ejecutarse, 
     * se limite a imprimir el mensaje "You have eaten now and you are not hungry any more" 
     * (acabas de comer y ya no tienes hambre).
     */
    public void eat(){
        System.out.println("You have eaten now and you not hungry any more -- Acabas de comer y ya no tienes hambre");
    }

    /**
     * nuevo metodo privado para evitar la repeticion de codigo entre printwelcome y goRoom
     */
    public void printLocationInfo(){ 
        System.out.println(currentRoom.getLongDescription());  
    }

    /**
     * Metodo para añadir items al inventario
     * @param un item que es un obejeto item
     */
    public void addItems(Item item){
        mochilo.add(item);
    }

    /**
     * comando take para recoger un itme de la sala y guardarlo en la mochilo
     */
    public void take(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Escribe el objeto que quieres guardar!!");
            return;
        }

        String objetoACoger = command.getSecondWord();

        if(currentRoom.getItem(objetoACoger) != null){
            if (currentRoom.getCantidadDeItems() > 0) {
                mochilo.add(currentRoom.getItem(objetoACoger));
                pesoMochilo += currentRoom.getItem(objetoACoger).getItemWeight();
                currentRoom.borrarItem(objetoACoger);
                System.out.println("Has cogido este item: " + objetoACoger);
            }
            else{
                System.out.println("La sala esta desierta, cambia de sala para encontrar algo!!");
            }
        }

        else{
            System.out.println("El objeto no existe!!");
        }

    }
}