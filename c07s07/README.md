Step 7: Navigate to Routes with Mandatory Parameters

In this step, we implement a feature that allows the user to click on an employee in the list to see additional details of the employee. A route pattern can have one or more mandatory parameters to identify objects in an app.

The detail page has to read the ID of the employee from the URL to fetch and display the employee data from the server. If the employee was not found, for example, because an invalid employee ID was passed on, we want to inform the user by displaying the notFound target. Of course, the back navigation has to work as well for this page.
