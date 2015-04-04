## Introduction ##

The Guice Tools Framework is at the heart of the guice IDE plugin and makes writing code analysis tools for guice painless.

Download a copy from the Downloads page.


## How to use the framework in your own tools ##

  1. Subclass the GuiceToolsModule
  1. Implement your tools framework classes
  1. Inject a ModuleManager
  1. Set up a Module Context
  1. Ask the !Module Context for bindings and problems

These steps are explained in detail below.  You may also look at the source code of the IDE plugin in test/com.google.inject.tools.suite for the SampleToolsSuiteUseCaseTest.java file which also serves as an example of how to use the tools suite.

### Subclass the GuiceToolsModule ###

The GuiceToolsModule binds the pieces of the framework together.  To use the framework you need to define your own classes and bind them.  The classes you may choose to bind your own implementations of are:
  * JavaManager
  * ProblemsHandler
  * ProgressHandler
  * Messenger

An example of a subclass of the GuiceToolsModule would be:
```
class MyToolsModule extends GuiceToolsModule {
  @Override
  void bindJavaManager(AnnotatedBindingBuilder<JavaManager> bindJavaManager) {
    bindJavaManager.to(MyJavaManager.class);
  }
}
```

The AnnotatedBindingBuilder<JavaManager> is the return value of a call to binder.bind(JavaManager.class) in guice, when overriding these methods you may do anything to the passed in variable that you would in a module following a bind statement.

Details about the five interfaces you may wish to implement yourself, and their default implementations, can be found below.

### Implement your tools framework classes ###

To continue with our above example:
```
class MyJavaManager implements JavaManager {
  String getGuiceClasspath() {
    return "/PATH/TO/GUICE";
  }
  String getProjectClasspath() {
    return "/PATH/TO/USER/CODE";
  }
  String getSnippetsClasspath() {
    return "/PATH/TO/SNIPPETS/JAR";
  }
  String getJavaCommand() {
    return "java";
  }
  List<String> getJavaFlags() {
    return Collections.<String>emptyList();
  }
}
```

**IMPORTANT** Version 1.0 of Guice is too old for the tools suite, you must use at least the version included in the tools framework download.

The sample code above defines a JavaManager that runs the standard java command and passes in hard coded classpath information about the code to analyze and the jar files from the tools framework.

### Inject a ModuleManager ###

```
ModuleManager moduleManager =
  Guice.createInjector(new MyToolsModule()).getInstance(ModuleManager.class);
```

### Set up a Module Context ###

```
moduleManager.createContext("My Context")
  .addModule(TheModuleToAnalyze.class.getName());
```

### Ask the Module Context for bindings and problems ###

```
moduleManager.update();

ModuleContextRepresentation context = moduleManager.get("My Context");

CodeLocation location = context.findLocation(SomeInterface.class, SomeAnnotation.class);
if (location instanceof BindingCodeLocation) {
  BindingCodeLocation bindingLocation = (BindingCodeLocation)location;
  System.out.println(bindingLocation.bindWhat() + " annotated with " +
    bindingLocation.annotatedWith() + " is bound to " + bindingLocation.bindTo() +
    " at " + bindingLocation.file() + ":" + bindingLocation.location());
} else if (location instanceof NoBindingLocation) {
  NoBindingLocation noBindingLocation = (NoBindingLocation)location;
  System.out.println(noBindingLocation.getTheClass() + " is not bound");
}

Set<CodeProblem> problems = location.getProblems();
for (CodeProblem problem : problems) {
  System.out.println("Guice Code Problem: " + problem.toString());
}
```

The above sample code will look for a binding of SomeClass annotated with SomeAnnotation in TheModuleToAnalyze and print out the result.  If no binding exists it will report that.  It will then report any problems with the binding (e.g. CreationException).

### Details about classes you may wish to implement ###

While none of these must be implemented to use the tools framework, in most cases you will want to at least implement the ModulesSource since without it using the framework can be tricky.  The most straightforward method of doing code analysis is to follow the sample code above and implement only the JavaManager (if necessary).

**JavaManager**: responsible for returning the java command line, any flags to pass in, and the classpath entries for the code to analyze and the jar files of the tools framework (included in the download).  Default implementation uses java as the command line with no flags and assumes the code and jar files are on the default classpath.

**ProblemsHandler**: notified automatically of any problems found in the module contexts (without a request for finding bindings).  Default implementation does nothing.

**Messenger**: passed messages from the tools suite regarding problems with running the framework.  Default implementation does nothing.

**ProgressHandler**: responsible for stepping through the steps of the code runner and measuring progress (if desired).  Default implementation simply blocks the thread calling the module manager until the snippets are run without reporting any progress.  Be careful overriding this, be sure to look at the BlockingProgressHandler class implementation before you do.