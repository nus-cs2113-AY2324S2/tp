# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

The financial manager application is designed using an object-oriented approach, focusing on user authentication, transaction management, and user interaction. The core components include:

- **Main Application Loop**: Handles the initialization of the application, including loading data from storage, authenticating the user, and processing user commands until the application exits.
- **Command Pattern**: User commands are encapsulated as objects, allowing for the addition of new commands with minimal changes to existing code.
- **Transaction Management**: Separate classes for managing inflows and outflows, with a unified interface for adding, editing, and deleting transactions.
- **User Authentication**: Ensures that users are authenticated before accessing their financial data, with a mechanism to track and limit failed attempts.
- **Inactivity Timer**: Automatically logs out users after a period of inactivity, enhancing security.
- **Storage Management**: Handles the persistent storage of transaction data, allowing users to save and load their financial information.


## Product scope
### Target user profile

The financial manager application is designed for individual users seeking a simple yet powerful tool to manage personal finances, including tracking income and expenses, and viewing transaction history.


### Value proposition

This application simplifies personal financial management by providing an intuitive interface for tracking and analyzing income and expenses. Users can easily add, edit, or delete transactions, view recent transaction history, and ensure their data is securely managed with user authentication and automatic logout features.


plaintext
Copy code
## User Stories

|Version| As a/an ... | I want to ... | So that ...|
|-------|-------------|---------------|------------|
|v1.0|user|receive alerts or notifications when I exceed my budget limits for specific expense categories|I can stay within my financial goals|
|v1.0|user|generate monthly reports summarizing my income, expenses, and budget performance|I can track my financial progress over time|
|v1.0|user|edit or delete past transactions|I can correct any errors or update information as needed|
|v1.0|user|search for specific transactions based on keywords, dates, or categories|I can quickly find the information I need|
|v1.0|user|add income transactions with details such as amount, date, and category|I can keep track of my earnings|
|v1.0|user|add expense transactions with details such as amount, date, and category|I can monitor where my money is going|
|v1.0|user|categorize my transactions|I can organize my finances and have a clearer view of my income and expenses|
|v1.0|user|add instalment payments into the tracker|I can track finances for big ticket purchases such as furniture/TVs|
|v1.0|user|Use a customisable interface for my financial goals|I can adjust these financial goals accordingly as time passes|
|v1.0|user|add payment types such as credit card, debit card or cash|I can be reminded to pay credit card bills each month|
|v1.0|user|Be confident that my banking information is encrypted and safe from being accessed by others|The private information is not easily leaked to others|

## Non-Functional Requirements

1. **Security**: User authentication must be secure, with a limit on login attempts to prevent unauthorized access.
2. **Usability**: The application should be easy to use, with clear instructions and feedback for users.
3. **Performance**: The application should respond quickly to user inputs, with minimal delays in processing transactions.
4. **Reliability**: Data storage should be reliable, ensuring that user data is not lost between sessions.


## Glossary

1. **Inflow** - A financial transaction representing income or money received.
2. **Outflow** - A financial transaction representing expenses or money spent.


## Instructions for manual testing

### Login Procedure:
- Start the application and enter your username and password when prompted.
- Test incorrect passwords to ensure the application correctly limits login attempts.

### Adding Transactions:
- Use the `add-inflow` and `add-outflow` commands to add new transactions, following the command format provided in the `help` command.
- Attempt to add transactions with missing or incorrect information to test validation.

### Editing and Deleting Transactions:
- Use the `edit-inflow` and `edit-outflow` commands to modify existing transactions.
- Use the `delete-inflow` and `delete-outflow` commands to remove transactions.
- Try editing or deleting transactions that do not exist to test error handling.

### Viewing Transaction History:
- Use the `view-history` command to display recent transactions.
- Test with different numbers of transactions to view.

### Inactivity Timeout:
- After logging in, do not input any commands for the duration specified by the inactivity timer to test automatic logout.

### Data Persistence:
- Exit the application and restart it to ensure that previously entered transactions are still present.