package io.springmvc.typeconverter.service;

import io.micrometer.common.lang.Nullable;

import java.lang.invoke.TypeDescriptor;

public interface ConversionService {
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);
    boolean canConvert(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType);
    <T> T convert(@Nullable Object source, Class<T> targetType);
    Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType, TypeDescriptor targetType);
}