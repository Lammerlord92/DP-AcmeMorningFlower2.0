package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.AdditionalCost;
@Component
@Transactional
public class AdditionalCostToStringConverter implements Converter<AdditionalCost, String>{
	@Override
	public String convert(AdditionalCost source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());

		return result;
	}
}
