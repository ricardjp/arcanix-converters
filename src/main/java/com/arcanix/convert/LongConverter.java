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

/**
 * @author Jean-Philippe Ricard (ricardjp@arcanix.com)
 * @since 1.0
 */
public final class LongConverter implements Converter<Long> {

    @Override
    public Long convert(final String value) throws ConversionException {
        try {
            return Long.valueOf(value);
        } catch (NumberFormatException e) {
            throw new ConversionException("Cannot convert value '" + value + "' to a Long");
        }
    }

}
