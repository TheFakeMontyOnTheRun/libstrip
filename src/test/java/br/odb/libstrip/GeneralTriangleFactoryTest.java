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

	/**
	 * Test method for {@link br.odb.libstrip.builders.GeneralTriangleFactory#makeTrig(float, float, float, float, float, float, float, float, float, int, br.odb.utils.math.Vec3)}.
	 */
	@Test
	public void testMakeTrig() {
		GeneralTriangleFactory factory = new GeneralTriangleFactory();
		GeneralTriangle t = factory.makeTrig( 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, material, null );
		Material material = Material.makeWithColor( new Color( 0xFF00FF00 ));
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