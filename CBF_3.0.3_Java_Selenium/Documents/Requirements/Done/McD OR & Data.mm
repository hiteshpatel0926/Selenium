<map version="1.0.1">
<!-- To view this file, download free mind mapping software FreeMind from http://freemind.sourceforge.net -->
<node CREATED="1455180172489" ID="ID_1340775482" MODIFIED="1455283943504" TEXT="McD Approach">
<node CREATED="1455180231051" ID="ID_1328560008" MODIFIED="1455180237556" POSITION="right" TEXT="Prerequisites">
<node CREATED="1455180241155" ID="ID_257210941" MODIFIED="1455180290795" TEXT="As part of every build, there are 4 xmls available with data relevant to be used for image mapping, verification etc"/>
<node CREATED="1455180397338" ID="ID_1486271334" MODIFIED="1455180412393" TEXT="XMLs in scope">
<node CREATED="1455180297760" ID="ID_1097815363" MODIFIED="1455180367678">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      names-db
    </p>
    <p>
      product-db
    </p>
    <p>
      screen
    </p>
    <p>
      store-db
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1455180201152" ID="ID_575641763" MODIFIED="1455180206710" POSITION="right" TEXT="Requirements">
<node CREATED="1455180209261" ID="ID_780641662" MODIFIED="1455180608640" TEXT="Use xml data for image mapping"/>
<node CREATED="1455180626019" ID="ID_635768134" MODIFIED="1455180672611" TEXT="Use product specific details like long name, short name etc for verification"/>
</node>
<node CREATED="1455196355509" ID="ID_977802559" MODIFIED="1455196395967" POSITION="left" TEXT="Approach">
<node CREATED="1455196520177" ID="ID_1743965127" MODIFIED="1455271232155">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      OR xml generation
    </p>
  </body>
</html></richcontent>
<node CREATED="1455197738779" ID="ID_1734603326" MODIFIED="1455281475739">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Parse screen xml to get OR specific data
    </p>
    <p>
      title(filter out special characters)
    </p>
    <p>
      product code
    </p>
    <p>
      h
    </p>
    <p>
      v
    </p>
    <p>
      title
    </p>
  </body>
</html></richcontent>
<node CREATED="1455271238660" ID="ID_958500596" MODIFIED="1455284070214" TEXT="&lt;images&gt;&#xa;&lt;image name = &quot;img1&quot;&gt;&#xa;&lt;attributes&gt;&#xa;&#x9;&lt;title&gt;Image Title&lt;/title&gt;&#xa;&#x9;&lt;image ref&gt;img.png&lt;/image ref&gt;&#xa;&#x9;&lt;h&gt;1&lt;/h&gt;&#xa;&#x9;&lt;v&gt;1&lt;/v&gt;&#xa;&#x9;&lt;button no&gt;&lt;/button no&gt;&#xa;&#x9;&lt;x&gt;&lt;/x&gt;&#xa;&#x9;&lt;y&gt;&lt;/y&gt;&#xa;&lt;/attributes&gt;&#xa;&lt;/image&gt;&#xa;&lt;/images&gt;"/>
</node>
<node CREATED="1455286099699" ID="ID_389541898" MODIFIED="1455286135205" TEXT="What is the chance that button title will go for a change">
<icon BUILTIN="help"/>
</node>
</node>
<node CREATED="1455284195429" ID="ID_1847949364" MODIFIED="1455284224040" TEXT="As part of new build, images should be copied to suite path"/>
<node CREATED="1455197907475" ID="ID_1104104697" MODIFIED="1455522977784" TEXT="Designer Mapping">
<node CREATED="1455197916209" ID="ID_528673974" MODIFIED="1455286088943">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Should take logical name
    </p>
    <p>
      for image mapping from designer
    </p>
  </body>
</html>
</richcontent>
</node>
</node>
<node CREATED="1455197962589" ID="ID_1977250561" MODIFIED="1455197966103" TEXT="Scripts">
<node CREATED="1455198000754" ID="ID_1375982514" MODIFIED="1455198077034">
<richcontent TYPE="NODE"><html>
  <head>
    
  </head>
  <body>
    <p>
      Use logical name(title)
    </p>
    <p>
      tagged to image in wrapper call
    </p>
  </body>
</html></richcontent>
</node>
</node>
</node>
<node CREATED="1455281184679" ID="ID_1022188165" MODIFIED="1455281206224" POSITION="left" TEXT="Design">
<node CREATED="1455281266270" ID="ID_965714911" MODIFIED="1455517518903" TEXT="Object Maps">
<node CREATED="1455281299507" ID="ID_338450417" MODIFIED="1455281361260" TEXT="XMLObject Map">
<node CREATED="1455281362746" ID="ID_1447736769" MODIFIED="1455281380090" TEXT="readlocators">
<node CREATED="1455281394724" ID="ID_656563633" MODIFIED="1455281438550" TEXT="parse out xml OR file to read images and attributes"/>
</node>
<node CREATED="1455281381557" ID="ID_1302685402" MODIFIED="1455281391464" TEXT="properties">
<node CREATED="1455281485031" ID="ID_946479725" MODIFIED="1455281542872" TEXT="Returns a map of identification attributes like image ref, title, product code"/>
</node>
</node>
<node CREATED="1455284241730" ID="ID_887468908" MODIFIED="1455284321197" TEXT="New images to be added for verifications"/>
<node CREATED="1455284344441" ID="ID_1504780915" MODIFIED="1455284369213" TEXT="OR xml also might require updation when new objects are added"/>
<node CREATED="1455281208413" ID="ID_1820245685" MODIFIED="1455281259499" TEXT="Utility to parse screen.xml and generate output xml for image specific data for the agreed structure"/>
</node>
</node>
<node CREATED="1455284089488" ID="ID_1899137594" MODIFIED="1455284099440" POSITION="left" TEXT="Concerns">
<node CREATED="1455284101195" ID="ID_1740777794" MODIFIED="1455284392099" TEXT="Impact on new builds">
<icon BUILTIN="help"/>
</node>
</node>
</node>
</map>
