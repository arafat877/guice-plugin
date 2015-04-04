## Features ##

### Find Bindings ###

The main feature of the plugin is the ability to search for guice bindings in your code.  Before you can search for bindings you must configure your module contexts (see below).  Once that is done, simply select text in a Java file and choose Find Bindings from the context menu.  The bindings for that Java element in your configured contexts will appear in the results view.

### Module Context Configuration ###

To use the plugin you must configure module contexts for it to run.  A **module context** is essentially the information on how to create an injector, so it is a set of modules in your code.

There are three ways to configure module contexts for use with the plugin:

  * Implement Iterable

&lt;Module&gt;

.
  * Use a single module.
  * Create a custom context.

**Implement Iterable**

&lt;Module&gt;

**: The plugin will search your code for anything that implements Iterable**

&lt;Module&gt;

 and configure itself to run each found class as a context.  This is the best way to use the plugin since it guarantees that everyone checking out the project will have the same contexts.  **Important**: You must explicitly implement Iterable

&lt;Module&gt;

 (i.e. add ` implements Iterable<Module> ` even if your object is say a Set

&lt;Module&gt;

).

**The single module method**: The easiest way to configure a context is to specify a single module in your code to be the context.  This amounts to having the plugin run ` Guice.createInjector(new YourModule()); `.  The Module Context Configure Dialog (see below) will allow you to set up such a context.  The downside to this approach is that it only works if you have single modules that function independently.

**The custom context method**: If you have created a class that returns a collection of modules, you can specify that class (and method) in the configure dialog (see below) and have it create a context.  However it is recommended to instead implement Iterable

&lt;Module&gt;

 directly.

## User Interface ##

### The Context Menu ###

The main entry point to the plugin's functionality is the context (right click) menu.  The Guice submenu contains "Find Bindings", "Configure", "Run Modules Now" and "Run Modules Automatically".

**Find Bindings** will run your configured contexts and present the results.

**Configure** will bring up the configure dialog (see below).

**Run Modules Now** will (re)run your contexts. The plugin attempts to listen for changes to your code that necessitate rerunning the module contexts but is not always successful.  If you edit your code to change the bindings, you may need to do this to refresh the cache.

**Run Modules Automatically** controls whether your contexts are automatically rerun in response to code changes.

### The Results View ###

The results view displays results from Find Bindings queries.  The results are displayed in a per context fashion and are hyperlinked wherever possible.

### The Configure Dialog ###

The Configure dialog allows you to specify what contexts to run.

To set up single module contexts, click the "Scan for New Contexts" link in the lower half of the dialog and then select which modules you want to run (clicking the link will temporarily close the dialog while your modules are detected).

The top half of the dialog displays your Iterable

&lt;Module&gt;

 contexts (and allows you to disable/enable them); custom contexts can also be added here.

### The Errors View ###

The errors view servers two purposes.  During context runtime it displays any problems with your code (though these are also reported in response to "Find Bindings").  It also shows any internal errors the plugin has.  During the alpha phase, please check this from time to time and report errors on this website.