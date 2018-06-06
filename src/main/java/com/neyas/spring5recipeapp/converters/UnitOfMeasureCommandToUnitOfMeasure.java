package com.neyas.spring5recipeapp.converters;

import com.neyas.spring5recipeapp.commands.UnitOfMeasureCommand;
import com.neyas.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements org.springframework.core.convert.converter.Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {
        if (unitOfMeasureCommand == null) {
            return null;
        }
        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(unitOfMeasureCommand.getId());
        uom.setDescription(unitOfMeasureCommand.getDescription());
        return uom;
    }
}
