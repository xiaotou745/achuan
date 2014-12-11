package com.achuan.springsample.component;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class BeanService implements IBeanService {

	private BeanDao beanDao;

	@Override
	public String getUserName(int userId) {
		return beanDao.getUserName(userId);
	}

	public BeanDao getBeanDao() {
		return beanDao;
	}

	@Resource
	public void setBeanDao(BeanDao beanDao) {
		this.beanDao = beanDao;
	}

}
