import java.util.Stack;
import java.util.ArrayList;
import java.util.Random;
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
    //creamos un arraylist de los objetos que vamos a ir a�adiendo a la mochilo
    private ArrayList<Item> mochilo;
    //Peso Maximo que puede llevar el jugador en una constante por eso va en mayusculas.
    private static final float PESO_MAXIMO = 50;
    //peso que tiene en la mochilo actualmente
    private float pesoMochilo;

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
            //a�adimos a la pila la room que acabamos de entrar con go
            rooms.push(currentRoom);
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /** 
     * metodo para que mover el rey aleatoriamente por el juego en las direcciones indicadas en el array de dirrecciones y
     * que solo pueda moverse en las habitaciones que tengan la misma descripcion indicada en la condicion de moverse
     */
    public void goRoomRey(){
        String[] posiblesDirecciones = {"north", "west", "east", "south"};
        boolean direccionBuena = false;
        Random rng = new Random();
        while (!direccionBuena){
            int testing = rng.nextInt(4);
            Room nextRoom = currentRoom.getExit(posiblesDirecciones[testing]);
            if (nextRoom != null){
                if (nextRoom.getDescription().equalsIgnoreCase("aposentos") || nextRoom.getDescription().equalsIgnoreCase("salones") || nextRoom.getDescription().equalsIgnoreCase("torreon1") || nextRoom.getDescription().equalsIgnoreCase("torreon2")){
                    currentRoom = nextRoom;
                    direccionBuena = true;
                }
            }
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
     * Metodo para a�adir el comando eat (comer) que, al ejecutarse, 
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
     * Metodo para a�adir items al inventario
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
            if (currentRoom.getCantidadDeItems() > 0){
                if(currentRoom.getItem(objetoACoger).getSePuedeCoger()){
                    if(pesoMochilo + currentRoom.getItem(objetoACoger).getItemWeight() <= PESO_MAXIMO){
                        mochilo.add(currentRoom.getItem(objetoACoger));
                        pesoMochilo += currentRoom.getItem(objetoACoger).getItemWeight();
                        currentRoom.borrarItem(objetoACoger);
                        System.out.println("Has cogido este item: " + objetoACoger);
                    }
                    else{
                        System.out.println("Mochilas mochales,tu mochila esta llega no puedes cargar mas peso" + "\n" + "Limite de peso: " 
                            + PESO_MAXIMO +"\n" + "Peso actual :" + pesoMochilo);
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
    public void objetosMochilo(Command command){
        int contador = 0;

        if(!mochilo.isEmpty()){
            for(Item objetosEnLaMochilo : mochilo){
                System.out.println((contador + 1) + "- " + objetosEnLaMochilo.getID() + "\n" + "Cantidad de Items: " + objetosEnLaMochilo.getItemWeight()+ "\n" + 
                    "Peso Mochilo: "+ pesoMochilo + "\n" 
                    + "Capacidad mochilo: " + (PESO_MAXIMO - pesoMochilo));
                contador++;
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
    public void drop(Command command){
        if(!command.hasSecondWord()){
            System.out.println("Escribe el objeto que quieres dejar!!");
            return;
        }

        String objetoADejar = command.getSecondWord();

        int objeto = 0;
        boolean objetoSoltado = false;
        for(int i = 0 ; i < mochilo.size() ; i++ ){
            if(mochilo.get(i).getID().equalsIgnoreCase(objetoADejar)){
                objeto = i;
                pesoMochilo -= mochilo.get(objeto).getItemWeight();
                currentRoom.addItem(mochilo.get(objeto).getID(),mochilo.get(objeto).getItemDescription(), mochilo.get(objeto).getItemWeight(), true);
                mochilo.remove(objeto);
            }
            else{
                System.out.println("No puedes dejar un objeto que no tienes en tu mochilo");
            }
        }

    }

    /**
     * metodo para saber la habitacion que estamos
     */
    public Room getCurrentRoom(){
        return currentRoom;
    }
}

