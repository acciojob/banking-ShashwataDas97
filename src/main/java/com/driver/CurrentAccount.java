package com.driver;

public class CurrentAccount extends BankAccount{
    String tradeLicenseId; // consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // Minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId = tradeLicenseId;
        if(balance < 5000){
            throw new InsufficientBalanceException("Insufficient Balance");
        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        boolean isValid = true;
        for(int i=0;i<tradeLicenseId.length()-1;i++){
            if(tradeLicenseId.charAt(i) == tradeLicenseId.charAt(i+1)){
                isValid = false;
            }
        }
        if(isValid == false){
            String newGeneratedTradeLicenseId = "";
            newGeneratedTradeLicenseId = rearrangeString(tradeLicenseId);
            if(newGeneratedTradeLicenseId == ""){
                throw new ValidLicenseException("Valid License can not be generated");
            }
            else{
                this.tradeLicenseId = newGeneratedTradeLicenseId;
            }
        }
    }

    public String rearrangeString(String tradeLicenseId){
        int length = tradeLicenseId.length();
        int[] frequency = new int[26];

        int maxFrequency = -1;
        char maxFrequencyChar = '#';

        for(int i=0;i<length;i++){
            char ch = tradeLicenseId.charAt(i);
            frequency[ch - 'A']++;
            if(frequency[ch - 'A'] > maxFrequency){
                maxFrequency = frequency[ch - 'A'];
                maxFrequencyChar = ch;
            }
        }

        int allowedFrequency = length % 2 == 0 ? length/2 : length/2 + 1;
        // Checking if the max frequency char is greater than the allowed frequency
        if(maxFrequency > allowedFrequency){
            return "";
        }

        // Placing the max freq char in the even position first
        char[] result = new char[length];
        int index = 0;
        while(frequency[maxFrequencyChar - 'A'] > 0){
            result[index] = maxFrequencyChar;
            index += 2;
            frequency[maxFrequencyChar - 'A']--;
        }

        // Then iterating over the remaining char and placing them respectively
        for(int i=0;i<26;i++){
            int times = frequency[i];
            while(times > 0){
                if (index >= length) {
                    index = 1;
                }
                result[index] = (char)(i + 'A');
                index += 2;
                times--;
            }
        }

        return result.toString();
    }
}
