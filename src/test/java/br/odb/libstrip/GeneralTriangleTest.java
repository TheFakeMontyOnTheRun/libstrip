package br.odb.libstrip;

import br.odb.gameutils.Color;
import br.odb.gameutils.Direction;
import br.odb.gameutils.math.Vec3;
import junit.framework.Assert;

import org.junit.Test;

public class GeneralTriangleTest {

	public GeneralTriangle makeTestSubject() {
		GeneralTriangle gt = new GeneralTriangle();
		
		gt.material = Material.makeWithColor( new Color( 0xFF00FF00 ) );
		
		gt.x0 = 0.0f;
		gt.y0 = 0.0f;
		gt.z0 = 0.0f;
		
		gt.x1 = 1.0f;
		gt.y1 = 0.0f;
		gt.z1 = 0.0f;
		
		gt.x2 = 0.0f;
		gt.y2 = 1.0f;
		gt.z2 = 0.0f;
		
		return gt;
	}
	
	
	@Test
	public void testFlush() {
		GeneralTriangle gt = makeTestSubject();
		gt.flush();
	}

	@Test
	public void testSingleColorData() {
		GeneralTriangle gt = makeTestSubject();
		float[] colour = gt.material.mainColor.getFloatData();
		Assert.assertEquals( 0.0f, colour[ 0 ], 0.001f );
		Assert.assertEquals( 1.0f, colour[ 1 ], 0.001f );
		Assert.assertEquals( 0.0f, colour[ 2 ], 0.001f );
		Assert.assertEquals( 1.0f, colour[ 3 ], 0.001f );
	}

	@Test
	public void testGetVertexData() {
		
		GeneralTriangle gt = makeTestSubject();		
		float[] vertices = gt.getVertexData();
		
		Assert.assertEquals( 0.0f, vertices[ 0 ], 0.001f );
		Assert.assertEquals( 0.0f, vertices[ 1 ], 0.001f );
		Assert.assertEquals( 0.0f, vertices[ 2 ], 0.001f );
		
		Assert.assertEquals( 1.0f, vertices[ 3 ], 0.001f );
		Assert.assertEquals( 0.0f, vertices[ 4 ], 0.001f );
		Assert.assertEquals( 0.0f, vertices[ 5 ], 0.001f );
		
		Assert.assertEquals( 0.0f, vertices[ 6 ], 0.001f );
		Assert.assertEquals( 1.0f, vertices[ 7 ], 0.001f );
		Assert.assertEquals( 0.0f, vertices[ 8 ], 0.001f );
	}

	@Test
	public void testMakeCopy() {
		GeneralTriangle gt = makeTestSubject();
		GeneralTriangle gt2 = gt.makeCopy();
		
		Assert.assertEquals( gt.hashCode(), gt2.hashCode() );
		Assert.assertEquals( gt, gt2 );
		
		gt2.material = null;
		
		Assert.assertFalse( gt.equals( gt2 ) );
		Assert.assertFalse( gt2.equals( gt ) );

		Assert.assertFalse( gt.hashCode() == gt2.hashCode() );
		
		gt2.material = Material.makeWithColor( new Color( 0xFFFFFF00 ));
		
		Assert.assertFalse( gt.equals( gt2 ) );
		Assert.assertFalse( gt2.equals( gt ) );
		
		Assert.assertFalse( gt.hashCode() == gt2.hashCode() );
	}
	
	@Test
	public void testEqualsAndHashcode() {
		GeneralTriangle gt = makeTestSubject();
		GeneralTriangle gt2 = makeTestSubject();
		GeneralTriangle gt3;
		

		
		
		Assert.assertEquals( gt, gt2 );
		Assert.assertEquals( gt.hashCode(), gt2.hashCode() );
		
		gt.hint = Direction.FLOOR;
		gt2.hint = Direction.CEILING;
		
		Assert.assertEquals( gt, gt2 );
		Assert.assertEquals( gt.hashCode(), gt2.hashCode() );
		
		gt3 = makeTestSubject();
		gt3.x0 = -1.0f;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.y0 = -1.0f;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.z0 = -1.0f;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.x1 = -1.0f;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.y1 = -1.0f;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.z1 = -1.0f;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.x2 = -1.0f;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.y2 = -1.0f;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.z2 = -1.0f;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.material.mainColor.a = 137;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.material.mainColor.r = 137;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.material.mainColor.g = 137;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		gt3 = makeTestSubject();
		gt3.material.mainColor.b = 137;		
		Assert.assertFalse( gt.equals( gt3 ) );
		
		
		
		Assert.assertTrue( gt.equals( gt ) );
		Assert.assertFalse( gt.equals( "Not even a damn triangle!" ) );
		Assert.assertFalse( gt.equals( null ) );
		
	}

	@Test
	public void testGetVertex() {
		GeneralTriangle gt = makeTestSubject();
		Assert.assertEquals( new Vec3(), gt.getVertex( 0 ) );
		Assert.assertEquals( new Vec3( 1.0f, 0.0f, 0.0f ), gt.getVertex( 1 ) );
		Assert.assertEquals( new Vec3( 0.0f, 1.0f, 0.0f ), gt.getVertex( 2 ) );
	}

	@Test
	public void testMakeNormal() {
		GeneralTriangle gt = makeTestSubject();
		Vec3 normal = gt.makeNormal();
		Assert.assertEquals( new Vec3( 0.0f, 0.0f, 1.0f ), normal );
	}

	@Test
	public void testFlatten() {
		GeneralTriangle gt = makeTestSubject();
		gt.z0 = -1.0f;
		gt.z1 = -2.0f;
		gt.z2 = -3.0f;
		
		Assert.assertFalse( gt.z0 >= 0.0f );
		Assert.assertFalse( gt.z1 >= 0.0f );
		Assert.assertFalse( gt.z2 >= 0.0f );
		
		gt.flatten( 1 );
		
		Assert.assertTrue( gt.z0 >= 0.0f );
		Assert.assertTrue( gt.z1 >= 0.0f );
		Assert.assertTrue( gt.z2 >= 0.0f );
	}

}
