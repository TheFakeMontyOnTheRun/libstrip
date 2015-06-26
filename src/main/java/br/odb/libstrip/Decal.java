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
	
	
	private void alignWithDirection( Direction d ) {
		float tmp;
		
		switch( d ) {
		case FLOOR:
			for ( GeneralTriangle gt : faces ) {
				tmp = gt.y0;
				gt.y0 = gt.z0;
				gt.z0 = tmp;
				
				tmp = gt.y1;
				gt.y1 = gt.z1;
				gt.z1 = tmp;
				
				tmp = gt.y1;
				gt.y1 = gt.z1;
				gt.z1 = tmp;
			}

			break;
		case CEILING:
			break;
		case W:
			for ( GeneralTriangle gt : faces ) {
				tmp = gt.x0;
				gt.x0 = gt.z0;
				gt.z0 = tmp;
				
				tmp = gt.x1;
				gt.x1 = gt.z1;
				gt.z1 = tmp;
				
				tmp = gt.x1;
				gt.x1 = gt.z1;
				gt.z1 = tmp;
			}

			break;
		}
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
		GeneralTriangle[] rawTrigs = loadFrom( is, -1.2f, 255, 255 );

		for ( GeneralTriangle gt : rawTrigs ) {
			toReturn.faces.add( gt );
		}
		
		return toReturn;		
	}
	
	
	public static final GeneralTriangle[] loadGraphic(InputStream is, float screenWidth, float screenHeight )
			throws IOException {
		
		return loadFrom( is, -1.2f, screenWidth, screenHeight );
		
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
					t.material = new Material( null, new Color(), null, null ,null );
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
					t.material = new Material( null, new Color(), null, null ,null );
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
				t.material = new Material( null, new Color(), null, null ,null );
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
