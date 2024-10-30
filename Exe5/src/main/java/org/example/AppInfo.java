package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppInfo {

    @Value("${app.name}")
    private String name;

    @Value("${app.version}")
    private String version;

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    public void displayInfo() {
        System.out.println("Application Name: " + name);
        System.out.println("Application Version: " + version);
    }
}
