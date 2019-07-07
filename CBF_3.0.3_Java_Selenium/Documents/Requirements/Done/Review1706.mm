<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1496467305947" ID="ID_1145453916" MODIFIED="1498476100855" TEXT="Review&#xa;1706">
<node CREATED="1496467324400" ID="ID_488528950" MODIFIED="1498476089809" POSITION="right" TEXT="General">
<node CREATED="1496467330251" ID="ID_1032558860" MODIFIED="1496467358184">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Gaps in implementations
    </p>
    <p>
      from earlier review comments
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1496467359363" ID="ID_1584102138" MODIFIED="1496467378518">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Critical cleanups
    </p>
    <p>
      before 3.0 rollout
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1498338482853" ID="ID_1905253736" MODIFIED="1498476382139" POSITION="left" TEXT="Top Priority">
<icon BUILTIN="full-1"/>
<node CREATED="1498338785493" FOLDED="true" ID="ID_532639398" MODIFIED="1499246335818" TEXT="Rerun">
<icon BUILTIN="button_ok"/>
<node CREATED="1498338508693" FOLDED="true" ID="ID_245856128" MODIFIED="1499246330772">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      rerun result
    </p>
    <p>
      summarization
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="flag-green"/>
<node CREATED="1498338515623" ID="ID_552970648" MODIFIED="1498338530821" TEXT="Will be discussed separately"/>
<node CREATED="1498338592089" ID="ID_494356490" MODIFIED="1498338596857" TEXT="&quot;significance&quot; logic"/>
</node>
<node CREATED="1498338589779" ID="ID_1176519323" MODIFIED="1499246330772">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Initialization error
    </p>
    <p>
      on rerun
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="flag-green"/>
<node CREATED="1498338616874" ID="ID_1499411255" MODIFIED="1498338679276">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      If (testcase.testresult.miscData.containsKey("InitializationError"))
    </p>
    <p>
      {
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;do not reschedule it for rerun
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1498338684990" FOLDED="true" ID="ID_861917479" MODIFIED="1499245586644" TEXT="This is applicable for 2 cases">
<node CREATED="1498338694468" ID="ID_1875463335" MODIFIED="1498338704875" TEXT="#trials &gt; 1"/>
<node CREATED="1498338705296" FOLDED="true" ID="ID_607631938" MODIFIED="1498562012321" TEXT="#batchTrials &gt; 1">
<node CREATED="1498338714688" ID="ID_752793718" MODIFIED="1498338767858">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      In this case, testResult of batch
    </p>
    <p>
      could be failed, but there might not be
    </p>
    <p>
      any thing for next batch to run.
    </p>
    <p>
      So. check that logic
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</node>
</node>
<node CREATED="1498338842835" FOLDED="true" ID="ID_1457706003" MODIFIED="1499246339968">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Some more
    </p>
    <p>
      renames
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
<node CREATED="1498338365905" ID="ID_171095820" MODIFIED="1498338389682" TEXT="cbfx.browsers ==&gt; cbfx.ui.browser"/>
<node CREATED="1498338391214" ID="ID_503558206" MODIFIED="1498338422955" TEXT="cbfx.objectmap ==&gt; cbfx.ui.objectmap"/>
<node CREATED="1498338885801" ID="ID_483040610" MODIFIED="1498338908577" TEXT="cbf.harness.ParameterAccess ==&gt; cbf.engine.ParameterAccess"/>
<node CREATED="1498338909754" ID="ID_690543545" MODIFIED="1498562012321" TEXT="cbf.plugin.ReportingManager ==&gt; cbf.harness.ReportingManager">
<arrowlink DESTINATION="ID_1883897817" ENDARROW="Default" ENDINCLINATION="293;0;" ID="Arrow_ID_1540734654" STARTARROW="None" STARTINCLINATION="293;0;"/>
<node CREATED="1498338939151" ID="ID_1318998098" MODIFIED="1498338948443" TEXT="It is a helper to Harness"/>
</node>
<node CREATED="1498339326880" ID="ID_853732279" MODIFIED="1498343304527" TEXT="cbf.plugin.PluginManager==&gt; cbf.utils.PluginManager">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_853732279" ENDARROW="Default" ENDINCLINATION="109;0;" ID="Arrow_ID_440182350" SOURCE="ID_1640332663" STARTARROW="None" STARTINCLINATION="109;0;"/>
</node>
<node CREATED="1498341363526" ID="ID_823842586" MODIFIED="1498341452354" TEXT="cbf.engine.TestCaseRunner ==&gt; TestRunner">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_823842586" ENDARROW="Default" ENDINCLINATION="52;0;" ID="Arrow_ID_1276700387" SOURCE="ID_377313521" STARTARROW="None" STARTINCLINATION="52;0;"/>
<node CREATED="1498341384911" ID="ID_764388266" MODIFIED="1498341397341" TEXT="Remove confusion on testInstance vs testCase"/>
</node>
<node CREATED="1498341399664" ID="ID_377313521" MODIFIED="1498341452354" TEXT="cbf.engineMultiTestCaseRunner ==&gt; MultiTestRunner">
<arrowlink DESTINATION="ID_823842586" ENDARROW="Default" ENDINCLINATION="52;0;" ID="Arrow_ID_1276700387" STARTARROW="None" STARTINCLINATION="52;0;"/>
</node>
<node CREATED="1498343452960" ID="ID_834600679" MODIFIED="1498343501794" TEXT="cbf.utils.Autoit ==&gt; cbfx.utils.Autoit?"/>
<node CREATED="1498338977579" ID="ID_954452799" MODIFIED="1498338989143" TEXT="remove obsolete packages">
<node CREATED="1498338990931" ID="ID_1895837644" MODIFIED="1498338994817" TEXT="cbf.runner"/>
<node CREATED="1498338995154" ID="ID_1640332663" MODIFIED="1498343333174" TEXT="cbf.plugin">
<arrowlink DESTINATION="ID_853732279" ENDARROW="Default" ENDINCLINATION="109;0;" ID="Arrow_ID_440182350" STARTARROW="None" STARTINCLINATION="109;0;"/>
</node>
</node>
</node>
<node CREATED="1498339376479" FOLDED="true" ID="ID_164877061" MODIFIED="1499246393896">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Some more
    </p>
    <p>
      cleanups
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
<node CREATED="1498339400283" FOLDED="true" ID="ID_1883897817" MODIFIED="1498648065955" TEXT="PluginManager">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1883897817" ENDARROW="Default" ENDINCLINATION="293;0;" ID="Arrow_ID_1540734654" SOURCE="ID_690543545" STARTARROW="None" STARTINCLINATION="293;0;"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1498339413032" ID="ID_1955942656" MODIFIED="1498339633543">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      It is an unnecessarily
    </p>
    <p>
      dirty mix of singleton and static
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1498339637219" ID="ID_1517698543" LINK="PluginManager.java" MODIFIED="1498340681605">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      A pure non-static class
    </p>
    <p>
      Use Harness.pluginManager() singleton
    </p>
    <p>
      See details as in...
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1498340690654" FOLDED="true" ID="ID_1524672781" MODIFIED="1498648073602" TEXT="ResourcePaths">
<icon BUILTIN="button_ok"/>
<node CREATED="1498340847864" ID="ID_1870996700" MODIFIED="1498342841106" TEXT="Remove singleton"/>
<node CREATED="1498340698610" ID="ID_196132094" MODIFIED="1498562012452">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Use Harness.resourcePaths() rather than
    </p>
    <p>
      ResourcePaths.getInstance()
    </p>
  </body>
</html></richcontent>
<node CREATED="1498342884014" ID="ID_1235278697" MODIFIED="1498343004387">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      void static Harness.initialize() {
    </p>
    <p>
      &#xfffd;&#xfffd;resourcePaths = new ResourcePaths(the 3 paths)
    </p>
    <p>
      }
    </p>
    <p>
      
    </p>
    <p>
      void static ResourcePaths resourcePaths() {
    </p>
    <p>
      &#xfffd;&#xfffd;return resourcePaths;
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1498343019859" ID="ID_1552556351" MODIFIED="1498343044133">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Remove getInstance() methods
    </p>
    <p>
      THey are unnecessary
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1498343121892" FOLDED="true" ID="ID_305136526" MODIFIED="1498648076254" TEXT="Harness.config">
<icon BUILTIN="button_ok"/>
<node CREATED="1498343147334" ID="ID_1052149690" MODIFIED="1498562012452">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Make it similar to other 2
    </p>
  </body>
</html></richcontent>
<node CREATED="1498343185158" ID="ID_1398742248" MODIFIED="1498343243124">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      private Configuration config;
    </p>
    <p>
      
    </p>
    <p>
      public static Configuration configuration() {
    </p>
    <p>
      &#xfffd;&#xfffd;return config;
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1498341251161" FOLDED="true" ID="ID_1815642899" MODIFIED="1498648082931">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      TestRunner usage
    </p>
    <p>
      of ParameterAccess
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
<node CREATED="1498341271720" ID="ID_1028429857" MODIFIED="1498342137285">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <pre>  public TestResult runTestInstance(TestInstance oTestInstance) {
    paramsAccess = new ParameterAccess();
    biComponentsDriver = new BuiltinComponentsDriver(paramsAccess);
    oResultsTracker.addReporter(paramsAccess);
try {
  return oResultsTracker.track(new Trackable() {
      ...
} finally { // remove observer after testcase is run
    oResultsTracker.removeReporter(paramsAccess); // new method
}
  }
    </pre>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1498343622667" ID="ID_1903445746" MODIFIED="1498737648593" TEXT="Split cbf.utils.Utils to 3">
<icon BUILTIN="button_ok"/>
<node CREATED="1498476699390" ID="ID_1075040366" MODIFIED="1498562012452" TEXT="Utils">
<node CREATED="1498343658631" ID="ID_1307291077" MODIFIED="1498562012336">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      If possible,
    </p>
    <p>
      rename to TypeUtils
    </p>
  </body>
</html></richcontent>
<node CREATED="1498343674690" ID="ID_1201751809" MODIFIED="1498343691289" TEXT="Type conversions"/>
</node>
</node>
<node CREATED="1498343693732" ID="ID_488642802" MODIFIED="1498562012469" TEXT="CliUtils">
<node CREATED="1498343712001" ID="ID_1020684126" MODIFIED="1498343852688" TEXT="Return Map&lt;&gt; not LinkedMap"/>
<node CREATED="1498343728857" ID="ID_423405764" MODIFIED="1498343856806" TEXT="Methods are static"/>
</node>
<node CREATED="1498343737823" ID="ID_1789038601" MODIFIED="1498562012469" TEXT="MiscUtils">
<node CREATED="1498343750038" ID="ID_1014816831" MODIFIED="1498343766709" TEXT="hostName"/>
</node>
</node>
<node CREATED="1498458508125" FOLDED="true" ID="ID_368193935" MODIFIED="1499246356063">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      utils cannot
    </p>
    <p>
      call other packages
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
<node CREATED="1498458525057" FOLDED="true" ID="ID_939064504" MODIFIED="1498648054409" TEXT="SleepUtils">
<icon BUILTIN="button_ok"/>
<node CREATED="1498458739410" ID="ID_1987932479" MODIFIED="1498458744984" TEXT="move to cbfx.utils?"/>
</node>
<node CREATED="1498458875765" ID="ID_639744522" MODIFIED="1498631245026" TEXT="Logger">
<icon BUILTIN="stop-sign"/>
<node CREATED="1498458886960" ID="ID_1768948234" MODIFIED="1498458908892" TEXT="Cannot call TestResultLogger">
<arrowlink DESTINATION="ID_1223932459" ENDARROW="Default" ENDINCLINATION="389;0;" ID="Arrow_ID_1613946790" STARTARROW="None" STARTINCLINATION="389;0;"/>
</node>
</node>
</node>
<node CREATED="1498469979219" ID="ID_1715674239" MODIFIED="1498652699299" TEXT="New cli">
<icon BUILTIN="button_ok"/>
<node CREATED="1498344527776" FOLDED="true" ID="ID_1644109259" MODIFIED="1498652695789">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Put common code
    </p>
    <p>
      in a cli.Helper class
    </p>
  </body>
</html></richcontent>
<node CREATED="1498344570365" ID="ID_1227257416" MODIFIED="1498470161967">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      package class Helper {
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;public static Map... parse(String[] args) {
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;...
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;}
    </p>
    <p>
      
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;public static void initialize(Map runArgs) {
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;Harness.iniialize
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;}
    </p>
    <p>
      
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;public static TestSetFactory getTestSetFactory() {
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;}
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1498344461561" ID="ID_234897417" MODIFIED="1498562012472" TEXT="Cli::getTestSetFactory()">
<node CREATED="1498344484510" ID="ID_1513832516" MODIFIED="1498344488123" TEXT="Cleanup code"/>
<node CREATED="1498344765352" ID="ID_295371306" MODIFIED="1498344773011" TEXT="Why so complex"/>
</node>
</node>
</node>
<node CREATED="1498342176767" FOLDED="true" ID="ID_611947003" MODIFIED="1499246470711" TEXT="Reporting">
<icon BUILTIN="button_ok"/>
<node CREATED="1498342228334" ID="ID_44887866" MODIFIED="1498342237934" TEXT="vs OPTIK reporter"/>
<node CREATED="1498338795547" ID="ID_824254619" MODIFIED="1498458363784">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Logger vs
    </p>
    <p>
      Reporter
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="help"/>
<node CREATED="1498338808874" ID="ID_1576581069" MODIFIED="1498338840186">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Need to discuss
    </p>
    <p>
      why it is mixed up
    </p>
    <p>
      and how to clean up
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1498458369138" ID="ID_1223932459" MODIFIED="1498458908892" TEXT="Logger cannot call Reporter">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1223932459" ENDARROW="Default" ENDINCLINATION="389;0;" ID="Arrow_ID_1613946790" SOURCE="ID_1768948234" STARTARROW="None" STARTINCLINATION="389;0;"/>
<icon BUILTIN="messagebox_warning"/>
</node>
</node>
</node>
<node CREATED="1498476382124" ID="ID_1105033425" MODIFIED="1504008316303" TEXT="Miscellaneous">
<icon BUILTIN="back"/>
<node CREATED="1498476239872" ID="ID_1498471495" MODIFIED="1498476248951" TEXT="Javadoc updates"/>
<node CREATED="1498344074280" ID="ID_295262529" MODIFIED="1498344090351" TEXT="Add copyright headers">
<icon BUILTIN="messagebox_warning"/>
</node>
</node>
</node>
<node CREATED="1498476100840" ID="ID_1022002672" MODIFIED="1504008319842" POSITION="left" TEXT="Future...">
<icon BUILTIN="back"/>
<node CREATED="1498458295654" ID="ID_768331973" MODIFIED="1498476233643" TEXT="Next Release">
<node CREATED="1498458305774" ID="ID_1079042725" MODIFIED="1498458316003" TEXT="Component library"/>
<node CREATED="1498459180205" ID="ID_799914559" MODIFIED="1498459189013" TEXT="XBT Console integration"/>
<node CREATED="1498458316701" ID="ID_1925542835" MODIFIED="1498458357173" TEXT="HIG Dashboard support"/>
</node>
<node CREATED="1498459164764" ID="ID_1105983198" MODIFIED="1498476915696" TEXT="Further">
<node CREATED="1498476915696" ID="ID_1562578987" MODIFIED="1498477064490">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Misc Cleanup
    </p>
    <p>
      &amp; Enhancements
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="group"/>
<node CREATED="1498458772224" ID="ID_1206355024" MODIFIED="1498476952986" TEXT="SmartSleep">
<icon BUILTIN="idea"/>
</node>
<node CREATED="1498476162616" ID="ID_113297959" MODIFIED="1498476185467" TEXT="Detailed reporting"/>
<node CREATED="1498476201964" ID="ID_1305600141" MODIFIED="1498476208211" TEXT="SOI integration"/>
<node CREATED="1498476862477" FOLDED="true" ID="ID_1804326721" MODIFIED="1498562012474" TEXT="Cli integration">
<node CREATED="1498476869332" ID="ID_1341382487" MODIFIED="1498476873096" TEXT="More elaborate"/>
</node>
</node>
<node CREATED="1498475708510" ID="ID_1291223067" MODIFIED="1498477099629" TEXT="Demos&amp;Tests">
<icon BUILTIN="group"/>
<node CREATED="1498475746180" ID="ID_234876175" MODIFIED="1498475754699" TEXT="Move Samples under Test"/>
<node CREATED="1498475859467" FOLDED="true" ID="ID_1890364927" MODIFIED="1498562012475">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Dummy config
    </p>
    <p>
      purely for cbf
    </p>
  </body>
</html></richcontent>
<node CREATED="1498475787972" ID="ID_1494990049" MODIFIED="1498475883408" TEXT="Dummy App"/>
<node CREATED="1498475884161" ID="ID_605500633" MODIFIED="1498475912236" TEXT="Dummy reporters"/>
<node CREATED="1498475804776" ID="ID_994794082" MODIFIED="1498475824312" TEXT="Dummy test suite"/>
</node>
<node CREATED="1498475938591" ID="ID_1736308976" MODIFIED="1498475952502" TEXT="Parallel &amp; serial runs"/>
</node>
<node CREATED="1498459174878" ID="ID_1726120454" MODIFIED="1498459179647" TEXT="Live Dashboards"/>
</node>
</node>
<node CREATED="1496467390002" ID="ID_1104351812" MODIFIED="1498476087606" POSITION="right" TEXT="Top priority">
<icon BUILTIN="full-1"/>
<node CREATED="1496467597747" FOLDED="true" ID="ID_87903124" MODIFIED="1498629582720" TEXT="Multi threading">
<icon BUILTIN="stop"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1496467401128" ID="ID_1465174190" MODIFIED="1496558650124" TEXT="Harness">
<cloud/>
<node CREATED="1496467552911" ID="ID_450940153" MODIFIED="1496467574632">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      RIght now neither
    </p>
    <p>
      here nor there
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1496467604628" ID="ID_38229409" MODIFIED="1496558650124" TEXT="Reporting">
<cloud/>
<node CREATED="1496467609211" FOLDED="true" ID="ID_1364590426" MODIFIED="1498562012475" TEXT="Specs given">
<node CREATED="1496467616811" ID="ID_1153590596" MODIFIED="1496467620830" TEXT="Or not committed"/>
</node>
<node CREATED="1496467622551" ID="ID_1974451965" MODIFIED="1496467630894" TEXT="Might need more change"/>
</node>
<node CREATED="1496558550818" ID="ID_1540017701" MODIFIED="1496558555635" TEXT="Static in webdrivers">
<node CREATED="1496558571115" ID="ID_146707047" MODIFIED="1496558626145">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      ./appium/AppiumNativeDriver.java:&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;private static WebDriver webDr;
    </p>
    <p>
      ./appium/AppiumWebDriver.java:&#xfffd;&#xfffd;private static WebDriver webDr;
    </p>
    <p>
      ./appium/AppiumWebDriver.java:&#xfffd;&#xfffd;private static int cnt = 0;
    </p>
    <p>
      ./eggplant/EggUIDriver.java:&#xfffd;&#xfffd;&#xfffd;&#xfffd;public static String screenTitle;
    </p>
    <p>
      ./seetest/SeetestUIDriver.java: private static int cnt = 0;
    </p>
    <p>
      ./selenium/WebUIDriver.java:&#xfffd;&#xfffd;&#xfffd;&#xfffd;private static ObjectMap objMap;
    </p>
    <p>
      ./testng/TestNGRunner.java:&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;public static Map&lt;String, String&gt; runMap;
    </p>
  </body>
</html></richcontent>
<cloud/>
</node>
</node>
</node>
<node CREATED="1496467405780" ID="ID_1770270136" LINK="testsetFactory/README.txt" MODIFIED="1498338228053" TEXT="TestsetFactory">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1496467413074" FOLDED="true" ID="ID_1478387613" MODIFIED="1498629589135" TEXT="Runner Merging">
<icon BUILTIN="button_ok"/>
<node CREATED="1496467428157" ID="ID_76721175" MODIFIED="1496467447538">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Remove obsolete runner
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1496467448431" ID="ID_459205033" MODIFIED="1496467467427">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Using TestNGrunner
    </p>
    <p>
      for MainRunner
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1496467526887" ID="ID_1054925397" MODIFIED="1496467542394">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;Can be discussed
    </p>
    <p>
      with HIG&gt;
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1496467650785" ID="ID_1840469895" MODIFIED="1504008073429" TEXT="Package renames">
<icon BUILTIN="button_ok"/>
<node CREATED="1496467660818" ID="ID_456734943" LINK="FactoryPackageRenaming.csv" MODIFIED="1498338259153" TEXT="Factory">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1496467690516" ID="ID_310099852" MODIFIED="1498562012374" TEXT="*WebDriver*">
<icon BUILTIN="stop"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1496467710543" ID="ID_390959199" MODIFIED="1496467726633">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Too many packages
    </p>
    <p>
      Cleanup will remove confusion
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1496557026294" FOLDED="true" ID="ID_50766218" MODIFIED="1498562012475" TEXT="Suggestion">
<node CREATED="1496557035934" FOLDED="true" ID="ID_1224289985" MODIFIED="1498562012374" TEXT="cbfx">
<node CREATED="1496558189021" FOLDED="true" ID="ID_1562970715" MODIFIED="1498562012374" TEXT="ui">
<node CREATED="1496557040777" FOLDED="true" ID="ID_474468705" MODIFIED="1498338153716" TEXT="web">
<node CREATED="1496557674729" FOLDED="true" ID="ID_549785035" MODIFIED="1498338153035">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      BaseWebAppDriver
    </p>
    <p>
      WebAppDriver.java&#xfffd;
    </p>
    <p>
      WebModuleDriver
    </p>
  </body>
</html></richcontent>
<node CREATED="1496557708180" ID="ID_1139635991" MODIFIED="1496557763218" TEXT="Discuss setupWrapper method">
<cloud COLOR="#ff3333"/>
</node>
</node>
<node CREATED="1496557980787" FOLDED="true" ID="ID_1368048156" MODIFIED="1498338153035">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      BaseWebAppDriver
    </p>
    <p>
      BaseWebModuleDriver
    </p>
  </body>
</html></richcontent>
<node CREATED="1496557957799" ID="ID_1530107434" MODIFIED="1496557978070">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Merge contents of&#xfffd;
    </p>
    <p>
      basedrivers
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1496558728803" ID="ID_239622985" MODIFIED="1496558734622" TEXT="objectmap"/>
<node CREATED="1496557433447" FOLDED="true" ID="ID_27664705" MODIFIED="1498338153716" TEXT="selenium">
<node CREATED="1496557506069" ID="ID_934752546" MODIFIED="1496557664483">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      NgWebUIDriver.java
    </p>
    <p>
      TableHandler.java
    </p>
    <p>
      WebElementDriver.java
    </p>
    <p>
      WebUIDriver.java
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1496557804704" FOLDED="true" ID="ID_1725540781" MODIFIED="1498338153035" TEXT="browser">
<node CREATED="1496557816568" ID="ID_1596387073" MODIFIED="1496557843744">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Rename cbfx.browsers as
    </p>
    <p>
      cbfx.selenium.browser
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1496557855053" FOLDED="true" ID="ID_1230683887" MODIFIED="1498338153716">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      appium
    </p>
    <p>
      seetest
    </p>
  </body>
</html></richcontent>
<node CREATED="1496559110936" ID="ID_851698847" MODIFIED="1496559535910">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Compatibility with:
    </p>
    <ul>
      <li>
        Browser
      </li>
      <li>
        WebUIDriver
      </li>
    </ul>
  </body>
</html></richcontent>
<icon BUILTIN="help"/>
</node>
</node>
<node CREATED="1496558102906" FOLDED="true" ID="ID_238784229" MODIFIED="1498338153716">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      sikuli
    </p>
    <p>
      eggplant
    </p>
  </body>
</html></richcontent>
<node CREATED="1496559554529" ID="ID_11365559" MODIFIED="1496559604653">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Conventions for different tools/interfaces:
    </p>
    <ul>
      <li>
        POS
      </li>
      <li>
        LeanFT
      </li>
    </ul>
  </body>
</html></richcontent>
<icon BUILTIN="help"/>
</node>
</node>
<node CREATED="1496558203117" FOLDED="true" ID="ID_1154368688" MODIFIED="1498338153716" TEXT="utils">
<node CREATED="1496558206776" ID="ID_42586169" MODIFIED="1496558227339">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Move Autoit
    </p>
    <p>
      from cbf/utils
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1496558228499" ID="ID_513877789" MODIFIED="1496558234905" TEXT="Make all methods static"/>
<node CREATED="1496558236909" ID="ID_20667801" MODIFIED="1496558252031" TEXT="Check thread-safety">
<icon BUILTIN="help"/>
</node>
</node>
</node>
<node CREATED="1496558194364" ID="ID_1365675784" MODIFIED="1496558198333" TEXT="testng"/>
<node CREATED="1496559149562" FOLDED="true" ID="ID_664883476" MODIFIED="1498562012374" TEXT="factory">
<node CREATED="1496559156331" ID="ID_639444818" MODIFIED="1496559158503" TEXT="data"/>
<node CREATED="1496559158855" ID="ID_284325007" MODIFIED="1496559160102" TEXT="test"/>
<node CREATED="1496559160677" ID="ID_1603121889" MODIFIED="1496559162526" TEXT="reporting"/>
</node>
</node>
</node>
</node>
<node CREATED="1496558785225" ID="ID_880457466" MODIFIED="1498562012390" TEXT="model">
<icon BUILTIN="button_cancel"/>
<node CREATED="1496558791615" FOLDED="true" ID="ID_1883024680" MODIFIED="1498562012475">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Can there be a cbfx.ui.model?
    </p>
    <p>
      Make it extensible as in cbf.model&#xfffd;
    </p>
  </body>
</html></richcontent>
<node CREATED="1496558891276" ID="ID_614934947" MODIFIED="1496558928810">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      cbfx.ui.model with...
    </p>
    <ul>
      <li>
        Browser &lt;-- gives WebDriver
      </li>
      <li>
        ObjectMap
      </li>
    </ul>
  </body>
</html></richcontent>
</node>
<node CREATED="1496558905905" ID="ID_671751049" MODIFIED="1496558997623">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      cbfx.ui.factory with:
    </p>
    <ul>
      <li>
        cbfx.ui.factory.browser
      </li>
      <li>
        cbfx.ui.factory.objectmap
      </li>
    </ul>
  </body>
</html></richcontent>
</node>
<node CREATED="1496559044503" ID="ID_481496921" MODIFIED="1496559070566">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Maybe not a good idea:
    </p>
    <p>
      since this is not end user extensible
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</node>
<node CREATED="1496467741904" ID="ID_87660610" MODIFIED="1504008328448" TEXT="NgWebDriver">
<icon BUILTIN="button_ok"/>
<node CREATED="1496467747470" ID="ID_1436383332" MODIFIED="1496467751867" TEXT="Should be optional"/>
<node CREATED="1496467752574" ID="ID_1439270146" MODIFIED="1496467766169" TEXT="CN (without NG) should be default"/>
<node CREATED="1498476008194" ID="ID_1312386723" MODIFIED="1498476015614" TEXT="Review">
<icon BUILTIN="help"/>
</node>
</node>
<node CREATED="1496468297722" ID="ID_642681021" LINK="logger-fix/LogUtils.java" MODIFIED="1498338447133" TEXT="Logger fix">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1496468408760" ID="ID_856578405" LINK="ObjectMap%201705.mm" MODIFIED="1504008118877" TEXT="ObjectMap">
<node CREATED="1496468445883" ID="ID_1001156219" MODIFIED="1496468807211">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      At least rename
    </p>
    <p>
      and javadoc as specified
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</node>
</map>
