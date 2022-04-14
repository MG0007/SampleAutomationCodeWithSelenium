import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ComparisonInnerAllTestCases {

//    private final String COMPARE_INNER = "http://localhost:9000/compare/deepstream-io-vs-twilio";
    private final String COMPARE_INNER = "https://ably-voltaire-pr-226.herokuapp.com/compare/deepstream-io-vs-twilio";
//    private final String COMPARE_INNER = "https://ably-voltaire-pr-226.herokuapp.com/compare/ably-vs-parse-server";
    private final String BORDER_THICKNESS = "4px";
    private final int FONT_BUFFER = 4;
    private final int DIVIDER_BORDER_THICKNESS = 2;
    private final int DESIGN_FONT_WEIGHT = 400;

    @Test()
    public void TestimonialShowcaseIssues()
    {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement testimonialBox = null;
        WebElement separatorLine = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 1280);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
        }catch (Exception e){
            driver.quit();
        }

        try {
            testimonialBox = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[3]/div/div/div"));
        }catch (Exception e){
            System.out.println();
            System.out.println("Element -testimonialBox- error message :" + e.getMessage());
            driver.quit();
        }

        int testimonialBoxWidth = 0;

        if (testimonialBox != null){
            testimonialBoxWidth = testimonialBox.getSize().width;
        }

        try {
            separatorLine = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[3]/div/div/div/div[1]/div"));
        }catch (Exception e){
            System.out.println();
            System.out.println("Element -separatorLine- error message :" + e.getMessage());
            driver.quit();
        }

        int separatorLineWidth = 0;

        if (separatorLine != null) {
            separatorLineWidth = separatorLine.getSize().width;
        }

        driver.quit();

        Assert.assertEquals(testimonialBoxWidth, separatorLineWidth);
    }

    @Test()
    public void FooterMoreSpaceNeededBetweenTextAndButtonStartWithAFreeAccount()
    {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        WebElement child = null;
        WebElement lastChild = null;
        WebElement button = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 375);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try
        {
            driver.get(COMPARE_INNER);
        }
        catch (Exception e)
        {
            driver.quit();
        }


        for (int i = 1; i <= 100; i++)
        {
            try
            {
                child = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[6]/div/div[2]/div/span[1]/div/div[" + i + "]"));
                lastChild = child;
            }
            catch (Exception e)
            {
                break;
            }
        }

        int yLastChildUpperLeft = lastChild.getLocation().getY();
        int lastChildHeight = lastChild.getSize().height;
        int yLastChildLowerLeft = yLastChildUpperLeft + lastChildHeight;

        try {
            button = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[6]/div/div[2]/div/span[2]/a"));
        }catch (Exception e){
            driver.quit();
        }

        int yButtonUpperLeft = button.getLocation().getY();

        int distance = yButtonUpperLeft - yLastChildLowerLeft;

        driver.quit();

        Assert.assertTrue(60 <= distance && distance <= 70);
    }

    @Test()
    public void FooterMoreSpaceNeededBetweenButtonStartALiveChatAndTextUnderneath()
    {
        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        WebElement buttonStartLiveChat = null;
        WebElement textUnderneath = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 375);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try
        {
            driver.get(COMPARE_INNER);
        }
        catch (Exception e)
        {
            driver.quit();
        }

        try {
            buttonStartLiveChat = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[6]/div/div[1]/div/div[2]/div/span[1]/a"));
        }catch (Exception e){
            driver.quit();
        }

        try {
            textUnderneath = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[6]/div/div[1]/div/div[2]/div/span[2]/p"));
        }catch (Exception e){
            driver.quit();
        }

        int yButtonStartLiveChatUpperLeft = buttonStartLiveChat.getLocation().getY();
        int buttonStartLiveChatHeight = buttonStartLiveChat.getSize().height;
        int yButtonStartLiveChatLowerLeft = yButtonStartLiveChatUpperLeft + buttonStartLiveChatHeight;

        int yTextUnderneathUpperLeft = textUnderneath.getLocation().getY();

        int distance = yTextUnderneathUpperLeft - yButtonStartLiveChatLowerLeft;

        driver.quit();

        Assert.assertTrue(83 <= distance && distance <= 93);

    }

    @Test()
    public void DistanceBetweenLogoAndCodeBoxExampleTooBig() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement logo = null;
        WebElement codeBox = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 375);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
        }catch (Exception e){
            System.out.println();
            System.out.println("Server did not respond");
            driver.quit();
        }

        try {
            logo = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[3]/div[1]/div/picture/img"));
        }catch (Exception e){
            System.out.println();
            System.out.println("Element -logo- cannot be found");
            driver.quit();
        }

        try {
            codeBox = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[3]/div[3]/div"));
        }catch (Exception e){
            System.out.println();
            System.out.println("Element -codeBox- cannot be found");
            driver.quit();
        }

        int yUpperLeftLogo = logo.getLocation().getY();
        int heightLogo = logo.getSize().height;
        int yLowerLeftLogo = yUpperLeftLogo + heightLogo;

        int yUpperLeftCodeBox = codeBox.getLocation().getY();

        int distance = yUpperLeftCodeBox - yLowerLeftLogo;

        driver.quit();

        Assert.assertTrue(15 <= distance && distance <= 25);
    }

    @Test()
    public void DistanceBetweenFeaturesTooBig(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement topFeature = null;
        WebElement bottomFeature = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 375);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
        }catch (Exception e){
            System.out.println();
            System.out.println("Server did not respond");
            driver.quit();
        }

        try {
            topFeature = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[13]/div[2]/ul[1]/div[1]/div"));
//            topFeature = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[14]/div[2]/ul[1]/div[1]/div[1]"));
        }catch (Exception e){
            driver.quit();
        }

        int yTopFeatureUpperLeft = topFeature.getLocation().getY();
        String topFeatureFontSizeAsString = topFeature.getCssValue("font-size");
        String topFeatureFontSizeAsStringNumberOnly = topFeatureFontSizeAsString.substring(0,
                topFeatureFontSizeAsString.length()-2);
        int topFeatureFontSize = Integer.parseInt(topFeatureFontSizeAsStringNumberOnly);
        int yTopFeatureLowerLeft = topFeatureFontSize + FONT_BUFFER + yTopFeatureUpperLeft;

        try {
            bottomFeature = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[13]/div[2]/ul[1]/div[2]/div"));
//            bottomFeature = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[14]/div[2]/ul[1]/div[2]/div[1]"));
        }catch (Exception e){
            driver.quit();
        }

        int yBottomFeatureUpperLeft = bottomFeature.getLocation().getY();

        int distance = yBottomFeatureUpperLeft - yTopFeatureLowerLeft;

        driver.quit();

        System.out.println();
        System.out.println("Distance is :" + distance);

        Assert.assertTrue(12 <= distance && distance <= 22);
    }

    @Test()
    public void DistanceBetweenFeatureDescriptionAndFirstFeatureTooBig(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement featureDescription = null;
        WebElement firstFeature = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 375);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
        }catch (Exception e){
            System.out.println();
            System.out.println("Server did not respond");
            driver.quit();
        }

        try {
            featureDescription = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[13]/div[2]/p[1]"));
//            featureDescription = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[14]/div[2]/p[1]/b"));
        }catch (Exception e){
            driver.quit();
        }

        int yFeatureDescriptionUpperLeft = featureDescription.getLocation().getY();
        String featureDescriptionFontSizeAsString = featureDescription.getCssValue("font-size");
        String featureDescriptionFontSizeAsStringNumberOnly = featureDescriptionFontSizeAsString.substring(0, featureDescriptionFontSizeAsString.length()-2);
        int featureDescriptionFontSize = Integer.parseInt(featureDescriptionFontSizeAsStringNumberOnly);
        int featureDescriptionLowerLeft = featureDescriptionFontSize + FONT_BUFFER + yFeatureDescriptionUpperLeft;

        try {
            firstFeature = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[13]/div[2]/ul[1]/div[1]/div"));
//            firstFeature = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[14]/div[2]/ul[1]/div[1]/div[1]"));
        }catch (Exception e){
            driver.quit();
        }

        int yFirstFeatureUpperLeft = firstFeature.getLocation().getY();

        int distance = yFirstFeatureUpperLeft - featureDescriptionLowerLeft;

        driver.quit();

        System.out.println();
        System.out.println("Distance is :" + distance);

        Assert.assertTrue(11 <= distance && distance <= 21);
    }

    @Test()
    public void MoreSpaceNeededBetweenCompanyNameAndFeatureDescription(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement companyName = null;
        WebElement featureDescription = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 375);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
        }catch (Exception e){
            System.out.println();
            System.out.println("Server did not respond");
            driver.quit();
        }

        try {
            companyName = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[13]/div[2]/div"));
//            companyName = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[14]/div[2]/div"));
        }catch (Exception e){
            driver.quit();
        }

        int yCompanyNameUpperLeft = companyName.getLocation().getY();
        String companyNameFontSizeAsString = companyName.getCssValue("font-size");
        String companyNameFontSizeAsStringNumberOnly = companyNameFontSizeAsString.substring(0, companyNameFontSizeAsString.length()-2);
        int companyNameFontSize = Integer.parseInt(companyNameFontSizeAsStringNumberOnly);
        int yCompanyNameLowerLeft = companyNameFontSize + FONT_BUFFER + yCompanyNameUpperLeft;

        try {
            featureDescription = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[13]/div[2]/p[1]"));
//            featureDescription = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[4]/div[14]/div[2]/p[1]/b"));
        }catch (Exception e){
            driver.quit();
        }

        int yFeatureDescriptionUpperLeft = featureDescription.getLocation().getY();

        int distance = yFeatureDescriptionUpperLeft - yCompanyNameLowerLeft;

        driver.quit();

        System.out.println();
        System.out.println("Distance is :" + distance);

        Assert.assertTrue(19 <= distance && distance <= 26);
    }

    @Test()
    public void BoxAboveFooterShouldBeTallerAndStartALiveChatHigher1(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement box = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 1280);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
            driver.manage().window().maximize();
        }catch (Exception e){
            driver.quit();
        }

        try {
            box = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[6]/div"));
        }catch (Exception e){
            driver.quit();
        }

        int boxHeight = box.getSize().getHeight();

        driver.quit();

        System.out.println();
        System.out.println("Box height is :" + boxHeight);

        Assert.assertTrue(523 <= boxHeight && boxHeight <= 533);
    }

    @Test()
    public void BoxAboveFooterShouldBeTallerAndStartALiveChatHigher2(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement button = null;
        WebElement textUnderButton = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 1280);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
            driver.manage().window().maximize();
        }catch (Exception e){
            driver.quit();
        }

        try {
            button = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[6]/div/div[1]/div/div[2]/div/span[1]/a"));
        }catch (Exception e){
            driver.quit();
        }

        int yButtonUpperLeft = button.getLocation().getY();
        int buttonHeight = button.getSize().height;
        int yButtonLowerLeft = yButtonUpperLeft + buttonHeight;

        try {
            textUnderButton = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[6]/div/div[1]/div/div[2]/div/span[2]/p"));
        }catch (Exception e){
            driver.quit();
        }

        int yTextUnderButtonUpperLeft = textUnderButton.getLocation().getY();

        int distance = yTextUnderButtonUpperLeft - yButtonLowerLeft;

        driver.quit();

        System.out.println();
        System.out.println("Distance is :" + distance);

        Assert.assertTrue(119 <= distance && distance <= 129);
    }

    @Test
    public void DividerSpacingAboveMoreToExploreMissing1(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement divider = null;
        WebElement tileAbove = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 1280);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
            driver.manage().window().maximize();
        }catch (Exception e){
            driver.quit();
        }

        try {
            divider = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[5]"));
        }catch (Exception e){
            driver.quit();
        }

        int yDividerUpperLeft = divider.getLocation().getY();

        try {
            tileAbove = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[4]/div/div/a[1]"));
        }catch (Exception e){
            driver.quit();
        }

        int yTileAboveUpperLeft = tileAbove.getLocation().getY();
        int tileHeight = tileAbove.getSize().height;
        int yTileAboveLowerLeft = yTileAboveUpperLeft + tileHeight;

        int distance = yDividerUpperLeft - yTileAboveLowerLeft;

        driver.quit();

        System.out.println();
        System.out.println("Distance is :" + distance);

        Assert.assertTrue(123 <= distance && distance <= 133);
    }

    @Test
    public void DividerSpacingAboveMoreToExploreMissing2(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement divider = null;
        WebElement textBelow = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 1280);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
            driver.manage().window().maximize();
        }catch (Exception e){
            driver.quit();
        }

        try {
            divider = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[5]"));
        }catch (Exception e){
            driver.quit();
        }

        int yDividerUpperLeft = divider.getLocation().getY();

        try {
            textBelow = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[5]/div/h3"));
        }catch (Exception e){
            driver.quit();
        }

        int yTextBelowUpperLeft = textBelow.getLocation().getY();

        int dividerThickness = yDividerUpperLeft + DIVIDER_BORDER_THICKNESS;

        int distance = yTextBelowUpperLeft - dividerThickness;

        driver.quit();

        System.out.println();
        System.out.println("Distance is :" + distance);

        Assert.assertTrue(75 <= distance && distance <= 85);
    }

    @Test
    public void LineAboveComparingAblyShouldBeThicker(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement paragraph = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 1280);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
            driver.manage().window().maximize();
        }catch (Exception e){
            driver.quit();
        }

        try {
            paragraph = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[4]/div/h3"));
        }catch (Exception e){
            driver.quit();
        }

        String borderThicknessString = paragraph.getCssValue("border-top-width");

        driver.quit();

        System.out.println();
        System.out.println("Border thickness is :" + borderThicknessString);

        Assert.assertTrue(borderThicknessString.equals(BORDER_THICKNESS));
    }

    @Test
    public void LogosLowerHelloWorldSection1(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement textAboveLogo = null;
        WebElement logo = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 1280);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
            driver.manage().window().maximize();
        }catch (Exception e){
            driver.quit();
        }

        try {
            textAboveLogo = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[3]/h3"));
        }catch (Exception e){
            driver.quit();
        }

        int yTextAboveLogoUpper = textAboveLogo.getLocation().getY();
        int textAboveLogoHeight = textAboveLogo.getSize().height;
        int yTextAboveLogoLower = yTextAboveLogoUpper + textAboveLogoHeight;

        try {
            logo = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[3]/div[1]"));
        }catch (Exception e){
            driver.quit();
        }

        int yLogoUpperLeft = logo.getLocation().getY();

        driver.quit();

        int distance = yLogoUpperLeft - yTextAboveLogoLower;

        System.out.println();
        System.out.println("Distance is :" + distance);

        Assert.assertTrue(39 <= distance && distance <= 49);
    }

    @Test
    public void LogosLowerHelloWorldSection2(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement codeBoxBelow = null;
        WebElement logo = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 1280);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
            driver.manage().window().maximize();
        }catch (Exception e){
            driver.quit();
        }

        try {
            codeBoxBelow = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[3]/div[3]/div"));
        }catch (Exception e){
            driver.quit();
        }

        int yCodeBoxBelowUpperLeft = codeBoxBelow.getLocation().getY();

        try {
            logo = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[3]/div[1]"));
        }catch (Exception e){
            driver.quit();
        }

        int yLogoUpperLeft = logo.getLocation().getY();
        int logoHeight = logo.getSize().height;
        int yLogoLowerLeft = yLogoUpperLeft + logoHeight;

        driver.quit();

        int distance = yCodeBoxBelowUpperLeft - yLogoLowerLeft;

        System.out.println();
        System.out.println("Distance is :" + distance);

        Assert.assertTrue(39 <= distance && distance <= 49);
    }

    @Test
    public void TextInSDKsShouldNotBeBold(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement text = null;
        WebElement title = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 1280);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
            driver.manage().window().maximize();
        }catch (Exception e){
            driver.quit();
        }

        try {
            text = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[1]/div[6]/div[2]/ul/li[1]/p"));
        }catch (Exception e){
            driver.quit();
        }

        String textFontWeightString = text.getCssValue("font-weight");
        int textFontWeightInt = Integer.parseInt(textFontWeightString);

        try {
            title = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[1]/div[6]/div[2]/p[1]/span"));
        }catch (Exception e){
            driver.quit();
        }

        String titleFontWeightString = title.getCssValue("font-weight");
        int titleFontWeightInt = Integer.parseInt(titleFontWeightString);

        driver.quit();

        if (textFontWeightInt <= 300){
            Assert.assertTrue(textFontWeightInt <= 300);
        }else if(textFontWeightInt < titleFontWeightInt){
            Assert.assertTrue(textFontWeightInt < titleFontWeightInt);
        }else {
            Assert.assertTrue(false);
        }
    }

    @Test
    public void DifferentFontWeightForHeadings(){

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();
        WebElement title = null;

        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {
            {
                put("width", 1280);
                put("height", 1000);
                put("mobile", true);
                put("deviceScaleFactor", 50);
            }
        };

        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);

        try {
            driver.get(COMPARE_INNER);
            driver.manage().window().maximize();
        }catch (Exception e){
            driver.quit();
        }

        try {
            title = driver.findElement(By.xpath("//*[@id=\"gatsby-focus-wrapper\"]/main/section[2]/div[1]/div[6]/div[2]/p[1]/span"));
        }catch (Exception e){
            driver.quit();
        }

        String titleFontWeightString = title.getCssValue("font-weight");
        int titleFontWeightInt = Integer.parseInt(titleFontWeightString);

        driver.quit();

        System.out.println();
        System.out.println("Design font weight is :" + titleFontWeightInt);
        Assert.assertTrue(titleFontWeightInt == DESIGN_FONT_WEIGHT);
    }
}
