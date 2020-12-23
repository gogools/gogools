(function() {

	'use strict';

	// basic
	$("form.validateMe").validate({
		
		highlight: function( label ) {
			$(label).closest('.form-group').removeClass('has-success').addClass('has-error');
		},
		success: function( label ) {
			$(label).closest('.form-group').removeClass('has-error');
			label.remove();
		},
		errorPlacement: function( error, element ) {
			var placement = element.closest('.input-group');
			if (!placement.get(0)) {
				placement = element;
			}
			if (error.text() !== '') {
				placement.after(error);
			}
		}
	});

	// validation summary
	var $summaryForm = $("form.validateMeSummary");
	$summaryForm.validate({
		errorContainer: $summaryForm.find( 'div.validation-message' ),
		errorLabelContainer: $summaryForm.find( 'div.validation-message ul' ),
		wrapper: "li",
		showErrors: function(errorMap, errorList) {
			
			this.defaultShowErrors();
			
			if($("#excel").hasClass("error")) {
				
				$("#uneditable-input").addClass("form-control error");
			} 
			else {
				
				$("#uneditable-input").removeClass("form-control error");
			}
			
		    
		  }
	});

}).apply(this, [jQuery]);