package br.odb.libstrip;
//TODO: revise this, you moron.
import java.util.Hashtable;

import br.odb.utils.math.Vec3;

public class VertexArrayManager {

	/**
	 * Holds the unique instances to the verteces
	 */
	private Hashtable<Vec3, Integer> Verteces;

	/**
	 * 
	 * @param x
	 * @param y
	 * @param z
	 * @return index for the given vertex. If there is no existing index for the
	 *         vertex, one is created
	 */
	public int requestVertex(float x, float y, float z) {

		Integer id = Integer.valueOf(-1);

		Vec3 v = new Vec3();

		v.x = (x);
		v.y = (y);
		v.z = (z);

		if (Verteces.containsKey(v))
			id = Verteces.get(v);
		else {

			id = Integer.valueOf(Verteces.size());
			Verteces.put(v, id);
		}

		return id.intValue();
	}

	/**
	 * Construtor - only builds the bare essential
	 */
	protected VertexArrayManager() {
		Verteces = new Hashtable<Vec3, Integer>();
	}

	public void init(int size) {
	}

	public void pushIntoFrameAsStatic(float[] vertexData,
			float[] singleColorData) {
	}

	public void flush() {
	}

	public void draw() {
	}

	public void pushIntoFrameAsStatic(int[] verticesBits, int[] colorBits) {
	}
}