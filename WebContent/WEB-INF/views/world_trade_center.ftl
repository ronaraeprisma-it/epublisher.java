<!DOCTYPE html>
<html>
<head>
	<title>Narrowcasting WTC</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="${redirect_base}/static/css/prisma-it/wtc-narrowcasting.css?v=${version}" type="text/css" />
</head>
<body>
	<iframe id="iframe1" scrolling="no" data-reset-attr="src" frameborder="0"></iframe>

	<div id="image">
		<img src="${redirect_base}/static/images/white.png" alt="" />
	</div>
	
	<div id="errorBackground">
		<img id="testscreen" src="${redirect_base}/static/images/wtc_bg.png" alt="" />
	</div>
	
	<div id="logoContainer">
		<div id="logo">
		</div>
		<div id="invertedLogo">
		</div>
	</div>
	
	<div id="list-headers">
	</div>
	
	<div id="list-headers-right">
	</div>
	
	<div id="wayfinderDirectionArrow">
		<img id="wayfinderArrowImg" src="${redirect_base}/static/images/prisma/wtc-arrow-wayfinder.png" alt="" />
	</div>
	
	<div id="title">
	</div>
	
	<div id="pagination">
	</div>
	
	<div id="list-separator">
	</div>
	
	<div id="content">
	</div>
		
	<div id="noConnection">
		<img id="testscreen" src="${redirect_base}/static/images/wtc_bg.png" alt="" />
	</div>
	
	<div id="noBroadcast">
		<img id="testscreen" src="${redirect_base}/static/images/wtc_bg.png" alt="" />
	</div>
	
	<div id="videoUnavailable">
		<img id="videoUnavailableImg" src="${redirect_base}/static/images/video_unavailable.png" alt="" />
	</div>

    <!-- scherm id debug info -->
	<div id="schermid-debug" class="hide">		
		<div id="schermid-debug-info"></div>
	</div>
	
	<script type="text/javascript" src="${redirect_base}/static/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${redirect_base}/static/js/narrowcasting-wtc.js?v=${version}"></script>
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