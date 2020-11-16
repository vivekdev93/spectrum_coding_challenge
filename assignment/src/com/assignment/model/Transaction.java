package com.assignment.model;

import java.util.Date;

public class Transaction {

    private int id;
    private Customer customer;
    private double amount;
    private Date dateOfPurchase;


    public Transaction(int id, Customer customer, double amount, Date date){
        this.id=id;
        this.customer = customer;
        this.amount = amount;
        this.dateOfPurchase = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDateOfPurchase() {
        return dateOfPurchase;
    }

    public void setDateOfPurchase(Date dateOfPurchase) {
        this.dateOfPurchase = dateOfPurchase;
    }
}
