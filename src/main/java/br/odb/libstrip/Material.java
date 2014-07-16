package br.odb.libstrip;

import br.odb.utils.Color;

public class Material {

	private Color mainColor;
	private String name;

	public Material(String name, int r, int g, int b, int a) {
		this.name = name;
		mainColor = new Color(r, g, b, a);
	}

	public Material(String name, int r, int g, int b) {
		this(name, r, g, b, 255);
	}

	public Material(Material material) {
		this(material.name, material.mainColor.getR(), material.mainColor
				.getG(), material.mainColor.getB(), material.mainColor.getA());
	}

	public Material(String op1) {
		name = op1;
	}

	public Material(Color color) {
		mainColor = new Color(color);
	}

	/**
	 * @param mainColor
	 *            the mainColor to set
	 */
	public void setMainColor(Color mainColor) {
		this.mainColor = mainColor;
	}

	/**
	 * @return the mainColor
	 */
	public Color getMainColor() {
		return mainColor;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	public boolean equals(Material another) {
		return another.getName().equals(name)
				&& mainColor.equals(another.getMainColor());
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

	public void destroy() {

		name = null;
		mainColor = null;
	}
}
