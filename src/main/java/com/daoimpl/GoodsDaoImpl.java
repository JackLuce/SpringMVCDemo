package com.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.GoodsDao;
import com.entity.Goods;
import com.entity.GoodsSaleDetails;
import com.entity.SaleDetail;
import com.util.JDBCUtil;

public class GoodsDaoImpl implements GoodsDao{
	
	private static JDBCUtil jdbcUtil = new JDBCUtil();
	private static Connection con = jdbcUtil.load();
	private static PreparedStatement stmt=null;
	private static ResultSet rsult = null;
	@Override
	public List<GoodsSaleDetails> findByPrice() {
		List<GoodsSaleDetails> goodsSaleDetails  = new ArrayList<GoodsSaleDetails>();
		
		String sql  = "select g.*,s.* from goods as g left join sale_detail as s on g.id = s.goodsno where g.price >1000";
		try {
			stmt = con.prepareStatement(sql);
			rsult = stmt.executeQuery();
			while (rsult.next()) {
				GoodsSaleDetails  gSaleDetails = new GoodsSaleDetails();
				Goods goods = new Goods();
				SaleDetail saleDetail = new SaleDetail();
					goods.setId(rsult.getInt("id"));
					goods.setName(rsult.getString("name"));
					goods.setPrice(rsult.getDouble("price"));
				 	saleDetail.setGoodsNo(rsult.getInt("goodsno"));
					saleDetail.setNumber(rsult.getInt("number"));
					saleDetail.setSubTotal(rsult.getDouble("subtotal"));
					gSaleDetails.setGoods(goods);
					gSaleDetails.setSaleDetail(saleDetail);
					goodsSaleDetails.add(gSaleDetails);
			}
			return goodsSaleDetails;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		jdbcUtil.close(stmt, con);
		return null;
	}
	/**
	 * findByName
	 */
	@Override
	public Goods findByName(String name) {
			Goods goods = new Goods();
			String sql ="select * from goods where name =?"; 
			try { 
				stmt = con.prepareStatement(sql);
				stmt.setString(1, name); 
				ResultSet rsult = stmt.executeQuery(); 
				while (rsult.next()) {
					goods.setId(rsult.getInt("id"));
					goods.setName(rsult.getString("name"));
					goods.setPrice(rsult.getDouble("price"));
				}
				return goods; 
		  } catch (SQLException e) {
		  e.printStackTrace(); 
		  } finally {
			}
			jdbcUtil.close(stmt, con);
			return null; 
	}
	/**
	 * 根据id查找
	 */
	@Override
	  public Goods findByID(int id) { 
		Goods goods = new Goods();
		String sql ="select * from goods where id =?"; 
		try { 
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id); 
			ResultSet rsult = stmt.executeQuery(); 
			while (rsult.next()) {
				goods.setId(rsult.getInt("id"));
				goods.setName(rsult.getString("name"));
				goods.setPrice(rsult.getDouble("price"));
			}
			return goods; 
	  } catch (SQLException e) {
	  e.printStackTrace(); 
	  } finally {
		}
		jdbcUtil.close(stmt, con);
		return null; 
	  }
	/**
	 * findIDByName
	 */
	@Override
	public Goods findIDByName(String name) {
		Goods goods = new Goods();
		String sql ="select * from goods where name =?";
		try { 
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rsult = stmt.executeQuery(); 
			while (rsult.next()) {
				goods.setId(rsult.getInt("id"));
				goods.setName(rsult.getString("name"));
				goods.setPrice(rsult.getDouble("price"));
			}
			return goods; 
	  } catch (SQLException e) {
	  e.printStackTrace(); 
	  } finally {
		}
		jdbcUtil.close(stmt, con);
		return null; 
	}
	/**
	 * 查询所有
	 */
	public List<Goods> findAll() {
		List<Goods> list =new ArrayList<Goods>();
		String sql = "select * from goods";
		try {
			stmt = con.prepareStatement(sql);
			rsult = stmt.executeQuery();
			while (rsult.next()) {
				Goods goods = new Goods();
					goods.setId(rsult.getInt("id"));
					goods.setName(rsult.getString("name"));
					goods.setPrice(rsult.getDouble("price"));
					list.add(goods);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		jdbcUtil.close(stmt, con);
		return null;
	}

	/**
	 * 添加
	 */
	public int insert(List<Goods> goodses) {
		int num = goodses.size();
		int result  = 0;
		int number  = 0;
		String sql = "insert into goods values(?,?,?)";
		try {
			if(num>0) {
				for (Goods goods : goodses) {
				stmt  = con.prepareStatement(sql);
				stmt.setInt(1, goods.getId());
				stmt.setString(2, goods.getName());
				stmt.setDouble(3, goods.getPrice());
				number = stmt.executeUpdate();
				System.out.println(number+"---->number");
				result = result+number;
				System.out.println(result+"---->result1");
				}
				System.out.println(result+"---->result2");
				return result;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		jdbcUtil.close(stmt, con);
		return 0;
	}

	/**
	 * 更新
	 */
	public int update(Goods goods) {
		String sql = "update goods set name = ?,price = ? where id = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, goods.getName());
			stmt.setDouble(2, goods.getPrice());
			stmt.setInt(3, goods.getId());
			
			int result = stmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		jdbcUtil.close(stmt, con);
		return 0;
	}

	/**
	 * 删除
	 */
	public int delete(Goods goods) {
		String sql = "delete from goods where id = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, goods.getId());

			int result = stmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
		}
		jdbcUtil.close(stmt, con);
		return 0;
	}


}
