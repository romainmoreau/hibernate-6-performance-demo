package demo;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
public class HibernateDemoTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(HibernateDemoTest.class);

	@Autowired
	private DemoRepository demoRepository;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void test() {
		var rows = 1_000_000;
		LOGGER.info("Data initialization started");
		IntStream.range(0, rows).mapToObj(Demo::new).forEach(entityManager::persist);
		LOGGER.info("Data initialization done");
		LOGGER.info("Performance test started");
		var start = System.currentTimeMillis();
		demoRepository.findByIdGreaterThanEqualOrderByIdAsc(rows / 2).size();
		LOGGER.info("Performance test done in {} ms", System.currentTimeMillis() - start);
	}
}
