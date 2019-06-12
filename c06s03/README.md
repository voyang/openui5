Step 3: Automatic Data Type Detection

In this step, we use the automatic data type detection of the OData V4 model to parse, validate, and format user entries. The service metadata contains type information for the properties of each entity.

The OData V4 Model utilizes this information to compute the corresponding SAPUI5 type, including constraints, and sets this type to the SAPUI5 property binding for the entity property. For example, for <Input value={Age}/> the SAPUI5 type Int64 is used, which corresponds to the OData type Edm.Int64
