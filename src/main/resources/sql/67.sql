Delete from zorggroep_almere.templatenarrowcasting_containerarea where templatenarrowcasting_id = (select id from zorggroep_almere.templatenarrowcasting where  name = 'JGZ template' ) and containerareas_id = (select containerarea_id from zorggroep_almere.containerarea_allowedcontent ac
 inner join zorggroep_almere.templatenarrowcasting_containerarea tc 
on ac.containerarea_id = tc.containerareas_id where tc.templatenarrowcasting_id = (select id from zorggroep_almere.templatenarrowcasting where  name = 'JGZ template') and ac.allowedcontent = 'afbeelding');

