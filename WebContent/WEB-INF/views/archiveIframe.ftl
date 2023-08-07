<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<style type="text/css">
		<#include "varia.css">
	</style>
</head>

<body>
<div id="mainContainer">
	
	<h1>${publication.name}</h1>
	<#if editions??>
	<ul>
		<#list editions as edition>
		<li>
			<a href="${redirect_base}/content/iframe/edition/${edition.id?c}" class="li">${edition.publicationDate?string("dd MMM yyyy")}</a>
		</li>
		</#list>
	</ul>
	</#if>
	
	<h3>Archief</h3>

	<ul>
		<#list currentYear..2011 as curYear>
		<li>
			<a href="${redirect_base}/content/iframe/publication/${publication.id?c}/archive/${curYear?string.computer}" class="li">${curYear?string.computer}</a>
		</li>
		</#list>
	</ul>

</div>
</body>
</html>