package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.AdditionalCostRepository;

import domain.AdditionalCost;

@Component
@Transactional
public class StringToAdditionalCostConverter implements Converter<String, AdditionalCost> {
	@Autowired
	private AdditionalCostRepository AdditionalCostRepository;

	@Override
	public AdditionalCost convert(String text) {
		AdditionalCost result;
		int id;
		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = AdditionalCostRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
