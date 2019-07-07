Overview
--------
There are different ways of reusing the component library from TestNG
Primarily in combining ‘Programming’/script-driven with Excel/file driven composition of testing.

Samples
-------
1. TestNGHelper: Provides helper methods to call component library from ad hoc TestNG test classes
2. TestNGTCDriver: Generic TestNG TC, which is also a generic TC driver for a test case serialized in a format such as xls or yaml.
* TestNGTCx.yaml is a sample yaml
* xls will be similar to CBF format except, there will be separate sheets for setup, teardown and test-case level steps

Note:
-----
BridgeTestCase: should be rewritten to use TestNGHelper. Needs discussion
