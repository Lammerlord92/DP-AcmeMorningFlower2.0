package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.FlowerOrderRepository;

import domain.FlowerOrder;

@Component
@Transactional
public class StringToFlowerOrderConverter implements Converter<String, FlowerOrder> {
	@Autowired
	private FlowerOrderRepository FlowerOrderRepository;

	@Override
	public FlowerOrder convert(String text) {
		FlowerOrder result;
		int id;
		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = FlowerOrderRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
