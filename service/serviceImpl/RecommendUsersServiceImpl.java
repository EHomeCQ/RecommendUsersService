package cn.edu.bjtu.weibo.service.serviceImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cn.edu.bjtu.weibo.dao.UserDAO;
import cn.edu.bjtu.weibo.dao.daoImpl.UserDaoImpl;
import cn.edu.bjtu.weibo.model.*;
import cn.edu.bjtu.weibo.service.RecommendUsersService;
import org.springframework.aop.*;

@Service("recommendUsersService")

public class RecommendUsersServiceImpl implements RecommendUsersService {

	// 基于用户的协同过滤推荐算法

	public List<User> user;

	public List<String> recommand_id;

	@Autowired
	private UserDAO userDAO;

	private static Map<String, Integer> temp;

	public RecommendUsersServiceImpl() {

		user = new ArrayList<User>();
		recommand_id = new ArrayList<String>();
		//userDAO = new UserDaoImpl();
		temp = new HashMap<String, Integer>();
	}

	public void getFriendsSimilarity(String userId, int pageIndex,
			int numberPerPage) {

		List<String> Myfriends1 = userDAO.getFollowers(userId, pageIndex,
				numberPerPage); // 返回一个UserID的List
		List<String> Myfriends2 = userDAO.getFollowing(userId, pageIndex,
				numberPerPage); // 返回一个UserID的List
		
		int Followers=0;
		int Following=0;
		
		if(Myfriends1.size()>=Myfriends2.size()){
			Followers=1;
			Following=3;
		}
		else{
			Followers=3;
			Following=1;
		}
		
		if (!Myfriends1.isEmpty()||!Myfriends2.isEmpty()) {
			
			

			for (String f : Myfriends1) {

				// 粉丝的权重
				int i = Followers;

				List<String> Followersfriends = userDAO.getFollowing(f, 1, 500);

				// 计算相关值
				
				getChildFollowerWeight(i, Followersfriends);

			}
			
			for (String f : Myfriends2) {

				// 关注用户的权重
				int i = Following;

				List<String> Followingfriends = userDAO.getFollowing(f, 1, 500);

				// 计算相关值
				
				getChildFollowerWeight(i, Followingfriends);

			}

			Set<Entry<String, Integer>> ks = temp.entrySet();
			List<Entry<String, Integer>> lm = new ArrayList<Map.Entry<String, Integer>>(
					ks);

			// 排序
			Collections.sort(lm, new Comparator<Entry<String, Integer>>() {

				@Override
				public int compare(Entry<String, Integer> o1,
						Entry<String, Integer> o2) {
					// TODO Auto-generated method stub
					if(o1.getValue() <o2.getValue())
						return 1;
					
					return -1;
				}
			});

			// 推荐用户
			
			int index=0;//已推荐用户数量
			int j = 0;
			while (true) {
				
				Entry<String, Integer> rec = lm.get(j);

				String ReName = rec.getKey();
				
				if (!Myfriends2.contains(ReName) && !ReName .equals(userId) ){
					recommand_id.add(ReName);
					index++;
				}
				if (index>5)
					break;
				else if (j >= lm.size() - 1)
					break;

				j++;
			}
		}
	}

	// 获取备选推荐用户的兴趣度

	public static void getChildFollowerWeight(int weight,
			List<String> Authorities) {
		
		for(String t : Authorities){
			
			if(temp.containsKey(t)){
				
				Integer m = temp.get(t)+weight*2;
				
				temp.put(t, m);
			}
			else{
				Integer mm = (Integer)weight*2;
				temp.put(t, mm);
			}
		}

	}

	@Override
	public List<User> getRecommendUserList(String userId, int pageIndex,
			int numberPerPage) {
		// TODO Auto-generated method stub

		getFriendsSimilarity(userId, pageIndex, numberPerPage);

		if (recommand_id.isEmpty())
			return null;

		for (String id : recommand_id) {

			User re = new User();

			// 设置用户属性
			re.setName(userDAO.getUserName(id));

			re.setLocation(userDAO.getLocation(id));

			re.setBirthday(userDAO.getBirthday(id));

			re.setSex(userDAO.getSex(id));

			re.setAge(userDAO.getUserAge(id));

			re.setEducation(userDAO.getUserEducation(id));

			re.setPhone(userDAO.getUserPhoneNumber(id));

			re.setQq(userDAO.getUserQQ(id));

			re.setLastWeiboId(userDAO.getLastWeiboId(id));

			user.add(re);
		}

		return user;
	}

	@Override
	public void updateRecommendResult() {
		// TODO Auto-generated method stub
		
	}
}
