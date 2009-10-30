package br.com.infosites.pdbclient.domain;

/**
 * Created by IntelliJ IDEA.
 * User: rcauler
 * Date: 08/10/2009
 * Time: 12:18:44
 * To change this template use File | Settings | File Templates.
 */
public enum DatabaseType {
	mysql( "com.mysql.jdbc.Driver", "jdbc:mysql://%s/%s?user=%s&password=%s" ),
	sqlserver( "", "" );

	private String urlTemplate;

	private String driver;

	public String getDriver() {
		return driver;
	}

	DatabaseType( String driver, String urlTemplate ) {
		this.driver = driver;
		this.urlTemplate = urlTemplate;
	}

	public String generateUrl( String host, String name, String login, String password ) {
		return String.format( urlTemplate, host, name, login, password );
	}
}
