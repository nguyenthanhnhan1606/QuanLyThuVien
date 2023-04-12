/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ktpm.services;

import java.util.regex.Pattern;

/**
 *
 * @author THANH NHAN
 */
public class PasswordService {

    private Pattern pattern;
    private Pattern pattern1;
    private Pattern pattern2;

    private static final String PASSWORD_PATTERN = "((?=.*d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!.#$@_+,?-]).{6,45})";
    private static final String EMAIL_PATTERN = "^[a-zA-Z]+[a-zA-Z0-9]*@{1}\\w+mail.com$";
    private static final String SDT_PATTERN = "([0-9].{9,12})";

    public PasswordService() {
        pattern = Pattern.compile(PASSWORD_PATTERN);
    }

    public boolean check(final String password) {
        return pattern.matcher(password).matches();
    }

    public PasswordService(String kw) {
        pattern1 = Pattern.compile(EMAIL_PATTERN);
    }

    public boolean checkEmail(final String email) {
        return pattern1.matcher(email).matches();
    }

    public PasswordService(int id) {
        pattern2 = Pattern.compile(SDT_PATTERN);
    }

    public boolean checkSdt(final String sdt) {
        return pattern2.matcher(sdt).matches();
    }
}
