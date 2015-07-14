/**
 * 
 */
package br.odb.libstrip;

import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import br.odb.utils.Color;
import br.odb.utils.Direction;
import br.odb.utils.FileServerDelegate;
import br.odb.utils.math.Vec3;

/**
 * @author monty
 *
 */
public class Decal extends GeneralTriangleMesh {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5971074037757787546L;

	/**
	 * @param name
	 */
	public Decal(String name) {
		super(name);
	}

	private static final float normalizeZ( byte z, HashMap<Byte, Float> zRoundings ) {
		
		if (! zRoundings.containsKey( z ) ) {
			zRoundings.put( z, ( (float) - ( ( z + ( -Byte.MIN_VALUE ) ) / 50.0f ) ) );
		}
		
		return zRoundings.get( z );
	}

	private static final float normalizeX( byte x, HashMap<Byte, Float> xRoundings, float screenWidth, float screenHeight ) {

		float rounded;
		if ( !xRoundings.containsKey( x ) ) {
			rounded = ( -( 0.5f ) + ( ( ( x + ( -Byte.MIN_VALUE ) ) * 800.0f ) / ( Byte.MAX_VALUE - Byte.MIN_VALUE ) ) / ( 800.0f ) );
			xRoundings.put( x, rounded );
		}

		rounded = xRoundings.get( x );
		return rounded;
	}
	
	private static final float normalizeY( byte y, HashMap<Byte, Float> yRoundings, float screenWidth, float screenHeight ) {

		
		if ( !yRoundings.containsKey( y ) ) {
			
			yRoundings.put( y, ( 0.5f ) - ( ( ( y + (-Byte.MIN_VALUE)) * 480.f ) / ( Byte.MAX_VALUE - Byte.MIN_VALUE ) ) / 480.0f );
		}

		return yRoundings.get( y );
	}
	
	
	public static final Decal loadDecal( String name, InputStream is, Direction d )
			throws IOException {
		
		Decal toReturn = new Decal( name ); 
		GeneralTriangle[] rawTrigs = loadFrom( is, -1.2f, 1, 1 );

		for ( GeneralTriangle gt : rawTrigs ) {
			
			toReturn.faces.add( applyToFace( d, new Vec3( 0.0f, 0.0f, 0.0f ), new Vec3( 1.0f, 1.0f, 1.0f), gt, 0.5f ) );
		}
		return toReturn;		
	}
	
	
	public static final GeneralTriangle[] loadGraphic(InputStream is, float screenWidth, float screenHeight )
			throws IOException {
		
		return loadFrom( is, -1.2f, screenWidth, screenHeight );
		
	}

	public static GeneralTriangle applyToFace( Direction d, Vec3 p0, Vec3 p1, GeneralTriangle face, float delta ) {
		
		float x;
		float y;
		float z;
		float x0 = 0.0f;
		float y0 = 0.0f;
		float z0 = 0.0f;
		float x1 = 0.0f;
		float y1 = 0.0f;
		float z1 = 0.0f;
		float x2 = 0.0f;
		float y2 = 0.0f;
		float z2 = 0.0f;
		Vec3 pd = p1.sub( p0 );
		float dx = pd.x;
		float dy = pd.y;
		float dz = pd.z;
		
		x = p0.x + dx / 2;
		y = p0.y;
		z = p0.z + dz / 2;
		
		face.hint = d;
		
		switch ( d ) {
			case N: {
				x0 = x + ( face.x0 ) * dx;
				y0 = ( dy / 2.0f ) + y + ( face.y0 ) * dy;
				z0 = z - delta - ( dz / 2 );
				x1 = x + ( face.x1 ) * dx;
				y1 = ( dy / 2.0f ) + y + ( face.y1 ) * dy;
				z1 = z - delta - ( dz / 2 );
				x2 = x + ( face.x2 ) * dx;
				y2 = ( dy / 2.0f ) + y + ( face.y2 ) * dy;
				z2 = z - delta - ( dz / 2 );
			}
			break;
			case E: {
				x0 = x - delta + ( dx / 2 );
				y0 = ( dy / 2.0f ) + y + ( face.y0 * dy );
				z0 = z + ( face.x0 * dx );
				x1 = x - delta + ( dx / 2 );
				y1 = ( dy / 2.0f ) + y + ( face.y1 * dy );
				z1 = z + ( face.x1 * dz );
				x2 = x - delta + ( dx / 2 );
				y2 = ( dy / 2.0f ) + y + ( face.y2 * dy );
				z2 = z + ( face.x2 * dz );
			}
			break;
			case S: {
				x0 = x + ( face.x0 ) * dx;
				y0 = ( dy / 2.0f ) + y + ( face.y0 ) * dy;
				z0 = z + delta + ( dz / 2 );
				x1 = x + ( face.x1 ) * dx;
				y1 = ( dy / 2.0f ) + y + ( face.y1 ) * dy;
				z1 = z + delta + ( dz / 2 );
				x2 = x + ( face.x2 ) * dx;
				y2 = ( dy / 2.0f ) + y + ( face.y2 ) * dy;
				z2 = z + delta + ( dz / 2 );
			}
			break;
			case W: {
				x0 = x + delta - ( dx / 2 );
				y0 = ( dy / 2.0f ) + y + ( face.y0 * dy );
				z0 = z + ( face.x0 * dx );
				x1 = x + delta - ( dx / 2 );
				y1 = ( dy / 2.0f ) + y + ( face.y1 * dy );
				z1 = z + ( face.x1 * dz );
				x2 = x + delta - ( dx / 2 );
				y2 = ( dy / 2.0f ) + y + ( face.y2 * dy );
				z2 = z + ( face.x2 * dz );			}
			break;
			case FLOOR: {
				x0 = x + ( face.x0 ) * dx;
				y0 = y + delta ;// + ( face.z0 );
				z0 = z + ( face.y0 ) * dz;
				x1 = x + ( face.x1 ) * dx;
				y1 = y + delta ;// + ( face.z1 );
				z1 = z + ( face.y1 ) * dz;
				x2 = x + ( face.x2 ) * dx;
				y2 = y + delta ;// + ( face.z2 );
				z2 = z + ( face.y2 ) * dz;
			}
			break;
			case CEILING: {
				x0 = x + ( face.x0 );
				y0 = - delta +pd.y + y + ( face.z0 );
				z0 = z + ( face.y0 );
				x1 = x + ( face.x1 );
				y1 = - delta + pd.y + y + ( face.z1 );
				z1 = z + ( face.y1 );
				x2 = x + ( face.x2 );
				y2 = - delta + pd.y + y + ( face.z2 );
				z2 = z + ( face.y2 );
			}
			break;
		}

		face.x0 = x0;
		face.y0 = y0;
		face.z0 = z0;
		
		face.x1 = x1;
		face.y1 = y1;
		face.z1 = z1;
		
		face.x2 = x2;
		face.y2 = y2;
		face.z2 = z2;		
		
		return face;
	}
	
	private static GeneralTriangle[] loadFrom( InputStream is, float offset, float screenWidth, float screenHeight ) throws IOException {
		
		DataInputStream dis;
		dis = new DataInputStream( is );
		
		HashMap< Byte, Float > xRoundings = new HashMap< Byte, Float >();
		HashMap< Byte, Float > yRoundings = new HashMap< Byte, Float >();
		HashMap< Byte, Float > zRoundings = new HashMap< Byte, Float >();
		
		ArrayList< GeneralTriangle> scratch = new ArrayList< GeneralTriangle>();
		GeneralTriangle[] toReturn = null;
		int entries = 0;
		byte[] bytes = new byte[11];
		byte[] edge = new byte[9];
		GeneralTriangle t;
		int header;
		int majorPolys;
		
		header = -1;
		
		if ( header == -1 ) {
			
			majorPolys = dis.readInt();
			
			for ( int d = 0; d < majorPolys; ++d ) {
				
				entries = dis.readInt();
				
				for (int c = 0; c < entries; ++c) {
					
					is.read(edge);
					
					if ( edge[ 8 ] == Byte.MIN_VALUE )
						continue;
					
					t = new GeneralTriangle();
					t.material = new Material( null, new Color(), null, null );
					t.material.mainColor.a = (edge[0] + (-Byte.MIN_VALUE));
					t.material.mainColor.r = (edge[1] + (-Byte.MIN_VALUE));
					t.material.mainColor.g = (edge[2] + (-Byte.MIN_VALUE));
					t.material.mainColor.b = (edge[3] + (-Byte.MIN_VALUE));
				
					
					t.x0 = normalizeX( edge[4], xRoundings, screenWidth, screenHeight );
					t.y0 = normalizeY( edge[5], yRoundings, screenWidth, screenHeight );
					
					t.x1 = normalizeX( edge[6], xRoundings, screenWidth, screenHeight );
					t.y1 = normalizeY( edge[7], yRoundings, screenWidth, screenHeight );
					
					t.x2 = normalizeX( edge[4], xRoundings, screenWidth, screenHeight );
					t.y2 = normalizeY( edge[5], yRoundings, screenWidth, screenHeight );
					
					t.z0 = normalizeZ( Byte.MIN_VALUE, zRoundings );
					t.z1 = normalizeZ( Byte.MIN_VALUE, zRoundings );
					t.z2 = normalizeZ( edge[ 8 ], zRoundings );
					
					t.flush();					
					scratch.add( t );
					
					t = new GeneralTriangle();
					t.material = new Material( null, new Color(), null, null );
					t.material.mainColor.a = (edge[0] + (-Byte.MIN_VALUE));
					t.material.mainColor.r = (edge[1] + (-Byte.MIN_VALUE));
					t.material.mainColor.g = (edge[2] + (-Byte.MIN_VALUE));
					t.material.mainColor.b = (edge[3] + (-Byte.MIN_VALUE));
					
					t.x0 = normalizeX( edge[6], xRoundings, screenWidth, screenHeight );
					t.y0 = normalizeY( edge[7], yRoundings, screenWidth, screenHeight );
					
					t.x1 = normalizeX( edge[6], xRoundings, screenWidth, screenHeight );
					t.y1 = normalizeY( edge[7], yRoundings, screenWidth, screenHeight );
					
					t.x2 = normalizeX( edge[4], xRoundings, screenWidth, screenHeight );
					t.y2 = normalizeY( edge[5], yRoundings, screenWidth, screenHeight );
					
					t.z0 = normalizeZ( Byte.MIN_VALUE, zRoundings );
					t.z1 = normalizeZ( edge[ 8 ], zRoundings );
					t.z2 = normalizeZ( edge[ 8 ], zRoundings );
					
					
					t.flush();	
					scratch.add( t );
				}
			}
		}
		
		header = -2;
		if ( header == -2 ) {
			
			entries = dis.readInt();
			
			for (int c = 0; c < entries; ++c) {
				
				is.read(bytes);
				
				t = new GeneralTriangle();
				t.material = new Material( null, new Color(), null, null );
				t.material.mainColor.a = (bytes[0] + (-Byte.MIN_VALUE));
				t.material.mainColor.r = (bytes[1] + (-Byte.MIN_VALUE));
				t.material.mainColor.g = (bytes[2] + (-Byte.MIN_VALUE));
				t.material.mainColor.b = (bytes[3] + (-Byte.MIN_VALUE));
				
				t.x0 = normalizeX( bytes[4], xRoundings, screenWidth, screenHeight );
				t.y0 = normalizeY( bytes[5], yRoundings, screenWidth, screenHeight );
				
				t.x1 = normalizeX( bytes[6], xRoundings, screenWidth, screenHeight );
				t.y1 = normalizeY( bytes[7], yRoundings, screenWidth, screenHeight );
				
				t.x2 = normalizeX( bytes[8], xRoundings, screenWidth, screenHeight );
				t.y2 = normalizeY( bytes[9], yRoundings, screenWidth, screenHeight );
				
				t.z0 = normalizeZ( bytes[ 10 ], zRoundings );
				t.z1 = normalizeZ( bytes[ 10 ], zRoundings );
				t.z2 = normalizeZ( bytes[ 10 ], zRoundings );
				
				t.flush();
				
				
				scratch.add( t );
			}
		}
		
		toReturn = new GeneralTriangle[ scratch.size() ];
		toReturn = scratch.toArray( toReturn );
		
		return toReturn;
	}
	
	public Decal( FileServerDelegate fileServer, String decalName, String decalFilename, float screenWidth, float screenHeight ) {
		
		super( decalFilename );
		
		try {			
			InputStream is = fileServer.openAsInputStream( decalFilename );
			
			GeneralTriangle[] trigs = loadFrom( is, 0.0f, screenWidth, screenHeight );
			
			for ( int c = 0; c < trigs.length; ++c ) {
				this.faces.add( trigs[ c ] );
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
