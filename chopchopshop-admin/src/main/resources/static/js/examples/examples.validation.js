(function() {

	'use strict';

	// basic
	$("#form").validate({
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
	var $summaryForm = $("#catalogForm");
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
	
//	jQuery.each( errorMap, function( key, value ) {
//		console.log( "key", key, "value", value );
//		var flag = false;
//		if( key == 'excel')	{			  
//			$("#uneditable-input").addClass("form-control error");
//			flag = true;
//		}
//	});
//	if(!flag) {
//		$("#uneditable-input").removeClass("form-control error");
//	}
//		
//    this.defaultShowErrors();

	// checkbox, radio and selects
	$("#chk-radios-form, #selects-form").each(function() {
		$(this).validate({
			highlight: function(element) {
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			success: function(element) {
				$(element).closest('.form-group').removeClass('has-error');
			},
			errorPlacement: function( error, element ) {
				var placement = $(element).parent();
				
				placement.append(error);
			}
		});
	});

	// Select 2 Fields
	$('select[data-plugin-selectTwo]').on('change', function() {
	    $(this).valid();
	});

}).apply(this, [jQuery]);