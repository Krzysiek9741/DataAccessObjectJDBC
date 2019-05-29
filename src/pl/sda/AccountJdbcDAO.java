package pl.sda;

import java.sql.*;
import java.time.LocalDate;

public class AccountJdbcDAO {

    final static String url = "jdbc:mysql://localhost/zad1?serverTimezone=Europe/Warsaw";
    final static String user = "root";
    final static String pass = "polibuda12";
    static Connection conn;






        //openConncection();
        //closeConncection();
        //createStatement();
        //createPrepareStatement();
        //findAll();
        //findById();
        //findByNumber();
        //findByCreationDate();
        //findAllAfterCreationDate();
        //findAccountsCount();


    public AccountJdbcDAO() throws SQLException {
        openConnection();
    }

    private void openConnection() throws SQLException {
        conn = DriverManager.getConnection(url, user, pass);
    }

    public void closeConnection() throws SQLException {
        conn.close();

    }

    private Statement createStatement() throws SQLException{
        return conn.createStatement();
    }

    private PreparedStatement createPreparedStatement(String sqlCommand) throws SQLException{
        return conn.prepareStatement(sqlCommand);

    }
    public void findAll() throws SQLException {
        String selectAll = "SELECT * FROM Account";
        Statement st = createStatement();
        ResultSet rs = st.executeQuery(selectAll);
        showResults(rs);
        st.close();
    }

    public void findById(int id) throws SQLException {
        String selectById = "SELECT * FROM Account WHERE idAccount = " + id;
        Statement st = createStatement();
        ResultSet rs = st.executeQuery(selectById);
        showResults(rs);
        st.close();
    }

    public void findByNumber(int number) throws SQLException{
        String selectByNumber = "SELECT * FROM Account WHERE number = " + number;
        Statement st = createStatement();
        ResultSet rs = st.executeQuery(selectByNumber);
        showResults(rs);
        st.close();
    }

    public void findByCreationDate(String date) throws SQLException{

        String selectByCreationDate = "SELECT * FROM Account WHERE creation_date = " + "'" + date + "'";
        Statement st = createStatement();
        ResultSet rs = st.executeQuery(selectByCreationDate);
        showResults(rs);
        st.close();
    }

    public void findAllAfterCreationDate(String date) throws SQLException{

        String selectByCreationDate = "SELECT * FROM Account WHERE creation_date > " + "'" + date + "'";
        Statement st = createStatement();
        ResultSet rs = st.executeQuery(selectByCreationDate);
        showResults(rs);
        st.close();
    }

    public void findAccountsCount() throws SQLException{

        String selectByCreationDate = "SELECT COUNT(idAccount) FROM Account";
        Statement st = createStatement();
        ResultSet rs = st.executeQuery(selectByCreationDate);
        System.out.println("Number of accounts is: " + rs.getLong("count(IdAccount)"));
    }

    private void showResults(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println("ID: " + rs.getString("idAccount"));
            System.out.println("Balance: " + rs.getInt("balance"));
            System.out.println("Number: " + rs.getInt("number"));
            System.out.println("Creation date: " + rs.getDate("creation_date"));
            System.out.println("Close date: " + rs.getDate("close_date"));
            System.out.println("-------------------------------------");
        }
    }

    public void creatNewAccount(int balance, int number, String cretionDate) throws SQLException {
        String insertNewAccount = "insert into Account(balance, number, creation_date) " +
                "values(" + balance + "," +  number + "," + "'" + cretionDate + "'" + ")";
        Statement st = createStatement();
        st.executeUpdate(insertNewAccount);
    }

    public void changeBalance(int id, int newBalance) throws SQLException {
        String changeBalance = "update Account set balance = ? where idAccount = ?";
        PreparedStatement pst = createPreparedStatement(changeBalance);
        pst.setInt(1, newBalance);
        pst.setInt(2, id);
        pst.execute();
    }

    public void changeNumber(int id, int newNumber) throws SQLException {
        String changeNumber = "update Account set number = ? where idAccount = ?";
        PreparedStatement pst = createPreparedStatement(changeNumber);
        pst.setInt(1, newNumber);
        pst.setInt(2, id);
        pst.execute();
    }

    public void changeCreationDate(int id, String newCreationDate) throws SQLException{
        String changeCreationDate = "update Account set creation_date = ? where idAccount = ?";
        PreparedStatement pst = createPreparedStatement(changeCreationDate);
        pst.setString(1, newCreationDate);
        pst.setInt(2, id);
        pst.execute();
    }

    public boolean deleteAccount(int id) throws SQLException{
        String deleteAccountById = "delete from Account where idAccount = " + id;
        Statement st = createStatement();
        int result = st.executeUpdate(deleteAccountById);
        if ( result > 0) {
            return true;
        }else {
            return false;
        }
    }
}
