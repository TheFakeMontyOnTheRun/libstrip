package br.odb.libstrip;

import br.odb.utils.Color;
import br.odb.utils.math.Vec3;

public class GeneralTriangleFactory implements AbstractTriangleFactory {

	public AbstractTriangle makeTrig(float x, float y, float z, float x2, float y2,
			float z2, float x3, float y3, float z3, int lastColor,
			Vec3 lightDirection) {

		GeneralTriangle toReturn = new GeneralTriangle();
		
		toReturn.x0 = x;
		toReturn.y0 = y;
		toReturn.z0 = z;
		toReturn.x1 = x2;
		toReturn.y1 = y2;
		toReturn.z1 = z2;
		toReturn.x2 = x3;
		toReturn.y2 = y3;
		toReturn.z2 = z3;
		
		toReturn.setColor( new Color( lastColor ) );

		return toReturn;
	}

}
