/**
 * 
 */
package br.odb.libstrip;

import br.odb.gameutils.Color;
import junit.framework.Assert;

import org.junit.Test;

import br.odb.libstrip.builders.GeneralTriangleFactory;

/**
 * @author monty
 *
 */
public class GeneralTriangleFactoryTest {

	@Test
	public void testMakeTrig() {
		GeneralTriangleFactory factory = new GeneralTriangleFactory();
		Material material = Material.makeWithColor( new Color( 0xFF00FF00 ));
		GeneralTriangle t = factory.makeTrig( 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, material);
		Assert.assertEquals( 0.0f, t.x0 );
		Assert.assertEquals( 0.0f, t.y0 );
		Assert.assertEquals( 0.0f, t.z0 );
		Assert.assertEquals( 1.0f, t.x1 );
		Assert.assertEquals( 0.0f, t.y1 );
		Assert.assertEquals( 0.0f, t.z1 );
		Assert.assertEquals( 0.0f, t.x2 );
		Assert.assertEquals( 1.0f, t.y2 );
		Assert.assertEquals( 0.0f, t.z2 );
		
		Assert.assertEquals( 0xFF00FF00, t.material.mainColor.getARGBColor() );
	}
}