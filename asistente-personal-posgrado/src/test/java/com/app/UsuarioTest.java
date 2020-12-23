package com.app;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.app.repository.UsuarioRepository;

@RunWith(SpringRunner.class)
@DataMongoTest 
public class UsuarioTest {
	
	@Autowired
	UsuarioRepository usrRepo;
	
	@Autowired
	MongoOperations operations;
	
	/*
	@Before
	public void setUp() {
		
		operations.dropCollection(Usuario.class);
		operations.save(new Usuario("Daniel", "Pulido", UserTypeEnum.SUPER));
		operations.save(new Usuario("Marisa", "Canela", UserTypeEnum.MAESTRO));
		operations.save(new Usuario("Marisa", "Ardillita", UserTypeEnum.MAESTRO));
		operations.save(new Usuario("Aldo", "Canela", UserTypeEnum.COORDINADOR));
		operations.save(new Usuario("Control", "Escolar", UserTypeEnum.ADMIN));
		operations.save(new Usuario("Juan", "Perez", UserTypeEnum.ALUMNO));
		
		operations.findAll(Usuario.class).forEach(x -> System.out.println(x.toString()));
	}

	@Test
	public void basicUsuarioTesting() {
		
		Usuario u = new Usuario( "nombre", "apParterno", UserTypeEnum.ALUMNO );
	
		assertThat(u.getNombre(), is("nombre"));
	}
	*/
}
