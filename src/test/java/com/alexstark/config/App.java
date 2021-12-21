package com.alexstark.config;

import org.aeonbits.owner.ConfigFactory;

public class App {
    public static com.alexstark.config.AppConfig config =
            ConfigFactory.create(com.alexstark.config.AppConfig.class, System.getProperties());
}
