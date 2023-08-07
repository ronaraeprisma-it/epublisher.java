<!DOCTYPE html>
<html>
<head>
	<title>Narrowcasting</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="${redirect_base}/static/css/medisign/zga-narrowcasting.css?v=${version}" type="text/css" />
</head>
<body>
	<iframe id="iframe1" scrolling="no" data-reset-attr="src" frameborder="0"></iframe>
	<iframe id="iframe2" scrolling="no" frameborder="0"></iframe>

	<div id="image">
		<img src="${redirect_base}/static/images/white.png" alt="" />
	</div>
	
	<div id="secondImage">
		<img src="" alt="" />
	</div>
	
	<div id="errorBackground">
		<img id="testscreen" src="${redirect_base}/static/images/nobroadcast.png" alt="" />
	</div>
	
	<div id="rssBottom">
		<div data-html2canvas-ignore="true" id="rssContainer"></div>
	</div>
	
	<div id="rssPlaceholder"></div>
	
	<div id="time">
		<div id="clock-clock"></div>
	</div>
	
	<div id="cover">
	</div>
	
	<div id="noConnection">
		<img id="testscreen" src="${redirect_base}/static/images/nobroadcast.png" alt="" />
	</div>
	
	<div id="noBroadcast">
		<img id="testscreen" src="${redirect_base}/static/images/nobroadcast.png" alt="" />
	</div>
	
	<div id="videoUnavailable">
		<img id="videoUnavailableImg" src="${redirect_base}/static/images/video_unavailable.png" alt="" />
	</div>
	
	<div id="mask"></div>
	
	<div id="zgaContainer">
		<div id="title-bar"></div>
		<div id="ckContent"></div>
	</div>
	
    <!-- scherm id debug info -->
	<div id="schermid-debug" class="hide">		
		<div id="schermid-debug-info"></div>
	</div>
	
	<script type="text/javascript" src="${redirect_base}/static/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${redirect_base}/static/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${redirect_base}/static/js/jquery.jqplugin.1.0.2.min.js"></script>
	<script type="text/javascript" src="${redirect_base}/static/js/narrowcasting-zga.js?v=${version}"></script>
	<script type="text/javascript" src="${redirect_base}/static/js/generic-narrowcasting.js?v=${version}"></script>
	<#if preview?? && preview == "broadcast"><script type="text/javascript" src="${redirect_base}/static/js/narrowcasting-preview-broadcast.js?v=${version}"></script>
	<#elseif preview?? && preview == "playlist"><script type="text/javascript" src="${redirect_base}/static/js/narrowcasting-preview-playlist.js?v=${version}"></script>
	<#elseif preview?? && preview == "thumbnail">
	<script type="text/javascript" src="${redirect_base}/static/js/narrowcasting-thumbnail.js?v=${version}"></script>
	<script type="text/javascript" src="${redirect_base}/static/js/html2canvas.js"></script>
	<#else><script type="text/javascript" src="${redirect_base}/static/js/narrowcasting-play.js?v=${version}"></script></#if>
	
	<script>
		 <!-- videojs.options.children.loadingSpinner = false; -->
		   $('.vjs-loading-spinner').remove();  
	</script>
</body>
</html>