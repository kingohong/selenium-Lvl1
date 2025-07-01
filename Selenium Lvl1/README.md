
# 🚆 QA Railway Automation Testing Project

This project automates the testing of the QA Railway website using **Selenium WebDriver, TestNG, and ExtentReports**.

---

## 🧰 Technologies Used

- ✅ Java 17
- ✅ Selenium WebDriver
- ✅ TestNG
- ✅ ExtentReports
- ✅ Maven
- ✅ Jackson (for reading data from JSON)

---

## 📁 Project Structure

```
Selenium Lvl1/
│
├── src/
│   ├── main/
│   │   └── java/com/railway/pages/        # Page Object Models
│   └── test/
│       ├── java/com/tests/                # Test Cases
│       └── java/com/tests/utilities/      # Driver, Reports, DataProvider
│
├── screenshots/                           # Screenshots when tests fail
├── reports/                               # HTML reports from ExtentReports
├── pom.xml                                # Maven configuration
└── README.md                              # This documentation
```

---

## 📦 Setup & Run

### Step 1: Clone the repository
```bash
git clone https://github.com/kingohong/selenium-Lvl1.git
```

### Step 2: Install dependencies
```bash
mvn clean install
```

> 📝  Requirements: Chrome browser and JDK 17 must be installed.

---

## ▶️ How to Run Tests

Run all tests with the command:
```bash
mvn test
```

Or in IntelliJ: `Right-click` on a test class > Run `TestCaseXX`

---

## 📊 View Test Reports

Detailed reports after each run are saved at:
```
reports/ExtentReport_YYYYMMDD_HHmmss.html
```

> ✅ Includes screenshots for failed tests

---

## 🔎 Highlight Test Cases

| Test Case ID      |Description                                |
|------------|---------------------------------------|
| TC01       | 	Successful login                 |
| TC03       | Failed login with incorrect password  |
| TC14       | Successfully book a ticket once              |
| TC16       | 	Cancel a booked ticket                         |
| TC12       | Invalid reset password token    |

---

## 🗂️ Data-driven testing

Some test cases use data from a JSON file:
```
src/test/resources/data.json
```

You can edit the test data in this file.

---

## 🧑‍💻 Author

- **Author:** KyNgo
- **Github:** [kingohong](https://github.com/kingohong)

---

## 📝 Additional Notes

- Test website: http://saferailway.somee.com/
- Temporary email used: https://www.guerrillamail.com/
- Screenshots are automatically captured if a test fails
