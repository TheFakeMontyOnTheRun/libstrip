/**
 * 
 */
package br.odb.libstrip;

import br.odb.utils.Color;
import br.odb.utils.math.Vec3;

/**
 * @author monty
 * 
 */
public interface IndexedSetFace {

	public IndexedSetFace makeCopy();

	public int getTotalIndexes();

	public int getIndex(int d);

	public void addIndex(int index);

	public Vec3 getVertex(int c);

	public void addVertex(Vec3 v);

	public Color getColor();

	public void setColor(Color c);

	public Material getMaterial();

	public void destroy();
}
