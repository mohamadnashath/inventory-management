package model;

public class Product {
    private int id;
    private String name;
    private String category;
    private double price;
    private int quantity;
    private int reorderLevel;

    public Product(int id,String name,String category,double price,int quantity,int reorderLevel){
        this.id=id;
        this.name=name;
        this.category=category;
        this.price=price;
        this.quantity=quantity;
        this.reorderLevel=reorderLevel;

    }
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public String getCategory(){
        return category;
    }
    public double getPrice(){
        return price;
    }
    public int getQuantity(){
        return quantity;
    }
    public int getReorderLevel(){
        return reorderLevel;
    }

    @Override 
    public String  toString(){
        return id +" | " + name +" | " + category +" | Rs." + price +" | Qty: " + quantity +" | Reorder: " + reorderLevel;
        
    }

}

