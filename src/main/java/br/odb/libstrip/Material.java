package br.odb.libstrip;

import java.io.Serializable;

import br.odb.utils.Color;

public class Material implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6359681992594825126L;
	
	public final Color mainColor = new Color();
	public final String name;
	public final String texture;
	public final String fragmentShader;
	public final String vertexShader;
	
	public Material(String name, int r, int g, int b, int a, String texture, String vertexShader, String fragmentShader ) {
		this.name = name;
		this.texture = texture;
		this.fragmentShader = fragmentShader;
		this.vertexShader = vertexShader;		
		mainColor.set(r, g, b, a);
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
	}

	public Material(Color color, String texture, String vertexShader, String fragmentShader) {
		name = "(unamed)";
		this.texture = texture;
		this.fragmentShader = fragmentShader;
		this.vertexShader = vertexShader;		
		mainColor.set(color);
	}

	public boolean equals(Material another) {
		return another.name.equals(name)
				&& mainColor.equals(another.mainColor );
	}

	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Material other = (Material) obj;
		if (fragmentShader == null) {
			if (other.fragmentShader != null)
				return false;
		} else if (!fragmentShader.equals(other.fragmentShader))
			return false;
		if (mainColor == null) {
			if (other.mainColor != null)
				return false;
		} else if (!mainColor.equals(other.mainColor))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (texture == null) {
			if (other.texture != null)
				return false;
		} else if (!texture.equals(other.texture))
			return false;
		if (vertexShader == null) {
			if (other.vertexShader != null)
				return false;
		} else if (!vertexShader.equals(other.vertexShader))
			return false;
		return true;
	}

	public Material makeCopy() {
		return new Material(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fragmentShader == null) ? 0 : fragmentShader.hashCode());
		result = prime * result
				+ ((mainColor == null) ? 0 : mainColor.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((texture == null) ? 0 : texture.hashCode());
		result = prime * result
				+ ((vertexShader == null) ? 0 : vertexShader.hashCode());
		return result;
	}

	@Override
	public String toString() {

		return "<material name='" + name + "' mainColor='"
				+ mainColor.getHTMLColor() + "' />";
	}
}
