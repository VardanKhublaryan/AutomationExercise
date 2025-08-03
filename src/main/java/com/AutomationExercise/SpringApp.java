package com.AutomationExercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.AutomationExercise.pages",
        "com.AutomationExercise.tests", // if needed
        "com.AutomationExercise.utils"  // if needed
})
public class SpringApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringApp.class, args);
    }
}
