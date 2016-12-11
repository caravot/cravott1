package com.yummly;

import java.io.IOException;
import java.util.ArrayList;

import com.yummly.models.Flavors;
import com.yummly.models.NutritionRange;
import com.yummly.models.NutritionRange.NUTRITION;
import com.yummly.models.Recipe;
import com.yummly.models.SearchResult;

public class Main {

	public static void main(String[] args) {
		Yummly y = new Yummly("385fb868", "ba3cd23fe375d062943ece58724148ab");

		SearchResult result;

		try {
			Flavors minFlavors = new Flavors();
			minFlavors.setPiquant(0.8);
			ArrayList<NutritionRange> nutrition = new ArrayList<NutritionRange>();
			NutritionRange range = new NutritionRange();
			range.setMin(5);
			range.setMax(100);
			range.setNutrition(NUTRITION.PROCNT);
			nutrition.add(range);
			result = y.search("Chicken", true, null, null, -1, minFlavors, null, true, true, nutrition);

			Recipe recipe = y.getRecipe(result.getMatches().get(0).getId());
			System.out.println(String.format("%s, (%d), (%s) %s", recipe.getName(), recipe.getRating(), recipe.getId(), recipe.getImages().get(0).getHostedSmallUrl()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
