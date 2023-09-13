package com.tourranger.item.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;
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
	@Cacheable(key = "#search+'-'+#pageable.pageNumber", value = "SearchedItemList")
	public List<ItemResponseDto> getSearchedItemList(
		String search,
		String condition,
		Pageable pageable,
		String countries,
		String travelAgencies,
		String startDate,
		String endDate,
		Integer priceValue,
		boolean priceAbove) {
		String keyword;
		if (search.length() > 2) {
			keyword = splitSearchKeywordForNgram(search);
		} else {
			keyword = search;
		}

		//국가 정보 List저장
		List<String> countryList = null;
		if (!countries.isEmpty()) {
			countryList = new ArrayList<>();
			countryList = List.of(countries.split(","));
		}
		//여행사 id 타입 변환 String To Integer
		List<Integer> convertTravelAgencyList = null;
		if (!travelAgencies.isEmpty()) {
			String[] travelAgencyList = travelAgencies.split(",");
			convertTravelAgencyList = new ArrayList<>();
			for (String num : travelAgencyList) {
				convertTravelAgencyList.add(Integer.parseInt(num));
			}
		}

		return switch (condition) {
			case "latest" -> // 최신순
				itemRepository.searchItemOrderbyIdDesc(keyword, pageable, countryList, convertTravelAgencyList,
					startDate, endDate, priceValue, priceAbove).stream().map(ItemResponseDto::new).toList();
			case "priceLowToHigh" -> // 가격 낮은순
				itemRepository.searchItemOrderbyDiscountPrice(keyword, pageable, countryList, convertTravelAgencyList,
					startDate, endDate, priceValue, priceAbove).stream().map(ItemResponseDto::new).toList();
			case "priceHighToLow" -> // 가격 높은순
				itemRepository.searchItemOrderbyDiscountPriceDesc(keyword, pageable, countryList,
						convertTravelAgencyList, startDate, endDate, priceValue, priceAbove)
					.stream()
					.map(ItemResponseDto::new)
					.toList();
			case "noFilterTest" ->//필터 미적용 테스트
				itemRepository.searchItemNoFilterTest(keyword, pageable).stream().map(ItemResponseDto::new).toList();
			case "likeTest" -> //LIKE문 조회 테스트
				itemRepository.searchItemLikeTest(keyword, pageable, countryList, convertTravelAgencyList, startDate,
					endDate, priceValue, priceAbove).stream().map(ItemResponseDto::new).toList();
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