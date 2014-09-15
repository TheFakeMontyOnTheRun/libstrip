package br.odb.libstrip;

import br.odb.utils.Color;

public class Material {

	public final Color mainColor;
	public final String name;

	public Material(String name, int r, int g, int b, int a) {
		this.name = name;
		mainColor = new Color(r, g, b, a);
	}

	public Material(String name, int r, int g, int b) {
		this(name, r, g, b, 255);
	}

	public Material(Material material) {
		this(material.name, material.mainColor.r, material.mainColor
				.g, material.mainColor.b, material.mainColor.a);
	}

	public Material(String op1) {
		name = op1;
		mainColor = new Color();
	}

	public Material(Color color) {
		name = "(unamed)";
		mainColor = new Color(color);
	}

	public boolean equals(Material another) {
		return another.name.equals(name)
				&& mainColor.equals(another.mainColor );
	}

	@Override
	public boolean equals(Object another) {
		if (another instanceof Material) {
			return equals((Material) another);
		} else {
			return false;
		}
	}

	public Material makeCopy() {
		return new Material(this);
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {

		return "<Material name: " + name + " mainColor: "
				+ mainColor.toString() + " />";
	}
}
