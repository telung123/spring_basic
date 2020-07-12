package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.Item;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.repository.ItemRepository;
import com.example.study.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ItemApiLogicService extends BaseService<ItemApiRequest, ItemApiResponse, Item> {
    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest body = request.getData();

        Item item = Item.builder()
            .status(body.getStatus())
            .name(body.getName())
            .title(body.getTitle())
            .content(body.getTitle())
            .price(body.getPrice())
            .brandName(body.getName())
            .registeredAt(LocalDateTime.now())
            .partner(partnerRepository.getOne(body.getPartnerId()))
            .build();

        Item newItem = baseRepository.save(item);
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        Optional<Item> optional = baseRepository.findById(id);
        return optional
                .map(item-> response(item))
                .orElseGet(()->Header.ERROR("아이템 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        // 리퀘스트의 Body 를 받음
        ItemApiRequest body = request.getData();

        // body에 선언된 ID로 row find
        return baseRepository.findById(body.getId())
            .map(entityItem ->{ // id에 해당하는 item 이 있는 경우
                entityItem
                        .setStatus(body.getStatus()) // body로 넘어온 data를 해당 id에 set해줌
                        .setName(body.getName())
                        .setTitle(body.getTitle())
                        .setContent(body.getContent())
                        .setBrandName(body.getName())
                        .setUnregisteredAt(body.getUnregisteredAt())
                        .setRegisteredAt(body.getRegisteredAt());
                return entityItem; // 갱신된 Item 다시 return
            })
            .map(newItem->baseRepository.save(newItem))
            .map(item-> response(item))
            .orElseGet(()->Header.ERROR("아이템 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> optional = baseRepository.findById(id);

        return optional
            .map(item->{
                baseRepository.delete(item);
                return Header.OK(); // delete 는 return type 없기 때문에 Item을 반환하는게 아님
            })
            .orElseGet(()->Header.ERROR(""));
    }

    // 항상 response 를 반환하기 때문에 공통 method로 작성
    private Header<ItemApiResponse> response(Item item) {
        ItemApiResponse body = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .title(item.getTitle())
                .brandName(item.getBrandName())
                .price(item.getPrice())
                .registeredAt(item.getRegisteredAt())
                .unregisteredAt(item.getUnregisteredAt())
                .partnerId(item.getPartner().getId())
                .build();
        return Header.OK(body);
    }
}
