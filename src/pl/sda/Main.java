package pl.sda;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

    AccountJdbcDAO dao = new AccountJdbcDAO();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    LocalDateTime now = LocalDateTime.now();

    public Main() throws SQLException {

    }

    public static void main(String[] args) throws SQLException {
        Main main = new Main();
        main.runApp(args);
    }

    public void runApp(String[] args) throws SQLException {


        boolean finish = false;
        try (Scanner scanner = new Scanner(System.in)) {
            while (!finish) {
                System.out.println("|------------------------------------|");
                System.out.println("|                MENU                |");
                System.out.println("|------------------------------------|");
                System.out.println("|    OPTIONS:                        |");
                System.out.println("|         1. Add new account         |");
                System.out.println("|         2. Edit account            |");
                System.out.println("|         3. Find account by ID      |");
                System.out.println("|         4. Show all account        |");
                System.out.println("|         5. Delete account by ID    |");
                System.out.println("|         6. Exit                    |");
                System.out.println("|------------------------------------|");
                int digit = scanner.nextInt();
                switch (digit) {
                    case 1:
                        System.out.println("|      Enter account's balance:      |");
                        int balance = scanner.nextInt();
                        System.out.println("|      Enter account's number:       |");
                        int number = scanner.nextInt();
                        String creationDate = dtf.format(now);
                        addNewAccount(balance, number, creationDate);
                        break;
                    case 2:
                        editAccount(scanner);
                        break;
                    case 3:
                        findAccountById(scanner);
                        System.out.println("|      Enter any key to return       |");
                        scanner.next();
                        break;
                    case 4:
                        showAllAccount();
                        break;
                    case 5:
                        deleteAccountById(scanner);
                        break;
                    case 6:
                        finish = true;
                }
            }
        }
    }


    private void addNewAccount(int balance, int number, String creationDate) throws SQLException {
        dao.creatNewAccount(balance, number, creationDate);
    }

    private void editAccount(Scanner scanner) {
        boolean finish = false;
        try  {
            while (!finish) {
                System.out.println("|------------------------------------|");
                System.out.println("|            EDIT ACCOUNT            |");
                System.out.println("|------------------------------------|");
                System.out.println("|    OPTIONS:                        |");
                System.out.println("|         1. Change balance          |");
                System.out.println("|         2. Change account's number |");
                System.out.println("|         3. Change creation date    |");
                System.out.println("|         4. Return to main menu     |");
                System.out.println("|------------------------------------|");
                int digit = scanner.nextInt();
                switch (digit) {
                    case 1:
                        System.out.println("|        Enter account's ID:       |");
                        int id = scanner.nextInt();
                        System.out.println("|   Enter new account's balance:   |");
                        int balance = scanner.nextInt();
                        dao.changeBalance(id, balance);
                        break;
                    case 2:
                        System.out.println("|        Enter account's ID:       |");
                        int id2 = scanner.nextInt();
                        System.out.println("|   Enter new account's number:    |");
                        int number = scanner.nextInt();
                        dao.changeNumber(id2, number);
                        break;
                    case 3:
                        System.out.println("|        Enter account's ID:       |");
                        int id3 = scanner.nextInt();
                        System.out.println("|   Enter new account's number:    |");
                        String creationDate = scanner.next();
                        dao.changeCreationDate(id3, creationDate);
                        break;
                    case 4:
                        finish = true;
                        break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void findAccountById(Scanner scanner) throws SQLException {
        System.out.println("|------------------------------------|");
        System.out.println("|         Enter account's ID:        |");
        int id = scanner.nextInt();
        dao.findById(id);
    }

    private void showAllAccount() throws SQLException {
        dao.findAll();
    }

    private void deleteAccountById(Scanner scanner) throws SQLException{
        System.out.println("|        Enter account's ID:       |");
        int id = scanner.nextInt();
        dao.deleteAccount(id);
    }
}
