Step 2: Creating a Model

In this step, we create a model as container for the data on which your application operates.

The business data within a model can be defined using various formats:
>JavaScript Object Notation (JSON)
>Extensible Markup Language (XML)
>OData
>Your own custom format (not covered in this tutorial)

When JSON, XML and resource models are created, the data they contain is loaded in a single request (either from a file stored locally on the client or by requesting it from a Web server). In other words, after the model's data has been requested, the entire model is known to the application. These models are known as client-side models and tasks such as filtering and sorting are performed locally on the client.

An OData model however, is a server-side model. This means that whenever an application needs data from the model, it must be requested from the server. Such a request will almost never return all the data in the model, typically because this would be far more data than is required by the client application. Consequently, tasks such as sorting and filtering should always be delegated to the server.

In this tutorial, we will focus on JSON models since they are the simplest ones to work with.

