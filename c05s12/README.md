Step 12: Aggregation Binding Using Templates

Aggregation binding (or "list binding") allows a control to be bound to a list within the model data and allows relative binding to the list entries by its child controls.

It will automatically create as many child controls as are needed to display the data in the model using one of the following two approaches:
	Use template control that is cloned as many times as needed to display the data.
	Use a factory function to generate the correct control per bound list entry based on the data received at runtime.
