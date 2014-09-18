package br.odb.libstrip;

import br.odb.utils.Color;
import br.odb.utils.math.Vec3;

public class GeneralQuad implements IndexedSetFace {
	final public Vec3[] vertex = { new Vec3(), new Vec3(), new Vec3(), new Vec3() };
	final public Color color = new Color();
	
	@Override
	public IndexedSetFace makeCopy() {
		
		GeneralQuad quad = new GeneralQuad();
		
		quad.vertex[ 0 ].set( vertex[ 0 ] );
		quad.vertex[ 1 ].set( vertex[ 1 ] );
		quad.vertex[ 2 ].set( vertex[ 2 ] );
		quad.vertex[ 3 ].set( vertex[ 3 ] );
		quad.color.set( color );
		
		return quad;
	}
	@Override
	public int getTotalIndexes() {
		return 4;
	}
	@Override
	public Vec3 getVertex(int c) {
		return vertex[ c ];
	}

	@Override
	public Color getColor() {
		return color;
	}
	@Override
	public void setColor(Color c) {
		color.set( c );
	}
}
