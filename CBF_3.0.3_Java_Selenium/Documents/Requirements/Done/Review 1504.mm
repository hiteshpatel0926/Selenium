<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1428488013776" ID="ID_460201841" MODIFIED="1428488021406" TEXT="Code Review">
<node CREATED="1428488023563" ID="ID_1774399127" MODIFIED="1430993032471" POSITION="right" TEXT="data">
<node CREATED="1428488216232" FOLDED="true" ID="ID_1929406500" MODIFIED="1430993277648" TEXT="DBAccess">
<icon BUILTIN="full-2"/>
<node CREATED="1428491801541" ID="ID_1835504281" MODIFIED="1430993236472">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      "Select * from " + tableName + " where <font color="#ff3333"><b>[_rowId]='?", new ArrayList&lt;Object&gt;([rowSelector])</b></font>)
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1428492179998" ID="ID_514794384" MODIFIED="1430993238282" TEXT="selectRows">
<icon BUILTIN="button_ok"/>
<node CREATED="1428492184499" ID="ID_1249790649" MODIFIED="1428492198758" TEXT="It should checkExists only if Select * returns error"/>
</node>
</node>
<node CREATED="1428492366162" FOLDED="true" ID="ID_1389612475" MODIFIED="1430993286914" TEXT="ExcelAccess">
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428492711211" ID="ID_587908714" MODIFIED="1428557252444" TEXT="selectRows return null instead of throwing error if file or sheet doesnt exist">
<icon BUILTIN="button_ok"/>
</node>
</node>
</node>
<node CREATED="1428492490207" ID="ID_1539963007" MODIFIED="1430993288037" POSITION="right" TEXT="harness">
<node CREATED="1428492493109" FOLDED="true" ID="ID_1877300792" MODIFIED="1430993303262" TEXT="ResourcePaths">
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428492498524" ID="ID_189542358" MODIFIED="1428559165644" TEXT="ResourcePaths.singleton should be private. Present one is error prone">
<icon BUILTIN="button_ok"/>
</node>
</node>
<node CREATED="1428494112248" ID="ID_611633026" MODIFIED="1430993293341" TEXT="AppLoader">
<node CREATED="1428494202955" FOLDED="true" ID="ID_1000107951" MODIFIED="1428559228472" TEXT="Let it accept Config as loadApp() parameter">
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428494234070" ID="ID_1382881888" MODIFIED="1428494239273" TEXT="Dont access GCONFIG directly"/>
</node>
<node CREATED="1428494117037" FOLDED="true" ID="ID_58058977" MODIFIED="1428494245905" TEXT="Make it use PluginManager">
<icon BUILTIN="full-2"/>
<node CREATED="1428494166278" ID="ID_322379537" MODIFIED="1428494176503" TEXT="Config is passed as constructor"/>
<node CREATED="1428494176848" ID="ID_1317397067" MODIFIED="1428494192192" TEXT="PluginManager is used to loading?"/>
</node>
</node>
<node CREATED="1428494436973" FOLDED="true" ID="ID_551397630" MODIFIED="1430993340605" TEXT="TestInstanceAccess">
<node CREATED="1428494444611" ID="ID_1012078026" MODIFIED="1428494465987" TEXT="testInanceInfo is dirtly. Easier way of handling...">
<icon BUILTIN="full-2"/>
</node>
<node CREATED="1428494584893" ID="ID_1649150686" MODIFIED="1428494601279" TEXT="testCase() method returns null. Check design">
<icon BUILTIN="full-1"/>
</node>
</node>
<node CREATED="1428494613988" FOLDED="true" ID="ID_1837435586" MODIFIED="1430993333227" TEXT="Harness">
<icon BUILTIN="button_cancel"/>
<node CREATED="1428494617519" ID="ID_397338853" MODIFIED="1428494628906" TEXT="Needs significant refactoring and fixing"/>
<node CREATED="1428494707814" ID="ID_656180099" MODIFIED="1428494783086" TEXT="TestSetRunner vs TestLinkRunner vs JenkinsRunner">
<icon BUILTIN="full-1"/>
</node>
<node CREATED="1428494762656" ID="ID_630776926" MODIFIED="1428494779270" TEXT="Move initialization routines to a new Initializer class">
<icon BUILTIN="full-2"/>
</node>
<node CREATED="1428494655380" ID="ID_1918035399" MODIFIED="1428494678250" TEXT="InterTcDelay should be in TestSet runner">
<icon BUILTIN="full-1"/>
</node>
<node CREATED="1428495859845" ID="ID_1009549421" MODIFIED="1428495875789" TEXT="getTestCase() should call the Plugin!">
<icon BUILTIN="full-1"/>
</node>
<node CREATED="1428495896519" ID="ID_76109771" MODIFIED="1428495920862" TEXT="make GCONFIG private">
<icon BUILTIN="full-2"/>
<icon BUILTIN="help"/>
</node>
<node CREATED="1428496408301" ID="ID_590073053" MODIFIED="1428496423741" TEXT="AppDriver.recover() should be called in runTest?">
<icon BUILTIN="full-2"/>
</node>
</node>
</node>
<node CREATED="1428493248362" FOLDED="true" ID="ID_976717808" MODIFIED="1428493801813" POSITION="right" TEXT="plugin">
<icon BUILTIN="full-2"/>
<node CREATED="1428493801794" FOLDED="true" ID="ID_1243022980" MODIFIED="1428554315745" TEXT="PluginManager">
<icon BUILTIN="full-2"/>
<node CREATED="1428493251665" ID="ID_1179518228" MODIFIED="1428493262286" TEXT="masterConfigFileName shouldnt be a member"/>
<node CREATED="1428493575716" ID="ID_1004646670" MODIFIED="1428493588567" TEXT="initializePlugin: exception messages"/>
</node>
<node CREATED="1428493806262" FOLDED="true" ID="ID_1331997185" MODIFIED="1428910037530" TEXT="ReportingManager">
<icon BUILTIN="button_ok"/>
<node CREATED="1428493810276" ID="ID_416868352" MODIFIED="1428493829402" TEXT="traverseArray: classCastException is not needed?">
<icon BUILTIN="full-2"/>
</node>
</node>
</node>
<node CREATED="1428489130892" ID="ID_1239327290" MODIFIED="1430993374423" POSITION="right" TEXT="utils">
<node CREATED="1428489133526" FOLDED="true" ID="ID_1839549702" MODIFIED="1428915400434" TEXT="MongoDBUtils">
<icon BUILTIN="yes"/>
<icon BUILTIN="full-3"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428489138228" ID="ID_1749167428" MODIFIED="1428559428907" TEXT="Name is incorrect. It is DesignerAccess">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1428489151580" FOLDED="true" ID="ID_1073899898" MODIFIED="1428915403129" TEXT="Method should be...">
<icon BUILTIN="button_ok"/>
<node CREATED="1428489196272" ID="ID_1366383352" MODIFIED="1428489209275" TEXT="Constructor(params)"/>
<node CREATED="1428489289703" FOLDED="true" ID="ID_1880761107" MODIFIED="1428489302601" TEXT="access(input)">
<node CREATED="1428489304867" ID="ID_1076045988" MODIFIED="1428489310374" TEXT="connect(params)"/>
<node CREATED="1428489311399" ID="ID_995864822" MODIFIED="1428489335251" TEXT="sendRequest(input-&gt;json)"/>
<node CREATED="1428489336380" ID="ID_1613169215" MODIFIED="1428489345871" TEXT="convert output back to json"/>
<node CREATED="1428489347575" ID="ID_1849374737" MODIFIED="1428489352032" TEXT="return json"/>
</node>
</node>
</node>
<node CREATED="1428492099478" FOLDED="true" ID="ID_1986526301" MODIFIED="1430993383220" TEXT="DBUtils">
<icon BUILTIN="button_ok"/>
<node CREATED="1428492103000" FOLDED="true" ID="ID_563440111" MODIFIED="1428910062772" TEXT="checkExists is inefficient.">
<icon BUILTIN="full-3"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428492120473" ID="ID_589006140" MODIFIED="1428492127853" TEXT="reads entire catalog"/>
</node>
<node CREATED="1428492272918" FOLDED="true" ID="ID_344380283" MODIFIED="1428559585426" TEXT="getConnection()">
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428492279584" ID="ID_1859280521" MODIFIED="1428492287547" TEXT="doesnt log connection parameters"/>
</node>
</node>
<node CREATED="1428493367066" FOLDED="true" ID="ID_1670125994" MODIFIED="1430993396885" TEXT="Configuration">
<node CREATED="1428493370545" ID="ID_624710322" MODIFIED="1428493429870" TEXT="FileName should be in toString">
<icon BUILTIN="full-2"/>
</node>
<node CREATED="1428493380462" ID="ID_511888357" MODIFIED="1428559768374" TEXT="logger.trace(&apos;loading configuration from&quot;, configFileName)">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1428493402460" ID="ID_442173757" MODIFIED="1428559765304" TEXT="load() error doesnt contain file name">
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
</node>
</node>
<node CREATED="1428496265840" FOLDED="true" ID="ID_966096880" MODIFIED="1430993401065" TEXT="UniqueUtils">
<icon BUILTIN="full-3"/>
<node CREATED="1428496270576" ID="ID_1232551797" MODIFIED="1428496273398" TEXT="has bug?"/>
<node CREATED="1428496274231" ID="ID_627159648" MODIFIED="1428496297212" TEXT="need to create UT"/>
</node>
<node CREATED="1428496280433" FOLDED="true" ID="ID_1699912987" MODIFIED="1430993403015" TEXT="StringUtils">
<icon BUILTIN="full-3"/>
<node CREATED="1428496284234" ID="ID_1247637571" MODIFIED="1428496300078" TEXT="need to create UT"/>
</node>
<node CREATED="1428498678341" FOLDED="true" ID="ID_1736581347" MODIFIED="1430993408989" TEXT="Utils">
<node CREATED="1428498680925" FOLDED="true" ID="ID_1967432594" MODIFIED="1428499675514" TEXT="stringToBool should accept a default">
<icon BUILTIN="full-2"/>
<node CREATED="1428498692233" ID="ID_1341526987" MODIFIED="1428498707547" TEXT="it returns false if nothing matches"/>
<node CREATED="1428498712813" ID="ID_446344887" MODIFIED="1428498742969" TEXT="it should throw exception if it is neither True nor False"/>
<node CREATED="1428498745806" ID="ID_1508741620" MODIFIED="1428554748291" TEXT="it should return default if blank or null"/>
</node>
</node>
<node CREATED="1428497668882" FOLDED="true" ID="ID_703937898" MODIFIED="1428665176202" TEXT="*.getInstance">
<icon BUILTIN="full-3"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428497678265" FOLDED="true" ID="ID_225005178" MODIFIED="1428497686259" TEXT="Convert these calls to static calls">
<node CREATED="1428497693776" ID="ID_1678350377" MODIFIED="1428497704761" TEXT="SleepUtils.getInstance().sleep()"/>
<node CREATED="1428501561579" ID="ID_1174405876" MODIFIED="1428501564125" TEXT="HtmlUtils"/>
<node CREATED="1428501607417" ID="ID_412426824" MODIFIED="1428501610907" TEXT="JSonUtils"/>
</node>
<node CREATED="1428555104453" FOLDED="true" ID="ID_1468052327" MODIFIED="1428555114874" TEXT="Make a private default constructor">
<node CREATED="1428555115991" ID="ID_862579106" MODIFIED="1428555124246" TEXT="prevent accidental construction"/>
<node CREATED="1428555139079" ID="ID_631705716" MODIFIED="1428555142802" TEXT="Also: FileUtils"/>
</node>
</node>
<node CREATED="1428499659133" FOLDED="true" ID="ID_1291810332" MODIFIED="1428562474951" TEXT="DTAccess">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428500078606" ID="ID_533519301" MODIFIED="1428500085525" TEXT="See ExcelAccess changes"/>
<node CREATED="1428500132392" ID="ID_1461461141" MODIFIED="1428500155181" TEXT="Uniform processing of BiffException etc. Shouldnt have throws in its declaration"/>
</node>
<node CREATED="1428499771734" FOLDED="true" ID="ID_1254142264" MODIFIED="1428562971299" TEXT="ExcelAccess">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428499802857" FOLDED="true" ID="ID_682683684" MODIFIED="1428499813446" TEXT="should implement isSheetExists">
<node CREATED="1428499794707" ID="ID_596613082" MODIFIED="1428499801385" TEXT="DTAccess also will change"/>
</node>
</node>
<node CREATED="1428501291783" FOLDED="true" ID="ID_1520227715" MODIFIED="1428665184039" TEXT="FileUtils">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428501295300" FOLDED="true" ID="ID_1370797451" MODIFIED="1428501299586" TEXT="fileList">
<node CREATED="1428501300707" ID="ID_438909648" MODIFIED="1428563189054" TEXT="make it static"/>
<node CREATED="1428501315554" ID="ID_364073292" MODIFIED="1428501328829" TEXT="throw new RuntimeException(&quot;NotImplemented&quot;)"/>
</node>
<node CREATED="1428501353941" FOLDED="true" ID="ID_980672225" MODIFIED="1428501398926" TEXT="getTempPath">
<node CREATED="1428501315554" ID="ID_475324616" MODIFIED="1428501328829" TEXT="throw new RuntimeException(&quot;NotImplemented&quot;)"/>
</node>
</node>
<node CREATED="1428501664179" FOLDED="true" ID="ID_35048621" MODIFIED="1430993424307" TEXT="LogUtils">
<icon BUILTIN="full-3"/>
<icon BUILTIN="button_cancel"/>
<node CREATED="1428501670881" ID="ID_415530346" MODIFIED="1428501677012" TEXT="CSV support?"/>
</node>
<node CREATED="1428501886076" FOLDED="true" ID="ID_821416675" MODIFIED="1430993430126" TEXT="RegExUtils">
<icon BUILTIN="full-4"/>
<node CREATED="1428501890180" ID="ID_1899901267" MODIFIED="1428501894772" TEXT="check this"/>
</node>
</node>
<node CREATED="1428496453431" ID="ID_1719654164" MODIFIED="1430993432044" POSITION="right" TEXT="engine">
<node CREATED="1428496456335" FOLDED="true" ID="ID_1115426218" MODIFIED="1428565234283" TEXT="AppDriver">
<icon BUILTIN="button_ok"/>
<node CREATED="1428496459412" ID="ID_1885655534" MODIFIED="1428496575562" TEXT="shouldnt have initializeModules">
<icon BUILTIN="full-2"/>
</node>
</node>
<node CREATED="1428496516310" FOLDED="true" ID="ID_493222555" MODIFIED="1428665280001" TEXT="BaseAppDriver">
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428496523584" FOLDED="true" ID="ID_973097871" MODIFIED="1428575674330" TEXT="should have initializeModules">
<icon BUILTIN="full-2"/>
<node CREATED="1428496554353" ID="ID_1899246183" MODIFIED="1428496570773" TEXT="initialize should return void"/>
<node CREATED="1428496869629" ID="ID_1509798725" MODIFIED="1428496893637" TEXT="rename as loadModuleDrivers(resultLogger, Map&lt;String, ModuleDriver&gt;)"/>
</node>
<node CREATED="1428496613288" ID="ID_94534932" MODIFIED="1428496636756" TEXT="RESULT = resultLogger should be removed">
<icon BUILTIN="full-2"/>
</node>
<node CREATED="1428496668958" FOLDED="true" ID="ID_1182040535" MODIFIED="1428496686832" TEXT="Remove all unused members">
<node CREATED="1428496688910" ID="Freemind_Link_722720406" MODIFIED="1428496688910" TEXT="public static WebUIDriver uiDriver;"/>
<node CREATED="1428496688912" ID="ID_884694147" MODIFIED="1428496688912" TEXT="public static ObjectMap objMap;"/>
<node CREATED="1428496688913" ID="ID_1865644837" MODIFIED="1428496688913" TEXT="public static WebDriver webDriver;"/>
<node CREATED="1428496688914" ID="ID_1651754084" MODIFIED="1428496688914" TEXT="public static Screen sikuli;"/>
<node CREATED="1428496688915" ID="ID_1630317234" MODIFIED="1428496688915" TEXT="public static SikuliUIDriver sikuliUIDriver;"/>
</node>
</node>
<node CREATED="1428497529706" FOLDED="true" ID="ID_1898627438" MODIFIED="1428665324459" TEXT="BaseModuleDriver">
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428497535150" ID="ID_938346709" MODIFIED="1428497549639" TEXT="Remove all unused members to new BaseWebModuleDriver"/>
</node>
<node CREATED="1428497744334" FOLDED="true" ID="ID_195526542" MODIFIED="1428574726936" TEXT="BuiltinComponentsDriver">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428497752002" FOLDED="true" ID="ID_777960524" MODIFIED="1428574723545" TEXT="This should extend BaseModuleDriver">
<node CREATED="1428497825430" ID="ID_1901231893" MODIFIED="1428497832568" TEXT="Will not need perform or eval"/>
</node>
<node CREATED="1428497806477" ID="ID_487726048" MODIFIED="1428497812205" TEXT="fail() is unimplemented"/>
</node>
<node CREATED="1428497955256" FOLDED="true" ID="ID_1890438585" MODIFIED="1428498142451" TEXT="TestCaseRunner">
<icon BUILTIN="full-2"/>
<node CREATED="1428497959665" FOLDED="true" ID="ID_895282909" MODIFIED="1428498147564" TEXT="Check if u can avoid hardcoding BuiltinComponentsDriver">
<icon BUILTIN="full-3"/>
<node CREATED="1428497975356" ID="ID_161999455" MODIFIED="1428497989201" TEXT="This can be an option by loadModules"/>
</node>
<node CREATED="1428498040192" ID="ID_1989342636" MODIFIED="1428574750993" TEXT="rename RPT to RESULT">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
</node>
</node>
<node CREATED="1428498362493" FOLDED="true" ID="ID_1957497333" MODIFIED="1428498389332" TEXT="TestInstance">
<icon BUILTIN="full-2"/>
<node CREATED="1428498366293" ID="ID_170281022" MODIFIED="1428498376614" TEXT="Check interface... Many unused"/>
</node>
<node CREATED="1428498448285" FOLDED="true" ID="ID_591082955" MODIFIED="1428498472940" TEXT="TestResultLogger">
<icon BUILTIN="full-4"/>
<node CREATED="1428498455009" ID="ID_1987179675" MODIFIED="1428498469801" TEXT="add a new CheckPoint() method as in VBScript"/>
</node>
</node>
<node CREATED="1428497332548" FOLDED="true" ID="ID_1159950035" MODIFIED="1428638718517" POSITION="right" TEXT="ui">
<node CREATED="1428497335045" FOLDED="true" ID="ID_1902756598" MODIFIED="1428576548527" TEXT="new BaseWebAppDriver">
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428496725923" ID="ID_1533301102" MODIFIED="1428576545863" TEXT="extending BaseAppDriver"/>
<node CREATED="1428496975083" FOLDED="true" ID="ID_656375887" MODIFIED="1428576543480" TEXT="Move UI stuff from BaseAppDriver here">
<node CREATED="1428496736571" ID="ID_529275583" MODIFIED="1428496748774" TEXT="all Selenium initializations"/>
<node CREATED="1428496778215" ID="ID_1082001826" MODIFIED="1428496785362" TEXT="object maps"/>
<node CREATED="1428496785740" ID="ID_161749079" MODIFIED="1428496789334" TEXT="SikuliDriver etc."/>
</node>
<node CREATED="1428496986958" FOLDED="true" ID="ID_682224604" MODIFIED="1428496996555" TEXT="Move common stuff from CompositeAppDriver here">
<node CREATED="1428497043990" ID="ID_1304708574" MODIFIED="1428497047756" TEXT="BrowserType"/>
<node CREATED="1428497091436" ID="ID_662116040" MODIFIED="1428497095503" TEXT="initializeDrivers etc."/>
</node>
</node>
<node CREATED="1428497552104" FOLDED="true" ID="ID_1562496622" MODIFIED="1428575701466" TEXT="new BaseWebModuleDriver">
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428497579090" ID="ID_993895400" MODIFIED="1428497599923" TEXT="Move all ui stuff from BaseModuleDriver"/>
</node>
<node CREATED="1428555218784" ID="ID_669858737" MODIFIED="1428575703451" TEXT="rename as selenium">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1428638258873" FOLDED="true" ID="ID_561440329" MODIFIED="1428665303343" TEXT="Refinements">
<icon BUILTIN="pencil"/>
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428638263936" ID="ID_1593533857" MODIFIED="1428644356574">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p class="MsoListParagraph" style="text-indent: -.25in">
      <font color="#993366">?????????Rename selenium package as cbfx.selenium (extension of cbf for selenium)<br/>Move it under src. (It will be in src/cbfx/selenium</font>
    </p>
    <p class="MsoListParagraph" style="margin-left: 1.0in; text-indent: -.25in">
      <font color="#993366" face="Courier New">o</font><font color="#993366" face="Times New Roman">?? </font><font color="#993366">It will be good to have its own eclipse project (depending on cbf project)</font>
    </p>
    <p class="MsoListParagraph" style="margin-left: 1.0in; text-indent: -.25in">
      <font color="#993366" face="Courier New">o</font><font color="#993366" face="Times New Roman">?? </font><font color="#993366">There should be provision for building?</font>
    </p>
    <p class="MsoListParagraph" style="margin-left: 1.5in; text-indent: -.25in">
      <font color="#993366" face="Wingdings">?</font><font color="#993366" face="Times New Roman">? </font><font color="#993366">Cbf.jar without cbfx packages (the pure java one)</font>
    </p>
    <p class="MsoListParagraph" style="margin-left: 1.5in; text-indent: -.25in">
      <font color="#993366" face="Wingdings">?</font><font color="#993366" face="Times New Roman">? </font><font color="#993366">Cbf_selenium.jar with cbfx packages</font>
    </p>
    <p class="MsoListParagraph" style="margin-left: 2.0in; text-indent: -.25in">
      <font color="#993366" face="Symbol">?</font><font color="#993366" face="Times New Roman">???????? </font><font color="#993366">Cbfx.selenium package: needs lots of revamping? there will be different versions for POM etc. Hence both jars may be needed</font>
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
</node>
<node COLOR="#000000" CREATED="1428637128249" FOLDED="true" ID="ID_384309353" MODIFIED="1428650322048" TEXT="BaseWebAppDriver">
<edge STYLE="linear" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="12"/>
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
<node COLOR="#000000" CREATED="1428637156036" FOLDED="true" ID="ID_1988716841" MODIFIED="1428650324143" TEXT="Null constructor probably doesnt work">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="10"/>
<node COLOR="#990000" CREATED="1428637164640" ID="ID_119448734" MODIFIED="1428638534125" TEXT="remove it">
<font NAME="SansSerif" SIZE="11"/>
</node>
</node>
<node COLOR="#000000" CREATED="1428637169405" FOLDED="true" ID="ID_1072732805" MODIFIED="1428650325767" TEXT="CONFIG in constructor">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="10"/>
<node COLOR="#990000" CREATED="1428637183404" FOLDED="true" ID="ID_560541274" MODIFIED="1428638534125" TEXT="rename as params">
<font NAME="SansSerif" SIZE="11"/>
<node COLOR="#111111" CREATED="1428637190135" ID="ID_290575062" MODIFIED="1428638534125" TEXT="Like a plugin">
<font NAME="SansSerif" SIZE="9"/>
</node>
</node>
</node>
<node COLOR="#000000" CREATED="1428637195806" FOLDED="true" ID="ID_832956557" MODIFIED="1428638515661" TEXT="loadModuleDrivers">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="10"/>
<node COLOR="#990000" CREATED="1428637369653" ID="ID_194518720" MODIFIED="1428638534125" TEXT="align the logic with BaseAppDriver">
<font NAME="SansSerif" SIZE="11"/>
</node>
</node>
<node COLOR="#000000" CREATED="1428637394363" ID="ID_987780515" MODIFIED="1428650328087" TEXT="Move browser open and close methods to WebUIDriver">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="10"/>
</node>
<node COLOR="#000000" CREATED="1428637523412" ID="ID_369284868" MODIFIED="1428650329727" TEXT="recover(): should call WebUIDriver.closeBrowsers()">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="10"/>
</node>
</node>
<node COLOR="#000000" CREATED="1428637458316" FOLDED="true" ID="ID_1398582625" MODIFIED="1428650203786" TEXT="WebUIDriver">
<edge STYLE="linear" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="12"/>
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
<node COLOR="#000000" CREATED="1428637493343" ID="ID_1809243005" MODIFIED="1428650206792" TEXT="Should **not** extend AppDriver">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="10"/>
</node>
<node COLOR="#000000" CREATED="1428637549565" ID="ID_1086809167" MODIFIED="1428650208962" TEXT="openBrowser(params)">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="10"/>
</node>
<node COLOR="#000000" CREATED="1428637555548" ID="ID_1108498826" MODIFIED="1428650212442" TEXT="closeBrowser(params)">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="10"/>
</node>
</node>
<node COLOR="#000000" CREATED="1428637602293" FOLDED="true" ID="ID_230125252" MODIFIED="1428650201505" TEXT="SikuliUIDriver">
<edge STYLE="linear" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="12"/>
<icon BUILTIN="full-1"/>
<icon BUILTIN="button_ok"/>
<node COLOR="#000000" CREATED="1428637608619" ID="ID_1720655209" MODIFIED="1428638515661" TEXT="Should **not** extend AppDriver">
<edge STYLE="bezier" WIDTH="thin"/>
<font NAME="SansSerif" SIZE="10"/>
</node>
</node>
</node>
</node>
<node CREATED="1428488057794" FOLDED="true" ID="ID_228878453" MODIFIED="1430993022206" POSITION="left" TEXT="Misc">
<node CREATED="1428488118765" FOLDED="true" ID="ID_535657300" MODIFIED="1430992976920" TEXT="Fix error messages">
<icon BUILTIN="full-1"/>
<node CREATED="1428488640110" ID="ID_1328526274" MODIFIED="1428488642287" TEXT="text"/>
<node CREATED="1428488642800" ID="ID_1065160703" MODIFIED="1428488653604" TEXT="eating root cause"/>
<node CREATED="1428488653950" ID="ID_1426911847" MODIFIED="1428488661706" TEXT="not logging context"/>
<node CREATED="1428501449230" ID="ID_1360509633" MODIFIED="1428501456609" TEXT="search and fix all e.printStackTrace"/>
</node>
<node CREATED="1428488146364" ID="ID_600378917" MODIFIED="1428489412421" TEXT="Eclipse warnings">
<icon BUILTIN="full-2"/>
</node>
<node CREATED="1428488863525" FOLDED="true" ID="ID_820394149" MODIFIED="1428489418173" TEXT="Interface vs implementation">
<icon BUILTIN="full-2"/>
<node CREATED="1428492385193" ID="ID_1618557183" MODIFIED="1428492391555" TEXT="List = new ArrayList"/>
</node>
<node CREATED="1428492379769" FOLDED="true" ID="ID_1274666783" MODIFIED="1428492931887" TEXT="Logger trace">
<icon BUILTIN="full-2"/>
<node CREATED="1428492395124" ID="ID_130957486" MODIFIED="1428492427751" TEXT="Pass all variables separately instead of concat&apos;ing"/>
</node>
<node CREATED="1428492933278" FOLDED="true" ID="ID_127885578" MODIFIED="1430992996872" TEXT="toString() methods">
<icon BUILTIN="full-1"/>
<node CREATED="1428492940240" ID="ID_479273921" MODIFIED="1428492954170" TEXT="Ensure all keys are serialized and returned"/>
<node CREATED="1428491926774" ID="ID_1083030368" MODIFIED="1428492010733">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      toString()?{
    </p>
    <p>
      ???return <font color="#ff3333">super.toString() + "{" + StringUtils.toString(params) + "}"</font>
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1428497422991" ID="ID_486169260" MODIFIED="1428910026465" TEXT="remove use of GCONFIG, G_AUTO_HOME etc.">
<icon BUILTIN="full-2"/>
</node>
</node>
<node CREATED="1428646873927" FOLDED="true" ID="ID_1481083138" MODIFIED="1428922668245" POSITION="left" TEXT="Addition-0410">
<node CREATED="1428646884553" FOLDED="true" ID="ID_1504073884" MODIFIED="1428646918827" TEXT="engine">
<node CREATED="1428646919981" FOLDED="true" ID="ID_881733483" MODIFIED="1428906053290" TEXT="BaseModuleDriver">
<icon BUILTIN="full-3"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428646924057" FOLDED="true" ID="ID_1481936197" MODIFIED="1428646958917" TEXT="factorize eval method">
<node CREATED="1428646960440" FOLDED="true" ID="ID_687089563" MODIFIED="1428646970103" TEXT="findMethod(componentCode)">
<node CREATED="1428646981725" ID="ID_1244154923" MODIFIED="1428646984580" TEXT="present logic"/>
<node CREATED="1428646985037" ID="ID_1586000984" MODIFIED="1428646996016" TEXT="//TODO: Match parameters also"/>
</node>
<node CREATED="1428646973571" ID="ID_589830548" MODIFIED="1428646979054" TEXT="invokeMethod"/>
</node>
</node>
</node>
<node CREATED="1428648138260" FOLDED="true" ID="ID_1891015243" MODIFIED="1428648140704" TEXT="testAccess">
<node CREATED="1428648141825" FOLDED="true" ID="ID_1750012864" MODIFIED="1428650074394" TEXT="ExcelAccess">
<icon BUILTIN="full-3"/>
<node CREATED="1428648151999" FOLDED="true" ID="ID_954483907" MODIFIED="1428648156318" TEXT="dataAccess">
<node CREATED="1428648157567" ID="ID_1953751627" MODIFIED="1428648161732" TEXT="Remove it as a member"/>
<node CREATED="1428648162062" ID="ID_1974282621" MODIFIED="1428648173348" TEXT="add a method getDataAccess()"/>
<node CREATED="1428648214978" ID="ID_1439913097" MODIFIED="1428648264024">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      ??private TestCase deserializeTest(String testName, String serializedFileName) {
    </p>
    <p>
      return ExcelDeserializer.deserialize(g<font color="#ff3399">etDataTableAccess(),</font>? testName,
    </p>
    <p>
      serializedFileName);
    </p>
    <p>
      ??}
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1428650145138" FOLDED="true" ID="ID_1871257552" MODIFIED="1428665142186" TEXT="ExcelDeserializer">
<icon BUILTIN="full-1"/>
<node CREATED="1428650153885" FOLDED="true" ID="ID_967820703" MODIFIED="1428665146818" TEXT="Something wrong with below pattern">
<icon BUILTIN="full-1"/>
<node CREATED="1428650167308" ID="ID_194600016" MODIFIED="1428650202076">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      if (ExcelAccess.isSheetExists(sSerializedFileName,SHN_REFERENCES)) {
    </p>
    <p>
      List&lt;Map&gt; refRows = tcDataAccess.readSheet(SHN_REFERENCES);
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1428650372121" FOLDED="true" ID="ID_500256767" MODIFIED="1428899272205" TEXT="static logger">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1428650379812" FOLDED="true" ID="ID_1413782676" MODIFIED="1428650460629" TEXT="Remove the isExists() check. That should be in DTAccess">
<node CREATED="1428650471370" ID="ID_1253787739" MODIFIED="1428650489317" TEXT="If needed it can throw FileNotFoundException"/>
</node>
<node CREATED="1428650490963" ID="ID_1124844217" MODIFIED="1428650510512" TEXT="logger should **not** be static"/>
</node>
<node CREATED="1428650511879" ID="ID_1854704003" MODIFIED="1428899274075" TEXT="remove null constructor">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
</node>
</node>
<node CREATED="1428649697209" FOLDED="true" ID="ID_719327267" MODIFIED="1428650100053" TEXT="DesignerSerializer">
<icon BUILTIN="full-3"/>
<node CREATED="1428649709648" FOLDED="true" ID="ID_586659391" MODIFIED="1428656067654">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      factorize code between
    </p>
    <p>
      DesignerAccess &amp; itself
    </p>
  </body>
</html></richcontent>
<node CREATED="1428649740291" FOLDED="true" ID="ID_1547041120" MODIFIED="1428649755936" TEXT="constructor(params)">
<node CREATED="1428649785633" ID="ID_1507377209" MODIFIED="1428649799551" TEXT="designerAccess.connect(params)"/>
</node>
<node CREATED="1428649757209" FOLDED="true" ID="ID_1512458606" MODIFIED="1428649768653" TEXT="getTestCase(folderPath, testcaseName)">
<node CREATED="1428649808771" ID="ID_1304139444" MODIFIED="1428649840885" TEXT="designerAccess.access(&lt;hash-of-request&gt;)"/>
</node>
<node CREATED="1428649848641" ID="ID_985702482" MODIFIED="1428649861232" TEXT="deserialize(fromMap)"/>
<node CREATED="1428649869206" FOLDED="true" ID="ID_1720996022" MODIFIED="1428649875486" TEXT="close()">
<node CREATED="1428649877136" ID="ID_1582921876" MODIFIED="1428649884939" TEXT="designerAccess.disconnect()"/>
</node>
</node>
<node CREATED="1428649931969" FOLDED="true" ID="ID_1798438" MODIFIED="1428649957002" TEXT="deserialize">
<node CREATED="1428649931974" ID="ID_132794383" MODIFIED="1428650060612" TEXT="Discuss with me"/>
</node>
</node>
</node>
<node CREATED="1428659871878" FOLDED="true" ID="ID_804110931" MODIFIED="1428659876703" TEXT="harness">
<node CREATED="1428659878082" FOLDED="true" ID="ID_1046201262" MODIFIED="1428659893407" TEXT="ResourcePaths">
<icon BUILTIN="full-1"/>
<node CREATED="1428659884787" ID="ID_299652605" MODIFIED="1428659888138" TEXT="changes are incorrect"/>
</node>
</node>
<node CREATED="1428647579093" FOLDED="true" ID="ID_1462706686" MODIFIED="1428657223369" TEXT="misc...">
<node CREATED="1428647582829" FOLDED="true" ID="ID_1974149128" MODIFIED="1428647828625" TEXT="PluginManager.getPlugin()">
<icon BUILTIN="full-3"/>
<node CREATED="1428647776870" ID="ID_786391587" MODIFIED="1428647824292">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      call getPlugin(Map) instead of
    </p>
    <p>
      getPlugin(String name, Map parameters)
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1428657170049" ID="ID_429310121" MODIFIED="1428657247831" TEXT="FolderPath in Plugin params vs ResourcePaths">
<icon BUILTIN="full-4"/>
</node>
<node CREATED="1428659016828" FOLDED="true" ID="ID_1515198540" MODIFIED="1428659190785" TEXT="toString()">
<icon BUILTIN="full-4"/>
<node CREATED="1428659021560" ID="ID_1969930370" MODIFIED="1428659035099" TEXT="If there are no members, dont override..."/>
<node CREATED="1428659036156" ID="ID_1693789124" MODIFIED="1428659050553" TEXT="Check if u need super.toString() instead of hard coded class name"/>
</node>
<node CREATED="1428659112944" FOLDED="true" ID="ID_1594743126" MODIFIED="1428659187393" TEXT="Minimize access">
<icon BUILTIN="full-4"/>
<node CREATED="1428659123254" FOLDED="true" ID="ID_670653854" MODIFIED="1428659184544" TEXT="Make members and methods private where possible">
<node CREATED="1428659143012" ID="ID_96878502" MODIFIED="1428659145816" TEXT="engine/Engine"/>
</node>
<node CREATED="1428659148215" FOLDED="true" ID="ID_1222024636" MODIFIED="1428659184545" TEXT="Use protected where appropriate">
<node CREATED="1428659166707" ID="ID_1038437413" MODIFIED="1428659171172" TEXT="loadModules"/>
</node>
</node>
<node CREATED="1428659420949" FOLDED="true" ID="ID_1236726965" MODIFIED="1428659554443" TEXT="Naming conventions">
<icon BUILTIN="full-4"/>
<node CREATED="1428659427454" FOLDED="true" ID="ID_1384478502" MODIFIED="1428659447090" TEXT="Use of CONFIG etc.">
<node CREATED="1428659448196" ID="ID_1607376053" MODIFIED="1428659468559" TEXT="Other than RESULT, other variables should follow conventions"/>
</node>
<node CREATED="1428659470789" FOLDED="true" ID="ID_1540633408" MODIFIED="1428659496989" TEXT="Dont use prefixe/suffixes, such as ">
<node CREATED="1428659516528" ID="ID_1303532132" MODIFIED="1428659516528" TEXT="mConfig"/>
<node CREATED="1428659518725" ID="ID_654811273" MODIFIED="1428659537429" TEXT="xyzObject"/>
<node CREATED="1428659538216" ID="ID_1098790507" MODIFIED="1428659544371" TEXT="oAbcd"/>
</node>
</node>
</node>
<node CREATED="1428656287255" FOLDED="true" ID="ID_781933283" MODIFIED="1428656288977" TEXT="reporter">
<node CREATED="1428656290640" FOLDED="true" ID="ID_1212562972" MODIFIED="1428663711422" TEXT="EmailAlerter">
<icon BUILTIN="full-2"/>
<node CREATED="1428656444343" ID="ID_1876019273" MODIFIED="1428906978445" TEXT="EmailAlerter is not following plugin forms">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1428656295024" ID="ID_742372602" MODIFIED="1428906981372" TEXT="Make all internal methods private">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1428656416494" ID="ID_1440680892" MODIFIED="1428906983108" TEXT="Make all internal members private">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1428656729646" ID="ID_944625382" MODIFIED="1428656741623" TEXT="Make all transient variables local"/>
<node CREATED="1428656356731" FOLDED="true" ID="ID_1359669339" MODIFIED="1428906986747" TEXT="Remove/replace System.out.println">
<icon BUILTIN="button_ok"/>
<node CREATED="1428656778502" ID="ID_1904234074" MODIFIED="1428656784132" TEXT="logger.trace(?)"/>
</node>
<node CREATED="1428656523322" FOLDED="true" ID="ID_1500663894" MODIFIED="1428907000317" TEXT="Fix location of vbUtils.vbs">
<icon BUILTIN="button_ok"/>
<node CREATED="1428658867606" FOLDED="true" ID="ID_1144198688" MODIFIED="1428658885850" TEXT="Use ResourcePaths.getFrameworkResource() method to determine vbUtils.vbs">
<node CREATED="1428656485389" ID="ID_708140062" MODIFIED="1428656518352">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      String Path_SendMailVbs = System.getProperty("user.dir")
    </p>
    <p>
      + "\\src\\cbf\\utils\\vbUtils.vbs";
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1428656791101" ID="ID_1341913029" MODIFIED="1428656797444" TEXT="Cleanup other aspects?"/>
</node>
<node CREATED="1428657090286" FOLDED="true" ID="ID_1798282654" MODIFIED="1428663717607" TEXT="ScreenDumpManager">
<icon BUILTIN="full-4"/>
<node CREATED="1428657098774" FOLDED="true" ID="ID_1153982999" MODIFIED="1428657116825" TEXT="Use *Utils.str2Boolean() here">
<node CREATED="1428657117644" MODIFIED="1428657117644" TEXT="if (details.get(&quot;screenDump&quot;).toString().equalsIgnoreCase(&quot;true&quot;)) {"/>
</node>
</node>
</node>
</node>
<node CREATED="1428916720179" FOLDED="true" ID="ID_51571729" MODIFIED="1428922710971" POSITION="left" TEXT="Harness-0413">
<icon BUILTIN="full-1"/>
<node CREATED="1428916916918" ID="ID_636240968" MODIFIED="1428917427100" TEXT="Factorize run*() methods">
<node CREATED="1428917067298" ID="ID_965349317" MODIFIED="1428921408135">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Move all non-run logic out of these methods
    </p>
    <p>
      Move these into initialize()
    </p>
  </body>
</html></richcontent>
<node CREATED="1428916882667" ID="ID_1611572663" MODIFIED="1428916887363" TEXT="runTestSet">
<node CREATED="1428917184810" ID="ID_1000281641" MODIFIED="1428928152858" TEXT="initializeConfig(configFileName): move to main. Rename as loadConfiguration">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1428917184812" ID="ID_234043350" MODIFIED="1428928943724" TEXT="initializeResourcePaths(): move to new initialize">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1428917184814" ID="ID_1359651980" MODIFIED="1428917232826">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      setErrorAndTracePath();
    </p>
    <p>
      // TODO : set run path for Erace.csv and Trace.csv files at the starting
    </p>
  </body>
</html></richcontent>
<node CREATED="1428916824697" ID="ID_1624107813" MODIFIED="1428928165202" TEXT="All logic should be in LogUtils">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1428916745142" ID="ID_1483015958" MODIFIED="1428928149066">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      move?setErrorAndTracePath(...) to LogUtils
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1428916844970" ID="ID_959103098" MODIFIED="1428916866509" TEXT="setErrorAndTracePath()... is best specified in log4j.properties"/>
<node CREATED="1428917254152" ID="ID_1319737292" MODIFIED="1428917261795" TEXT="LogUtils.initialize() may be called"/>
</node>
<node CREATED="1428919131602" ID="ID_644768609" MODIFIED="1428928155961" TEXT="new loadAppDriver">
<icon BUILTIN="button_ok"/>
<node CREATED="1428919172311" ID="ID_3891020" MODIFIED="1428919175733">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      try {
    </p>
    <p>
      appDriver = new AppLoader().loadApp(GCONFIG);
    </p>
    <p>
      } catch (Exception e) {
    </p>
    <p>
      logger.handleError("Failed to LoadAppDriver " , e);
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1428919205383" ID="ID_1050572119" MODIFIED="1428928266174" TEXT="runTestCase">
<icon BUILTIN="button_ok"/>
<node CREATED="1428919242678" ID="ID_1666320501" MODIFIED="1428919255973" TEXT="it is presently doing nothing"/>
<node CREATED="1428919223747" ID="ID_712353977" MODIFIED="1428919561883" TEXT="replace runHarness() by this"/>
</node>
<node CREATED="1428919776010" ID="ID_489410340" MODIFIED="1428930834466" TEXT="runHarness">
<icon BUILTIN="button_ok"/>
<node CREATED="1428919474961" ID="ID_1203187569" MODIFIED="1428920496935" TEXT="move makeReporter  to new initialize"/>
<node CREATED="1428919427502" ID="ID_1334017996" MODIFIED="1428928557987" TEXT="remove finalize">
<icon BUILTIN="button_ok"/>
</node>
</node>
<node CREATED="1428919382522" ID="ID_1746913245" MODIFIED="1428929674188" TEXT="run">
<icon BUILTIN="button_ok"/>
<node CREATED="1428919515926" ID="ID_1857835445" MODIFIED="1428920505464" TEXT="reporter.open/close should be also to initialize">
<node CREATED="1428919530934" ID="ID_951186959" MODIFIED="1428919540683" TEXT="separate open/close are not required for each test"/>
</node>
</node>
<node CREATED="1428919633014" ID="ID_81981325" MODIFIED="1428929670964" TEXT="runTest">
<icon BUILTIN="button_ok"/>
<node CREATED="1428919653458" ID="ID_572812346" MODIFIED="1428920514999" TEXT="move engine initialize to new initialize"/>
<node CREATED="1428919668153" ID="ID_784400241" MODIFIED="1428920521365" TEXT="move reporter initialize to new initialize"/>
</node>
</node>
<node CREATED="1428920012882" ID="ID_1537473211" MODIFIED="1428930850119" TEXT="add method for executing">
<icon BUILTIN="button_ok"/>
<node CREATED="1428920243081" ID="ID_1360346951" MODIFIED="1428929685651" TEXT="initialize">
<icon BUILTIN="button_ok"/>
<node CREATED="1428920257355" ID="ID_970801057" MODIFIED="1428920264283" TEXT="loadConfig"/>
<node CREATED="1428920291321" ID="ID_1644663181" MODIFIED="1428920297513" TEXT="initializeResourcePaths"/>
<node CREATED="1428920268260" ID="ID_1938566564" MODIFIED="1428920281208" TEXT="LogUtils.initialize"/>
<node CREATED="1428920328028" ID="ID_373394379" MODIFIED="1428920340243" TEXT="setup reporters"/>
<node CREATED="1428920402718" ID="ID_1608342750" MODIFIED="1428920408610" TEXT="initialize engine"/>
<node CREATED="1428920359151" ID="ID_874581218" MODIFIED="1428920366411" TEXT="loadAppDriver"/>
</node>
<node CREATED="1428920422079" ID="ID_1883382784" MODIFIED="1428929827555" TEXT="runTest(tcMaker)">
<icon BUILTIN="button_ok"/>
<node CREATED="1428920649212" ID="ID_576559024" MODIFIED="1428920659013" TEXT="call appDriver.recover on abort"/>
</node>
<node CREATED="1428920345960" ID="ID_790629287" MODIFIED="1428929693764" TEXT="finalize">
<icon BUILTIN="button_ok"/>
<node CREATED="1428920349396" ID="ID_1841038201" MODIFIED="1428920354284" TEXT="close reporters"/>
</node>
</node>
</node>
<node CREATED="1428917470562" ID="ID_533802038" MODIFIED="1428930844488">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Move the factorized run* methods to
    </p>
    <p>
      a new SimpleTestSetRunner class
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
<node CREATED="1428920544823" ID="ID_65342310" MODIFIED="1428920550328" TEXT="Harness.initialize"/>
<node CREATED="1428917538206" ID="ID_1031761651" MODIFIED="1428917541238" TEXT="runTestSet"/>
<node CREATED="1428919293690" ID="ID_359782413" MODIFIED="1428929990779" TEXT="runHarness">
<icon BUILTIN="button_ok"/>
<node CREATED="1428919579161" ID="ID_1224520456" MODIFIED="1428919591503" TEXT="rename as runTestInstance"/>
<node CREATED="1428919302862" ID="ID_1065090422" MODIFIED="1428919331334" TEXT="move interTcDelay logic to new delayTestcase"/>
<node CREATED="1428919333166" ID="ID_940295641" MODIFIED="1428919342308" TEXT="move delayTestCase() call to runTestSet"/>
</node>
<node CREATED="1428919382522" ID="ID_609448297" MODIFIED="1428919383702" TEXT="run">
<node CREATED="1428919625844" ID="ID_268939669" MODIFIED="1428919631367" TEXT="replace by runTest"/>
</node>
<node CREATED="1428919633014" ID="ID_1671704255" MODIFIED="1428919635287" TEXT="runTest">
<node CREATED="1428920555710" ID="ID_438188934" MODIFIED="1428920562269" TEXT="harness.runTest(tcMaker)"/>
</node>
<node CREATED="1428920565171" ID="ID_1463385722" MODIFIED="1428920569146" TEXT="Harness.finalize"/>
</node>
</node>
<node CREATED="1428916720179" FOLDED="true" ID="ID_727290387" MODIFIED="1430992962100" POSITION="left" TEXT="0414">
<node CREATED="1428981192165" ID="ID_1567975864" MODIFIED="1429245587510" TEXT="General">
<node CREATED="1428981163331" ID="ID_93398391" MODIFIED="1429245411947">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Enable references
    </p>
    <p>
      to a strict module hierarchy
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="full-4"/>
<node CREATED="1428981204095" ID="ID_232846170" MODIFIED="1428981207085" TEXT="Hierarchy">
<node CREATED="1428981335888" MODIFIED="1428981335888" TEXT="harness"/>
<node CREATED="1428981335888" MODIFIED="1428981335888" TEXT="data plugin reporting testAccess engine"/>
<node CREATED="1428981335888" MODIFIED="1428981335888" TEXT="utils"/>
</node>
<node CREATED="1428981342359" ID="ID_580527622" MODIFIED="1428981344601" TEXT="utils">
<node CREATED="1428981346602" ID="ID_470936246" MODIFIED="1428981371413" TEXT="LogUtils">
<node CREATED="1428981454474" ID="ID_818969411" MODIFIED="1428981459401" TEXT="Remove ResourcePaths"/>
<node CREATED="1428981460251" ID="ID_1532872949" MODIFIED="1428981484307" TEXT="initialize() should get name of the ResourcePath and RunPath as parameter"/>
</node>
<node CREATED="1428981800488" ID="ID_11367384" MODIFIED="1428981808427" TEXT="PersistentMap">
<node CREATED="1428981968325" ID="ID_507312675" MODIFIED="1428981980304" TEXT="remove cbf.harness reference"/>
<node CREATED="1428981980935" ID="ID_1940904289" MODIFIED="1428981997171" TEXT="pass globalDataAccess class as parameter">
<node CREATED="1428982182070" ID="ID_1857915043" MODIFIED="1428982197887" TEXT="affects parameterAccess"/>
</node>
<node CREATED="1428981997803" ID="ID_443000789" MODIFIED="1428982059387" TEXT="make persistentDataAccess as a full utils clas"/>
</node>
</node>
<node CREATED="1428986849509" ID="ID_669320936" MODIFIED="1428986854554" TEXT="GCONFIG usage">
<node CREATED="1428986855772" ID="ID_298993862" MODIFIED="1428986861436" TEXT="reporting\ReportingManager">
<node CREATED="1428986886407" ID="ID_201110751" MODIFIED="1428986902169" TEXT="Pass configuration to ReportingManager"/>
</node>
<node CREATED="1428987240538" ID="ID_738022357" MODIFIED="1428987252429" TEXT="testAccess\ExcelAccess">
<node CREATED="1428987253576" ID="ID_1811766693" MODIFIED="1428987278746" TEXT="How to eliminate use of global DataAccess?">
<icon BUILTIN="help"/>
</node>
</node>
<node CREATED="1428987346220" ID="ID_1627880017" MODIFIED="1429250145083" TEXT="testAccess\ExcelDeserializaer">
<icon BUILTIN="button_ok"/>
<node CREATED="1428987374288" ID="ID_1316655385" MODIFIED="1428987422609">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Move this from global config to
    </p>
    <p>
      Plugin parameter
    </p>
  </body>
</html></richcontent>
<node CREATED="1428987356218" ID="ID_1372875137" MODIFIED="1428987362028" TEXT="enableStepSelection"/>
<node CREATED="1428987362430" ID="ID_324865479" MODIFIED="1428987367171" TEXT="enableIterationSelection"/>
</node>
</node>
<node CREATED="1428982274072" ID="ID_685673661" MODIFIED="1428982277597" TEXT="SleepUtils">
<node CREATED="1428982279134" ID="ID_173950654" MODIFIED="1428987495922" TEXT="timer slabs global configuration">
<icon BUILTIN="edit"/>
<icon BUILTIN="help"/>
</node>
</node>
</node>
</node>
</node>
<node CREATED="1428981515410" ID="ID_647709044" MODIFIED="1429245616065" TEXT="utils">
<node CREATED="1428981517835" ID="ID_1444705178" MODIFIED="1428982516777" TEXT="LogUtils">
<icon BUILTIN="full-1"/>
<node CREATED="1428981522598" ID="ID_1884415096" MODIFIED="1428982489728" TEXT="initialize() should be static">
<icon BUILTIN="full-1"/>
<node CREATED="1428982506380" ID="ID_1603544305" MODIFIED="1428982512214" TEXT="who will call?"/>
</node>
<node CREATED="1428981606117" ID="ID_1166194405" MODIFIED="1428982456017" TEXT="current implementation is faulty">
<icon BUILTIN="full-3"/>
<node CREATED="1428981616165" ID="ID_1015655575" MODIFIED="1428981622573" TEXT="Separate logger for each owner"/>
<node CREATED="1428981630828" ID="ID_1408913435" MODIFIED="1428981645624" TEXT="See log4r implementation in Designer"/>
</node>
<node CREATED="1429245593243" ID="ID_1706991340" MODIFIED="1429245610668" TEXT="make implementation similar to Designer/log4r">
<icon BUILTIN="full-3"/>
</node>
</node>
</node>
</node>
</node>
</map>
