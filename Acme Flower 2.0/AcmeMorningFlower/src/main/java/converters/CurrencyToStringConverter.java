package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Currency;
@Component
@Transactional
public class CurrencyToStringConverter implements Converter<Currency, String>{
	@Override
	public String convert(Currency source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());

		return result;
	}
}
