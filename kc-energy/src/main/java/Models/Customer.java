package Models;

public class Customer {
    public String CustomerId;
    public String CustomerName;
    public String PhoneNumber;
    public String CurrentAddress;
    public double CurrentTariff;
    public double CurrentEnergyRate;
    public String MeterType;

    public Customer(String customerId, String customerName, String phoneNumber, String currentAddress, double currentTariff, double currentEnergyRate, String meterType) {
        CustomerId = customerId;
        CustomerName = customerName;
        PhoneNumber = phoneNumber;
        CurrentAddress = currentAddress;
        CurrentTariff = currentTariff;
        CurrentEnergyRate = currentEnergyRate;
        MeterType = meterType;
    }
}
