package com.digital.library.dlbms.models.converter;

import com.digital.library.dlbms.models.AvailabilityStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AvailabilityStatusConverter implements AttributeConverter<AvailabilityStatus, String> {


    @Override
    public String convertToDatabaseColumn(AvailabilityStatus availabilityStatus) {
        return availabilityStatus.getValue();
    }

    @Override
    public AvailabilityStatus convertToEntityAttribute(String s) {
        for (AvailabilityStatus availabilityStatus : AvailabilityStatus.values()) {
            if (availabilityStatus.getValue().equalsIgnoreCase(s)) {
                return availabilityStatus;
            }
        }
        return null;
    }
}
