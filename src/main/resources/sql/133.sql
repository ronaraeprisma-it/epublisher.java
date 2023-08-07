--change template name from Grey to Roze
UPDATE alrijne_ziekenhuis.templatenarrowcasting
	SET name='Slide + Portrait Image Roze'
	WHERE name = 'Slide + Portrait Image Grey';

UPDATE alrijne_ziekenhuis.templatenarrowcasting
	SET htmltemplate='slide_portrait_image_roze'
	WHERE htmltemplate = 'slide_portrait_image_grey';
	
--change template name from Blue to Blauw	
UPDATE alrijne_ziekenhuis.templatenarrowcasting
	SET name='Slide + Portrait Image Blauw'
	WHERE name = 'Slide + Portrait Image Blue';

UPDATE alrijne_ziekenhuis.templatenarrowcasting
	SET htmltemplate='slide_portrait_image_blauw'
	WHERE htmltemplate = 'slide_portrait_image_blue';
	
--change template name from Orange to Oranje	
UPDATE alrijne_ziekenhuis.templatenarrowcasting
	SET name='Slide + Portrait Image Oranje'
	WHERE name = 'Slide + Portrait Image Orange';

UPDATE alrijne_ziekenhuis.templatenarrowcasting
	SET htmltemplate='slide_portrait_image_oranje'
	WHERE htmltemplate = 'slide_portrait_image_orange';
	
--change template name from Left to Links	
UPDATE alrijne_ziekenhuis.templatenarrowcasting
	SET name='Slide + Portrait Image Links'
	WHERE name = 'Slide + Portrait Image Left';

UPDATE alrijne_ziekenhuis.templatenarrowcasting
	SET htmltemplate='slide_portrait_image_links'
	WHERE htmltemplate = 'slide_portrait_image_left';

--change template name from Right to Rechts	
UPDATE alrijne_ziekenhuis.templatenarrowcasting
	SET name='Slide + Portrait Image Rechts'
	WHERE name = 'Slide + Portrait Image Right';

UPDATE alrijne_ziekenhuis.templatenarrowcasting
	SET htmltemplate='slide_portrait_image_rechts'
	WHERE htmltemplate = 'slide_portrait_image_right';
