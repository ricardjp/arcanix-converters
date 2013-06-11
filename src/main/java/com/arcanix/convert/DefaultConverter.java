/**
 * Copyright (C) 2013 Jean-Philippe Ricard.
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

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @author ricardjp@arcanix.com (Jean-Philippe Ricard)
 */
public final class DefaultConverter {

	public Object convert(final Class<?> clazz, final String value) throws ConversionException {
		try {
		
			// look for string constructor
			try {
				Constructor<?> constructor = clazz.getConstructor(String.class);
				return constructor.newInstance(value);
			} catch (NoSuchMethodException e) {
				
				// look for static valueOf(String) method
				Method method = clazz.getMethod("valueOf", String.class);
				if (!Modifier.isStatic(method.getModifiers())) {
					throw new ConversionException("No String constructor found of static valueOf method found");
				}
				return method.invoke(null, value);
			}
		} catch (ReflectiveOperationException e) {
			throw new ConversionException(e);
		}
	}
	
}
