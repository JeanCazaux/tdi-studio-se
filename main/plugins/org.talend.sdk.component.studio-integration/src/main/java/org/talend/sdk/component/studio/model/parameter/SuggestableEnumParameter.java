/**
 * Copyright (C) 2006-2023 Talend Inc. - www.talend.com
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.talend.sdk.component.studio.model.parameter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.talend.core.model.process.IElement;
import org.talend.sdk.component.studio.model.action.SuggestionsAction;

public class SuggestableEnumParameter extends ValueSelectionParameter {
    // [id, label]
    private final LinkedHashMap<String, String> possibleEnumValues;

    public SuggestableEnumParameter(final IElement element, final SuggestionsAction action,
                                    final LinkedHashMap<String, String> possibleEnumValues) {
        super(element, action);
        this.possibleEnumValues = possibleEnumValues;
    }

    @Override
    public Object[] getListItemsValue() {
        return getListItemsDisplayCodeName();
    }

    @Override
    public String[] getListItemsDisplayCodeName() {
        // [label, id]
        final Map<String, String> suggestionValues = getSuggestionValues();

        return possibleEnumValues.keySet().stream()
                .filter(suggestionValues::containsValue)
                .toArray(String[]::new);
    }

    @Override
    public String[] getListItemsDisplayName() {
        // [label, id]
        final Map<String, String> suggestionValues = getSuggestionValues();

        return possibleEnumValues.entrySet().stream()
                .filter(entry -> suggestionValues.containsValue(entry.getKey()))
                .map(Entry::getValue)
                .toArray(String[]::new);
    }
}
