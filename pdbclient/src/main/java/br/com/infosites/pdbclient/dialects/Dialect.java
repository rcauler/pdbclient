package br.com.infosites.pdbclient.dialects;

import br.com.infosites.pdbclient.domain.Column;
import br.com.infosites.pdbclient.domain.ColumnType;
import br.com.infosites.pdbclient.domain.DatabaseType;
import br.com.infosites.pdbclient.domain.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 15:48:20
 * To change this template use File | Settings | File Templates.
 */
public abstract class Dialect {
	public static Dialect getInstance( DatabaseType databaseType ) {
		if ( databaseType.equals( DatabaseType.mysql )) {
			return new MySqlDialect();
		}

		return null;
	}

	private static final List<ColumnType> STRING_TYPES;

	static {
		STRING_TYPES = new ArrayList<ColumnType>();
		STRING_TYPES.add( ColumnType.dbDateTime );
		STRING_TYPES.add( ColumnType.dbLongText );
		STRING_TYPES.add( ColumnType.dbVarchar );
	}

	public abstract String getColumnScapeBegin();
	public abstract String getColumnScapeEnd();
	public abstract String getStringChar();
	public abstract String selectAll( Table table, long offset, int max );

	public String createTable( Table table ) {
		StringBuffer buffer = new StringBuffer();

		boolean firstColumn = true;

		buffer.append( "CREATE TABLE " + getColumnScapeBegin() + table.getName() + getColumnScapeEnd() + " (" );
		for ( Column column : table.getColumns() ) {
			if ( !firstColumn ) {
				buffer.append( "," );
			}
			buffer.append( "\n\t" );
			buffer.append( getColumnScapeBegin() + column.getName() + getColumnScapeEnd() + " " + column.getType().toString() );
			firstColumn = false;
		}

		buffer.append( "\n);\n\n" );

		return buffer.toString();
	}

	public String insert( Table table, ResultSet rs ) throws SQLException {
		StringBuffer buffer = new StringBuffer();

		buffer.append( "INSERT INTO " + getColumnScapeBegin() + table.getName() + getColumnScapeEnd() + " ( " );

		boolean firstColumn = true;

		for( Column column : table.getColumns() ) {
			if ( !firstColumn ) {
				buffer.append( ", " );
			}
			buffer.append( getColumnScapeBegin() + column.getName() + getColumnScapeEnd() );
			firstColumn = false;
		}

		buffer.append( " )\nVALUES ( " );

		firstColumn = true;
		for( Column column : table.getColumns() ) {
			if ( !firstColumn ) {
				buffer.append( ", " );
			}

			buffer.append( value( column, rs.getObject( column.getName() ) ) );

			firstColumn = false;
		}

		buffer.append( " );\n" );

		return buffer.toString();
	}

	public String value( Column column, Object value ) {
		if ( value == null ) {
			return "null";
		} else {
			if ( STRING_TYPES.contains( column.getType() ) ) {
				return getStringChar() + value + getStringChar();
			} else {
				return value.toString();
			}
		}
	}
}
