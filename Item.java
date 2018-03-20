
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
    private boolean objetosQueSePuedenCoger;

    /**
     * Constructor de la clase item, crea objetos de tipo item
     * @param itemDescription descripcion del item.
     * @param itemWeight peso o cantidad del item.
     */
    public Item(String itemDescription, float itemWeight, boolean objetosQueSePuedenCoger){
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
        this.objetosQueSePuedenCoger = objetosQueSePuedenCoger;
    }

    /**
     * item
     * @return itemDescription.
     */
    public String getItemDescription(){
        return itemDescription;
    }

    /**
     * cantidad
     * @return itemWeight.
     */
    public float getItemWeight(){
        return itemWeight;
    }

    /**
     * metodo para devolver si el objeto se pude coger o no
     * @return true si se puede , false no
     */
    public boolean getSePuedeCoger(){
        return objetosQueSePuedenCoger;
    }

    /**
     * metodo para devolver un String con la descripcion de los items
     * @return items y peso.
     */

    public String getDescription(){
        String description = "\n" + "Item: " + itemDescription + "\n" + "Items en la sala: "+ itemWeight;
        return description;
    }
}
