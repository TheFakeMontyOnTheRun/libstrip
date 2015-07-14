package br.odb.libstrip;

/**
 * ShaderProram
 * @author monty
 * @brief Merely a "load this program, please" object. It's not meat to be a full object-orientation for a shader.
 */
public class ShaderProgram {

	public enum ShaderKind {
		Fragment,
		Vertex,
		Geometry
	}

	public final String name;
	public final ShaderKind kind;
	public final String code;
	
	public ShaderProgram( String name, ShaderKind kind, String code ) {
		this.name = name;
		this.kind = kind;
		this.code = code;
	}
}
