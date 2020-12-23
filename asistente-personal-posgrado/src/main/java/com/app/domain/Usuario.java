package com.app.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.app.enums.UserTypeEnum;
import com.app.forms.AlumnoForm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document
@EqualsAndHashCode(callSuper = false)
public class Usuario extends Bitacora {

	@Id
	private String			id;

	private String			nombre;
	private String			ap_p;
	private String			ap_m;
	private String			email;
	private String			pass;

	private UserTypeEnum	usertype;

	private Alumno				alumnoData;
	private Maestro				maestroData;
	
	public static Usuario createAlumno( AlumnoForm form) {
		
		Usuario u = new Usuario();
		
		u.setNombre	( form.getNombre()	);
		u.setAp_p	( form.getAp_p() 	);
		u.setAp_m	( form.getAp_m()	);
		u.setEmail	( form.getEmail()	);
		u.setPass	( form.getPass()	);
		u.setUsertype( UserTypeEnum.ALUMNO );
		
		Alumno a = new Alumno(form.getBoleta(), 0f);
		u.setAlumnoData( a );
		
		return u;
	}
}
