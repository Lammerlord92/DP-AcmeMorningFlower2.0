package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.DoughnutRepository;

import domain.Doughnut;

@Component
@Transactional
public class StringToDoughnutConverter implements Converter<String, Doughnut> {
	@Autowired
	private DoughnutRepository doughnutRepository;

	@Override
	public Doughnut convert(String text) {
		Doughnut result;
		int id;
		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = doughnutRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
