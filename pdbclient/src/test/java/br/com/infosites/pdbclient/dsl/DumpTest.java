package br.com.infosites.pdbclient.dsl;

import br.com.infosites.pdbclient.core.dump.TableDumpDataListener;
import br.com.infosites.pdbclient.core.dump.TableDumpListener;
import br.com.infosites.pdbclient.domain.Database;
import br.com.infosites.pdbclient.domain.DatabaseType;
import br.com.infosites.pdbclient.domain.Table;
import org.junit.Test;
import static org.junit.Assert.* ;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 13:18:12
 * To change this template use File | Settings | File Templates.
 */
public class DumpTest {
	@Test
	public void testDump() throws ClassNotFoundException, SQLException, IOException {
		Database database =  new Database( "localhost", "dbtest", "root", "senha", DatabaseType.mysql );
		database.connect();

		TableDumpListener tableDumpListener = new TableDumpListener() {
			public void onDumpTable( Table table ) {
				System.out.println( "----------------------------------------------" );
				System.out.println( "Dump table " + table.getName() + "." );
				System.out.println( "----------------------------------------------" );
			}
		};

		TableDumpDataListener tableDumpDataListener = new TableDumpDataListener() {
			public void onDumpRow( Table table, ResultSet rs, long current, long total ) {
				System.out.println( "Dump row " + current + " from " + total + " from table " + table.getName() + "." );
			}
		};

		Dump dump = new Dump( tableDumpListener, tableDumpDataListener );
		dump.database( database ).in( "C:\\Users\\rcauler\\Workspace\\dump.sql" );
	}
}
