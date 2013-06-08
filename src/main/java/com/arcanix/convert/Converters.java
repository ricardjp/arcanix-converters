/**
 * Copyright (C) 2012 Arcanix.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.arcanix.convert;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jean-Philippe Ricard (ricardjp@arcanix.com)
 * @since 1.0
 */
public final class Converters {

    private final Map<Class<?>, Converter<?>> converters = new HashMap<Class<?>, Converter<?>>();

    private static final Converters DEFAULT_CONVERTERS = new Converters();
    
    static {
    	DEFAULT_CONVERTERS.add(new BooleanConverter());
    	DEFAULT_CONVERTERS.add(new DoubleConverter());
    	DEFAULT_CONVERTERS.add(new IntegerConverter());
    	DEFAULT_CONVERTERS.add(new LongConverter());
    	DEFAULT_CONVERTERS.add(new StringConverter());
    }
    
    // TODO immutable Converters
    public static Converters getDefaultConverters() {
    	return DEFAULT_CONVERTERS;
    }
    
    public void add(final Converter<?> converter) {
        if (converter == null) {
            throw new NullPointerException("Converter cannot be null");
        }

        Class<?> targetClass = null;
        for (Type type : converter.getClass().getGenericInterfaces()) {
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                if (parameterizedType.getRawType().equals(Converter.class)) {
                    targetClass = (Class<?>) parameterizedType.getActualTypeArguments()[0];
                    break;
                }
            }
        }

        if (targetClass == null) {
            throw new RuntimeException("Could not found converter target type");
        }

        this.converters.put(targetClass, converter);
    }
    
    public Converter<?> getConverter(final Class<?> targetType) {
    	return this.converters.get(targetType);
    }

    public Object convert(final Class<?> targetType, final String value) throws ConversionException {
        if (value == null) {
            return null;
        }
        Converter<?> converter = (Converter<?>) this.converters.get(targetType);
        if (converter == null) {
            throw new ConversionException("Could not find a converter for target type: " + targetType.getName());
        }
        return converter.convert(value);
    }

}
