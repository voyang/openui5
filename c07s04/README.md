Step 4: Add a Back Button to Not Found Page

When we are on the Not Found page because of an invalid hash, we want to get back to our app to select another page. Therefore, we will add a Back button to the Not Found view and make sure that the user gets redirected to either the previous page or the overview page when the Back button is pressed.

Note
In this step we have added the Back button. The user can always use the browser’s native Back button as well. Each app can freely configure the behavior of the Back button. However, there is no clean way to apply the same logic for the browser’s Back button in single-page applications. Tweaking the browser history or using other quirks for cancelling backward or forward navigation is not recommended due to the implementation details of the browsers. The browser’s Back button always uses the browser history while the Back button of the app can make use of the browser history or can implement its own navigation logic. Make sure to understand this difference and only control the Back button inside the app.
