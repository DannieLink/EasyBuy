package inter;

import java.util.List;

import entity.Product;
import entity.Shopping;

/**
 * 购物类接口
 * @author pc
 *
 */
public interface IShopping {
	/**
	 * 获取购买商品信息，显示购物车列表
	 * @return
	 */
	List<Product> getShopList(String euid);
	
	/**
	 * 删除购物车的商品
	 * @param epid 商品Id
	 * @param euid 用户Id
	 * @return
	 */
	int delShopGoods(int epid,String euid);
	
	/**
	 * 添加商品信息到购物车
	 * @param epid 商品id
	 * @param euid 用户id
	 * @return
	 */
	int addShopGoods(int epid,String euid);
	
	/**
	 * 获取用户购买相同商品的数量
	 * @param epid
	 * @param euid
	 * @return
	 */
	int getShopCount(int epid,String euid);
	
	/**
	 * 获取购物车总数量
	 * @param euid
	 * @return
	 */
	int getTotalCount(String euid);
	
	
}
