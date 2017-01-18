package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.FlowerRepository;

import domain.Flower;

@Component
@Transactional
public class StringToFlowerConverter implements Converter<String, Flower> {
	@Autowired
	private FlowerRepository FlowerRepository;

	@Override
	public Flower convert(String text) {
		Flower result;
		int id;
		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = FlowerRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
