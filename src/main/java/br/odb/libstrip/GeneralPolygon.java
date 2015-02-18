package br.odb.libstrip;

import java.util.ArrayList;
import java.util.List;

import br.odb.utils.Color;
import br.odb.utils.math.Vec3;

public class GeneralPolygon {

	public final List<Vec3> vertices = new ArrayList<>();
	public final Color color = new Color();
	
	/**
	 * Makes a deep copy, including the order of the indexes
	 * @return a deep copy
	 */
	public GeneralPolygon makeCopy() {

		GeneralPolygon poly = new GeneralPolygon();
		
		for ( Vec3 v : vertices ) {
			poly.vertices.add( new Vec3( v ) );
		}
			
		poly.color.set( color );

		return poly;
	}
	
	/**
	 * Returns a list of vertexes, separated by \n
	 */
	public String toString() {
		String toReturn = "";
		
		for ( Vec3 v : vertices ) {
			toReturn += v.toString() + "\n";
		}
		
		return toReturn;
	}
}
