package net.grocerypricebook.model.dbmanagers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import net.grocerypricebook.model.JDBCUtilities;
import net.grocerypricebook.model.Unit;

/**
 * This class manages queries for the table "units"
 * @author mike
 */
public class UnitManager {

	/**
	 * Returns all available units from the units table.
	 * @return
	 * @throws SQLException 
	 */
	public ArrayList<Unit> getUnits() throws SQLException{
		Connection con = JDBCUtilities.getConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT id FROM units";
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Unit> units = new ArrayList<Unit>();
		while(rs.next()){
			units.add(getUnit(rs.getInt(1)));
		}
		con.close();
		return units;
	}

	/**
	 * Returns a single Unit object representing the corresponding entry
	 * in the units table.
	 * @return 
	 */
	public Unit getUnit(int unitId) throws SQLException{
		Connection con = JDBCUtilities.getConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT id,name,base_unit_id,multiplier FROM units WHERE id = " + unitId;
		ResultSet rs = stmt.executeQuery(query);
		rs.next();
		Unit unit = new Unit();
		unit.setId(unitId);
		unit.setName(rs.getString("name"));
		unit.setBaseUnitId(rs.getInt("base_unit_id"));
		unit.setMultiplier(rs.getFloat("multiplier"));
		con.close();
		return unit;
	}
	
}
