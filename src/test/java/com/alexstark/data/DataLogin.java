package com.alexstark.data;

import com.alexstark.config.App;

import java.util.HashMap;
import java.util.Map;

public class DataLogin {

    public Map<String, String> getDataForLogin() {

        final Map<String, String> DATA = new HashMap<>();

        DATA.put("Email", App.config.userLogin());
        DATA.put("Password", App.config.userPassword());

        return DATA;
    }
}

