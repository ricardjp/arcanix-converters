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

import java.util.HashSet;
import java.util.Set;

/**
 * @author Jean-Philippe Ricard (ricardjp@arcanix.com)
 * @since 1.0
 */
public final class BooleanConverter implements Converter<Boolean> {

    /** The set of strings that are known to map to Boolean.TRUE. */
    private static final Set<String> TRUE_STRINGS = new HashSet<>();

    /** The set of strings that are known to map to Boolean.FALSE. */
    private static final Set<String> FALSE_STRINGS = new HashSet<>();

    static {
        TRUE_STRINGS.add("true");
        TRUE_STRINGS.add("yes");
        TRUE_STRINGS.add("y");
        TRUE_STRINGS.add("on");
        TRUE_STRINGS.add("1");

        FALSE_STRINGS.add("false");
        FALSE_STRINGS.add("no");
        FALSE_STRINGS.add("n");
        FALSE_STRINGS.add("off");
        FALSE_STRINGS.add("0");
    }


    @Override
    public Boolean convert(final String value) throws ConversionException {
        if (value == null) {
            return Boolean.FALSE;
        }
        String stringValue = value.toString().toLowerCase();
        if (TRUE_STRINGS.contains(stringValue)) {
            return Boolean.TRUE;
        } else if (FALSE_STRINGS.contains(stringValue)) {
            return Boolean.FALSE;
        }
        throw new ConversionException("Cannot convert value '" + value + "' to a Boolean");
    }

}
