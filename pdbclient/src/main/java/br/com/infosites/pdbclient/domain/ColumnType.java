package br.com.infosites.pdbclient.domain;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 12:50:38
 * To change this template use File | Settings | File Templates.
 */
public enum ColumnType {
	dbBigInt( new String[] { "bigint" } ),
	dbBit( new String[] { "bit" } ),
	dbDateTime( new String[] { "datetime" } ),
	dbFloat( new String[] { "float" } ),
	dbInt( new String[] { "int" } ),
	dbLongBlob( new String[] { "longblob" } ),
	dbLongText( new String[] { "longtext" } ),
	dbMediumBlob( new String[] { "mediumblob" } ),
	dbTinyBlob( new String[] { "tinyblob" } ),
	dbVarchar( new String[] { "varchar" } );

	private String[] aliases;

	ColumnType( String[] aliases ) {
		this.aliases = aliases;
	}

	public static ColumnType parse( String type ) {
		for ( ColumnType columnType : ColumnType.values() ) {
			for ( String alias : columnType.aliases ) {
				if ( alias.toLowerCase().equals( type.toLowerCase() ) ) {
					return columnType;
				}
			}
		}

		throw new RuntimeException( "Unrecognized type: " + type + "." );
	}
}
