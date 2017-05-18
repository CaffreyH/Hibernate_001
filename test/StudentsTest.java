//������
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
		//�������ö���
		Configuration config = new Configuration().configure();
		//��������ע�����
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties()).buildServiceRegistry();
		//�����Ự��������
		sessionFactory = config.buildSessionFactory(serviceRegistry);
		//�����Ự
		session = sessionFactory.openSession();
		//��������
		transaction = session.beginTransaction();
	}
	@After
	public void destory()
	{
		System.out.println("destoy");
		transaction.commit();
		session.close();
		sessionFactory.close();
		System.out.println("����");
	}
	@Test
	public void testSaveStudents()
	{
		//����ѧ������
		students s = new students(2,"����",new Date(),"����");
		System.out.println("����");
		session.save(s);//����������ݿ�
		/*System.out.println("���");
		//��ʹ������ķ�ʽ
		session.doWork(new Work(){

			@Override
			public void execute(Connection connection) throws SQLException {
				// TODO Auto-generated method stub
				connection.setAutoCommit(true);
			}
		}
		);
		session.flush();//---------��ʱ��Ҫ flush
		session.save(s);//����������ݿ�
*/	}
}
