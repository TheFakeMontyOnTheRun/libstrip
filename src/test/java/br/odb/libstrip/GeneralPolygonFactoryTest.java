/**
 * 
 */
package br.odb.libstrip;

import junit.framework.Assert;

import org.junit.Test;

import br.odb.libstrip.builders.GeneralPolygonFactory;
import br.odb.utils.math.Vec3;

/**
 * @author monty
 *
 */
public class GeneralPolygonFactoryTest {

	/**
	 * Test method for {@link br.odb.libstrip.builders.GeneralPolygonFactory#GeneralPolygonFactory()}.
	 */
	@Test
	public void testGeneralPolygonFactory() {
		new GeneralPolygonFactory();
	}

	/**
	 * Test method for {@link br.odb.libstrip.builders.GeneralPolygonFactory#MakeXY(float, float, float, float, float)}.
	 */
	@Test
	public void testMakeXY() {
		GeneralPolygon gp = GeneralPolygonFactory.MakeXY( 0.0f, 0.0f, 1.0f, 1.0f, -1.0f );
		for ( Vec3 v : gp.vertices ) {
			Assert.assertEquals( -1.0f, v.z, 0.001f );
		}
	}

	/**
	 * Test method for {@link br.odb.libstrip.builders.GeneralPolygonFactory#MakeYZ(float, float, float, float, float)}.
	 */
	@Test
	public void testMakeYZ() {
		GeneralPolygon gp = GeneralPolygonFactory.MakeYZ( 0.0f, 0.0f, 1.0f, 1.0f, -1.0f );
		for ( Vec3 v : gp.vertices ) {
			Assert.assertEquals( -1.0f, v.x, 0.001f );
		}
	}

	/**
	 * Test method for {@link br.odb.libstrip.builders.GeneralPolygonFactory#MakeXZ(float, float, float, float, float)}.
	 */
	@Test
	public void testMakeXZ() {
		GeneralPolygon gp = GeneralPolygonFactory.MakeXZ( 0.0f, 0.0f, 1.0f, 1.0f, -1.0f );
		for ( Vec3 v : gp.vertices ) {
			Assert.assertEquals( -1.0f, v.y, 0.001f );
		}
	}
}
