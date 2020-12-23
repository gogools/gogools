package com.app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;

import com.app.domain.Usuario;
import com.app.forms.AlumnoForm;

@Component
public class InitDatabasa {

	@Bean 
    CommandLineRunner init(MongoOperations operations) { 
		
		return args -> {
			
			operations.dropCollection(Usuario.class);
			
			AlumnoForm af = new AlumnoForm();
			af.setNombre("Juan");
			af.setAp_p("Perez");
			af.setAp_m("PerezM");
			af.setEmail("jperez@mail.com");
			af.setPass("p455w0rd");
			af.setBoleta("99090001");
			
			operations.save( Usuario.createAlumno( af ) );

			operations.findAll(Usuario.class)
						.forEach(usr -> { 
	       					System.out.println(usr.toString());
			}); 
		};
	}
}
