package br.odb.libstrip;

import br.odb.utils.math.Vec3;

public interface AbstractTriangle extends IndexedSetFace {
	Vec3 makeNormal();
	void flatten( float z);
	void flush();
}
