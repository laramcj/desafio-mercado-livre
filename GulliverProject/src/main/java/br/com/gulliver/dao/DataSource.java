package br.com.gulliver.dao;
import java.sql.Connection;
import java.sql.DriverManager;

import oracle.jdbc.OracleDriver;

public class DataSource {
	private Connection connection;
	private String url;
	private String hostname;
	private String username;
	private String password;
	private String database;
	private int port;
	
	public DataSource() {
		try {
			DriverManager.registerDriver(new OracleDriver());
			
			hostname="oracle.fiap.com.br";
			port=1521;
			username="traveller.project";
			password="elastraveller";
			database = "SID";
			url = "jdbc:oracle:thin:@"+
					hostname+":"+
					port+":"+
					database;
			
			connection = DriverManager.getConnection(url, username, password);
			
			if(connection == null) {
				System.out.println("Erro ao conectar com o BD");
			} else {
				System.out.println("Conexão feita com sucessos");
			}
			
		}
		catch(Exception ex) {
			System.out.println("Falha de conexão com o banco de dados " + ex.getMessage());
		}
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}
