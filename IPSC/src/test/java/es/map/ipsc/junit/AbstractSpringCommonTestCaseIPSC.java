package es.map.ipsc.junit;

import org.springframework.test.context.ContextConfiguration;

import es.map.ips.junit.AbstractSpringCommonTestCase;

/**
 * El Class AbstractSpringCommonTestCaseIPSC.
 */
@ContextConfiguration(locations={
        "classpath:applicationContext-managers.xml",
        "classpath:applicationContext-properties.xml", 
        "classpath:applicationContext-db-test.xml",
        "classpath:spring-forward-config.xml"
})
public class AbstractSpringCommonTestCaseIPSC extends AbstractSpringCommonTestCase  {
	
}

