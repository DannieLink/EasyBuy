package inter;
import java.util.ArrayList;

import entity.ProductType;
/**
 * 商品类别接口
 * @author pc
 *
 */
public interface IProductType {
	/**
	 * 查询所有商品类别
	 * @return
	 */
	ArrayList<ProductType> getAllProductType();
	/**
	 * 根据商品类别id删除删除商品类别
	 * @param id
	 * @return 受影响的行数
	 */
	int delProductTypeById(int id);
	/**
	 * 修改商品类别
	 * @return 受影响的行数
	 */
	int updateProductType(ProductType pt);
	/**
	 * 添加商品类别
	 * @return 受影响的行数
	 */
	int addProductType(ProductType pt);
	
	/**
	 * 总商品类别分页
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public ArrayList<ProductType> getAllProductType(int pageNo, int pageSize);
	/**
	 * 获得商品类别的总数量
	 */
	int getCount();
	
	/**
	 * 根据商品类别名称判断该商品类别是否存在
	 * @param epcname
	 * @return
	 */
	public ProductType getType(String epcname);
	
	/**
	 * 修改
	 * @param epcname
	 * @return
	 */
	public ProductType getType(int epcid, String epcname) ;
	
	/**
	 * 根据商品类别获取商品类别对象
	 * @param epcid
	 * @return
	 */
	public ProductType getType(int epcid);

}
