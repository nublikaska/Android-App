package com.example.denis.holodos.beans.authorization;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.denis.holodos.modules.User;
import com.example.denis.holodos.services.AuthorizationService;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import static com.example.denis.holodos.activity.AuthorizationActivity.APP_PREFERENCES;

/**
 * Created by Maxim on 03.01.2018.
 */
@Named
@SessionScoped
public class Authentication implements Serializable {

    @EJB
    private AuthorizationService authorizationService;

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
