Step 11: Assign Multiple Targets

In this step, we will add a new button to the home page to illustrate the usage of multiple targets for a route. When the button is pressed, a new page opens that contains two parts: a header part at the top and a content part. The content part displays a table of employees that can be sorted and searched. We will use the array notation in the routing configuration to assign multiple targets to a route - a feature that we have not yet introduced.

We extend our current routing configuration with a new route employeeOverview. Note that this route has to be configured before the employee route, else the employee route would be matched with a hash like /#/employees/overview. The new route employeeOverview references two targets at the same time with an array notation: employeeOverviewTop and employeeOverviewContent. As you can see here, a route can reference an arbitrary number of targets that will be displayed when the route is matched.

Both targets employeeOverviewTop and employeeOverviewContent reference the target employeeOverview as their parent target because we want to place them both inside the parent. Please also note that we also introduce a new layer overview in the viewPath property.

Note
The order of the routing configuration matters here, because the router stops matching additional routes when the first match is found. You can override this behavior if you set parameter greedy to true on the route. Then the route will always be matched when the pattern matches the current URL, even if another route has been matched before. The greedy option comes from the underlying Crossroads.js library, a popular routing library. A common use case for using greedy is configuring targets without views and then listening for route-matched events.






