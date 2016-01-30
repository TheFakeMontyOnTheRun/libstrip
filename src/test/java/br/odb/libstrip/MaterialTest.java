/**
 * 
 */
package br.odb.libstrip;

import br.odb.gameutils.Color;
import junit.framework.Assert;

import org.junit.Test;

/**
 * @author Daniel "Monty" Monteiro
 *
 */
public class MaterialTest {

	@Test
	public void testConstructor() {
		Material m = new Material("test1", new Color(255, 128, 64), "tex0.png",
				"shader-program");
		Assert.assertEquals(new Color(255, 128, 64), m.mainColor);
		Assert.assertEquals("tex0.png", m.texture);
		Assert.assertEquals("shader-program", m.shaderProgram);
	}

	@Test
	public void testEqualsAndHashcode() {
		Material m;
		m = new Material("test1", new Color(255, 128, 64), "tex0.png",
				"shader-program");
		Material m1 = new Material("test1", new Color(255, 128, 64),
				"tex0.png", "shader-program");
		Material m2 = new Material("test1", new Color(255, 128, 64),
				"tex0.png", null );		
		Material m3 = new Material("test3", new Color(64, 128, 255),
				"tex0.png", null);
		Material m4 = new Material("test1", new Color(255, 128, 64),
				"tex0.png", null );
		Material m5 = new Material("test5", new Color(255, 128, 64), null,
				null);
		Material m6 = new Material(null, new Color(255, 128, 64), "tex0.png",
				"shader-program");
		
		Material m7 = new Material( "test314", new Color(137, 137, 137), "tex2.png",
				"shader-program");
		Material m8 = new Material( "test314", new Color(137, 137, 137), "tex2.png",
				"other-shader-program");


		Assert.assertTrue(m.equals(m));
		Assert.assertTrue(m.equals(m1));
		Assert.assertTrue(m1.equals(m));
		Assert.assertFalse(m1.equals(null));
		Assert.assertFalse(m1.equals("not a material"));
		Assert.assertFalse(m1.equals(m3));
		Assert.assertFalse(m7.equals(m8));
		Assert.assertFalse(m1.equals(m5));
		Assert.assertTrue(m4.equals(m2));
		Assert.assertFalse(m3.equals(m1));
		Assert.assertFalse(m5.equals(m1));
		
		Assert.assertFalse(m3.equals(m5));
		Assert.assertFalse(m3.equals(m6));
		
		Assert.assertFalse(m2.equals(m1));
		Assert.assertFalse(m5.equals(m3));
		Assert.assertFalse(m5.equals(m6));
		
		Assert.assertFalse(m6.equals(m3));
		Assert.assertFalse(m6.equals(m5));
		
		Assert.assertFalse(m7.equals(m1));
		Assert.assertFalse(m7.equals(m3));
		Assert.assertFalse(m7.equals(m5));
		Assert.assertFalse(m7.equals(m6));
		
		

		Assert.assertTrue(m.hashCode() == m1.hashCode());

		m = new Material("test1", new Color(255, 128, 64), "tex0.png","shader-program");
		m.mainColor.set(0, 0, 0, 0);
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test2", new Color(255, 128, 64), "tex0.png","shader-program");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material( null, new Color(255, 128, 64), "tex0.png","shader-program");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test1", null, "tex0.png","shader-program");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test1", new Color(255, 128, 64), "tex1.png","shader-program");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test1", new Color(255, 128, 64), null,"shader-program");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test1", new Color(255, 128, 64), "tex1.png", "shader-program2");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
				
		m = new Material("test1", new Color(255, 128, 64), "tex0.png","shader-program");
		m1 = new Material("test1", new Color(255, 128, 64), "tex0.png","shader-program");
		Assert.assertTrue(m.hashCode() == m1.hashCode());
		Assert.assertTrue(m.equals(m1));
		Assert.assertTrue(m1.equals(m));


		Assert.assertFalse(m1.hashCode() == m3.hashCode());
		Assert.assertFalse(m1.hashCode() == m5.hashCode());
		Assert.assertFalse(m1.hashCode() == m6.hashCode());
	}

	/**
	 * Test method for {@link br.odb.libstrip.Material#toString()}.
	 */
	@Test
	public void testToString() {

		Material m = new Material("test1", new Color(255, 128, 64), "tex0.png",
				"shader-program");
		Material m1 = new Material("test1", new Color(255, 128, 64),
				"tex0.png", "shader-program");

		Material m3 = new Material("test3", new Color(64, 128, 255),
				"tex0.png", null);
		Material m5 = new Material("test5", new Color(255, 128, 64), null,
				null);

		String fromString;

		fromString = m.toString();
		Assert.assertTrue(fromString.contains("test1"));
		Assert.assertTrue(fromString.contains(new Color(255, 128, 64)
				.getHTMLColor()));
		Assert.assertTrue(fromString.contains("tex0.png"));
		Assert.assertTrue(fromString.contains("shader-program"));
		Assert.assertTrue(fromString.startsWith("<"));
		Assert.assertTrue(fromString.endsWith("/>"));

		Assert.assertEquals(m.toString(), m1.toString());

		fromString = m3.toString();
		Assert.assertTrue(fromString.contains("test3"));
		Assert.assertTrue(fromString.contains(new Color(64, 128, 255)
				.getHTMLColor()));
		Assert.assertTrue(fromString.contains("tex0.png"));
		Assert.assertFalse(fromString.contains("vertex"));
		Assert.assertFalse(fromString.contains("fragment"));
		Assert.assertTrue(fromString.startsWith("<"));
		Assert.assertTrue(fromString.endsWith("/>"));

		fromString = m5.toString();
		Assert.assertTrue(fromString.contains("test5"));
		Assert.assertTrue(fromString.contains(new Color(255, 128, 64)
				.getHTMLColor()));
		Assert.assertFalse(fromString.contains("texture"));
		Assert.assertFalse(fromString.contains("vertex"));
		Assert.assertFalse(fromString.contains("fragment"));
		Assert.assertTrue(fromString.startsWith("<"));
		Assert.assertTrue(fromString.endsWith("/>"));
	}
}
