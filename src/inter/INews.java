package inter;
import java.util.List;

import entity.News;
/**
 * 新闻接口
 * @author Administrator
 *
 */
public interface INews {
	/**
	 * 分页查询所有新闻
	 * @param pageNo 当前页号
	 * @param pageSize 当前页号显示数据的条数
	 * @return
	 */
	List<News> getAllNewsBypage(int pageNo,int pageSize);
	/**
	 * 获取新闻数据总条数
	 * @return
	 */
	int getCount();
	/**
	 * 查询所有新闻
	 * @return
	 */
	List<News> getAllNews();
	/**
	 * 修改新闻
	 * @param n 新闻对象
	 * @return
	 */
	int UpdateNews(News n);
	/**
	 * 删除新闻
	 * @param en_id 新闻ID
	 * @return
	 */
	int DeleteNews(int en_id);
	/**
	 * 添加新闻
	 * @param n 新闻对象
	 * @return
	 */
	int InsertNews(News n);
	
	/**
	 * 根据新闻ID获取一条详细的新闻信息
	 * @param en_id 新闻ID
	 * @return
	 */
	News getOneNewsByID(int en_id);

}
