package com.example.denis.holodos.implementsService;

import com.example.denis.holodos.modules.User;
import com.example.denis.holodos.services.AuthorizationService;

import java.io.Serializable;

/**
 * Created by Maxim on 03.01.2018.
 */
public class Authentication implements Serializable {

    private AuthorizationService authorizationService = new AuthorizationService();

    private User user;

    public boolean Login(String login, String password) {
        if (!(user = authorizationService.authenticationUser(login)).equals(null)) {
            if (authorizationService.checkUser(user, login, password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
