package com.example.happytownclone.entities.cadeau.gateway;

import com.example.happytownclone.commons.gateway.BaseGateway;
import com.example.happytownclone.entities.cadeau.model.Cadeau;

public interface CadeauGateway extends BaseGateway<Cadeau, String> {
    void attribuerCadeaux();
}
