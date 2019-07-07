/*
Requirements
------------
1. Flexibility for variations:
* cross-browser variations can widely vary in different runs or within a run itself
* a test case may run on same browser maybe multiple times: reruns for e.g.
2. Extensibility:
* for new OS-browser combinations: use the names if no logo provided; e.g. Edge, Opera
3. Predictability:
* Some order in reporting across test cases in the same report

Specifications
--------------
1. Summarize at global and test case level
* Just the counts

2. Detail at test case level
{ os: { browser: [ each detail ] } }

3. On reporting, use the meta order
*/
// below also will serve as a template for order of reporting
var meta = { // {name, logo; logo if not given, then name is shown}
  os: {
    windows: { name: 'Windows', logo: 'https://upload.wikimedia.org/wikipedia/commons/thumb/5/5f/Windows_logo_-_2012.svg/64px-Windows_logo_-_2012.svg.png' },
    linux: { name: 'Linux', logo: 'https://upload.wikimedia.org/wikipedia/commons/thumb/3/35/Tux.svg/256px-Tux.svg.png' },
    android: { name: 'Android', logo: 'https://upload.wikimedia.org/wikipedia/commons/thumb/d/d7/Android_robot.svg/256px-Android_robot.svg.png' },
    ios: { name: 'iOS', logo: '' } // Need to add iOS logo
  },
  browser: {
    ie: { name: 'IE', logo: 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/1b/Internet_Explorer_9_icon.svg/256px-Internet_Explorer_9_icon.svg.png' },
    chrome: { name: 'Chrome', logo: 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e2/Google_Chrome_icon_%282011%29.svg/512px-Google_Chrome_icon_%282011%29.svg.png' },
    firefox: { name: 'FireFox', logo: 'https://upload.wikimedia.org/wikipedia/commons/thumb/1/18/Firefox_icon.svg/64px-Firefox_icon.svg.png' },
    safari: { name: 'Safari', logo: 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/cc/Safari_rounded.svg/64px-Safari_rounded.svg.png' },
	phantomjs:{ name: 'PhantomJS', logo: 'https://upload.wikimedia.org/wikipedia/commons/4/41/Phantomjs-logo.png' }
  }
};

// summary:
var summary = {
};

var logs = {
};

 var start,finish; // checkMe

function summarize(summary, isStart, result, ts,skips) {

  var counts = summary["counts"] =(summary["counts"] || {
    totalTC: 0,
	total: 0,
    passed: 0,
    failed: 0,
    skips: 0
  });
  if (isStart){
    counts.total++;
	}
  else { // finish
	counts.skips=skips;
    if (result){
      counts.passed++;
	  }
    else{
      counts.failed++;}
	counts.totalTC=Number(counts.passed)+Number(counts.failed)+Number(counts.skips);

  }
  var times = summary[times] = (summary[times] || {
  });
  if (isStart)
    times[start] = times[start] || ts;
  else
    times[finish] = ts;
  // always overwrite
}


function addLog(ts, resId, tcId, tcName, os, browser, eventType, detail,skips) {
  var osId = os.toLowerCase(); // CHECKME javascript
  meta.os[osId] = meta.os[osId] || {
    name: os
  }

  var browserId = browser.toLowerCase();
  meta.browser[browserId] = meta.browser[browserId] || {
    name: browser
  }

 // var isStart =
  var isStart = eventType.match(/START/i);
  var passed = (isStart? null: detail.match(/passed|warning|done/i));



  // grand summary
  summarize(summary, isStart, passed, ts,skips);

 // var db = logs["tcId"] = (logs["tcId"] || {
 db = logs[tcName] = (logs[tcName] ||{
    id: tcId,
    name: tcName,
    summary: {}, // test case level summary
    results: {
      
   os: {
        browser: {
          resId: {
          // start, finish, startDetail, detail,
			start, finish, detail,
          }
        }
      }
      
    }
  });
  summarize(db.summary, isStart, passed, ts,skips);

  var resDb = db.results
  resDb = resDb[osId] = resDb[osId] || {}
  resDb = resDb[browserId] = resDb[browserId] || {}

  if (isStart) 
    resDb[resId] = {
      start: ts,
      startDetail: detail,
    }
  else  { // merge new value with isStart
    var start = resDb[resId]
    if (!start) {
   	// error("invalid finish log without start", arguments);
      return;
    }
    resDb[resId] = Object.assign(start, {
      finish: ts,
      result: detail
    })
  }
}

function buildReport(domEl) {
  if (!summary["counts"]) {
	  // domEl.innerHtml = "No test cases to report";
    return;
  }

  function buildHeading() {
  // var counts1 = summary;//|| {};
    return`
<table class='summaryTable'>
<tr>
<td class='totalText'>Total: ${summary["counts"].totalTC}</td>
<td class='passedText'>Passed: ${summary["counts"].passed}</td>
<td class='failedText'>Failed: ${summary["counts"].failed}</td>
<td class='skippedText'>Skipped: ${summary["counts"].skips}</td>
</tr>
</table>

<br><br>

<table class='overviewTable'>
<tr>
  <th class='tableHeading'>Test Case</th>
  <th class='tableHeading'>Total</th>
  <th class='tableHeading'>Passed</th>
  <th class='tableHeading'>Failed</th>
  <th class='tableHeading'>Execution Environment</th>
</tr>
`;
  }

  function buildRow(row) {
	return `
<tr>
<td>${row["name"]}</td>
<td class='totalText'>${row.summary["counts"].total}</td>
<td class='passedText'>${row.summary["counts"].passed}</td>
<td class='failedText'>${row.summary["counts"].failed}</td>
<td>${buildRunDetails(row)}</td></tr>
`
  }

  function buildRunDetails(row) {

    for (os in meta.os) {
	
	var r=row["results"]
      var sumos =r[os]
	 
      if (!sumos)
        continue;

      var s = "";
      var showOs = true;
      for (browser in meta.browser) {
        var sumbr = sumos[browser];
		        if (!sumbr)
          continue;

        var showBr = true;
        for ( var res in sumbr) {
          if (showOs) {
            showOs = false;
			s += `
			<table class='envTable'>
			<tr>
			<td>`;
            s += buildOs(meta.os[os]);
			s += `</td>`;
          }
		  console.log(JSON.stringify(sumbr));
          if (showBr) {
            showBr = false;
			s +=`<td>`;
            s += buildBr(meta.browser[browser], sumbr[res]);
			s +=`</td>`;
          }

          // s += buildResult(res);
        }
		
      }
	  s +=`</table>`
      showOs = false;
    }

    return s;
  }

  function buildOs(info){
	 if (info.logo)
      return `<img width='35px' height='35px' alt="${info.name}" title="${info.name}" src="${info.logo}">`
    return info.name;
	// return buildIcon(osDetails)
  }
  function buildBr(info, report){
	if (info.logo){
		JSON.stringify(report.result)
		if(report.result == "PASSED"){
			return `<a href="${report.startDetail}" style="text-decoration: none"><img width='20px' height='20px' alt="${info.name}" title="${info.name}" src="${info.logo}">
				<span class='successIndicator'>&#10004;</span></a>`
		}else if(report.result == "DONE"){
			return `<a href="${report.startDetail}" style="text-decoration: none"><img width='20px' height='20px' alt="${info.name}" title="${info.name}" src="${info.logo}">
				<span class='successIndicator'>&#10004;</span></a>`
		}else{
			return `<a href="${report.startDetail}" style="text-decoration: none"><img width='20px' height='20px' alt="${info.name}" title="${info.name}" src="${info.logo}">
				<span class='failureIndicator'>&#x2718;</span></a>`
		}
	}else{
		if(report.result == "PASSED"){
			return `<a href="${report.startDetail}" style="text-decoration: none">info.name
				<span class='successIndicator'>&#10004;</span></a>`
		}else if(report.result == "DONE"){
			return `<a href="${report.startDetail}" style="text-decoration: none">info.name
				<span class='successIndicator'>&#10004;</span></a>`
		}else{
			return `<a href="${report.startDetail}" style="text-decoration: none">info.name
				<span class='failureIndicator'>&#x2718;</span></a>`
		}
	}
    return info.name;
    
	// return buildIcon(browserDet)
  }
  function buildResult(res){
  // TBD
  }
  
  function buildIcon(info) {
      if (info.logo)
      return `<img width="5%" name="${info.name}" title="${info.name}" src="${info.logo}">`
    return info.name;
  }

  var s = buildHeading();

  for (var log in logs) {
    s += "\n" + buildRow(logs[log]);
  }
  s += "</table>"

return s;
  // domEl.innerHtml = s;
}
