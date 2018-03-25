import java.util.HashMap;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes y fernando
 * @version 09/03/2018
 */
public class Room 
{
    private String description;
    // creamos un hashmap y eliminamos los atributos anteriores
    private HashMap<String, Room> salidas;

    // //ArrayList para los items de juego
    //cambio el array list de items por un hashmap de items
    // private ArrayList<Item> items;
    private HashMap<String, Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     * 
     */
    public Room(String description) 
    {
        this.description = description;

        //inicializamos el hashmap
        salidas = new HashMap<>();

        //inicializamos el arraylist de items
        //cambio el array list de items por un hashmap de items
        items = new HashMap<>();

    }

    /**
     * Define an exit from this room.
     * @param direccion The direction of the exit.
     * @param habitacion The room in the given direction.
     */
    public void setExit(String direccion, Room habitacion){
        salidas.put(direccion, habitacion);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Metodo denominado getExit que tome como parámetro una cadena que represente una dirección y devuelva el objeto de la clase Room asociado a esa salida o 
     * null si no existe esa salida en dicha ubicación.
     * 
     * @param tiene como parametro un String
     * @return objeto de la clase Room asociado a esa salida o null si no existe esa salida en dicha ubicación
     */
    public Room getExit(String direccion){
        Room roomADevolver = null;
        if(direccion.equals("north")){
            roomADevolver = salidas.get("north");
        }
        if(direccion.equals("east")){
            roomADevolver = salidas.get("east");
        }
        if(direccion.equals("south")){
            roomADevolver = salidas.get("south");
        }
        if(direccion.equals("west")){
            roomADevolver = salidas.get("west");
        }
        if(direccion.equals("southeast")){
            roomADevolver = salidas.get("southeast");
        }
        if(direccion.equals("northwest")){
            roomADevolver = salidas.get("northwest");
        }
        return roomADevolver;
    }

    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west southeast "
     *
     * @ return descripcion de las salidas en la habitacion en la que estamos.
     */
    public String getExitString(){
        String salidaDescripcion = "Salidas: ";

        if(salidas.get("north") != null){
            salidaDescripcion += "north ";
        }
        if(salidas.get("east") != null){
            salidaDescripcion += "east ";
        }
        if(salidas.get("south") != null){
            salidaDescripcion += "south ";
        }
        if(salidas.get("west") != null){
            salidaDescripcion += "west ";
        }
        if(salidas.get("southeast") != null){
            salidaDescripcion += "southeast ";
        }
        if(salidas.get("northwest") != null){
            salidaDescripcion += "northwest ";
        }

        return salidaDescripcion;
    }

    /**
     * Modificado el metodo para que devuelva todo los items mas la descripcion de las habitacones y sus objetos
     * @return A description of the room, including exits.
     */
    //Creado el metodo getLongDescription en la clase Room para aplicar reponsability-driven design
    public String getLongDescription(){ 
<<<<<<< HEAD
        //return "You are " + getDescription() + getExitString();
        String longDescription = "You are  " + getDescription() + "\n"  + getExitString() ;
        //return longDescription;
        for(int cantidadDeItems = 0; cantidadDeItems < items.size(); cantidadDeItems++)
        {
            longDescription += "\n" + items.get(cantidadDeItems).getDescription();
=======
        String longDescription = "You are  " + getDescription() + "\n"  + getExitString();
        if (items.size() > 0){
            for(Item objetoActual : items.values()){
                longDescription += "\n" + objetoActual.getDescription();
            }
>>>>>>> cogersoltar
        }
        return longDescription;
    }

    /**
     * Metodo para añadir items al juego
     * 
     * @param itemDescription item
     * @param itemWeight cantidad de items o peso
     */

<<<<<<< HEAD
    public void addItem(String iD,String itemDescription, float itemWeight)
    {
        items.add(new Item(iD,itemDescription,itemWeight));
=======
    public void addItem(String itemDescription, float itemWeight , boolean objetosQueSePuedenCoger)
    {
        items.put(itemDescription,new Item(itemDescription,itemWeight,objetosQueSePuedenCoger));
    }

    /**
     * metodo para devolver el item que pasas por parametro
     * @param item que quieres devolver
     * @return item devuelto
     */
    public Item getItem(String item){
        return items.get(item);
    }

    /**
     * metodo para saber la cantidad de items que hay
     * @return numerode items en la sala
     */
    public int getCantidadDeItems(){
        return items.size();
    }

    /**
     * metodo para remover el item de la sala que indiquemos por parametro
     * @param item a borrar por parametro
     */
    public void borrarItem(String item){
        items.remove(item);
>>>>>>> cogersoltar
    }
}