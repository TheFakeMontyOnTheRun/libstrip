/**
 * 
 */
package br.odb.libstrip;

import junit.framework.Assert;

import org.junit.Test;

import br.odb.utils.math.Vec3;

/**
 * @author monty
 *
 */
public class GeneralPolygonTest {

	/**
	 * Test method for {@link br.odb.libstrip.GeneralPolygon#makeCopy()}.
	 */
	@Test
	public void testMakeCopy() {
		
		GeneralPolygon poly1;
		GeneralPolygon poly2;
		
		poly1 = new GeneralPolygon();
		poly1.vertices.add( new Vec3( 2.0f, 3.0f, 3.0f ) );
		poly1.vertices.add( new Vec3( 2.0f, 2.0f, 3.0f ) );
		poly1.vertices.add( new Vec3( 3.0f, 2.0f, 3.0f ) );
		poly1.vertices.add( new Vec3( 3.0f, 3.0f, 3.0f ) );
		
		
		poly2 = poly1.makeCopy();
		
		for ( Vec3 v : poly1.vertices ) {
			Assert.assertEquals( poly1.vertices.indexOf( v ), poly2.vertices.indexOf( v ) );			
		}	
		
		Assert.assertEquals( poly1.material, poly2.material );
	}


	/**
	 * Test method for {@link br.odb.libstrip.GeneralPolygon#toString()}.
	 */
	@Test
	public void testToString() {
		GeneralPolygon poly1;
		
		Vec3[] points = {
			new Vec3( 2.0f, 3.0f, 3.0f ),
			new Vec3( 2.0f, 2.0f, 3.0f ),
			new Vec3( 3.0f, 2.0f, 3.0f ),
			new Vec3( 3.0f, 3.0f, 3.0f )			
		};
		
		poly1 = new GeneralPolygon();
		
		for ( Vec3 v : points ) {			
			poly1.vertices.add( v );
		}
		
		String toString = poly1.toString();
		
		for ( Vec3 v : points ) {
			Assert.assertTrue( toString.contains( v.toString() ) );
		}
	}
}
