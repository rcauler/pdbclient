package br.com.infosites.pdbclient.core.dump;

import br.com.infosites.pdbclient.domain.Database;
import br.com.infosites.pdbclient.domain.Table;

import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 14:59:09
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseDump {
	private Database database;

	public Database getDatabase() {
		return database;
	}

	private List<TableDump> tables;

	private TableDumpListener tableDumpListener;
	private TableDumpDataListener tableDumpDataListener;

	public DatabaseDump( Database database, TableDumpListener tableDumpListener, TableDumpDataListener tableDumpDataListener ) {
		this.database = database;
		this.tableDumpListener = tableDumpListener;
		this.tableDumpDataListener = tableDumpDataListener;

		tables = new ArrayList<TableDump>();

		for( Table table : database.getTables() ) {
			TableDump tableDump = new TableDump( table, tableDumpListener, tableDumpDataListener );
			tables.add( tableDump );
		}
	}

	public void dump( Writer writer ) throws IOException, SQLException {
		for( TableDump tableDump : tables ) {
			tableDump.dump( writer );
			tableDump.dumpData( writer );
		}
	}
}
