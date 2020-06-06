package com.chinasoft.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.chinasoft.mapper.UserMapper;
import com.chinasoft.pojo.User;
import com.chinasoft.util.SessionUtil;

public class UserMapperTest {
	public SqlSessionFactory factory = SessionUtil.getSession();

	public void addUserNoPar() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		mapper.addUserNoPar();
		session.commit();
		session.close();
	}

	public void addUserByPar() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = new User(25, "黎明", "liming111", "6666666", "台湾新竹", "723872387@qq.com", 1);
		mapper.addUserByPar(user);
		session.commit();
		session.close();
	}

	@Test
	public void selectOneByPar() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.selectOneByPar(5);// 第一次执行sql语句
		System.out.println(user);

		// session.clearCache();// 清除一级缓存

		User user1 = mapper.selectOneByPar(5);// 第二次执行sql语句
		System.out.println(user1);

		session.close();

		System.out.println(user == user1);
	}

	@Test
	public void selectOneByParCache() {

		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = mapper.selectOneByPar(5);// 第一次执行sql语句 System.out.println(user);
		System.out.println(user);
		session.close();

		SqlSession session1 = factory.openSession();
		UserMapper mapper1 = session1.getMapper(UserMapper.class);
		User user1 = mapper1.selectOneByPar(5);// 第一次执行sql语句
		System.out.println(user1);
		session1.close();

		System.out.println(user == user1);

	}

	public void selectAll() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<User> list = mapper.selectAll();
		for (User user : list) {
			System.out.println(user);
		}
		session.close();
	}

	@Test
	public void selectLogin() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername("lol");
		user.setPassword("123456");
		User user1 = mapper.selectLogin(user);
		System.out.println(user1);
		session.close();
	}

	@Test
	public void selectOrderBy() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<User> users = mapper.selectOrderBy("username");
		for (User user : users) {
			System.out.println(user);
		}
		session.close();
	}

	// 测试动态sql——if
	@Test
	public void findUserWith_if() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = new User();
		user.setUsername("lol");
		user.setPassword("123456");
		User user1 = mapper.findUserWith_if(user);
		System.out.println(user1);
		session.close();
	}

	//测试动态dql_foreach
	@Test
	public void findUsersWithList() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(3);
		list.add(6);
		List<User> users = mapper.findUsersWithList(list);
		for (User user : users) {
			System.out.println(user);
		}
		session.close();
	}
	
	@Test
	public void addUserWithList() {
		SqlSession session = factory.openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user1 = new User();
		user1.setId(6);
		user1.setUsername("lolwe");
		
		User user2 = new User();
		user2.setId(7);
		user2.setUsername("lqweol");
		
		List<User> list = new ArrayList<>();
		list.add(user1);
		list.add(user2);
		List<User> users = mapper.addUserWithList(list);
		System.out.println(user1);
		session.close();
		//User usera = new User(6, "明", "ling111", "64896", "广东", "10000@qq.com", 1);
		//User userb = new User(7, "明", "limng111", "6265666", "深圳", "14548@qq.com", 1);
	
	
	
	}
	
	
	public static void main(String[] args) {
		// new UserMapperTest().addUserNoPar();
		// new UserMapperTest().addUserByPar();
		// new UserMapperTest().selectOneByPar();
		// new UserMapperTest().selectOneByPar();
		//new UserMapperTest().selectAll();
		new UserMapperTest().addUserWithList();
	}

}
