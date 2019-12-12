package za.co.ilab.automation.utilities;

import com.google.common.base.Function;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Utilities {

    public static void switchToWindow(WebDriver driver) {
        String currentTab = driver.getWindowHandle();

        for (String tab : driver.getWindowHandles()) {
            if (!tab.equals(currentTab)) {
                driver.switchTo().window(tab);
            }
        }
    }

    public static void scrollToView(By by, String elementName, WebDriver driver) throws InterruptedException {
        WebElement element = driver.findElement(by.id(elementName));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
    }

    public static WebElement waitForElement(WebDriver driver, By by, int timeout) {
        long end = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < end) {
            List<WebElement> elements = driver.findElements(by);

            if (elements.toArray().length > 0) {
                return elements.get(0);
            }
        }
        throw new RuntimeException("Could not find element " + by.toString());
    }

    public static void waitForElement(WebDriver driver, String text, int timeout, String errorMSG) throws Exception {
        long end = System.currentTimeMillis() + timeout;
        while (System.currentTimeMillis() < end) {
            if (isTextOnPage(driver, text)) {
                return;
            }
        }
        throw new RuntimeException("Could not find text " + text + " - " + errorMSG);
    }

    public static boolean isTextOnPage(WebDriver driver, String text) throws Exception {
        return (driver.getPageSource().indexOf(text) >= 0);
    }

    public static WebElement fluentWait(final By locator, WebDriver rwd) {
        Wait<WebDriver> wait = new FluentWait<WebDriver>(rwd)
                .withTimeout(40, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS)
                .ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(locator);
            }
        });
        return foo;
    }

    public static Boolean isTextPresentAfterWait(
            final String strStringToAppear, WebDriver rwd) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(rwd)
                    .withTimeout(40, TimeUnit.SECONDS)
                    .pollingEvery(5, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);

            Boolean foo = wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver webDriver) {
                    return (webDriver.getPageSource().indexOf(strStringToAppear) >= 0);
                }
            });
            return foo;

        } catch (TimeoutException e) {
            throw new RuntimeException("Could not find text " + strStringToAppear + " - " + e);
        }
    }

    public static Boolean isTitlePresentAfterWait(final String strPageTitle, RemoteWebDriver rwd) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(rwd)
                    .withTimeout(40, TimeUnit.SECONDS)
                    .pollingEvery(5, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);

            Boolean foo = wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver webDriver) {
                    return ((webDriver.getTitle().equals(strPageTitle)));
                }
            });
            return foo;
        } catch (TimeoutException e) {
            throw new RuntimeException("Could not find Title " + strPageTitle + " - " + e);
        }
    }

    public static Boolean containsTitleAfterWait(final String strPageTitle, WebDriver rwd) {
        try {
            Wait<WebDriver> wait = new FluentWait<WebDriver>(rwd)
                    .withTimeout(40, TimeUnit.SECONDS)
                    .pollingEvery(5, TimeUnit.SECONDS)
                    .ignoring(NoSuchElementException.class);

            Boolean foo = wait.until(new ExpectedCondition<Boolean>() {
                public Boolean apply(final WebDriver webDriver) {
                    return ((webDriver.getTitle().contains(strPageTitle)));
                }
            });
            return foo;
        } catch (TimeoutException e) {
            throw new RuntimeException("Could not find Title " + strPageTitle + " - " + e);
        }
    }

    public static String getScreenShot(WebDriver driver) {
        String reportDirectory = null;
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");

        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            reportDirectory = System.getProperty("user.dir") + "/src/main/java/za/co/ilab/automation/images/";
            reportDirectory = reportDirectory + "Image-" + formater.format(calendar.getTime()) + ".jpg";
            File destFile = new File(reportDirectory);
            FileUtils.copyFile(scrFile, destFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reportDirectory;
    }

}
