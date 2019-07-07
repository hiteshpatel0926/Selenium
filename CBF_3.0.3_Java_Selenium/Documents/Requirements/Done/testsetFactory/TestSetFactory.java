package cbf.model;

interface TestSetFactory {
/*
  Returns null if not found.
  info: of what test set is as per factory
*/
  abstract public TestSet getTestSet(Map info);
}
