Step 4: Two-Way Data Binding

In the examples used so far, we have used a read-only field to display the value of a model property. We will now change the user interface so that the first and last name fields are displayed using sap.m.Input fields and an additional check box control is used to enable or disable both input fields. This arrangement illustrates a feature known as "two-way data binding". Now that the view contains more controls, we will also move the view definition into an XML file.

It is clear that we have not written any code to transfer data between the user interface and the model, yet the Input controls are enabled or disabled according to the state of the checkbox. This behaviour is the result of the fact that all SAPUI5 models implement two-way data binding, and for JSON Models, two-way binding is the default behavior.

Data binding allows the property of a control to derive its value from any suitable property in a model.

SAPUI5 automatically handles the transport of data both from the model to the controls, and back from the controls to the model. This is called two-way binding.
