package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.mapping.MemberFavor;

import java.util.List;
import java.util.stream.Collectors;

public class MemberFavorConverter {
    public static List<MemberFavor> toMemberFavorList(List<FoodCategory> foodCategoryList) {
        return foodCategoryList.stream()
                .map(foodCategory ->
                        MemberFavor.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
