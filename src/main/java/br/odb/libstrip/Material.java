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
	
	public Material(String name, Color c, String texture, String vertexShader, String fragmentShader ) {
		this.name = name;
		this.texture = texture;
		this.fragmentShader = fragmentShader;
		this.vertexShader = vertexShader;		
		mainColor.set( c );
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Material other = (Material) obj;
		if (fragmentShader == null) {
			if (other.fragmentShader != null) {
				return false;
			}
		} else if (!fragmentShader.equals(other.fragmentShader)) {
			return false;
		}
		
		if (!mainColor.equals(other.mainColor)) {
			return false;
		}
		
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (texture == null) {
			if (other.texture != null) {
				return false;
			}
		} else if (!texture.equals(other.texture)) {
			return false;
		}
		if (vertexShader == null) {
			if (other.vertexShader != null) {
				return false;
			}
		} else if (!vertexShader.equals(other.vertexShader)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((fragmentShader == null) ? 0 : fragmentShader.hashCode());
		result = prime * result
				+ ( mainColor.hashCode() );
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((texture == null) ? 0 : texture.hashCode());
		result = prime * result
				+ ((vertexShader == null) ? 0 : vertexShader.hashCode());
		return result;
	}

	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder("");
		
		sb.append( "<material ");
		sb.append( "name='" + name + "' " );
		sb.append( "mainColor='" + mainColor.getHTMLColor() + "' " );
		
		if ( texture != null ) {
			sb.append( "texture = '" + texture + "' " );
		}
		
		if ( fragmentShader != null ) {
			sb.append( "fragment = '" + fragmentShader + "' " );
		}
		
		if ( vertexShader != null ) {
			sb.append( "vertex = '" + vertexShader + "' " );	
		}
		
		sb.append( " />" );

		return sb.toString();
	}
}
