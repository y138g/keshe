package pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderInfo {
    private int orderID;
    private int userID;
    private int powerBankID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BigDecimal totalCost;

    // 构造方法、Getter和Setter方法


    @Override
    public String toString() {
        return "OrderInfo{" +
                "orderID=" + orderID +
                ", userID=" + userID +
                ", powerBankID=" + powerBankID +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalCost=" + totalCost +
                '}';
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPowerBankID() {
        return powerBankID;
    }

    public void setPowerBankID(int powerBankID) {
        this.powerBankID = powerBankID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public OrderInfo(int orderID, int userID, int powerBankID, LocalDateTime startTime, LocalDateTime endTime, BigDecimal totalCost) {
        this.orderID = orderID;
        this.userID = userID;
        this.powerBankID = powerBankID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
    }
}
