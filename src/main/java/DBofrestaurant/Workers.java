
package DBofrestaurant;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @angelika
 */
public class Workers {
        private static final String DB_NAME = "sqlite";
	private static final String DB_FILE_NAME = "restaurant.db";
	private static final String DB = "org.sqlite.JDBC";

	private static final String TABLE_NAME = "WORKERS";

	public Workers() {
		initialization();
	}

	/**
	 * 
	 * @return
	 */
	public Iterable<Worker> getWorkers() {
		Connection connection = getConnection();
		Statement st = null;
		try {
			st = connection.createStatement();
			ResultSet resultSet = st
					.executeQuery("SELECT * FROM " + TABLE_NAME);

			List<Worker> result = convertResultSetToWorker(resultSet);

			connection.close();

			return result;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		return Collections.emptyList();
	}
        private List<Worker> convertResultSetToWorker(ResultSet rs)
			throws SQLException {
		List<Worker> list = new ArrayList<Worker>();

		while (rs.next()) {
			String name = rs.getObject(2)+"";
			String surname = rs.getObject(3)+"";
			int experiense = Integer.parseInt(rs.getObject(4)+"");
			String post = rs.getObject(5)+"";
			double salary = Double.parseDouble(rs.getObject(6)+"");
			Worker worker = new Worker(name, surname, experiense, post, salary);
			list.add(worker);
		}

		return list;
	}

	/**
	 * 
	 * @return
	 */
	private Connection getConnection() {
		try {
			Class.forName(DB);
			Connection connection = DriverManager.getConnection(String.format(
					"jdbc:%s:%s", DB_NAME, DB_FILE_NAME));
			return connection;
		} catch (ClassNotFoundException e) {
			System.err.println(DB);
			System.err.println(String.format("jdbc:%s:%s", DB_NAME,
					DB_FILE_NAME));
                        e.printStackTrace();
                        return null;
		}catch (SQLException e1)
                {
                    e1.printStackTrace();;
                    return null;
                }
	}

	//
	private void initialization() {
		try {
			Connection con = getConnection();
			PreparedStatement work = con
					.prepareStatement("create table if not exists 'WORKERS' ('Id' INTEGER PRIMARY KEY UNIQUE, 'NAME' text UNIQUE, 'SURNAME' text UNIQUE, 'EXPERIENSE' INTEGER, 'POST' text, 'SALARY' REAL);");
			work.executeUpdate();

			work.close();
			con.close();

		} catch (SQLException e) {
			System.out.println("�� ����� SQL �����");
			e.printStackTrace();
		}
	}

    
	public class Worker {
		private String name;
		private String surname;
		private int experiense;
		private String post;
		private double salary;
		
		public Worker(String name, String surname, int experiense, String post,
				double salary) {
			super();
			this.name = name;
			this.surname = surname;
			this.experiense = experiense;
			this.post = post;
			this.salary = salary;
		}

		public int getExperiense() {
			return experiense;
		}

		public void setExperiense(int experiense) {
			this.experiense = experiense;
		}

		public double getSalary() {
			return salary;
		}

		public void setSalary(double salary) {
			this.salary = salary;
		}

		public String getName() {
			return name;
		}

		public String getSurname() {
			return surname;
		}

		public String getPost() {
			return post;
		}
               
                @Override
                public String toString(){
                    return String.format("name = %s , surname = %s , experiense = %s, post = %s, salary = %s ", name,surname,experiense,post,salary);
                }
	}


}
