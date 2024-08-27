package co.com.Serrato.CarlosSerratoApiMongo.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.Serrato.CarlosSerratoApiMongo.Model.GroceryItem;
import co.com.Serrato.CarlosSerratoApiMongo.Repository.GroceryRepository;


@Service
public class ItemService {
	@Autowired
	private GroceryRepository repo; 
	private List<GroceryItem> groceryItems;

    //Esta variable indica si setGroceryItems() ya ha sido iniciada, si no ha sido iniciada entonces se asignan los datos a la lista groceryItems, de lo contrario no hace nada
    boolean started=false;
    public void setGroceryItems(){
        if (this.started==false){
            this.groceryItems = new ArrayList<GroceryItem>();
            repo.save(new GroceryItem("1", "Whole Wheat Biscuit", 5, "snacks"));
            repo.save(new GroceryItem("2", "Dried Whole Red Chilli", 2, "spices"));
            repo.save(new GroceryItem("3", "Healthy Pearl Millet", 1, "millets"));
            repo.save(new GroceryItem("4", "Bonny Cheese Crackers Plain", 6, "snacks"));
            this.started=true;
        }
    }

    public String getAll(){
        setGroceryItems();
        List<GroceryItem> groceryItems = repo.findAll();
        return "---ITEMS EXISTENTES---\n" + groceryItems.toString();
    }
    
    public String getAny(String id){
        setGroceryItems();
        if (repo.existsById(id)) { 
        	Optional<GroceryItem> optionalItem= repo.findById(id);
        	return "---ITEM ENCONTRADO---\n" + optionalItem.get().toString();
        } else {
            return "---ITEM NO ENCONTRADO---";
        }
    }

    public String insert(GroceryItem groceryItem) {
        setGroceryItems();
        repo.save(groceryItem);
        return "---ITEM INSERTADO---\n" + groceryItem.toString();
    }

    public String update(GroceryItem groceryItem) {
        if (repo.existsById(groceryItem.getId())) {
            repo.save(groceryItem);
            return "---ITEM ACTUALIZADO---\n" + groceryItem.toString();
        } else {
            return "---ITEM NO ENCONTRADO---";
        }
    }
    
    public String delete(String id) {
        setGroceryItems();

        if (repo.existsById(id)) { 
            repo.deleteById(id); 
            return "---ITEM ELIMINADO---";
        } else {
            return "---ITEM NO ENCONTRADO---";
        }
    }

    public String updateData(String id, GroceryItem groceryItem) {
        Optional<GroceryItem> optionalItem = repo.findById(id);
        if (optionalItem.isPresent()) {
            GroceryItem item = optionalItem.get();
            if (groceryItem.getName() != null) {
                item.setName(groceryItem.getName());
            }
            if (groceryItem.getQuantity() > 0) {
                item.setQuantity(groceryItem.getQuantity());
            }
            if (groceryItem.getCategory() != null) {
                item.setCategory(groceryItem.getCategory());
            }
            repo.save(item);
            
            return "---ITEM ACTUALIZADO---\n" + item.toString();
        } else {
            return "---ITEM NO ENCONTRADO---";
        }
    }


    public String optionsUpdate(){
        return "OPTIONS: It insert a new grosery item, if the grosery item doesn't exist, it will create automatically";
    }

}
