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
import com.entity.GoodsSaleDetailsChart;
import com.entity.SaleDetail;
import com.util.JDBCUtil;

public class GoodsDaoImpl implements GoodsDao{
	
	private  JDBCUtil jdbcUtil = new JDBCUtil();
	private  PreparedStatement stmt=null;
	private  ResultSet rsult = null;
	@Override
	public List<GoodsSaleDetails> findByPrice() {
		Connection con = jdbcUtil.load();
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
			jdbcUtil.close(rsult,stmt, con);
		}
		return null;
	}
	/**
	 * findByName
	 */
	@Override
	public Goods findByName(String name) {
		Connection con = jdbcUtil.load();
			Goods goods = new Goods();
			String sql ="select * from goods where name =?"; 
			try { 
				stmt = con.prepareStatement(sql);
				stmt.setString(1, name); 
				rsult = stmt.executeQuery();
				while (rsult.next()) {
					goods.setId(rsult.getInt("id"));
					goods.setName(rsult.getString("name"));
					goods.setPrice(rsult.getDouble("price"));
				}
				return goods; 
		  } catch (SQLException e) {
		  e.printStackTrace(); 
		  } finally {
				jdbcUtil.close(rsult,stmt, con);
			}
			return null;
	}
	/**
	 * 根据id查找
	 */
	@Override
	  public Goods findByID(int id) {
		Connection con = jdbcUtil.load();
		Goods goods = new Goods();
		String sql ="select * from goods where id =?"; 
		try { 
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id); 
			rsult = stmt.executeQuery();
			while (rsult.next()) {
				goods.setId(rsult.getInt("id"));
				goods.setName(rsult.getString("name"));
				goods.setPrice(rsult.getDouble("price"));
			}
			return goods; 
	  } catch (SQLException e) {
	  e.printStackTrace(); 
	  } finally {
			jdbcUtil.close(rsult,stmt, con);
	  }
		return null;
	  }
	/**
	 * findIDByName
	 * @param name
	 * @return
	 */
	@Override
	public Goods findIDByName(String name) {
		Connection con = jdbcUtil.load();
		Goods goods = new Goods();
		String sql ="select * from goods where name =?";
		try { 
			stmt = con.prepareStatement(sql);
			stmt.setString(1, name);
			rsult = stmt.executeQuery();
			while (rsult.next()) {
				goods.setId(rsult.getInt("id"));
				goods.setName(rsult.getString("name"));
				goods.setPrice(rsult.getDouble("price"));
			}
			return goods; 
	  } catch (SQLException e) {
	  e.printStackTrace(); 
	  } finally {
			jdbcUtil.close(rsult,stmt, con);
		}
		return null;
	}
	/**
	 * 查询所有
	 */
	public List<Goods> findAll() {
		Connection con = jdbcUtil.load();
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
		}finally {
			jdbcUtil.close(rsult,stmt, con);
		}
		return null;
	}

	/**
	 * 添加
	 */
	public int insert(List<Goods> goodses) {
		Connection con = jdbcUtil.load();
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
			jdbcUtil.close(stmt, con);
		}
		return 0;
	}

	/**
	 * 更新
	 */
	public int update(Goods goods) {
		Connection con = jdbcUtil.load();
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
			jdbcUtil.close(stmt, con);
		}
		return 0;
	}

	/**
	 * 删除
	 */
	public int delete(Goods goods) {
		Connection con = jdbcUtil.load();
		String sql = "delete from goods where id = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, goods.getId());

			int result = stmt.executeUpdate();
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(stmt, con);
		}
		return 0;
	}

	@Override
	public List<GoodsSaleDetailsChart> findGoodsSaledetailsChart() {
		Connection con = jdbcUtil.load();
		List<GoodsSaleDetailsChart> list =new ArrayList<GoodsSaleDetailsChart>();
		String sql = "select g.`name` as name , SUM(s.number) as sumnumber,SUM(s.subtotal) as sumtotal from sale_detail as s LEFT JOIN goods as g \n" +
				"ON g.id = s.goodsno GROUP BY g.`name`";
		try {
			stmt = con.prepareStatement(sql);
			rsult = stmt.executeQuery();
			while (rsult.next()) {
				GoodsSaleDetailsChart saleDetailsChart = new GoodsSaleDetailsChart();
				saleDetailsChart.setName(rsult.getString("name"));
				saleDetailsChart.setSumNumber(rsult.getLong("sumnumber"));
				saleDetailsChart.setSumSubTotal(rsult.getLong("sumtotal"));
				list.add(saleDetailsChart);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(rsult,stmt, con);
		}
		return null;
	}


}
