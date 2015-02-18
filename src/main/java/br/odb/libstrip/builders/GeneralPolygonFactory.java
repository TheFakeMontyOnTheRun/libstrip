/**
 * 
 */
package br.odb.libstrip.builders;

import br.odb.libstrip.GeneralPolygon;
import br.odb.utils.math.Vec3;

/**
 * @author monty
 * 
 */
public class GeneralPolygonFactory {

	/**
	 * 
	 */
	public GeneralPolygonFactory() {
	}

	public static GeneralPolygon MakeXY(float x0, float y0, float x1, float y1,
			float z) {

		GeneralPolygon toReturn = new GeneralPolygon();

		toReturn.vertices.add(new Vec3(x0, y0, z));
		toReturn.vertices.add(new Vec3(x0, y1, z));
		toReturn.vertices.add(new Vec3(x1, y1, z));
		toReturn.vertices.add(new Vec3(x1, y0, z));
		return toReturn;
	}

	public static GeneralPolygon MakeYZ(float y0, float z0, float y1, float z1,
			float x) {
		GeneralPolygon toReturn = new GeneralPolygon();
		toReturn.vertices.add(new Vec3(x, y0, z0));
		toReturn.vertices.add(new Vec3(x, y1, z0));
		toReturn.vertices.add(new Vec3(x, y1, z1));
		toReturn.vertices.add(new Vec3(x, y0, z1));
		return toReturn;
	}

	public static GeneralPolygon MakeXZ(float x0, float z0, float x1, float z1,
			float y) {
		GeneralPolygon toReturn = new GeneralPolygon();
		toReturn.vertices.add(new Vec3(x0, y, z0));
		toReturn.vertices.add(new Vec3(x1, y, z0));
		toReturn.vertices.add(new Vec3(x1, y, z1));
		toReturn.vertices.add(new Vec3(x0, y, z1));

		return toReturn;
	}
}
