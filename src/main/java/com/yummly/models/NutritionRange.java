/*
 * (C) Copyright 2013 Mads Kal�r
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.yummly.models;

public class NutritionRange {

	public enum NUTRITION {
		/**
		 * Potassium (grams)
		 */
		K,

		/**
		 * Sodium (grams)
		 */
		NA,

		/**
		 * Cholesterol (grams)
		 */
		CHOLE,

		/**
		 * Fatty acids, total trans (grams)
		 */
		FATRN,

		/**
		 * Fatty acids, total saturated (grams)
		 */
		FASAT,

		/**
		 * Carbohydrate, by difference (grams)
		 */
		CHOCDF,

		/**
		 * Fiber, total dietary (grams)
		 */
		FIBTG,

		/**
		 * Protein (grams)
		 */
		PROCNT,

		/**
		 * Vitamin C, total ascorbic acid (grams)
		 */
		VITC,

		/**
		 * Calcium, Ca (grams)
		 */
		CA,

		/**
		 * Iron, Fe (grams)
		 */
		FE,

		/**
		 * Sugars (grams)
		 */
		SUGAR,

		/**
		 * Energy (kcla)
		 */
		ENERC_KCAL,

		/**
		 * Fat
		 */
		FAT,

		/**
		 * Vitamin A (IU)
		 */
		VITA_IU
	}

	private NUTRITION nutrition;
	private Number min;
	private Number max;

	public NUTRITION getNutrition() {
		return nutrition;
	}

	public void setNutrition(NUTRITION nutrition) {
		this.nutrition = nutrition;
	}

	public Number getMin() {
		return min;
	}

	public void setMin(Number min) {
		this.min = min;
	}

	public Number getMax() {
		return max;
	}

	public void setMax(Number max) {
		this.max = max;
	}

}
