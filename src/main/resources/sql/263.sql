select updatespecificschema('viecuri',
'
UPDATE templatenarrowcasting
	SET enabletitle = true
	WHERE htmltemplate = ''default-wayfinder'';
'
);
