package pageTests;

import pageObjects.FitPeoObjects;

public class FitPeoTest extends BaseTest {

	static FitPeoObjects fp;

	public static void main(String[] args) {

		openBrowser("chrome");
		fp = new FitPeoObjects(driver);
		fp.openUrl("https://www.fitpeo.com/");
		fp.openRevenueCalculaor();
		fp.scrollToSlider();
		fp.adjustTheSlider(820);
		fp.enterValueToSlide("560");
		fp.selectCheckBox("CPT-99091");
		fp.selectCheckBox("CPT-99453");
		fp.selectCheckBox("CPT-99454");
		fp.selectCheckBox("CPT-99474");
		fp.validateTotalReimburshement("75600");
		
		
		tearDown();
		

	}

}
