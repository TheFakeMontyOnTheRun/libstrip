/**
 * 
 */
package br.odb.libstrip;

import br.odb.gameutils.Color;
import br.odb.gameutils.math.Vec3;
import junit.framework.Assert;

import org.junit.Test;

import br.odb.libstrip.builders.GeneralTriangleFactory;

/**
 * @author monty
 *
 */
public class GeneralTriangleMeshTest {

	/**
	 * Test method for {@link br.odb.libstrip.TriangleMesh#clear()}.
	 */
	@Test
	public void testClear() {
		GeneralTriangleFactory factory = new GeneralTriangleFactory();
		TriangleMesh mesh1 = new TriangleMesh( "mesh1" );
		
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, material, null ) );
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, material, null ) );
		Material material = Material.makeWithColor(new Color(0xFFFF0000));

		mesh1.clear();
		Assert.assertEquals( 0, mesh1.faces.size() );		
	}

	@Test
	public void testGeneralTriangleMeshStringGeneralTriangleMesh() {
		GeneralTriangleFactory factory = new GeneralTriangleFactory();
		Material material = Material.makeWithColor(new Color(0xFFFF0000));
		TriangleMesh mesh1 = new TriangleMesh("mesh1");
		TriangleMesh mesh2;
		
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, material, null ) );
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, material, null ) );
		mesh2 = new TriangleMesh( "mesh2", mesh1 );
		
		for ( GeneralTriangle face : mesh1.faces ) {
			Assert.assertTrue( mesh2.faces.contains( face ) );
		}
	}

	@Test
	public void testGeneralTriangleMeshString() {
		TriangleMesh mesh1 = new TriangleMesh( "mesh1" );
		Assert.assertEquals( "mesh1", mesh1.name );
	}

	/**
	 * Test method for {@link br.odb.libstrip.TriangleMesh#toString()}.
	 */
	@Test
	public void testToString() {
		GeneralTriangleFactory factory = new GeneralTriangleFactory();
		
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, material, null ) );
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, material, null ) );
		Material material = Material.makeWithColor(new Color(0xFFFF0000));
		TriangleMesh mesh1 = new TriangleMesh("mesh1");

		String fromString = mesh1.toString();
		
		Assert.assertTrue( fromString.contains( mesh1.name ) );
		
		for ( GeneralTriangle face : mesh1.faces ) {
			Assert.assertTrue( fromString.contains( face.toString() ) );
		}
	}

	/**
	 * Test method for {@link br.odb.libstrip.TriangleMesh#getCenter()}.
	 */
	@Test
	public void testGetCenter() {
		GeneralTriangleFactory factory = new GeneralTriangleFactory();
		
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, material, null ) );
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, material, null ) );
		Material material = Material.makeWithColor(new Color(0xFFFF0000));
		TriangleMesh mesh1 = new TriangleMesh("mesh1");

		Assert.assertEquals( new Vec3( 0.5f, 0.5f, 0.0f ), mesh1.getCenter() );
	}

	/**
	 * Test method for {@link br.odb.libstrip.TriangleMesh#equals(java.lang.Object)} and {@link br.odb.libstrip.TriangleMesh#hashCode()}.
	 */
	@Test
	public void testHashCodeAndEquals() {
		GeneralTriangleFactory factory = new GeneralTriangleFactory();
		Material material = Material.makeWithColor(new Color(0xFFFF0000));
		TriangleMesh mesh1 = new TriangleMesh("mesh1");
		TriangleMesh mesh2;
		TriangleMesh mesh3 = new TriangleMesh( null ) ;
		TriangleMesh mesh4 = new TriangleMesh( null ) ;
		TriangleMesh mesh5;
		
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.0f, 1.0f, 0.0f, 0.0f, material, null ) );
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, material, null ) );
		
		mesh2 = new TriangleMesh( "mesh1", mesh1 );
		mesh4 = new TriangleMesh( "mesh2", mesh1 );
		mesh5 = new TriangleMesh( null, mesh1 );
		
		for ( GeneralTriangle face : mesh1.faces ) {
			Assert.assertTrue( mesh2.faces.contains( face ) );
		}
		Assert.assertFalse( mesh4.equals( mesh5 ) );
		Assert.assertEquals( mesh1, mesh2 );
		Assert.assertEquals( mesh1, mesh1 );
		Assert.assertEquals( mesh1.hashCode(), mesh2.hashCode() );
		
		TriangleMesh nullMesh1 = new TriangleMesh( null, mesh1 );
		
		Assert.assertFalse( nullMesh1.equals( mesh2 ) );
		Assert.assertFalse( mesh1.equals( nullMesh1 ) );		
		Assert.assertFalse( mesh1.hashCode() == nullMesh1.hashCode() );
		
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, -1.0f, 0.0f, 1.0f, -1.0f, 1.0f, 1.0f, -1.0f, material, null ) );
		
		Assert.assertFalse( mesh1.equals( mesh2 ) );
		Assert.assertFalse( mesh1.equals( mesh4 ) );
		Assert.assertFalse( mesh4.equals( mesh1 ) );
		Assert.assertFalse( mesh1.equals( mesh3 ) );
		Assert.assertFalse( mesh2.equals( mesh1 ) );
		Assert.assertFalse( mesh3.equals( mesh1 ) );
		Assert.assertFalse( mesh1.equals( null ) );
		Assert.assertFalse( mesh1.equals( "Not even a mesh!" ) );
		
		Assert.assertFalse( mesh1.hashCode() == mesh2.hashCode() );
		Assert.assertFalse( mesh1.hashCode() == mesh3.hashCode() );
		
		
	}	

	@Test
	public void testTranslate() {
		GeneralTriangleFactory factory = new GeneralTriangleFactory();
		
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, material, null ) );
		mesh1.translate( new Vec3( 3.0f, 0.0f, 0.0f ) );
		
		
		Assert.assertEquals( 3.0f, mesh1.faces.get( 0 ).x0 );
		Assert.assertEquals( 3.0f, mesh1.faces.get( 0 ).x1 );
		Assert.assertEquals( 4.0f, mesh1.faces.get( 0 ).x2 );
		
		Assert.assertEquals( 0.0f, mesh1.faces.get( 0 ).y0 );
		Assert.assertEquals( 1.0f, mesh1.faces.get( 0 ).y1 );
		Assert.assertEquals( 1.0f, mesh1.faces.get( 0 ).y2 );
		
		Assert.assertEquals( 0.0f, mesh1.faces.get( 0 ).z0 );
		Assert.assertEquals( 0.0f, mesh1.faces.get( 0 ).z1 );
		Assert.assertEquals( 0.0f, mesh1.faces.get( 0 ).z2 );
				
		Material material = Material.makeWithColor(new Color(0xFFFF0000));
		TriangleMesh mesh1 = new TriangleMesh("mesh1");
		float[] data = mesh1.getVertexData();

		mesh1.translate( new Vec3( 2.0f, 0.0f, 0.0f ) );
		
		Assert.assertEquals( 5.0f, mesh1.faces.get( 0 ).x0 );
		Assert.assertEquals( 5.0f, mesh1.faces.get( 0 ).x1 );
		Assert.assertEquals( 6.0f, mesh1.faces.get( 0 ).x2 );
		
		Assert.assertEquals( 0.0f, mesh1.faces.get( 0 ).y0 );
		Assert.assertEquals( 1.0f, mesh1.faces.get( 0 ).y1 );
		Assert.assertEquals( 1.0f, mesh1.faces.get( 0 ).y2 );
		
		Assert.assertEquals( 0.0f, mesh1.faces.get( 0 ).z0 );
		Assert.assertEquals( 0.0f, mesh1.faces.get( 0 ).z1 );
		Assert.assertEquals( 0.0f, mesh1.faces.get( 0 ).z2 );
		
		Assert.assertEquals( 5.0f, data[ 0 ] );
		Assert.assertEquals( 5.0f, data[ 3 ] );
		Assert.assertEquals( 6.0f, data[ 6 ] );

		Assert.assertEquals( 0.0f, data[ 1 ] );
		Assert.assertEquals( 1.0f, data[ 4 ] );
		Assert.assertEquals( 1.0f, data[ 7 ] );
		
		Assert.assertEquals( 0.0f, data[ 2 ] );
		Assert.assertEquals( 0.0f, data[ 5 ] );
		Assert.assertEquals( 0.0f, data[ 8 ] );		
	}

	/**
	 * Test method for {@link br.odb.libstrip.TriangleMesh#getVertexData()}.
	 */
	@Test
	public void testGetVertexData() {
		GeneralTriangleFactory factory = new GeneralTriangleFactory();
		
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, material, null ) );
		mesh1.translate( new Vec3( 5.0f, 0.0f, 0.0f ) );
		Material material = Material.makeWithColor(new Color(0xFFFF0000));
		TriangleMesh mesh1 = new TriangleMesh("mesh1");
		float[] data = mesh1.getVertexData();

		Assert.assertEquals( mesh1.faces.get( 0 ).x0, data[ 0 ] );
		Assert.assertEquals( mesh1.faces.get( 0 ).x1, data[ 3 ] );
		Assert.assertEquals( mesh1.faces.get( 0 ).x2, data[ 6 ] );
		
		Assert.assertEquals( mesh1.faces.get( 0 ).y0, data[ 1 ] );
		Assert.assertEquals( mesh1.faces.get( 0 ).y1, data[ 4 ] );
		Assert.assertEquals( mesh1.faces.get( 0 ).y2, data[ 7 ] );
		
		Assert.assertEquals( mesh1.faces.get( 0 ).z0, data[ 2 ] );
		Assert.assertEquals( mesh1.faces.get( 0 ).z1, data[ 5 ] );
		Assert.assertEquals( mesh1.faces.get( 0 ).z2, data[ 8 ] );
		
		float[] data2 = mesh1.getVertexData();
		
		Assert.assertEquals( data.length, data2.length );
		
		for ( int c = 0; c < data.length; ++c ) {
			Assert.assertEquals( data[ c ], data2[ c ] );	
		}
	}

	/**
	 * Test method for {@link br.odb.libstrip.TriangleMesh#getColorData()}.
	 */
	@Test
	public void testGetColorData() {
		GeneralTriangleFactory factory = new GeneralTriangleFactory();
		mesh1.faces.add( factory.makeTrig( 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 1.0f, 1.0f, 0.0f, material, null ) );
		Material material = Material.makeWithColor(new Color(0xFFFF0000));
		TriangleMesh mesh1 = new TriangleMesh("mesh1");
		float[] colour = mesh1.getColorData();
		float[] colour2 = mesh1.getColorData();
		
		Assert.assertTrue( colour == colour2 );
		
		float[] original = mesh1.faces.get( 0 ).material.mainColor.getFloatData();
		
		for ( int c = 0; c < colour.length; ++c ) {
			Assert.assertEquals( colour[ c ], original[ c ] );
		}
	}

}
