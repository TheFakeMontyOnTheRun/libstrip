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

	public Vec3 getVertex(int c);

	public Color getColor();

	public void setColor(Color c);
}
