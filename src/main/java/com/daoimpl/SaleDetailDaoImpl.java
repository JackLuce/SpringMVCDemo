package com.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.SaleDetailDao;
import com.entity.Goods;
import com.entity.GoodsSaleDetails;
import com.entity.SaleDetail;
import com.util.JDBCUtil;

public class SaleDetailDaoImpl implements SaleDetailDao{

	private  JDBCUtil jdbcUtil = new JDBCUtil();
	private  PreparedStatement stmt=null;
	private  ResultSet rsult = null;
	
	@Override
	public SaleDetail findByID(int id) {
		Connection con = jdbcUtil.load();
		SaleDetail saleDetail = new SaleDetail();
		String sql ="select * from sale_detail where goodsno =?"; 
		try { 
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id); 
			rsult = stmt.executeQuery();
			while (rsult.next()) {
				saleDetail.setSaleNo(rsult.getLong("saleNo"));
				saleDetail.setGoodsNo(rsult.getInt("goodsNo"));
				saleDetail.setNumber(rsult.getInt("number"));
				saleDetail.setSubTotal(rsult.getDouble("subTotal"));
			}
			return saleDetail; 
		} catch (SQLException e) {
			  e.printStackTrace(); 
		  }finally {
			jdbcUtil.close(rsult,stmt, con);
		}
		return null;
	}
	/***
	 * 查询所有
	 */
	public List<SaleDetail> findAll() {
		Connection con = jdbcUtil.load();
		 List<SaleDetail> list = new ArrayList<SaleDetail>();
		 String sql = "select * from sale_detail";
				try {
					stmt = con.prepareStatement(sql);
					rsult = stmt.executeQuery();
					while (rsult.next()) {
						SaleDetail saleDetail = new SaleDetail();
						saleDetail.setSaleNo(rsult.getLong("saleNo"));
						saleDetail.setGoodsNo(rsult.getInt("goodsNo"));
						saleDetail.setNumber(rsult.getInt("number"));
						saleDetail.setSubTotal(rsult.getDouble("subTotal"));
						list.add(saleDetail);
					}
					return list;
				} catch (SQLException e) {
					e.printStackTrace();
				}finally {
					jdbcUtil.close(rsult,stmt, con);
				}
				return null;
	}

	/***
	 * 添加
	 */
	public int insert(List<SaleDetail> saleDetails) {
		Connection con = jdbcUtil.load();
		int num = saleDetails.size();
		int result  = 0;
		int insertNumber = 0;
		String sql = "insert into sale_detail(goodsno,number,subtotal) values(?,?,?)";
		try {
			if(num>0) {
				for (SaleDetail saleDetail : saleDetails) {
				stmt  = con.prepareStatement(sql);
//				stmt.setLong(1, saleDetail.getSaleNo());
				stmt.setInt(1, saleDetail.getGoodsNo());
				stmt.setInt(2, saleDetail.getNumber());
				stmt.setDouble(3, saleDetail.getSubTotal());
				result = stmt.executeUpdate();
				insertNumber = insertNumber + result;
				}
			}
			return insertNumber;
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
	public int update(SaleDetail saleDetail) {
		Connection con = jdbcUtil.load();
		String sql = "update sale_detail set saleNo = ?,number = ?,subTotal = ? where goodsNo = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setLong(1, saleDetail.getSaleNo());
			stmt.setInt(2,saleDetail.getNumber() );
			stmt.setDouble(3, saleDetail.getSubTotal());
			stmt.setInt(4,saleDetail.getGoodsNo() );
			
			int resultNum = stmt.executeUpdate();
			return resultNum;
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
	public int delete(SaleDetail saleDetail) {
		Connection con = jdbcUtil.load();
		String sql = "delete from sale_detail where goodsNo = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1,saleDetail.getGoodsNo());

			int resultNum = stmt.executeUpdate();
			return resultNum;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(stmt, con);
		}
		return 0;
	}


	/**
	 * 查询商品和销售表所有
	 * @return
	 */
	@Override
	public List<GoodsSaleDetails> findAllOf() {
		Connection con = jdbcUtil.load();
		 List<GoodsSaleDetails> goodsSaleDetailsList = new  ArrayList<GoodsSaleDetails>();
		String sql = "select * from goods as g left join sale_detail as s on g.id  = s.goodsno ";
		try {
			stmt = con.prepareStatement(sql);

			ResultSet rsult = stmt.executeQuery(); 
			while (rsult.next()) {
				GoodsSaleDetails goodsSaleDetails = new GoodsSaleDetails();
				SaleDetail saleDetail =new SaleDetail();
				Goods goods = new Goods();
				goods.setId(rsult.getInt("id"));
				goods.setName(rsult.getString("name"));
				goods.setPrice(rsult.getDouble("price"));
				saleDetail.setSaleNo(rsult.getLong("saleNo"));
				saleDetail.setGoodsNo(rsult.getInt("goodsNo"));
				saleDetail.setNumber(rsult.getInt("number"));
				saleDetail.setSubTotal(rsult.getDouble("subTotal"));
				goodsSaleDetails.setGoods(goods);
				goodsSaleDetails.setSaleDetail(saleDetail);
				goodsSaleDetailsList.add(goodsSaleDetails);
			}
			return goodsSaleDetailsList;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(rsult,stmt, con);
		}
		return null;
	}

	/**
	 * 根据name查找销售情况
	 * @return
	 */
	@Override
	public List<GoodsSaleDetails> findGoodsSaleDeatilsByName(String name) {
		Connection con = jdbcUtil.load();
		List<GoodsSaleDetails> goodsSaleDetailsList = new  ArrayList<GoodsSaleDetails>();
		String sql = "select * from goods as g left join sale_detail as s on g.id  = s.goodsno where g.name = ?";
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1,name);
			ResultSet rsult = stmt.executeQuery();
			while (rsult.next()) {
				GoodsSaleDetails goodsSaleDetails = new GoodsSaleDetails();
				SaleDetail saleDetail =new SaleDetail();
				Goods goods = new Goods();
				goods.setId(rsult.getInt("id"));
				goods.setName(rsult.getString("name"));
				goods.setPrice(rsult.getDouble("price"));
				saleDetail.setSaleNo(rsult.getLong("saleNo"));
				saleDetail.setGoodsNo(rsult.getInt("goodsNo"));
				saleDetail.setNumber(rsult.getInt("number"));
				saleDetail.setSubTotal(rsult.getDouble("subTotal"));
				goodsSaleDetails.setGoods(goods);
				goodsSaleDetails.setSaleDetail(saleDetail);
				goodsSaleDetailsList.add(goodsSaleDetails);
			}
			return goodsSaleDetailsList;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(rsult,stmt, con);
		}
		return null;
	}

}
