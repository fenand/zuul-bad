
/**
 * clase encargarda de crear objetos en el juego
 * 
 * @author (fernando) 
 * @version (15/03/2018)
 */
public class Item
{
    private String itemDescription;
    private float itemWeight;

    /**
     * Constructor de la clase item, crea objetos de tipo item
     * @param itemDescription descripcion del item.
     * @param itemWeight peso o cantidad del item.
     */
    public Item(String itemDescription, float itemWeight){
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
    }

    /**
     * @return itemDescription.
     */
    public String getItemDescription(){
        return itemDescription;
    }

    /**
     * @return itemWeight.
     */
    public float getItemWeight(){
        return itemWeight;
    }

    /**
     * metodo para devolver un String con la descripcion de los items
     * @return descripcion de los items.
     */
    //Creado el metodo getLongDescription en la clase Room para aplicar reponsability-driven design
    public String getDescription(){

        //return "You are " + getDescription() + getExitString();

        String description = "\n" + "Item: " + itemDescription + "\n" + "Numero De Items: "+ itemWeight;

        return description;
    }
}
