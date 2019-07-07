<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1494321482596" ID="ID_768138248" MODIFIED="1494321758979">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Review: 1705
    </p>
    <p>
      Major: 0300 version
    </p>
    <p>
      Minor: Cleanups also
    </p>
  </body>
</html></richcontent>
<node CREATED="1494321530451" ID="ID_1789632833" MODIFIED="1494321541759" POSITION="right" TEXT="Context">
<node CREATED="1494321579654" ID="ID_429878948" MODIFIED="1494321597390">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Major changes already:
    </p>
    <p>
      so, be bold
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1494321598628" ID="ID_169432335" MODIFIED="1494321608065" TEXT="Identify minor fixes also"/>
</node>
<node CREATED="1494321758936" ID="ID_1273611684" MODIFIED="1494321760542" POSITION="left" TEXT="cbf">
<node CREATED="1494388717444" ID="ID_887206745" MODIFIED="1494388722115" TEXT="general">
<node CREATED="1494388729226" ID="ID_292786701" MODIFIED="1494419266703" TEXT="Rename *Access to *Factory">
<icon BUILTIN="button_ok"/>
<node CREATED="1494388742431" ID="ID_638458533" MODIFIED="1494388753089" TEXT="DataAccess to DataFactory"/>
<node CREATED="1494388754225" ID="ID_1177065644" MODIFIED="1494388770566" TEXT="Test"/>
</node>
</node>
<node CREATED="1494321615894" ID="ID_1648246043" MODIFIED="1494321754961" TEXT="engine">
<node CREATED="1494321763495" ID="ID_1796876689" MODIFIED="1494321767683" TEXT="TestResultTracker">
<node CREATED="1494321799007" ID="ID_1827669102" MODIFIED="1496378065548" TEXT="public void log(ResultType rsType, Object details) {">
<icon BUILTIN="full-3"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1494321818073" ID="ID_1987453581" MODIFIED="1494323224306">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Change this rigid logic
    </p>
    <p>
      for handling log(set entityDetails)
    </p>
    <p>
      of testcase entity
    </p>
  </body>
</html></richcontent>
<node CREATED="1494321867848" ID="ID_756790188" MODIFIED="1494322276000">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;if (leafResult.entityType == EntityType.TESTCASE) { // details is
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;try {
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;if (((Map) details).get("actual").toString().contains("Initialization error")) {
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;leafResult.add(rsType, (Map) details);
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;return;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;}
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;} catch (Exception e) {
    </p>
    <p>
      
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;}
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;// TestCase
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;leafResult.entityDetails = details;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;return;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;}
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_cancel"/>
</node>
<node CREATED="1494322173308" ID="ID_377163250" MODIFIED="1494322278572">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;if (leafResult.entityType == EntityType.TESTCASE) {
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;try {
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;leafResult.entityDetails = (TestCase)details; // TestCase has been formed by TCMaker
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;return;
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;} catch(ClassCastException ce) {
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;// fall through to the default logic
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;&#xfffd;}
    </p>
    <p>
      &#xfffd;&#xfffd;&#xfffd;&#xfffd;}
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
</node>
</node>
</node>
</node>
</node>
<node CREATED="1494386131067" ID="ID_116237466" MODIFIED="1494386133529" TEXT="model">
<node CREATED="1494387081814" ID="ID_1574366937" MODIFIED="1496378020668">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Component Lib
    </p>
    <p>
      Organization Variations
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
<node CREATED="1494387223998" ID="ID_1363961339" MODIFIED="1494387327761">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Component
    </p>
    <p>
      Driver
    </p>
  </body>
</html></richcontent>
<node CREATED="1494387258038" ID="ID_1467657675" MODIFIED="1494387296832" TEXT="As separate class"/>
<node CREATED="1494387267100" ID="ID_87662903" MODIFIED="1494387273325" TEXT="Use Map instead of DataRow"/>
<node CREATED="1494387273766" ID="ID_644855552" MODIFIED="1494387280317" TEXT="No output; only input"/>
<node CREATED="1494387281253" ID="ID_1163967433" MODIFIED="1494387286509" TEXT="No input either"/>
</node>
<node CREATED="1494387309641" ID="ID_1925859769" MODIFIED="1494387319881">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Module
    </p>
    <p>
      Driver
    </p>
  </body>
</html></richcontent>
<node CREATED="1494387376610" ID="ID_1288826550" MODIFIED="1494387381186" TEXT="Conventional"/>
<node CREATED="1494387393797" ID="ID_1788758737" MODIFIED="1494387400403" TEXT="Only component drivers"/>
<node CREATED="1494387362010" ID="ID_794114642" MODIFIED="1494387365175" TEXT="Hybrid"/>
</node>
</node>
<node CREATED="1494388538459" ID="ID_1112378512" MODIFIED="1494388541719" TEXT="DataAccess">
<node CREATED="1494388543515" ID="ID_1624040759" MODIFIED="1494502021138" TEXT="selectRows()">
<icon BUILTIN="full-4"/>
<node CREATED="1494388552952" ID="ID_1803277074" MODIFIED="1494388579613">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      returning List&lt;Map&gt;
    </p>
    <p>
      not List&lt;DataRow&gt;
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1494387551372" ID="ID_1226852195" MODIFIED="1494388700272" TEXT="TestInstance">
<node CREATED="1494387559778" ID="ID_377511260" MODIFIED="1494417890548" TEXT="folderPath() should be removed">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_cancel"/>
<node CREATED="1494417918302" ID="Freemind_Link_124796886" MODIFIED="1494418258044" TEXT="Used in &#xa;cbf.runner.TestSetRunner.runTestSet(LinkedHashMap&lt;String, String&gt; runMap)"/>
</node>
<node CREATED="1494388490228" ID="ID_1576410544" MODIFIED="1494416496081" TEXT="description() not needed">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
</node>
</node>
<node CREATED="1494391178339" ID="ID_1565396010" MODIFIED="1494391187692" TEXT="TestStep">
<node CREATED="1494391190319" ID="ID_1876498397" MODIFIED="1494417778237" TEXT="&#x9;public String componentOutputValidation();">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_cancel"/>
<node CREATED="1494391197710" ID="ID_10139722" MODIFIED="1494391207249">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Remove
    </p>
    <p>
      It is not used
    </p>
  </body>
</html></richcontent>
<node CREATED="1494417674319" ID="Freemind_Link_1375955760" MODIFIED="1494417762948" TEXT="Used in cbf.reporting.ResultEventLogger"/>
</node>
</node>
<node CREATED="1494391226028" ID="ID_737226218" MODIFIED="1494417110038" TEXT="public String stepId();">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1494391235814" ID="ID_873384748" MODIFIED="1494416483943">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Remove
    </p>
    <p>
      why is this needed?
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1494391287771" ID="ID_1425652962" MODIFIED="1494417104922" TEXT="public ArrayList&lt;TestStep&gt; subSteps();">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1494391301152" ID="ID_1375988455" MODIFIED="1494391312486" TEXT="Should be List&lt;TestStep&gt;"/>
<node CREATED="1494391352593" ID="ID_505073957" MODIFIED="1494391421136">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Document what it returns
    </p>
    <p>
      when no substeps
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1494391428676" ID="ID_1524328885" MODIFIED="1494391444156" TEXT="Is there a better design?">
<icon BUILTIN="full-3"/>
</node>
</node>
</node>
</node>
</node>
<node CREATED="1494323236020" ID="ID_1053965088" MODIFIED="1494323237880" POSITION="left" TEXT="cbfx">
<node CREATED="1494385896990" ID="ID_1470135143" MODIFIED="1494385943963" TEXT="objectMaps">
<node CREATED="1494385903733" ID="ID_986313186" MODIFIED="1494419345924" TEXT="rename as objectMap">
<icon BUILTIN="full-2"/>
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1494385908693" ID="ID_697271153" MODIFIED="1494385954225" TEXT="revisit entire design">
<icon BUILTIN="full-1"/>
</node>
</node>
<node CREATED="1494323245805" ID="ID_67142599" MODIFIED="1494323248033" TEXT="eggplant">
<node CREATED="1494323250006" ID="ID_1176097809" MODIFIED="1494497785820" TEXT="constructor(objMap)">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1176097809" ENDARROW="Default" ENDINCLINATION="59;0;" ID="Arrow_ID_135202625" SOURCE="ID_291955288" STARTARROW="None" STARTINCLINATION="59;0;"/>
<icon BUILTIN="button_ok"/>
<node CREATED="1494323714137" ID="ID_1839773996" MODIFIED="1494323714137">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      // Use plugin directly
    </p>
  </body>
</html></richcontent>
<node CREATED="1494323348064" ID="ID_1514885710" MODIFIED="1494323703024">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      public EggUIDriver(Map objmapParams) {
    </p>
    <p>
      eggDriver = new EggDriver();
    </p>
    <p>
      objMap = (ObjectMap) PluginManager.getPlugin(objmapParams);
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1494323711828" ID="ID_209690520" MODIFIED="1494323741849">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      objMap shouldnt
    </p>
    <p>
      be static
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1494331829503" ID="ID_277591249" MODIFIED="1494386031566" TEXT="sikuli">
<node CREATED="1494386031510" ID="ID_1661174416" MODIFIED="1494497951472">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      constructor(objmap)
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
<node CREATED="1494331843858" ID="ID_291955288" MODIFIED="1494386087106">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      same changes
    </p>
    <p>
      as...
    </p>
  </body>
</html></richcontent>
<arrowlink DESTINATION="ID_1176097809" ENDARROW="Default" ENDINCLINATION="59;0;" ID="Arrow_ID_135202625" STARTARROW="None" STARTINCLINATION="59;0;"/>
</node>
</node>
</node>
<node CREATED="1494393301292" ID="ID_1885125919" MODIFIED="1494393303272" TEXT="autoit">
<node CREATED="1494393333224" ID="ID_9582282" MODIFIED="1494499341866" TEXT="AutoitDriver.java">
<icon BUILTIN="button_ok"/>
<node CREATED="1494393344343" ID="ID_57523499" MODIFIED="1494418884334" TEXT="rename as Autoit.java">
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1494393356795" ID="ID_49490188" MODIFIED="1494418888921" TEXT="move to utils">
<arrowlink DESTINATION="ID_108758056" ENDARROW="Default" ENDINCLINATION="71;0;" ID="Arrow_ID_560072788" STARTARROW="None" STARTINCLINATION="71;0;"/>
<icon BUILTIN="button_ok"/>
</node>
<node CREATED="1494393376724" ID="ID_362579018" MODIFIED="1494499334210" TEXT="Make method as static">
<icon BUILTIN="button_ok"/>
</node>
</node>
</node>
<node CREATED="1494393159947" ID="ID_496068049" MODIFIED="1494393163532" TEXT="general">
<node CREATED="1494393165861" ID="ID_1852571136" MODIFIED="1494393170216" TEXT="reorganize">
<node CREATED="1494393172357" ID="ID_108758056" MODIFIED="1494393369674" TEXT="utils">
<linktarget COLOR="#b0b0b0" DESTINATION="ID_108758056" ENDARROW="Default" ENDINCLINATION="71;0;" ID="Arrow_ID_560072788" SOURCE="ID_49490188" STARTARROW="None" STARTINCLINATION="71;0;"/>
<node CREATED="1494393182263" ID="ID_759272158" MODIFIED="1494393270594">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Move general
    </p>
    <p>
      utilities here
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</node>
</node>
<node CREATED="1494393427908" ID="ID_1472295641" MODIFIED="1494393432900" POSITION="left" TEXT="miscellaneous">
<node CREATED="1494393434380" ID="ID_682264919" MODIFIED="1494503194965" TEXT="Remove System.out.println">
<icon BUILTIN="button_ok"/>
</node>
</node>
</node>
</map>
