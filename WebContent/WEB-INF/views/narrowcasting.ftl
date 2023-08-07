<!DOCTYPE html>
<html>
<head>
	<title>Narrowcasting</title>
	<meta charset="utf-8">
	<link rel="stylesheet" href="${redirect_base}/static/css/narrowcasting.css?v=${version}" type="text/css" />
</head>
<body>

	<video
		id="brightcovePlayer"
		data-account=${accountId}
		data-player=${playerId}
		data-embed="default"
		class="video-js"
		errors="false"
		controls></video>
		
		<video
		id="brightcovePlayer_2"
		data-account=${accountId}
		data-player=${playerId}
		data-embed="default"
		class="video-js"
		errors="false"
		controls></video>
	<script src="//players.brightcove.net/${accountId}/${playerId}_default/index.min.js"></script>

	<div id="nsBar">
		<span></span>
		<ul>
			<li></li>
		</ul>
	</div>

	<div id="nedtrain-multipage-menubar">
		<img id="nedtrain-multipage-logo" src="${redirect_base}/static/images/nslogo.svg" alt="" />
		<span></span>
		<ul>
			<li></li>
		</ul>
	</div>
	
	<video autoplay muted id="mediBg">
  <source src="${redirect_base}/static/videos/mch_foto_template.mp4" type="video/mp4">
  Your browser does not support HTML5 video.
</video>

<video autoplay muted id="mediFysioBg">
  <source src="${redirect_base}/static/videos/fysiotherapie_leusden.mp4" type="video/mp4">
  Your browser does not support HTML5 video.
</video>

<video autoplay muted id="aszVideo">
	<source src="${redirect_base}/static/videos/asz.mp4" type="video/mp4">
	Your browser does not support HTML5 video.
</video>

	<div id="bgblock"></div>

	<div id="ckContent"></div>

	<div id="bottomContent"></div>

	<iframe id="iframe1" scrolling="no" data-reset-attr="src" frameborder="0"></iframe>
	<iframe id="iframe2" scrolling="no" frameborder="0"></iframe>
	<iframe id="iframe3" scrolling="no" frameborder="0"></iframe>
	<iframe id="iframe4" scrolling="no" frameborder="0"></iframe>
	<iframe id="iframe5" scrolling="no" frameborder="0"></iframe>
	<iframe id="iframe6" scrolling="no" frameborder="0"></iframe>
	<iframe id="iframe7" scrolling="no" frameborder="0"></iframe>
	<iframe id="iframe8" scrolling="no" frameborder="0"></iframe>
	<iframe id="iframe9" scrolling="no" frameborder="0"></iframe>
	<iframe id="iframe10" scrolling="no" frameborder="0"></iframe>

	<div id="image">
		<img src="${redirect_base}/static/images/white.png" alt="" />
	</div>

	
	
	<div id="mediLogo">
	</div>
	
	<div id="clock">
		<canvas class="CoolClock:chunkySwiss:100"></canvas>
		<img id="clockpreview" src="${redirect_base}/static/images/clockpreview.png" alt="" />
	</div>

	<div id="twitter"></div>
	
	<div id="errorBackground">
		<img id="testscreen" src="${redirect_base}/static/images/nobroadcast.png" alt="" />
	</div>
	
	<div id="rssBottom">
		<div data-html2canvas-ignore="true" id="rssContainer"></div>
	</div>
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

	<div id="livebroadcastnotsupported">
		<span>NOTICE</span> Live broadcasts are not supported on this screen.
	</div>

	<div id="title-bar"></div>

	<div id="time-indicator"></div>
	
	<div id="date-clock">
		<div id="date-clock-date"></div>
		<div id="logo"><img src="${redirect_base}/static/images/nslogo.svg" alt="" /></div>
		<div id="date-clock-clock"></div>
		<div id="screen-location"></div>
	</div>
	
	<div id="alarm" class="alarm">
		<div><strong>Alarmnummer</strong><div class="alarm-number"></div></div>
		<p>	Algemeen alarmnummer</p>
		<p class="sm-red-75">BRAND</p>
		<ul>
			<li>Bel alarmnummer</li>
			<li>Tracht een begin van de brand te blussen</li>
			<li>Vlucht niet door de dichte rook</li>
			<li>Gebruik nooit de lift</li>
		</ul>
		<p class="sm-blue-75">ONTRUIMING</p>
		<ul>
			<li>Maak gebruik van de dichtbijzijnde nooduitgang</li>
			<li>Volg aanwijzingen van de ontruimings functionaris op</li>
			<li>Vlucht niet door de dichte rook</li>
			<li>Gebruik nooit de lift</li>
		</ul>
		<p class="sm-green-75">ONGEVAL</p>
		<ul>
			<li>Bel alarmnummer</li>
			<li>Blijf bij het slachtoffer tot er hulp is</li>
			<li>Denk aan de eigen veiligheid</li>
		</ul>
	</div>

	<#-- Disabling for now, it may be the cause of missing template content in some situations. SAAS-484 -->
	<#-- <div id="travel" class="hide">		
	    <table class="travel-vertrekstaat" cellspacing="0" rules="all" border="0" id="departuresGrid" style="border-width:0px;border-collapse:collapse;">
			<tbody id="travelBody">
			</tbody>
		</table>
	</div>	-->
	
	<div id="info" class="hide">
	</div>

    <!-- scherm id debug info -->
	<div id="schermid-debug" class="hide">		
		<div id="schermid-debug-info"></div>
	</div>
	
	<!--[if lte IE 9]><link rel="stylesheet" href="${redirect_base}/static/css/narrowcasting-ie.css?v=${version}" type="text/css" /><![endif]-->
	<script type="text/javascript" src="${redirect_base}/static/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${redirect_base}/static/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="${redirect_base}/static/js/jquery.jqplugin.1.0.2.min.js"></script>
 	<!--[if gt IE 10]>--><script type="text/javascript" src="${redirect_base}/static/js/coolclock.js"></script><!--<![endif]-->
	<script type="text/javascript" src="${redirect_base}/static/js/narrowcasting.js?v=${version}"></script>
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