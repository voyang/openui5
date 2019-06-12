Step 6: Resource Models

Business applications also require language-specific (translatable) texts used as labels and descriptions on the user interface.

The example we used at the start of this tutorial was overly simplistic as we stored language-specific text directly in a JSON model object. Generally speaking, unless language-specific text is derived directly from a back-end system, it is not considered good programming practice to place translatable texts directly into a model. So let's correct this situation by placing all translatable texts (such as field labels) into a resource bundle.
