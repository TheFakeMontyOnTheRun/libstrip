package br.odb.libstrip;

import java.util.ArrayList;
import java.util.List;

import br.odb.utils.Color;
import br.odb.utils.math.Vec3;

public class GeneralPolygon {

	public final List<Integer> indexes = new ArrayList<>();
	public final List<Vec3> vertexes = new ArrayList<>();
	public final Color color = new Color();
	
	/**
	 * Makes a deep copy, including the order of the indexes
	 * @return a deep copy
	 */
	public GeneralPolygon makeCopy() {

		GeneralPolygon poly = new GeneralPolygon();
		
		for ( Integer i : indexes ) {
			poly.indexes.add( i.intValue() );
		}
		
		for ( Vec3 v : vertexes ) {
			poly.vertexes.add( new Vec3( v ) );
		}
			
		poly.color.set( color );

		return poly;
	}
	
	/**
	 * Convenience method for adding vertexes taking into consideration what's already in the polygon.
	 * @param v
	 */
	public void addVertex( Vec3 v ) {
		
		int index;
		index = vertexes.indexOf( v );
		
		if ( index != -1 ) {
			indexes.add( index );
		} else {
			vertexes.add( v );
			index = vertexes.indexOf( v );
			indexes.add( index );
		}
	}
	
	/**
	 * Returns a list of vertexes, separated by \n
	 */
	public String toString() {
		String toReturn = "";
		
		for ( Vec3 v : vertexes ) {
			toReturn += "" + v.x + " " + v.y + " " + v.z + "\n";
		}
		
		return toReturn;
	}
}
