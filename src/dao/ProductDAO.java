package dao;
import java.util.*;
import model.Product;
import java.sql.*;
public class ProductDAO {
    public void addProduct(Product p) throws SQLException {
        String sql = "INSERT INTO products (name, category, price, quantity, reorder_level) VALUES (?, ?, ?, ?, ?)";
        try(Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setString(1,p.getName());
            ps.setString(2, p.getCategory());
            ps.setDouble(3, p.getPrice());
            ps.setInt(4, p.getQuantity());
            ps.setInt(5,p.getReorderLevel());
            ps.executeUpdate();
        }
    }   
    public List<Product> getAllProducts() throws SQLException {
        List<Product> list= new ArrayList<>();
        String sql = "SELECT * FROM products";
        try(Connection con=DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql) ){
                while(rs.next()){
                    list.add(new Product(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("category"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getInt("reorder_level")));
                }

            }
            return list;

    }
    public void deleteProduct(int id) throws SQLException{
        String sql = "DELETE FROM products WHERE ID =? ";
        try(Connection con=DBConnection.getConnection();
        PreparedStatement ps= con.prepareStatement(sql)){
            ps.setInt(1,id);
            int rows=ps.executeUpdate();
            if (rows==0){
                System.out.println("product not found");
            }else{
                System.out.println("product deleted");
            }

        }

    }
    public void updateProduct(double price,int quantity,int id)throws SQLException{
        String sql="Update products SET price = ?, quantity= ? WHERE id=?";
        try( Connection con=DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setDouble(1,price);
            ps.setInt(2, quantity);
            ps.setInt(3, id);
            int updated=ps.executeUpdate();
            if(updated==0){
                System.out.println("product not found");
            }else{
                System.out.println("product updated");
            }
        }

    }
    public List<Product> searchByName(String keyword) throws SQLException{
        List<Product> list=new ArrayList<>();
        String sql ="SELECT * FROM products WHERE name LIKE ?";
        try(Connection con = DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql))
        {

        ps.setString(1,"%"+keyword+"%");
        ResultSet rs=ps.executeQuery();
        while (rs.next()) {
            list.add(new Product(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("category"),
                rs.getDouble("price"),
                rs.getInt("quantity"),
                rs.getInt("reorder_level")));
        }
        }
        return list;


    }
    public void stockIn(int id,int quantity) throws SQLException{
        String sql="UPDATE products SET quantity= quantity + ? WHERE ID=?";
        try(Connection con = DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            if (rows == 0) {
                System.out.println("product not found");
            } else {
                new TransactionDAO().addTransaction(id, "IN", quantity);
                System.out.println("stock added");
}

        }
    }
    public void stockOut(int id,int quantity) throws SQLException{
        String sql="UPDATE products SET quantity= quantity - ? WHERE ID=? AND quantity >= ?";
        try(Connection con = DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql)){
            ps.setInt(1, quantity);
            ps.setInt(2, id);
            ps.setInt(3, quantity);
        int rows = ps.executeUpdate();
         if (rows == 0) {
            System.out.println("product not found or not enough stock");
         }else{
            new TransactionDAO().addTransaction(id, "OUT", quantity);
            System.out.println("stock removed");
         }

        }
    }
}

