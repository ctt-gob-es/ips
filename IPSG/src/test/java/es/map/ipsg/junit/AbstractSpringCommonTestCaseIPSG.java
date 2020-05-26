package es.map.ipsg.junit;

import org.springframework.test.context.ContextConfiguration;

import es.map.ips.junit.AbstractSpringCommonTestCase;

/**
 * El Class AbstractSpringCommonTestCaseIPSG.
 */
@ContextConfiguration(locations={
        "classpath:applicationContext-managers.xml",
        "classpath:applicationContext-properties.xml", 
        "classpath:applicationContext-db-test.xml",
        "classpath:spring-forward-config.xml"
})
public class AbstractSpringCommonTestCaseIPSG extends AbstractSpringCommonTestCase  {
	
}

