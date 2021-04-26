package com.softserveinc.ita.common;

import io.qameta.allure.Attachment;
import lombok.SneakyThrows;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

import static com.codeborne.selenide.Screenshots.takeScreenShotAsFile;
import static com.google.common.io.Files.toByteArray;

public class ScreenshotListener implements ITestListener {
    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        screenshot();
    }

    @Attachment(value = "{Assert test failed screenshot}", type = "image/png")
    public byte[] screenshot() throws IOException {
        return toByteArray(takeScreenShotAsFile());
    }
}
