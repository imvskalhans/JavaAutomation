# JavaAutomation
# 🚀 SwagLabs Automation Test Suite

This is an automated test project written in **Java** using **Selenium WebDriver** and **TestNG**. It simulates end-to-end user interactions on the [SwagLabs Demo Website](https://www.saucedemo.com/v1/), including logging in, sorting products, adding items to the cart, and completing checkout with form data loaded from a **JSON file**.

---

## 📁 Project Structure

SwagLabsTestSuite/

├── src/

│ ├── main/

│ │ └── java/

│ │ └── com/

│ │ └── demo/

│ │ └── tests/

│ │ └── SwagLabsTest.java # Main test class

│ └── test/

│ └── resources/

│ └── checkoutInfo.json # Test data for checkout

├── testng.xml (optional)

├── README.md

---

## ✅ Features

- ✅ Browser setup using **WebDriverManager**
- ✅ Automated login using valid credentials
- ✅ Product **sorting** by price (low to high)
- ✅ Add multiple items to cart dynamically
- ✅ View cart and perform checkout
- ✅ Fill checkout form using external **JSON**
- ✅ Organized using **TestNG annotations**

---

## 🧰 Tools & Technologies

| Tool              | Purpose                              |
|-------------------|---------------------------------------|
| Java              | Programming Language                  |
| Selenium WebDriver| Browser automation                    |
| TestNG            | Testing framework                     |
| org.json          | JSON parsing and manipulation         |
| WebDriverManager  | Automatic browser driver management   |
| Microsoft Edge    | Target browser                        |

---

## 🧪 Prerequisites

Before running the tests, ensure the following are installed:

- Java 17 or higher
- Maven (optional, for dependency management)
- Microsoft Edge browser
- IDE like IntelliJ IDEA or Eclipse

---

## 📄 How to Run the Tests
```bash
1. Clone the repository:

     git clone https://github.com/your-username/SwagLabsTestSuite.git
     cd SwagLabsTestSuite
   
  Add JSON file at:
  
    src/test/resources/checkoutInfo.json

  Sample content:
 
    {
      "firstName": "John",
      "lastName": "Doe",
      "postalCode": "12345"
    }

  Run the test:
    
    From IDE: Right-click on SwagLabsTest.java and choose Run.

  If using Maven:
   
    mvn test
