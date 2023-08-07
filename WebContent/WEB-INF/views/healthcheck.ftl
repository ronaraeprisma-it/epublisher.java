<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>ePublisher Health Check</title>

	<style type="text/css">
		.error {color: red;}
	</style>
</head>

<body>

	<h1>ePublisher Health Check</h1>

	<p>Test run starting on ${monitoringStartTimeStamp?datetime}.</p>

	<h2>Services availability</h2>

	<ul>
	<#list monitorResults as monitorResult>
		<li<#if monitorResult.success?? && !monitorResult.success> class="error"</#if>>${monitorResult.serviceName!} ${monitorResult.statusMessage!}</li>
	</#list>
	</ul>

	<p>All services checked.</p>

	<h2>Server memory</h2>

	<dl>

		<dt>Maximum</dt>
		<dd>${memoryMax?string("###.##")} MB</dd>

		<dt>Allocated</dt>
		<dd>${memoryAllocated?string("###.##")} MB (${memoryPercentAllocated?string("###.#")} %)</dd>

		<dt>Used</dt>
		<dd>${memoryUsed?string("###.##")} MB</dd>

		<dt>Free (Maximum)</dt>
		<dd>${memoryFreeTotal?string("###.##")} MB (${memoryPercentFreeTotal?string("###.#")} %)</dd>

		<dt>Free (Allocated)</dt>
		<dd>${memoryFreeAllocated?string("###.##")} MB (${memoryPercentFreeAllocated?string("###.#")} %)</dd>
	</dl>

	<p>Memory checked.</p> <p>Test run completed at ${monitoringEndTimeStamp?datetime}.</p>

</body>
</html>
