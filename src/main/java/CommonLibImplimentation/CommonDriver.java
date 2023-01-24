package CommonLibImplimentation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class CommonDriver {
	private WebDriver driver;
	private int pageloadTimeout;
	private int elementDetectTimeout;

	public CommonDriver(String browserType) throws Exception {
		pageloadTimeout = 60;
		elementDetectTimeout = 10;
		if (browserType.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browserType.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new Exception("Invalid Browser Type");
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		

	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public int getPageloadTimeout() {
		return pageloadTimeout;
	}

	public void setPageloadTimeout(int pageloadTimeout) {
		this.pageloadTimeout = pageloadTimeout;
	}

	public int getElementDetectTimeout() {
		return elementDetectTimeout;
	}

	public void setElementDetectTimeout(int elementDetectTimeout) {
		this.elementDetectTimeout = elementDetectTimeout;
	}

	public void navigateToFirstUrl(String url) {
		driver.manage().timeouts().pageLoadTimeout(pageloadTimeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(elementDetectTimeout, TimeUnit.SECONDS);
		url = url.trim();
		driver.get(url);
	}

	public String getTitle() {
		String title = driver.getTitle();
		return title;
	}

	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getPageSource() {
		return driver.getPageSource();
	}

	public void navigateToUrl(String url) {
		url = url.trim();
		driver.navigate().to(url);
	}

	public void navigateForward() {
		driver.navigate().forward();
	}

	public void navigateBackward() {
		driver.navigate().back();
	}

	public void refresh() {
		driver.navigate().refresh();
	}

	public void closeBrowser() {
		if (driver != null) {
			driver.close();
		}
	}

	public void closeAllBrowser() {
		if (driver != null) {
			driver.quit();
		}
	}

}
