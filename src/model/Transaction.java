package model;

public class Transaction {
    private int id;
    private int productId;
    private String type;
    private int quantity;
    private String date;
    
    public Transaction(int id,int productId,String type,int quantity,String date){
      this.id=id;
      this.productId=productId;
      this.type=type;
      this.quantity=quantity;
      this.date=date;
    }
    public int getId(){
        return id;
    }
    public int getProductId(){
        return productId;
    }
    public String getType(){
        return type;
    }
    public int getQuantity(){
        return quantity;
    }
    public String getDate(){
        return date;
    }
    @Override
    public String toString() {
        return id + " | Product " + productId + " | " + type + " | Qty: " + quantity + " | " + date;
    }

    
}
