
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
     * @param id item
     * @param itemDescription descripcion del item.
     * @param itemWeight peso o cantidad del item.
     * @param booleano para poder coger items o no
     */
    public Item(String iD,String itemDescription, float itemWeight,boolean objetosQueSePuedenCoger ){
        this.iD = iD;
        this.itemDescription = itemDescription;
        this.itemWeight = itemWeight;
        this.objetosQueSePuedenCoger = objetosQueSePuedenCoger;
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
     * @return iD
     */
    public String getID(){
        return iD;
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
     * @return descripcion de los items.
     */
    public String getDescription(){

        String description = "\n" +"iD: "+ iD + "\n" +"Descripcion: " + itemDescription + "\n" + "Numero De Items: "+ itemWeight;

        return description;
    }
}
