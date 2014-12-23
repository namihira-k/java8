/**
 * Copyright 2014 Kohsuke Namihira All Rights Reserved.
 */

package jp.co.namihira.java8;

public class LoginModel {

    private static String username = "namihira";
    private static String password = "namihira";

    public LoginModel(){
    }

    public static boolean login(final String username, final String password) {
        return LoginModel.username.equals(username) && LoginModel.password.equals(password);
    }

}