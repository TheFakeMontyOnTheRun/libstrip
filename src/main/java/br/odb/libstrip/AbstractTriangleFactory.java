package br.odb.libstrip;

import br.odb.utils.math.Vec3;

public interface AbstractTriangleFactory {

	AbstractTriangle makeTrig(float x, float y, float z, float x2, float y2,
			float z2, float x3, float y3, float z3, int lastColor,
			Vec3 lightDirection);

}
