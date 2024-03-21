package io.everyone.travel.core.support;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;

public class PagingSupports {

	public static <T,R> PagingResponse<R> sliceConvertToPage(
		Pageable pageable,
		List<T> totalElementList,
		Function<? super T, R> convert
	) {
		List<T> sliced = sliceByPageable(pageable, totalElementList);
		List<R> converted = sliced.stream().map(convert).toList();
		int totalPages = (int) Math.ceil((double) totalElementList.size() / (double) pageable.getPageSize());
		return PagingResponse.<R>builder()
			.content(converted)
			.count(converted.size())
			.page(pageable.getPageNumber())
			.size(pageable.getPageSize())
			.totalElement(totalElementList.size())
			.totalPage(totalPages)
			.build();
	}

	public static <T> List<T> sliceByPageable(Pageable pageable, List<T> totalElementsList) {
		int totalElements = totalElementsList.size();
		int offset = (int)pageable.getOffset();
		int pageSize = pageable.getPageSize();
		List<T> slicedList;
		if (offset >= totalElements) {
			slicedList = List.of();
		} else if (offset + pageSize > totalElements) {
			slicedList = totalElementsList.subList(offset, totalElements);
		} else {
			slicedList = totalElementsList.subList(offset, offset + pageSize);
		}
		return slicedList;
	}

}
