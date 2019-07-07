package cbfx.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import cbf.utils.LogUtils;



public class DataBaseOperations {
	
	private ResultSetMetaData rsmd;
	private int columnNumber;
	private int rowNumber;
	

	public DataBaseOperations(){

	}
	
	public ResultSet runQuery(String queryString) {
		try {
			try {
				connection = getConnection();
			} catch (IOException e) {
				logger.handleError("Exception caught while connecting to the database", e);
			}
			ResultSet result = execute(connection, queryString);
			return result;

		} catch (SQLException e) {
			logger.handleError("Error in executing query : ", e, queryString);
			}
			return null;
		
	}
	
	
	
	public boolean checkExists(String tableName) {
		boolean result = false;
		try {
			connection = getConnection();
		} catch (IOException e1) {
			logger.handleError("Exception caught while connecting to the database", e1);
		}
		try {

			DatabaseMetaData d = connection.getMetaData();
			ResultSet resultSet = d.getTables(null, null, "%", null);

			while (resultSet.next()) {
				String table = resultSet.getString(1);
				if (table.equals(tableName)) {
					result = true;
				}
			}
			resultSet.close();
			return result;
		} catch (Exception e) {
			logger.handleError("Exception caught while accessing database for" + tableName, e);
			return result;
		}

	}


	public void disconnect() {
		if (connection == null)
			return;

		try {
			logger.trace("disconnecting");
			connection.close();
		} catch (SQLException e) {
			logger.handleError("Database is already disconnected : ", e);
		} finally {
			connection = null;
		}
	}
	

	private ResultSet execute(Connection connection, String queryString) throws SQLException {
		PreparedStatement preStmt = null;
		ResultSet resultSet = null;
		try{
		preStmt = connection.prepareStatement(queryString);

		resultSet = preStmt.executeQuery();

		return resultSet;
		}catch(SQLException e){
			logger.handleError("Query Execution error : ", e);
		}finally{
			if(preStmt != null){
			preStmt.close();
			}
		}
			return null;
	}

	public Connection getConnection() throws IOException 
	{
			Properties prop = new Properties();
			InputStream input = new FileInputStream("Resources/dbAccess.properties"); 
			prop.load(input);
			String dbUser=prop.getProperty("username");
			String dbPass=prop.getProperty("password");
			String dbURL=prop.getProperty("URL");
			String dbPort=prop.getProperty("port");
			String dbName=prop.getProperty("DBName");
			String className=prop.getProperty("className");
			try {
					Class.forName(className);
					disconnect();
					connection = DriverManager.getConnection(dbURL+dbPort+"/"+dbName, dbUser, dbPass);
					return connection;
				} catch (Exception e) {
					logger.handleError("Error in connecting to the database " + dbName + " for user : " + dbUser+
							" and password : "+ dbPass, e);
					return null;
				}
					
		}

	public int getColumnCount(String q) {
			try 
			{
			ResultSet resultSet	= runQuery(q);
			rsmd = resultSet.getMetaData();
			columnNumber = rsmd.getColumnCount();
			} catch (SQLException e) 
			{
				logger.handleError("Error in getting column count :" , e.getMessage());
			}
			return columnNumber;
			
		}
		
	public int getRowCount(String q) {
		ResultSet resultSet	= runQuery(q);
		  if (resultSet == null) {
		        return 0;
		    }
		    try {
		        resultSet.last();
		        return resultSet.getRow();
		    } catch (SQLException exp) {
		    	logger.handleError("Error in row query execution :" , exp.getMessage());
		    } finally {
		        try {
		            resultSet.beforeFirst();
		        } catch (SQLException exp) {
		        	logger.handleError("Error in row query execution :" , exp.getMessage());
		        }
		    }
		    return 0;
		}
		
		/*
		 * 
		 * executes all data in table
		 * 
		 */
	
		public void getAllData(String q) {
			List<String> data = new ArrayList<String>();
			try {
				ResultSet rs = runQuery(q);
				columnNumber = getColumnCount(q);
				rowNumber = getRowCount(q);
				while(rs.next())
				{
					for(int i=1; i<= columnNumber; i++)
					{
						data.add(rs.getString(i));
					}
				}
				System.out.println("\t   "+rsmd.getColumnName(1)+"   "+rsmd.getColumnName(2)+"\t\t   "+rsmd.getColumnName(3));
				System.out.println("\t   ......................................................");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				logger.handleError("Error in row query execution :" , e.getMessage());
			}
			
			
			System.out.println("Total Columns :"+columnNumber);
			System.out.println("Total Rows :"+rowNumber);
			

		}

		public boolean compareRowCount(String elementName, String sqlQuery) {
			int rowCount1=45;
			
			int rowCount2= getRowCount(sqlQuery);//Returns row count from DB
			
			if (rowCount1==rowCount2) {
					return true;
			}else
			{
				return false;
			}
				
		}
	
		
			
//fetching total row wise data| if row = 3 and column = 4 then total fields will be =3*4 = 12
			
		
/**
* remember after adding data to list, now
* data.get(0) = will return (1st Row)-1st Column 
* data.get(1) = will return (1st Row)-2nd Column
* will print row wise from starting to end row 
*/

		private Connection connection;
		private LogUtils logger = new LogUtils(this);

	}



