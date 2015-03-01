package br.odb.libstrip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.odb.utils.math.Vec3;

/**
 * 
 * @author Daniel "Monty" Monteiro
 * 
 */
public class GeneralTriangleMesh implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3375701151469728552L;
	
	final public List<GeneralTriangle> faces = new ArrayList<GeneralTriangle>();
	
	final public String name;
	private float[] cachedVertexData;
	private float[] cachedColorData;

	public void clear() {
		faces.clear();
		cachedColorData = null;
		cachedVertexData = null;
	}

	/**
	 * 
	 * @param mesh
	 */
	public GeneralTriangleMesh(String name, GeneralTriangleMesh mesh) {

		this(name);

		for (GeneralTriangle face : mesh.faces) {
			faces.add(face.makeCopy());
		}
	}

	public GeneralTriangleMesh(String name) {
		this.name = name;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append( "<name>" + name + "</name>\n" );

		for (GeneralTriangle isf : faces) {
			sb.append( "\n" + isf );
		}
		return sb.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + faces.hashCode();
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}



	public Vec3 getCenter() {
		float points = 0;
		Vec3 center = new Vec3();

		for ( GeneralTriangle t : faces ) {
			
			center.addTo( t.x0, t.y0, t.z0 );
			center.addTo( t.x1, t.y1, t.z1 );
			center.addTo( t.x2, t.y2, t.z2 );			
			points += 3.0f;
		}

		center.scale( 1.0f / points );

		return center;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if ( !( obj instanceof GeneralTriangleMesh ) ) {
			return false;
		}
		GeneralTriangleMesh other = (GeneralTriangleMesh) obj;
		if (!faces.equals(other.faces)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}

	public void translate(Vec3 translation) {

		for ( GeneralTriangle trig : faces ) {
			trig.x0 += translation.x;
			trig.x1 += translation.x;
			trig.x2 += translation.x;
			trig.y0 += translation.y;
			trig.y1 += translation.y;
			trig.y2 += translation.y;
			trig.z0 += translation.z;
			trig.z1 += translation.z;
			trig.z2 += translation.z;
		}
		
		if (cachedVertexData != null) {

			for (int c = 0; c < cachedVertexData.length; c += 9) {

				cachedVertexData[c    ] += translation.x;
				cachedVertexData[c + 1] += translation.y;
				cachedVertexData[c + 2] += translation.z;

				cachedVertexData[c + 3] += translation.x;
				cachedVertexData[c + 4] += translation.y;
				cachedVertexData[c + 5] += translation.z;

				cachedVertexData[c + 6] += translation.x;
				cachedVertexData[c + 7] += translation.y;
				cachedVertexData[c + 8] += translation.z;
			}
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
			
			float[] colourData;
			GeneralTriangle t;
			cachedColorData = new float[4 * this.faces.size()];

			for (int c = 0; c < faces.size(); ++c) {

				t = (GeneralTriangle) faces.get(c);
				colourData = t.material.mainColor.getFloatData();
				cachedColorData[(c * 4)] = colourData[ 0 ];
				cachedColorData[(c * 4) + 1] = colourData[ 1 ];
				cachedColorData[(c * 4) + 2] = colourData[ 2 ];
				cachedColorData[(c * 4) + 3] = colourData[ 3 ];
			}
		}

		return cachedColorData;
	}
}
