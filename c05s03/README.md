Step 3: Create Property Binding

Although there is no visible difference, the text on the screen is now derived from model data.

The text property of the sap.m.Text control is set to the value {/greetingText}. The curly brackets enclosing a binding path (binding syntax) are automatically interpreted as a binding. These binding instances are called PropertyBindings. In this case, the control's text property is bound to the greetingText property at the root of the default model, as the slash (/) at the beginning of the binding path denotes an absolute binding path.
