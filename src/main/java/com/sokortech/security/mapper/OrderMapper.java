package com.sokortech.security.mapper;

import com.sokortech.security.config.MapperConfig;
import com.sokortech.security.dto.order.OrderDto;
import com.sokortech.security.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {

    @Mapping(source = "user.id", target = "userId")
    OrderDto toDto(Order order);
}

