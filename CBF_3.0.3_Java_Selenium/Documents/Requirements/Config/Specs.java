/*
Requirements
------------
1. Simplify config. Remove config hard-codings.
2. Move WebAppDriver to a flexible cli-level class.
  Remove coupling of AppDriver to Harness.
2.1 This will make Harness pure: only handles configuration, Reporting
    ResourcePaths and Engine setup

Specifications
--------------
1. Move AppDriverLoader.java to cli. It is a Helper.
1.1 Remove loadAppDriver method from Harness.

2. Add method in Helper to call AppDriverLoader and directly invoke Engine.
* this will do all "runArgs" manipulation for browser as needed

2.1 Simplify WebAppDriver so that:
* browser/object map are simple plugin instantiation

3. Refine MasterConfig.xml as attached.
* Browser should have right parameters to remove hard-codings in factory
* Remove unneeded BaseWebAppDriver/ModuleDriver entries

4. Refine Config.xml:
* nest browser and objectmap parameters under webappdriver
*/
