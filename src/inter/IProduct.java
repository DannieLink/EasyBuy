package inter;
import java.util.ArrayList;

import entity.Product;

/**
 * 商品接口
 * @author pc
 *
 */
public interface IProduct {
	/**
	 * 获得所有商品
	 * @return
	 */
	ArrayList<Product> getAllProduct();
	/**
	 * 分页显示指定商品类型下的商品数据
	 * @param pageNo
	 * @param pageSize
	 * @param epcId
	 * @return
	 */
	ArrayList<Product> getAllProduct(int pageNo, int pageSize,int epcId);
	
	/**
	 *分页显示所有商品数据
	 * @param pageNo
	 * @param pageSize
	 * @param epcId
	 * @return
	 */
	ArrayList<Product> getAllProduct(int pageNo, int pageSize);
	
	

	/**
	 * 根据商品类别Id获得商品
	 * @param pro
	 * @return 一件商品
	 */
	ArrayList<Product> getAllProduct(int epc_id);
	
	/**
	 * 根据商品Id获得商品
	 * @param pro
	 * @return 一件商品
	 */
	Product getProductById(int id);
	/**
	 * 添加商品
	 * @param pro
	 * @return 数据库受影响的行数
	 */
	int addProduct(Product pro);
	/**
	 * 根据商品Id修改商品
	 * @param pro
	 * @return 数据库受影响的行数
	 */
	int updateProductById(Product pro);
	/**
	 * 根据商品Id删除商品
	 * @param pro
	 * @return 数据库受影响的行数
	 */
	int selectProductById(int id);
	
	/**
	 * 无参epcId时，查询所有商品总行数
	 * 有参数epcId时，根据商品类型ID查询指定商品总量
	 * @param epcId
	 * @return
	 */
	int getCount(int epcId);
	
	/**
	 * 更新库存
	 * @param epid
	 * @param stock
	 * @return 库存
	 */
	public int getProductNewStock(int epid,int stock);

}
