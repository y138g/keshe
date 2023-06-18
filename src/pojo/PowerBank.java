package pojo;

public class PowerBank {
    private int powerBankID;
    private String brand;
    private String model;
    private int capacity;
    private int remainingPower;
    private String status;

    // 构造方法、Getter和Setter方法


    @Override
    public String toString() {
        return "PowerBank{" +
                "powerBankID=" + powerBankID +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", remainingPower=" + remainingPower +
                ", status='" + status + '\'' +
                '}';
    }

    public int getPowerBankID() {
        return powerBankID;
    }

    public void setPowerBankID(int powerBankID) {
        this.powerBankID = powerBankID;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRemainingPower() {
        return remainingPower;
    }

    public void setRemainingPower(int remainingPower) {
        this.remainingPower = remainingPower;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public PowerBank() {
    }

    public PowerBank(int powerBankID, String brand, String model, int capacity, int remainingPower, String status) {
        this.powerBankID = powerBankID;
        this.brand = brand;
        this.model = model;
        this.capacity = capacity;
        this.remainingPower = remainingPower;
        this.status = status;
    }
}
