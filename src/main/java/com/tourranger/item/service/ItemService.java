package com.tourranger.item.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.tourranger.item.dto.ItemResponseDto;

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
	 * @param search 검색창에 입력한 키워드 정보
	 * @param condition 검색 당시 선택한 검색조건(최신순/ 가격높은 순/ 가격낮은 순)
	 * @param pageable 사용자가 보는 페이지정보
	 * @param countries 국가 EX)'중국, 일본, 대만'
	 * @param travelAgencies 여행사id EX) '1,2,3'
	 * @param startDate 출발 날짜 'YYYY-MM-DD'
	 * @param endDate  도착 날짜 'YYYY-MM-DD'
	 * @param priceValue 가격검색 비교를 위한 수치값
	 * @param priceAbove 가격검색 이상, 이하 결정값
	 * @return
	 */
	List<ItemResponseDto> getSearchedItemList(String search, String condition, Pageable pageable, String countries,
		String travelAgencies, String startDate, String endDate, Integer priceValue, boolean priceAbove);
}
