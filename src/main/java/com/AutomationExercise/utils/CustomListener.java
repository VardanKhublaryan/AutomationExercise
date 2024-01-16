package com.AutomationExercise.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.AutomationExercise.utils.CustomWebDriver.getDriver;

public class CustomListener implements ITestListener {
    private int fileNum = 1;


    @Override
    public void onTestStart(ITestResult result) {
        CustomLog4j.info("Start: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        CustomLog4j.info("Success of test cases and its details are : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        CustomLog4j.error("Failure of test cases and its details are : " + result.getName());

        Screenshot screenshot1;
        screenshot1 = new AShot().coordsProvider(new WebDriverCoordsProvider()).
                takeScreenshot(getDriver());
        BufferedImage randomImageItem1 = screenshot1.getImage();
        File file = new File("ScreenShots" + "bug" + fileNum + ".png");
        try {
            ImageIO.write(randomImageItem1, "png", file);
            if (file.exists()) {
                fileNum++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        CustomLog4j.info("Skip of test cases and its details are : " + result.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        CustomLog4j.info("Failure of test cases and its details are : " + result.getName());
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
    }

    public static void log(String info) {
        CustomLog4j.info(info);
    }
}
