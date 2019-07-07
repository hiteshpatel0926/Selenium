<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1431663771467" ID="ID_1216097021" MODIFIED="1432271783856" TEXT="Miscellaneous">
<node CREATED="1431663786827" ID="ID_368026626" MODIFIED="1432272075981" POSITION="right" TEXT="ResultLogger">
<icon BUILTIN="button_ok"/>
<node CREATED="1431663793387" ID="ID_1738559621" MODIFIED="1431663828075">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      At present, result logger instance is
    </p>
    <p>
      passed around in multiple places
    </p>
  </body>
</html></richcontent>
<node CREATED="1431663829611" ID="ID_742092776" MODIFIED="1431663835384" TEXT="Engine -&gt; AppDriver"/>
<node CREATED="1431663835828" ID="ID_688017434" MODIFIED="1431663846156" TEXT="AppDriver -&gt; Module Drivers"/>
<node CREATED="1431663846507" ID="ID_836598708" MODIFIED="1431663857378" TEXT="BuiltinDriver, Utilities"/>
</node>
<node CREATED="1431663859360" ID="ID_234525655" MODIFIED="1431663889719" TEXT="We need only a singleton">
<node CREATED="1431663891287" ID="ID_558567161" MODIFIED="1431663902129" TEXT="Make ResultLogger methods static">
<node CREATED="1431663923470" ID="ID_1081702076" MODIFIED="1431663939741" TEXT="Calls a private static singleton instance&apos; methods"/>
<node CREATED="1431663940436" ID="ID_1720171918" MODIFIED="1431663962245" TEXT="Initialize the singleton when ResultTracker is created"/>
</node>
<node CREATED="1431663964289" ID="ID_125587598" MODIFIED="1431664012323" TEXT="Simplify the AppDriver &amp; ModuleDriver instantiations"/>
</node>
<node CREATED="1431664028493" ID="ID_1407416298" MODIFIED="1431664034687" TEXT="Further simplifications">
<node CREATED="1431664013470" ID="ID_892862369" MODIFIED="1431664044453" TEXT="Now AppDriver can be truly a plugin"/>
<node CREATED="1431664045786" ID="ID_1537067932" MODIFIED="1431664060887" TEXT="ModuleDriver can use static import of ResultLogger">
<node CREATED="1431664062221" ID="ID_1764568492" MODIFIED="1431664081394" TEXT="So, instead of RESULT.passed() call simply passed()"/>
</node>
</node>
</node>
<node CREATED="1431664779080" ID="ID_1739980648" MODIFIED="1432272078955" POSITION="right" TEXT="AppLoader">
<icon BUILTIN="button_ok"/>
<node CREATED="1431664111269" ID="ID_1805003652" MODIFIED="1431664883519" TEXT="AppDriver plugin">
<node CREATED="1431664883488" ID="ID_1880422747" MODIFIED="1431664887076" TEXT="Using Default">
<node CREATED="1431664123343" ID="ID_244953427" MODIFIED="1431664501240">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;variable&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&lt;name&gt;AppDriver&lt;/name&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&lt;value&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&lt;plugin&gt;DefaultAppDriver&lt;/plugin&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&lt;parameters&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&lt;modulePackage&gt;com.client1.myapp&lt;/modulePackage&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&lt;/parameters&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&lt;/value&gt;
    </p>
    <p>
      &lt;/variable&gt;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1431664892232" ID="ID_1959359634" MODIFIED="1431664906131" TEXT="MasterConfig will have DefaultAppDriver"/>
</node>
<node CREATED="1431664909548" ID="ID_851117712" MODIFIED="1431664913604" TEXT="Using custom">
<node CREATED="1431664123343" ID="ID_30767285" MODIFIED="1431665009834">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &lt;variable&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&lt;name&gt;AppDriver&lt;/name&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&lt;value&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&lt;plugin&gt;???&lt;/plugin&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&lt;class&gt;com.client1.myApp.AppDriver&lt;/class&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&lt;parameters&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;whatever is specific to AppDriver
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&lt;/parameters&gt;
    </p>
    <p>
      &#xfffd;&#xfffd;&lt;/value&gt;
    </p>
    <p>
      &lt;/variable&gt;
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1431664917122" ID="ID_247740648" MODIFIED="1431664925281" TEXT="MasterConfig will not have any entry"/>
</node>
</node>
<node CREATED="1431664789118" ID="ID_874194591" MODIFIED="1431664816672" TEXT="AppLoader will call PluginManager">
<node CREATED="1431665040894" ID="ID_1647461676" MODIFIED="1431665048413" TEXT="It will not need ResultLogger"/>
<node CREATED="1431665048702" ID="ID_468297431" MODIFIED="1431665060885" TEXT="Only Map params will be passed"/>
</node>
</node>
<node CREATED="1431664820923" ID="ID_442866195" MODIFIED="1431664843645" POSITION="right" TEXT="Applies to...">
<node CREATED="1431664831711" ID="ID_1300487011" MODIFIED="1431664838356" TEXT="VBS"/>
<node CREATED="1431664845322" ID="ID_686396178" MODIFIED="1431664846804" TEXT="Java"/>
<node CREATED="1431664847155" ID="ID_1932233763" MODIFIED="1431664848777" TEXT="C#"/>
</node>
<node CREATED="1432271784960" ID="ID_1174050068" MODIFIED="1432271801273" POSITION="left" TEXT="SVN Updated ">
<node CREATED="1432271941065" ID="ID_1424944726" MODIFIED="1432272007702" TEXT="ResultLogger">
<node CREATED="1432271803437" ID="ID_408328024" MODIFIED="1432271843737" TEXT="&#x9;TestResultLogger"/>
<node CREATED="1432271822502" ID="ID_1236822340" MODIFIED="1432271836720" TEXT="AppDriver"/>
<node CREATED="1432271846910" ID="ID_471911925" MODIFIED="1432271862091" TEXT="&#x9;BaseAppDriver"/>
<node CREATED="1432271862751" ID="ID_1646131433" MODIFIED="1432271873364" TEXT="&#x9;Engine"/>
<node CREATED="1432271882032" ID="ID_1877024996" MODIFIED="1432271885828" TEXT="&#x9;TestCaseRunner"/>
<node CREATED="1432271892136" ID="ID_52886003" MODIFIED="1432271895671" TEXT="BaseWebAppDriver"/>
<node CREATED="1432271902240" ID="ID_1765109948" MODIFIED="1432271906829" TEXT="&#x9;BaseWebModuleDriver"/>
<node CREATED="1432271912785" ID="ID_1809234429" MODIFIED="1432271915237" TEXT="&#x9;CompositeAppDriver"/>
<node CREATED="1432271922593" ID="ID_1329345770" MODIFIED="1432271925397" TEXT="&#x9;ModuleDriver files"/>
</node>
<node CREATED="1432271973211" ID="ID_1304111518" MODIFIED="1432272014800" TEXT="AppDriver Plugin">
<node CREATED="1432272033117" ID="ID_273042992" MODIFIED="1432272036521" TEXT="&#x9;PluginManager"/>
<node CREATED="1432272044365" ID="ID_436927007" MODIFIED="1432272048090" TEXT="&#x9;Engine"/>
<node CREATED="1432272055373" ID="ID_1801691902" MODIFIED="1432272057914" TEXT="&#x9;AppLoader"/>
<node CREATED="1432272065502" ID="ID_1732103618" MODIFIED="1432272069898" TEXT="&#x9;WebUIDriver"/>
</node>
</node>
</node>
</map>
