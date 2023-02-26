public class AccountUser {
    private int id;
    private int userId;
    private double balance;
    private String currency;

    public AccountUser(int id, int userId, double balance, String currency) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.currency = currency;
    }

    public AccountUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
