package br.odb.libstrip;

import java.util.ArrayList;
import br.odb.libstrip.GeneralTriangle;
import br.odb.utils.Utils;
import br.odb.utils.math.Vec3;

/**
 * 
 * @author Daniel "Monty" Monteiro
 * 
 */
public class Mesh {
	final public ArrayList<IndexedSetFace> faces = new ArrayList<IndexedSetFace>();
	final public ArrayList<Vec3> points = new ArrayList<Vec3>();
	final public String name;
	public Material material;
	public boolean renderable = true;
	public boolean solid = false;
	public boolean visible = true;
	private float[] cachedVertexData;
	private float[] cachedColorData;

	public void clear() {
		faces.clear();
		points.clear();
		cachedColorData = null;
		cachedVertexData = null;
	}
	
	
	/**
	 * 
	 * @param mesh
	 */
	public Mesh( String name, Mesh mesh) {
		
		this( name );

		solid = mesh.solid;

		for (IndexedSetFace face : mesh.faces) {
			faces.add(face.makeCopy());
		}

		for (Vec3 vec : mesh.points) {
			points.add(new Vec3(vec));
		}

		if (mesh.material != null)
			material = new Material(mesh.material);
	}

	public Mesh(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		String toReturn = "";
		for (int c = 0; c < faces.size(); ++c)
			toReturn += "\n" + faces.get(c).toString();
		return toReturn;
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	public boolean equals(Mesh another) {
		boolean isEqual = true;
		int size;

		if (name != null)
			isEqual = isEqual && name.equals(another.name );
		else
			isEqual = isEqual && (another.name == null);

		size = faces.size();
		for (int c = 0; c < size; ++c) {
			isEqual = isEqual && faces.get(c).equals(another.getFace(c));
		}
		size = points.size();
		for (int c = 0; c < size; ++c) {
			isEqual = isEqual && points.get(c).equals(another.getPoint(c));
		}

		if (material != null)
			isEqual = isEqual && material.equals(another.getMaterial());
		else
			isEqual = isEqual && (material == another.getMaterial());

		return isEqual;
	}
	
	public Vec3 getCenter() {
		Vec3 center = new Vec3( points.get(0));

		for (int c = 1; c < points.size(); ++c) {
			center.set(center.add( points.get(c)));
		}

		center.scale(1.0f / points.size());
		
		return center;
	}
	

	private Material getMaterial() {
		return material;
	}

	public Vec3 getPoint(int c) {
		return points.get(c);
	}

	public IndexedSetFace getFace(int c) {
		return faces.get(c);
	}

	@Override
	public boolean equals(Object another) {
		if (another instanceof Mesh) {
			return equals((Mesh) another);
		} else {
			return false;
		}
	}

	
	private float snap(float value, int snapLevel ) {
		float[] val;
		float[] dist;
		int smaller = 0;

		float floor = (float) Math.floor(value);

		switch( snapLevel) {
		case 0:
			return value;
		case 1:
			// val = new float[ 11 ];
			// dist = new float[ 11 ];
			// val[ 0 ] = floor;
			// dist[ 0 ] = Math.abs( value - val[ 0 ] );
			// val[ 1 ] = floor + 0.1f;
			// dist[ 1 ] = Math.abs( value - val[ 1 ] );
			// val[ 2 ] = floor + 0.2f;
			// dist[ 2 ] = Math.abs( value - val[ 2 ] );
			// val[ 3 ] = floor + 0.3f;
			// dist[ 3 ] = Math.abs( value - val[ 3 ] );
			// val[ 4 ] = floor + 0.4f;
			// dist[ 4 ] = Math.abs( value - val[ 4 ] );
			// val[ 5 ] = floor + 0.5f;
			// dist[ 5 ] = Math.abs( value - val[ 5 ] );
			// val[ 6 ] = floor + 0.6f;
			// dist[ 6 ] = Math.abs( value - val[ 6 ] );
			// val[ 7 ] = floor + 0.7f;
			// dist[ 7 ] = Math.abs( value - val[ 7 ] );
			// val[ 8 ] = floor + 0.8f;
			// dist[ 8 ] = Math.abs( value - val[ 8 ] );
			// val[ 9 ] = floor + 0.9f;
			// dist[ 9 ] = Math.abs( value - val[ 9 ] );
			// val[ 10 ] = floor + 1.0f;
			// dist[ 10 ] = Math.abs( value - val[ 10 ] );
			//
			// break;
			// case 2:
			val = new float[5];
			dist = new float[5];
			val[0] = floor;
			dist[0] = Math.abs(value - val[0]);
			val[1] = floor + 0.25f;
			dist[1] = Math.abs(value - val[1]);
			val[2] = floor + 0.5f;
			dist[2] = Math.abs(value - val[2]);
			val[3] = floor + 0.75f;
			dist[3] = Math.abs(value - val[3]);
			val[4] = floor + 1.0f;
			dist[4] = Math.abs(value - val[4]);
			break;
		case 2:
			val = new float[3];
			dist = new float[3];
			val[0] = floor;
			dist[0] = Math.abs(value - val[0]);
			val[1] = floor + 0.5f;
			dist[1] = Math.abs(value - val[1]);
			val[2] = floor + 1.0f;
			dist[2] = Math.abs(value - val[2]);
			break;
		default:
			return Utils.snap(value);
		}

		for (int c = 1; c < val.length; ++c) {
			if (dist[c] < dist[smaller])
				smaller = c;
		}

		return val[smaller];
	}

	public void addFace(IndexedSetFace poly) {
		faces.add(poly);
	}

	public void translate(Vec3 translation) {

		for (Vec3 point : points) {
			point.set(point.add(translation));
		}
	}

	public float[] getVertexData() {

		if (cachedVertexData == null) {

			GeneralTriangle t;
			cachedVertexData = new float[9 * this.faces.size()];

			for (int c = 0; c < cachedVertexData.length; c += 9) {

				t = (GeneralTriangle) faces.get(c / 9);
				cachedVertexData[c] = t.x0;
				cachedVertexData[c + 1] = t.y0;
				cachedVertexData[c + 2] = t.z0;

				cachedVertexData[c + 3] = t.x1;
				cachedVertexData[c + 4] = t.y1;
				cachedVertexData[c + 5] = t.z1;

				cachedVertexData[c + 6] = t.x2;
				cachedVertexData[c + 7] = t.y2;
				cachedVertexData[c + 8] = t.z2;
			}
		}

		return cachedVertexData;
	}

	public float[] getColorData() {

		if (cachedColorData == null) {
			GeneralTriangle t;
			cachedColorData = new float[4 * this.faces.size()];

			for (int c = 0; c < faces.size(); ++c) {

				t = (GeneralTriangle) faces.get(c);
				cachedColorData[(c * 4)] = t.r / 255.0f;
				cachedColorData[(c * 4) + 1] = t.g / 255.0f;
				cachedColorData[(c * 4) + 2] = t.b / 255.0f;
				cachedColorData[(c * 4) + 3] = t.a / 255.0f;
			}
		}

		return cachedColorData;
	}

	public void destroy() {

		faces.clear();

		points.clear();

		material = null;

		cachedVertexData = null;
		cachedColorData = null;
	}

	public void addFacesFrom(Mesh otherMesh) {

		for (IndexedSetFace isf : otherMesh.faces)
			addFace(isf);
	}
}
