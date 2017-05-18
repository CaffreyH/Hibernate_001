//测试类
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.jdbc.Work;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
public class StudentsTest {

	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	@Before
	public void init()
	{
		//创建配置对象
		Configuration config = new Configuration().configure();
		//创建服务注册对象
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//创建会话工厂对象
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//创建会话
		session = sessionFactory.openSession();
		//开启事务
		transaction = session.beginTransaction();
	}
	@After
	public void destory()
	{
		System.out.println("destoy");
		transaction.commit();
		session.close();
		sessionFactory.close();
		System.out.println("销毁");
	}
	@Test
	public void testSaveStudents()
	{
		//生成学生对象
		students s = new students(2,"兔兔",new Date(),"订单");
		System.out.println("建表");
		session.save(s);//保存对象到数据库
		/*System.out.println("完成");
		//不使用事务的方式
		session.doWork(new Work(){

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				connection.setAutoCommit(true);
			}
		}
		);
		session.flush();//---------此时需要 flush
		session.save(s);//保存对象到数据库
*/	}
}
