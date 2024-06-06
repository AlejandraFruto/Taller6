package tests;



import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import src.Hamburguesa;
import src.Ingrediente;

public class testsTaller {
	
	Hamburguesa hamburguesa;
	Hamburguesa vegana;
	Hamburguesa noVegana;
	Hamburguesa mixta;
	Ingrediente tomate;
	Ingrediente carne;
	Ingrediente lechuga;
	Ingrediente queso;
	Ingrediente berenjena;
	Ingrediente tocineta;
	Ingrediente maiz;
	
	@BeforeEach
	void setUp() {
		Hamburguesa hamburguesaPrueba = new Hamburguesa("DeliTuto",1000 );
		Hamburguesa vegana = new Hamburguesa("Veggie", 1000);
		Hamburguesa noVegana = new Hamburguesa("Full", 1000);
		Hamburguesa mixta = new Hamburguesa("Mix", 1000);
		
		Ingrediente ingredienteVegano1 = new Ingrediente("Tomate", true);
		Ingrediente ingredienteNoVegano1 = new Ingrediente("Carne", false);
		Ingrediente ingredienteVegano2 = new Ingrediente("Lechuga", true);
		Ingrediente ingredienteNoVegano2 = new Ingrediente("Queso", false);
		Ingrediente ingredienteVegano3 = new Ingrediente("Berenjena", true);
		Ingrediente ingredienteNoVegano3 = new Ingrediente("Tocineta", false);
		Ingrediente ingredienteVegano4 = new Ingrediente("Maiz", true);
		
		hamburguesa = hamburguesaPrueba;
		tomate = ingredienteVegano1;
		carne = ingredienteNoVegano1;
		lechuga = ingredienteVegano2;
		queso = ingredienteNoVegano2;
		berenjena = ingredienteVegano3;
		tocineta = ingredienteNoVegano3;
		maiz = ingredienteVegano4;
		
		this.vegana = vegana;
		this.noVegana = noVegana;
		this.mixta = mixta;
		
	
	}

	
	@Test
	void crearRebajaMenor30() {
		
			
			double porcentajemenor30 = 20;
			double porcentajemod = porcentajemenor30/100.0;
			int precioEsperado = (int) (((double) hamburguesa.getPrecio())*(1.0-porcentajemod));
			
			hamburguesa.crear_rebaja(porcentajemenor30);
			int precioObtenido = hamburguesa.getPrecio();
			assertEquals(precioEsperado,precioObtenido, "Son diferentes cuando deberian ser iguales." );
	
		
		
	}
	
	@Test
	void crearRebajaIgual30(){
		
		double porcentaje30 = 30;
		double porcentajemod = porcentaje30/100.0;
		int precioEsperado = (int) (((double) hamburguesa.getPrecio())*(1.0-porcentajemod));
		
		hamburguesa.crear_rebaja(porcentaje30);
		int precioObtenido = hamburguesa.getPrecio();
		assertEquals(precioEsperado,precioObtenido, "Son diferentes cuando deberian ser iguales." );

	
	}
	
	@Test
	void crerRebajaMayor30() {
		double porcentajemayor30 = 32;
		double porcentajemod = 30/100.0;
		int precioEsperado = (int) (((double) hamburguesa.getPrecio())*(1.0-porcentajemod));
		
		hamburguesa.crear_rebaja(porcentajemayor30);
		int precioObtenido = hamburguesa.getPrecio();
		assertEquals(precioEsperado,precioObtenido, "Son diferentes cuando deberian ser iguales." );

	
	}
	
	
	@Test
	void aumentarPrecio() throws Exception {
		int precioNuevo = (int) (((double) hamburguesa.getPrecio())*1.2);
		hamburguesa.aumentar_precio(precioNuevo);
		assertEquals(precioNuevo, hamburguesa.getPrecio());
		
	}
	
	@Test
    public void aumentarPrecioExcepcion() {
		int precioNuevo = ((int) (((double) hamburguesa.getPrecio())*1.2))+1;
        Exception exception = assertThrows(Exception.class, () -> {
            hamburguesa.aumentar_precio(precioNuevo); 
        });

        assertEquals("101 dólares por una cangreburguer!?", exception.getMessage());
    }
	
	@DisplayName("Al agregar ingrediente, quede en los ingredientes")
	
	@Test
	public void agregarIngredinte1() throws Exception {
		hamburguesa.agregar_ingrediente(queso);
		assertTrue(hamburguesa.getIngredientes().contains(queso));
		
	}
	
	@DisplayName("Se cambia el tamaño")
	@Test 
	public void agregarIngredienteTamanio() throws Exception{
		int tamanioEsperado = hamburguesa.getIngredientes().size() + 1;
		
		hamburguesa.agregar_ingrediente(carne);
		assertEquals(tamanioEsperado, hamburguesa.getIngredientes().size());
	}
	
	@DisplayName("Todo falla, si se trata de agregar un 7mo ingrediente")
	@Test
	public void agregar7moIngrediente() throws Exception {
		hamburguesa.agregar_ingrediente(carne);
		hamburguesa.agregar_ingrediente(berenjena);
		hamburguesa.agregar_ingrediente(lechuga);
		hamburguesa.agregar_ingrediente(maiz);
		hamburguesa.agregar_ingrediente(tocineta);
		hamburguesa.agregar_ingrediente(tomate);
		 Exception exception = assertThrows(Exception.class, () -> {
	            hamburguesa.agregar_ingrediente(queso);
	        });

	        assertEquals("Esto no es Burgermaster, muchos ingredientes", exception.getMessage());
	    }
	
	@DisplayName("Solo ingredientes veganos")
	@Test
	public void determinarVeganaSoloVeganos() throws Exception {
		
		vegana.agregar_ingrediente(berenjena);
		vegana.agregar_ingrediente(lechuga);
		vegana.agregar_ingrediente(maiz);
		
		assertTrue(vegana.determinar_vegana());
	}
	
	@DisplayName("Ningun ingrediente vegano")
	@Test
	public void determinarVeganaNoVegana() throws Exception {
		noVegana.agregar_ingrediente(carne);
		noVegana.agregar_ingrediente(queso);
		noVegana.agregar_ingrediente(tocineta);
		
		assertFalse(noVegana.determinar_vegana());
	}
	
	@DisplayName("Varios ingredientes veganos y no veganos")
	@Test
	public void determinarVeganaVarios() throws Exception {
		
		mixta.agregar_ingrediente(lechuga);
		mixta.agregar_ingrediente(queso);
		mixta.agregar_ingrediente(maiz);
		mixta.agregar_ingrediente(carne);
		
		assertFalse(mixta.determinar_vegana());
	}
	
	

}
