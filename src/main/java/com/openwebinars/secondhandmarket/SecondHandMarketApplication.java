package com.openwebinars.secondhandmarket;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.openwebinars.secondhandmarket.modelo.Producto;
import com.openwebinars.secondhandmarket.modelo.Usuario;
import com.openwebinars.secondhandmarket.servicios.ProductoServicio;
import com.openwebinars.secondhandmarket.servicios.UsuarioServicio;
import com.openwebinars.secondhandmarket.upload.StorageService;

@SpringBootApplication
public class SecondHandMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecondHandMarketApplication.class, args);
	}

	@Bean
	public CommandLineRunner initData(UsuarioServicio usuarioServicio, ProductoServicio productoServicio) {
		return args -> {

			Usuario usuario = new Usuario("Ismael", "Heras Salvador", null, "ismaelom83@gmail.com", "luismi");
			usuario = usuarioServicio.registrar(usuario);

			Usuario usuario2 = new Usuario("Jose", "Salvador Temprano", null, "jose88@gmail.com", "antonio");
			usuario2 = usuarioServicio.registrar(usuario2);

			
			List<Producto> listado = Arrays.asList(new Producto("Bicicleta de montaña", 100.0f,
					"https://www.buhobike.com/c/3001-medium_default_2x/rigidas-aluminio.jpg", usuario),
					new Producto("Renault-5 Copa Turbo", 2500.0f,
							"https://cdn.autobild.es/sites/navi.axelspringer.es/public/styles/480/public/media/image/2016/12/600833-mitos-automovil-saltaba-turbo-r5.jpg?itok=gKEbtuIV",
							usuario),
					new Producto("Guitarra Acustica", 100.0f, "https://mercasonic.soniccdn.com/sda/640/2428/3122428.jpg", usuario),
					new Producto("Play Station 4", 425.0f, "https://upload.wikimedia.org/wikipedia/commons/3/33/PlayStation_Four.png", usuario2),
					new Producto("Vinilo AC/DC", 10.0f, "https://www.emp-online.es/dw/image/v2/BBQV_PRD/on/demandware.static/-/Sites-master-emp/default/dw0048103f/images/3/0/4/4/304400-emp.jpg?sw=1000&sh=800&sm=fit&sfrm=png", usuario2),
					new Producto("Xiaomi M-10", 250.0f, "https://pbs.twimg.com/media/Dyjccg1UcAA1ZtA.jpg", usuario2));
			
			listado.forEach(productoServicio::insertar);
			

		};
	}
	
	/**
	 * Este bean se inicia al lanzar la aplicación. Nos permite inicializar el almacenamiento
	 * secundario del proyecto
	 * 
	 * @param storageService Almacenamiento secundario del proyecto
	 * @return
	 */
	@Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            storageService.deleteAll();
            storageService.init();
        };
    }
}
