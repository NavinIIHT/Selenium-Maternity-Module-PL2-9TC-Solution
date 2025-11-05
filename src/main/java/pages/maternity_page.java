package pages;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class maternity_page extends StartupPage {

//	TC-1 Locators
	By getUsernameTextfieldLocator = By.id("username_id");
	By getPasswordTextboxLocator = By.xpath("//input[@id='password']");
	By getSignInButtonLocator = By.xpath("//button[@id='login']");
	By getMaternityLocator = By.xpath("//a[@href='#/Maternity']");
//	TC-2 and 3 Locators
	By getAnchorTagLocatorForMaternityList =  By.xpath("//a[contains(text(),'" + "Maternity List" + "')]");
	By getAnchorTagLocatorForPayments = By.xpath("//a[contains(text(),'" + "Payments" + "')]");
	By getAnchorTagLocatorForReports = By.xpath("//a[contains(text(),'" + "Reports" + "')]");

//	TC-4 Locators
	By getButtonLocatorForRegister = By.xpath("//button[contains(text(),'" + "Register" + "')]");
	By getButtonLocatorForLast = By.xpath("//button[contains(text(),'" + "Last" + "')]");
	By searchBarId = By.id("quickFilterInput");
	By editInformationOfField = By.id("maternityPats");
	By getDateRangeButton = By.cssSelector("td [data-hover='dropdown']");
	By getAllMaternityPatientCheckbox = By.xpath("//label[contains(text(),'View all Maternity Patient')]/span");
	By calendarFromDropdown =  By.xpath("(//input[@id='date'])[1]");
	By calendarToDropdown  = By.xpath("(//input[@id='date'])[2]");
	By getStarIconLocator =  By.xpath("//i[contains(@class,'icon-favourite')]/..");
//	TC-5 Locators
	By getEditHusbandField = By.xpath("//input[@id='patHusbandName']");
	By getRowsOfResult = By.xpath("//div[not(contains(@class,'hidden'))]/div[@row-id]");
	public By getHusbandNameByRowIndex(String index) {
	    return By.xpath("(//div[@role='gridcell' and @col-id='HusbandName'])[" + index + "]");
	}
//	TC-6 Locators
	By getButtonLocatorForOk = By.xpath("//button[contains(text(),'" + "OK" + "')]");
	By getActualEddDates =  By.xpath("//div[@role='gridcell' and @col-id='ExpectedDeliveryDate']");
//	TC-7 Locators	
	public By getAnchorTagLocatorLast1Week = By.xpath("//a[contains(text(),'" + "Last 1 Week" + "')]");
	
//	TC-8 Locators	
	public By getTotalRecordCount = By.xpath("//span[@ref='lbRecordCount']");	
//	TC-9 Locators
//	Write it here if required
	public By favouriteOrStarIconMedicalRecord() {
		return By.xpath("//i[@title='Remember this Date']");
	}
	

	public maternity_page(WebDriver driver) {
		super(driver);
	} 
	
    /**
     * @Test1.1 - Automates login to the Health App using valid credentials.
     *
     * @param expectedData A map containing the login credentials:
     *                     - Key: "username" → Value: valid username
     *                     - Key: "password" → Value: valid password
     *
     * @description This method automates the login process in the Health App using the following steps:
     *              1. Locate the username input field on the login screen.
     *              2. Highlight the username field and enter the provided username.
     *              3. Locate the password input field.
     *              4. Highlight the password field and enter the provided password.
     *              5. Locate the sign-in button on the screen.
     *              6. Highlight the sign-in button and click it to initiate login.
     *              7. If all actions are performed without exceptions, the method returns true.
     *              8. If any step fails, an exception is thrown and false is returned.
     *
     * @return boolean Returns true if all login steps execute successfully; otherwise throws an exception.
     *
     * @throws Exception if any of the UI interactions (element finding, sending keys, or clicking) fail.
     *
     * @author Yaksha
     */
	public boolean loginToHealthAppByGivenValidCredetial(Map<String, String> expectedData) throws Exception {
		Boolean textIsDisplayed = false;
		try {
			WebElement usernametextFieldWebElement = commonEvents.findElement(getUsernameTextfieldLocator);
			commonEvents.highlightElement(usernametextFieldWebElement);
			commonEvents.sendKeys(getUsernameTextfieldLocator, expectedData.get("username"));

			WebElement passwordtextFieldWebElement = commonEvents.findElement(getPasswordTextboxLocator);
			commonEvents.highlightElement(passwordtextFieldWebElement);
			commonEvents.sendKeys(getPasswordTextboxLocator, expectedData.get("password"));

			WebElement signinButtonWebElement = commonEvents.findElement(getPasswordTextboxLocator);
			commonEvents.highlightElement(signinButtonWebElement);
			commonEvents.click(getSignInButtonLocator);
			textIsDisplayed = true;
		} catch (Exception e) {
			throw e;
		}
		return textIsDisplayed;
	}
	  /**
     * @Test1.2 - Navigates to the Maternity tab by scrolling and clicking on it.
     *
     * @param None - This method does not require any input parameters.
     *
     * @description This method performs the following steps to ensure successful navigation to the Maternity tab:
     *              1. Locate the Maternity tab element on the page using the defined locator.
     *              2. Scroll the page to bring the Maternity tab into view.
     *              4. Highlight and click the Maternity tab element to visually confirm interaction.
     *              6. Wait until the URL contains the substring "Maternity/PatientList" to confirm successful navigation.
     *              7. If all steps are successful, return true.
     *              8. If any step fails, an exception is thrown and false is returned.
     *
     * @return boolean - Returns true if scrolling to and clicking on the Maternity tab is successful; otherwise throws an exception.
     *
     * @throws Exception - Propagates any exceptions that occur during element interaction, scrolling, clicking, or URL verification.
     *
     * @author YAKSHA
     */
	
	public boolean scrollDownAndClickMaternityTab() throws Exception {
		boolean scrolledTillElemet = false;
		try {
			JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
			WebElement radiologyTab = commonEvents.findElement(getMaternityLocator);
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", radiologyTab);
			jsExecutor.executeScript("window.scrollBy(0, -50)");
			commonEvents.highlight(radiologyTab);
			commonEvents.click(radiologyTab);

			// Wait for the URL to contain "Maternity/PatientList"
			commonEvents.waitForUrlContains("Maternity/PatientList", 10);

			scrolledTillElemet = true;
		} catch (Exception e) {
			throw e;
		}
		return scrolledTillElemet;
	}
	
	/**
     * @Test1.3 - Retrieves and returns the current URL to verify Maternity page navigation.
     *
     * @param None - This method does not accept any parameters.
     *
     * @description This method performs the following actions to assist in verifying that the user
     *              has successfully navigated to the Maternity page:
     *              1. Fetches the current URL of the web page.
     *              2. Returns the URL as a string, which can be validated against the expected Maternity page URL.
     *              3. If any exception occurs during URL retrieval, it is propagated for external handling.
     *
     * @return String - Returns the current URL of the web page.
     *
     * @throws Exception - Throws any exception encountered while attempting to retrieve the current URL.
     *
     * @author YAKSHA
     */
	
	public String verifyMaternityPageUrl() throws Exception {
		try {
			String titleToVerify = commonEvents.getCurrentUrl();
			return titleToVerify;
		} catch (Exception e) {
			throw e;
		}
	}
	
	/**
     * @Test2 - Expands the Maternity dropdown and verifies the visibility of its sub-modules.
     *
     * @param None - This method does not accept any parameters.
     *
     * @description This method performs a UI validation of the Maternity section by executing the following steps:
     *              1. Clicks on the Maternity dropdown arrow to expand the sub-module list.
     *              2. Locates the individual sub-modules: "Maternity List", "Payments", and "Reports".
     *              3. Highlights each sub-module for visibility confirmation during test execution.
     *              4. Verifies that each sub-module is displayed by checking their visibility status.
     *              5. If all sub-modules are visible, the method returns true.
     *              6. If any sub-module is not visible, an exception is thrown with details of the failure.
     *
     * @return boolean - Returns true if all sub-modules ("Maternity List", "Payments", and "Reports") are visible; 
     *                   otherwise throws an exception indicating which one failed.
     *
     * @throws Exception - Throws a detailed exception if:
     *                     - Clicking the dropdown arrow fails.
     *                     - Any of the sub-modules cannot be found or are not visible.
     *
     * @author YAKSHA
     */
	
	
	public boolean clickMaternityArrowAndVerifySubModules() throws Exception {
		boolean subModulesVisible = false;
		try {

			// Click the maternity dropdown arrow to expand the sub-modules
			commonEvents.click(getMaternityLocator);

			// Locate the sub-modules under the maternity section
			WebElement maternityList = commonEvents.findElement(getAnchorTagLocatorForMaternityList);
			WebElement payments = commonEvents.findElement(getAnchorTagLocatorForPayments);
			WebElement reports = commonEvents.findElement(getAnchorTagLocatorForReports);

			// Store the located sub-modules in a list for easy iteration
			List<WebElement> options = Arrays.asList(maternityList, payments, reports);

			// Verify that each sub-module is visible by checking its height
			for (int i = 0; i < options.size(); i++) {
				WebElement option = options.get(i);
				commonEvents.highlight(option);
				System.out.println("Is " + option.getText() + " visible : " + option.isDisplayed());
				if (!option.isDisplayed()) {
					subModulesVisible = false;
					throw new Exception("Sub-module visibility check failed for: " + option.getText());
				}
			}

			// Return true if all sub-modules are visible, otherwise false
			subModulesVisible = true;
		} catch (Exception e) {
			// Throw a descriptive error with additional context
			throw new Exception("Failed to verify maternity sub-modules visibility. Reason: " + e.getMessage(), e);
		}

		return subModulesVisible;
	}

    /**
     * @Test3 - Verifies navigation across sub-tabs within the Maternity module.
     *
     * @param None - This method does not take any input parameters.
     *
     * @description This method ensures that the user can successfully navigate between all sub-modules
     *              of the Maternity section by performing the following steps:
     *              1. Click on the "Payments" sub-tab and verify that the current URL contains 
     *                 "Maternity/Payments/PaymentPatientList".
     *              2. Click on the "Reports" sub-tab and verify that the current URL contains 
     *                 "Maternity/Reports".
     *              3. Click on the "Maternity List" sub-tab and verify that the current URL contains 
     *                 "Maternity/PatientList".
     *              4. If all URL verifications are successful after navigation, return true.
     *              5. If any navigation or verification fails, throw an exception with relevant details.
     *
     * @return boolean - Returns true if navigation to all three sub-tabs is successful and each 
     *                   expected URL is correctly loaded; otherwise throws an exception.
     *
     * @throws Exception - Throws a detailed exception if:
     *                     - Clicking any of the sub-tabs fails.
     *                     - The expected URL fragment is not found within the defined wait time.
     *
     * @author YAKSHA
     */
	public boolean verifyNavigationBetweenMaternitySubModules() throws Exception {
		try {
			// Navigate to the "Payments" sub-tab and verify the URL
			commonEvents.click(getAnchorTagLocatorForPayments);
			commonEvents.waitForUrlContains("Maternity/Payments/PaymentPatientList", 5);

			// Navigate to the "Reports" sub-tab and verify the URL
			commonEvents.click(getAnchorTagLocatorForReports);
			commonEvents.waitForUrlContains("Maternity/Reports", 5);

			// Navigate to the "Maternity List" sub-tab and verify the URL
			commonEvents.click(getAnchorTagLocatorForMaternityList);
			commonEvents.waitForUrlContains("Maternity/PatientList", 5);

			// If all navigations are successful, return true
			return true;
		} catch (Exception e) {
			// Throw a descriptive error with additional context
			throw new Exception("Navigation between maternity sub-modules failed. Reason: " + e.getMessage(), e);
		}
	}
	
    /**
     * @Test4 - Verifies the visibility of all key UI components in the "Maternity List" sub-module.
     *
     * @param None - This method does not take any parameters.
     *
     * @description This method ensures the proper rendering of critical components on the "Maternity List" screen
     *              by executing the following steps:
     *              1. Locate all essential UI elements such as:
     *                 - Search Bar
     *                 - Edit Information Field
     *                 - Date Range Button
     *                 - "Select All" Patient Checkbox
     *                 - Calendar From Dropdown
     *                 - Calendar To Dropdown
     *                 - Star Icon
     *              2. Highlight each UI component for visibility tracking during test execution.
     *              3. Check if each element is visible on the screen using `.isDisplayed()`.
     *              4. If any element is not visible, throw an exception with the specific element’s text for debugging.
     *              5. If all elements are successfully verified, return true.
     *
     * @return boolean - Returns true if all specified UI components are visible; otherwise, an exception is thrown.
     *
     * @throws Exception - Throws a detailed exception if:
     *                     - Any UI component cannot be located.
     *                     - Any component is not visible on the page.
     *
     * @author YAKSHA
     */
	public boolean verifyMaternityComponentsAreVisible() throws Exception {

		boolean areAllFieldsDisplayed = false;
		try {
			// Navigate to the "Maternity List" sub-tab and verify the URL

			
			WebElement searchBarId1 = commonEvents.findElement(searchBarId);
			WebElement editInformationOfField1 = commonEvents.findElement(editInformationOfField);
			WebElement getDateRangeButton1 = commonEvents.findElement(getDateRangeButton);
			WebElement allMaternityPatientCheckbox = commonEvents.findElement(getAllMaternityPatientCheckbox);
			WebElement calendarFromDropdown1 = commonEvents.findElement(calendarFromDropdown);
			WebElement calendarToDropdown1 = commonEvents.findElement(calendarToDropdown);
			WebElement starIconLocator = commonEvents.findElement(getStarIconLocator);

			List<WebElement> options = Arrays.asList(searchBarId1, editInformationOfField1, getDateRangeButton1, allMaternityPatientCheckbox,
					calendarFromDropdown1, calendarToDropdown1, starIconLocator);

			for (int i = 0; i < options.size(); i++) {
				WebElement option = options.get(i);
				commonEvents.highlight(option);
				if (!option.isDisplayed()) {
					areAllFieldsDisplayed = false;
					throw new Exception("Visibility check failed for: " + option.getText());
				}
			}
			areAllFieldsDisplayed = true;
		} catch (Exception e) {
			// Throw an exception with a meaningful message if any UI component is not found
			throw new Exception("Failed to verify if all fields are displayed!", e);
		}
		// Return the result of the visibility check
		return areAllFieldsDisplayed;
	}
	
    /**
     * @Test5 - Edits a patient’s first name and husband's name, and verifies the update.
     *
     * @param None - This method does not accept any parameters.
     *
     * @description This method performs the following actions to verify that patient details
     *              are editable and the changes are reflected correctly:
     *              1. Generates a random husband name by appending a 4-digit random number.
     *              2. Locates and highlights the patient’s first name input field and updates it.
     *              3. Tabs to the next field to ensure input registration.
     *              4. Locates and updates the husband's name field with the newly generated value.
     *              5. Submits the form by clicking the "Register" button.
     *              6. Navigates away to the "Payments" tab and then returns to the "Maternity List" to refresh the view.
     *              7. If pagination is available, navigates to the last page to locate the most recent entry.
     *              8. Retrieves the updated husband's name from the last visible row.
     *              9. Compares the actual displayed name with the expected updated name.
     *              10. Returns true if the verification is successful; otherwise, throws an exception.
     *
     * @return boolean - Returns true if the husband's name is updated and verified successfully; 
     *                   otherwise throws an exception with details.
     *
     * @throws Exception - If there is a failure in locating fields, entering data, clicking elements, 
     *                     or validating the updated details, an exception is thrown with a clear message.
     *
     * @author YAKSHA
     */
	public boolean editPatientInformationAndVerify() throws Exception {
		String patientFirstName = "Akshit";
		Random random = new Random();
		long randomNumber = 1000L + (long) (random.nextDouble() * 9000L);
		String updatedHusbandName = "Husband" + randomNumber;

		try {
			WebElement editInformationField = commonEvents.findElement(editInformationOfField);
			commonEvents.highlight(editInformationField).sendKeys(editInformationField, patientFirstName);
			Thread.sleep(3000);
			commonEvents.sendKeys(editInformationField, Keys.TAB);

			WebElement editHusbandNameField = commonEvents.findElement(getEditHusbandField);
			commonEvents.highlight(editHusbandNameField).sendKeys(editHusbandNameField, updatedHusbandName);

			commonEvents.click(getButtonLocatorForRegister);

			commonEvents.click(getAnchorTagLocatorForPayments);
			commonEvents.click(getAnchorTagLocatorForMaternityList);

			List<WebElement> rowsElements = commonEvents.getWebElements(getRowsOfResult);
			System.out.println("Number of elements : " + rowsElements.size());

			// Navigate to the last page if the "Last" button is enabled
			WebElement lastButton = commonEvents.findElement(getButtonLocatorForLast);
			if (lastButton.isEnabled()) {
				commonEvents.highlight(lastButton).click(lastButton);
			}

			// Verify the updated husband's name
			
			String totalNumberOfVisibleRows = String.valueOf(rowsElements.size());
			WebElement actualHusbandNameElement = commonEvents
					.findElement(getHusbandNameByRowIndex(totalNumberOfVisibleRows));
			String actualHusbandName = commonEvents.getText(actualHusbandNameElement);
			System.out.println("Actual Husband Name : " + actualHusbandName);
			System.out.println("Updated Husband Name : " + actualHusbandName);

			if (actualHusbandName.trim().contains(updatedHusbandName.trim())) {
				return true;
			} else {
				throw new Exception("Verification failed: Updated husband's name does not match the expected value.");
			}
		} catch (Exception e) {
			throw new Exception("Failed to edit and verify patient information due to: " + e.getMessage(), e);
		}
	}

    /**
     * @Test6.1 - Verifies whether the current URL contains a specified text fragment.
     *
     * @param urlTextToVerify - A string representing the partial text expected to be found in the current URL.
     *
     * @description This method performs the following steps:
     *              1. Retrieves the current page URL.
     *              2. Waits until the URL contains the specified text fragment.
     *              3. Returns true if the text is found in the URL.
     *              4. If the expected text is not found or an error occurs during the check, an exception is thrown.
     *
     * @return boolean - Returns true if the URL contains the specified text; otherwise throws an exception.
     *
     * @throws Exception - If there is a failure retrieving the URL or the expected text is not present in it.
     *
     * @author YAKSHA
     */
	public boolean verifyUrlContains(String urlTextToVerify) throws Exception {
		try {
			commonEvents.waitForUrlContains(urlTextToVerify, 0);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
    /**
     * @Test6.2 - Applies a date filter by setting the 'From' and 'To' date ranges.
     *
     * @param fromDate - A string representing the start date in the format "dd-MM-yyyy".
     * @param toDate   - A string representing the end date in the format "dd-MM-yyyy".
     *
     * @description This method performs the following steps to apply a date filter:
     *              1. Parses the provided `fromDate` and `toDate` strings into day, month, and year components.
     *              2. Locates the "From Date" and "To Date" input fields on the UI.
     *              3. Highlights and inputs each parsed component into the respective date fields.
     *              4. Clicks the "OK" button to apply the selected date range.
     *              5. Returns true if all actions complete successfully.
     *              6. Throws an exception if any part of the interaction fails (e.g., locating fields or sending input).
     *
     * @return boolean - Returns true if the date range is applied successfully; otherwise throws an exception.
     *
     * @throws Exception - If there is an issue locating the date fields, parsing the date, or interacting with the UI.
     *
     * @author YAKSHA
     */

	public boolean applyDateFilter(String fromDate, String toDate) throws Exception {
		try {
			String fromDay, fromMonth, fromYear, toDay, toMonth, toYear;
			fromDay = fromDate.split("-")[0];
			fromMonth = fromDate.split("-")[1];
			fromYear = fromDate.split("-")[2];
			toDay = toDate.split("-")[0];
			toMonth = toDate.split("-")[1];
			toYear = toDate.split("-")[2];
			WebElement fromDateDropdown = commonEvents.findElement(calendarFromDropdown);
			WebElement toDateDropdown = commonEvents.findElement(calendarToDropdown);
			commonEvents.highlight(fromDateDropdown).sendKeys(fromDateDropdown, fromDay)
					.sendKeys(fromDateDropdown, fromMonth).sendKeys(fromDateDropdown, fromYear);
			commonEvents.highlight(toDateDropdown).sendKeys(toDateDropdown, toDay).sendKeys(toDateDropdown, toMonth)
					.sendKeys(toDateDropdown, toYear);
			commonEvents.click(getButtonLocatorForOk);
		} catch (Exception e) {
			throw e;
		}
		return true;
	}
	
  /**
     * @Test7.1 - Selects a predefined value from the date range dropdown.
     *
     * @param valueToSelect - A string representing the visible text of the option to be selected from the dropdown.
     *
     * @description This method simulates the user behavior of selecting a date range filter by:
     *              1. Locating and clicking the date range dropdown button.
     *              2. Identifying the specific value to be selected (e.g., "Last 1 Week") from the dropdown options.
     *              3. Highlighting and clicking the desired value.
     *              4. Returning true if the value is successfully selected.
     *              5. If the selection fails or the element is not found, the method throws a detailed exception.
     *
     * @return boolean - Returns true if the dropdown value is selected successfully; otherwise throws an exception.
     *
     * @throws Exception - If the dropdown or selection element is not found, or interaction with them fails.
     *
     * @author YAKSHA
     */
	public boolean clickDateRangeDropdownAndSelect(String valueToSelect) throws Exception {
		try {
			// Click the date range button
	WebElement dateRangeButton = commonEvents.findElement(getDateRangeButton);
	commonEvents.highlight(dateRangeButton).click(dateRangeButton);
	System.out.println("Clicked on the date range dropdown.");
	
	// Find and select the desired value from the dropdown
	WebElement valueToSelectElement = commonEvents.findElement(getAnchorTagLocatorLast1Week);
	boolean isValueSelected = commonEvents.highlight(valueToSelectElement).click(valueToSelectElement) != null;
	System.out.println("Selected value from dropdown: " + valueToSelect);
		Thread.sleep(3000); 
		return isValueSelected;
	
	} catch (Exception e) {
		System.out.println("Exception occurred while selecting date range: " + e.getMessage());
	throw new Exception("Failed to select the value from date range dropdown due to: " + e.getMessage(), e);
		}
	}
	
    /**
     * @Test6.3 / @Test7.2 - Verifies that all appointment dates in the result list fall within the specified date range.
     *
     * @param fromDate - A string representing the start date in the format "dd-MM-yyyy".
     * @param toDate   - A string representing the end date in the format "dd-MM-yyyy".
     *
     * @description This method ensures that each appointment date displayed in the result set falls 
     *              within the user-specified range. It performs the following steps:
     *              1. Parses the `fromDate` and `toDate` strings into `LocalDate` objects.
     *              2. Waits briefly to allow the filtered result list to load (via `Thread.sleep`).
     *              3. Retrieves all date elements from the appointment results.
     *              4. For each date:
     *                  a. Highlights the element for visibility in test reporting.
     *                  b. Parses the date text from "yyyy-MM-dd" format into a `LocalDate`.
     *                  c. Checks if the parsed date falls within the `fromDate` and `toDate` range (inclusive).
     *              5. If any date is outside the range or parsing fails, logs an error and returns false.
     *              6. Returns true only if all dates are within the specified range.
     *
     * @return boolean - Returns true if all appointment dates fall within the specified range; false otherwise.
     *
     * @throws Exception - If there's a failure in locating elements, parsing dates, or validating range compliance.
     *
     * @author YAKSHA
     */

	public boolean verifyResultsAppointmentDateFallsWithin(String fromDate, String toDate) throws Exception {
		try {
			// Formatters for date conversion
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			Thread.sleep(3000); // To let the results gets refreshed

			// Parse the from and to dates
			LocalDate from = LocalDate.parse(fromDate, formatter);
			LocalDate to = LocalDate.parse(toDate, formatter);

			// Retrieve the list of dates from the results
			List<WebElement> actualDatesAfterFilterApplied = commonEvents.getWebElements(getActualEddDates);

			for (WebElement dateElement : actualDatesAfterFilterApplied) {
				commonEvents.highlight(dateElement);
				String dateText = dateElement.getText();

				try {
					// Convert the date string to LocalDate
					LocalDate date = LocalDate.parse(dateText, inputFormatter);

					// Check if the date is within the specified range
					if (date.isBefore(from) || date.isAfter(to)) {
						System.out.println("Date " + dateText + " is outside the range " + fromDate + " to " + toDate);
						return false;
					}
				} catch (Exception e) {
					System.out.println("Date parsing failed for: " + dateText);
					return false;
				}
			}
			return true;

		} catch (Exception e) {
			throw new Exception("Failed to verify dates within the specified range due to: " + e.getMessage(), e);
		}
	}
	
    /**
     * @Test8 - Verifies the functionality of the "View All Maternity Patient" checkbox.
     *
     * @param None - This method does not accept any parameters.
     *
     * @description This method checks whether enabling the "View All Maternity Patient" checkbox
     *              increases the visible patient record count. It performs the following steps:
     *              1. Retrieves and stores the initial patient record count from the UI.
     *              2. Locates and clicks the "View All Maternity Patient" checkbox.
     *              3. Waits briefly to allow the page to refresh and update the record count.
     *              4. Retrieves the updated patient record count.
     *              5. Compares the initial and updated counts.
     *              6. Returns true if the updated count is greater than the initial count,
     *                 indicating that the checkbox functionality is working as expected.
     *              7. Returns false if the count does not increase.
     *
     * @return boolean - Returns true if the patient record count increases after clicking the checkbox;
     *                   false if it remains the same or decreases.
     *
     * @throws Exception - If there's an issue locating the checkbox or count element, or parsing the count values.
     *
     * @note Consider replacing `Thread.sleep` with an explicit wait to ensure more reliable test execution.
     *
     * @author YAKSHA
     */

	public boolean verifyViewAllMaternityPatientCheckBoxFunctionality() throws Exception {
	    try {
	        WebElement viewAllMaternityPatientCheckbox = commonEvents.findElement(getAllMaternityPatientCheckbox);
	        WebElement totalRecordCountElement = commonEvents.findElement(getTotalRecordCount);

	        int initialCount = Integer.parseInt(totalRecordCountElement.getText());
	        System.out.println("Initial patient record count: " + initialCount);

	        commonEvents.highlight(viewAllMaternityPatientCheckbox).click(viewAllMaternityPatientCheckbox);
	        System.out.println("Clicked on 'View All Maternity Patient' checkbox.");

	        Thread.sleep(3000); // Consider replacing with explicit wait in real-world code

	        int updatedCount = Integer.parseInt(totalRecordCountElement.getText());
	        System.out.println("Updated patient record count after checkbox click: " + updatedCount);

	        if (updatedCount > initialCount) {
	            System.out.println("Record count increased. Checkbox functionality verified.");
	            return true;
	        } else {
	            System.out.println("Record count did not increase. Checkbox functionality might be broken.");
	            return false;
	        }
	    } catch (Exception e) {
	        System.out.println("Exception occurred while verifying checkbox functionality: " + e.getMessage());
	        throw e;
	    }
	}


    /**
     * @Test9 - Searches for a keyword and verifies that every result row contains it.
     *
     * @param keywordToVerify - A string representing the keyword to search for in the result set.
     *
     * @description This method performs the following steps to validate search functionality:
     *              1. Locates and highlights the search bar on the UI.
     *              2. Enters the provided keyword into the search bar.
     *              3. Waits briefly for the filtered results to load.
     *              4. Retrieves all rows from the result section.
     *              5. For each row, highlights it and verifies that the row’s text contains the search keyword.
     *              6. Returns true if the keyword is found in **every** row.
     *              7. Returns false immediately if any row does not contain the keyword.
     *
     * @return boolean - Returns true if the keyword is present in all result rows; false if at least one row does not contain the keyword.
     *
     * @throws Exception - If an issue occurs while interacting with the search bar or reading row data (e.g., elements not found, timeout, etc.).
     *
     * @note The method currently uses a fixed `Thread.sleep`. For improved stability, replace it with an explicit wait for result visibility.
     *
     * @author YAKSHA
     */

	public boolean searchAndVerifyKeywordInEveryResult(String keywordToVerify) throws Exception {
		try {
			boolean keywordFoundInAllRows = true;

			// Find and highlight the search bar, then enter the keyword
			WebElement searchBar = commonEvents.findElement(searchBarId);
			commonEvents.highlightElement(searchBar);
			commonEvents.sendKeys(searchBar, keywordToVerify);

			// Wait for the results to load, if necessary
			Thread.sleep(2000);

			// Get all rows of the search result
			List<WebElement> rows = commonEvents.getWebElements(getRowsOfResult);

			// Verify the keyword is present in each row
			for (WebElement row : rows) {
				
				commonEvents.highlight(row);
				String rowText = row.getText();
				
				if (!rowText.contains(keywordToVerify)) {
					keywordFoundInAllRows = false;
					break; // Exit the loop early if the keyword is not found in any row
				}
			}
			
			
			return keywordFoundInAllRows;
		} catch (Exception e) {
			throw new Exception("Failed to verify keyword in all rows due to: " + e.getMessage(), e);
		}
	}

}
