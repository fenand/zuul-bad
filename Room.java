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
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southeastExit;
    private Room northwestExit;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     * @param southeast The southeast exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southeast ,Room northwest) 
    {
        if(north != null)
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if( southeast != null)
            southeastExit = southeast; 
        if( northwest != null)
            northwestExit = northwest;
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
            roomADevolver = northExit;
        }
        if(direccion.equals("east")){
            roomADevolver = eastExit;
        }
        if(direccion.equals("south")){
            roomADevolver = southExit;
        }
        if(direccion.equals("west")){
            roomADevolver = westExit;
        }
        if(direccion.equals("southeast")){
            roomADevolver = southeastExit;
        }
        if(direccion.equals("northwest")){
            roomADevolver = northwestExit;
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

        if(northExit != null) {
            System.out.print("north ");
        }
        if(eastExit != null) {
            System.out.print("east ");
        }
        if(southExit != null) {
            System.out.print("south ");
        }
        if(westExit != null) {
            System.out.print("west ");
        }
        if(southeastExit != null) {
            System.out.print("southeast ");
        }
        if(northwestExit != null) {
            System.out.print("northwest ");
        }
        System.out.println();

        return salidaDescripcion;
    }
}
