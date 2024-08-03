package pageObjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class FitPeoObjects {

	WebDriver driver;

	public FitPeoObjects(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[contains(text(),'Revenue Calculator')]")
	WebElement revenueCalculator;

	@FindBy(css = ".MuiSlider-root")
	WebElement slider;

	@FindBy(xpath = "//div[@class='MuiGrid-root MuiGrid-item MuiGrid-grid-xs-12 MuiGrid-grid-md-6 css-iol86l'][2]")
	WebElement sliderDiv;

	@FindBy(css = ".MuiSlider-thumb")
	WebElement sliderHandle;

	@FindBy(css = ".MuiInputBase-input")
	WebElement sliderTextBox;

	public void openUrl(String url) {
		driver.navigate().to(url);

	}

	public void openRevenueCalculaor() {
		revenueCalculator.click();
	}

	public void scrollToSlider() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		js.executeScript("arguments[0].scrollIntoView(true);", sliderDiv);
	}

	public void adjustTheSlider(double value) {
		Actions action = new Actions(driver);

		int width = slider.getSize().getWidth();

		double offsetX = (value * width) / 2000;
		int target = (int) (offsetX);
		action.clickAndHold(sliderHandle).moveByOffset(-30, 0).release().build().perform();
		action.clickAndHold(sliderHandle).moveByOffset(target, 0).release().build().perform();
	}

	@FindBy(id = ":r0:")
	WebElement sliderTB;

	public void enterValueToSlide(String value) {

		Actions action = new Actions(driver);
		sliderTB.click();
		action.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.BACK_SPACE).perform();
		sliderTB.sendKeys(value);

	}

	public void selectCheckBox(String text) {
		WebElement checkBox = driver
				.findElement(By.xpath("//*[contains(text(),'" + text + "')]/..//input[@type='checkbox']"));
		if (!(checkBox.isSelected())) {
			checkBox.click();
		}
	}
	
	public void validateTotalReimburshement(String expectedAmount) {
		String actualAmount;
		actualAmount = driver.findElement(By.xpath("//p[contains(text(),'Total Recurring Reimbursement for all Patients Per Month:')]/p")).getText();
		actualAmount = actualAmount.replace("$", "");
		Assert.assertEquals(actualAmount, expectedAmount);
		System.out.println(actualAmount);
		
		
}

}
