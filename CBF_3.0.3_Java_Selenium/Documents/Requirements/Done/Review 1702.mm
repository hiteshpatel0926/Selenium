<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1488079309106" ID="ID_1415479468" MODIFIED="1488080576931">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Review1702
    </p>
  </body>
</html></richcontent>
<node CREATED="1488079332828" ID="ID_1479536720" MODIFIED="1488079339876" POSITION="right" TEXT="Utils/Utils.java">
<node CREATED="1488079483475" ID="ID_1867177437" MODIFIED="1488079518345" TEXT="Make all methods static">
<icon BUILTIN="yes"/>
<node CREATED="1488079493306" ID="ID_849064220" MODIFIED="1488079502582" TEXT="Why only some methods are non-static?"/>
<node CREATED="1488079343168" ID="ID_10070578" MODIFIED="1488079350800" TEXT="Factorize">
<node CREATED="1488079355344" ID="ID_1993801352" MODIFIED="1488079366791" TEXT="Cli.java"/>
</node>
</node>
<node CREATED="1488079431988" ID="ID_649470188" MODIFIED="1488079475214">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Lots of Catch/Println Exception!
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="closed"/>
</node>
<node CREATED="1488079578867" ID="ID_415004771" MODIFIED="1488079588552" TEXT="parseCommandLineArgs">
<icon BUILTIN="yes"/>
<node CREATED="1488079595711" ID="ID_885498892" MODIFIED="1488079604132" TEXT="Make it static"/>
<node CREATED="1488079604777" ID="ID_30155323" MODIFIED="1488079617783" TEXT="It should return Map not LinkedHashMap"/>
<node CREATED="1488079624167" ID="ID_262026077" MODIFIED="1488079646628" TEXT="makeCommandLineArgs must be private"/>
</node>
<node CREATED="1488079821334" ID="ID_499596656" MODIFIED="1488079901181" TEXT="Factorize">
<icon BUILTIN="idea"/>
<node CREATED="1488079827229" ID="ID_1351110739" MODIFIED="1488079852276" TEXT="XtoY methods should be in TypeUtils.java"/>
<node CREATED="1488079853049" ID="ID_646015673" MODIFIED="1488079862357" TEXT="Rest should be in MiscUtils.java"/>
</node>
</node>
<node CREATED="1488080582279" ID="ID_557704792" MODIFIED="1488085346682" POSITION="left" TEXT="runner/TestSetRunner.java">
<icon BUILTIN="full-1"/>
<node CREATED="1488082180385" ID="ID_856763288" MODIFIED="1488082212884" TEXT="delayTestCase">
<icon BUILTIN="idea"/>
<node CREATED="1488082186749" ID="ID_816489162" MODIFIED="1488082205675">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      It should be shared
    </p>
    <p>
      across other testset runners?
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1488084060927" ID="ID_243282415" MODIFIED="1488084081588" TEXT="instanceMap">
<icon BUILTIN="full-2"/>
<node CREATED="1488085222517" ID="ID_1523136309" MODIFIED="1488085322494">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      TestSet modelling
    </p>
    <p>
      and encapsulation is unclear
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="help"/>
<node CREATED="1488084384401" ID="ID_173434208" MODIFIED="1488084409376" TEXT="Dont break encapsulation"/>
<node CREATED="1488085022224" ID="ID_1226785383" MODIFIED="1488085329245">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Use TestInstance object
    </p>
    <p>
      as much as possible
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="yes"/>
<node CREATED="1488084068345" ID="ID_982556968" MODIFIED="1488085317560">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Implementation specifics are actually needed
    </p>
    <p>
      only in TCMaker
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1488084459310" ID="ID_1381247756" MODIFIED="1488085011534">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#160;&#160;private TestResult runTestInstance(final TestInstance testInstance) {
    </p>
    <p>
      &#160;&#160;&#160;&#160;TCMaker tcMaker = new TCMaker() {
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;public TestCase make() throws Exception {
    </p>
    <p>
      &#160;&#160;Map&lt;String, String&gt; instanceMap = new HashMap&lt;String, String&gt;();
    </p>
    <p>
      &#160;&#160;instanceMap.put(&quot;folderPath&quot;, instance.folderPath());
    </p>
    <p>
      &#160;&#160;instanceMap.put(&quot;instanceName&quot;, instance.instanceName());
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;return getTestCaseAccess().getTestCase(instanceMap);
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;}
    </p>
    <p>
      
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;public String toString() {
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;return testInstance.instanceName();
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;}
    </p>
    <p>
      &#160;&#160;&#160;&#160;};
    </p>
    <p>
      &#160;&#160;&#160;&#160;return harness.runTest(tcMaker, instanceName);
    </p>
    <p>
      &#160;&#160;}
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</node>
<node CREATED="1488080711849" FOLDED="true" ID="ID_1429511094" MODIFIED="1488082179200">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Logic could be generalized
    </p>
    <p>
      using lambda expression
    </p>
  </body>
</html></richcontent>
<node CREATED="1488080728331" ID="ID_887676105" MODIFIED="1488081315074" TEXT="&amp;lt;html&amp;gt;&#xa;  &amp;lt;body&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      interface ResultHandler { // &amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;Refer functional interface in Lambda&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;void handleResult(Map testInstance, TestResult result);&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      }&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      function run(List&amp;amp;lt;TestInstance&amp;amp;gt; testInstances, int rerunIndex, &#xa;      &amp;#xa0;ResultHandler resultHandler) {&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;testInstances.forEach {|testInstance|&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;TestResult result = runTestInstance(testInstance);&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;resultHandler.handleResult(testInstance, result);&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;}&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      }&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      function run(TestSet testSet, in noRetries) {&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&amp;#xa0;&amp;#xa0;&amp;#xa0;// &amp;amp;lt;-- the same &#xa;      name run() is overloaded&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;List&amp;amp;lt;TestInstance&amp;amp;gt; testInstances = getAllTestInstances(testSet);&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;for (int i=0; i&amp;amp;lt;noRetries; ++i) {&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;List&amp;amp;lt;TestInstance&amp;amp;gt; failed = null;&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&amp;#xa0;&#xa;      &amp;#xa0;// failed Instances&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;run(testInstances, i, (testInstance, testResult) -&amp;amp;gt; {&amp;#xa0;&amp;#xa0;&amp;amp;lt;--&amp;#xa0;&#xa;      Lambda expression&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;if (testResult.isFailed/isError) {&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;if (failed == null)&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;failed = new &#xa;      ArrayList&amp;amp;lt;TestInstance&amp;amp;gt;();&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0; &amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;failed.add(testInstance);&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;}&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0; &#xa;      &amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;}));&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;If (failed == null) break; // nothing failed&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;testInstances = failed;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;// retry failed test instances&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      &amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;&amp;#xa0;}&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p class=&amp;quot;MsoNormal&amp;quot;&amp;gt;&#xa;      }&amp;lt;o p=&amp;quot;#DEFAULT&amp;quot;&amp;gt;&amp;lt;/o&amp;gt;&#xa;    &amp;lt;/p&amp;gt;&#xa;    &amp;lt;p&amp;gt;&#xa;      &#xa;    &amp;lt;/p&amp;gt;&#xa;  &amp;lt;/body&amp;gt;&#xa;&amp;lt;/html&amp;gt;"/>
</node>
<node CREATED="1488086485986" ID="ID_184023477" MODIFIED="1488086511543">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Singletone usage
    </p>
    <p>
      needs clean up
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="full-1"/>
<node CREATED="1488086517461" ID="ID_1242879975" MODIFIED="1488087237428">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      class TestSetRunner {
    </p>
    <p>
      public static void main(String[] args) {
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;Map runMap = parseCommandLine(args);
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;TestSetRunner runner = TestSetRunner.new(runMap);
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;try {
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;runner.runTestset();
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&#160;} finalize {
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;runner.tearDown();
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&#160;}
    </p>
    <p>
      &#160;&#160;}
    </p>
    <p>
      }
    </p>
    <p>
      private:
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;TestSetRunner(Map&lt;&gt; runMap) {
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;harness = new Harness(runMap, runMap.get(&quot;testSetSheet&quot;));
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;logger = new Logger(this);
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;&#160;}
    </p>
    <p>
      
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;void tearDown() {
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;}
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1488087239077" ID="ID_539476766" MODIFIED="1488087247752" TEXT="There wont be any static members"/>
<node CREATED="1488087248672" ID="ID_1266864472" MODIFIED="1488087270298" TEXT="Harness will be a private member of TestsetRunner"/>
<node CREATED="1488166741383" FOLDED="true" ID="ID_1320965029" MODIFIED="1488167106639" TEXT="Maybe gets changed as">
<icon BUILTIN="idea"/>
<node CREATED="1488166761875" ID="ID_1243114625" MODIFIED="1488166841662" TEXT="java TestsetRunner --config xxxconfig.xml --testsetfile abcd.xls --sheetname Sheet1"/>
<node CREATED="1488166845897" ID="ID_298710201" MODIFIED="1488166855534" TEXT="Key methods could be">
<node CREATED="1488166860125" ID="ID_196863932" MODIFIED="1488167102805">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      class TestsetRunner {
    </p>
    <p>
      &#160;&#160;public static void main(String[] args) {
    </p>
    <p>
      &#160;&#160;}
    </p>
    <p>
      
    </p>
    <p>
      &#160;&#160;public TestsetRunner(String configFilePath) {
    </p>
    <p>
      &#160;&#160;&#160;&#160;&#160;// initialize harness etc.
    </p>
    <p>
      &#160;&#160;}
    </p>
    <p>
      
    </p>
    <p>
      &#160;&#160;public void run(String testsetFileName, String sheetName) {
    </p>
    <p>
      &#160;&#160;}
    </p>
    <p>
      
    </p>
    <p>
      &#160;&#160;private void run(TestSet testset) {
    </p>
    <p>
      &#160;&#160;}
    </p>
    <p>
      }
    </p>
  </body>
</html>
</richcontent>
</node>
</node>
</node>
</node>
</node>
<node CREATED="1488087301057" ID="ID_1431628879" MODIFIED="1488087628576" POSITION="left" TEXT="harness/Harness.java">
<icon BUILTIN="full-2"/>
<node CREATED="1488087628560" ID="ID_107463994" MODIFIED="1488166659875" TEXT="Requires cleanup">
<icon BUILTIN="full-2"/>
<node CREATED="1488087315717" ID="ID_1960039401" MODIFIED="1488087496045">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      It can use Lambda
    </p>
    <p>
      to reduce coupling with Runners
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="idea"/>
<node CREATED="1488087346454" ID="ID_1402185733" MODIFIED="1488087361847" TEXT="Use map for initialization"/>
</node>
<node CREATED="1488087500440" ID="ID_850187937" MODIFIED="1488087523829" TEXT="Pass Configuration explicitly">
<icon BUILTIN="full-3"/>
</node>
<node CREATED="1488087517495" ID="ID_629742433" MODIFIED="1488087568474" TEXT="Presently runMap is used only to extract configFilePath"/>
<node CREATED="1488166639945" ID="ID_1195384156" MODIFIED="1488166648082" TEXT="GCONFIG">
<node CREATED="1488166650499" ID="ID_1666935811" MODIFIED="1488166697807">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      would be better use a
    </p>
    <p>
      global harness.Configuration class?
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1488166700427" ID="ID_1965863936" MODIFIED="1488166713471" TEXT="will internally use Utils.Config class"/>
</node>
</node>
</node>
</node>
<node CREATED="1488081639027" ID="ID_919974287" MODIFIED="1488081678251" POSITION="right" TEXT="General">
<node CREATED="1488085676128" ID="ID_89619342" MODIFIED="1488085834393">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Use base class/interface
    </p>
    <p>
      Hide implementation when possible
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="full-2"/>
<node CREATED="1488085776025" ID="ID_865519150" MODIFIED="1488085828556">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Map&lt;xxx&gt; instead of
    </p>
    <p>
      LinkedHashMap or HashMap
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1488085855207" ID="ID_235422105" MODIFIED="1488085921381">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Examples:
    </p>
    <p>
      Map&lt;String, String&gt; abc = new HashMap&lt;String, String&gt;();
    </p>
    <p>
      List&lt;String&gt; xxx = new ArrayList&lt;String&gt;();
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1488085943240" ID="ID_761126237" MODIFIED="1488086085043">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Clean up code
    </p>
    <p>
      Enable/Remove warnings in Eclipse/compier
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="full-3"/>
<node CREATED="1488085977062" ID="ID_1181737381" MODIFIED="1488085981625" TEXT="Unused variables"/>
<node CREATED="1488086230033" ID="ID_159737164" MODIFIED="1488086254001" TEXT="Unused imports"/>
<node CREATED="1488085982389" ID="ID_1746125452" MODIFIED="1488085994661" TEXT="Wrong scope">
<node CREATED="1488085996710" ID="ID_480219970" MODIFIED="1488086013382" TEXT="static variables insted of  member variables"/>
<node CREATED="1488086013789" ID="ID_1771953129" MODIFIED="1488086026671" TEXT="member variables instead of local variables"/>
<node CREATED="1488086027115" ID="ID_577499727" MODIFIED="1488086039218" TEXT="public scope instead of private"/>
</node>
</node>
<node CREATED="1488081681335" ID="ID_615974593" MODIFIED="1488081691898" TEXT="Use of system.println">
<icon BUILTIN="stop-sign"/>
</node>
<node CREATED="1488081710469" ID="ID_309179786" MODIFIED="1488081718890" TEXT="Use of catch(Exception)">
<icon BUILTIN="stop-sign"/>
</node>
<node CREATED="1488081720037" ID="ID_403919420" MODIFIED="1488081759848">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Inconsistent singleton
    </p>
    <p>
      pattern in design
    </p>
  </body>
</html></richcontent>
<node CREATED="1488081763118" FOLDED="true" ID="ID_1828107366" MODIFIED="1488082057037" TEXT="LogUtils if used must be initialized before">
<icon BUILTIN="messagebox_warning"/>
<node CREATED="1488081804872" ID="ID_1594646742" MODIFIED="1488081832135">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      If it is redirected to null logger,
    </p>
    <p>
      then redirected to the right place, it is okay
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1488081859537" ID="ID_48990882" MODIFIED="1488081903373">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      if static methods, can use a
    </p>
    <p>
      static logger method, if not performance sensitive
    </p>
  </body>
</html></richcontent>
<node CREATED="1488081906298" ID="ID_1472425513" MODIFIED="1488082041710">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      private static Logger logger() {
    </p>
    <p>
      &#160;&#160;&#160;&#160;return logger || (logger = new Logger('MyClass'))
    </p>
    <p>
      }
    </p>
    <p>
      private static logger;
    </p>
    <p>
      public static xxx methodxxx(....) {
    </p>
    <p>
      .....
    </p>
    <p>
      &#160;&#160;&#160;logger().handleError(&quot;Something wrong&quot;, exception)
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1488082060815" ID="ID_1811054917" MODIFIED="1488086163056" TEXT="Pattern 1: static constructor"/>
</node>
<node CREATED="1488085080720" ID="ID_58607755" MODIFIED="1488086135907" TEXT="Standardize eclipse settings">
<icon BUILTIN="full-3"/>
<node CREATED="1488085088829" ID="ID_523938103" MODIFIED="1488085103005" TEXT="Expand tab to 2 spaces">
<icon BUILTIN="help"/>
</node>
</node>
</node>
</node>
</map>
