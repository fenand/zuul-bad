import java.util.HashMap;

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
    // private Room northExit;
    // private Room southExit;
    // private Room eastExit;
    // private Room westExit;
    // private Room southeastExit;
    // private Room northwestExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        //inicializamos el hashmap
        salidas = new HashMap<>();
    }

    /**
     * Define an exit from this room.
     * @param direccion The direction of the exit.
     * @param habitacion The room in the given direction.
     */
    public void setExit(String direccion, Room habitacion){
        salidas.put(direccion, habitacion);
    }
    // /**
    // * Define the exits of this room.  Every direction either leads
    // * to another room or is null (no exit there).
    // * @param north The north exit.
    // * @param east The east east.
    // * @param south The south exit.
    // * @param west The west exit.
    // * @param southeast The southeast exit.
    // */
    // public void setExits(Room north, Room east, Room south, Room west, Room southeast ,Room northwest) 
    // {
    // if(north != null)
    // salidas.put("north",north);
    // if(east != null)
    // salidas.put("east",east);
    // if(south != null)
    // salidas.put("south",south);
    // if(west != null)
    // salidas.put("west",west);
    // if( southeast != null)
    // salidas.put("southeast",southeast);
    // if( northwest != null)
    // salidas.put("northwest",northwest);
    // }
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
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    //Creado el metodo getLongDescription en la clase Room para aplicar reponsability-driven design
    public String getLongDescription(){
        return "You are " + getDescription() + getExitString();
    }
}
