package com.study.board.demo.code.type;

import jakarta.persistence.AttributeConverter;

public class DatabaseCodeConverter<T extends Enum<T> & DatabaseCode> implements AttributeConverter<T, String> {
    private final Class<T> enumType;

    public DatabaseCodeConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public String convertToDatabaseColumn(T attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public T convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        for (T enumConstant : enumType.getEnumConstants()) {
            if (enumConstant.getCode().equals(dbData)) {
                return enumConstant;
            }
        }

        return null;
    }
}
