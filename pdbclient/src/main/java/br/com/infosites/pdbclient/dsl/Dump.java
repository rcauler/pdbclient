package br.com.infosites.pdbclient.dsl;

import br.com.infosites.pdbclient.core.dump.TableDumpDataListener;
import br.com.infosites.pdbclient.core.dump.TableDumpListener;
import br.com.infosites.pdbclient.domain.Database;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 10:58:18
 * To change this template use File | Settings | File Templates.
 */
public class Dump {
	private TableDumpListener tableDumpListener;
	private TableDumpDataListener tableDumpDataListener;

	public Dump( TableDumpListener tableDumpListener, TableDumpDataListener tableDumpDataListener ) {
		this.tableDumpListener = tableDumpListener;
		this.tableDumpDataListener = tableDumpDataListener;
	}

	public DatabaseDump database( Database database ) {
		return new DatabaseDump( database, tableDumpListener, tableDumpDataListener );
	}
}
