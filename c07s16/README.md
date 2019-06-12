Step 16: Handle Invalid Hashes by Listening to Bypassed Events

So far we have created many useful routes in our app. In the very early steps we have also made sure that a Not Found page is displayed in case the app was called with an invalid hash. Now, we proceed further and track invalid hashes to be able to detect and correct any invalid links or add new URL patterns that are often requested but not found. Therefore, we simply listen to the bypassed events

Now try to access webapp/index.html#/thisIsInvalid while you have your browser console open. As you can see, there is a message that issues a faulty hash. Furthermore, our NotFound page is displayed.
