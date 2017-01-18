package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Doughnut;
@Component
@Transactional
public class DoughnutToStringConverter implements Converter<Doughnut, String>{
	@Override
	public String convert(Doughnut source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());

		return result;
	}
}
