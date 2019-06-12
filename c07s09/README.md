Step 9: Allow Bookmarkable Tabs with Optional Query Parameters

The resume view contains four tabs as we have seen in the previous steps. However, when the user navigates to the resume page, only the first tab is displayed initially. Navigating directly to a specific tab or bookmarking a tab is not yet supported in our current app.

In this step, we implement a bookmarking feature by enabling deep linking to tabs with optional query parameters. A deep link is basically a link that directly references a deeper structure and parameters of the app in the URL. It is often bookmarked or shared to have a convenient entry point into the app for a certain task or action. The selected tab should be reflected in the URL but the tab can also be omitted, for example, when we initially navigate to the resume page.
