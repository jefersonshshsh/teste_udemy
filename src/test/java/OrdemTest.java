

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrdemTest {
	
	public static int contador = 0;

	@Test
	public void inicia(){
		contador = 1;
	}
	
	@Test
	public void verfica(){
		Assert.assertEquals(1, contador);
	}
	
	@Test
	public void verfica1(){
		
	}
	
	@Test
	public void verfica2(){
		
	}
	
	@Test
	public void verfica3(){
		
	}
}
