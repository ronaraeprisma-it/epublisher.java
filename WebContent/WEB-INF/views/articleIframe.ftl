<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
	<style type="text/css">
		<#include "varia.css">
	</style>
</head>

<body>
<div id="mainContainer">
	
	<h1>${articleWrapper.article.title}</h1>
	
	<div id="articleContent">
		<#if articleWrapper.thumbnailImage ??>
			<img src="${articleWrapper.thumbnailImage.url}" width="${articleWrapper.thumbnailImage.width}" height="${articleWrapper.thumbnailImage.height}" align="left" />
		</#if>
		${articleWrapper.article.content}
	</div>
	
	<a class="back" href="javascript:history.go(-1)">terug</a>
	
</div>
</body>
</html>