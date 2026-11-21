package dao;
import model.Transaction;
import java.util.*;
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
    public List<Transaction> getAllTransactions() throws SQLException {
        List<Transaction> list = new ArrayList<>();
        String sql = "SELECT * FROM transactions ORDER BY txn_date DESC";
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Transaction(
                    rs.getInt("id"),
                    rs.getInt("product_id"),
                    rs.getString("type"),
                    rs.getInt("quantity"),
                    rs.getString("txn_date")));
            }
        }
        return list;
    }
}
