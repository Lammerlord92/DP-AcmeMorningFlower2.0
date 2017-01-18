package converters;

import javax.transaction.Transactional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.CurrencyRepository;

import domain.Currency;

@Component
@Transactional
public class StringToCurrencyConverter implements Converter<String, Currency> {
	@Autowired
	private CurrencyRepository currencyRepository;

	@Override
	public Currency convert(String text) {
		Currency result;
		int id;
		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = currencyRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
