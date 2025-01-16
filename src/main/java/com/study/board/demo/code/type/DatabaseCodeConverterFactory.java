package com.study.board.demo.code.type;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.Arrays;

/**
 * Springboot @RequestParam, @ModelAttribute Enum Binding
 */
public class DatabaseCodeConverterFactory implements ConverterFactory<String, Enum> {

	@Override
	public <T extends Enum> Converter<String, T> getConverter(Class<T> targetType) {
		return new StringToEnumsConverter<>(targetType);
	}

	private static final class StringToEnumsConverter<T extends Enum<? extends DatabaseCode>> implements Converter<String, T> {
		private final Class<T> enumType;
		private final boolean constantEnum;

		public StringToEnumsConverter(Class<T> enumType) {
			this.enumType = enumType;
			this.constantEnum = Arrays.stream(enumType.getInterfaces()).anyMatch(i -> i == DatabaseCode.class);
		}

		@Override
		public T convert(String source) {
			if (source.isEmpty()) {
				return null;
			}

			T[] constants = enumType.getEnumConstants();
			for (T c : constants) {
				if (constantEnum) {
					if (((DatabaseCode) c).getCode().equals(source.trim())) {
						return c;
					}
				} else {
					if (c.name().equals(source.trim())) {
						return c;
					}
				}
			}
			return null;
		}
	}
}