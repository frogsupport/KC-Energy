package Models;

import java.util.Vector;

public class Customer {
    public int CustomerId;
    public String CustomerName;
    public String PhoneNumber;
    public String CurrentAddress;
    public double CurrentTariff;
    public double CurrentEnergyRate;
    public String MeterType;

    public Customer(int customerId, String customerName, String phoneNumber, String currentAddress, double currentTariff, double currentEnergyRate, String meterType) {
        CustomerId = customerId;
        CustomerName = customerName;
        PhoneNumber = phoneNumber;
        CurrentAddress = currentAddress;
        CurrentTariff = currentTariff;
        CurrentEnergyRate = currentEnergyRate;
        MeterType = meterType;
    }

    public Vector<String> getRow(){
        Vector<String> customerRow = new Vector<String>();

//        customerRow.add(Integer.toString(CustomerId));
        customerRow.add(CustomerName);
        customerRow.add(PhoneNumber);
        customerRow.add(CurrentAddress);
//        customerRow.add(Double.toString(CurrentTariff));
//        customerRow.add(Double.toString(CurrentEnergyRate));
//        customerRow.add(MeterType);

        return customerRow;
    }

    public void printCustomer() {
        System.out.printf("%s %s %s %s %s %s %s %s\n",
                CustomerId,
                CustomerName,
                PhoneNumber,
                CurrentAddress,
                CurrentTariff,
                CurrentEnergyRate,
                MeterType);
    }

    @Override
    public String toString() {
        return "Customer Name: " + CustomerName
                + " Address: " + CurrentAddress
                + " Phone Number: " + PhoneNumber;
    }
}
