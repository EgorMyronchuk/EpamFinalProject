package com.epam.rd.autocode.spring.project.dto.mapper;

import com.epam.rd.autocode.spring.project.dto.request.order.OrderReq;
import com.epam.rd.autocode.spring.project.dto.response.order.OrderRes;
import com.epam.rd.autocode.spring.project.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OrderMapper {

    Order toEntity(OrderReq dto);

    @Mapping(target = "clientEmail", source = "client.user.email")
    OrderRes toDto(Order entity);

}
