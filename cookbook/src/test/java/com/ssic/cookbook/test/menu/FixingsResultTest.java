package com.ssic.cookbook.test.menu;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.ssic.cookbook.base.BaseTestCase;
import com.ssic.cookbook.base.BasicWebTest;
import com.ssic.cookbook.manager.service.IFixingsResultService;
public class FixingsResultTest extends BaseTestCase{

	@Autowired
	private IFixingsResultService fixingsResultService;

	protected static final Log logger = LogFactory
			.getLog(FixingsResultTest.class);



	@Test
	public void findFixingsResultCount() {
		Integer count = fixingsResultService.findFixingsResultCount();
		logger.info(count+"################");
	}
}
