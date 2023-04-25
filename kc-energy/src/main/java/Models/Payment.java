package Models;

public class Payment {
    public int UserId;
    public int BillId;
    public int PaymentId;
    public double PaymentAmount;

    public Payment(int userId, int billId, int paymentId, double paymentAmount) {
        UserId = userId;
        BillId = billId;
        PaymentId = paymentId;
        PaymentAmount = paymentAmount;
    }

    public String toString() {
       return "Payment Amount: $" + this.PaymentAmount + "\n";
    }
}
