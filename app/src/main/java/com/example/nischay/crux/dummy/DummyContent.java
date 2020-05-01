package com.example.nischay.crux.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<DummyItem> ITEMS = new ArrayList<DummyItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, DummyItem> ITEM_MAP = new HashMap<String, DummyItem>();

    /**
     * A dummy item representing a piece of content.
     */
    public static class DummyItem {
        public final String id;
//        public final String content;
//        public final String details;
//        public final String details_2;
//        public final String image_url;

        public final String drink_name;
        public final String drink_image;
        public final String drink_category;
        public final String drink_alcoholic;
        public final String drink_instruction;
        public final String drink_ingredients;

        public DummyItem(String id, String drink_name, String drink_image, String drink_category, String drink_alcoholic, String drink_instruction, String drink_ingredients) {
            this.id = id;
            this.drink_name = drink_name;
            this.drink_image = drink_image;
            this.drink_category = drink_category;
            this.drink_alcoholic = drink_alcoholic;
            this.drink_instruction = drink_instruction;
            this.drink_ingredients = drink_ingredients;
        }

        @Override
        public String toString() {
            return drink_name;
        }
    }
}
