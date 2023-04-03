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
    private static final String PASSWORD_PATTERN = "((?=.*d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!.#$@_+,?-]).{6,45})"; 
    public PasswordService() { 
        pattern = Pattern.compile(PASSWORD_PATTERN); 
    } 
    
    public boolean check(final String password) { 
        return pattern.matcher(password).matches(); 
    } 
}
