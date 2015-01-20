package br.odb.libstrip;

import java.io.Serializable;

import br.odb.utils.Color;

public class Material implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6359681992594825126L;
	
	public final Color mainColor;
	public final String name;
	public final String texture;
	public final String fragmentShader;
	public final String vertexShader;
	
	public Material(String name, int r, int g, int b, int a, String texture, String vertexShader, String fragmentShader ) {
		this.name = name;
		this.texture = texture;
		this.fragmentShader = fragmentShader;
		this.vertexShader = vertexShader;		
		mainColor = new Color(r, g, b, a);
	}

	public Material(String name, int r, int g, int b, String texture, String vertexShader, String fragmentShader ) {
		this(name, r, g, b, 255, texture, vertexShader, fragmentShader);
	}

	public Material(Material material) {
		this(material.name, material.mainColor.r, material.mainColor
				.g, material.mainColor.b, material.mainColor.a, material.texture, material.vertexShader, material.fragmentShader);
	}

	public Material(String name, String texture, String vertexShader, String fragmentShader ) {
		this.name = name;
		this.texture = texture;
		this.fragmentShader = fragmentShader;
		this.vertexShader = vertexShader;
		mainColor = new Color();
	}

	public Material(Color color, String texture, String vertexShader, String fragmentShader) {
		name = "(unamed)";
		this.texture = texture;
		this.fragmentShader = fragmentShader;
		this.vertexShader = vertexShader;
		
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
