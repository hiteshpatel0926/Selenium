<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1417222984117" ID="ID_53466042" MODIFIED="1417223003141">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      CBF.java
    </p>
    <p>
      Latest review
    </p>
  </body>
</html></richcontent>
<node CREATED="1417223042465" ID="ID_1926719848" MODIFIED="1417223075248" POSITION="right" TEXT="application">
<node CREATED="1417223050771" ID="ID_1387806902" MODIFIED="1417227910349" TEXT="CompositeAppDriver">
<icon BUILTIN="closed"/>
<icon BUILTIN="closed"/>
<node CREATED="1417224331880" ID="ID_690991168" MODIFIED="1417224340617" TEXT="Dont know why it&apos;s required"/>
<node CREATED="1417224341186" ID="ID_265729280" MODIFIED="1417224381271">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      If it's specific to Web/Se,
    </p>
    <p>
      call it WebAppDriver
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1417228404702" ID="ID_1684779113" MODIFIED="1417228410897" TEXT="General code review needed"/>
</node>
<node CREATED="1417228187883" ID="ID_574662416" MODIFIED="1417509230896" TEXT="IConnectDriver">
<icon BUILTIN="closed"/>
<icon BUILTIN="closed"/>
<icon BUILTIN="closed"/>
<node CREATED="1417228212836" ID="ID_1406541311" MODIFIED="1417228221995" TEXT="Push all perform code to BaseModuleDriver"/>
<node CREATED="1417228282000" ID="ID_598770232" MODIFIED="1417228291627" TEXT="Rename as WebAppModuleDriver">
<node CREATED="1417228324900" ID="ID_1765549952" MODIFIED="1417228339035" TEXT="Copy Selenium stuff from BaseModuleDriver here"/>
<node CREATED="1417228339510" ID="ID_1461800024" MODIFIED="1417228344924" TEXT="Will need further cleanup"/>
</node>
<node CREATED="1417228418563" ID="ID_1586821542" MODIFIED="1417228442284" TEXT="Move all iconnect logic to Test folders"/>
</node>
</node>
<node CREATED="1417223181318" ID="ID_1221255516" MODIFIED="1417223693543" POSITION="right" TEXT="engine">
<node CREATED="1417223185179" ID="ID_742186251" MODIFIED="1417227906106" TEXT="BaseAppDriver">
<icon BUILTIN="closed"/>
<icon BUILTIN="closed"/>
<node CREATED="1417223190489" ID="ID_702770066" MODIFIED="1417223331304" TEXT="It should simply have a constructor taking (HashMap&lt;moduleCode, ModuleDriver&gt;)"/>
<node CREATED="1417223332372" ID="ID_1482316778" MODIFIED="1417223337396" TEXT="Rest of code is bogus">
<node CREATED="1417223337917" ID="ID_84972319" MODIFIED="1417223359184" TEXT="logger.handleError() is wrong"/>
<node CREATED="1417227779632" ID="ID_1146443722" MODIFIED="1417227794375" TEXT="Move selenium to application package"/>
</node>
</node>
<node CREATED="1417227893313" ID="ID_669897211" MODIFIED="1417227902580" TEXT="BaseModuleDriver">
<icon BUILTIN="closed"/>
<icon BUILTIN="closed"/>
<node CREATED="1417227918086" ID="ID_1081220032" MODIFIED="1417227940161" TEXT="Remove references to selenium"/>
<node CREATED="1417227942281" ID="ID_698132444" MODIFIED="1417227954201" TEXT="Should have the perform() logic here"/>
<node CREATED="1417228136199" ID="ID_326191193" MODIFIED="1417228142736" TEXT="Rest of code is bogus"/>
</node>
<node CREATED="1417238725194" ID="ID_1472499695" MODIFIED="1417238792335" TEXT="TestSetAccess">
<icon BUILTIN="info"/>
<node CREATED="1417238729039" ID="ID_1745877923" MODIFIED="1417238732441" TEXT="coding convention"/>
</node>
<node CREATED="1417238777303" ID="ID_473013150" MODIFIED="1417238796032" TEXT="AppLoader">
<icon BUILTIN="info"/>
<node CREATED="1417238781632" ID="ID_1033191150" MODIFIED="1417238788513" TEXT="Clean up error handling"/>
</node>
<node CREATED="1417238798800" ID="ID_1453589763" MODIFIED="1417238897238" TEXT="*Access">
<node CREATED="1417238839633" ID="ID_1868229497" MODIFIED="1417238844267" TEXT="See plugin stuff"/>
</node>
<node CREATED="1417238897900" ID="ID_205895301" MODIFIED="1417238932135" TEXT="PluginManager">
<icon BUILTIN="button_cancel"/>
<node CREATED="1417238904366" ID="ID_595434075" MODIFIED="1417238905833" TEXT="Remove"/>
</node>
</node>
<node CREATED="1417223694127" ID="ID_516668829" MODIFIED="1417223696952" POSITION="right" TEXT="utils">
<node CREATED="1417223699096" ID="ID_245561596" MODIFIED="1417228861462" TEXT="LogUtils">
<icon BUILTIN="button_ok"/>
<node CREATED="1417223710858" ID="ID_226151232" MODIFIED="1417224172101" TEXT="minor stuff"/>
</node>
<node CREATED="1417237114937" ID="ID_1988254503" MODIFIED="1417237156857" TEXT="SIngletons">
<icon BUILTIN="info"/>
<node CREATED="1417237127698" ID="ID_1395800459" MODIFIED="1417237138215" TEXT="use static where it makes sense"/>
<node CREATED="1417237138784" ID="ID_1090111319" MODIFIED="1417238067529" TEXT="UniqueUtils, StringUtils, SleepUtils"/>
<node CREATED="1417237867021" ID="ID_1881016972" MODIFIED="1417237872092" TEXT="E.g. Utils.java"/>
</node>
<node CREATED="1417237377637" ID="ID_1589800365" MODIFIED="1417237396475" TEXT="DBUtils">
<icon BUILTIN="info"/>
<node CREATED="1417237380718" ID="ID_251678283" MODIFIED="1417237390281" TEXT="Hardcoded database names">
<node CREATED="1417237426100" ID="ID_1540232571" MODIFIED="1417237455893" TEXT="Read it from configuration"/>
</node>
<node CREATED="1417237457756" ID="ID_7847923" MODIFIED="1417237462453" TEXT="Code needs revamping"/>
</node>
<node CREATED="1417237912666" ID="ID_1088390743" MODIFIED="1417302970425" TEXT="FileUtils">
<icon BUILTIN="info"/>
<node CREATED="1417237918852" ID="ID_1838848078" MODIFIED="1417237930459" TEXT="Make logger static"/>
<node CREATED="1417237931480" ID="ID_686537679" MODIFIED="1417237935459" TEXT="Enable error handling"/>
</node>
<node CREATED="1417238312158" ID="ID_1850202358" MODIFIED="1417238363622" TEXT="Expand documentation for Utils"/>
</node>
<node CREATED="1417225874460" ID="ID_1960091253" MODIFIED="1417225876364" POSITION="right" TEXT="harness">
<node CREATED="1417225166939" ID="ID_1671139667" MODIFIED="1417302934404" TEXT="ResourcePaths">
<icon BUILTIN="closed"/>
<icon BUILTIN="closed"/>
<icon BUILTIN="closed"/>
<node CREATED="1417225173015" ID="ID_525441373" MODIFIED="1417304609061" TEXT=" fix interface">
<node CREATED="1417303766497" ID="ID_1358811205" MODIFIED="1417303807849" TEXT="standardize paths">
<node CREATED="1417303813050" ID="ID_1674524010" MODIFIED="1417303891383" TEXT="getSuiteResource(relPath, relName)"/>
<node CREATED="1417303892123" ID="ID_1980384951" MODIFIED="1417303903263" TEXT="getFrameworkResource(relPath, relName)"/>
</node>
<node CREATED="1417303963353" ID="ID_435405639" MODIFIED="1417303977293" TEXT="fix constructors">
<node CREATED="1417303984975" ID="ID_1702268647" MODIFIED="1417303990795" TEXT="fix extra constructor"/>
<node CREATED="1417304092092" ID="ID_107672338" MODIFIED="1417304109331" TEXT="sAutoHome + sWorkHome etc."/>
</node>
<node CREATED="1417304122761" ID="ID_1851588456" MODIFIED="1417304263795" TEXT="remove getTestFile, getTestName"/>
<node CREATED="1417304609045" ID="ID_609865842" MODIFIED="1417304612617" TEXT="singleton">
<node CREATED="1417304601501" ID="ID_176723932" MODIFIED="1417304605792" TEXT="consider static method"/>
</node>
</node>
<node CREATED="1417303498167" ID="ID_1320246538" MODIFIED="1417303511477" TEXT="Usage">
<node CREATED="1417239023403" ID="ID_1033546547" MODIFIED="1417239036764">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Hard coded
    </p>
    <p>
      folder names
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="closed"/>
<node CREATED="1417239054259" ID="ID_104136712" MODIFIED="1417239065850" TEXT="src/lib: fix these">
<node CREATED="1417303080739" ID="ID_973546353" MODIFIED="1417303085483" TEXT="check entire code base"/>
</node>
</node>
<node CREATED="1417303515165" ID="ID_980435333" MODIFIED="1417303525166" TEXT="ExcelDataAccess">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1417304366411" ID="ID_65380731" MODIFIED="1417304372153" TEXT="ExcelTestCaseAccess">
<node CREATED="1417304432033" ID="ID_1062593792" MODIFIED="1417304509252" TEXT="getTestCase() should get name from map argument"/>
<node CREATED="1417304531699" ID="ID_1484625845" MODIFIED="1417304547597" TEXT="getSuiteResource() called to get folder path"/>
<node CREATED="1417304548446" ID="ID_1636099726" MODIFIED="1417304556387" TEXT="Remove Harness import"/>
</node>
<node CREATED="1417304215036" ID="ID_1845521506" MODIFIED="1417304220684" TEXT="ReporterFactory">
<node CREATED="1417304228507" ID="ID_214133562" MODIFIED="1417304242657" TEXT="Resources/ReportTemplate.xls"/>
</node>
<node CREATED="1417304665391" ID="ID_906488528" MODIFIED="1417304855365" TEXT="ManagingReporter">
<node CREATED="1417304673870" ID="ID_1387021538" MODIFIED="1417304867517" TEXT="Not using resourcePaths"/>
</node>
<node CREATED="1417305084544" ID="ID_444105919" MODIFIED="1417305086370" TEXT="Harness">
<node CREATED="1417305091899" ID="ID_1084421008" MODIFIED="1417305143010" TEXT="masterConfigFilePath = resourcePaths.getFrameworkResource(&quot;config&quot;, &quot;masterConfig.xml&quot;)"/>
<node CREATED="1417305182212" ID="ID_1158493653" MODIFIED="1417305206144" TEXT="configFilePath = resourcePaths.getSuiteResource(&quot;config&quot;, &quot;config.xml&quot;)"/>
</node>
<node CREATED="1417305274861" ID="ID_529784262" MODIFIED="1417305280291" TEXT="EmailAlerter">
<node CREATED="1417305303105" ID="ID_592907114" MODIFIED="1417305402517">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      vbsFilePath = resourcePaths.getFrameworkResource(&quot;utils&quot;, &quot;sendMail.vbs&quot;)
    </p>
    <p>
      vbsFilePath = FileUtils.toWindowsPath(vbsFilePath) // replace '/' by '\'
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</node>
<node CREATED="1417302879531" ID="ID_653271269" MODIFIED="1417302893805" TEXT="Harness">
<icon BUILTIN="closed"/>
<node CREATED="1417302899412" ID="ID_1798116831" MODIFIED="1417302919545" TEXT="logger.error">
<icon BUILTIN="button_cancel"/>
<node CREATED="1417302908718" ID="ID_1157491563" MODIFIED="1417302925005" TEXT="logger.handleError">
<icon BUILTIN="button_ok"/>
</node>
</node>
</node>
<node CREATED="1417227169842" ID="ID_672659060" MODIFIED="1417227248764" TEXT="Reorg plugin">
<icon BUILTIN="messagebox_warning"/>
<node CREATED="1417227185770" ID="ID_151590035" MODIFIED="1417227339345">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Move plugin stuff to
    </p>
    <p>
      new config package
    </p>
  </body>
</html></richcontent>
<node CREATED="1417227197477" ID="ID_1938090522" MODIFIED="1417227219701">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      engine.TestAccess
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1417227224389" ID="ID_1124394233" MODIFIED="1417227224389">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      
    </p>
    <p>
      engine.DataAccess
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1417227258302" ID="ID_1014665816" MODIFIED="1417227305961" TEXT="see deserializer for further changes"/>
</node>
</node>
<node CREATED="1417303641174" ID="ID_1579740804" MODIFIED="1417303643079" POSITION="right" TEXT="plugin">
<node CREATED="1417303647469" ID="ID_1729642417" MODIFIED="1417303672586" TEXT="Remove extra pluginmanager classes">
<icon BUILTIN="closed"/>
<icon BUILTIN="closed"/>
</node>
</node>
<node CREATED="1417226460100" ID="ID_367297976" MODIFIED="1417302941908" POSITION="right" TEXT="deserializer">
<icon BUILTIN="closed"/>
<node CREATED="1417226557249" ID="ID_581990969" MODIFIED="1417226571306" TEXT="move Excel/DesignerTestCase access here"/>
<node CREATED="1417226480739" ID="ID_793607889" MODIFIED="1417226496230" TEXT="move dataAccess classes to a new dataAccess package"/>
<node CREATED="1417226500192" ID="ID_158700879" MODIFIED="1417226509210" TEXT="after plugin implementation...">
<node CREATED="1417226511026" ID="ID_625443439" MODIFIED="1417226552188" TEXT="There should not be any import to plugin specific classes"/>
</node>
<node CREATED="1417226815961" ID="ID_1118827213" MODIFIED="1417587593804" TEXT="reorganize plugin folders">
<node CREATED="1417587600286" ID="ID_606673695" MODIFIED="1417587607275" TEXT="testAccess">
<node CREATED="1417587625215" ID="ID_170839404" MODIFIED="1417587630004" TEXT="ExcelAccess"/>
<node CREATED="1417587630355" ID="ID_1379396094" MODIFIED="1417587635722" TEXT="ExcelDeserializer"/>
<node CREATED="1417587641143" ID="ID_1190487357" MODIFIED="1417587645121" TEXT="DesignerAccess"/>
<node CREATED="1417587653280" ID="ID_1221740741" MODIFIED="1417587659005" TEXT="DesignerDeserializer"/>
</node>
<node CREATED="1417587607813" ID="ID_223773879" MODIFIED="1417587613242" TEXT="dataAccess">
<node CREATED="1417587616011" ID="ID_996103456" MODIFIED="1417587618460" TEXT="DbAccess"/>
<node CREATED="1417587619264" ID="ID_710734410" MODIFIED="1417587621947" TEXT="ExcelAccess"/>
</node>
<node CREATED="1417226832938" ID="ID_505133466" MODIFIED="1417227072124" TEXT="plugin">
<node CREATED="1417587673178" ID="ID_1348855395" MODIFIED="1417587677078" TEXT="PluginManager"/>
<node CREATED="1417587685159" ID="ID_1428032933" MODIFIED="1417587688810" TEXT="ReportingManager">
<node CREATED="1417587689988" ID="ID_1780805852" MODIFIED="1417587695198" TEXT="Might move to harness"/>
</node>
</node>
<node CREATED="1417587680440" ID="ID_264072500" MODIFIED="1417587682234" TEXT="reporting"/>
</node>
<node CREATED="1417226991664" ID="ID_206150251" MODIFIED="1417308354669" TEXT="Designer access plugin might be in a separate folder">
<node CREATED="1417227025853" ID="ID_1662486523" MODIFIED="1417227031688" TEXT="Not part of standard release"/>
</node>
</node>
<node CREATED="1417234449620" ID="ID_32746179" MODIFIED="1417234451602" POSITION="right" TEXT="reporting">
<node CREATED="1417234452888" ID="ID_1157648649" MODIFIED="1417234943966" TEXT="EmailAlerter">
<icon BUILTIN="info"/>
<node CREATED="1417234458091" ID="ID_587799685" MODIFIED="1417234833459" TEXT="all methods should be private"/>
<node CREATED="1417234523938" ID="ID_1777874107" MODIFIED="1417234556980" TEXT="sendMail content should be delegated to utils\MailCommand.vbs"/>
<node CREATED="1417234503557" ID="ID_1184676296" MODIFIED="1417234509735" TEXT="attachments: look dubious"/>
<node CREATED="1417234758165" ID="ID_1008803600" MODIFIED="1417234767401" TEXT="Error handling is bad"/>
<node CREATED="1417234768632" ID="ID_1991851278" MODIFIED="1417234773266" TEXT="Hard coded paths"/>
</node>
<node CREATED="1417235581718" ID="ID_765711398" MODIFIED="1417235685592" TEXT="ResultEventLogger">
<icon BUILTIN="closed"/>
<node CREATED="1417235587529" ID="ID_1456835089" MODIFIED="1417235594706" TEXT="method names starting in capital"/>
<node CREATED="1417235651224" ID="ID_272383026" MODIFIED="1417235656139" TEXT="dubious static members"/>
<node CREATED="1417235695840" ID="ID_1719023713" MODIFIED="1417235698181" TEXT="FIXME&apos;s"/>
<node CREATED="1417235735292" ID="ID_986469390" MODIFIED="1417235738741" TEXT="check logic">
<node CREATED="1417235740417" ID="ID_1844371795" MODIFIED="1417235744895" TEXT="kludgy stuff"/>
<node CREATED="1417235745229" ID="ID_1253611256" MODIFIED="1417235753608" TEXT="class.name.string() comparison"/>
</node>
</node>
<node CREATED="1417234946017" ID="ID_1746073482" MODIFIED="1417234983770" TEXT="plugin support">
<icon BUILTIN="info"/>
<node CREATED="1417234971569" ID="ID_35063142" MODIFIED="1417234979948" TEXT="Discuss..."/>
</node>
</node>
<node CREATED="1417224428978" ID="ID_1961453949" MODIFIED="1417239082964" POSITION="left">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      General
    </p>
    <p>
      cleanup
    </p>
  </body>
</html></richcontent>
<node CREATED="1417224755352" ID="ID_1686020592" MODIFIED="1417303057083" TEXT="Bootstrap">
<icon BUILTIN="info"/>
<node CREATED="1417224761412" ID="ID_288819451" MODIFIED="1417224955348" TEXT="remove G from globals">
<node CREATED="1417224767504" ID="ID_1449107377" MODIFIED="1417224791755">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      VBScript is case insensitive.
    </p>
    <p>
      G was added to make global unique
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1417224955713" ID="ID_138953771" MODIFIED="1417224974287" TEXT="Have a global Config">
<node CREATED="1417224984200" ID="ID_1297233455" MODIFIED="1417224991798" TEXT="new package config"/>
<node CREATED="1417224993443" ID="ID_939853329" MODIFIED="1417225010713" TEXT="new class config.Configuration"/>
<node CREATED="1417225012187" ID="ID_1550797856" MODIFIED="1417225035057" TEXT="has static method Config.get(key)"/>
</node>
<node CREATED="1417225048348" ID="ID_1449976935" MODIFIED="1417225056632" TEXT="Harness will bootstrap it.">
<node CREATED="1417225093494" ID="ID_881741133" MODIFIED="1417225104524" TEXT="read Config.xml using ConfigUtils etc."/>
</node>
<node CREATED="1417225063355" ID="ID_1225163929" MODIFIED="1417225087138" TEXT="You can merge present plugin package with that">
<icon BUILTIN="help"/>
</node>
</node>
<node CREATED="1417224685441" ID="ID_245157659" MODIFIED="1417239235837" TEXT="imports">
<icon BUILTIN="info"/>
<node CREATED="1417224434883" ID="ID_497256041" MODIFIED="1417224445694" TEXT="Remove unused imports"/>
<node CREATED="1417224446824" ID="ID_1213264042" MODIFIED="1417224457449" TEXT="Remove circular imports"/>
<node CREATED="1417224459187" ID="ID_1413752605" MODIFIED="1417224700456" TEXT="Ensure hierarchy of imports">
<node CREATED="1417224642167" MODIFIED="1417224642167" TEXT="application"/>
<node CREATED="1417224642182" MODIFIED="1417224642182" TEXT="harness"/>
<node CREATED="1417224642182" MODIFIED="1417224642182" TEXT="plugin"/>
<node CREATED="1417224642182" MODIFIED="1417224642182" TEXT="deserializer"/>
<node CREATED="1417224642182" MODIFIED="1417224642182" TEXT="reporting"/>
<node CREATED="1417224642182" ID="ID_808158562" MODIFIED="1417224642182" TEXT="selenium"/>
<node CREATED="1417224642182" ID="ID_47685673" MODIFIED="1417224642182" TEXT="engine"/>
<node CREATED="1417224642182" ID="ID_1640859028" MODIFIED="1417224642182" TEXT="utils"/>
</node>
</node>
<node CREATED="1417225417871" ID="ID_333458588" MODIFIED="1417305617446" TEXT="Error handling">
<icon BUILTIN="closed"/>
<icon BUILTIN="closed"/>
<node CREATED="1417225430601" ID="ID_441132294" MODIFIED="1417225592272" TEXT="logger.handleError(&quot;Error in loading AppDriver:&quot; + startUp, e.getMessage());">
<icon BUILTIN="button_cancel"/>
<node CREATED="1417225441045" ID="ID_815203338" MODIFIED="1417225595720">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      logger.handleError(&quot;Loading AppDriver&quot; + startUp, e);
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
</node>
</node>
<node CREATED="1417225520558" ID="ID_1086522902" MODIFIED="1417225600868" TEXT="Avoid generic catch(Exception)">
<icon BUILTIN="button_cancel"/>
<node CREATED="1417308160343" MODIFIED="1417308160343" TEXT="./deserializer/ExcelDataAccess.java: throw/catch issues"/>
<node CREATED="1417308160343" MODIFIED="1417308160343" TEXT="./engine/TestResult.java: minor"/>
<node CREATED="1417308160343" MODIFIED="1417308160343" TEXT="./engine/TestResultTracker.java: bad catch"/>
<node CREATED="1417308160343" MODIFIED="1417308160343" TEXT="./harness/AppLoader.java: log issue"/>
<node CREATED="1417308160343" MODIFIED="1417308160343" TEXT="./harness/ExcelTestCaseAccess.java: catch issue: FrameworkException"/>
<node CREATED="1417308160359" MODIFIED="1417308160359" TEXT="./harness/Harness.java: catch issues; log issues"/>
<node CREATED="1417308160359" MODIFIED="1417308160359" TEXT="./harness/ParameterAccess.java: log issue: log warning"/>
<node CREATED="1417308160359" MODIFIED="1417308160359" TEXT="./plugin/PluginManager.java: log issue"/>
<node CREATED="1417308160359" MODIFIED="1417308160359" TEXT="./reporting/HtmlEventReporter.java: catch issue; it is a FIXME"/>
<node CREATED="1417308160359" MODIFIED="1417308160359" TEXT="./reporting/ManagingReporter.java: log issue"/>
<node CREATED="1417308160359" MODIFIED="1417308160359" TEXT="./reporting/ResultEventLogger.java: minor fix in log text"/>
<node CREATED="1417308160359" MODIFIED="1417308160359" TEXT="./reporting/ScreenDumpManager.java: log issue"/>
<node CREATED="1417308160374" MODIFIED="1417308160374" TEXT="./selenium/ObjectMap.java: log issue"/>
<node CREATED="1417308160374" MODIFIED="1417308160374" TEXT="./selenium/WebUIDriver.java: logic in getwebDriverLocator"/>
<node CREATED="1417308160374" MODIFIED="1417308160374" TEXT="./utils/DBUtils.java: both logic and catch issues"/>
<node CREATED="1417308160374" MODIFIED="1417308160374" TEXT="./utils/PersistentMap.java: catch issue"/>
</node>
<node CREATED="1417234092001" ID="ID_1605861801" MODIFIED="1417305753433" TEXT="Avoid generic throw(): call handleError">
<icon BUILTIN="button_cancel"/>
<node CREATED="1417234104567" ID="ID_1985289578" MODIFIED="1417234145744" TEXT="import FrameworkException should not be usually needed"/>
<node CREATED="1417305753386" ID="ID_776388020" MODIFIED="1417305757037" TEXT="check these">
<node CREATED="1417305714792" ID="ID_530657910" MODIFIED="1417305714792" TEXT="./deserializer/ExcelDataAccess.java"/>
<node CREATED="1417305714792" ID="ID_1599487225" MODIFIED="1417305714792" TEXT="./deserializer/ExcelDeserializer.java"/>
<node CREATED="1417305714792" ID="ID_540015260" MODIFIED="1417305714792" TEXT="./engine/TestResultTracker.java"/>
<node CREATED="1417305714792" ID="ID_341459029" MODIFIED="1417305714792" TEXT="./reporting/HtmlEventReporter.java"/>
<node CREATED="1417305714792" ID="ID_250453268" MODIFIED="1417305714792" TEXT="./selenium/WebUIDriver.java"/>
<node CREATED="1417305714792" ID="ID_1824500392" MODIFIED="1417305714792" TEXT="./utils/Configuration.java"/>
<node CREATED="1417305714807" ID="ID_1560018557" MODIFIED="1417305714807" TEXT="./utils/ExcelAccess.java"/>
</node>
</node>
</node>
<node CREATED="1417225551914" ID="ID_1077721887" MODIFIED="1417225555565" TEXT="Configuration">
<node CREATED="1417225584238" ID="ID_872257648" MODIFIED="1417225605938" TEXT="Configuration GCONFIG = Harness.GCONFIG;">
<icon BUILTIN="button_cancel"/>
<node CREATED="1417225611467" ID="ID_411652652" MODIFIED="1417225782522" TEXT="Configuration.get()">
<icon BUILTIN="button_ok"/>
</node>
</node>
</node>
<node CREATED="1417225761110" ID="ID_237112591" MODIFIED="1417225763451" TEXT="Logging">
<node CREATED="1417225768381" ID="ID_285251225" MODIFIED="1417225844977" TEXT="logger.debug(&quot;AppDriverBootStrap: &quot; + startUp);">
<icon BUILTIN="info"/>
<node CREATED="1417225776095" ID="ID_750052818" MODIFIED="1417225812989">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      logger.debug(&quot;AppDriverBootStrap&quot;, startUp);
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
</node>
</node>
</node>
<node CREATED="1417225982516" ID="ID_10505039" MODIFIED="1417226354200" TEXT="toString">
<icon BUILTIN="info"/>
<node CREATED="1417225986985" ID="ID_558741885" MODIFIED="1417226330963" TEXT="super.toString() + &quot;{&quot; + specific params + &quot;}&quot;"/>
<node CREATED="1417226332873" ID="ID_1233116786" MODIFIED="1417226345916" TEXT="dont override if params are not there">
<node CREATED="1417234249057" ID="ID_755548720" MODIFIED="1417234257762" TEXT="Check how things look"/>
</node>
</node>
<node CREATED="1417227653117" ID="ID_675014723" MODIFIED="1417227658937" TEXT="Code stuff">
<node CREATED="1417227660363" ID="ID_383286130" MODIFIED="1417227665559" TEXT="Unused variables"/>
<node CREATED="1417227700596" ID="ID_630170168" MODIFIED="1417227708052" TEXT="Conventions">
<node CREATED="1417227710118" ID="ID_447731698" MODIFIED="1417227713145" TEXT="GCONFIG"/>
<node CREATED="1417227715274" ID="ID_1396863882" MODIFIED="1417227719752" TEXT="Capital vs small"/>
</node>
</node>
<node CREATED="1417228816127" ID="ID_1368942208" MODIFIED="1417238688168" TEXT="engine">
<node CREATED="1417228827484" ID="ID_1913708665" MODIFIED="1417228848335" TEXT="AppDriver">
<icon BUILTIN="closed"/>
<node CREATED="1417228831470" ID="ID_33572749" MODIFIED="1417228836744" TEXT="remove initialize() method"/>
<node CREATED="1417228665033" ID="ID_777260124" MODIFIED="1417228670870" TEXT="BuiltinComponentsDriver">
<node CREATED="1417228674090" ID="ID_1040190840" MODIFIED="1417228685214" TEXT="Reuse new BaseModuleDriver"/>
</node>
</node>
<node CREATED="1417228939328" ID="ID_1450838806" MODIFIED="1417303031655" TEXT="DataAccess">
<icon BUILTIN="closed"/>
<node CREATED="1417228947542" ID="ID_908808746" MODIFIED="1417228966216" TEXT="Remove getModuleAccess()"/>
</node>
<node CREATED="1417233722006" ID="ID_221300486" MODIFIED="1417233728264" TEXT="ParameterAccess">
<icon BUILTIN="info"/>
<node CREATED="1417233729923" ID="ID_570329044" MODIFIED="1417233734886" TEXT="Explore simplification"/>
<node CREATED="1417233735469" ID="ID_1922800853" MODIFIED="1417233740869" TEXT="Explore making it optional"/>
</node>
<node CREATED="1417233788416" ID="ID_1228960180" MODIFIED="1417233899677" TEXT="TestResult">
<icon BUILTIN="messagebox_warning"/>
<node CREATED="1417233793089" ID="ID_1801774761" MODIFIED="1417233847604" TEXT="add toString()">
<node CREATED="1417233799056" ID="ID_173150374" MODIFIED="1417233808152" TEXT="Should print both type &amp; name"/>
<node CREATED="1417233852681" ID="ID_1232330045" MODIFIED="1417233858049" TEXT="Will be logged several times"/>
</node>
</node>
<node CREATED="1417233901010" ID="ID_1663496627" MODIFIED="1417234033042" TEXT="TestResultLogger">
<icon BUILTIN="info"/>
<node CREATED="1417233909551" ID="ID_64089089" MODIFIED="1417233913873" TEXT="add logger"/>
<node CREATED="1417233914223" ID="ID_1836147099" MODIFIED="1417233932180" TEXT="add logger.trace(...) to the log"/>
<node CREATED="1417233932655" ID="ID_1330259497" MODIFIED="1417233941704" TEXT="no need to override toString"/>
</node>
<node CREATED="1417227425598" ID="ID_1618065580" MODIFIED="1417227449795" TEXT="ExcelTestSet">
<icon BUILTIN="info"/>
<node CREATED="1417227430037" ID="ID_1793728817" MODIFIED="1417227444187" TEXT="Should move to a custom plugin folder"/>
<node CREATED="1417238675032" ID="ID_1049917093" MODIFIED="1417238679838" TEXT="Make that configurable"/>
</node>
</node>
<node CREATED="1417237111326" ID="ID_1544082775" MODIFIED="1417237113168" TEXT="utils">
<node CREATED="1417237261533" ID="ID_1360015625" MODIFIED="1417238136224" TEXT="Substitutor">
<icon BUILTIN="info"/>
<node CREATED="1417238126809" ID="ID_250241756" MODIFIED="1417238130569" TEXT="add logger"/>
</node>
<node CREATED="1417238192215" ID="ID_204876858" MODIFIED="1417238245568" TEXT="ExcelAccess">
<icon BUILTIN="idea"/>
<node CREATED="1417238197418" ID="ID_1497001704" MODIFIED="1417238201054" TEXT="add logger"/>
<node CREATED="1417238201560" ID="ID_420106872" MODIFIED="1417238236879">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      check fixing excelreporter/reader
    </p>
    <p>
      why one is using jxl, other poi?
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1417224258802" ID="ID_1058191162" MODIFIED="1417225892058" TEXT="MongoDBUtils">
<icon BUILTIN="info"/>
<node CREATED="1417224264925" ID="ID_1532828883" MODIFIED="1417224270479" TEXT="Needs big fixes"/>
<node CREATED="1417224270939" ID="ID_1643709281" MODIFIED="1417224276743" TEXT="Low priority for now"/>
</node>
<node CREATED="1417238006267" ID="ID_1779145268" MODIFIED="1417308354669" TEXT="FrameworkException">
<icon BUILTIN="info"/>
<node CREATED="1417238010799" ID="ID_666945675" MODIFIED="1417238014419" TEXT="fix toString()"/>
</node>
<node CREATED="1417238441178" ID="ID_602050206" MODIFIED="1417238457286" TEXT="LogUtils">
<icon BUILTIN="closed"/>
<node CREATED="1417238459648" ID="ID_586897348" MODIFIED="1417303025399" TEXT="Consider renaming to Logger"/>
<node CREATED="1417238468688" ID="ID_645197253" MODIFIED="1417238479406" TEXT="Remove hard coding of the 2 types of loggers"/>
</node>
</node>
</node>
</node>
</map>
