package com.gildedrose;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class TexttestFixture {
    public static void main(String[] args) throws IOException {

        File file = new File("target/test-output/TexttestFixture.received.txt");
        if (!file.getParentFile().mkdirs()) {
            throw new IOException("Couldn't create the output file");
        }

        try (PrintStream out = new PrintStream(file)) {
            out.println("OMGHAI!");

            Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20), //
                new Item("Aged Brie", 2, 0), //
                new Item("Elixir of the Mongoose", 5, 7), //
                new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
                new Item("Sulfuras, Hand of Ragnaros", -1, 80),
                new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
                new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
                // this conjured item does not work properly yet
                new Item("Conjured Mana Cake", 3, 6) };

            GildedRose app = new GildedRose(items);

            int days = 2;
            if (args.length > 0) {
                days = Integer.parseInt(args[0]) + 1;
            }

            for (int i = 0; i < days; i++) {
                out.println("-------- day " + i + " --------");
                out.println("name, sellIn, quality");
                for (Item item : items) {
                    out.println(item);
                }
                out.println();
                app.updateQuality();
            }
        } catch (final IOException e) {
            System.err.println("Problem! " + e.getMessage());
        }
    }

}
