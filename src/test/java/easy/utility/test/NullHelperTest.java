package easy.utility.test;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;

import org.junit.Assert;
import org.junit.Test;

import easy.utility.NullHelper;

public class NullHelperTest {

	@Test
	public void nullTest() {
		DemoA a = new DemoA();
		a.setName("default");
		
		DemoA actual = NullHelper.ifNull(null, a);
		
		Assert.assertEquals(a.getName(), actual.getName());
	}
	@Test
	public void notNullTest(){
		DemoA a = new DemoA();
		a.setName("this");
		
		DemoA b = new DemoA();
		b.setName("default");
		
		DemoA actual = NullHelper.ifNull(a, b);
		
		Assert.assertEquals(a.getName(), actual.getName());
	}
	@Test
	public void nullHelperTest2(){
		
		Demo demo =new Demo("test");
		
		String value = NullHelper.ifNull(demo, "", s->s.getName());
		
		Assert.assertEquals("test",value);
		
		Demo demo2 = null;
		
		String value2 =NullHelper.ifNull(demo2, "null value", s->s.getName());
		Assert.assertEquals("null value", value2);
	}
	@Test
	public void testAA() throws UnknownHostException {
		TestDemo t =new TestDemo();
		
		
		t.a2((Demo d)->d.getName());
		
		String hostName = java.net.Inet4Address.getLocalHost().getHostName();
		System.out.println(hostName);
		Arrays.stream( java.net.Inet4Address.getAllByName(hostName)).forEach(s->System.out.println(s.getHostAddress()));
	}
	
	class DemoA{
		private String name;

		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
	}
	
	
	class Demo{
		
		public Demo(String name){
			this.name=name;
		}
		
		private String name;
		public String getName(){
			return name;
		}
	}
	
	
	class TestDemo{
		public <T,R> R a(T t,Function<T,R> func){
			return func.apply(t);
		}
		
		public <T> void a2(Consumer<T> c){
			System.out.println(c.getClass());
			System.out.println(c.getClass().getMethods().length);
			
			Arrays.stream(c.getClass().getMethods()).forEach(s->System.out.println(s.getName()));
		}
	}

}
