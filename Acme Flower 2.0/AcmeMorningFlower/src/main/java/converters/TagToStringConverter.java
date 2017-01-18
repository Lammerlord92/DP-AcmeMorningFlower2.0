package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.Tag;
@Component
@Transactional
public class TagToStringConverter implements Converter<Tag, String>{
	@Override
	public String convert(Tag source) {
		String result;

		if (source == null)
			result = null;
		else
			result = String.valueOf(source.getId());

		return result;
	}
}
