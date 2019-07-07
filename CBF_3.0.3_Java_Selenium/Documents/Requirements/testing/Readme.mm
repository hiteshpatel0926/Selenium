<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1503633711676" ID="ID_829763929" MODIFIED="1503633753742">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Test setup
    </p>
    <p>
      for CBF
    </p>
  </body>
</html></richcontent>
<node CREATED="1503633768931" ID="ID_1011769528" MODIFIED="1503633773543" POSITION="right" TEXT="Requirements">
<node CREATED="1503633775628" ID="ID_1665730440" MODIFIED="1503633857581">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Update present
    </p>
    <p>
      samples
    </p>
  </body>
</html></richcontent>
<node CREATED="1503633966662" ID="ID_1895698886" MODIFIED="1503633985722">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Move present samples
    </p>
    <p>
      into a Test\Samples folder
    </p>
  </body>
</html></richcontent>
<node CREATED="1503633994129" ID="ID_1999158972" MODIFIED="1503634297768">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Dummy
    </p>
    <p>
      Dummy1
    </p>
    <p>
      Sample_Appium
    </p>
    <p>
      Sample_AutoIT
    </p>
    <p>
      Sample_EggPlant
    </p>
    <p>
      Sample_Ng
    </p>
    <p>
      Sample_seetest
    </p>
    <p>
      Sample_Selenium
    </p>
    <p>
      Selenium-Scriptless
    </p>
    <p>
      testng
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1503634108652" ID="ID_16129069" MODIFIED="1503634142710">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Update references to
    </p>
    <p>
      old class names
    </p>
  </body>
</html></richcontent>
<node CREATED="1503634145096" ID="ID_627878060" MODIFIED="1503634163858">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      ExcelTestAccess
    </p>
    <p>
      instead of ExcelFactory etc
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1503635445984" ID="ID_1186624745" MODIFIED="1503635451098" TEXT="Remove obsolete ones">
<node CREATED="1503634328512" ID="ID_1774308070" MODIFIED="1503634365449" TEXT="testng"/>
</node>
</node>
<node CREATED="1503634318300" ID="ID_109426004" MODIFIED="1503634361103">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Refine the samples
    </p>
    <p>
      to make it more friendly
    </p>
  </body>
</html></richcontent>
<node CREATED="1503635693313" ID="ID_1806272314" MODIFIED="1503635711077">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      MasterConfig.xml
    </p>
  </body>
</html></richcontent>
<node CREATED="1503636173867" ID="ID_1869890117" MODIFIED="1503636239405" TEXT="Remove unnecessary stuff">
<node CREATED="1503636293077" ID="ID_1184540800" MODIFIED="1503894790553">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      BaseModuleDriver
    </p>
    <p>
      BaseWebAppDriver
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1503636480390" ID="ID_1490659447" MODIFIED="1503636485368" TEXT="EggUIDriver"/>
</node>
<node CREATED="1503636241850" ID="ID_1925371743" MODIFIED="1503637606229">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Simplify using
    </p>
    <p>
      <i>convention over configuration</i>
    </p>
  </body>
</html></richcontent>
<node CREATED="1503636607448" ID="ID_1335199179" MODIFIED="1503637571462" TEXT="Browser">
<icon BUILTIN="idea"/>
<node CREATED="1503637283360" ID="ID_1443218806" MODIFIED="1503637565778">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      WebAppDriver::getBrowserConfig
    </p>
    <p>
      requires cleanup
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1503637434262" ID="ID_72674720" MODIFIED="1503637539832" TEXT="Use className as the id">
<node CREATED="1503637481006" ID="ID_1382005974" MODIFIED="1503637620710">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      CHROME -&gt; Chrome
    </p>
    <p>
      IE -&gt; same
    </p>
    <p>
      FIREFOX -&gt; Firefox
    </p>
    <p>
      APPIUM -&gt; Appium
    </p>
    <p>
      REMOTE -&gt; Remote
    </p>
    <p>
      PhantomJS -&gt; same
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1503637303554" ID="ID_1800712688" MODIFIED="1503637359864">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      If browser is not configured,
    </p>
    <p>
      a base Map with cbfx.ui.browser.&lt;className&gt; can be used.
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1503638886267" ID="ID_1356887108" MODIFIED="1503638890100" TEXT="Appium">
<node CREATED="1503894863250" ID="ID_1451499856" MODIFIED="1503894879155" TEXT="See Appium Refinements specified separately"/>
</node>
</node>
</node>
<node CREATED="1503636379213" ID="ID_817640621" MODIFIED="1503636418221" TEXT="Remove hard-coded values">
<node CREATED="1503636421626" ID="ID_768876480" MODIFIED="1503636449933" TEXT="Replace by TBD"/>
<node CREATED="1503637581987" ID="ID_378415548" MODIFIED="1503638688817">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Things which still have
    </p>
    <p>
      TBD will throw error
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</node>
</node>
</node>
</map>
