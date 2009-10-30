package br.com.infosites.pdbclient.core.dump;

import br.com.infosites.pdbclient.dialects.Dialect;
import br.com.infosites.pdbclient.domain.Column;
import br.com.infosites.pdbclient.domain.Table;

import java.io.IOException;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 14:59:38
 * To change this template use File | Settings | File Templates.
 */
public class TableDump {
	private Dialect dialect;

	private Table table;

	private TableDumpListener tableDumpListener;
	private TableDumpDataListener tableDumpDataListener;

	public TableDump( Table table, TableDumpListener tableDumpListener, TableDumpDataListener tableDumpDataListener ) {
		this.dialect = Dialect.getInstance( table.getDatabase().getType() );
		this.table = table;
		this.tableDumpListener = tableDumpListener;
		this.tableDumpDataListener = tableDumpDataListener;
	}

	public void dump( Writer writer ) throws IOException {
		if ( tableDumpListener != null ) {
			tableDumpListener.onDumpTable( table );
		}
		writer.write( dialect.createTable( table ) );
	}

	public void dumpData( Writer writer ) throws SQLException, IOException {
		int max = 100;
		long current = 0;
		long total = numRows();

		while ( current < total ) {
			Statement statement = table.getDatabase().getConnection().createStatement();
			ResultSet rs = statement.executeQuery( dialect.selectAll( table, current, max ) );

			while ( rs.next() ) {
				if ( tableDumpDataListener != null ) {
					tableDumpDataListener.onDumpRow( table, rs, current + 1, total );
				}
				writer.write( dialect.insert( table, rs ) );
				current++;
			}

			rs.close();
			statement.close();
		}

		writer.write( "\n\n" );
	}

	public long numRows() throws SQLException {
		Statement statement = table.getDatabase().getConnection().createStatement();
		ResultSet rs = statement.executeQuery( "select count(*) as total from " + table.getName() + ";" );
		rs.next();
		long total = rs.getLong( "total" );
		rs.close();
		statement.close();
		
		return total;
	}
}
