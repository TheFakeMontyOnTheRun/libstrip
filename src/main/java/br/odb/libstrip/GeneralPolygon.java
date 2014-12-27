package br.odb.libstrip;

import java.util.ArrayList;

import br.odb.utils.Color;
import br.odb.utils.math.Vec3;

public class GeneralPolygon implements IndexedSetFace {

	private ArrayList<Integer> indexes = new ArrayList<Integer>();
	private ArrayList<Vec3> vertexes = new ArrayList<Vec3>();
	public Color color = new Color();
	public String id = new String( "" );
	
	@Override
	public IndexedSetFace makeCopy() {

		GeneralPolygon poly = new GeneralPolygon();

		for (int c = 0; c < getTotalIndexes(); ++c)
			poly.addIndex(getIndex(c));

		return poly;
	}
	
	@Override
	public String toString() {
		String toReturn = "";
		
		for ( Vec3 v : vertexes ) {
			toReturn += "" + v.x + " " + v.y + " " + v.z + "\n";
		}
		
		return toReturn;
	}

	@Override
	public int getTotalIndexes() {

		return indexes.size();
	}

	public int getIndex(int d) {

		return indexes.get(d).intValue();
	}

	public void addIndex(int index) {

		indexes.add(Integer.valueOf(index));
	}

	@Override
	public Vec3 getVertex(int c) {

		return vertexes.get(c);
	}

	
	public void addVertex(Vec3 v) {

		vertexes.add(v);
	}

	@Override
	public Color getColor() {

		return color;
	}

	@Override
	public void setColor(Color c) {

		color = c;
	}

	
	public void destroy() {

		indexes.clear();
		indexes = null;
		vertexes.clear();
		vertexes = null;
		color = null;
	}
}
