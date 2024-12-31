Intellij Decompile Example
===

This project contains the class `TestController` from [grails-core](https://github.com/grails/grails-core.git) to demonstrate how the [java-decompiler](https://java-decompiler.github.io/) can decompile methods on the `TestController.class` but the FernFlower decompiler cannot. The Grails' `ControllerActionTransformer` is the groovy transform applied to `TestController` to generate various methods for the Grails framework.

To Test
---
This project contains a `.sdkmanrc` file to set the version of java.  Assuming sdkman is installed and that version of java is selected: 

* `./gradlew classes` - compiles the project
* `cd build/classes/groovy/main/com/example` - changes to the directory containing the class
* `idea ./TestController.class` - to open the class file in IntelliJ

The class will show methods like this: 

    @DelegatingMethod
    public Object methodAction(Person param1) {
    // $FF: Couldn't be decompiled
    }

While the java decompiler will show the method implementation: 

    @DelegatingMethod
    public Object methodAction(Person p) {
    CallSite[] arrayOfCallSite = $getCallSiteArray();
    try {
    if (!DefaultTypeTransformation.booleanUnbox(arrayOfCallSite[10].call(arrayOfCallSite[11].callGroovyObjectGetProperty(this), "ALLOWED_METHODS_HANDLED")))
    arrayOfCallSite[12].call(arrayOfCallSite[13].callGroovyObjectGetProperty(this), "ALLOWED_METHODS_HANDLED", "methodAction");
    return ScriptBytecodeAdapter.createMap(new Object[] { "person", p });
    } catch (Exception $caughtException) {
    Method $method = (Method)ScriptBytecodeAdapter.castToType(arrayOfCallSite[18].callCurrent(this, arrayOfCallSite[19].callGetProperty($caughtException)), Method.class);
    if (DefaultTypeTransformation.booleanUnbox($method))
    return $method.invoke(this, new Object[] { $caughtException });
    throw (Throwable)$caughtException;
    } finally {
    try {
    Object $allowed_methods_attribute_value = arrayOfCallSite[28].call(arrayOfCallSite[29].callGroovyObjectGetProperty(this), "ALLOWED_METHODS_HANDLED");
    if (ScriptBytecodeAdapter.compareEqual("methodAction", $allowed_methods_attribute_value))
    arrayOfCallSite[30].call(arrayOfCallSite[31].callGroovyObjectGetProperty(this), "ALLOWED_METHODS_HANDLED");
    } catch (Exception $exceptionRemovingAttribute) {
    
          } finally {}
        } 
    }