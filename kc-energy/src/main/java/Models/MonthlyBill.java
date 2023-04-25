package Models;

import DataAccess.CustomerPaymentRepository;

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
        billRow.add(Double.toString(AmountReceived));
        billRow.add(Double.toString(AmountDue));

        String paymentStatus = "Unpaid";
        if (AmountReceived == AmountDue) {
            paymentStatus = "Paid";
        }
        else if (AmountReceived > AmountDue) {
            paymentStatus = "Credit";
        }
        billRow.add(paymentStatus);

        return billRow;
    }

    public String printCustomerBill(Customer customer) {
        return "Customer: " + customer.CustomerName + "\n\n"
                + "Address: " + this.Address + "\n"
                + "Energy Used: " + this.EnergyUsed + "\n"
                + "Energy Tariff: " + this.EnergyTariff + "\n"
                + "Amount Due: " + this.AmountDue + "\n"
                + "Amount Received: " + this.AmountReceived + "\n\n"
                + "Payments:" + "\n\n"
                + CustomerPaymentRepository.GetPayments(this.BillId);
    }

    @Override
    public String toString() {
        return "MonthlyBill{" +
                "BillId=" + BillId +
                ", UserId=" + UserId +
                ", EnergyTariff=" + EnergyTariff +
                ", EnergyUsed=" + EnergyUsed +
                ", AmountReceived=" + AmountReceived +
                ", EnergyRate=" + EnergyRate +
                ", AmountDue=" + AmountDue +
                ", MeterType='" + MeterType + '\'' +
                ", Period='" + Period + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }
}
