import dao.ProductDAO;
import model.Product;

import java.util.List;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        ProductDAO dao = new ProductDAO();
        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("\n--- Inventory Management ---");
            System.out.println("1. Add product");
            System.out.println("2. View all products");
            System.out.println("3. delete product ");
            System.out.println("4. update products");
            System.out.println("5. Search product");
            System.out.println("6. Stock in");
            System.out.println("7. Stock out");
            System.out.println("8. Exit");
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try{
                if(choice==1){
                    System.out.print("Name: ");
                    String name = sc.nextLine();
                    System.out.print("Category: ");
                    String category = sc.nextLine();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Reorder level: ");
                    int reorder = sc.nextInt();
                    dao.addProduct(new Product(0, name, category, price, qty, reorder));
                    System.out.println("Product added!");
                }else if (choice==2){
                    for (Product p : dao.getAllProducts()) {
                        System.out.println(p);
                    }

                }else if(choice==3){
                    System.out.println("enter the id of the element to delete");
                    int id=sc.nextInt();
                    dao.deleteProduct(id);
                   
                }else if(choice==4){
                    System.out.print("int: ");
                    int id = sc.nextInt();
                    System.out.print("Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt();
                    dao.updateProduct(price,qty,id);

                    
                }else if(choice==5){
                    System.out.println("enter name to search");
                    String keyword=sc.nextLine();
                    List<Product> results=dao.searchByName(keyword);
                    if(results.isEmpty())
                        System.out.println("no products found");
                    else{
                        for(Product p:results){
                        System.out.println(p);
                    }
                    }

                }else if(choice==6){
                    System.out.print("Product id: ");
                    int id = sc.nextInt();
                    System.out.print("Quantity to add: ");
                    int qty = sc.nextInt();
                    dao.stockIn(id, qty);
                }else if(choice==7){
                    System.out.print("Product id: ");
                    int id = sc.nextInt();
                    System.out.print("Quantity to remove: ");
                    int qty = sc.nextInt();
                    dao.stockOut(id, qty);
                }else if(choice==8){
                    System.out.println("Bye!");
                    break;
                }

            }catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }

       
        }
    }