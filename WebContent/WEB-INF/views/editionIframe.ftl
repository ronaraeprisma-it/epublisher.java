<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<style type="text/css">
		<#include "varia.css">
	</style>
</head>

<body>
<div id="mainContainer">

	<h1>${edition.publication.name}</h1>
	
	<#setting locale="nl_NL">
	<h2>Editie ${edition.publicationDate?string("d MMMM yyyy")}<h2>
	
	<#if articleWrappers??>
	<ul>
		<#list articleWrappers as articleWrapper>
		<li>
			<a href="${redirect_base}/content/iframe/article/${articleWrapper.id?c}" class="li">${articleWrapper.article.title}</a>
		</li>
		</#list>
	</ul>
	 </#if>		

	<h3>Archief</h3>

	<ul>
		<#list currentYear..2011 as curYear>
		<li>
			<a href="${redirect_base}/content/iframe/publication/${edition.publication.id?c}/archive/${curYear?string.computer}" class="li">${curYear?string.computer}</a>
		</li>
		</#list>
	</ul>

</div>
</body>
</html>