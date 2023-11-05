package com.driver;

public class SavingsAccount extends BankAccount{
    double rate;
    double maxWithdrawalLimit;

    public SavingsAccount(double rate, double maxWithdrawalLimit) {
        super();
        this.rate = rate;
        this.maxWithdrawalLimit = maxWithdrawalLimit;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMaxWithdrawalLimit() {
        return maxWithdrawalLimit;
    }

    public void setMaxWithdrawalLimit(double maxWithdrawalLimit) {
        this.maxWithdrawalLimit = maxWithdrawalLimit;
    }


    public SavingsAccount(String name, double balance, double maxWithdrawalLimit, double rate){
        // minimum balance is 0 by default
        super(name, balance, 0);
        this.maxWithdrawalLimit=maxWithdrawalLimit;
        this.rate= rate;
    }

    public SavingsAccount() {
    }

    @Override
    public void withdraw(double amount) throws Exception {
        // Might throw the following errors:
        // 1. "Maximum Withdraw Limit Exceed" : If the amount exceeds maximum withdrawal limit
        // 2. "Insufficient Balance" : If the amount exceeds balance
        if(amount>maxWithdrawalLimit){
            throw new Exception("Maximum Withdraw Limit Exceeded");
        }
        else if(amount>this.getBalance()){
            throw new Exception("Insufficient Balance");
        }
        else this.setBalance(this.getBalance()-amount);

    }

    public double getSimpleInterest(int years){
        // Return the final amount considering that bank gives simple interest on current amount
        // S.I= P*T*R/100
        return (this.getBalance()*years*this.rate/100)+this.getBalance();
    }

    public double getCompoundInterest(int times, int years){
        // Return the final amount considering that bank gives compound interest on current amount given times per year
        // Amount= p(1+r/n*100)^nt
        return this.getBalance()*Math.pow((1+this.rate/(times*100)),times*years);
    }

}