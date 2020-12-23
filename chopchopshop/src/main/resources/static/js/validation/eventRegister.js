$(document).ready(

		function() {
			// Initialize form validation on the registration form.
			// It has the name attribute "registration"
			$("form[name='eventRegister']").validate({
				// Specify validation rules
				rules : {
					// The key name on the left side is the name attribute
					// of an input field. Validation rules are defined
					// on the right side
					name : {
	                        required: true,
	                        minlength: 3,
	                        maxlength: 50
	                	},
					lastname : {
	                        required: true,
	                        minlength: 5,
	                        maxlength: 50
		                },
					mothersname : {
	                        required: true,
	                        minlength: 5,
	                        maxlength: 50
		                },
					email : {
						required : true,
						// Specify that email should be validated
						// by the built-in "email" rule
						email : true
					},
					phone : "required",
					origin : "required",
					eventDesc : "required"
				},
				// Specify validation error messages
				messages : {
					name : {
	                        required: "Captura tu nombre",
	                        minlength: jQuery.validator.format("Necesitamos por lo menos {0} caracteres"),
	                        maxlength: jQuery.validator.format("Más de {0} caracteres son demasiados!")
		                },
					lastname : {
	                        required: "Captura tu apellido paterno",
	                        minlength: jQuery.validator.format("Necesitamos por lo menos {0} caracteres"),
	                        maxlength: jQuery.validator.format("Más de {0} caracteres son demasiados!")
		                },
					mothersname : {
	                        required: "Captura tu apellido materno",
	                        minlength: jQuery.validator.format("Necesitamos por lo menos {0} caracteres"),
	                        maxlength: jQuery.validator.format("Más de {0} caracteres son demasiados!") 
		                },
					email : "Captura tu correo electrónico",
					phone : "Captura tu teléfono",
					origin : "Dinos de donde nos visitas",
					eventDesc : "Selecciona que evento prefieres"
				},
				// Make sure the form is submitted to the destination defined
				// in the "action" attribute of the form when valid
				submitHandler : function(form) {
					
					$('#divErrors').hide();
					$(form).find(':submit').prop('disabled', true);
					$(form).find(':submit').html('Enviando...');

					$.ajax({
						type : "POST",
						url : '/event',
						data : $(form).serialize(),
						success : function(res) {
							
							if( res.includes("<div class='alert alert-secondary'>") ) {
							
								$('#registryError').html(res)
								$(form).find(':submit').prop('disabled', false);
								$(form).find(':submit').html('Enviar');
								$('#divErrors').show();
								return;
							}
							
							$('#confirm-section').html(res);
						},
						error : function(e) {

							$('#divErrors').show();
							$(form).find(':submit').prop('disabled', false);
							$(form).find(':submit').html('Enviar');
						}
					})
				}
			});
		});