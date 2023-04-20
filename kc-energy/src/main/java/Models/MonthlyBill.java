package Models;

import java.util.Vector;

public class MonthlyBill {
    public int BillId;
    public int UserId;
    public double EnergyTariff;
    public double EnergyUsed;
    public double AmountReceived;
    public double EnergyRate;
    public double AmountDue;
    public String MeterType;
    public String Period;
    public String Address;


    public MonthlyBill(
            int billId,
            int userId,
            double energyTariff,
            double energyUsed,
            double amountReceived,
            double energyRate,
            double amountDue,
            String meterType,
            String period,
            String address) {
        BillId = billId;
        UserId = userId;
        EnergyTariff = energyTariff;
        EnergyUsed = energyUsed;
        AmountReceived = amountReceived;
        EnergyRate = energyRate;
        AmountDue = amountDue;
        MeterType = meterType;
        Period = period;
        Address = address;
    }

    public Vector<String> getRow(){
        Vector<String> billRow = new Vector<String>();

        billRow.add(Period);
        billRow.add(Double.toString(AmountDue));

        String paymentStatus = "Unpaid";
        if (AmountReceived >= AmountDue) {
            paymentStatus = "Paid";
        }
        billRow.add(paymentStatus);

        return billRow;
    }
}
