package outsourcing.model;

import java.util.ArrayList;
import java.util.List;

public class Grocery {
	String groceryType;
	List<String> Items;
	
	public Grocery(){
		Items = new ArrayList<String>();
	}
	
	public String[] getItems(){
		return Items.toArray(new String[Items.size()]);
	}
	
	public void setItems(String[] items){
		if(!Items.isEmpty())
			Items.clear();
		for(String item:items){
			Items.add(item);
		}
	}
	
	public void addItem(String item){
		Items.add(item);
	}

	public String getGroceryType() {
		return groceryType;
	}

	public void setGroceryType(String groceryType) {
		this.groceryType = groceryType;
	}
}
