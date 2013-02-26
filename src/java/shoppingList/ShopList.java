package shoppingList;

import java.util.ArrayList;
/**
 *
 * @author Doug
 */
public class ShopList {
    
    ArrayList<String> slist = new ArrayList<String>();
    double price;
    
    
    
    public ShopList(){
        addToList();
    }
    public void addToList(){
        slist.add("milk");
        slist.add("cheese");
        slist.add("eggs");
        slist.add("cereal");
        slist.add("bread");
        slist.add("chicken");
    }
    public ArrayList<String> getList(){
        return slist;
        
    }
}
