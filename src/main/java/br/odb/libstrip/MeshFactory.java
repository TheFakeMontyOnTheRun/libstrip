/**
 * 
 */
package br.odb.libstrip;

import br.odb.utils.math.Vec3;

/**
 * @author monty
 * 
 */
public abstract class MeshFactory {

	public static final Vec3 DEFAULT_LIGHT_VECTOR = new Vec3(-1.0f, -1.0f,
			-1.0f);

	public abstract IndexedSetFace makeTrig(float x, float y, float z,
			float x2, float y2, float z2, float x3, float y3, float z3,
			int color, Vec3 defaultLightVector);

	public abstract Mesh emptyMeshNamed(String subToken);

	public abstract AbstractTriangle[][] newTriangleGroups(int i);

}
