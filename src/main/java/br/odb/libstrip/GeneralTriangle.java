package br.odb.libstrip;

import br.odb.utils.Color;
import br.odb.utils.math.Vec3;

public class GeneralTriangle implements AbstractTriangle {

	public float r;
	public float g;
	public float b;
	public float a;
	public float x0;
	public float y0;
	public float z0;
	public float x1;
	public float y1;
	public float z1;
	public float x2;
	public float y2;
	public float z2;

	public void flush() {

	}

	public float[] singleColorData() {
		return new float[] { a, r, g, b };
	}

	public float[] getVertexData() {
		return new float[] { x0, y0, z0, x1, y1, z1, x2, y2, z2 };
	}

	@Override
	public IndexedSetFace makeCopy() {
		
		GeneralTriangle toReturn = new GeneralTriangle();
		
		toReturn.x0 = x0;
		toReturn.x1 = x1;
		toReturn.x2 = x2;
		toReturn.y0 = y0;
		toReturn.y1 = y1;
		toReturn.y2 = y2;
		toReturn.z0 = z0;
		toReturn.z1 = z1;
		toReturn.z2 = z2;
		
		toReturn.r = r;
		toReturn.g = g;
		toReturn.b = b;
		toReturn.a = a;
		
		return toReturn;
	}

	@Override
	public int getTotalIndexes() {
		return 3;
	}

	@Override
	public Vec3 getVertex(int c) {
		switch ( c ) {
		case 0:
			return new Vec3( x0, y0, z0 );
		case 1:
			return new Vec3( x1, y1, z1);
		case 2:
		default:
			return new Vec3( x2, y2, z2 );
		}		
	}

	@Override
	public Color getColor() {
		return new Color( r, g, b, a );
	}

	@Override
	public void setColor(Color c) {
		r = c.r / 255.0f;
		g = c.g / 255.0f;
		b = c.b / 255.0f;
		a = c.a / 255.0f;
	}

	@Override
	public Vec3 makeNormal() {
		Vec3 v1 = new Vec3( x1 - x0, y1 - y0, z1 - z0 );
		Vec3 v2 = new Vec3( x2 - x0, y2 - y0, z2 - z0 );
		Vec3 v3 = v1.crossProduct( v2 );
		return v3;
	}

	@Override
	public void flatten(float z) {
		z0 = z1 = z2 = z;		
	}
}
