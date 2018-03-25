
/**
 * clase encargarda de crear objetos en el juego
 * 
 * @author (fernando) 
 * @version (15/03/2018)
 */
public class Item
{
    private String iD;
    private String itemDescription;
    private float itemWeight;
    private boolean objetosQueSePuedenCoger;

    /**
     * Constructor de la clase item, crea objetos de tipo item
     * @param itemDescription descripcion del item.
     * @param itemWeight peso o cantidad del item.
     */
<<<<<<< HEAD
    public Item(String iD,String itemDescription, float itemWeight){
        this.iD = iD;
=======
    public Item(String itemDescription, float itemWeight, boolean objetosQueSePuedenCoger){
>>>>>>> cogersoltar
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
<<<<<<< HEAD
     * return iD
     */
    public String getID(){
    return iD;
    }
    /**
     * metodo para devolver un String con la descripcion de los items
     * @return descripcion de los items.
=======
     * metodo para devolver si el objeto se pude coger o no
     * @return true si se puede , false no
>>>>>>> cogersoltar
     */
    public boolean getSePuedeCoger(){
        return objetosQueSePuedenCoger;
    }

<<<<<<< HEAD
        String description = "\n" +"iD: "+ iD + "\n" +"Descripcion: " + itemDescription + "\n" + "Numero De Items: "+ itemWeight;
=======
    /**
     * metodo para devolver un String con la descripcion de los items
     * @return items y peso.
     */
>>>>>>> cogersoltar

    public String getDescription(){
        String description = "\n" + "Item: " + itemDescription + "\n" + "Items en la sala: "+ itemWeight;
        return description;
    }
}
