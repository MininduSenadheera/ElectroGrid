package org.relishnepal.relisnp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.relishnepal.relisnp.beans.State;
import org.relishnepal.relisnp.db.DBType;
import org.relishnepal.relisnp.db.DBUtil;

public class StateManager {
	public static List<State> displayAllRows() throws SQLException {
		List<State> list = new ArrayList<State>();
		String sql = "SELECT noticeId, nmessage, duration,aparty, heading FROM notices";
				try(
						Connection conn = DBUtil.getConnection(DBType.MYSQL);
						Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						ResultSet rs = stmt.executeQuery(sql);
						) {
					while (rs.next()) {
						State state = new State();
						int noticeId = rs.getObject("noticeId", int.class);
						String nmessage = rs.getObject("nmessage", String.class);
						String duration = rs.getObject("duration", String.class);
						String aparty = rs.getObject("aparty", String.class);
						String heading = rs.getObject("heading", String.class);
						
						state.setNoticeId(noticeId);
						state.setNmessage(nmessage);
						state.setDuration(duration);
						state.setAparty(aparty);
						state.setHeading(heading);
						list.add(state);
						
					}
				} catch (SQLException e) {
					DBUtil.processException(e);
				} 
				
				return list;
		
	}
	
	public static State getRow(int noticeId) throws SQLException {
	
		String sql = "SELECT * FROM notices WHERE noticeId = ?";
		ResultSet rs = null;
		
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setInt(1, noticeId);
			rs = stmt.executeQuery();
			
			if (rs.next()){
				State state = new State();
				state.setNoticeId(noticeId);
				state.setNmessage(rs.getObject("nmessage", String.class));
				state.setDuration(rs.getObject("duration", String.class));
				state.setAparty(rs.getObject("aparty", String.class));
				state.setHeading(rs.getObject("heading", String.class));
				return state;
			}else{
				System.err.println("No rows were found.");
				return null;
				
			}		
			}catch(SQLException e){
			System.err.println(e);
			return null;
		}finally{
			if (rs != null){
				rs.close();
			}
		}
	}
	
	
	public static State insert(State state) throws SQLException {
		
		String sql = "INSERT INTO notices(noticeId, nmessage,duration,aparty,heading)" + "VALUES (?,?,?,?,?)";
		ResultSet keys = null;
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sql);
				){		
		
			stmt.setInt(1, state.getNoticeId());
			stmt.setString(2, state.getNmessage());
			stmt.setString(3, state.getDuration());
			stmt.setString(4, state.getAparty());
			stmt.setString(5, state.getHeading());
			stmt.executeUpdate();
			}catch(SQLException e){
			System.err.println(e);
			return null;
		}finally{
			if (keys != null) keys.close();
		}
		return state;
	}
	
	public static State update(State state) throws SQLException {
		String sql = "update notices set nmessage=?,duration=?,aparty=?,heading=? where noticeId=?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setString(1, state.getNmessage());
			
			stmt.setString(2, state.getDuration());
			stmt.setString(3, state.getAparty());
			stmt.setString(4, state.getHeading());
			stmt.setInt(5, state.getNoticeId());
			int affected = stmt.executeUpdate();
			System.out.println(affected);
			if (affected==1){
				return state;
			}else{
				return null;
			}
		}
		catch(SQLException e){
			System.err.println(e);
			return null;
		}
	
	}
	public static void delete(int noticeId) throws SQLException {
		String sql = "DELETE FROM notices WHERE noticeId = ?";
		try(
				Connection conn = DBUtil.getConnection(DBType.MYSQL);
				PreparedStatement stmt = conn.prepareStatement(sql);
				){
			stmt.setInt(1, noticeId);
			int affected = stmt.executeUpdate();
			if (affected == 1){
				System.out.println("Deleted the row.");
			}else{
				System.out.println("Could not perform the delete operation.");
			}
		}
		catch(SQLException e){
			System.err.println(e);
		}
	}
}
