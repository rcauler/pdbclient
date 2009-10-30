package br.com.infosites.pdbclient.domain;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 10:52:58
 * To change this template use File | Settings | File Templates.
 */
public class Database {
	private Connection connection;

	public Connection getConnection() {
		return connection;
	}

	private String host;

	public String getHost() {
		return host;
	}

	private String name;

	public String getName() {
		return name;
	}

	private String login;

	public String getLogin() {
		return login;
	}

	private String password;

	public String getPassword() {
		return password;
	}

	private DatabaseType type;

	public DatabaseType getType() {
		return type;
	}

	private List<Table> tables;

	public List<Table> getTables() {
		return tables;
	}

	public Database( String host, String name, String login, String password, DatabaseType type ) {
		this.host = host;
		this.name = name;
		this.login = login;
		this.password = password;
		this.type = type;
	}

	public Connection connect() throws SQLException, ClassNotFoundException {
		if ( connection != null && !connection.isClosed() ) {
			connection.close();
		}

		Class.forName( type.getDriver() );
		String url = type.generateUrl( host, name, login, password );

		connection = DriverManager.getConnection( url );

		DatabaseMetaData dbMetaData = connection.getMetaData();

		ResultSet rs = dbMetaData.getTables( null, null, null, null );

		tables = new ArrayList<Table>();

		while ( rs.next() ) {
			String tableName = rs.getString( "TABLE_NAME" );
			String tableType = rs.getString( "TABLE_TYPE" );

			Table table = new Table( this, tableName );
			tables.add( table );
		}

		return connection;
	}
}
