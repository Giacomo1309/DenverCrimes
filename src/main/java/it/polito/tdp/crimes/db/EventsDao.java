package it.polito.tdp.crimes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.crimes.model.Event;
import it.polito.tdp.crimes.model.Reato;
import it.polito.tdp.crimes.model.categoriaIncidente;

public class EventsDao {

	public List<Event> listAllEvents() {
		String sql = "SELECT * FROM events";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);

			List<Event> list = new ArrayList<>();

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					list.add(new Event(res.getLong("incident_id"), res.getInt("offense_code"),
							res.getInt("offense_code_extension"), res.getString("offense_type_id"),
							res.getString("offense_category_id"), res.getTimestamp("reported_date").toLocalDateTime(),
							res.getString("incident_address"), res.getDouble("geo_lon"), res.getDouble("geo_lat"),
							res.getInt("district_id"), res.getInt("precinct_id"), res.getString("neighborhood_id"),
							res.getInt("is_crime"), res.getInt("is_traffic")));
				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();
			return list;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<categoriaIncidente> get(Map<Integer, Reato> idMap, String macroCategoria, int mese) {
		String sql = "SELECT neighborhood_id ,  offense_code, offense_type_id, incident_id " + "FROM EVENTS "
				+ "WHERE offense_category_id = ? AND MONTH(reported_date)= ? ";
		List<categoriaIncidente> list = new ArrayList<categoriaIncidente>();
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, macroCategoria);
			st.setInt(2, mese);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					Integer i = res.getInt("offense_code");
					String s = res.getString("offense_type_id");
					Reato r = idMap.get(i);
					String quartiere = res.getString("neighborhood_id");
					int incidente = res.getInt("incident_id");

					categoriaIncidente c = new categoriaIncidente(r, incidente, quartiere);
					list.add(c);

				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return list;
	}

	public void riempioIdMap(Map<Integer, Reato> idMap, String macroCategoria, int mese) {
		String sql = "SELECT offense_code, offense_type_id " + "FROM EVENTS "
				+ "WHERE offense_category_id = ? AND MONTH(reported_date)= ? " + "GROUP BY offense_code ";
		try {
			Connection conn = DBConnect.getConnection();

			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, macroCategoria);
			st.setInt(2, mese);

			ResultSet res = st.executeQuery();

			while (res.next()) {
				try {
					Integer i = res.getInt("offense_code");
					String s = res.getString("offense_type_id");
					if (!idMap.containsKey(i)) {

						Reato r = new Reato(s, i);
						idMap.put(i, r);
					}

				} catch (Throwable t) {
					t.printStackTrace();
					System.out.println(res.getInt("id"));
				}
			}

			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
}
