UPDATE zorggroep_almere.templatenarrowcasting
	SET htmltemplate='zga-fullscreen-Image'
	WHERE htmltemplate = 'fullscreen-Image';

UPDATE zorggroep_almere.templatenarrowcasting
	SET htmltemplate='zga-video-fullscreen'
	WHERE htmltemplate = 'video-fullscreen';

UPDATE zorggroep_almere.templatenarrowcasting
	SET htmltemplate='zga-website-fullscreen'
	WHERE htmltemplate = 'website-fullscreen';
	
UPDATE medi_fysio.templatenarrowcasting
	SET htmltemplate='medi-fysio-website-fullscreen'
	WHERE htmltemplate = 'website-fullscreen';