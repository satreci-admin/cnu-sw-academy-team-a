package com.example.simplerpa.model;

import org.springframework.util.Assert;

import java.util.Objects;
import java.util.regex.Pattern;

public class Email {

    private final String address;
    public Email(String address){
        //이메일 검증
        Assert.notNull(address,"address should not null");
        Assert.isTrue(address.length()>=4 && address.length()<=50,"address Length error");
        Assert.isTrue(checkAddress(address), "invalid email address");
        this.address = address;
    }
    private static boolean checkAddress(String address){
        return Pattern.matches("\\b[\\w\\.-]+@[\\w\\.-]+\\.\\w{2,4}\\b",address);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Email email = (Email) o;
        return Objects.equals(address, email.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(address);
        return sb.toString();
    }
    public String getAddress(){
        return address;
    }

}