package com.codecta.gameservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventoryItemsPk implements Serializable {

    private Integer items;


    private Integer inventory;


}

