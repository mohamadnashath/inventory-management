package dao;

import java.sql.*;
public class TransactionDAO{
    public void addTransaction(int productId,String type,int quantity)throws SQLException{
        String sql="INSERT INTO transactions (product_id,type,quantity) VALUES (?,?,?)";
        try(Connection con = DBConnection.getConnection();
        PreparedStatement ps=con.prepareStatement(sql))
        {
            ps.setInt(1,productId);
            ps.setString(2,type);
            ps.setInt(3,quantity);
            ps.executeUpdate();
        }
    }
}
