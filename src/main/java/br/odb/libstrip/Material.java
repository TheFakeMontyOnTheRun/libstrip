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
	public final String shaderProgram;
	
	public Material(String name, Color c, String texture, String shaderProgram ) {
		this.name = name;
		this.texture = texture;
		this.shaderProgram = shaderProgram;		
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
		if ( !( obj instanceof Material ) ) {
			return false;
		}
		Material other = (Material) obj;
		
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
		if (shaderProgram == null) {
			if (other.shaderProgram != null) {
				return false;
			}
		} else if (!shaderProgram.equals(other.shaderProgram)) {
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
				+ ((shaderProgram == null) ? 0 : shaderProgram.hashCode());
		result = prime * result
				+ ( mainColor.hashCode() );
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((texture == null) ? 0 : texture.hashCode());
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
		
		if ( shaderProgram != null ) {
			sb.append( "program = '" + shaderProgram + "' " );	
		}
		
		sb.append( " />" );

		return sb.toString();
	}
}
