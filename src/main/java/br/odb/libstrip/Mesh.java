package br.odb.libstrip;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import br.odb.utils.Color;
import br.odb.utils.Utils;
import br.odb.utils.math.Vec3;

/**
 * 
 * @author Daniel "Monty" Monteiro
 * 
 */
public class Mesh {
	/**
	 * 
	 */
	public ArrayList<IndexedSetFace> faces;
	/**
	 * 
	 */
	public ArrayList<Vec3> points;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	public Material material;
	public boolean renderable = true;
	private boolean solid;
	private boolean visible = true;
	public VertexArrayManager manager;
	private float[] cachedVertexData;
	private float[] cachedColorData;

	/**
	 * 
	 * @param solid
	 */
	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * 
	 */
	public Mesh() {
		solid = false;
		faces = new ArrayList<IndexedSetFace>();
		points = new ArrayList<Vec3>();
	}

	/**
	 * 
	 * @param mesh
	 */
	public Mesh(Mesh mesh) {

		// if ( mesh == null )

		solid = mesh.solid;

		faces = new ArrayList<IndexedSetFace>();
		points = new ArrayList<Vec3>();
		System.out.println("about to copy " + mesh.faces.size() + " faces");

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
		this();
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

	public void addPoint(float x, float y, float z) {
		points.add(new Vec3(x, y, z));
	}

	public static void dumpToViewSVG(ArrayList<Mesh> meshList, OutputStream os) {
		IndexedSetFace face;
		Vec3 v = null;
		int e = 0;
		Mesh mesh;
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

			bw.write("<?xml version='1.0' encoding='UTF-8' standalone='no'?>");
			bw.newLine();
			bw.write("<svg>");
			// bw.newLine();
			//
			// bw.write("<script type='text/ecmascript'>" +
			// "<![CDATA[" +
			// "function echoShape(evt)" +
			// "{" +
			// "var rect = evt.getTarget();" +
			// "alert('rect:' +  rect );" +
			// "}" +
			// "function rect_highlight() {" +
			//
			// "}" +
			// "function rect_normal() {" +
			// "}" +
			// "// ]]>" +
			// "</script>"
			// );
			bw.newLine();
			// for every face in the mesh
			for (int f = 0; f < meshList.size(); ++f) {
				mesh = meshList.get(f);

				if (!mesh.renderable)
					continue;

				bw.write("<g id='" + mesh.getName() + "' >");
				bw.newLine();
				System.out.println("dumping " + mesh.getName());

				for (int c = 0; c < mesh.faces.size(); ++c) {
					face = mesh.faces.get(c);

					bw.write("<path ");

					if (mesh.material != null) {
						bw.write("style='fill:#");
						bw.write(mesh.material.getMainColor().toString());
						bw.write("' ");
					}
					// bw.write(" onmouseclick='echoShape(evt)'");
					bw.write(" d='");
					// for every point in the face
					for (int d = 0; d < face.getTotalIndexes(); ++d) {

						try {
							e = face.getIndex(d);
							v = (mesh.points.get(e));
						} catch (Exception ex) {
							ex.printStackTrace();
						}

						if (d == 0)
							bw.write("M");
						else
							bw.write("L");
						bw.write(" " + v.x + "," + v.z);
					}

					bw.write(" z' /> ");
					bw.newLine();
				}

				bw.write("</g>");
				bw.newLine();
			}

			bw.write("</svg>");
			bw.newLine();

			bw.close();

		} catch (Exception x) {
			x.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String text) {
		name = text;
	}

	public boolean equals(Mesh another) {
		boolean isEqual = true;
		int size;

		if (name != null)
			isEqual = isEqual && name.equals(another.getName());
		else
			isEqual = isEqual && (another.getName() == null);

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

	public Mesh makeCopy() {
		Mesh copy = new Mesh();
		copy.solid = solid;
		copy.faces = new ArrayList<IndexedSetFace>();

		for (IndexedSetFace face : faces) {
			copy.faces.add(face.makeCopy());
		}

		if (material != null)
			copy.material = material.makeCopy();

		copy.name = name;

		for (Vec3 vec : points) {
			copy.points.add(new Vec3(vec));
		}

		copy.renderable = renderable;

		return copy;
	}

	public void translate(Vec3 translation) {

		for (Vec3 point : points) {
			point.set(point.add(translation));
		}

	}

	public void setVisibility(boolean b) {

		visible = b;

		// for (IndexedSetFace face : faces ) {
		// face.setVisibility( b );
		// }
	}

	public boolean isVisible() {
		return visible;
	}

	public int getTotalItems() {
		return this.faces.size();
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

		for (IndexedSetFace isf : faces)
			isf.destroy();

		faces.clear();
		faces = null;

		points.clear();
		points = null;

		if (material != null)
			material.destroy();

		material = null;

		cachedVertexData = null;
		cachedColorData = null;
	}

	public void addFacesFrom(Mesh otherMesh) {

		for (IndexedSetFace isf : otherMesh.faces)
			addFace(isf);
	}

	public void preBuffer() {
		// TODO major code clean up  I sense

	}
}
