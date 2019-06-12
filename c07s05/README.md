Step 5: Display a Target Without Changing the Hash

In this step, you will learn more about targets and how to display a target from the routing configuration manually.

We will display the Not Found target from the previous step without changing the hash to illustrate this navigation pattern. We will also consider a side-effect that prevents us from navigating back in this case.

Fortunately, we can extend our app and offer an easy solution. There are some use cases that should not be persisted in the URL but just be triggered by the application logic if needed. A target is a navigation-related configuration for a view and we can display targets manually without referencing them in a navigation route. Good examples for this are temporary errors, switching to an edit page for a business object, or going to a Settings page. Sometimes you will also have to implement a way back manually.

Conventions
Display targets manually if you want to trigger a navigation without changing the hash

Think carefully about all navigation patterns in your application, otherwise the user might get stuck
