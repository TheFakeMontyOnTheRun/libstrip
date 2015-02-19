/**
 * 
 */
package br.odb.libstrip;

import junit.framework.Assert;

import org.junit.Test;

import br.odb.utils.Color;

/**
 * @author Daniel "Monty" Monteiro
 *
 */
public class MaterialTest {

	/**
	 * Test method for
	 * {@link br.odb.libstrip.Material#Material(java.lang.String, int, int, int, java.lang.String, java.lang.String, java.lang.String)}
	 * .
	 */
	@Test
	public void testConstructor() {
		Material m = new Material("test1", new Color(255, 128, 64), "tex0.png",
				"vert1.glsl", "frag1.glsl");
		Assert.assertEquals(new Color(255, 128, 64), m.mainColor);
		Assert.assertEquals("tex0.png", m.texture);
		Assert.assertEquals("vert1.glsl", m.vertexShader);
		Assert.assertEquals("frag1.glsl", m.fragmentShader);
	}

	/**
	 * Test method for
	 * {@link br.odb.libstrip.Material#equals(br.odb.libstrip.Material)}.
	 */
	@Test
	public void testEqualsAndHashcode() {
		Material m;
		m = new Material("test1", new Color(255, 128, 64), "tex0.png",
				"vert1.glsl", "frag1.glsl");
		Material m1 = new Material("test1", new Color(255, 128, 64),
				"tex0.png", "vert1.glsl", "frag1.glsl");
		Material m2 = new Material("test2", new Color(64, 128, 255),
				"tex1.png", "vert1.glsl", null);
		Material m3 = new Material("test3", new Color(64, 128, 255),
				"tex0.png", null, null);
		Material m4 = new Material("test4", null, null, null, "frag1.glsl");
		Material m5 = new Material("test5", new Color(255, 128, 64), null,
				null, null);
		Material m6 = new Material(null, new Color(255, 128, 64), "tex0.png",
				"vert1.glsl", "frag1.glsl");
		
		Material m7 = new Material( "test314", new Color(137, 137, 137), "tex2.png",
				"vert3.glsl", "frag5.glsl");

		Assert.assertTrue(m.equals(m));
		Assert.assertTrue(m.equals(m1));
		Assert.assertTrue(m1.equals(m));
		Assert.assertFalse(m1.equals(null));
		Assert.assertFalse(m1.equals("not a material"));
		Assert.assertFalse(m1.equals(m2));
		Assert.assertFalse(m1.equals(m3));
		Assert.assertFalse(m1.equals(m4));
		Assert.assertFalse(m1.equals(m5));
		
		Assert.assertFalse(m2.equals(m1));
		Assert.assertFalse(m3.equals(m1));
		Assert.assertFalse(m4.equals(m1));
		Assert.assertFalse(m5.equals(m1));
		
		Assert.assertFalse(m2.equals(m3));
		Assert.assertFalse(m2.equals(m4));
		Assert.assertFalse(m2.equals(m5));
		Assert.assertFalse(m2.equals(m6));
		
		Assert.assertFalse(m3.equals(m2));
		Assert.assertFalse(m3.equals(m4));
		Assert.assertFalse(m3.equals(m5));
		Assert.assertFalse(m3.equals(m6));
		
		Assert.assertFalse(m4.equals(m2));
		Assert.assertFalse(m4.equals(m3));
		Assert.assertFalse(m4.equals(m5));
		Assert.assertFalse(m4.equals(m6));
		
		Assert.assertFalse(m5.equals(m2));
		Assert.assertFalse(m5.equals(m3));
		Assert.assertFalse(m5.equals(m4));
		Assert.assertFalse(m5.equals(m6));
		
		Assert.assertFalse(m6.equals(m2));
		Assert.assertFalse(m6.equals(m3));
		Assert.assertFalse(m6.equals(m4));
		Assert.assertFalse(m6.equals(m5));
		
		Assert.assertFalse(m7.equals(m1));
		Assert.assertFalse(m7.equals(m2));
		Assert.assertFalse(m7.equals(m3));
		Assert.assertFalse(m7.equals(m4));
		Assert.assertFalse(m7.equals(m5));
		Assert.assertFalse(m7.equals(m6));
		
		

		Assert.assertTrue(m.hashCode() == m1.hashCode());

		m = new Material("test1", new Color(255, 128, 64), "tex0.png","vert1.glsl", "frag1.glsl");
		m.mainColor.set(0, 0, 0, 0);
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test2", new Color(255, 128, 64), "tex0.png","vert1.glsl", "frag1.glsl");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material( null, new Color(255, 128, 64), "tex0.png","vert1.glsl", "frag1.glsl");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test1", null, "tex0.png","vert1.glsl", "frag1.glsl");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test1", new Color(255, 128, 64), "tex1.png","vert1.glsl", "frag1.glsl");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test1", new Color(255, 128, 64), null,"vert1.glsl", "frag1.glsl");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test1", new Color(255, 128, 64), "tex1.png", null, "frag1.glsl");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test1", new Color(255, 128, 64), "tex1.png", "vert5.glsl", "frag1.glsl");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test1", new Color(255, 128, 64), "tex1.png","vert1.glsl", null );
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));
		
		m = new Material("test1", new Color(255, 128, 64), "tex1.png","vert1.glsl", "frag7.glsl" );
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));		
		
		m = new Material("test1", new Color(255, 128, 64), "tex0.png","vert1.glsl", "frag1.glsl");
		m1 = new Material("test1", new Color(255, 128, 64), "tex0.png","vert1.glsl", "frag1.glsl");
		Assert.assertTrue(m.hashCode() == m1.hashCode());
		Assert.assertTrue(m.equals(m1));
		Assert.assertTrue(m1.equals(m));
		

		Assert.assertFalse(m1.hashCode() == m2.hashCode());
		Assert.assertFalse(m1.hashCode() == m3.hashCode());
		Assert.assertFalse(m1.hashCode() == m4.hashCode());
		Assert.assertFalse(m1.hashCode() == m5.hashCode());
		Assert.assertFalse(m1.hashCode() == m6.hashCode());
	}

	/**
	 * Test method for {@link br.odb.libstrip.Material#toString()}.
	 */
	@Test
	public void testToString() {

		Material m = new Material("test1", new Color(255, 128, 64), "tex0.png",
				"vert1.glsl", "frag1.glsl");
		Material m1 = new Material("test1", new Color(255, 128, 64),
				"tex0.png", "vert1.glsl", "frag1.glsl");

		Material m2 = new Material("test2", new Color(64, 128, 255),
				"tex1.png", "vert1.glsl", null);
		Material m3 = new Material("test3", new Color(64, 128, 255),
				"tex0.png", null, null);
		Material m4 = new Material("test4", new Color(64, 128, 255), null,
				null, "frag1.glsl");
		Material m5 = new Material("test5", new Color(255, 128, 64), null,
				null, null);

		String fromString;

		fromString = m.toString();
		Assert.assertTrue(fromString.contains("test1"));
		Assert.assertTrue(fromString.contains(new Color(255, 128, 64)
				.getHTMLColor()));
		Assert.assertTrue(fromString.contains("tex0.png"));
		Assert.assertTrue(fromString.contains("vert1.glsl"));
		Assert.assertTrue(fromString.contains("frag1.glsl"));
		Assert.assertTrue(fromString.startsWith("<"));
		Assert.assertTrue(fromString.endsWith("/>"));

		Assert.assertEquals(m.toString(), m1.toString());

		fromString = m2.toString();
		Assert.assertTrue(fromString.contains("test2"));
		Assert.assertTrue(fromString.contains(new Color(64, 128, 255)
				.getHTMLColor()));
		Assert.assertTrue(fromString.contains("tex1.png"));
		Assert.assertTrue(fromString.contains("vert1.glsl"));
		Assert.assertFalse(fromString.contains("fragment"));
		Assert.assertTrue(fromString.startsWith("<"));
		Assert.assertTrue(fromString.endsWith("/>"));

		fromString = m3.toString();
		Assert.assertTrue(fromString.contains("test3"));
		Assert.assertTrue(fromString.contains(new Color(64, 128, 255)
				.getHTMLColor()));
		Assert.assertTrue(fromString.contains("tex0.png"));
		Assert.assertFalse(fromString.contains("vertex"));
		Assert.assertFalse(fromString.contains("fragment"));
		Assert.assertTrue(fromString.startsWith("<"));
		Assert.assertTrue(fromString.endsWith("/>"));

		fromString = m4.toString();
		Assert.assertTrue(fromString.contains("test4"));
		Assert.assertTrue(fromString.contains(new Color(64, 128, 255)
				.getHTMLColor()));
		Assert.assertFalse(fromString.contains("texture"));
		Assert.assertFalse(fromString.contains("vertex"));
		Assert.assertTrue(fromString.contains("frag1.glsl"));
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
