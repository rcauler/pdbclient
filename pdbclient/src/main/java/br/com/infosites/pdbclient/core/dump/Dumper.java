package br.com.infosites.pdbclient.core.dump;

import br.com.infosites.pdbclient.domain.Database;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 14:57:44
 * To change this template use File | Settings | File Templates.
 */
public class Dumper {
	private Database database;
	private TableDumpListener tableDumpListener;
	private TableDumpDataListener tableDumpDataListener;

	public Dumper( Database database, TableDumpListener tableDumpListener, TableDumpDataListener tableDumpDataListener ) {
		this.database = database;
		this.tableDumpListener = tableDumpListener;
		this.tableDumpDataListener = tableDumpDataListener;
	}

	public void dump( Writer writer ) throws IOException, SQLException {
		DatabaseDump databaseDump = new DatabaseDump( database, tableDumpListener, tableDumpDataListener );
		databaseDump.dump( writer );
	}
}
