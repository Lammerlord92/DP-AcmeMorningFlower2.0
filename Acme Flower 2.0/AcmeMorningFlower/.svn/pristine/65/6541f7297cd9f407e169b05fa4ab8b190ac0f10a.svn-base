package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.FlowerOrder;
@Component
@Transactional
public class FlowerOrderToStringConverter implements Converter<FlowerOrder, String>{
	@Override
	public String convert(FlowerOrder source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());

		return result;
	}
}
