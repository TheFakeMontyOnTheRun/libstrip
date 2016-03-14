/**
 *
 */
package br.odb.libstrip;

import br.odb.gameutils.Color;
import junit.framework.Assert;

import org.junit.Test;

/**
 * @author Daniel "Monty" Monteiro
 */
public class MaterialTest {

	@Test
	public void testConstructor() {
		Material m = Material.makeWithColorAndTexture(new Color(255, 128, 64), "tex0.png");
		Assert.assertEquals(new Color(255, 128, 64), m.mainColor);
		Assert.assertEquals("tex0.png", m.texture);
	}

	@Test
	public void testEqualsAndHashcode() {
		Material m = Material.makeWithColorAndTexture(new Color(255, 128, 64), "tex0.png");
		Material m1 = Material.makeWithColorAndTexture(new Color(255, 128, 64), "tex0.png");
		Material m3 = Material.makeWithColorAndTexture(new Color(64, 128, 255), "tex0.png");
		Material m5 = Material.makeWithColor(new Color(255, 128, 64));
		Material m6 = Material.makeWithColorAndTexture(new Color(255, 128, 64), "tex0.png");
		Material m7 = Material.makeWithColorAndTexture(new Color(137, 137, 137), "tex2.png");

		Assert.assertTrue(m.equals(m));
		Assert.assertTrue(m.equals(m1));
		Assert.assertTrue(m1.equals(m));
		Assert.assertFalse(m1.equals(null));
		Assert.assertFalse(m1.equals("not a material"));
		Assert.assertFalse(m1.equals(m3));
		Assert.assertFalse(m1.equals(m5));
		Assert.assertFalse(m3.equals(m1));
		Assert.assertFalse(m5.equals(m1));

		Assert.assertFalse(m3.equals(m5));
		Assert.assertFalse(m3.equals(m6));

		Assert.assertFalse(m5.equals(m3));
		Assert.assertFalse(m5.equals(m6));

		Assert.assertFalse(m6.equals(m3));
		Assert.assertFalse(m6.equals(m5));

		Assert.assertFalse(m7.equals(m1));
		Assert.assertFalse(m7.equals(m3));
		Assert.assertFalse(m7.equals(m5));
		Assert.assertFalse(m7.equals(m6));

		Assert.assertTrue(m.hashCode() == m1.hashCode());

		m = Material.makeWithColorAndTexture(new Color(255, 128, 64), "tex0.png");
		m.mainColor.set(0, 0, 0, 0);
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));

		m = Material.makeWithTexture( "tex0.png");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));

		m = Material.makeWithColorAndTexture(new Color(255, 128, 64), "tex1.png");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));

		m = Material.makeWithColor(new Color(255, 128, 64));
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));

		m = Material.makeWithColorAndTexture(new Color(255, 128, 64), "tex1.png");
		Assert.assertFalse(m.hashCode() == m1.hashCode());
		Assert.assertFalse(m.equals(m1));
		Assert.assertFalse(m1.equals(m));

		m = Material.makeWithColorAndTexture(new Color(255, 128, 64), "tex0.png");
		m1 = Material.makeWithColorAndTexture(new Color(255, 128, 64), "tex0.png");
		Assert.assertTrue(m.hashCode() == m1.hashCode());
		Assert.assertTrue(m.equals(m1));
		Assert.assertTrue(m1.equals(m));


		Assert.assertFalse(m1.hashCode() == m3.hashCode());
		Assert.assertFalse(m1.hashCode() == m5.hashCode());
	}

	/**
	 * Test method for {@link br.odb.libstrip.Material#toString()}.
	 */
	@Test
	public void testToString() {

		Material m = Material.makeWithColorAndTexture(new Color(255, 128, 64), "tex0.png");
		Material m1 = Material.makeWithColorAndTexture(new Color(255, 128, 64), "tex0.png");
		Material m3 = Material.makeWithColorAndTexture(new Color(64, 128, 255), "tex0.png");
		Material m5 = Material.makeWithColor(new Color(255, 128, 64));

		String fromString;

		fromString = m.toString();
		Assert.assertTrue(fromString.contains(new Color(255, 128, 64)
				.getHTMLColor()));
		Assert.assertTrue(fromString.contains("tex0.png"));
		Assert.assertTrue(fromString.startsWith("<"));
		Assert.assertTrue(fromString.endsWith("/>"));

		Assert.assertEquals(m.toString(), m1.toString());

		fromString = m3.toString();
		Assert.assertTrue(fromString.contains(new Color(64, 128, 255)
				.getHTMLColor()));
		Assert.assertTrue(fromString.contains("tex0.png"));
		Assert.assertTrue(fromString.startsWith("<"));
		Assert.assertTrue(fromString.endsWith("/>"));

		fromString = m5.toString();
		Assert.assertTrue(fromString.contains(new Color(255, 128, 64)
				.getHTMLColor()));
		Assert.assertFalse(fromString.contains("texture"));
		Assert.assertTrue(fromString.startsWith("<"));
		Assert.assertTrue(fromString.endsWith("/>"));
	}
}
