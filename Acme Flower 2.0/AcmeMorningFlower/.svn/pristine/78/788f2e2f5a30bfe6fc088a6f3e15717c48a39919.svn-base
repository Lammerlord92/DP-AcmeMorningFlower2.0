package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Flower;
@Component
@Transactional
public class FlowerToStringConverter implements Converter<Flower, String>{
	@Override
	public String convert(Flower source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());

		return result;
	}
}
