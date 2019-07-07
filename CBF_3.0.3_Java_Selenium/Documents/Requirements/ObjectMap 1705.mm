<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1495236143467" ID="ID_243425674" MODIFIED="1495239188066">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      ObjectMap
    </p>
    <p>
      1705
    </p>
  </body>
</html></richcontent>
<node CREATED="1495236258293" ID="ID_1026592078" MODIFIED="1495236281498" POSITION="right" TEXT="cbfx.objectmap">
<node CREATED="1495237416510" ID="ID_1545804439" MODIFIED="1495237419563" TEXT="All files">
<node CREATED="1495237424765" ID="ID_812469515" MODIFIED="1495237696632">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      plugin params
    </p>
    <p>
      is not fully used
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="full-3"/>
<node CREATED="1495237451303" ID="ID_1317114489" MODIFIED="1495237634126">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      File name is
    </p>
    <p>
      hard coded
    </p>
  </body>
</html></richcontent>
<node CREATED="1495237482472" ID="ID_1782159936" MODIFIED="1495237486517" TEXT="Examples">
<node CREATED="1495237521265" ID="ID_504085841" MODIFIED="1495237521265" TEXT="getSuiteResource(&quot;Plan/AppDriver/OR&quot;, &quot;uiMap.xls&quot;);"/>
<node CREATED="1495237543643" ID="ID_1165787354" MODIFIED="1495237561635">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      getSuiteResource(&quot;Plan/AppDriver/OR&quot;, &quot;uiMap.xml&quot;);
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1495237634110" ID="ID_1276693306" MODIFIED="1495237643423" TEXT="Better flexibility?">
<node CREATED="1495237573344" ID="ID_1397931383" MODIFIED="1495237616829" TEXT="getSuiteResource(params.get(&quot;filepath&quot;), &quot;&quot;)&apos;"/>
<node CREATED="1495237645367" ID="ID_618977315" MODIFIED="1495237658275" TEXT="Give default in MasterConfig.xml?"/>
</node>
</node>
</node>
<node CREATED="1495237799307" ID="ID_762663640" MODIFIED="1495237805665" TEXT="error handling">
<node CREATED="1495238035034" ID="ID_1555338131" MODIFIED="1495238059707">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Protocol should be clear
    </p>
    <p>
      as per interface definition
    </p>
  </body>
</html></richcontent>
<node CREATED="1495238080369" ID="ID_148844711" MODIFIED="1495238088191" TEXT="return null if not found"/>
<node CREATED="1495238062151" ID="ID_1877792088" MODIFIED="1495238075931">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      logger.handleError when
    </p>
    <p>
      not as per protocol
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</node>
<node CREATED="1495236281498" ID="ID_1919173769" MODIFIED="1495236284983" TEXT="ObjectMap">
<node CREATED="1495236332343" ID="ID_1028258215" MODIFIED="1495237122082" TEXT="Add javadoc">
<icon BUILTIN="full-2"/>
</node>
<node CREATED="1495236292224" ID="ID_624027161" MODIFIED="1495237118245" TEXT="rename methods">
<icon BUILTIN="full-2"/>
<node CREATED="1495236376684" ID="ID_423377986" MODIFIED="1495236449963">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      method name must <u><b>not</b></u>&#160;start in Capital
    </p>
  </body>
</html></richcontent>
<font NAME="SansSerif" SIZE="12"/>
</node>
<node CREATED="1495236459542" ID="ID_1731086986" MODIFIED="1495236580511" TEXT="ObjectMaps --&gt; pageProperties">
<node CREATED="1495236654770" ID="ID_1581493094" MODIFIED="1495236674931" TEXT="Should be similar to properties, the other method"/>
<node CREATED="1495236675899" ID="ID_1273451256" MODIFIED="1495236741774">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      properties ==&gt; objectMap
    </p>
    <p>
      ObjectMaps ==&gt; pageMap?
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</node>
<node CREATED="1495236767494" ID="ID_1046937615" MODIFIED="1495237020448" TEXT="ExcelModuleMap">
<node CREATED="1495237025371" ID="ID_1290274896" MODIFIED="1495237105100" TEXT="setCodes">
<icon BUILTIN="closed"/>
<icon BUILTIN="full-1"/>
<node CREATED="1495237032174" ID="ID_67793575" MODIFIED="1495427349935">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Smart but dangerous
    </p>
    <p>
      design
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1495237184565" ID="ID_1673139201" MODIFIED="1495237199832">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      No documentation in code
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1495237143499" ID="ID_489144926" MODIFIED="1495237177628">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      How come same is not there in
    </p>
    <p>
      other *ObjectMap?
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1495427360566" ID="ID_1197950358" MODIFIED="1495427766948">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Use a more generic facility
    </p>
    <p>
      to determine 'current component'
    </p>
  </body>
</html>
</richcontent>
<arrowlink DESTINATION="ID_1557018079" ENDARROW="Default" ENDINCLINATION="1082;0;" ID="Arrow_ID_1724656267" STARTARROW="None" STARTINCLINATION="1082;0;"/>
</node>
</node>
<node CREATED="1495237881609" ID="ID_497881509" MODIFIED="1495238020996" TEXT="all methods">
<icon BUILTIN="full-2"/>
<node CREATED="1495237905839" ID="ID_1231823990" MODIFIED="1495237920529" TEXT="logger.error --&gt; logger.handleError()"/>
</node>
<node CREATED="1495237965634" ID="ID_928641300" MODIFIED="1495237981433" TEXT="ObjectMaps">
<icon BUILTIN="full-1"/>
<icon BUILTIN="stop-sign"/>
<node CREATED="1495237970262" ID="ID_1910896451" MODIFIED="1495237973507" TEXT="commented out"/>
</node>
<node CREATED="1495237216461" ID="ID_1657451511" MODIFIED="1495237229519" TEXT="logger should be private">
<icon BUILTIN="full-3"/>
</node>
</node>
<node CREATED="1495238359854" ID="ID_1971412143" MODIFIED="1495239034874" TEXT="ExcelObjectMap">
<node CREATED="1495238368577" ID="ID_471193673" MODIFIED="1495239048293" TEXT="ObjectMaps">
<icon BUILTIN="full-2"/>
<node CREATED="1495238374360" FOLDED="true" ID="ID_1790386849" MODIFIED="1495239079523" TEXT="Fix logic">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1790386849" ENDARROW="Default" ENDINCLINATION="26;0;" ID="Arrow_ID_108907488" SOURCE="ID_1704737705" STARTARROW="None" STARTINCLINATION="26;0;"/>
<node CREATED="1495238379668" ID="ID_1407152968" MODIFIED="1495238867945">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      List&lt;Map&gt; resultMap = new ArrayList();
    </p>
    <p>
      for (Map map : uiMap) {
    </p>
    <p>
      &#160;&#160;boolean match = false;
    </p>
    <p>
      &#160;&#160;try {
    </p>
    <p>
      &#160;&#160;&#160;&#160;match = pageName.matches((String) map.get(&quot;page&quot;));
    </p>
    <p>
      &#160;&#160;} catch (Exception e) {
    </p>
    <p>
      &#160;&#160;&#160;&#160;logger.warning(&quot;matching page&quot;, map, e)
    </p>
    <p>
      &#160;&#160;}
    </p>
    <p>
      
    </p>
    <p>
      &#160;&#160;if (!match)
    </p>
    <p>
      &#160;&#160;&#160;&#160;continue;
    </p>
    <p>
      
    </p>
    <p>
      &#160;&#160;resultMap.add(map);
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1495239008184" ID="ID_481108659" MODIFIED="1495239057271" TEXT="properties">
<icon BUILTIN="full-2"/>
<node CREATED="1495239014135" ID="ID_1704737705" MODIFIED="1495239079523" TEXT="Fix logic">
<arrowlink DESTINATION="ID_1790386849" ENDARROW="Default" ENDINCLINATION="26;0;" ID="Arrow_ID_108907488" STARTARROW="None" STARTINCLINATION="26;0;"/>
</node>
</node>
</node>
<node CREATED="1495237129113" ID="ID_1164248449" MODIFIED="1495237133426" TEXT="XmlModuleMap">
<node CREATED="1495237267419" ID="ID_1324410132" MODIFIED="1495237287678" TEXT="All except interface methods should be private">
<icon BUILTIN="full-2"/>
</node>
</node>
</node>
<node CREATED="1495239188066" ID="ID_1474862620" MODIFIED="1495427441347" POSITION="left" TEXT="Design">
<node CREATED="1495427462124" ID="ID_1228362381" MODIFIED="1495427479887">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Grouping by modules
    </p>
    <p>
      and components
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1495427483513" ID="ID_1557018079" MODIFIED="1495427795088">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Build generic facility
    </p>
    <p>
      to determine current component
    </p>
    <p>
      and current module
    </p>
  </body>
</html>
</richcontent>
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1557018079" ENDARROW="Default" ENDINCLINATION="1082;0;" ID="Arrow_ID_1724656267" SOURCE="ID_1197950358" STARTARROW="None" STARTINCLINATION="1082;0;"/>
<node CREATED="1495427586262" ID="ID_1946518814" MODIFIED="1495427611247">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Enhance TestResultLogger
    </p>
    <p>
      for returning these
    </p>
  </body>
</html>
</richcontent>
<node CREATED="1495427678682" ID="ID_286318654" MODIFIED="1495427719333">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      static String currentComponent();
    </p>
    <p>
      static String currentModule();
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1495427721958" ID="ID_195224142" MODIFIED="1495427729989" TEXT="These are thread-local"/>
<node CREATED="1495427847120" ID="ID_1381399661" MODIFIED="1495427876310" TEXT="Engine will ensure these are maintained right"/>
</node>
<node CREATED="1495427615221" ID="ID_1100639226" MODIFIED="1495427669428">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      TestResultLogger is the facade
    </p>
    <p>
      of the framework to the script
    </p>
  </body>
</html>
</richcontent>
</node>
</node>
<node CREATED="1495427882651" ID="ID_459715736" MODIFIED="1495428017144" TEXT="Now WebUI"/>
</node>
<node CREATED="1495239193225" ID="ID_428517019" MODIFIED="1495239216449" TEXT="Clashing elements in different pages"/>
<node CREATED="1495236746990" ID="ID_1762954635" MODIFIED="1495236764919" TEXT="can this be extended to child frames?"/>
</node>
</node>
</map>
