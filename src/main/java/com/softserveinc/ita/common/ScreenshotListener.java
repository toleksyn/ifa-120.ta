package com.softserveinc.ita.common;

import com.codeborne.selenide.Screenshots;
import com.google.common.io.Files;
import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;

public class ScreenshotListener implements ITestListener {
    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        screenshot();
    }

    @Attachment(value = "{Assert test failed screenshot}", type = "image/png")
    public byte[] screenshot() throws IOException {
        File screenshot = Screenshots.takeScreenShotAsFile();
        FileUtils.copyFile(screenshot, new File("allure-results/img.png"));
        return Files.toByteArray(screenshot);
    }
}
