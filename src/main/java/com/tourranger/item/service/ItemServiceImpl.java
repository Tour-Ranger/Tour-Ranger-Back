package com.tourranger.item.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tourranger.common.error.CustomErrorCode;
import com.tourranger.common.exception.CustomException;
import com.tourranger.item.dto.ItemResponseDto;
import com.tourranger.item.entity.Item;
import com.tourranger.item.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public ItemResponseDto getItem(Long itemId) {
        Item item = findItem(itemId);
        ItemResponseDto responseDto = new ItemResponseDto(item);
        return responseDto;
    }

    @Override
    public List<ItemResponseDto> getItemList(Pageable pageable) {
        return itemRepository.findAllByOrderById(pageable).stream().map(ItemResponseDto::new).toList();
    }

    @Override
    public List<ItemResponseDto> getSearchedItemList(String search, String condition, Pageable pageable) {
        String keyword;
        if(search.length()>2){
            keyword = splitSearchKeywordForNgram(search);
        }
        else{
            keyword = search;
        }

        return switch (condition) {
            case "latest" -> // 최신순
                    itemRepository.searchItemOrderbyIdDesc(keyword, pageable).stream().map(ItemResponseDto::new).toList();
            case "priceLowToHigh" -> // 가격 낮은순
                    itemRepository.searchItemOrderbyDiscountPrice(keyword, pageable).stream().map(ItemResponseDto::new).toList();
            case "priceHighToLow" -> // 가격 높은순
                    itemRepository.searchItemOrderbyDiscountPriceDesc(keyword, pageable).stream().map(ItemResponseDto::new).toList();
            default -> null;
        };
    }

    public Item findItem(Long id) {
        return itemRepository.findById(id).orElseThrow(() ->
                new CustomException(CustomErrorCode.ITEM_NOT_FOUND, null)
        );
    }

    public Item findItemPessimisticLock(Long id){
        return  itemRepository.findByIdWithPessimisticLock(id).orElseThrow(()->
            new CustomException(CustomErrorCode.ITEM_NOT_FOUND, null)
        );
    }

    public String splitSearchKeywordForNgram(String search) {
        // 공백을 기준으로 문자열 분리
        String[] tokens = search.split(" ");

        // n-gram 형식으로 변환
        StringBuilder ngramSearch = new StringBuilder();
        for (int i = 0; i < tokens.length; i++) {
            for (int j = 0; j < tokens[i].length(); j++) {
                if (j + 2 <= tokens[i].length()) {
                    if (ngramSearch.length() > 0) {
                        ngramSearch.append(" ");
                    }
                    ngramSearch.append("+").append(tokens[i].substring(j, j + 2));
                }
            }
        }

        return ngramSearch.toString();
    }
}
