package serviceImpl;

import java.util.ArrayList;  
import java.util.Collections;  
import java.util.Comparator;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
import java.util.Map.Entry;  
import java.util.Set;

import cn.edu.bjtu.weibo.model.*;



@Service

public class RecommendUsersServiceImpl implements RecommendUsersService{

	//�����û���Эͬ�����Ƽ��㷨
	
	public List<User> user;
	
	public List<String> recommand_id;
	
	/*public UserDaoImpl Daoimp;
	
	
	public RecommendUsersServiceImpl(){
		
		user = new ArrayList<User>();
		recommand_id = new ArrayList<String>(); 
		Daoimp = new UserDaoImpl();
	}
	
	public  void getFriendsSimilarity(String userId, int pageIndex, int numberPerPage ){
		
		List<String> Myfriends = Daoimp.getFollowers(userId,pageIndex,numberPerPage);  //����һ��UserID��List
		Map<String, Double> temp=new HashMap<String, Double>();
		
		if(!Myfriends.isEmpty())
		{	
		
		for(String f:Myfriends){
			
			//����ֵ
			int i=100;
			
			List<String> Hisfriends = Daoimp.getFollowers(f,pageIndex,numberPerPage); 
			
			//�������ֵ
			for (String m : Hisfriends) {//����Hisfriends
	            if (Myfriends.contains(m)&&m==userId) //�����������û�
	                i-=3;
	            else if(Myfriends.contains(m)&&m!=userId)
	            	i-=1;
	        }
			
			double sim = getEuclidDistance(i);
			
			temp.put(f, sim);
		}
		
		  Set<Entry<String, Double>> ks = temp.entrySet();
	      List<Entry<String, Double>> lm = new ArrayList<Map.Entry<String, Double>>(
	                ks);
		
		// ����  
		Collections.sort(lm, new Comparator<Entry<String, Double>>() {  
			
			@Override
			public int compare(Entry<String, Double> o1,
					Entry<String, Double> o2) {
				// TODO Auto-generated method stub
				return (int) (o1.getValue()-o2.getValue());
			}  
		});  
		
		//�Ƽ��û�
		while(true){
			int j=0;
			Entry<String, Double> rec=lm.get(j);
			
			List<String> notexist = Daoimp.getFollowers(rec.getKey(),pageIndex,numberPerPage);
			
			for(String t:notexist){
				if(!Myfriends.contains(t)&&t!=userId)
					recommand_id.add(t);
			}
			
			if(!user.isEmpty())
				break;
			else if(j==lm.size()-1)
				break;
			
			j++;
		 }
		}
	}
	
	
	//��ȡ�����û�֮���ŷ����þ���,����ԽСԽ�� 
	
	public static double getEuclidDistance(int temp) {  
		 double a = Math.pow(temp, 2);  
		 double totalscore = Math.abs(a);  
	     
	     return Math.sqrt(totalscore);  
	}  
	*/
  
	@Override
	public List<User> getRecommendUserList(String userId, int pageIndex,
			int numberPerPage) {
		// TODO Auto-generated method stub
		
		/*getFriendsSimilarity(userId,pageIndex,numberPerPage);
		
		if(recommand_id.isEmpty())
			return null;
		
		for(String id : recommand_id){
			
			User re = new User();
			
			//�����û�����
			re.setName(Daoimp.getUserName(id));
			
			re.setLocation(Daoimp.getLocation(id));
			
			re.setBirthday(Daoimp.getBirthday(id));
			
			re.setSex(Daoimp.getSex(id));
			
			re.setAge(Daoimp.getUserAge(id));
			
			re.setEducation(Daoimp.getUserEducation(id));
			
			re.setPhone(Daoimp.getUserPhoneNumber(id));
			
			re.setQq(Daoimp.getUserQQ(id));
			
			re.setLastWeiboId(Daoimp.getLastWeiboId(id));
			
			
			user.add(re);
		}
		
		return user;*/
		return null;
	}
}
