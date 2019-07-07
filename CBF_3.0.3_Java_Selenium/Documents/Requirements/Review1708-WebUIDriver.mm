<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1503796500237" ID="ID_1505828584" MODIFIED="1503796509766" TEXT="Review 1708">
<node CREATED="1503796516350" ID="ID_1032799755" MODIFIED="1503796553045" POSITION="right" TEXT="WebUIDriver">
<node CREATED="1503796552953" ID="ID_1539799341" MODIFIED="1503796566542" TEXT="Common">
<node CREATED="1503796523851" ID="ID_1939060692" MODIFIED="1503797243400">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      When NonSpecific, do not pass it
    </p>
    <p>
      It is default
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="full-2"/>
<node CREATED="1503796717929" ID="ID_1618676057" MODIFIED="1503796846515">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      &#160;&#160;failed(&quot;Open &quot; + &quot; &quot; + url, &quot;Should open &quot; + url, url + &quot; &quot;
    </p>
    <p>
      &#160;&#160;+ &quot;opened unsuccessfully&quot;<b><i><strike><font color="#ff0033">,FailureCause.NonSpecific</font></strike></i></b>);
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1503796586231" ID="ID_1242559051" MODIFIED="1503797239481" TEXT="Exceptions are being eaten away">
<icon BUILTIN="full-1"/>
<node CREATED="1503796956758" ID="ID_307862763" MODIFIED="1503797059041">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      } catch (Exception e) {
    </p>
    <p>
      &#160;&#160;failed(&quot;Open &quot; + &quot; &quot; + url, &quot;Should open &quot; + url, url + &quot; &quot;
    </p>
    <p>
      &#160;&#160;+ &quot;opened unsuccessfully&quot;,FailureCause.NonSpecific);
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_cancel"/>
</node>
<node CREATED="1503796956758" ID="ID_1733661731" MODIFIED="1503797234156">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      } catch (Exception e) {
    </p>
    <p>
      &#160;&#160;handleError(&quot;Opening url: &quot;, url, e);
    </p>
    <p>
      }
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="button_ok"/>
<node CREATED="1503796612981" ID="ID_800621114" MODIFIED="1503797187264">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      TestResultLogger should mark it
    </p>
    <p>
      as a NonSpecific Error
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1503797200148" ID="ID_729069682" MODIFIED="1503797211931" TEXT="It should also call Logger"/>
<node CREATED="1503797189342" ID="ID_1034248950" MODIFIED="1503797226471" TEXT="Logger  should also log stacktrace"/>
</node>
</node>
<node CREATED="1503797299408" ID="ID_1996586428" MODIFIED="1503799994984">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Using passed/failed
    </p>
    <p>
      should be in caller
    </p>
  </body>
</html></richcontent>
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1996586428" ENDARROW="Default" ENDINCLINATION="189;0;" ID="Arrow_ID_722866068" SOURCE="ID_1415603714" STARTARROW="None" STARTINCLINATION="189;0;"/>
<icon BUILTIN="full-1"/>
<node CREATED="1503797337500" ID="ID_1071013968" MODIFIED="1503797403124">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Not all check have pass/fail
    </p>
  </body>
</html></richcontent>
<node CREATED="1503797591359" ID="ID_877505483" MODIFIED="1503797595433" TEXT="handleAlert"/>
<node CREATED="1503797596041" ID="ID_1278152057" MODIFIED="1503797607485">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      checkToolTip
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1503797403887" ID="ID_788444739" MODIFIED="1503797416175" TEXT="No actions have a pass"/>
</node>
<node CREATED="1503797426418" ID="ID_1933343760" MODIFIED="1503800021611">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      getControl()
    </p>
    <p>
      returning null?
    </p>
  </body>
</html></richcontent>
<icon BUILTIN="full-1"/>
<node CREATED="1503797500595" ID="ID_1878144347" MODIFIED="1503800096979">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      NullpointerException galore
    </p>
  </body>
</html></richcontent>
<linktarget COLOR="#b0b0b0" DESTINATION="ID_1878144347" ENDARROW="Default" ENDINCLINATION="293;0;" ID="Arrow_ID_445387301" SOURCE="ID_1839966457" STARTARROW="None" STARTINCLINATION="293;0;"/>
</node>
</node>
</node>
<node CREATED="1503797644710" ID="ID_634038972" MODIFIED="1503800015167" TEXT="Specific">
<icon BUILTIN="full-2"/>
<node CREATED="1503797649386" ID="ID_697572421" MODIFIED="1503797653410" TEXT="checkToolTip">
<node CREATED="1503797657754" ID="ID_872838608" MODIFIED="1503797664111" TEXT="ToolTip or Tooltip?"/>
<node CREATED="1503797664610" ID="ID_1271555286" MODIFIED="1503797671587" TEXT="Bug?">
<node CREATED="1503797710382" ID="ID_1467808292" MODIFIED="1503797875596">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      String value = getAttribute(elementName, <font color="#ff0000">expectedText</font>); <i>&lt;-- why <u>expectedText passed as attributeName</u>?</i>
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1503798441947" ID="ID_465816721" MODIFIED="1503798477040">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      getControl vs
    </p>
    <p>
      getControlDriver
    </p>
  </body>
</html></richcontent>
<node CREATED="1503798458575" ID="ID_1179924480" MODIFIED="1503798472773">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Not quite clear when
    </p>
    <p>
      to use what
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
</node>
<node CREATED="1503799936058" ID="ID_929575664" MODIFIED="1503799951766" POSITION="right" TEXT="WebElementDriver">
<node CREATED="1503799966588" ID="ID_1415603714" MODIFIED="1503799994984">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Using passed/failed
    </p>
    <p>
      should be in caller
    </p>
  </body>
</html>
</richcontent>
<arrowlink DESTINATION="ID_1996586428" ENDARROW="Default" ENDINCLINATION="189;0;" ID="Arrow_ID_722866068" STARTARROW="None" STARTINCLINATION="189;0;"/>
<icon BUILTIN="full-1"/>
</node>
<node CREATED="1503800067948" ID="ID_1839966457" MODIFIED="1503800096979">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      getControl()
    </p>
    <p>
      returning null
    </p>
  </body>
</html>
</richcontent>
<arrowlink DESTINATION="ID_1878144347" ENDARROW="Default" ENDINCLINATION="293;0;" ID="Arrow_ID_445387301" STARTARROW="None" STARTINCLINATION="293;0;"/>
<icon BUILTIN="full-1"/>
<node CREATED="1503800115006" ID="ID_1292828949" MODIFIED="1503800212954">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      if (el == null)
    </p>
    <p>
      &#160;&#160;handleError(elementName + &quot; not found&quot;) &lt;-- FailureCause?
    </p>
  </body>
</html>
</richcontent>
</node>
<node CREATED="1503800248521" ID="ID_555674345" MODIFIED="1503800468844">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Affects:
    </p>
    <p>
      setValue
    </p>
    <p>
      getValue &lt;-- catch(Exception e): seems dubious
    </p>
    <p>
      click
    </p>
    <p>
      clear
    </p>
    <p>
      getDropDownOptions
    </p>
    <p>
      getAttribute
    </p>
    <p>
      setAttribute
    </p>
    <p>
      mouseOver
    </p>
  </body>
</html>
</richcontent>
</node>
</node>
</node>
<node CREATED="1503798481850" ID="ID_1047788819" MODIFIED="1503798499958" POSITION="right">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      AppiumWebDriver
    </p>
  </body>
</html></richcontent>
<node CREATED="1503798515841" ID="ID_1578196330" MODIFIED="1503798902797">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Rename as
    </p>
    <p>
      AppiumWebElementDriver
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1503798891084" ID="ID_1411314792" MODIFIED="1503798924794" TEXT="This is not designed right">
<node CREATED="1503798566833" ID="ID_1837155233" MODIFIED="1503798581213">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      WHat happens when
    </p>
    <p>
      &#160;Ng on Appium?
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1503799051111" ID="ID_21573076" MODIFIED="1503799075129">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      launch
    </p>
    <p>
      closeBrowsers must not be in this class
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1503799527787" ID="ID_1261707224" MODIFIED="1503799537185" TEXT="It has to be specific to an elementName"/>
<node CREATED="1503799298986" ID="ID_932652675" MODIFIED="1503799304763" TEXT="setValue is not right"/>
<node CREATED="1503799319121" ID="ID_37639942" MODIFIED="1503799349167">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      By adding optional Wait
    </p>
    <p>
      at WebElementDriver, many methods can go
    </p>
  </body>
</html></richcontent>
</node>
</node>
<node CREATED="1503799194742" ID="ID_574234160" MODIFIED="1503799201395" TEXT="This is not implemented right">
<node CREATED="1503799084609" ID="ID_1847643007" MODIFIED="1503799219382">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      clear() returns boolean
    </p>
    <p>
      super.clear() is void
    </p>
  </body>
</html></richcontent>
</node>
<node CREATED="1503799307929" ID="ID_1728803530" MODIFIED="1503799314546" TEXT="super() calls"/>
</node>
</node>
<node CREATED="1503800530471" ID="ID_1339957765" MODIFIED="1503800537165" POSITION="right" TEXT="NgWebUIDriver">
<node CREATED="1503800539563" ID="ID_1323252495" MODIFIED="1503800548478" TEXT=" Rename as NgWebElementDriver"/>
<node CREATED="1503800551859" ID="ID_657568971" MODIFIED="1503800560254" TEXT="More on redesign"/>
</node>
</node>
</map>
