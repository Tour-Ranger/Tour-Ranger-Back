package com.tourranger.item.service;

import com.tourranger.item.dto.ItemResponseDto;
import org.springframework.data.domain.Pageable;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ItemService {
    /**
     * 상품 조회
     *
     * @param itemId 조회할 상품 ID
     * @return 조회한 상품 정보
     */
    ItemResponseDto getItem(Long itemId);

    /**
     * @param pageable 프론트엔드에서 보내주는 페이지정보 예: page=1&size=5
     * @return 해당페이지의 ItemList
     */
    List<ItemResponseDto> getItemList(Pageable pageable);

    /**
     * @param search    검색창에 입력한 String값
     * @param condition 검색 당시 선택한 검색조건(최신순/가격 높은순/가격 낮은 순)
     * @param pageable  사용자가 누른 페이지정보
     * @return 검색결과가 반영된 해당 페이지의 ItemList
     */
    List<ItemResponseDto> getSearchedItemList(String search, String condition, Pageable pageable) throws UnsupportedEncodingException;
}
