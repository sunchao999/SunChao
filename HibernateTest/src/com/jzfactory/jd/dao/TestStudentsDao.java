package com.jzfactory.jd.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.jzfactory.jd.bean.Master;
import com.jzfactory.jd.util.BaseHibernateDAO;

/**
 * ����Hibernate����״̬
 * 
 * @author �ﳬ
 *
 */
public class TestStudentsDao extends BaseHibernateDAO {
	/**
	 * ������ʱ״̬���־�״̬��ת��
	 */
	public void testT2P() {
		Master master = new Master();
		// ��ʱ̫
		master.setName("dema");
		master.setSex(1);
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		session.save(master);
		trans.commit();
		// �־�̫
		master.setSex(0);
		session.close();
		// ����̬
		master.setName("assasa");
		// ��ʱ̫
		master.setId(10000);
	}

	/**
	 * ������ʱ״̬���־�״̬�󣬸��������ύ��
	 */
	public void testT2P2Update() {
		Master master = new Master();
		master.setName("dema");
		master.setSex(0);
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		session.save(master);
		session.save(master);
		master.setSex(0);
		trans.commit();
		session.close();
	}

	/**
	 * ���Գ־�״̬�޸ĺ��ύ
	 */
	public void testP2Update() {
		Session session = getSession();
		Master master = (Master) session.get(Master.class, 1);
		Transaction trans = session.beginTransaction();
		session.save(master);// �ñ���������
		master.setSex(0);
		master.setName("lll");
		trans.commit();
		session.close();

	}

	/**
	 * ���־�̬ת��Ϊ����̬������ύ clear �� evict �� session�ر�
	 */
	public void testP2D2Update() {
		Session session = getSession();
		//�־û�״̬
		Master master = (Master) session.get(Master.class, 1);
		session.evict(master);//���־ö���ת��Ϊ�������
		Transaction trans = session.beginTransaction();
		master.setName("һ��");//����̬����
		trans.commit();
		session.close();
	}

	/**
	 * ���־�̬ת��Ϊ��ʱ̬
	 */
	public void testP2T() {
		Session session = getSession();
		Master master = (Master) session.get(Master.class,2);
		Transaction trans = session.beginTransaction();
		session.delete(master);
	    master.setName("lu");	
		trans.commit();
		session.close();
	}

	/**
	 * ͬ���־û�����
	 */
	public void testRefresh() {
		Session session = getSession();
		Master master = (Master) session.get(Master.class,3);
		System.out.println("before:"+master.getName());
		Transaction trans = session.beginTransaction();
		trans.commit();
		session.refresh(master);	
		System.out.println("after:"+master.getName());
		session.close();
	}

	/**
	 * ������̬ת���ɳ־�̬
	 */
	public void testD2P() {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		//��ʱ̫
		Master master = new Master();
		//����̬
		master.setId(3);
		master.setName("lloo");
		master.setSex(0);
		session.update(master);
		//�־�̫
		String name=master.getName();
		trans.commit();
	}

	/**
	 * �־�״̬�޸�id(����)
	 */
	public void testP2EditId() {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		Master master = (Master) session.get(Master.class,3);
		master.setId(100);
		trans.commit();
		session.close();
	}

	/**
	 * ����̬ת��Ϊ��ʱ״̬
	 */
	public void testD2S() {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		//��ʱ̬
		Master master = new Master();
		//����̬
        master.setId(3);
        session.delete(master);
        trans.commit();
        session.close();
	}

	/**
	 * �����ظ��ĳ־û����� (����)
	 */
	public void testDuplicateP() {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		//�־�̫
		Master master = (Master) session.get(Master.class,4);
		//��ʱ̬
		Master master2 = new Master();
		master2.setName("����");
		session.update(master2);
		System.out.println(master2);
		trans.commit();
		session.close();
	}

	/**
	 * �����ظ��ĳ־û�����
	 */
	public void testRemoveDupli() {
		Session session = getSession();
		Transaction trans = session.beginTransaction();
		//�־�̫
		Master master = (Master) session.get(Master.class,4);
		//��ʱ̬
		Master master2 = new Master();
		master2.setId(4);
		master2.setName("����");
		session.merge(master2);	
		trans.commit();
		System.out.println(master);
		System.out.println(master2);
		session.close();
	}

	public static void main(String[] args) {
		TestStudentsDao tsd = new TestStudentsDao();
		tsd.testRemoveDupli();
	}
}