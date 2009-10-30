package br.com.infosites.pdbclient.dsl;

import br.com.infosites.pdbclient.core.dump.Dumper;
import br.com.infosites.pdbclient.core.dump.TableDumpDataListener;
import br.com.infosites.pdbclient.core.dump.TableDumpListener;
import br.com.infosites.pdbclient.domain.Database;

import java.io.*;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 10:59:11
 * To change this template use File | Settings | File Templates.
 */
public class DatabaseDump {
	private Database database;
	private TableDumpListener tableDumpListener;
	private TableDumpDataListener tableDumpDataListener;

	public DatabaseDump( Database database, TableDumpListener tableDumpListener, TableDumpDataListener tableDumpDataListener ) {
		this.database = database;
		this.tableDumpListener = tableDumpListener;
		this.tableDumpDataListener = tableDumpDataListener;
	}

	public void in( Writer writer ) throws IOException, SQLException {
		Dumper dumper = new Dumper( database, tableDumpListener, tableDumpDataListener );
		dumper.dump( writer );
	}

	public void in( String path ) throws IOException, SQLException {
		File file  = new File( path );
		if ( !file.exists() ) {
			file.createNewFile();
		}
		Writer writer = new BufferedWriter( new FileWriter( file, false ) );
		in( writer );
		writer.close();
	}
}
